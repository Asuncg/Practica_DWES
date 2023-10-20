<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Practica Nóminas" %></h1>
<br/>
<ul>
  <li><a href="empleados?opcion=listar"> Mostrar información de los empleados</a></li>
  <li><a href="forms/sueldoform.jsp">Mostrar salario existente de un empleado</a></li>
  <li><a href="forms/buscarempleado.jsp">Modificar los datos de un empleado existente</a></li>

</ul>
</body>
</html>