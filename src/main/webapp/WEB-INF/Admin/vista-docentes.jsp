<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Docentes</title>
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
<<<<<<< HEAD
        <!-- Contenido Principal -->
        <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

            <!-- Encabezado -->
            <div class="w-100 position-relative d-flex align-items-center justify-content-center py-3 text-white" style="background-color: #002E60">
                <h1 class="m-0 fs-2 fw-normal">Docentes</h1>

                <!-- Botón de registrar (Ruta corregida) -->
                <a href="${pageContext.request.contextPath}/servlet-registro-docente"
                   class="btn btn-success position-absolute end-0 me-4 d-flex align-items-center justify-content-center p-0 rounded"
                   style="width: 38px; height: 38px;"
                   title="Registrar Nuevo Docente">
                    <i class="bi bi-person-plus-fill fs-5"></i>
                </a>
            </div>

            <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

                <!-- Contenedor principal alineado con la maqueta -->
                <div class="w-100" style="max-width: 850px;">

                    <!-- Buscador con botón de Limpiar -->
                    <form action="${pageContext.request.contextPath}/servlet-lista-docentes" method="post" class="mb-4">
                        <div id="busqueda" class="row g-2">

                            <!-- Caja de texto (reducida a 8 columnas para dar espacio a los botones) -->
                            <div class="col-8">
                                <div class="input-group h-100">
                                <span class="input-group-text bg-white border-end-0">
                                    <i class="bi bi-search text-muted"></i>
                                </span>
                                    <input id="buscador"
                                           name="buscador"
                                           type="text"
                                           value="${terminoBuscado}"
                                           placeholder="Buscar por nombre, apellido o estado"
                                           class="form-control border-start-0 p-2">
                                </div>
                            </div>

                            <!-- Contenedor de botones (4 columnas) usando Flexbox para que se acomoden solos -->
                            <div class="col-4 d-flex gap-2">
                                <button class="btn btn-success flex-grow-1 fw-medium" type="submit">Buscar</button>

=======
            <!-- Encabezado -->
            <div class="w-100 position-relative d-flex align-items-center justify-content-center py-3 text-white" style="background-color: #002E60">
                <h1 class="m-0 fs-2 fw-normal">Docentes</h1>

                <!-- Botón de registrar (Ruta corregida) -->
                <a href="${pageContext.request.contextPath}/servlet-registro-docente"
                   class="btn position-absolute end-0 me-4 d-flex align-items-center justify-content-center p-0 rounded"
                   style="width: 38px; height: 38px;"
                   title="Registrar Nuevo Docente">
                    <i class="bi bi-person-plus-fill fs-5"></i>
                </a>
            </div>

            <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

                <!-- Contenedor principal alineado con la maqueta -->
                <div class="w-100" style="max-width: 850px;">

                    <!-- Buscador con botón de Limpiar -->
                    <form action="${pageContext.request.contextPath}/servlet-lista-docentes" method="post" class="mb-4">
                        <div id="busqueda" class="row g-2">

                            <!-- Caja de texto (reducida a 8 columnas para dar espacio a los botones) -->
                            <div class="col-8">
                                <div class="input-group h-100">
                                <span class="input-group-text bg-white border-end-0">
                                    <i class="bi bi-search text-muted"></i>
                                </span>
                                    <input id="buscador"
                                           name="buscador"
                                           type="text"
                                           value="${terminoBuscado}"
                                           placeholder="Buscar por nombre, apellido o estado"
                                           class="form-control border-start-0 p-2">
                                </div>
                            </div>

                            <!-- Contenedor de botones (4 columnas) usando Flexbox para que se acomoden solos -->
                            <div class="col-4 d-flex gap-2">
                                <button class="btn btn-success flex-grow-1 fw-medium" style="background-color: #429983;" type="submit">Buscar</button>

>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                                <!-- Este botón de Limpiar solo se mostrará si 'terminoBuscado' tiene texto -->
                                <c:if test="${not empty terminoBuscado}">
                                    <a href="${pageContext.request.contextPath}/servlet-lista-docentes"
                                       class="btn btn-outline-secondary flex-grow-1 fw-medium d-flex align-items-center justify-content-center"
                                       title="Borrar búsqueda y ver todos">
                                        <i class="bi bi-x-circle me-1"></i> Limpiar
                                    </a>
                                </c:if>
                            </div>

                        </div>
                    </form>

                    <!-- Alerta de éxito al registrar -->
                    <c:if test="${not empty sessionScope.mensajeOk}">
                        <div class="alert alert-success text-center py-2 mb-3 shadow-sm" role="alert">
                            <c:out value="${sessionScope.mensajeOk}" />
                        </div>
                        <c:remove var="mensajeOk" scope="session" />
                    </c:if>

                    <!-- Lista de Docentes -->
                    <div class="d-flex flex-column gap-2 mb-4">

                        <c:choose>
                            <c:when test="${not empty listaDocentes}">
                                <c:forEach var="docente" items="${listaDocentes}">
                                    <div class="card border border-secondary border-opacity-25 rounded-3 shadow-sm">
                                        <div class="card-body py-2 px-3 d-flex align-items-center justify-content-between">

                                            <!-- LADO IZQUIERDO: Icono, Nombre y Estado -->
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="rounded-circle bg-dark text-white d-flex align-items-center justify-content-center flex-shrink-0" style="width: 38px; height: 38px;">
                                                    <i class="bi bi-person-fill fs-5"></i>
                                                </div>
                                                <div class="d-flex flex-column">
                                                <span class="fw-semibold text-secondary fs-5 m-0 lh-1">
                                                    <c:out value="${docente.nombre} ${docente.apellido}" />
                                                </span>
                                                    <span class="text-secondary small mt-1">Estado: <c:out value="${docente.estado}" default="Activo"/></span>
                                                </div>
                                            </div>

                                            <!-- LADO DERECHO: Alumnos y Botón Detalles -->
                                            <div class="d-flex align-items-center gap-4">
                                                <span class="fw-bold text-secondary fs-5 m-0">Alumnos: <c:out value="${docente.numAlumnos}" default="0"/></span>

                                                <a href="${pageContext.request.contextPath}/servlet-datos-docente?id=${docente.id}"
                                                   class="btn px-3 py-1 me-2 text-white"
<<<<<<< HEAD
                                                    style="background-color: #002E60">
=======
                                                   style="background-color: #002E60">
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                                                    Detalles<i class="bi bi-journal-text ms-1"></i>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <!-- Estado vacío -->
                                <div class="text-center text-secondary fs-5 py-4">
                                    <c:out value="${mensajeVacio}" default="No hay docentes registrados." />
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>