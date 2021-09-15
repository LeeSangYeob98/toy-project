package com.kh.toy.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.toy.member.validator.JoinForm;

/**
 * Servlet Filter implementation class ValidatorFilter
 */
public class ValidatorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidatorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String[] uriArr = httpRequest.getRequestURI().split("/");
		
		String redirectURI = null;
		
		if(uriArr.length != 0) {
			switch (uriArr[1]) {
			case "member":
				redirectURI = memberValidation(httpRequest,httpResponse,uriArr);
				break;
			
			default:
				break;
			}
			
			if(redirectURI != null) {
				httpResponse.sendRedirect(redirectURI);
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private String memberValidation(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) throws IOException, ServletException {
		
		String redirectURI = null;
		
		switch (uriArr[2]) {
		case "join":
			JoinForm joinForm = new JoinForm(httpRequest);
			if(!joinForm.test()) {
				redirectURI = "/member/join-form?err=1"; // 분기용 에러파라미터
				return redirectURI;
			}
			break;

		default:
			break;
		}
		
		return redirectURI; // 검증에 성공하면 null 반환
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
