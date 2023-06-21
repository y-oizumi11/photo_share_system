<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<form action="/photo_share_system/ImageAction/" method="post" enctype="multipart/form-data">
<fmt:parseDate value="${image.image_date}" pattern="yyyy-MM-dd" var="imageDay" type="date" />
<label for="${AttributeConst.IMG_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.IMG_DATE.getValue()}" id="${AttributeConst.IMG_DATE.getValue()}" value="<fmt:formatDate value='${imageDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label>ユーザー</label><br />
<c:out value="${sessionScope.login_user.code}" />
<br /><br />

<label for="${AttributeConst.IMG_TITLE.getValue()}">タイトル</label><br />
<input type="text" name="${AttributeConst.IMG_TITLE.getValue()}" id="${AttributeConst.IMG_TITLE.getValue()}" value="${image.title}" />
<br /><br />

<label for="${AttributeConst.IMG_ADDRESS.getValue()}">画像</label>
<input type="file" name="${AttributeConst.IMG_ADDRESS.getValue()}" id="${AttributeConst.IMG_ADDRESS.getValue()}" value="${image.address}" />

<label for="${AttributeConst.IMG_CONTENT.getValue()}">コメント</label><br />
<textarea  name="${AttributeConst.IMG_CONTENT.getValue()}" id="${AttributeConst.IMG_CONTENT.getValue()}" rows="10" cols="50">${image.content}</textarea>
<br /><br />
<input type="hidden" name="${AttributeConst.IMG_ID.getValue()}" value="${image.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>
</form>