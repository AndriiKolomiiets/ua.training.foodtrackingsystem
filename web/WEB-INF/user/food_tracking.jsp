<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>
<%@ page errorPage="/error.jsp"%>
<link rel="stylesheet" type="text/css" href="../../style/settingsStyle.css">
<link rel="stylesheet" type="text/css" href="../../style/addNewFoodStyle.css">

<script>
    function preback() {
        window.history.forward();
    }
    setTimeout("preback()", 0);
    window.onload = function () {
        null
    };
</script>

<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="food.tracking.title"/></h1>
<br>

<c:if test="${calories_norm!=null}">

</c:if>
<c:if test="${calories_norm==null}">

    <div align="center">
        <h2><fmt:message key="page.daymeal.client.doesnt.exist"/></h2>
        <br>
        <div class="row">
            <button onclick="location.href='${pageContext.request.contextPath}/fts/userSettings'" type="button">
                Create account
            </button>
        </div>
    </div>

</c:if>

<c:if test="${calories_norm!=null}">

<div align="center">
    <div class="settings_container">
        <form <%--action="http://localhost:8080/fts/trackFood"--%>>
       <div class="row">
                <div class="col-25">
                    <label for="food"><fmt:message key="page.foodtracking.food"/></label>
                </div>
                <div class="col-75">
                    <select id="food"  class="select_life_style" name="food">
                        <option value="soup"><fmt:message key="page.foodtracking.soup"/></option>
                        <option value="porridge"><fmt:message key="page.foodtracking.porridge"/></option>
                        <option value="hamburger"><fmt:message key="page.foodtracking.hamburger"/></option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="number"><fmt:message key="page.foodtracking.number"/></label>
                </div>
                <div class="col-75">
                    <input type="text" class="settings_label" id="number" name="number" placeholder="<fmt:message key="page.foodtracking.number.placeholder"/>">
                </div>
            </div>
            <br>
            <div class="row">
                <input type="button" name="settings_button" id="settings_button" value="<fmt:message key="page.foodtracking.button"/>">
            </div>
        </form>
    </div>
</div>

    <button class="open-button" onclick="openForm()"><fmt:message key="page.foodtracking.mealform.header"/></button>

    <div class="form-popup" id="newFoodForm">
        <form <%--action="http://localhost:8080/fts/trackFood"--%> class="form-container">
            <h1>New food</h1>

            <label for="foodName"><b><fmt:message key="page.foodtracking.foodname"/></b></label>
            <input type="text" placeholder="<fmt:message key="page.foodtracking.foodname.holder"/>"
                   id="foodName" name="foodName" required>

            <label for="newFoodNumber"><b><fmt:message key="page.foodtracking.foodnumber"/></b></label>
            <input type="text" placeholder="<fmt:message key="page.foodtracking.foodnumber.holder"/>"
                   id="newFoodNumber" name="newFoodNumber" required>

            <label for="calories"><b><fmt:message key="page.foodtracking.calories"/></b></label>
            <input type="text" placeholder="<fmt:message key="page.foodtracking.calories.holder"/>"
                   id="calories" name="calories" required>

            <label for="proteins"><b><fmt:message key="page.foodtracking.proteins"/></b></label>
            <input type="text" placeholder="<fmt:message key="page.foodtracking.proteins.holder"/>"
                   id="proteins" name="proteins" required>

            <label for="lipids"><b><fmt:message key="page.foodtracking.lipids"/></b></label>
            <input type="text" placeholder="<fmt:message key="page.foodtracking.lipids.holder"/>"
                   id="lipids" name="lipids" required>

            <label for="carbs"><b><fmt:message key="page.foodtracking.carbohydrates"/></b></label>
            <input type="text" placeholder="<fmt:message key="page.foodtracking.carbohydrates.holder"/>"
                   id="carbs" name="carbs" required>

            <button type="button" id="newFoodButton" class="btn">
                <fmt:message key="page.foodtracking.foodbutton"/></button>
            <button type="button" class="btn cancel" onclick="closeForm()">
                <fmt:message key="page.foodtracking.foodbutton.close"/></button>
        </form>
    </div>

