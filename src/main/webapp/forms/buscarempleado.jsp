<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Búsqueda de Empleados</title>
</head>
<body>
<h1>Búsqueda de Empleados</h1>
<form action="../empleados" method="get">
    <label for="dni">DNI:</label>
    <input type="text" name="dni" id="dni">
    <br>
    <label for="nombre">Nombre:</label>
    <input type="text" name="nombre" id="nombre">
    <br>
    <input type="submit" value="Buscar Empleados">
</form>
</body>
</html>
