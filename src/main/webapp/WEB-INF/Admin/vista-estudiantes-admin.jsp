<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estudiantes</title>
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


        <div class="w-100 position-relative d-flex align-items-center justify-content-center py-3 text-white" style="background-color: #002E60">
            <h1 class="m-0 fs-2 fw-normal">Estudiantes</h1>
            <a href="${pageContext.request.contextPath}/servlet-registro-estudiante"
               class="btn btn-success position-absolute end-0 me-4 d-flex align-items-center justify-content-center p-0 rounded"
               style="width: 38px; height: 38px;"
               title="Registrar Nuevo Estudiante">
                <i class="bi bi-person-plus-fill fs-5"></i>
            </a>
        </div>

        <!-- Área de Datos -->
        <div id="datos" class="p-4 flex-grow-1">
            <div class="container" style="max-width: 800px;">
                <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 850px;">

                    <!-- Barra de Búsqueda con botón de Limpiar -->
                    <form action="${pageContext.request.contextPath}/servlet-admin-estudiantes" method="post" class="mb-4">
                        <div id="busqueda" class="row g-2">

                            <!-- Caja de texto -->
                            <div class="col-8">
                                <div class="input-group h-100">
                                    <span class="input-group-text bg-white border-end-0">
                                        <i class="bi bi-search text-muted"></i>
                                    </span>
                                    <input id="buscador"
                                           name="buscador"
                                           type="text"
                                           value="${terminoBuscado}"
                                           placeholder="Buscar por nombre, apellido o matrícula"
                                           class="form-control border-start-0 p-2">
                                </div>
                            </div>

                            <!-- Botones -->
                            <div class="col-4 d-flex gap-2">
                                <button class="btn btn-success flex-grow-1 fw-medium" type="submit">Buscar</button>

                                <c:if test="${not empty terminoBuscado}">
                                    <a href="${pageContext.request.contextPath}/servlet-admin-estudiantes"
                                       class="btn btn-outline-secondary flex-grow-1 fw-medium d-flex align-items-center justify-content-center"
                                       title="Borrar búsqueda y ver todos">
                                        <i class="bi bi-x-circle me-1"></i> Limpiar
                                    </a>
                                </c:if>
                            </div>

                        </div>
                    </form>

                    <!-- Tablas de Estudiantes -->
                    <div id="Estudiantes" class="row">
                        <div class="col-12">
                            <c:choose>
                                <c:when test="${not empty listaEstudiantes}">

                                    <!-- ==========================================
                                    1. TABLA: ESTUDIANTES ACTIVOS
                                    ========================================== -->
                                    <c:set var="hayActivos" value="false" />
                                    <c:forEach var="est" items="${listaEstudiantes}">
                                        <c:if test="${est.estado eq 'Activo'}">
                                            <c:set var="hayActivos" value="true" />
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${hayActivos}">
                                        <div class="d-flex align-items-center my-3">
                                            <span class="fw-bold text-success text-uppercase small me-3">Activos</span>
                                            <hr class="flex-grow-1 my-0 text-secondary opacity-25">
                                        </div>

                                        <table class="table align-middle bg-white border-1 mb-4">
                                            <thead>
                                            <tr class="border-bottom">
                                                <th class="fs-6 text-secondary">Matrícula</th>
                                                <th class="fs-6 text-secondary">Nombre</th>
                                                <th class="fs-6 text-secondary">Carrera</th>
                                                <th class="fs-6 text-secondary">Docente asignado</th>
                                                <th class="fs-6 text-secondary"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="estudiante" items="${listaEstudiantes}">
                                                <c:if test="${estudiante.estado eq 'Activo'}">
                                                    <tr class="border-bottom">
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.matricula}" /></td>
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.nombre} ${estudiante.apellido}" /></td>
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.carrera}" /></td>
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.docenteAsignado}" /></td>
                                                        <td class="py-3 text-end text-nowrap">
                                                            <a href="servlet-datos-estudiante?matricula=${estudiante.matricula}"
                                                               class="btn px-3 py-1 me-2 text-white"
                                                               style="background-color: #002E60">
                                                                Detalles <i class="bi bi-journal-text ms-1"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>

                                    <!-- ==========================================
                                    2. SECCIÓN Y TABLA: INACTIVOS
                                    ========================================== -->
                                    <c:set var="hayInactivos" value="false" />
                                    <c:forEach var="est" items="${listaEstudiantes}">
                                        <c:if test="${est.estado ne 'Activo'}">
                                            <c:set var="hayInactivos" value="true" />
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${hayInactivos}">
                                        <!-- Divisor de Inactivos -->
                                        <div class="d-flex align-items-center my-3">
                                            <span class="fw-bold text-danger text-uppercase small me-3">Inactivos</span>
                                            <hr class="flex-grow-1 my-0 text-secondary opacity-25">
                                        </div>

                                        <table class="table align-middle border-1 mb-0" style="background-color: #fdfdfd;">
                                            <thead>
                                            <tr class="border-bottom">
                                                <th class="fs-6 text-secondary">Matrícula</th>
                                                <th class="fs-6 text-secondary">Nombre</th>
                                                <th class="fs-6 text-secondary">Carrera</th>
                                                <th class="fs-6 text-secondary">Docente asignado</th>
                                                <th class="fs-6 text-secondary"></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="estudiante" items="${listaEstudiantes}">
                                                <c:if test="${estudiante.estado ne 'Activo'}">
                                                    <tr class="border-bottom text-muted">
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.matricula}" /></td>
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.nombre} ${estudiante.apellido}" /></td>
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.carrera}" /></td>
                                                        <td class="py-3 fs-6"><c:out value="${estudiante.docenteAsignado}" /></td>
                                                        <td class="py-3 text-end text-nowrap">
                                                            <a href="servlet-datos-estudiante?matricula=${estudiante.matricula}"
                                                               class="btn px-3 py-1 me-2 text-white"
                                                               style="background-color: #002E60">
                                                                Detalles <i class="bi bi-journal-text ms-1"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>

                                </c:when>
                                <c:otherwise>
                                    <div class="text-center text-secondary fs-5 py-4">
                                        <c:out value="${mensajeVacio}" default="No hay estudiantes registrados." />
                                    </div>
                                </c:otherwise>
                            </c:choose>
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
