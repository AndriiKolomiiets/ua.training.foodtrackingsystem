<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>
<link rel="stylesheet" type="text/css" href="../../style/settingsStyle.css">

<script>
    function preback() {
        window.history.forward();
    }

    setTimeout("preback()", 0);
    window.onload = function () {
        null
    };
</script>

<%--todo: add styles --%>


<%--<link rel="stylesheet" href="../../style/form_style.css">--%>

<%--<div align="center">
    <h3>Life style coefficients:</h3>
<table >
    <tr>
        <th>Style</th>
        <th>Coefficient</th>
    </tr>
    <tr>
        <td>Active</td>
        <td>3</td>

    </tr>
    <tr>
        <td>Normal</td>
        <td>2</td>

    </tr>
    <tr>
        <td>Sedentary</td>
        <td>1</td>

    </tr>
</table>
</div>--%>

<%--<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="client.settings.title"/></h1>
<br>--%>

<%--     *****************USER SETTINGS FORM*************************--%>
<br>
<h1 align="center" style="color:#1e4103"><fmt:message key="client.settings.title"/></h1>
<br>

<div align="center">
<div class="settings_container">
    <form action="/fts/addClient">
        <div class="row">
            <div class="col-25">
                <label for="birth_date"><fmt:message key="page.settings.input.birthDate"/></label>
            </div>
            <div class="col-75">
                <input type="date" class="settings_label" id="birth_date" name="birth_date" placeholder="<fmt:message key="page.settings.placeholder.birthDate"/>">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="height"><fmt:message key="page.settings.input.height"/></label>
            </div>
            <div class="col-75">
                <input type="text" class="settings_label" id="height" name="height" placeholder="<fmt:message key="page.settings.placeholder.height"/>">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="weight"><fmt:message key="page.settings.input.weight"/></label>
            </div>
            <div class="col-75">
                <input type="text" class="settings_label" id="weight" name="weight" placeholder="<fmt:message key="page.settings.placeholder.weight"/>">
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="country"><fmt:message key="page.lifestyle"/></label>
            </div>
            <div class="col-75">
                <select id="country"  class="select_life_style" name="country">
                    <option value="1"><fmt:message key="page.lifestyle.sedentary"/></option>
                    <option value="2"><fmt:message key="page.lifestyle.normal"/></option>
                    <option value="3"><fmt:message key="page.lifestyle.active"/></option>
                </select>
            </div>
        </div>
<br>
        <div class="row">
            <input type="submit" name="settings_button" id="settings_button" value="<fmt:message key="page.lifestyle.button"/>">
        </div>
    </form>
</div>
</div>
<%--     ******END***********USER SETTINGS FORM ENDS************END*************--%>

