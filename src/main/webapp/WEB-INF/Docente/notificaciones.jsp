<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 8/1/2026
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Notificaciones</title>
</head>
<body>
<c:if test="${empty listaNotificaciones}">
    <p><em>${mensajeNoti}</em></p>
</c:if>
<c:forEach items="${listaNotificaciones}" var="noti">
    <p>Notificacion</p>
    <p>${noti.fechaLimite}</p>
    <p>${noti.descripcion}</p>
    <form method="POST" action="servlet-mostrar-notificaciones">
        <input type="hidden" name="idNoti" value="${noti.idCalendario}">
        <button type="submit" onclick="return confirm('¿Deseas archivar esta notificación?');">
            Eliminar
        </button>
    </form>
</c:forEach>

</body>
</html>
