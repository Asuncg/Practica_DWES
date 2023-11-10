<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="standard">
    <h2>Resultado</h2>
</div>

<c:set var="dni" value="${requestScope.dni}" />
<c:set var="sueldo" value="${requestScope.sueldo}" />

<div class="styled-div">
    <c:choose>
        <c:when test="${sueldo > 0}">
            <div class="rowlistar">
                <div class="celllistar">DNI del empleado: <b>${dni}</b> - Sueldo: <b>${sueldo}</b> €</div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="celllistar">
                <p>No se ha encontrado ningún sueldo por el DNI ${dni}.</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>

