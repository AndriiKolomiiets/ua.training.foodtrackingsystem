<%@ include file="../pagecomponents/allImports.jsp" %>

<jsp:include page="../pagecomponents/header.jsp"/>
<jsp:include page="../pagecomponents/footer.jsp"/>


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
