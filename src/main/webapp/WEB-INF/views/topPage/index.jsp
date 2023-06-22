<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actUser" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="actImg" value="${ForwardConst.ACT_IMG.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>My Page</h2>
        <h3>【投稿画像 一覧】</h3>
        <table id="report_list">
            <tbody>
              <tr>
                <th class="image_address"></th>
                <th class= "image_code">ユーザー名</th>
                <th class = "image_title">タイトル</th>
                <th class="image_action">詳細</th>
              </tr>

                <c:forEach var="image" items="${images}" varStatus="status">
                    <fmt:parseDate value="${image.imageDate}" pattern="yyyy-MM-dd" var="imageDay" type="date" />
                     <tr class="row${status.count % 2}">
                        <td class= "image_address"><img src="/upload/${image.address}"></td>
                        <td class="image_code"><c:out value="${image.user.code}" /></td>
                        <td class="image_title">${image.title}</td>
                        <td class="image_action"><a href="<c:url value='?action=${actImg}&command=${commShow}&id=${image.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${images_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((images_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actTop}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actImg}&command=${commNew}' />">新規画像の投稿</a></p>

    </c:param>
</c:import>