</c:if>

<script>
    function openForm() {
        document.getElementById("newFoodForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("newFoodForm").style.display = "none";
    }
</script>

<script>
    $("#newFoodButton").on('click', function () {
        var foodName = $("#foodName").val();
        var newFoodNumber = $("#newFoodNumber").val();
        var calories = $("#calories").val();
        var proteins = $("#proteins").val();
        var lipids = $("#lipids").val();
        var carbs = $("#carbs").val();
        // var password = $("#password").val();
        var foodNumberRegex = new RegExp("^([1-9])$");
        var foodRegex = new RegExp("[A-za-z0-9]{4,9}");
        var caloriesRegex = new RegExp("^([0-9]{1,3})$")
        if (foodRegex.test(foodName)) {
            console.log("Food name is Valid");
        } else {
            console.log("Food name is Invalid");
            alert("<fmt:message key="page.foodtracking.foodname.wrong"/>");
            foodName.focus();
        }
        if (foodNumberRegex.test(newFoodNumber)) {
            console.log("Food number is Valid");
        } else {
            console.log("Food number is Invalid");
            alert("<fmt:message key="page.meal.wrong.number"/>");
            newFoodNumber.focus();
        }
        if (caloriesRegex.test(calories)) {
            console.log("Calories number is Valid");
        } else {
            console.log("Calories number is Invalid");
            alert("<fmt:message key="page.elements.wrong.number"/>");
            calories.focus();
        }
        if (caloriesRegex.test(proteins)) {
            console.log("Proteins number is Valid");
        } else {
            console.log("Proteins number is Invalid");
            alert("<fmt:message key="page.elements.wrong.number"/>");
            proteins.focus();
        }
        if (caloriesRegex.test(lipids)) {
            console.log("Lipids number is Valid");
        } else {
            console.log("Lipids number is Invalid");
            alert("<fmt:message key="page.elements.wrong.number"/>");
            lipids.focus();
        }
        if (caloriesRegex.test(carbs)) {
            console.log("Carbs number is Valid");
        } else {
            console.log("Carbs number is Invalid");
            alert("<fmt:message key="page.elements.wrong.number"/>");
            carbs.focus();
        }

        $.ajax({
            url: "/fts/trackNewFood",
            method: "POST",
            /*dataType: 'json',
            contentType: 'application/json; charset=utf-8',*/
            data: {
                food_name: foodName,
                number: newFoodNumber,
                calories: calories,
                proteins: proteins,
                lipids: lipids,
                carbs: carbs
            },
            success: function (result) {
                if (result === "success") {
                    alert("<fmt:message key="page.new.food.added"/>");
                    resetData();
                    location.reload();
                }else if (result==="foodExists"){
                    alert("<fmt:message key="page.new.food.exists"/>");
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
        $("#foodName").val("");
        $("#newFoodNumber").val("");
        $("#calories").val("");
        $("#proteins").val("");
        $("#lipids").val("");
        $("#carbs").val("");
    }
</script>

<script>
    $("#settings_button").on('click', function () {
        var number = $("#number").val();
        var foodNumberRegex = new RegExp("^([1-9])$");
        if (foodNumberRegex.test(number)) {
            console.log("Food number is Valid");
        } else {
            console.log("Food number is Invalid");
            alert("<fmt:message key="page.meal.wrong.number"/>");
            number.focus();
        }

        $.ajax({
            url: "/fts/trackFood",
            method: "POST",
            /*dataType: 'json',
            contentType: 'application/json; charset=utf-8',*/
            data: {
                food_name: $("#food").val(),
                number: number,
            },
            success: function (result) {
                if (result === "success") {
                    alert("<fmt:message key="page.meal.added"/>");
                    location.reload();
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
        resetData();
    });
    function resetData() {
        $("#number").val("");
    }

</script>
