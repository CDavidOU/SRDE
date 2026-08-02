<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 8/1/2026
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${listaDocentes}" var="docente">
    <p>${docente.nombre} ${docente.apellido}</p> <p>${docente.estado}</p>
    <form action="servlet-lista-docente" method="post">
        <input type="hidden" name="idDocente" value="${docente.id}">
        <button type="submit">Detalles</button>
    </form>
</c:forEach>
</body>
</html>
