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

        <h2> ${user.code} のアカウント 詳細ページ</h2>
            <div>
              <p>ユーザーID</p>
              <p><c:out value="${user.code}" /></p>
            </div>

              <div>
                <p>アカウントの種類</p>
                  <c:choose>
                    <c:when test="${user.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">管理者</c:when>
                    <c:otherwise>一般会員</c:otherwise>
                   </c:choose>
              </div>

        <p>
            <a href="<c:url value='?action=${actUser}&command=${commEdit}&id=${user.id}' />">このユーザー情報を編集する</a>
        </p>
        <p>
            <a href="<c:url value='?action=${actUser}&command=${commIdx}' />">トップページに戻る</a>
        </p>
    </c:param>
</c:import>