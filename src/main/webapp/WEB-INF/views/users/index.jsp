<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "constants.AttributeConst" %>
<%@ page import = "constants.ForwardConst" %>
<c:set var = "actUser" value="${ForwardConst.ACT_USER.getValue() }" />
<c:set var = "commShow" value="${ForwardConst.CMD_SHOW.getValue() }" />
<c:set var = "commNew" value="${ForwardConst.CMD_NEW.getValue() }" />
<c:set var = "commId" value="${ForwardConst.CMD_INDEX.getValue() }" />

<c:import url="../layout/app.jsp">
  <c:param name="content">
    <c:if test="${flush != null}">
      <div id = "flush_access">
        <c:out value="${flush }"></c:out>
      </div>
    </c:if>

  <h2>投稿画像一覧</h2>

</c:param>
</c:import>

