<%--
  Created by IntelliJ IDEA.
  User: Carlos
  Date: 25/07/2026
  Time: 08:51 p.m.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Contenedor principal del sidebar con fondo azul marino y alto completo (vh-100) -->
<div class="d-flex flex-column justify-content-between p-0 text-white min-vh-100" style="background-color: #002E60;">

    <div>
        <div class="bg-white p-3 text-center">
            <img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png" class="img-fluid" alt="Logo UTEZ" style="height: 110px; width: 100%;">
        </div>

        <!-- DOCENTE-->
        <c:if test="${not empty sessionScope.docenteLogueado}">
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
            <a href="${pageContext.request.contextPath}/servlet-mostrar-notificaciones" class="btn text-white w-100 text-start d-flex align-items-center gap-2 border-0 bg-transparent">
                <i class="bi bi-bell"></i>
                <span>Notificaciones</span>
            </a>
        </div>
        </c:if>


        <!-- Admin-->
        <c:if test="${not empty sessionScope.adminLogueado}">
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
                <!-- Opción Notificaciones fijada abajo -->
                <div class="p-2 border-top border-secondary">
                    <a href="${pageContext.request.contextPath}/servlet-crear-notificacion" class="btn text-white w-100 text-start d-flex align-items-center gap-2 border-0 bg-transparent">
                        <i class="bi bi-bell"></i>
                        <span>Notificaciones</span>
                    </a>
                </div>


            </div>
        </c:if>
    </div>

</div>