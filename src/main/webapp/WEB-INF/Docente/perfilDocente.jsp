<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/24/2026
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="contenido">
    <p>${sessionScope.docenteLogueado.nombre}</p>
    <p>${sessionScope.docenteLogueado.apellido}</p>
    <p>${sessionScope.docenteLogueado.correo}</p>
    <p>${sessionScope.docenteLogueado.telefono}</p>
    <p>${sessionScope.docenteLogueado.carrera}</p>
    <p>${sessionScope.docenteLogueado.academia}</p>
</div>
<div id="menu">
    <form action="servlet-inicio" method="post">
        <button type="submit">Perfil</button>
    </form>
    <form action="servlet-" method="post">
        <button type="submit">Periodos</button>
    </form>
    <form action="servlet-" method="post">
        <button type="submit">Estudiantes</button>
    </form>
    <form action="servlet-" method="post">
        <button type="submit">salir</button>
    </form>
    <form action="servlet-" method="post">
        <button type="submit">Notificaciones</button>
    </form>
</div>
</body>
</html>
