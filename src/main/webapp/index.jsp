<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Práctica Nóminas</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Notable">
</head>
<body>
<div class="container">
    <%@ include file="header/header.jsp" %>
    <div class="content">
        <% if (request.getAttribute("content") == null || ((String) request.getAttribute("content")).isEmpty()) { %>
        <%@ include file="views/main.jsp" %>
        <% } else { %>
        <jsp:include page="${content}"/>
        <% } %>
    </div>
    <%@ include file="footer/footer.jsp" %>
</body>
</html>
