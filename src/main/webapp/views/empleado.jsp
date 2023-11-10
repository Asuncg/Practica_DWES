
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Resultado de Búsqueda de Empleado</h1>

<c:choose>
    <c:when test="${not empty empleado}">
        <table>
            <tr>
                <th>Nombre</th>
                <td>${empleado.nombre}</td>
                <td><a href="modificarEmpleado.jsp?nombre=${empleado.nombre}">Modificar</a><br></td>
            </tr>
            <tr>
                <th>DNI</th>
                <td>${empleado.dni}</td>
                <td><a href="modificarEmpleado.jsp?dni=${empleado.dni}">Modificar</a><br></td>
            </tr>
            <tr>
                <th>Sexo</th>
                <td>${empleado.sexo}</td>
                <td><a href="modificarEmpleado.jsp?nombre=${empleado.nombre}">Modificar</a><br></td>
            </tr>
            <tr>
                <th>Categoría</th>
                <td>${empleado.categoria}</td>
                <td><a href="modificarEmpleado.jsp?categoria=${empleado.categoria}">Modificar</a><br></td>
            </tr>
            <tr>
                <th>Años</th>
                <td>${empleado.anyos}</td>
                <td><a href="modificarEmpleado.jsp?anyos=${empleado.anyos}">Modificar</a><br></td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>No se encontró ningún empleado con los criterios de búsqueda proporcionados.</p>
    </c:otherwise>
</c:choose>

