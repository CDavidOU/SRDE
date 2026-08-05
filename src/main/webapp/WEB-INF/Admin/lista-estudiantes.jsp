<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 8/2/2026
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Estudiantes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

    <!-- Menú Lateral -->
    <div id="menu" class="bg-white border-end" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>

    <!-- Contenido Principal -->
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

        <!-- Encabezado con Botón de Registro Integrado -->
        <div class="w-100 position-relative d-flex align-items-center justify-content-center py-3 text-white" style="background-color: #002E60">
            <h1 class="m-0 fs-2 fw-normal">Estudiantes</h1>

            <!-- Botón de registrar -->
            <a href="${pageContext.request.contextPath}/servlet-registro-estudiante"
               class="btn btn-success position-absolute end-0 me-4 d-flex align-items-center justify-content-center p-0 rounded"
               style="width: 38px; height: 38px;"
               title="Registrar Nuevo Estudiante">
                <i class="bi bi-person-plus-fill fs-5"></i>
            </a>
        </div>

        <!-- Área de Datos -->
        <div id="datos" class="p-4 flex-grow-1 ">
            <div class="container" style="max-width: 800px;">
                <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 850px;">

                    <!-- Barra de Búsqueda -->

                    <form action="${pageContext.request.contextPath}/servlet-estudiantes-admin" method="post">
                        <div id="busqueda" class="row g-2 mb-4">
                            <div class="col-9">
                                <div class="input-group">
                                    <span class="input-group-text bg-white border-end-0">
                                        <i class="bi bi-search text-muted"></i>
                                    </span>
                                    <!-- Mantener el texto buscado en el input -->
                                    <input id="buscador"
                                           name="buscador"
                                           type="text"
                                           value="${terminoBuscado}"
                                           placeholder="Buscar por nombre, apellido o matrícula"
                                           class="form-control border-start-0 p-2">
                                </div>
                            </div>
                            <div class="col-3">
                                <button class="btn btn-success w-100 h-100 fw-medium" type="submit">Buscar</button>
                            </div>
                        </div>
                    </form>

                    <!-- Muestra si se agrego correctamente al estudiante -->
                    <c:if test="${not empty sessionScope.mensajeOk}">
                        <div class="alert alert-success alert-dismissible fade show text-center m-3" role="alert">
                            <c:out value="${sessionScope.mensajeOk}" />
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                        <%-- Limpiamos el mensaje de la sesión para que no vuelva a aparecer al recargar --%>
                        <c:remove var="mensajeOk" scope="session" />
                    </c:if>

                    <!-- Tabla de Estudiantes -->
                    <div id="Estudiantes" class="row">
                        <div class="col-12">
                            <table class="table align-middle bg-white border-1">
                                <tbody>
                                <c:forEach var="asignacionEstadias" items="${listaTodosLosEstudiantes}">
                                    <tr class="border-bottom">
                                        <td class="py-3 fs-5 w-20">
                                            <c:out value="${asignacionEstadias.estudiante.matricula}" />
                                        </td>
                                        <td class="py-3 fs-5 w-80">
                                            <c:out value="${asignacionEstadias.estudiante.nombre}" />
                                        </td>
                                        <td class="py-3 text-end text-nowrap">
                                            <!-- Botón de Detalles -->
                                            <a href="servlet-datos-estudiante?matricula=${asignacionEstadias.estudiante.matricula}">
                                                <button class="btn px-3 py-1 me-2 text-white" style="background-color: #002E60" type="button">
                                                    Detalles <i class="bi bi-journal-text ms-1"></i>
                                                </button>
                                            </a>

                                            <!-- Formulario para Desasignar -->
                                            <form action="${pageContext.request.contextPath}/servlet-eliminar-asignacion" method="POST" class="d-inline" onsubmit="return confirm('¿Estás seguro de desasignar a este estudiante?');">
                                                <input type="hidden" name="matricula" value="${asignacionEstadias.matricula}" />
                                                <input type="hidden" name="id_periodo" value="${asignacionEstadias.id_periodo}" />
                                                <button class="btn btn-link text-dark p-0 ms-1 align-middle" type="submit" title="Desasignar">
                                                    <i class="bi bi-arrow-down-up fs-5"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>