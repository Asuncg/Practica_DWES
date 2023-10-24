<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Búsqueda de Empleados</title>
</head>
<body>
<h1>Búsqueda de Empleados</h1>
<form action="../empleados" method="get">
    <input type="hidden" name="opcion" value="buscar"> <!-- Agregamos un campo oculto con el valor "dni" -->
    <label for="dni">DNI:</label>
    <input type="text" name="dni" id="dni"> <!-- Cambiamos el nombre a "valor" para el DNI -->
    <input type="submit" value="Buscar">
</form>
<form action="../empleados" method="get">
    <input type="hidden" name="opcion" value="buscar"> <!-- Agregamos un campo oculto con el valor "nombre" -->
    <label for="nombre">Nombre:</label>
    <input type="text" name="dni" id="nombre"> <!-- Cambiamos el nombre a "valor" para el nombre -->
    <input type="submit" value="Buscar">
</form>
</body>
</html>
