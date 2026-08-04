<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Periodos</title>
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
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Periodos</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="w-100" style="max-width: 850px;">

                <div class="d-flex flex-column gap-2 mb-4">

                    <c:choose>
                        <c:when test="${not empty listaPeriodos}">
                            <c:forEach var="periodo" items="${listaPeriodos}">
                                <div class="card border border-secondary border-opacity-25 rounded-3 shadow-sm">
                                    <div class="card-body py-2 px-3 d-flex align-items-center justify-content-between">
                                        <div class="d-flex align-items-center gap-3">
                                            <div class="rounded-circle bg-dark text-white d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">
                                                <i class="bi bi-calendar3 fs-5"></i>
                                            </div>
                                            <div class="d-flex flex-column">
                                                <span class="fw-semibold text-secondary fs-5"><c:out value="${periodo.nombre_periodo}" /></span>
                                                <span class="text-muted small">
                                                    <fmt:formatDate value="${periodo.fecha_inicio}" pattern="dd/MM/yyyy" /> -
                                                    <fmt:formatDate value="${periodo.fecha_fin}" pattern="dd/MM/yyyy" />
                                                </span>
                                            </div>
                                        </div>
                                        <span class="fw-bold text-secondary fs-5">Alumnos: <c:out value="${periodo.totalEstudiantes}" /></span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center text-secondary fs-5 py-4">
                                <c:out value="${mensajeVacio}" default="No hay periodos registrados." />
                            </div>
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
