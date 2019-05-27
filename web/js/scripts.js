
var modal = document.getElementById('loginDiv');
// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

// +1
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
        window.alert("<font color='green'>You are successfully logged in. </font>")
        $("#messageDiv").show(50);
        $('#messageDiv').html("<font color='green'>You are successfully logged in. </font>")
    } else {
        $('#messageDiv').html("<font color='red'>Username or password incorrect </font>")
    }
}