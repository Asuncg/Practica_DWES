<%@ page import="model.Empleado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="../js/validacion.js"></script>
<h2>Resultado de Búsqueda de Empleado</h2>

<form id="formModificarEmpleado" action="empresa" method="post">
    <input type="hidden" name="opcion" value="modificarEmpleado">

    <c:if test="${not empty empleado}">
        <input type="hidden" name="id" value="${empleado.id}">
        <div class="styled-div">
            <div class="row">
                <div class="header-modificar">Nombre</div>
                <div class="cell">
                    <input type="text" name="nombre" value="${empleado.nombre}" required/>
                </div>
            </div>
            <div class="row">
                <div class="header-modificar">DNI</div>
                <div class="cell">
                    <input type="text" name="dni" value="${empleado.dni}" minlength="2" maxlength="50" required/>
                </div>
            </div>
            <div class="row">
                <div class="header-modificar">Sexo</div>
                <div class="cell">
                    <input type="text" name="sexo" value="${empleado.sexo}" minlength="1" maxlength="1" required/>
                </div>
            </div>
            <div class="row">
                <div class="header-modificar">Años</div>
                <div class="cell">
                    <input type="text" name="anyos" value="${empleado.anyos}" required/>
                </div>
            </div>
            <div class="row">
                <div class="header-modificar">Categoría</div>
                <div class="cell">
                    <input type="text" name="categoria" value="${empleado.categoria}" minlength="1" maxlength="2" pattern="[0-9]+" required/>
                </div>
            </div>
        </div>
        <p></p>
        <input type="submit" value="Guardar Cambios">
    </c:if>

    <c:if test="${empty empleado}">
        <p>No se encontró ningún empleado con los criterios de búsqueda proporcionados.</p>
    </c:if>
</form>


