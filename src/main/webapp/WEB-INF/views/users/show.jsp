<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actUser" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>id : ${user.id} のアカウント 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>ユーザーID</th>
                    <td><c:out value="${user.code}" /></td>
                </tr>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${user.name}" /></td>
                </tr>
                <tr>
                    <th>アカウントの種類</th>
                    <td><c:choose>
                            <c:when test="${employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">管理者</c:when>
                            <c:otherwise>一般会員</c:otherwise>
                        </c:choose></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="<c:url value='?action=${actUser}&command=${commEdit}&id=${user.id}' />">このユーザー情報を編集する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actUser}&command=${commIdx}' />">トップページに戻る</a>
        </p>
    </c:param>
</c:import>