<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/29/2026
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Detalles</title>
</head>
<body>
        <a href="servlet-lista-estudiante">
            <button type="button"><</button>
        </a>
        <p>${datosEstudiante.nombre}</p>
        <p>${datosEstudiante.apellido}</p>
        <p>${datosEstudiante.carrera}</p>
        <p>${datosEstudiante.matricula}</p>
        <p>${datosEstudiante.cuatrimestre}</p>
        <p>${datosEstudiante.correo}</p>
        <p>${datosEstudiante.estado}</p>

</body>
</html>
