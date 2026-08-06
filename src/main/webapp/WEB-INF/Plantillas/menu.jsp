<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="servicioNotificaciones" class="mx.edu.utez.pres.srde.service.ServicioNotificaciones" scope="page" />

<%-- Documentos pendientes: por sus estudiantes si es Docente, de todo el sistema si es Admin --%>
<c:if test="${not empty sessionScope.docenteLogueado}">
    <c:set var="totalNotificaciones" value="${servicioNotificaciones.contarDocumentosPendientesDocente(sessionScope.docenteLogueado.id)}" />
</c:if>
<c:if test="${not empty sessionScope.adminLogueado}">
    <c:set var="totalNotificaciones" value="${servicioNotificaciones.contarDocumentosPendientesGlobal()}" />
</c:if>

<!-- Carga de Bootstrap Icons para la campana -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<!-- Contenedor principal del sidebar fijado a la izquierda (sticky) -->
<div class="d-flex flex-column justify-content-between p-0 text-white"
     style="background-color: #002E60; position: sticky; top: 0; height: 100vh; overflow-y: auto; z-index: 1000;">

    <!-- ========================================== -->
    <!-- BLOQUE SUPERIOR: LOGO Y OPCIONES DE MENÚ   -->
    <!-- ========================================== -->
    <div>
        <!-- Logo UTEZ sin margen inferior -->
        <div class="bg-white p-3 text-center mb-0">
            <img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png" class="img-fluid" alt="Logo UTEZ" style="height: 110px; width: 100%;">
        </div>

        <c:choose>
            <c:when test="${not empty sessionScope.docenteLogueado}">
                <!-- Menú Docente -->
                <div class="list-group list-group-flush rounded-0 m-0">
                    <a href="${pageContext.request.contextPath}/servlet-inicio"
                       class="list-group-item list-group-item-action text-white d-flex align-items-center border-0 px-3 py-3"
                       style="background-color: #429983;">
                        <img src="${pageContext.request.contextPath}/imagenes/user.png" class="rounded-circle me-3" alt="Icono usuario" style="width: 35px; height: 35px; background: white; padding: 2px;">
                        <div class="d-flex flex-column text-start w-100">
                            <span class="fw-bold" style="font-size: 0.95rem;">Docente</span>
                            <input type="text" value="${sessionScope.docenteLogueado.nombre}" class="form-control form-control-sm p-0 text-white bg-transparent border-0 fw-light" disabled style="font-size: 0.9rem;">
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet-periodos" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Periodos</a>
                    <a href="${pageContext.request.contextPath}/servlet-lista-estudiantes" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Estudiantes</a>
                    <a href="${pageContext.request.contextPath}/servlet-logout" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Salir</a>
                </div>
            </c:when>

            <c:when test="${not empty sessionScope.adminLogueado}">
                <!-- Menú Admin -->
                <div class="list-group list-group-flush rounded-0 m-0">
                    <a href="${pageContext.request.contextPath}/servlet-inicio"
                       class="list-group-item list-group-item-action text-white d-flex align-items-center border-0 px-3 py-3"
                       style="background-color: #429983;">
                        <img src="${pageContext.request.contextPath}/imagenes/user.png" class="rounded-circle me-3" alt="Icono usuario" style="width: 35px; height: 35px; background: white; padding: 2px;">
                        <div class="d-flex flex-column text-start w-100">
                            <span class="fw-bold" style="font-size: 0.95rem;">Admin</span>
                            <input type="text" value="${sessionScope.adminLogueado.nombre}" class="form-control form-control-sm p-0 text-white bg-transparent border-0 fw-light" disabled style="font-size: 0.9rem;">
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet-admin-periodos" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Periodos</a>
                    <a href="${pageContext.request.contextPath}/servlet-admin-estudiantes" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Estudiantes</a>
                    <a href="${pageContext.request.contextPath}/servlet-lista-docentes" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Docentes</a>
                    <a href="${pageContext.request.contextPath}/servlet-admin-grafica" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Gráficas</a>
                    <a href="${pageContext.request.contextPath}/servlet-logout" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">Salir</a>
                </div>
            </c:when>
        </c:choose>
    </div> <!-- Fin Bloque Superior -->

    <!-- ========================================== -->
    <!-- BLOQUE INFERIOR: NOTIFICACIONES            -->
    <!-- ========================================== -->
    <div class="mt-auto">
        <c:choose>
            <c:when test="${not empty sessionScope.docenteLogueado}">
                <div class="p-1 border-top border-secondary w-100">
                    <a href="${pageContext.request.contextPath}/servlet-notificaciones" class="btn text-white w-100 text-start d-flex align-items-center gap-2 border-0 bg-transparent" title="Documentos pendientes">
                        <i class="bi bi-bell"></i>
                        <span>Notificaciones</span>
                        <c:if test="${totalNotificaciones > 0}">
                            <span class="badge rounded-pill ms-auto" style="background-color: #D4AC0D;"><c:out value="${totalNotificaciones}" /></span>
                        </c:if>
                    </a>
                </div>
            </c:when>

            <c:when test="${not empty sessionScope.adminLogueado}">
                <div class="p-2 border-top border-secondary w-100">
                    <a href="${pageContext.request.contextPath}/servlet-crear-notificacion" class="btn text-white w-100 text-start d-flex align-items-center gap-2 border-0 bg-transparent" title="Crear notificaciones">
                        <i class="bi bi-bell"></i>
                        <span>Notificaciones</span>
                    </a>
                </div>
            </c:when>
        </c:choose>
    </div> <!-- Fin Bloque Inferior -->

</div>