<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notificaciones</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

    <!-- Menú Lateral -->
    <div id="menu" class="bg-white border-end" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="menu.jsp" />
    </div>

    <!-- Contenido Principal -->
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

        <!-- Encabezado -->
        <div class="w-100 text-center mb-4">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Notificaciones</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="w-100" style="max-width: 850px;">

                <!-- ========================================== -->
                <!-- ALERTA DE ÉXITO (Si se envió el mensaje)   -->
                <!-- ========================================== -->
                <c:if test="${not empty sessionScope.mensajeExito}">
                    <div class="alert alert-success alert-dismissible fade show shadow-sm border-0 d-flex align-items-center" role="alert" style="background-color: #d1e7dd; color: #0f5132;">
                        <i class="bi bi-check-circle-fill me-2 fs-5"></i>
                        <div><strong>${sessionScope.mensajeExito}</strong></div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <!-- Borramos el mensaje para que no salga de nuevo al recargar -->
                    <c:remove var="mensajeExito" scope="session"/>
                </c:if>

                <!-- ========================================== -->
                <!-- ALERTA DE ERROR                            -->
                <!-- ========================================== -->
                <c:if test="${not empty sessionScope.mensajeError}">
                    <div class="alert alert-danger alert-dismissible fade show shadow-sm border-0 d-flex align-items-center" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
                        <div><strong>${sessionScope.mensajeError}</strong></div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <c:remove var="mensajeError" scope="session"/>
                </c:if>
                <!-- ========================================== -->


                <c:choose>
                    <c:when test="${not empty listaNotificaciones}">
                        <div class="d-flex flex-column gap-2">
                            <c:forEach var="notificacion" items="${listaNotificaciones}">
                                <div class="card border border-secondary border-opacity-25 rounded-3 shadow-sm">
                                    <div class="card-body py-2 px-3 d-flex align-items-center justify-content-between">
                                        <div class="d-flex align-items-center gap-3">
                                            <div class="rounded-circle text-white d-flex align-items-center justify-content-center" style="width: 38px; height: 38px; background-color: #D4AC0D;">
                                                <i class="bi bi-file-earmark-excel-fill fs-5"></i>
                                            </div>
                                            <div class="d-flex flex-column">
                                                <span class="fw-semibold text-secondary fs-6">
                                                    <c:out value="${notificacion.nombreDocumento}" /> pendiente &ndash;
                                                    <c:out value="${notificacion.estudianteNombre} ${notificacion.estudianteApellido}" />
                                                </span>
                                                <span class="text-muted small">
                                                    Matrícula: <c:out value="${notificacion.matricula}" />
                                                    <c:if test="${not empty notificacion.docenteNombre}">
                                                        &middot; Docente: <c:out value="${notificacion.docenteNombre}" />
                                                    </c:if>
                                                </span>
                                            </div>
                                        </div>
                                        <a href="${pageContext.request.contextPath}/servlet-datos-estudiante?matricula=${notificacion.matricula}"
                                           class="btn text-white d-flex align-items-center justify-content-center px-3" style="background-color: #002E60;">
                                            Ver <i class="bi bi-arrow-right ms-1"></i>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="text-center text-secondary py-5">
                            <i class="bi bi-bell-slash fs-1 d-block mb-3"></i>
                            <span class="fs-5">No hay notificaciones</span>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>