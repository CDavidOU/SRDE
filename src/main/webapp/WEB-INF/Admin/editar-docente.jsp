<%--
  Created by IntelliJ IDEA.
  User: car15
  Date: 29/07/2026
  Time: 07:01 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registro Docente</title>
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
      <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Registro Docente</h1>
    </div>

    <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

      <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 900px; height: fit-content;">

        <!-- Alerta de Error (JSTL) -->
        <c:if test="${not empty mensajeError}">
          <div class="alert alert-danger text-center py-2 mb-3" role="alert">
            <c:out value="${mensajeError}" />
          </div>
        </c:if>
        <!-- El action cambiará dinámicamente según si existe un ID (modificar) o no (registrar) -->
        <form action="${not empty datoDocente.id ? 'servlet-modificar-docente' : 'servlet-registro-docente'}" method="POST">

          <!-- Si estamos editando, inyectamos el ID oculto de forma automática -->
          <c:if test="${not empty datoDocente.id}">
            <input type="hidden" name="idDocente" value="${datoDocente.id}">
          </c:if>

          <!-- Fila 1: Nombre y Apellidos -->
          <div id="nombre-completo" class="row g-3 mb-3">
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="nombre">Nombre:</label>
              <!-- Agregamos value dinámico -->
              <input class="form-control p-2" type="text" id="nombre" name="nombre" minlength="2" placeholder="Ej: Nathaly" value="${datoDocente.nombre}" required>
            </div>
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="apellidos">Apellidos:</label>
              <!-- Aseguramos name="apellidoPaterno" para tu servlet de modificar -->
              <input class="form-control p-2" type="text" id="apellidos" name="apellidoPaterno" minlength="2" placeholder="Ej: Escalona" value="${datoDocente.apellido}" required>
            </div>
          </div>

          <!-- Fila 2: Carrera y Área -->
          <div id="academico-docente" class="row g-3 mb-3">
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="carrera">Carrera:</label>
              <input type="text" name="carrera" id="carrera" class="form-control p-2" placeholder="Ej: DSM" value="${datoDocente.carrera}" required>
            </div>
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="area">Área:</label>
              <!-- Aseguramos name="area" para tu servlet de modificar -->
              <input type="text" name="area" id="area" class="form-control p-2" placeholder="Ej: DATID" value="${datoDocente.academia}" required>
            </div>
          </div>

          <!-- Fila 3: Teléfono, Correo Electrónico y Estado -->
          <div id="contacto-docente" class="row g-3 mb-4">
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="telefono">Teléfono:</label>
              <input class="form-control p-2" type="text" id="telefono" name="telefono" placeholder="Ej: 7773712397" maxlength="10" minlength="10" value="${datoDocente.telefono}" required>
            </div>
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="correo">Correo Electrónico:</label>
              <input class="form-control p-2" type="email" id="correo" name="correo" pattern="[a-zA-Z0-9.]+@utez\.edu\.mx$" placeholder="Ej: nathaly.escalona@utez.edu.mx" value="${datoDocente.correo}" required>
            </div>
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary d-block">Estado:</label>
              <div class="form-check form-check-inline mt-2">
                <input class="form-check-input" type="radio" name="estado" id="estadoActivo" value="activo" ${datoDocente.estado == 'activo' ? 'checked' : ''} required>
                <label class="form-check-label fs-5" for="estadoActivo">Activo</label>
              </div>
              <div class="form-check form-check-inline mt-2">
                <input class="form-check-input" type="radio" name="estado" id="estadoInactivo" value="inactivo" ${datoDocente.estado == 'inactivo' ? 'checked' : ''} required>
                <label class="form-check-label fs-5" for="estadoInactivo">Inactivo</label>
              </div>
            </div>
          </div>

          <!-- Botones de Acción -->
          <div id="botones" class="row justify-content-between mt-4">
            <div class="col-5">
              <a class="btn btn-danger w-100 py-2 fs-5 text-white fw-medium rounded-3"
                 href="${pageContext.request.contextPath}/servlet-lista-docente?idDocente=${datoDocente.id}">
                Cancelar
              </a>
            </div>
            <div class="col-5">
              <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">
                ${not empty datoDocente.id ? 'Guardar Cambios' : 'Registrar'}
              </button>
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