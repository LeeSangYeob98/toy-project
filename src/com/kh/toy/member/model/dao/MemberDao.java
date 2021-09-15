package com.kh.toy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.toy.common.db.JDBCTemplate;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.member.model.dto.Member;


//7월 13일 저녁
//$매우 중요. JDBC의 5단계는 반드시 알아둬야 한다.
//DAO(Data Access Object)
//DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 하나의 메서드에서 하나의 쿼리만 처리하도록 작성
public class MemberDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public MemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	//사용자의 id와 password를 전달받아서
	//id와 password가 일치하는 회원을 조회하는 메서드
	public Member memberAuthenticate(String userId,String password
			,Connection conn) { 
		//예외를 처리하지 않고 던져서 
		//Service에서 커밋/롤백을 결정할 수 있게 처리
		//시간이 지남에 따라 불필요하게 CheckedException으로 던지지 않고
		//UnCheckedException으로 던지게 바뀜
		
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member "
				+ "where user_id = ? and password = ?";
		
		try {
			//DB와 연결 수립
			//쿼리 실행용 객체 생성
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			//쿼리 실행 후 결과 반환
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm); //conn을 Dao가 닫으면 안된다.
			//PreparedStatement 는 Statement의 후손 객체(다형성)
		}
		return member;
	}
	
	public Member selectMemberById(String userId, Connection conn) {
		//dml
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where user_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			//*** 가져온 값이 null일 수도 있으므로 확인 작업 필요
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return member;
	}
	//크기를 몰라도 되는 list 사용
	public List<Member> selectMemberList(Connection conn) {
		//dml
		List<Member> memberList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member";
		
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = convertRowToMember(rset);
				//다중 행 결과값을 한 줄 씩 반환받아서 List에 추가
				memberList.add(member);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return memberList;
	}
	
	public int insertMember(Member member, Connection conn) {
		//dml
		int res = 0;
		PreparedStatement pstm = null;
		String query = "insert into "
				+ "member(user_id,password,email,tell) "
				+ "values(?, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getEmail());
			pstm.setString(4, member.getTell());
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int updateMember(Member member, Connection conn) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query 
			= "update member set password = ? where user_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getPassword());
			pstm.setString(2, member.getUserId());
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int deleteMember(String userId, Connection conn) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "delete from member where user_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	private Member convertRowToMember(ResultSet rset) 
			throws SQLException {
		Member member = new Member();
		member.setUserId(rset.getString("user_id"));
		member.setPassword(rset.getString("password"));
		member.setEmail(rset.getString("email"));
		member.setGrade(rset.getString("grade"));
		member.setTell(rset.getString("tell"));
		member.setRegDate(rset.getDate("reg_date"));
		member.setRentable(rset.getDate("rentable_date"));
		member.setIsLeave(rset.getInt("is_leave"));
		return member;
	}
	
}
