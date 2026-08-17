<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gráfica</title>
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

        <!-- Encabezado -->
        <div class="w-100 text-center mb-4">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Gráfica</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="w-100" style="max-width: 850px;">

                <!-- Tarjetas de totales -->
                <div class="row g-3 mb-4">
                    <div class="col-4">
                        <div class="card border-0 shadow-sm rounded-3 text-center p-3 text-white" style="background-color: #002E60;">
                            <span class="fs-3 fw-bold"><c:out value="${totalEstudiantesActivos}" /></span>
                            <span class="fs-6">Alumnos activos</span>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="card border-0 shadow-sm rounded-3 text-center p-3 text-white" style="background-color: #429983;">
                            <span class="fs-3 fw-bold"><c:out value="${totalDocentes}" /></span>
                            <span class="fs-6">Docentes</span>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="card border-0 shadow-sm rounded-3 text-center p-3 text-white" style="background-color: #D4AC0D;">
                            <span class="fs-4 fw-bold"><c:out value="${periodoActual.nombre_periodo}" /></span>
                            <span class="fs-6">Periodo actual</span>
                        </div>
                    </div>
                </div>

                <!-- Alumnos por docente -->
                <div class="card border-0 shadow-sm rounded-3 p-4 bg-white mb-4">
                    <h5 class="fw-bold text-secondary mb-3">Alumnos por docente (periodo actual)</h5>
                    <c:choose>
                        <c:when test="${not empty listaDocentes}">
                            <div class="d-flex flex-column gap-3">
                                <c:forEach var="docente" items="${listaDocentes}">
                                    <div>
                                        <div class="d-flex justify-content-between mb-1">
                                            <span class="text-secondary fw-semibold"><c:out value="${docente.nombre} ${docente.apellido}" /></span>
                                            <span class="text-secondary fw-bold"><c:out value="${docente.numAlumnos}" /></span>
                                        </div>
                                        <div class="progress" style="height: 14px;">
                                            <div class="progress-bar" role="progressbar"
                                                 style="width: ${docente.numAlumnos * 100 / maxAlumnosPorDocente}%; background-color: #002E60;">
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center text-secondary py-3">No hay docentes registrados.</div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- Alumnos por carrera -->
                <div class="card border-0 shadow-sm rounded-3 p-4 bg-white mb-4">
                    <h5 class="fw-bold text-secondary mb-3">Alumnos activos por carrera</h5>
                    <c:choose>
                        <c:when test="${not empty listaCarreras}">
                            <div class="d-flex flex-column gap-3">
                                <c:forEach var="carrera" items="${listaCarreras}">
                                    <div>
                                        <div class="d-flex justify-content-between mb-1">
                                            <span class="text-secondary fw-semibold"><c:out value="${carrera.etiqueta}" /></span>
                                            <span class="text-secondary fw-bold"><c:out value="${carrera.cantidad}" /></span>
                                        </div>
                                        <div class="progress" style="height: 14px;">
                                            <div class="progress-bar" role="progressbar"
                                                 style="width: ${carrera.cantidad * 100 / maxAlumnosPorCarrera}%; background-color: #429983;">
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center text-secondary py-3">No hay estudiantes registrados.</div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
