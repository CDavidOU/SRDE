<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Calendario de Documentos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

    <!-- Menú Lateral -->
    <div id="menu" class="flex-shrink-0" style="width: 180px;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>

    <!-- Contenido Principal -->
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

        <!-- Encabezado -->
        <div class="w-100 position-relative d-flex align-items-center justify-content-center py-3 text-white" style="background-color: #002E60">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold">Editar Calendario de Documentos</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 900px; height: fit-content;">

                <!-- Formulario apunta al Servlet encargado de guardar los cambios -->
                <form method="POST" action="${pageContext.request.contextPath}/actualizarCalendarioServlet">

                    <!-- Mensajes de alerta -->
                    <c:if test="${not empty mensajeCorrecto}">
                        <div class="alert alert-success" role="alert">
                            <p class="m-0">${mensajeCorrecto}</p>
                        </div>
                    </c:if>
                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger" role="alert">
                            <p class="m-0">${mensajeError}</p>
                        </div>
                    </c:if>


                    <input type="hidden" name="idCalendario" value="${calendario.idCalendario}">
                    <input type="hidden" name="idTipo" value="${calendario.tipo_doc}">

                    <!-- Campos del Formulario -->
                    <div id="campos-docs" class="row g-3 mb-4">

                        <!-- 1. Fecha Inicio -->
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="fechaInicio">Fecha Inicio:</label>
                            <input class="form-control p-2" type="date" id="fechaInicio" name="fechaInicio" value="${calendario.fechaInicio}">
                        </div>

                        <!-- 2. Fecha Límite -->
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="fechaLimite">Fecha Límite:</label>
                            <input class="form-control p-2" type="date" id="fechaLimite" name="fechaLimite" value="${calendario.fechaLimite}">
                        </div>

                        <!-- 3. Documento (Mostrar solo el nombre en modo lectura) -->
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="tipoDoc">Documento:</label>
                            <input class="form-control p-2 bg-light" type="text" id="tipoDoc" value="${calendario.nombreDoc}" readonly>
                            <!-- Se envía el ID por hidden si tu DAO de actualización lo requiere -->
                            <input type="hidden" name="idTipoDoc" value="${calendario.tipo_doc}">
                        </div>

                        <!-- 4. Comentario -->
                        <div class="col-md-12">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="txtComentario">Comentario:</label>
                            <textarea class="form-control" id="txtComentario" name="txtComentario" style="resize: none;" rows="3" placeholder="Escriba un comentario">${calendario.comentario}</textarea>
                        </div>

                    </div>

                    <!-- Botones Cancelar y Guardar Cambios -->
                    <div id="botones-accion" class="row g-3 mb-2">
                        <div class="col-md-6">
                            <a href="${pageContext.request.contextPath}/actualizarCalendarioServlet" class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3 text-decoration-none text-center d-block" style="background-color: #C85252;">
                                Cancelar
                            </a>
                        </div>
                        <div class="col-md-6">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">
                                Actualizar
                            </button>
                        </div>
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>