<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>
<%@ page errorPage="/error.jsp" %>
<link rel="stylesheet" type="text/css" href="../../style/settingsStyle.css">


<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="page.admin.title"/></h1>
<br>

<%--<c:if test="${calories_norm == null}">
    <div align="center">
        <h2>To track a meal, please, create a client account!</h2>
        <br>
        <div class="row">
            <button onclick="location.href='${pageContext.request.contextPath}/fts/userSettings'" type="button">
                Create account
            </button>
        </div>
    </div>

</c:if>--%>

<c:if test="${clientList.size()!=0}">

    <table id="dayMeal">
        <tr>
            <th><fmt:message key="page.admin.userlogin"/>Meal ID</th>
            <th><fmt:message key="page.admin.birthdate"/>Food name</th>
            <th><fmt:message key="page.admin.caloriesnorm"/>Proteins</th>
            <th><fmt:message key="page.admin.stylecoeff"/>Lipids</th>
        </tr>
        <c:forEach items="${clientList}" var="element">

            <tr>
                <td>${element.getUser().getUsername()}</td>
                <td>${element.getBirthDate()}</td>
                <td>${element.getCaloriesNorm()}</td>
                <td>${element.getLifeStyleCoefficient()}</td>

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
                        <label for="mealId"><fmt:message key="page.admin.id"/></label>
                    </div>
                    <div class="col-75">
                        <input type="text" class="settings_label" id="mealId" name="mealId"
                               placeholder="<fmt:message key="page.admin.id.placeholder"/>">
                    </div>
                </div>
                <br>
                <div class="row">
                    <input type="button" name="clientDeleteButton" id="clientDeleteButton"
                           value="<fmt:message key="page.admin.id.button"/>">
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

<c:if test="${clientList == null}">
    <div align="center">
        <h1><fmt:message key="page.admin.userlist"/>There are no users in database.</h1>
    </div>
</c:if>
<script>
    $("#clientDeleteButton").on('click', function (event) {
        var mealId = $("#mealId").val();
      /*  var mealIdRegex = new RegExp("^([0-9]{1,3})$");

        if (mealIdRegex.test(mealId)) {
            console.log("MealId is Valid");
        } else {
            event.preventDefault();
            console.log("MealId is Invalid");
            alert("<fmt:message key="page.daymeal.id.wrong"/>");
        }*/

        $.ajax({
            url: "/fts/deleteUser",
            method: "POST",
            data: {
                user_id: mealId,
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
