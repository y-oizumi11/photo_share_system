<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actUser" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="actImg" value="${ForwardConst.ACT_IMG.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>画像 詳細ページ</h2>
           <p><img alt="投稿画像" src="/upload/${img.address}"></p>
           <p>ユーザー</p>
           <p><c:out value="${image.user.code}" /></p>
           <br/><br/>
           <p>コメント</p>
           <p><c:out value="${image.content}" /></p>
           <br/><br/>
           <p>登録日時</p>
           <fmt:parseDate value="${image.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="imageDay" type="date" />
           <p><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
           <br/><br/>

            <c:if test="${sessionScope.login_user.id == image.user.id}">
            <p>
                <a href="<c:url value='?action=${actImg}&command=${commEdt}&id=${report.id}' />">この投稿を編集する</a>
            </p>
            </c:if>

        <p>
            <a href="<c:url value='?action=${actImg}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>