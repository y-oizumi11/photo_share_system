<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="${AttributeConst.U_CODE.getValue()}">ユーザーID</label><br />
<input type="text" name="${AttributeConst.U_CODE.getValue()}" id="${AttributeConst.U_CODE.getValue()}" value="${user.code}" />
<br /><br />

<label for="${AttributeConst.U_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.U_NAME.getValue()}" id="${AttributeConst.U_NAME.getValue()}" value="${user.name}" />
<br /><br />

<label for="${AttributeConst.U_PASS.getValue()}">パスワード</label><br />
<input type="password" name="${AttributeConst.U_PASS.getValue()}" id="${AttributeConst.U_PASS.getValue()}" />
<br /><br />

<label for="${AttributeConst.U_CONTENT.getValue()}">コメント</label><br />
<input type="text" name="${AttributeConst.U_CONTENT.getValue()}" id="${AttributeConst.U_CONTENT.getValue()}" value="${user.u_comment}" />
<br /><br />

<label for="${AttributeConst.U_ADMIN_FLG.getValue()}">権限</label><br />
    <input type="radio" name="${AttributeConst.U_ADMIN_FLG.getValue()}" id="${AttributeConst.U_ADMIN_FLG.getValue()}" value="${AttributeConst.ROLE_GENERAL.getIntegerValue()}">一般
    <input type="radio" name="${AttributeConst.U_ADMIN_FLG.getValue()}" id="${AttributeConst.U_ADMIN_FLG.getValue()}" value="${AttributeConst.ROLE_ADMIN.getIntegerValue()}">管理者
<br /><br />

<input type="hidden" name="${AttributeConst.U_ID.getValue()}" value="${user.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>