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

                <form action="#" method="POST">

                    <!-- Fila 1: Nombres, Apellido Paterno, Apellido Materno -->
                    <div id="nombre-completo" class="row g-3 mb-3">
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="nombre">Nombre(s):</label>
                            <input class="form-control p-2" type="text" id="nombre" name="nombre" value="Nathaly" readonly>
                        </div>
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="apellidoPaterno">Apellido Paterno:</label>
                            <input class="form-control p-2" type="text" id="apellidoPaterno" name="apellidoPaterno" value="Escalona" readonly>
                        </div>
                        <div class="col-md-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="apellidoMaterno">Apellido Materno:</label>
                            <input class="form-control p-2" type="text" id="apellidoMaterno" name="apellidoMaterno" value="Ruiz" readonly>
                        </div>
                    </div>

                    <!-- Fila 2: Correo electrónico y Teléfono -->
                    <div id="contacto-docente" class="row g-3 mb-3">
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="correo">Correo electrónico:</label>
                            <input class="form-control p-2" type="email" id="correo" name="correo" value="nathalyescalona@utez.edu.mx" readonly>
                        </div>
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="telefono">Teléfono:</label>
                            <input class="form-control p-2" type="tel" id="telefono" name="telefono" value="7773712397" readonly>
                        </div>
                    </div>

                    <!-- Fila 3: Área  -->
                    <div id="academico-area" class="row g-3 mb-4">
                        <div class="col-md-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="area">Área:</label>
                            <input class="form-control p-2" type="text" id="area" name="area" value="DATIC" readonly>
                        </div>
                    </div>

                    <!-- Botón "Editar" -->
                    <div id="botones" class="d-flex justify-content-end mt-4">
                        <div style="width: 200px;">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="button">Editar</button>
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