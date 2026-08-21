<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendarios Programados</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
        .btn-editar {
            background-color: #345177;
            color: #ffffff;
            border: 1px solid #345177;
            transition: all 0.2s ease-in-out;
        }
        .btn-editar:hover {
            background-color: #ffffff;
            color: #345177;
            border-color: #345177;
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
        <div class="w-100 position-relative d-flex align-items-center justify-content-center py-3 text-white" style="background-color: #002E60">
            <h1 class="m-0 fs-2 fw-normal">Calendarios Programados De Documentos</h1>
        </div>

        <!-- Área de Datos -->
        <div id="datos" class="p-4 flex-grow-1">
            <div class="container" style="max-width: 850px;">
                <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100">

                    <!-- Subtítulo con el Periodo Activo -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="text-secondary m-0">
                            Periodo:
                            <strong class="text-dark">
                                ${periodoActivo != null ? periodoActivo.nombre_periodo : 'Sin Periodo Activo'}
                            </strong>
                        </h5>
                        <a href="servlet-crear-calendario" class="btn btn-success position-absolute end-0 me-3 d-flex align-items-center justify-content-center shadow-sm" style="background-color: #429983;" >Registrar calendario</a>
                    </div>
                    <c:if test="${not empty mensajeCorrecto}">
                        <div class="alert alert-success" role="alert">
                            <p>${mensajeCorrecto}</p>
                        </div>
                    </c:if>
                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger" role="alert">
                            <p>${mensajeError}</p>
                        </div>
                    </c:if>

                    <!-- Tabla de Datos -->
                    <div class="table-responsive">
                        <table class="table table-hover align-middle border">
                            <thead class="table-light">
                            <tr>
                                <th>Documento</th>
                                <th>Fecha Inicio</th>
                                <th>Fecha Límite</th>
                                <th class="text-center">Acción</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${not empty listaProgramada}">
                                    <c:forEach items="${listaProgramada}" var="cal">
                                        <tr>
                                            <td class="fw-semibold">
                                                    ${cal.nombreDoc}
                                            </td>
                                            <td>
                                                    ${cal.fechaInicio}
                                            </td>
                                            <td>
                                                    ${cal.fechaLimite}
                                            </td>
                                            <td class="text-center">
                                                <form action="${pageContext.request.contextPath}/listaCalendariosServlet" method="POST" class="d-inline">
                                                    <input type="hidden" name="idCalendario" value="${cal.idCalendario}">

                                                    <button type="submit" class="btn btn-editar btn-sm d-flex align-items-center gap-1 mx-auto" title="Editar Calendario">
                                                        <i class="bi bi-pencil-square"></i> Editar
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="4" class="text-center py-4 text-muted">
                                            <i class="bi bi-calendar-x fs-3 d-block mb-2"></i>
                                            No hay calendarios programados para el periodo actual.
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>