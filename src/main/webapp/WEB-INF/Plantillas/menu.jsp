<%--
  Created by IntelliJ IDEA.
  User: Carlos
  Date: 25/07/2026
  Time: 08:51 p.m.
--%>
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

<!-- Contenedor principal del sidebar con fondo azul marino y alto completo (vh-100) -->
<div class="d-flex flex-column justify-content-between p-0 text-white min-vh-100" style="background-color: #002E60;">

    <div>
        <div class="bg-white p-3 text-center">
            <img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png" class="img-fluid" alt="Logo UTEZ" style="height: 110px; width: 100%;">
        </div>

        <%-- DOCENTE / Admin: bloques mutuamente excluyentes según el rol en sesión --%>
        <c:choose>
            <c:when test="${not empty sessionScope.docenteLogueado}">
            <!-- Menú de opciones sin bordes externos -->
            <div class="list-group list-group-flush rounded-0 mt-2">

                <!-- Opción Activa: Perfil / Docente (Fondo Verde) -->
                <a href="${pageContext.request.contextPath}/servlet-inicio"
                   class="list-group-item list-group-item-action text-white d-flex align-items-center border-0 px-3 py-2"
                   style="background-color: #429983;">

                    <img src="${pageContext.request.contextPath}/imagenes/user.png" class="rounded-circle me-3" alt="Icono usuario" style="width: 35px; height: 35px; background: white; padding: 2px;">

                    <div class="d-flex flex-column text-start w-100">
                            <span class="fw-bold" style="font-size: 0.95rem;">Docente</span>
                            <input type="text" value="${sessionScope.docenteLogueado.nombre}" class="form-control form-control-sm p-0 text-white bg-transparent border-0 fw-light" disabled style="font-size: 0.9rem;">
                    </div>
                </a>

                <!-- Resto de enlaces con texto blanco y fondo transparente -->
                <a href="${pageContext.request.contextPath}/servlet-periodos" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Periodos
                </a>
                <a href="${pageContext.request.contextPath}/servlet-lista-estudiantes" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Estudiantes
                </a>
                <a href="${pageContext.request.contextPath}/servlet-logout" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Salir
                </a>

            </div>
            </c:when>
            <c:when test="${not empty sessionScope.adminLogueado}">
                <div class="list-group list-group-flush rounded-0 mt-2">

                    <!-- Opción Activa: Perfil / Docente (Fondo Verde) -->
                    <a href="${pageContext.request.contextPath}/servlet-inicio"
                       class="list-group-item list-group-item-action text-white d-flex align-items-center border-0 px-3 py-2"
                       style="background-color: #429983;">

                        <img src="${pageContext.request.contextPath}/imagenes/user.png" class="rounded-circle me-3" alt="Icono usuario" style="width: 35px; height: 35px; background: white; padding: 2px;">

                        <div class="d-flex flex-column text-start w-100">
                            <span class="fw-bold" style="font-size: 0.95rem;">Admin</span>
                            <input type="text" value="${sessionScope.adminLogueado.nombre}" class="form-control form-control-sm p-0 text-white bg-transparent border-0 fw-light" disabled style="font-size: 0.9rem;">
                        </div>
                    </a>

                    <!-- Resto de enlaces con texto blanco y fondo transparente -->
                    <a href="${pageContext.request.contextPath}/servlet-admin-periodos" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                        Periodos
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet-admin-estudiantes" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                        Estudiantes
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet-lista-docentes" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                        Docente
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet-admin-grafica" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                        Grafica
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet-logout" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                        Salir
                    </a>

                </div>
            </c:when>
        </c:choose>
    </div>

    <!-- Opción Notificaciones fijada abajo: documentos pendientes de revisión -->
    <div class="p-2 border-top border-secondary">
        <a href="${pageContext.request.contextPath}/servlet-notificaciones" class="btn text-white w-100 text-start d-flex align-items-center gap-2 border-0 bg-transparent" title="Documentos pendientes">
            <i class="bi bi-bell"></i>
            <span>Notificaciones</span>
            <c:if test="${totalNotificaciones > 0}">
                <span class="badge rounded-pill ms-auto" style="background-color: #D4AC0D;"><c:out value="${totalNotificaciones}" /></span>
            </c:if>
        </a>
    </div>

</div>