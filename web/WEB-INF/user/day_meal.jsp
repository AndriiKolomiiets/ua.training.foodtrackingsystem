<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>


<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="day.meal.title"/></h1>
<br>

<c:forEach items="myList" var="element">
    <tr>
        <td>${element.getStatus()}</td>
        <td>${element.getRequestType()}</td>
        <td>${element.getRequestedFor()}</td>
        <td>${element.getTimeSubmitted()}</td>
    </tr>
</c:forEach>