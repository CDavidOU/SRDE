<%--
  Created by IntelliJ IDEA.
  User: Carlos
  Date: 25/07/2026
  Time: 10:58 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">
<head>
    <title>Perfil Docente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body class="bg-light">

<div id="contenido" class="d-flex">

    <div id="menu" class="bg-white border-end min-vh-100" style="width: 180px;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>

    <div id="cambiantes" class="flex-grow-1 d-flex flex-column">
        <div class="text-center w-150 mb-4">
            <h1 style="background: #002E60; color: white; margin: 0; padding: 10px 0;">Perfil</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1">
            <div class="mx-auto" style="max-width: 950px;">
                <div id="personal" class="row">
                    <div class="col-6">
                        <label class="fw-bold mb-1 fs-5">Nombre(s)</label>
                        <input class="form-control" type="text" value="${sessionScope.docenteLogueado.nombre}" disabled readonly>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold mb-1 fs-5">Apellido(s):</label>
                        <input class="form-control" type="text" value="${sessionScope.docenteLogueado.apellido}" disabled readonly>
                    </div>
                </div>

                <div id="contacto" class="row mt-4">
                    <div class="col-6">
                        <label class="fw-bold mb-1 fs-5">Correo electrónico:</label>
                        <input class="form-control" type="text" value="${sessionScope.docenteLogueado.correo}" disabled readonly>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold mb-1 fs-5">Teléfono:</label>
                        <input class="form-control" type="text" value="${sessionScope.docenteLogueado.telefono}" disabled readonly>
                    </div>
                </div>

                <div id="universidad" class="row mt-4">
                    <div class="col-6">
                        <label class="fw-bold mb-1 fs-5">Carrera:</label>
                        <input class="form-control" type="text" value="${sessionScope.docenteLogueado.carrera}" disabled readonly>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold mb-1 fs-5">Academia:</label>
                        <input class="form-control" type="text" value="${sessionScope.docenteLogueado.academia}" disabled readonly>
                    </div>
                </div>

                <div id="CambioContraseña" class="row mt-5 p-2" style="border-top: 1px solid #c2c2c2;">
                    <h3 class="col-9">Cambiar contraseña</h3>
                    <a class="btn text-white col-3" style="background-color: #429983;" href="#" role="button">Cambiar</a>
                </div>

            </div>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
