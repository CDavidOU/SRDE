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

        <form action="servlet-registro-docente" method="POST">

          <!-- Fila 1: Nombre, Apellido -->
          <div id="nombre-completo" class="row g-3 mb-3">
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="nombre">Nombre:</label>
              <input class="form-control p-2" type="text" id="nombre" name="nombre" required minlength="2" placeholder="Ej: Nathaly" required>
            </div>
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="apellidos">Apellidos:</label>
              <input class="form-control p-2" type="text" id="apellidos" name="apellido" required minlength="2" placeholder="Ej: Escalona" required>
            </div>
          </div>

          <!-- Fila 2: Carrera y Área (2 Columnas) -->
          <div id="academico-docente" class="row g-3 mb-3">
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="periodo">Carrera:</label>
              <input type="text" name="carrera" id="carrera" class="form-control" placeholder="Ej: DSM" required>
            </div>
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="area">Área:</label>
              <input type="text" name="academia" id="academia" class="form-control" placeholder="Ej: DATID" required>
            </div>
          </div>

          <!-- Fila 3: Teléfono y Correo Electrónico (2 Columnas) -->
          <div id="contacto-docente" class="row g-3 mb-4">
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="telefono">Teléfono:</label>
              <input class="form-control p-2" type="tel" id="telefono" name="telefono" required pattern="[0-9]{10}" placeholder="Ej: 7773712397" required>
            </div>
            <div class="col-6">
              <label class="fw-bold mb-1 fs-5 text-secondary" for="correo">Correo Electrónico:</label>
              <input class="form-control p-2" type="email" id="correo" name="correo" pattern="[a-zA-Z0-9.]+@utez\.edu\.mx$" required placeholder="Ej: nathaly.escalona@utez.edu.mx">
            </div>
          </div>

          <!-- Botones de Acción -->
          <div id="botones" class="row justify-content-between mt-4">
            <div class="col-5">
              <a class="btn btn-danger w-100 py-2 fs-5 text-white fw-medium rounded-3" href="${pageContext.request.contextPath}/servlet-lista-docente">Cancelar</a>
            </div>
            <div class="col-5">
              <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">Registrar</button>
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