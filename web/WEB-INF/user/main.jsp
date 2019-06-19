<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/footer.jsp"/>
<%@ page errorPage="/error.jsp"%>

<html>
<head>
    <title>Food Tracking System</title>

    <link rel="stylesheet" type="text/css" href="../../style/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="../../selectstyle/js/jquery.nice-select.js"></script>
    <link rel="stylesheet" href="../../selectstyle/css/nice-select.css">

    <script type="text/javascript">
        $(document).ready(function () {
            $('select').niceSelect();
        });

        function chooseLanguage() {
            $('#selectId').val('${pageContext.request.contextPath}' + '/fts/changeLanguage?lang=' + '${localeLang}' + '&currPage=').change();
        }
    </script>

</head>
<body>

<div align="left" class="form-group">
    <button class="header_button"  onclick="document.getElementById('loginDiv').style.display='block'"><fmt:message key="page.signIn"/></button>

    <select id="selectId" onchange="getCurrentlink()">
        <option value="${pageContext.request.contextPath}/fts/changeLanguage?lang=uk_UA&currPage=">
            <fmt:message key="page.lang.ua_UA"/>
        </option>
        <option value="${pageContext.request.contextPath}/fts/changeLanguage?lang=en_US&currPage=">
            <fmt:message key="page.lang.en_US"/>
        </option>
    </select>
</div>

<ul>
    <%--<li><a href="${pageContext.request.contextPath}/fts/main">Home</a></li>--%>
    <li><a href="${pageContext.request.contextPath}/fts/foodTracking"><fmt:message key="food.tracking.title"/></a></li>
    <li><a href="${pageContext.request.contextPath}/fts/dayMeal"><fmt:message key="day.meal.title"/></a></li>
    <li><a href="${pageContext.request.contextPath}/fts/mealStatistic">
        <fmt:message key="meal.statistic.title"/></a></li>
    <li style="float:right"><a href="${pageContext.request.contextPath}/fts/userSettings">
        <fmt:message key="client.settings.title"/></a></li>
    <c:if test="${role == 'ADMIN'}">
        <li style="float:right"><a href="${pageContext.request.contextPath}/fts/userManagement">
            <fmt:message key="page.admin.title"/></a></li>
    </c:if>
    <c:if test="${mealList != null}">
        <li style="float:right"><a><fmt:message key="page.calories.to.norm"/>
            <c:out value='${calories_to_norm}'/></a></li>
        <c:if test="${calories_status == 'normal'}">
            <li style="float:right"><a><fmt:message key="page.calories.norm"/></a></li>
        </c:if>
        <c:if test="${calories_status == 'exceeded'}">
            <li style="float:right"><a><fmt:message key="page.calories.exceeded"/></a></li>
        </c:if>
    </c:if>
</ul>

<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="main.title"/></h1>
<br>

<div class="mainDiv">
    <fmt:message key="page.main.info"/>
</div>

<script type="text/javascript">
    chooseLanguage()
</script>

<script>
    function getCurrentlink() {
        var select, value, page;

        page = window.location.pathname;
        select = document.getElementById("selectId");
        value = select.options[select.selectedIndex].value;
        window.location.href = value + page;
    }
</script>
<%--                    LOGIN FORM                           --%>

<div id="loginDiv" class="modal">
    <form id="loginForm" class="modal-content animate" name="login_form">
        <%--onsubmit="return validateForm()--%>
        <div class="imgcontainer">
            <span onclick="document.getElementById('loginDiv').style.display='none'"
                  class="close" title="Close Form">&times;</span>
            <img src="/images/login.jpg" alt="Avatar" class="avatar">
        </div>
        <div class="container">
            <tr><fmt:message key="index.login.input"/><input type="text" name="login" id="login"
                                                             placeholder="<fmt:message key="index.login.enter"/>"
                                                             pattern="[0-9A-Za-z ]{2,15}"/>
            </tr>
            <tr><fmt:message key="index.password.input"/><input type="password" name="password" id="password"
                                                                placeholder="<fmt:message key="index.password.enter"/>"
                                                                pattern="[0-9A-Za-z ]{4,15}"/></tr>
            <button type="button" name="login_button" id="login_button">Login</button>
        </div>

        <div class="container" style="background-color:#f1f1f1">
            <button type="button" onclick="document.getElementById('loginDiv').style.display='none'" class="cancelbtn">
                Cancel
            </button>
            <span class="psw"><a href="${pageContext.request.contextPath}/fts/mainPage"><fmt:message
                    key="main.page.direct"/></a></span>
        </div>
    </form>
</div>

<script>
    $("#login_button").on('click', function () {
        var login = $("#login").val();
        var password = $("#password").val();
        var passRegex = new RegExp("^([a-z0-9]{3,9})$");
        var loginRegex = new RegExp("[A-za-z0-9]{4,9}");
        if (loginRegex.test(login)) {
            console.log("Login is Valid");
        } else {
            console.log("Login is Invalid");
            alert("<fmt:message key="page.name.wrong"/>");
        }
        if (passRegex.test(password)) {
            console.log("Password is Valid");
        } else {
            console.log("Password is Invalid");
            alert("<fmt:message key="page.password.wrong"/>");
        }
        $.ajax({
            url: "/fts/login",
            method: "POST",
            data: {
                login: login,
                password: password
            },
            success: function (result) {
                if (result === "success") {
                    window.location.reload(true);
                    window.location = "/fts/foodTracking";
                }else if (result==="userIsLogged"){
                    alert("<fmt:message key="page.user.alreadyLoggedIn"/>");
                } else if (result==="userExistsInDb"){
                    alert("<fmt:message key="page.user.loginAlreadyExists"/>");
                } else if (result==="userPassError"){
                    alert("<fmt:message key="page.user.wrong.password"/>");
                    console.log("user pass error")
                }else if (result==="userErrorLogin"){
                    alert("<fmt:message key="page.name.wrong"/>");
                }else if (result==="userNotExist"){
                    alert("<fmt:message key="page.user.loginDoesntExistInDb"/>");
                }
                else {
                    alert("<fmt:message key="page.user.problem"/>");
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
<%--                               LOGIN FORM ENDS                  --%>

