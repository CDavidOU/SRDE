<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 8/2/2026
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Estudiantes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

    <!-- Menú Lateral -->
    <div id="menu" class="bg-white border-end" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

        <!-- Encabezado -->
        <div class="w-100 text-center mb-4">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Estudiantes</h1>
        </div>
        <div class="d-flex flex-column gap-2 mb-4">

        <!-- Estudiantes -->

        <c:forEach items="${listaTodosLosEstudiantes}" var="asignacion">
            <div class="card border border-secondary border-opacity-25 rounded-3 shadow-sm">
                <div class="card-body py-2 px-3 d-flex align-items-center justify-content-between">
                    <div class="d-flex align-items-center gap-3">
                        <div class="col-6">
                                <p>${asignacion.estudiante.nombre} ${asignacion.estudiante.apellido}   ${asignacion.estudiante.estado}</p
                                <form action="${pageContext}" method="post">
                                    <input type="hidden" name="matricula" value="${asignacion.matricula}">
                                    <a href="${pageContext.request.contextPath}/servlet-datos-estudiante-admin?matricula=${asignacion.matricula}"
                                       class="btn btn-primary w-100 py-2 fw-medium rounded-3"
                                       style="background-color: #002E60; border: none; text-align: center; display: block; color: white; text-decoration: none;">
                                        Detalles
                                    </a>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
