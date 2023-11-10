<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="standard">
<h2>Crear Empleado</h2>
</div>

<form id="formCrearEmpleado" action="empresa" method="post">
    <input type="hidden" name="opcion" value="nuevaAltaEmpleado">
    <div class="styled-div">
        <div class="row">
            <div class="header-modificar">Nombre</div>
            <div class="cell">
                <input type="text" name="nombre" required/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">DNI</div>
            <div class="cell">
                <input type="text" name="dni" minlength="2" maxlength="50" required/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">Sexo</div>
            <div class="cell">
                <input type="text" name="sexo" minlength="1" maxlength="1" required pattern="[FM]"/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">Años</div>
            <div class="cell">
                <input type="text" name="anyos" required/>
            </div>
        </div>
        <div class="row">
            <div class="header-modificar">Categoría</div>
            <div class="cell">
                <input type="text" name="categoria" minlength="1" maxlength="2" pattern="[0-9]+" required/>
            </div>
        </div>
    </div>
    <p></p>
    <input type="submit" value="Crear Empleado">
</form>

<div class="standard">
    <h2>Listar Empleados</h2>
</div>

<div class="styled-div">
    <div class="headerlistar">
        <div class="celllistar">Nombre</div>
        <div class="celllistar">DNI</div>
        <div class="celllistar">Sexo</div>
        <div class="celllistar">Categoría</div>
        <div class="celllistar">Años trabajados</div>
        <div class="celllistar">Dar de Alta</div>
    </div>

    <c:forEach var="empleado" items="${listaEmpleados}">
        <div class="rowlistar">
            <div class="celllistar">${empleado.getNombre()}</div>
            <div class="celllistar">${empleado.getDni()}</div>
            <div class="celllistar">${empleado.getSexo()}</div>
            <div class="celllistar">${empleado.categoria}</div>
            <div class="celllistar">${empleado.anyos}</div>
            <div class="celllistar">
                <form action="empresa" method="post">
                    <input type="hidden" name="opcion" value="altaEmpleado">
                    <input type="hidden" name="id" value="${empleado.id}">
                    <input type="hidden" name="dni" value="${empleado.dni}">
                    <input type="hidden" name="categoria" value="${empleado.categoria}"
                    <input type="hidden" name="anyos" value="${empleado.anyos}"
                    <input type="submit" value="Alta Empleado">
                </form>
            </div>
        </div>
    </c:forEach>
</div>

