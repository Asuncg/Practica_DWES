<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="standard">
    <h2>Resultado</h2>
</div>
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
        <p>No se ha encontrado ningún sueldo por el DNI <%= dni %>.</p>
    </div>
    <%
        }
    %>
</div>
