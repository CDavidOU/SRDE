<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/21/2026
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio</title>
</head>
<body>
    <div id="contenido">
        <p>${sessionScope.adminLogueado.nombre}</p>
        <p>${sessionScope.adminLogueado.apellido}</p>
        <p>${sessionScope.adminLogueado.correo}</p>
        <p>${sessionScope.adminLogueado.telefono}</p>
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
            <button type="submit">Docente</button>
        </form>
        <form action="servlet-" method="post">
            <button type="submit">Grafica</button>
        </form>
        <form action="servlet-" method="post">
            <button type="submit">Salir</button>
        </form>
        <form action="servlet-" method="post">
            <button type="submit">Notificaciones</button>
        </form>
    </div>

</body>
</html>
