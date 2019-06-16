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

<c:if test="${user.client.id!=null}">


</c:if>
<c:if test="${client.id==null}">


</c:if>



<div align="center">
    <div class="settings_container">
        <form action="http://localhost:8080/fts/trackFood">
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
