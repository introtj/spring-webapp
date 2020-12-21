<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- Placed at the end of the document so the pages load faster --%>
<spring:url value="/webjars/jquery/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>

<%-- jquery-ui.js file is really big so we only load what we need instead of loading everything --%>
<spring:url value="/webjars/jquery-ui/jquery-ui.min.js" var="jQueryUiCore"/>
<script src="${jQueryUiCore}"></script>

<%-- Bootstrap --%>
<spring:url value="/webjars/bootstrap/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>

