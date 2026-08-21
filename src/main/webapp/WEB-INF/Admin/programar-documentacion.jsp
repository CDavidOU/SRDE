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
    <div id="menu" class="flex-shrink-0" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>

    <!-- Contenido Principal -->
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

        <!-- Encabezado -->
        <div class="w-100  position-relative d-flex align-items-center justify-content-center py-3 text-white" style="background-color: #002E60" >
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Programar Calendario de Documentos</h1>
            <!-- Botón de calendarios -->
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 900px; height: fit-content;">

                <form method="POST" action="servlet-crear-calendario">


                    <c:if test="${not empty mensajeCorrecto}">
                        <div class="alert alert-success" role="alert">
                            <p>${mensajeCorrecto}</p>
                        </div>
                    </c:if>
                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger" role="alert">
                            <p>${mensajeError}</p>
                        </div>
                    </c:if>
                    <input type="hidden" name="idPeriodo" value="${periodoActivo.id_periodo}">

                    <!-- Campos Documentos -->
                    <div id="campos-docs" class="row g-3 mb-4">

                        <!-- 1. Fecha inicio -->
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="fechaInicio">Fecha Inicio:</label>
                            <input class="form-control p-2" type="date" id="fechaInicio" name="fechaInicio" value="2026-07-29">
                        </div>
                        <!-- 1. Fecha Límite -->
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="fechaLimite">Fecha límite:</label>
                            <input class="form-control p-2" type="date" id="fechaLimite" name="fechaLimite" value="2026-07-29">
                        </div>

                        <!-- 2. Documento -->
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="tipoDoc">Documento:</label>
                            <select class="form-select p-2" id="tipoDoc" name="tipoDoc">
                                <option value="" selected disabled>Selecciona documento...</option>
                                <c:forEach items="${listaTiposDocs}" var="listaDocs">
                                    <option value="${listaDocs.id_tipo}">${listaDocs.nombreDoc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 2. Comentario -->
                    <div class="col-md-12">
                        <label class="fw-bold mb-1 fs-6 text-secondary" for="txtComentario">Comentario:</label>
                        <textarea class="form-control" id="txtComentario" name="txtComentario" style="resize: none;" placeholder="Escriba un comentario"></textarea>
                    </div>

                    <!-- Fila 3: Botones Cancelar y Programar -->
                    <div id="botones-accion" class="row g-3 mb-4">
                        <div class="col-md-6">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #C85252;" type="button">Cancelar</button>
                        </div>
                        <div class="col-md-6">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">Programar</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

</body>
</html>