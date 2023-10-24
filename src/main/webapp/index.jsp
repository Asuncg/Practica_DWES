<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #1a1a1a; /* Fondo negro */
      color: #2ecc71; /* Texto en verde */
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    header {
      background-color: #2ecc71; /* Fondo verde para el encabezado */
      color: #1a1a1a; /* Texto en negro para el encabezado */
      text-align: center;
      padding: 20px;
    }

    h1 {
      font-size: 28px;
    }

    ul {
      list-style: none;
      padding: 0;
    }

    li {
      margin: 10px 0;
    }

    a {
      display: block;
      text-decoration: none;
      color: #2ecc71;
      transition: color 0.3s;
      padding: 10px 20px;
      border: 1px solid #2ecc71;
      border-radius: 5px;
      text-align: center;
    }

    a:hover {
      color: #27ae60;
    }
  </style>
</head>
<body>
<header>
  <h1><%= "Práctica Nóminas" %></h1>
</header>
<ul>
  <li><a href="empleados?opcion=listar">Mostrar información de los empleados</a></li>
  <li><a href="forms/sueldoform.jsp">Mostrar salario existente de un empleado</a></li>
  <li><a href="forms/buscarempleado.jsp">Modificar los datos de un empleado existente</a></li>
</ul>
</body>
</html>
