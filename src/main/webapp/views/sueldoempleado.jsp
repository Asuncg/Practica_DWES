<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Resultado de Consulta de Sueldo</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
<h1>Resultado de Consulta de Sueldo</h1>
<%
    String dni = (String) request.getAttribute("dni");
    double sueldo = (Double) request.getAttribute("sueldo");
%>
<div class="styled-div">
    <div class="rowlistar">
        <%
            if (sueldo > 0) {
        %>
        <div class="celllistar">DNI del empleado: <b><%= dni %>
        </b> - Sueldo: <b><%= String.format("%.0f", sueldo) %> €
        </b></div>
    </div>
    <%
    } else {
    %>
    <div class="celllistar">
        <p>DNI no proporcionado o empleado no encontrado.</p>
    </div>
    <%
        }
    %>
</div>
</div>
<p></p>
<a class="button" href="/Practica_DWES/index.jsp">Volver al menú</a>
<a class="button" href="/Practica_DWES/forms/sueldoform.jsp">Buscar otro empleado</a>

</body>
</html>
