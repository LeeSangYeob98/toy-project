package com.kh.toy.admin.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.code.member.MemberGrade;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.exception.PageNotFoundException;
import com.kh.toy.member.model.dto.Member;
import com.kh.toy.member.model.service.MemberService;

@WebServlet("/admin/member/*")
public class AdminMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       
    public AdminMemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length - 1]) {
		case "member-list":
			memberList(request,response);
			break;
		case "leave":
			deleteMember(request,response);
			break;

		default: throw new PageNotFoundException();
		}
	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		Member member = memberService.selectMemberById(userId);
		
		if(member == null) {
			request.setAttribute("msg", "존재하지 않는 회원입니다.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/common/result").forward(request, response);
			return;
		}
		
		String memberGrade = MemberGrade.valueOf(member.getGrade()).ROLE;
		
		if(memberGrade.contains("AD")) {
			request.setAttribute("msg", "관리자계정은 탈퇴할 수 없습니다.");
			request.setAttribute("back", "a");
			request.getRequestDispatcher("/common/result").forward(request, response);
			return;
		}else if(memberGrade.contains("ME")) {
			request.setAttribute("msg", "탈퇴처리가 완료되었습니다.");
			request.setAttribute("url", "/member/member-list");
			request.getRequestDispatcher("/common/result").forward(request, response);
			return;
		}
		// 약간 수정 필요
	}

	private void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> memberList = memberService.selectMemberList();
		request.setAttribute("members", memberList);
		request.getRequestDispatcher("/admin/member-list").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
