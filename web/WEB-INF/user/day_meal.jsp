<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>


<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="day.meal.title"/></h1>
<br>

<c:if test="${calories_norm == null}">
    <div align="center">
        <h2>To track a meal, please, create a client account!</h2>
        <br>
        <div class="row">
            <button onclick="location.href='${pageContext.request.contextPath}/fts/userSettings'" type="button">
                Create account
            </button>
        </div>
    </div>

</c:if>


<c:if test="${mealList != null}">
    <c:forEach items="${mealList}" var="element">

        <c:set var="caloriesNorm" value="${calories_norm}"/>
        <c:out value="${client_id}"/>
        <c:out value="${calories_to_norm}"/>
        <c:out value="${calories_status}"/>
        <c:out value="${calories_norm}"/>


        <tr>
            <td>${element.getFood()}</td>
            <td>${element.getNumber()}</td>
        </tr>
    </c:forEach>
</c:if>