<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>
<link rel="stylesheet" type="text/css" href="../../style/settingsStyle.css">
<%@ page errorPage="/error.jsp" %>


<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="meal.statistic.title"/></h1>
<br>
<c:if test="${calories_norm==null}">

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

<c:if test="${calories_norm!=null}">

    <c:if test="${trackList.size()!=0}">

        <table id="dayMeal">
            <tr>
                <th><fmt:message key="page.clienttrack.date"/>Meal ID</th>
                <th><fmt:message key="page.clienttrack.caloriesstatus"/>Calories Status</th>
                <th><fmt:message key="page.clienttrack.caloriestonorm"/>Calories to Norm</th>
            </tr>
            <c:forEach items="${trackList}" var="element">

                <tr>
                    <td>${element.getDate()}</td>
                    <td>${element.getCaloriesStatus()}</td>
                    <td>${element.getCaloriesToNorm()}</td>
                </tr>

            </c:forEach>
        </table>
    </c:if>

    <c:if test="${trackList.size()==0}">
        <div align="center">
        <h1><fmt:message key="page.clienttrack.tracklist"/>Track list will appear here next day after you make meal track.</h1>
        </div>
    </c:if>

</c:if>