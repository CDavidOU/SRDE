<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/24/2026
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="servlet-registro-estudiante" method="POST">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required min="2">
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required min="2">
        <label for="matricula">Matricula:</label>
        <input type="text" id="matricula" name="matricula" required max="10" min="10">
        <label for="cuatrimestre">Cuatrimestre:</label>
        <select id="cuatrimestre" name="cuatrimestre" required>
            <option value="">Selecciona una opción</option>
            <option value="6">6° Cuatrimestre</option>
            <option value="11">11° Cuatrimestre</option>
        </select>
        <label for="carrera">Carrera:</label>
        <input type="text" id="carrera" name="carrera" required min="3">
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" pattern="[a-zA-Z0-9]+@utez\.edu\.mx$">
        <button type="submit">Registrar</button>
        <a href="servlet-inicio"><button type="reset">Cancelar</button></a>

    </form>

</body>
</html>
