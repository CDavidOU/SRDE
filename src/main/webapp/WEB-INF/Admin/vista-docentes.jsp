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

        <!-- Encabezado -->
        <div class="w-100 text-center mb-4">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Docentes</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <!-- Contenedor principal alineado con la maqueta -->
            <div class="w-100" style="max-width: 850px;">

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
                                            
                                            <!-- Botón Detalles combinando el diseño de Carlos con el enlace de union -->
                                            <a href="${pageContext.request.contextPath}/servlet-datos-docente?matricula=${docente.matricula}"
                                               class="btn btn-outline-dark btn-sm px-3 border border-dark d-flex align-items-center gap-2">
                                                <i class="bi bi-file-earmark-text-fill"></i> Detalles
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

                <!-- Botón 'Registrar docente' -->
                <div class="d-flex justify-content-end align-items-center gap-2 mt-4">
                    <span class="fw-bold text-secondary fs-5">Registrar docente</span>
                    <a href="${pageContext.request.contextPath}/servlet-registro-docente" class="btn border border-secondary border-opacity-50 bg-white fs-4 fw-bold px-3 py-0 shadow-sm text-dark">
                        +
                    </a>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>