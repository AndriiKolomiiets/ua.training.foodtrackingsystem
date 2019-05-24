
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
            url: "LoginServlet",
            method: get,
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
        if (results == 'Success') {
            $('#messageDiv').html("<font color='green'>You are successfully logged in. </font>")
        } else if (results == 'Failure') {
            $('#messageDiv').html("<font color='red'>Username or password incorrect </font>")
        }
    }
