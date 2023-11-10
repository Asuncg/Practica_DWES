<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://kit.fontawesome.com/b481faf5db.js" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
    <title>Práctica Nóminas</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Notable">
    <script>
        function confirmarBaja() {
            return confirm("¿Está seguro de que desea dar de baja al empleado?");
        }
    </script>
</head>
<body>
<div class="container">
    <%@ include file="header/header.jsp" %>
    <div class="content">
        <c:choose>
            <c:when test="${empty content}">
                <c:import url="views/main.jsp" />
            </c:when>
            <c:otherwise>
                <jsp:include page="${content}" />
            </c:otherwise>
        </c:choose>
    </div>
    <%@ include file="footer/footer.jsp" %>
</div>
</body>
</html>

