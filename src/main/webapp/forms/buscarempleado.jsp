<div class="standard">
    <h2>Buscar empleado</h2>
</div>

<form action="empresa" method="get">
    <input type="hidden" name="opcion" value="buscar_dni">
    <label for="dni">Buscar por DNI:</label>
    <input type="text" name="dni" id="dni">
    <input type="submit" value="Buscar">
</form>

<form action="empresa" method="get">
    <input type="hidden" name="opcion" value="buscar_nombre">
    <label for="nombre">Buscar por Nombre:</label>
    <input type="text" name="nombre" id="nombre">
    <input type="submit" value="Buscar">
</form>

