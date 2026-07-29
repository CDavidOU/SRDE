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
<c:if test="${not empty mensajeVacio}">
    <div class="alert alert-danger text-center py-2 mb-3" role="alert">
            ${mensajeVacio}
    </div>
</c:if>
<form action="servlet-formulario-estudiante" method="POST">
    <button type="submit">registrar</button>
</form>
<tbody>
<c:forEach var="asignacionEstadias" items="${listaEstudiantesActivos}">
    <tr>
        <td><c:out value="${asignacionEstadias.matricula}" /></td>
        <td><c:out value="${asignacionEstadias.estudiante.nombre}" /></td>
        <td>
            <button type="button">Ver Reportes</button>
        </td>
    </tr>
</c:forEach>
</tbody>
</body>
</html>
