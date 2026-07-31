<%--
  Created by IntelliJ IDEA.
  User: car15
  Date: 29/07/2026
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Título dinámico -->
    <title><c:out value="${not empty docente ? 'Editar Docente' : 'Registro Docente'}" /></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

    <!-- Menú Lateral -->
    <div id="menu" class="bg-white border-end" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="WEB-INF/Plantillas/menu.jsp" />
    </div>

    <!-- Contenido Principal -->
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

        <!-- Encabezado Dinámico -->
        <div class="w-100 text-center mb-4">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">
                <c:out value="${not empty docente ? 'Editar Docente' : 'Registro Docente'}" />
            </h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 900px; height: fit-content;">

                <!-- Alerta de Error (JSTL) -->
                <c:if test="${not empty mensajeError}">
                    <div class="alert alert-danger text-center py-2 mb-3" role="alert">
                        <c:out value="${mensajeError}" />
                    </div>
                </c:if>

                <!-- El action del form cambia según si registras o actualizas -->
                <form action="${not empty docente ? 'servlet-actualizar-docente' : 'servlet-registro-docente'}" method="POST">

                    <!-- Campo oculto con el ID para la edición -->
                    <c:if test="${not empty docente}">
                        <input type="hidden" name="id" value="${docente.id}">
                    </c:if>

                    <!-- Fila 1: Nombre, Apellido Paterno, Apellido Materno -->
                    <div id="nombre-completo" class="row g-3 mb-3">
                        <div class="col-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="nombre">Nombre:</label>
                            <input class="form-control p-2" type="text" id="nombre" name="nombre" required minlength="2" placeholder="Ej: Nathaly" value="${docente.nombre}">
                        </div>
                        <div class="col-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="apellidoPaterno">Apellido Paterno:</label>
                            <input class="form-control p-2" type="text" id="apellidoPaterno" name="apellidoPaterno" required minlength="2" placeholder="Ej: Escalona" value="${docente.apellidoPaterno}">
                        </div>
                        <div class="col-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="apellidoMaterno">Apellido Materno:</label>
                            <input class="form-control p-2" type="text" id="apellidoMaterno" name="apellidoMaterno" required minlength="2" placeholder="Ej: Ruiz" value="${docente.apellidoMaterno}">
                        </div>
                    </div>

                    <!-- Fila 2: Periodo y Área -->
                    <div id="academico-docente" class="row g-3 mb-3">
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="periodo">Periodo:</label>
                            <select class="form-select p-2" id="periodo" name="periodo" required>
                                <option value="" disabled ${empty docente ? 'selected' : ''}>Seleccione Periodo</option>
                                <option value="Enero - Abril 2026" ${docente.periodo == 'Enero - Abril 2026' ? 'selected' : ''}>Enero - Abril 2026</option>
                                <option value="Mayo - Agosto 2026" ${docente.periodo == 'Mayo - Agosto 2026' ? 'selected' : ''}>Mayo - Agosto 2026</option>
                                <option value="Septiembre - Diciembre 2026" ${docente.periodo == 'Septiembre - Diciembre 2026' ? 'selected' : ''}>Septiembre - Diciembre 2026</option>
                            </select>
                        </div>
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="area">Área:</label>
                            <select class="form-select p-2" id="area" name="area" required>
                                <option value="" disabled ${empty docente ? 'selected' : ''}>Seleccione Academia</option>
                                <option value="DATIC" ${docente.area == 'DATIC' ? 'selected' : ''}>DATIC</option>
                                <option value="DAMI" ${docente.area == 'DAMI' ? 'selected' : ''}>DAMI</option>
                                <option value="DCEA" ${docente.area == 'DCEA' ? 'selected' : ''}>DCEA</option>
                            </select>
                        </div>
                    </div>

                    <!-- Fila 3: Teléfono y Correo Electrónico -->
                    <div id="contacto-docente" class="row g-3 mb-4">
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="telefono">Teléfono:</label>
                            <input class="form-control p-2" type="tel" id="telefono" name="telefono" required pattern="[0-9]{10}" placeholder="Ej: 7773712397" value="${docente.telefono}">
                        </div>
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="correo">Correo Electrónico:</label>
                            <input class="form-control p-2" type="email" id="correo" name="correo" pattern="[a-zA-Z0-9.]+@utez\.edu\.mx$" required placeholder="Ej: nathaly.escalona@utez.edu.mx" value="${docente.correo}">
                        </div>
                    </div>

                    <!-- Botones de Acción -->
                    <div id="botones" class="row justify-content-between mt-4">
                        <div class="col-5">
                            <a class="btn btn-danger w-100 py-2 fs-5 text-white fw-medium rounded-3" href="${pageContext.request.contextPath}/servlet-lista-docentes">Cancelar</a>
                        </div>
                        <div class="col-5">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">
                                <c:out value="${not empty docente ? 'Actualizar' : 'Registrar'}" />
                            </button>
                        </div>
                    </div>

                    <!-- Sección Restablecer Contraseña (SOLO VISIBLE EN MODO EDICIÓN) -->
                    <c:if test="${not empty docente}">
                        <div id="seccion-restablecer" class="d-flex align-items-center justify-content-between mt-4 pt-3 border-top">
                            <span class="fw-bold fs-3 text-dark">Restablecer Contraseña</span>
                            <a href="${pageContext.request.contextPath}/servlet-restablecer-pass?id=${docente.id}" class="btn text-white px-4 py-2 fs-5 rounded-3 d-flex align-items-center justify-content-center" style="background-color: #429983;" title="Restablecer">
                                <i class="bi bi-arrow-counterclockwise fs-4"></i>
                            </a>
                        </div>
                    </c:if>

                </form>
            </div>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>