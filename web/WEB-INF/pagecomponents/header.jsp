<%@ include file="allImports.jsp" %>
<html>
<head>
    <title>Food Tracking System</title>


    <link rel="stylesheet" type="text/css" href="../../style/styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="../../selectstyle/js/jquery.nice-select.js"></script>
    <link rel="stylesheet" href="../../selectstyle/css/nice-select.css">
    <%--<script src="path/to/jquery.nice-select.js"></script>--%>

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

<%--<form class="left_form">
    <label for="language"></label>
    <h3 align="right" style="color:#FF4500">
        <fmt:message key="language"/><select id="language" name="language"
                                             onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
    </select></h3>
</form>--%>

<%--<div align="left" class="login">
    <button class="header_button" onclick="window.location.href='/fts/logOut'">Log Out</button>
</div>--%>
<div align="left" class="form-group">
    <button class="header_button" onclick="window.location.href='/fts/logOut'"><fmt:message key="page.logout"/></button>

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