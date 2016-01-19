<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:page-template>
  <jsp:attribute name="title">Secret Message</jsp:attribute>
  <jsp:body>
    <h1>Tozny Java Secret Message Example</h1>
    <p class="lead">Log in to see the secret message.</p>

    <c:if test="${not empty param.error}">
      <div class="alert alert-danger" role="alert">
        <p><c:out value="${param.error}"/></p>
      </div>
    </c:if>

    <div id="tozny-login"></div>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://api.tozny.com/interface/javascript/v2/jquery.tozny.js"></script>
    <script>
      $(document).ready(function () {
        $('#tozny-login').tozny({
          realm_key_id: 'sid_5647f50d6743e',
          debug: true,
          form_action: '${pageContext.request.contextPath}/login',
          style: 'box'
        })
      })
    </script>
  </jsp:body>
</t:page-template>
