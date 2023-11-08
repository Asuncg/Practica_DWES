<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Resultado de Consulta de Sueldo</h2>
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
