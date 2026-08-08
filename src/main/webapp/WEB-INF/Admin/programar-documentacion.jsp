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
        <div class="w-100 text-center mb-4">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Programar Calendario de Documentos</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 900px; height: fit-content;">

                <form method="POST" action="servlet-crear-notificacion">

                    <!-- Campos Notificación -->
                    <div id="campos-notificacion" class="row g-3 mb-4">

                        <!-- FILA 1: 3 Campos en 4 columnas cada uno (4 + 4 + 4 = 12) -->

                        <!-- 1. Fecha Límite -->
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="fechaLimite">Fecha límite:</label>
                            <input class="form-control p-2" type="date" id="fechaLimite" name="fechaLimite" value="2026-07-29">
                        </div>
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-6 text-secondary" for="fechaInicio">Fecha Inicio:</label>
                            <input class="form-control p-2" type="date" id="fechaInicio" name="fechaInicio" value="2026-07-29">
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