<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Consulta de Sueldo</h1>
<form action="../sueldo" method="get">
    <label for="dni">Introduce el DNI del empleado:</label>
    <input type="text" name="dni" id="dni" required>
    <br>
    <input type="submit" value="Consultar Sueldo">
</form>
</body>
</html>