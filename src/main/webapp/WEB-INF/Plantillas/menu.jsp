<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Carga de Bootstrap Icons para la campana -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<!-- Contenedor principal del sidebar fijado a la izquierda (sticky) -->
<div class="d-flex flex-column justify-content-between p-0 text-white"
     style="background-color: #002E60; position: sticky; top: 0; height: 100vh; overflow-y: auto; z-index: 1000;">

    <!-- Parte Superior: Logo y Opciones del Menú -->
    <div>
        <!-- Logo UTEZ (Sin márgenes inferiores para eliminar la línea blanca) -->
        <div class="bg-white p-3 text-center mb-0">
            <img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png" class="img-fluid" alt="Logo UTEZ" style="height: 110px; width: 100%;">
        </div>

        <!-- DOCENTE -->
        <c:if test="${not empty sessionScope.docenteLogueado}">
            <div class="list-group list-group-flush rounded-0 m-0 p-0">

                <!-- Perfil Docente (Pega directo al logo sin separación) -->
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
        </c:if>

        <!-- ADMIN -->
        <c:if test="${not empty sessionScope.adminLogueado}">
            <div class="list-group list-group-flush rounded-0 m-0 p-0">

                <!-- Perfil Admin (Pega directo al logo sin separación) -->
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
                <a href="${pageContext.request.contextPath}/servlet-periodo-admin" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Periodos
                </a>
                <a href="${pageContext.request.contextPath}/servlet-estudiantes-admin" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Estudiantes
                </a>
                <a href="${pageContext.request.contextPath}/servlet-lista-docente" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Docente
                </a>
                <a href="#" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Grafica
                </a>
                <a href="${pageContext.request.contextPath}/servlet-logout" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2">
                    Salir
                </a>
            </div>
        </c:if>
    </div>

    <!-- Parte Inferior: Notificaciones Fijadas Abajo con Línea Superior -->
    <div>
        <c:if test="${not empty sessionScope.docenteLogueado}">
            <div class="p-2 border-top border-white border-opacity-25">
                <a href="${pageContext.request.contextPath}/servlet-mostrar-notificaciones" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2 d-flex align-items-center gap-2">
                    <i class="bi bi-bell-fill"></i>
                    <span>Notificaciones</span>
                </a>
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.adminLogueado}">
            <div class="p-2 border-top border-white border-opacity-25">
                <a href="${pageContext.request.contextPath}/servlet-crear-notificacion" class="list-group-item list-group-item-action text-white bg-transparent border-0 px-3 py-2 d-flex align-items-center gap-2">
                    <i class="bi bi-bell-fill"></i>
                    <span>Notificaciones</span>
                </a>
            </div>
        </c:if>
    </div>

</div>