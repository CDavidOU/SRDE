<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Periodo</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
        .accordion-button:not(.collapsed) {
            background-color: transparent;
            color: inherit;
            box-shadow: none;
        }
        .accordion-button:focus {
            box-shadow: none;
        }
        .selector-flecha:not(.collapsed) i {
            transform: rotate(180deg);
            display: inline-block;
            transition: transform 0.2s ease-in-out;
        }
        .selector-flecha.collapsed i {
            transform: rotate(0deg);
            display: inline-block;
            transition: transform 0.2s ease-in-out;
        }
    </style>
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
        <div class="w-100 position-relative d-flex align-items-center justify-content-center py-3 text-white mb-4" style="background-color: #002E60">
            <h1 class="m-0 text-white">Periodo</h1>
            <!-- Botón de calendarios -->
            <a href="${pageContext.request.contextPath}/listaCalendariosServlet"
               class="btn btn-success position-absolute end-0 me-3 d-flex align-items-center justify-content-center shadow-sm"
<<<<<<< HEAD
               style="width: 38px; height: 38px; z-index: 40;"
               title="Calendario de Documentos">
=======
               style="background-color: #429983;"
               title="Calendarios Programados">
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                <i class="bi bi-calendar-plus fs-5"></i>
            </a>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <!-- Contenedor principal alineado -->
            <div class="w-100" style="max-width: 850px;">

                <!-- Buscador / Filtro superior -->
                <form action="${pageContext.request.contextPath}/servlet-periodo-admin" method="POST" class="row g-2 mb-4 justify-content-center">
                    <div class="col-9">
                        <div class="input-group">
                            <select class="form-select border-start-0 p-2" name="filtroPeriodo" id="filtroPeriodo" required>
                                <option value="" disabled>Periodo:</option>
                                <c:forEach var="p" items="${listaPeriodos}">
                                    <option value="${p.id_periodo}" ${idPeriodoActivo == p.id_periodo ? 'selected' : ''}>
                                        <c:out value="${p.nombre_periodo}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-3">
<<<<<<< HEAD
                        <button class="btn w-100 py-2 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">
=======
                        <button class="btn w-100 py-2 text-white fw-medium rounded-3" style="background-color: #429983;"type="submit">
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                            Buscar
                        </button>
                    </div>
                </form>

                <!-- Lista de Periodos -->
                <div class="accordion d-flex flex-column gap-3" id="acordeonPeriodos">

                    <c:forEach var="p" items="${listaPeriodos}">
                        <c:set var="estaAbierto" value="${idPeriodoActivo == p.id_periodo}" />

                        <div class="card border border-secondary border-opacity-25 rounded-3 shadow-sm bg-white">

                            <!-- Cabecera -->
                            <div class="card-header bg-white border-0 py-2 px-3 d-flex align-items-center justify-content-between">
                                <span class="fw-normal fs-5 text-dark">PERIODO : <c:out value="${p.nombre_periodo}"/></span>

                                <button class="btn p-0 border-0 fs-4 text-dark selector-flecha ${estaAbierto ? '' : 'collapsed'}"
                                        type="button"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#periodo_${p.id_periodo}"
                                        aria-expanded="${estaAbierto ? 'true' : 'false'}"
                                        aria-controls="periodo_${p.id_periodo}">
                                    <i class="bi bi-chevron-down"></i>
                                </button>
                            </div>

                            <!-- Contenido desplegable -->
                            <div id="periodo_${p.id_periodo}"
                                 class="collapse ${estaAbierto ? 'show' : ''}"
                                 data-bs-parent="#acordeonPeriodos">

                                <div class="card-body pt-0 px-3 pb-3 d-flex flex-column gap-2">

                                    <c:choose>
                                        <c:when test="${not empty p.listaEstudiantes}">
                                            <c:forEach var="est" items="${p.listaEstudiantes}">

                                                <div class="border border-secondary border-opacity-25 rounded-2 p-2 d-flex align-items-center justify-content-between bg-white">
                                                    <span class="fs-5 text-secondary ps-2">
                                                        <c:out value="${est.nombre} ${est.apellido}"/>
                                                    </span>
                                                    <a href="${pageContext.request.contextPath}/servlet-datos-estudiante?matricula=${est.matricula}"
                                                       class="btn text-white px-3 py-1 d-flex align-items-center gap-2"
                                                       style="background-color: #002E60;">
                                                        Detalles <i class="bi bi-file-earmark-text-fill"></i>
                                                    </a>
                                                </div>

                                            </c:forEach>
                                        </c:when>

                                        <c:otherwise>
                                            <div class="text-muted p-2 text-center fs-6">
                                                No hay estudiantes asignados en este periodo.
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>

                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>