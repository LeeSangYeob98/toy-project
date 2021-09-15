<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 공통헤드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <% String contextPath = request.getContextPath(); %> --%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%-- == getPageContextPath().getRequest().getContextPath()
el 표현식 태그(${})는 getter 메서드를 호출한다. --%>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<script type="text/javascript" src="${contextPath}/resources/js/webUtil.js"></script>
