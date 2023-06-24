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

        <table>
            <tbody>
                <tr>
                    <th>ユーザー</th>
                    <td><c:out value="${image.user.code}" /></td>
                </tr>
                <tr>
                    <th>コメント</th>
                    <td><pre><c:out value="${image.comment}" /></pre></td>
                </tr>
                <tr>
                    <th>投稿日時</th>
                    <fmt:parseDate value="${image.created_at}" pattern="yyyy-MM-dd" var="created_at" type="date" />
                    <td><fmt:formatDate value="${created_at}" pattern="yyyy-MM-dd" /></td>
                </tr>
            </tbody>
        </table>

                 <th>画像</th>
                 <img class="uploadFile" src="<c:url value="/upload/${image.filePath}" />" />

            <c:if test="${sessionScope.login_user.id == image.user.id}">
            <p>
                <a href="<c:url value='?action=${actImg}&command=${commEdt}&id=${image.id}' />">この投稿を編集する</a>
            </p>
            </c:if>

        <p>
            <a href="<c:url value='?action=${actImg}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>