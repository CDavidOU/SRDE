<<<<<<< HEAD
<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/24/2026
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/24/2026
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

=======
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- O "http://java.sun.com/jsp/jstl/core" si usas una versión anterior a Jakarta --%>
>>>>>>> 67f14d35925100cfeb86833b4f77989b397c1287
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro Estudiante</title>
    <!-- Bootstrap CSS (versión estable) -->
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
            <h1 class="text-white m-0 py-2 fs-2 fw-normal" style="background: #002E60;">Registro Estudiante</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 800px; height: fit-content;">

                <!-- Alerta de Error (JSTL) -->
                <c:if test="${not empty mensajeError}">
                    <div class="alert alert-danger text-center py-2 mb-3" role="alert">
                        <c:out value="${mensajeError}" />
                    </div>
                </c:if>

                <form action="servlet-registro-estudiante" method="POST">

                    <!-- Fila 1: Nombre y Apellido -->
                    <div id="personal" class="row g-3 mb-3">
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="nombre">Nombre:</label>
                            <input class="form-control p-2" type="text" id="nombre" name="nombre" required minlength="2" placeholder="Mariam Carlos">
                        </div>
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="apellido">Apellido:</label>
                            <input class="form-control p-2" type="text" id="apellido" name="apellido" required minlength="2" placeholder="Ortega Valdez">
                        </div>
                    </div>

<<<<<<< HEAD
                    <!-- Fila 2: Matrícula y Correo (Rellena toda la fila) -->
=======
                    <!-- Fila 2: Matrícula y Correo -->
>>>>>>> 67f14d35925100cfeb86833b4f77989b397c1287
                    <div id="institucional-contacto" class="row g-3 mb-3">
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="matricula">Matrícula:</label>
                            <input class="form-control p-2" type="text" id="matricula" name="matricula" required maxlength="10" minlength="10" placeholder="20253DS043">
                        </div>
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="correo">Correo:</label>
                            <input class="form-control p-2" type="email" id="correo" name="correo" pattern="[a-zA-Z0-9.]+@utez\.edu\.mx$" required placeholder="20253DS043@utez.edu.mx">
                        </div>
                    </div>

<<<<<<< HEAD
                    <!-- Fila 3: Cuatrimestre y Carrera -->
                    <div id="academicos" class="row g-3 mb-4">
                        <div class="col-6">
=======
                    <!-- Fila 3: Cuatrimestre, Carrera y Grupo -->
                    <div id="academicos" class="row g-3 mb-3">
                        <div class="col-4">
>>>>>>> 67f14d35925100cfeb86833b4f77989b397c1287
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="cuatrimestre">Cuatrimestre:</label>
                            <select class="form-select p-2" id="cuatrimestre" name="cuatrimestre" required>
                                <option value="">Selecciona una opción</option>
                                <option value="6">6° Cuatrimestre</option>
                                <option value="11">11° Cuatrimestre</option>
                            </select>
                        </div>
<<<<<<< HEAD
                        <div class="col-6">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="carrera">Carrera:</label>
                            <input class="form-control p-2" type="text" id="carrera" name="carrera" required minlength="3" placeholder="DSM">
                        </div>
=======
                        <div class="col-4">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="carrera">Carrera:</label>
                            <input class="form-control p-2" type="text" id="carrera" name="carrera" required minlength="3" placeholder="DSM">
                        </div>
                        <div class="col-4">
                            <label class="fw-bold fs-5 mb-1 text-secondary" for="grupo">Grupo:</label>
                            <input class="form-control p-2" type="text" id="grupo" name="grupo" required>
                        </div>
                    </div>

                    <!--Fila 4 Asignar profesor-->
                    <div id="asignacion-tutor" class="row g-3 mb-4">
                        <div class="col-12">
                            <label class="fw-bold mb-1 fs-5 text-secondary" for="idDocenteAsignado">Profesor a Cargo:</label>
                            <select class="form-select p-2" name="idDocenteAsignado" id="idDocenteAsignado" required>
                                <option value="">-- Selecciona un Docente --</option>
                                <c:forEach var="docente" items="${listaDocente}">
                                    <option value="${docente.id}">${docente.nombre} ${docente.apellido}</option>
                                </c:forEach>
                            </select>
                        </div>
>>>>>>> 67f14d35925100cfeb86833b4f77989b397c1287
                    </div>

                    <!-- Botones de Acción -->
                    <div id="botones" class="row justify-content-between mt-4">
                        <div class="col-5">
<<<<<<< HEAD
                            <a class="btn btn-danger w-100 py-2 fs-5 text-white fw-medium rounded-3" href="${pageContext.request.contextPath}/servlet-lista-estudiantes">Cancelar</a>
                        </div>
                        <div class="col-5">
                            <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">Registrar</button>
=======
                            <a class="btn btn-danger w-100 py-2 fs-5 text-white fw-medium rounded-3" href="${pageContext.request.contextPath}/servlet-estudiantes-admin">Cancelar</a>
                        </div>
                        <div class="col-5">
                            <form method="get" action="servlet-estudiantes-admin">
                                <button class="btn w-100 py-2 fs-5 text-white fw-medium rounded-3" style="background-color: #429983;" type="submit">Registrar</button>
                            </form>
>>>>>>> 67f14d35925100cfeb86833b4f77989b397c1287
                        </div>
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>
<<<<<<< HEAD
=======

<!-- Bootstrap JS -->
>>>>>>> 67f14d35925100cfeb86833b4f77989b397c1287
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>