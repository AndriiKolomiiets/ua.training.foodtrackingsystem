<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>
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
        <h2>To track meal, please, create a client account!</h2>
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
        <form <%--action="http://localhost:8080/fts/trackNewFood"--%>>
       <div class="row">
                <div class="col-25">
                    <label for="food"><fmt:message key="page.foodtracking.food"/></label>
                </div>
                <div class="col-75">
                    <select id="food"  class="select_life_style" name="food">
                        <option value="1"><fmt:message key="page.foodtracking.soup"/></option>
                        <option value="2"><fmt:message key="page.foodtracking.porridge"/></option>
                        <option value="3"><fmt:message key="page.foodtracking.hamburger"/></option>
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
                <input type="submit" name="settings_button" id="settings_button" value="<fmt:message key="page.foodtracking.button"/>">
            </div>
        </form>
    </div>
</div>

    <button class="open-button" onclick="openForm()">Add New Food</button>

    <div class="form-popup" id="newFoodForm">
        <form <%--action="http://localhost:8080/fts/trackFood"--%> class="form-container">
            <h1>New food</h1>

            <label for="foodName"><b>Name</b></label>
            <input type="text" placeholder="Enter food name" id="foodName" name="foodName" required>

            <label for="newFoodNumber"><b>Number of foods</b></label>
            <input type="text" placeholder="Enter number of foods" id="newFoodNumber" name="newFoodNumber" required>

            <label for="calories"><b>Calories</b></label>
            <input type="text" placeholder="Enter number of calories" id="calories" name="calories" required>

            <label for="proteins"><b>Proteins</b></label>
            <input type="text" placeholder="Enter number of proteins" id="proteins" name="proteins" required>

            <label for="lipids"><b>Lipids</b></label>
            <input type="text" placeholder="Enter number of lipids" id="lipids" name="lipids" required>

            <label for="carbs"><b>Carbohydrates</b></label>
            <input type="text" placeholder="Enter number of carbs" id="carbs" name="carbs" required>

            <button type="button" id="newFoodButton" class="btn">Add Food</button>
            <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
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
        var foodNumberRegex = new RegExp("^([0-9]{1,2})$");
        var foodRegex = new RegExp("[A-za-z0-9]{4,9}");
        var caloriesRegex = new RegExp("^([0-9]{1,3})$")
        if (foodRegex.test(foodName)) {
            console.log("Food name is Valid");
        } else {
            console.log("Food name is Invalid");
            alert("<fmt:message key="page.name.wrong"/>");
            foodName.focus();
        }
        if (foodNumberRegex.test(newFoodNumber)) {
            console.log("Food number is Valid");
        } else {
            console.log("Food number is Invalid");
            alert("<fmt:message key="page.name.wrong"/>");
            newFoodNumber.focus();
        }
        if (caloriesRegex.test(calories)) {
            console.log("Calories number is Valid");
        } else {
            console.log("Calories number is Invalid");
            alert("<fmt:message key="page.name.wrong"/>");
            calories.focus();
        }
        if (caloriesRegex.test(proteins)) {
            console.log("Proteins number is Valid");
        } else {
            console.log("Proteins number is Invalid");
            alert("<fmt:message key="page.name.wrong"/>");
            proteins.focus();
        }
        if (caloriesRegex.test(lipids)) {
            console.log("Lipids number is Valid");
        } else {
            console.log("Lipids number is Invalid");
            alert("<fmt:message key="page.name.wrong"/>");
            lipids.focus();
        }
        if (caloriesRegex.test(carbs)) {
            console.log("Carbs number is Valid");
        } else {
            console.log("Carbs number is Invalid");
            alert("<fmt:message key="page.name.wrong"/>");
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
</script>
