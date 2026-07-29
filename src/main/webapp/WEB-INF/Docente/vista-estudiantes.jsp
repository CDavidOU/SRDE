<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/24/2026
  Time: 7:45 PM
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
    <button type="submit">registrar</button>
</form>
<table>

    <tbody>
    <c:forEach var="asignacionEstadias" items="${listaEstudiantes}">
        <tr>
            <td><c:out value="${asignacionEstadias.matricula}" /></td>
            <td><c:out value="${asignacionEstadias.datosEstudiante.nombre}" /></td>
            <td>
                <button type="button">Ver Reportes</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
