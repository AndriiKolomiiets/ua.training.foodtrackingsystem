<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="page_content"/>

<link rel="stylesheet" type="text/css" href="styles.css">

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="index.title"/></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

</head>

<body>
<div align="right">
    <button class="header_button" onclick="document.getElementById('loginDiv').style.display='block'">Login</button>
</div>

<div id="loginDiv" class="modal">
    <form id="loginForm" class="modal-content animate" name="login_form" <%--action="/LoginServlet/"--%>>
        <%--onsubmit="return validateForm()--%>
        <div class="imgcontainer">
            <span onclick="document.getElementById('loginDiv').style.display='none'"
                  class="close" title="Close Form">&times;</span>
            <img src="images/login.jpg" alt="Avatar" class="avatar">
        </div>
        <div class="container">
            <tr><fmt:message key="index.login.input"/><input type="text" name="login" id="login"
                                                             placeholder="<fmt:message key="index.login.enter"/>"
                                                             pattern="[0-9A-Za-z ]{2,15}"/>
            </tr>
            <tr><fmt:message key="index.password.input"/><input type="password" name="password" id="password"
                                                                placeholder="<fmt:message key="index.password.enter"/>"
                                                                pattern="[0-9A-Za-z ]{4,15}"/></tr>
            <button type="submit" name="login_button" id="login_button">Login</button>
            <label>
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>
        </div>

        <div class="container" style="background-color:#f1f1f1">
            <button type="button" onclick="document.getElementById('loginDiv').style.display='none'" class="cancelbtn">
                Cancel
            </button>
            <span class="psw">Forgot <a href="#">password?</a></span>
        </div>
    </form>
</div>
<div id="messageDiv" style="display:none;"></div>

<script>
    var modal = document.getElementById('loginDiv');
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>

<script>
    // Get the modal
    var modal = document.getElementById('loginDiv');
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>

<%--<script>
    window.onload = function() {
        if (window.jQuery) {
            showMessage("Success");
            $('#messageDiv').css("display", "block");

        } else {
            // jQuery is not loaded
            alert("Doesn't Work");
        }
    }

</script>--%>

<script>
    $("#login_button").on('click', function () {
        var login = $("#login").val();
        var password = $("#password").val();
        if (login == "") {
            $('#messageDiv').css("display", "none");
            alert("<fmt:message key="index.input.login.message"/>");
            login.focus()
            return;
        }
        if (password == "") {
            $('#messageDiv').css("display", "none");
            alert("<fmt:message key="index.input.pass.message"/>");
            return;
        }
        $.ajax({
            /*cache: false,*/
            url: "/LoginServlet/",
            method: "get",
            /*dataType: 'json',
            contentType: 'application/json; charset=utf-8',*/
            data: {
                login: login,
                password: password
            },
            success: function (results) {
                if (results != null && results != "") {
                    showMessage(results);
                    $('#messageDiv').css("display", "block");
                } else {
                    $('#messageDiv').css("display", "none");
                    $('#messageDiv').html("");
                    alert("Some exception occurred! Please try again.");
                }
            }
        });
    });

    //function to display message to the user
    function showMessage(results) {
        if (results === 'Success') {
            $("#messageDiv").show(5000);
            $('#messageDiv').html("<font color='green'>You are successfully logged in. </font>")
        } else {
            $('#messageDiv').html("<font color='red'>Username or password incorrect </font>")
        }
    }
</script>


<script> function validateForm() {
    var login = document.forms["login_form"]["login"].value;
    var pass = document.forms["login_form"]["pass"].value;
    if (login === "") {
        window.alert("<fmt:message key="index.input.login.message"/>");
        return false;
    }
    if (pass === "") {
        window.alert("<fmt:message key="index.input.pass.message"/>");
        return false;
    }
    return true;
}  </script>
<form class="left_form">
    <label for="language"></label>
    <h3 align="right" style="color:#FF4500">
        <fmt:message key="language"/><select id="language" name="language"
                                             onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
    </select></h3>
</form>
<br>
<ul>
    <li><a href="index.jsp">Home</a></li>
    <li style="float:right"><a href="user_settings.jsp">User Settings</a></li>
    <li><a href="food_tracking.jsp">Food Tracking</a></li>
    <li><a href="statistic.jsp">Statistic</a></li>
</ul>
<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="index.title"/></h1>
<br>
<br>
<div class="menu-container">
    <h2 align="center" style="color:#000000"><fmt:message key="index.body"/></h2>
</div>

<div class="container">
    <div class="buttons" align="center" style="text-align:center;">
        <a href="http://localhost:8080/TaxDeclarationFilling/">
            <button type="button" class="choose_button" id="slide_start_button">
                <fmt:message key="index.button.fillDeclaration"/></button>
        </a>
        <a href="http://localhost:8080/declaration_check/">
            <button type="button" class="choose_button" id="slide_stop_button">
                <fmt:message key="index.button.getInfoById"/></button>
        </a>
        <a href="http://localhost:8080/admin/">
            <button type="button" class="choose_button" id="admin_page_button">
                <%-- <fmt:message key="index.button.toadminpage"/></button>--%>
        </a>
    </div>
</div>
</body>
</html>

<%--
<div id="loginDiv" class="modal">
    <form id="loginForm" class="left_form" name="login_form" action="/LoginServlet/" onsubmit="return validateForm()">
        <table>
            <tr><fmt:message key="index.login.input"/><input type="text" name="login" id="login" pattern="[0-9A-Za-z ]{2,15}"/></tr>
            <tr><fmt:message key="index.password.input"/><input type="text" name="pass" id="password" pattern="[0-9A-Za-z ]{4,15}"/></tr>

            <div class="buttons" align="center">
                <input type="submit" class="sub_button" value=<fmt:message key="index.login.button"/>>
                <input type="reset" class="sub_button" value=<fmt:message key="index.clear.button"/>>
            </div>
        </table>
    </form>
    <div id="messageDiv" style="display:none;"></div>
</div>--%>
