<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actUser" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="actImg" value="${ForwardConst.ACT_IMG.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commIn" value="${ForwardConst.CMD_SHOW_LOGIN.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_LOGOUT.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title><c:out value="写真共有システム"></c:out></title>
<link rel = "stylesheet" href="<c:url value='/css/reset.css' />">
<link rel = "stylesheet" href="<c:url value='/css/style.css' />">
</head>

<body>
   <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/?action=${actImg}&command=${commIdx}' />">Photo Share System</a></h1>&nbsp;&nbsp;&nbsp;
                <c:choose>
                <c:when test="${sessionScope.login_user != null}">
                    <c:if test="${sessionScope.login_user.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                        <a href="<c:url value='?action=${actUser}&command=${commIdx}' />">ユーザー管理</a>&nbsp;
                    </c:if>
                    <a href="<c:url value='?action=${actTop}&command=${commIdx}' />">My page</a>&nbsp;
                </c:when>
                <c:otherwise>
                 <a href="<c:url value='?action=${actAuth}&command=${commIn}' />">ログイン</a>&nbsp;
                 <a href="<c:url value='?action=${actUser}&command=${commNew}'/>">新規登録</a>
                </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${sessionScope.login_user != null}">
                <div id="user_code">
                    <c:out value="${sessionScope.login_user.code}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='?action=${actAuth}&command=${commOut}' />">ログアウト</a>
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Oizumi.</div>
    </div>
</body>
</html>