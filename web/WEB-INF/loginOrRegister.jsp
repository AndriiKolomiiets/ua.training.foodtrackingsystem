<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${localeLang}"/>
<fmt:setBundle basename="messages"/>


<html>
<head>
    <script>
        function preback() {
            window.history.forward();
        }

        setTimeout("preback()", 0);
        window.onload = function () {
            null
        };
    </script>
    <title>Food Tracking System</title>

    <link rel="stylesheet" type="text/css" href="../style/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="../selectstyle/js/jquery.nice-select.js"></script>
    <link rel="stylesheet" href="../selectstyle/css/nice-select.css">

    <link rel="stylesheet" href="../style/form_style.css">


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

<br>
<div align="left" class="form-group">
    <%--   <fmt:message key="login.register.message"/>--%>
    <select id="selectId" onchange="getCurrentlink()">
        <option value="${pageContext.request.contextPath}/fts/changeLanguage?lang=uk_UA&currPage=">
            <fmt:message key="page.lang.ua_UA"/>
        </option>
        <option value="${pageContext.request.contextPath}/fts/changeLanguage?lang=en_US&currPage=">
            <fmt:message key="page.lang.en_US"/>
        </option>
    </select>
</div>
<br>
<br>
<br>

<ul>
    <li><a href="${pageContext.request.contextPath}/fts/mainPage"><fmt:message key="main.page"/></a></li>
    <li style="float:right"><a type="button" onclick="document.getElementById('loginDiv').style.display='block'">
        <fmt:message key="page.signIn"/></a></li>
</ul>
<script type="text/javascript">
    chooseLanguage()
</script>

<script>
    function getCurrentlink() {
        var select, value, page;

        page = window.location.pathname;
        select = document.getElementById("selectId");
        value = select.options[select.selectedIndex].value;
        if (value == null) {
            value = "${pageContext.request.contextPath}/fts/changeLanguage?lang=uk_UA&currPage="
        }
        window.location.href = value + page;
    }
</script>


<br>
<%--<h1 align="center" style="color:#1e4103"><fmt:message key="main.title"/></h1>--%>
<br>

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
<%--<div id="messageDiv" style="display:none;"></div>--%>
<%--<script>
    // Get the modal
    var modal = document.getElementById('loginDiv');
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>--%>

<script>
    $("#login_button").on('click', function () {
        var login = $("#login").val();
        var password = $("#password").val();
        if (login == "") {
            // $('#messageDiv').css("display", "none");
            alert("<fmt:message key="index.input.login.message"/>");
            login.focus()
            return;
        }
        if (password == "") {
            // $('#messageDiv').css("display", "none");
            alert("<fmt:message key="index.input.pass.message"/>");
            return;
        }
        $.ajax({
            /*cache: false,*/
            url: "/fts/login",
            method: "POST",
            /*dataType: 'json',
            contentType: 'application/json; charset=utf-8',*/
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

<div class="login-box">
    <h1><fmt:message key="form.register.name"/></h1>
    <form id="registrationForm">
        <div class="textbox">
            <i class="fas fa-user"></i>
            <input type="text" name="username" id="username" placeholder="<fmt:message key="form.login.input"/>"
                   pattern="[A-za-z0-9]{3,9}" value="topic1" required>
        </div>

        <div class="textbox">
            <i class="fas fa-lock"></i>
            <input type="password" id="new-password" name="new-password" placeholder="<fmt:message key="form.pass.input"/>"
                   pattern="[A-za-z0-9]{4,9}" value="topic1" required>
        </div>

        <div class="textbox">
            <i class="fas fa-lock"></i>
            <input type="password" id="new-password2" name="new-password2"
                   placeholder="<fmt:message key="form.pass.confirm.input"/>"
                   pattern="[A-za-z0-9]{4,9}" value="topic1" required>
        </div>
        <div class="textbox">
            <i class="fas fa-user"></i>
            <input type="text" id="emailId" name="email" placeholder="<fmt:message key="form.email.input"/>"
            <%-- pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
--%> value="mail@gmail.com" required>
        </div>

        <input type="button" class="btn" id="registerButton" value="<fmt:message key="form.register.button"/>">
    </form>
</div>

<script>
    $(document).ready(function () {
        // SUBMIT FORM
        $("#registerButton").click(function (event) {
            var username = $("#username").val();
            var password = $("#new-password").val();
            var password2 = $("#new-password2").val();
            var email = $("#emailId").val();
            var passRegex = new RegExp("^([a-z0-9]{3,9})$");
            var loginRegex = new RegExp("[A-za-z0-9]{4,9}");
            var emailRegex = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
            if (loginRegex.test(username)) {
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
            if (passRegex.test(password2)) {
                console.log("Password2 is Valid");
            } else {
                console.log("Password2 is Invalid");
                alert("<fmt:message key="page.password.unconfirmed"/>");
            }
            if (password===password2){
                console.log("Password confirmed!");
            } else {
                console.log("Password wasn't confirmed!");
                alert("<fmt:message key="page.password.unconfirmed"/>");
            }
            if (emailRegex.test(email)) {
                console.log("Email is Valid");
            } else {
                console.log("Email is Invalid");
                alert("<fmt:message key="page.email.wrong"/>");
            }

            // Prevent the form from submitting via the browser.
            if ((passRegex.test(password)) &
                (loginRegex.test(username)) &
                (passRegex.test(password)) &
                (passRegex.test(password2)) &
                (emailRegex.test(email))&
                (password===password2)) {
                event.preventDefault();
                ajaxPost();
                console.log("Form sent");
            }
        });

        function ajaxPost() {
            // PREPARE FORM DATA
            var formData = {
                login: $("#username").val(),
                password: $("#new-password").val(),
                password2: $("#new-password2").val(),
                // _csrf: $("#csrf").val(),
                email: $("#emailId").val()
            }

            // DO POST
            $.ajax({
                type: "POST",
                url: "/fts/register",
                data: formData,
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
                        console.log("user pass error");
                    } else if (result==="userErrorLogin"){
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
                error: function (e) {
                    alert("<fmt:message key="page.user.error"/>");
                    console.log("ERROR: ", e);
                }
            });

            // Reset FormData after Posting
            resetData();
        }

        function resetData() {
            $("#username").val("");
            $("#new-password").val("");
            $("#new-password2").val("");
            $("#emailId").val("");
        }
    })
</script>


<%--               FOOTER                     --%>

<footer class="footer" id="footer">
    <div class="footer-content">
        <p class="copyright"><fmt:message key="page.footer"/></p>
        <p class="copyright">ua.training.FoodTrackingSystem</p>
    </div>
</footer>
</body>
</html>