<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Notificaciones</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Iconos de Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<div class="d-flex">
    <!-- 1. Menú Lateral -->
    <div id="menu" class="flex-shrink-0" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>
    <!-- 2. Área Principal -->
    <div class="flex-grow-1 bg-white">

        <!-- Cabecera / Header Superior -->
        <div class="w-100 text-center text-white py-3 fw-bold fs-3" style="background-color: #002B66;">
            Notificaciones
        </div>

        <!-- Contenedor de Contenido -->
        <div class="container my-4 px-5" style="max-width: 900px;">

            <!-- Pestañas para alternar entre Notificaciones Activas y Ocultas -->
            <ul class="nav nav-tabs mb-4" id="notiTabs" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active fw-bold" id="activas-tab" data-bs-toggle="tab" data-bs-target="#activas" type="button" role="tab">
                        <i class="bi bi-bell-fill me-1"></i> Activas
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link text-secondary fw-bold" id="ocultas-tab" data-bs-toggle="tab" data-bs-target="#ocultas" type="button" role="tab">
                        <i class="bi bi-eye-slash-fill me-1"></i> Ocultas
                    </button>
                </li>
            </ul>

            <div class="tab-content" id="notiTabsContent">

                <!-- ================= TAB 1: ACTIVAS (visto == 1) ================= -->
                <div class="tab-pane fade show active" id="activas" role="tabpanel">
                    <c:set var="tieneActivas" value="false" />

                    <c:forEach items="${listaNotificaciones}" var="noti">
                        <c:if test="${noti.visibilidad == 1}">
                            <c:set var="tieneActivas" value="true" />
                            <div class="card mb-3 border shadow-sm">
                                <div class="card-body d-flex align-items-center justify-content-between p-3">

                                    <!-- Icono y Contenido -->
                                    <div class="d-flex align-items-start">
                                        <i class="bi bi-bell-fill fs-2 me-3 text-dark mt-1"></i>
                                        <div>
                                            <h5 class="fw-bold mb-1" style="color: #002B66;">${noti.nombreDoc}</h5>
                                            <p class="mb-1 text-muted fw-semibold">
                                                <i class="bi bi-calendar-event me-1"></i>Fecha límite: ${noti.fechaLimite}
                                            </p>
                                            <p class="mb-0 text-secondary fs-6">${noti.comentario}</p>
                                        </div>
                                    </div>

                                    <!-- Formulario para Ocultar -->
                                    <div>
                                        <form method="POST" action="servlet-mostrar-notificaciones" class="m-0">
                                            <input type="hidden" name="idNoti" value="${noti.idCalendario}">
                                            <input type="hidden" name="accion" value="ocultar">
                                            <button type="submit"
                                                    class="btn text-white px-3 py-2 fw-semibold"
                                                    style="background-color: #C85252;"
                                                    onclick="return confirm('¿Deseas archivar esta notificación?');">
                                                <i class="bi bi-trash3-fill me-1"></i> Ocultar
                                            </button>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${!tieneActivas}">
                        <div class="alert alert-info text-center fs-5 shadow-sm">
                            <em>${not empty mensajeNoti ? mensajeNoti : 'No tienes notificaciones activas.'}</em>
                        </div>
                    </c:if>
                </div>

                <!-- ================= TAB 2: OCULTAS (visto == 0) ================= -->
                <div class="tab-pane fade" id="ocultas" role="tabpanel">
                    <c:set var="tieneOcultas" value="false" />

                    <c:forEach items="${listaNotificaciones}" var="noti">
                        <c:if test="${noti.visibilidad == 0}">
                            <c:set var="tieneOcultas" value="true" />
                            <div class="card mb-3 border bg-light shadow-sm">
                                <div class="card-body d-flex align-items-center justify-content-between p-3">

                                    <!-- Icono y Contenido (Estilo desvanecido) -->
                                    <div class="d-flex align-items-start opacity-75">
                                        <i class="bi bi-eye-slash-fill fs-2 me-3 text-secondary mt-1"></i>
                                        <div>
                                            <h5 class="fw-bold mb-1 text-secondary">${noti.nombreDoc}</h5>
                                            <p class="mb-1 text-muted fw-semibold">
                                                <i class="bi bi-calendar-event me-1"></i>Fecha límite: ${noti.fechaLimite}
                                            </p>
                                            <p class="mb-0 text-secondary fs-6">${noti.comentario}</p>
                                        </div>
                                    </div>

                                    <!-- Formulario para Desocultar -->
                                    <div>
                                        <form method="POST" action="servlet-mostrar-notificaciones" class="m-0">
                                            <input type="hidden" name="idNoti" value="${noti.idCalendario}">
                                            <input type="hidden" name="accion" value="restaurar">
                                            <button type="submit"
                                                    class="btn btn-success text-white px-3 py-2 fw-semibold"
                                                    onclick="return confirm('¿Deseas restaurar esta notificación?');">
                                                <i class="bi bi-arrow-counterclockwise me-1" style="background-color: #429983;" ></i> Desocultar
                                            </button>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${!tieneOcultas}">
                        <div class="alert alert-secondary text-center fs-5 shadow-sm">
                            <em>No tienes notificaciones archivadas.</em>
                        </div>
                    </c:if>
                </div>

            </div>

        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>