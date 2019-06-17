<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>
<%@ page errorPage="/error.jsp" %>
<link rel="stylesheet" type="text/css" href="../../style/settingsStyle.css">


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

<c:if test="${mealList.size()!=0}">

    <table id="dayMeal">
        <tr>
            <th><fmt:message key="page.daymeal.mealid"/>Meal ID</th>
            <th><fmt:message key="page.daymeal.food"/>Food name</th>
            <th><fmt:message key="page.daymeal.proteins"/>Proteins</th>
            <th><fmt:message key="page.daymeal.lipids"/>Lipids</th>
            <th><fmt:message key="page.daymeal.carbs"/>Carbs</th>
            <th><fmt:message key="page.daymeal.calories"/>Calories</th>
            <th><fmt:message key="page.daymeal.number"/>Number</th>
            <th><fmt:message key="page.daymeal.datetime"/>Date/Time</th>
        </tr>
        <c:forEach items="${mealList}" var="element">

            <tr>
                <td>${element.getId()}</td>
                <td>${element.getFood().getFoodName()}</td>
                <td>${element.getFood().getProteins()}</td>
                <td>${element.getFood().getLipids()}</td>
                <td>${element.getFood().getCarbs()}</td>
                <td>${element.getFood().getCalories()}</td>
                <td>${element.getNumber()}</td>
                <td>${element.getDateTime()}</td>

            </tr>

        </c:forEach>
    </table>
    <br>
    <br>

    <div align="center">
        <div class="settings_container">
            <form>
                <div class="row">
                    <div class="col-25">
                        <label for="mealId"><fmt:message key="page.daymeal.id"/></label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="settings_label" id="mealId" name="mealId"
                               placeholder="<fmt:message key="page.daymeal.id.placeholder"/>">
                    </div>
                </div>
                <br>
                <div class="row">
                    <input type="button" name="mealDeleteButton" id="mealDeleteButton"
                           value="<fmt:message key="page.daymeal.id.button"/>">
                </div>
            </form>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</c:if>
<div align="center">
    <h1><fmt:message key="page.daymeal.meallist"/>DayMeal list will appear here after you track first meal.</h1>
</div>
<c:if test="${calories_norm != null}">

</c:if>
<script>
    $("#mealDeleteButton").on('click', function (event) {
        var mealId = $("#mealId").val();
        var mealIdRegex = new RegExp("^([0-9]{1,3})$");

        if (mealIdRegex.test(mealId)) {
            console.log("MealId is Valid");
        } else {
            event.preventDefault();
            console.log("MealId is Invalid");
            alert("<fmt:message key="page.daymeal.id.wrong"/>");
        }

        $.ajax({
            url: "/fts/deleteMeal",
            method: "POST",
            data: {
                meal_id: mealId,
                client_id:<c:out value='${client_id}' />
            },
            success: function (result) {
                if (result === "success") {
                    alert("<fmt:message key="page.meal.deleted"/>");
                    location.reload();
                    resetData();
                } else if (result === "wrongId") {
                    alert("<fmt:message key="page.daymeal.wrong.id"/>");
                }
                else {
                    alert("<fmt:message key="page.food.problem"/>");
                    console.log("Unknown response from Servlet.");
                }
                console.log("Servlet response: " + result);
            },
            error: function (result) {
                alert("Error!")
            }
        });

    });

    function resetData() {
        $("#mealId").val("");
    }
</script>