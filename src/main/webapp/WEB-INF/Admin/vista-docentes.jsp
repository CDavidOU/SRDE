<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Docentes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<div class="d-flex">
    <!-- 1. Menú Lateral -->
    <div style="width: 250px; min-height: 100vh;">
        <jsp:include page="/WEB-INF/Plantillas/menu.jsp" />
    </div>

    <!-- 2. Área Principal de Contenido -->
    <div class="flex-grow-1">

        <!-- Cabecera / Header Superior -->
        <div class="w-100 text-center text-white py-3 fw-bold fs-3" style="background-color: #002B66;">
            Docentes
        </div>

        <!-- Contenido Central (Lista de tarjetas según Wireframe) -->
        <div class="container my-4 px-5" style="max-width: 900px;">

            <!-- Mensaje si no hay registros -->
            <c:if test="${not empty mensajeVacio}">
                <div class="alert alert-warning text-center">${mensajeVacio}</div>
            </c:if>

            <!-- Tarjetas de Docentes -->
            <c:if test="${not empty listaDocentesActivos}">
                <c:forEach var="doc" items="${listaDocentesActivos}">
                    <div class="card mb-3 border shadow-sm">
                        <div class="card-body d-flex align-items-center justify-content-between p-2">

                            <!-- Icono y Nombre -->
                            <div class="d-flex align-items-center ms-2">
                                <i class="bi bi-person-fill fs-2 me-3"></i>
                                <span class="fs-5 fw-semibold">${doc.nombre}</span>
                            </div>

                            <!-- Alumnos y Botón -->
                            <div class="d-flex align-items-center">
                                <span class="fs-5 me-3">Alumnos: ${doc.totalAlumnos}</span>
                                <button class="btn text-white px-3 py-2" style="background-color: #002B66;">
                                    <i class="bi bi-card-text"></i>
                                </button>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <!-- Botón Registrar Docente (Abajo a la derecha) -->
            <div class="d-flex justify-content-end align-items-center mt-4">
                <span class="me-2 fw-semibold fs-5">Registrar docente</span>
                <a href="#" class="btn btn-outline-dark px-3 py-1 fw-bold fs-4">+</a>
            </div>

        </div>
    </div>
</div>

</body>
</html>