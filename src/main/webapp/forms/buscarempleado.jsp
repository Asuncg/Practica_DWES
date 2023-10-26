<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Búsqueda de Empleados</title>
    <link rel="stylesheet" type="text/css" href="../css/estilos.css">
</head>
<body>
<h1>Búsqueda de Empleados</h1>

<form action="../empleados" method="get">
    <input type="hidden" name="opcion" value="buscar_dni">
    <label for="dni">Buscar por DNI:</label>
    <input type="text" name="dni" id="dni">
    <input type="submit" value="Buscar">
</form>

<form action="../empleados" method="get">
    <input type="hidden" name="opcion" value="buscar_nombre">
    <label for="nombre">Buscar por Nombre:</label>
    <input type="text" name="nombre" id="nombre">
    <input type="submit" value="Buscar">
</form>

<a class="button" href="../index.jsp">Volver al menú</a>
</body>
</html>
