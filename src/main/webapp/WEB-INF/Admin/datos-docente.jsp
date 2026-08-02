<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/24/2026
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Datos Docente</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Datos Docente</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 900px; height: fit-content;">

                <form action="servlet-modificar-docente" method="POST">
                    <input type="hidden" name="idDocente" value="${datoDocente.id}">

                    <!-- Fila 1: Nombres, Apellido -->
                    <div id="nombre-completo" class="row g-3 mb-3">
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="nombre">Nombre(s):</label>
                            <input class="form-control p-2" type="text" id="nombre" name="nombre" value="${datoDocente.nombre}" readonly disabled>
                        </div>
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="apellidoPaterno">Apellidos:</label>
                            <input class="form-control p-2" type="text" id="apellidoPaterno" name="apellidoPaterno" value="${datoDocente.apellido}" readonly disabled>
                        </div>
                    </div>

                    <!-- Fila 2: Área y carrera-->
                    <div id="academico-area" class="row g-3 mb-4">
                        <div class="col-md-6">
                                <label class="fw-bold mb-1 fs-5 text-secondary" for="area">Carrera:</label>
                                <input class="form-control p-2" type="text" id="carrera" name="carrera" value="${datoDocente.carrera}" disabled>
                            </div>
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="area">Área:</label>
                            <input class="form-control p-2" type="text" id="area" name="area" value="${datoDocente.academia}" disabled>
                        </div>
                    </div>
                    <!-- Fila 3: Correo electrónico y Teléfono -->
                    <div id="contacto-docente" class="row g-3 mb-3">
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="correo">Correo electrónico:</label>
                            <input class="form-control p-2" type="email" id="correo" name="correo" value="${datoDocente.correo}" disabled>
                        </div>
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="telefono">Teléfono:</label>
                            <input class="form-control p-2" type="tel" id="telefono" name="telefono" value="${datoDocente.telefono}" disabled>
                        </div>
                    </div>
                    <!-- Fila 4: Estado-->
                        <div id="estado-columna" class="row g-3 mb-4">
                            <div id="estado" class="col-md-6">
                                <label class="fw-bold mb-1 fs-5 text-secondary d-block">Estado:</label>
                                <div class="form-check form-check-inline mt-2">
                                    <input class="form-check-input" type="radio" name="estado" id="estadoActivo" value="activo" ${datoDocente.estado.toLowerCase() == 'activo' ? 'checked' : ''} disabled>
                                    <label class="form-check-label fs-5" for="estadoActivo">Activo</label>
                                </div>
                                <div class="form-check form-check-inline mt-2">
                                    <input class="form-check-input" type="radio" name="estado" id="estadoInactivo" value="inactivo" ${datoDocente.estado.toLowerCase() == 'inactivo' ? 'checked' : ''} disabled>
                                    <label class="form-check-label fs-5" for="estadoInactivo">Inactivo</label>
                                </div>
                            </div>
                    </div>

                    <!-- Botón "Editar" -->
                    <div id="botones" class="d-flex justify-content-end mt-4">
                        <div style="width: 200px;">
                            <a href="${pageContext.request.contextPath}/servlet-modificar-docente?idDocente=${datoDocente.id}"
                               class="btn btn-primary w-100 py-2 fw-medium rounded-3"
                               style="background-color: #002E60; border: none; text-align: center; display: block; color: white; text-decoration: none;">
                                Editar
                            </a>
                        </div>
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