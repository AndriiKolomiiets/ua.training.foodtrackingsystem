<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>


<%--todo: add styles --%>


<link rel="stylesheet" href="../../style/form_style.css">

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
<div class="login-box" align="right">
    <h1>Client</h1>
    <div class="textbox">
        <i class="fas fa-user"></i>
        <input type="text" placeholder="Birth date (dd.mm.yy)">
    </div>

    <div class="textbox">
        <i class="fas fa-user"></i>
        <input type="text" placeholder="Height (centimeters)">
    </div>
    <div class="textbox">
        <i class="fas fa-user"></i>
        <input type="text" placeholder="Weight (grams)">
    </div>
    <%--<div class="textbox">
        <i class="fas fa-user"></i>
        <input type="text" placeholder="Life style coefficient">
    </div>--%>
    <div class = "custom-select">
        <label>
            <select >
                <option value="1">
                    <fmt:message key="page.lifestyle.sedentary"/>
                </option>
                <option value="2">
                    <fmt:message key="page.lifestyle.normal"/>
                </option>
                <option value="3">
                    <fmt:message key="page.lifestyle.active"/>
                </option>
            </select>
        </label>
    </div>
    <input type="button" class="btn" value="Add Client Info">
</div>