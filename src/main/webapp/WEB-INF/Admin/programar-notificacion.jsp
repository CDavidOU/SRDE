<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Programar Notificación</title>
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
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Programar notificación</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 900px; height: fit-content;">

                <form action="#" method="POST">

                    <!-- Fila 1: Fecha límite, Documento y Comentario (3 Columnas) -->
                    <div id="campos-notificacion" class="row g-3 mb-4">
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="fechaLimite">Fecha límite:</label>
                            <input class="form-control p-2" type="date" id="fechaLimite" name="fechaLimite" value="2026-07-29">
                        </div>
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="documento">Documento:</label>
                            <input class="form-control p-2" type="text" id="documento" name="documento" placeholder="El documento deberá ser PDF">
                        </div>
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="comentario">Comentario:</label>
                            <input class="form-control p-2" type="text" id="comentario" name="comentario" placeholder="El documento deberá ser PDF">
                        </div>
                        <div class="col-md-4">
                            <label>Selecciona al Docente:</label>
                            <select name="idDocenteSelect">
                                <c:forEach items="${docentesDisponibles}" var="docente">
                                    <option value="${docente.id}">${docente.nombre} ${docente.apellido}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Fila 2: Botones Cancelar y Programar -->
                    <div id="botones-accion" class="row g-3 mb-4">
                        <div class="col-md-6">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #C85252;" type="button">Cancelar</button>
                        </div>
                        <div class="col-md-6">
                            <form method="POST" action="servlet-crear-notificacion">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">Programar</button>
                            </form>
                        </div>
                    </div>

                    <!-- Fila 3: Sección Programadas recientes -->
                    <div id="seccion-recientes" class="border border-secondary border-opacity-25 rounded-3 p-3 d-flex align-items-center gap-3 bg-white">
                        <i class="bi bi-bell-fill fs-3 text-dark"></i>
                        <span class="fw-normal fs-4 text-dark">Programadas recientes</span>
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>