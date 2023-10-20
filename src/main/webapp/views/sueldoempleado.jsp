<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Resultado de Consulta de Sueldo</title>
</head>
<body>
<h1>Resultado de Consulta de Sueldo</h1>
<%
    String dni = (String) request.getAttribute("dni");
    double sueldo = (Double) request.getAttribute("sueldo");
%>
<%
    if (sueldo > 0) {
%>
<p>El sueldo del empleado con DNI <%= dni %> es: <%= sueldo %></p>
<%
} else {
%>
<p>DNI no proporcionado o empleado no encontrado.</p>
<%
    }
%>
</body>
</html>
