<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estudiantes</title>
    <!-- Bootstrap CSS -->
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
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Estudiantes</h1>
        </div>

        <!-- Área de Datos -->
        <div id="datos" class="p-4 flex-grow-1">
            <div class="container" style="max-width: 800px;">
                <div class="card border-0 shadow-sm p-4 bg-white rounded-3 w-100" style="max-width: 850px;">

                    <!-- Barra de Búsqueda -->
                    <form action="${pageContext.request.contextPath}/servlet-admin-estudiantes" method="post">
                        <div id="busqueda" class="row g-2 mb-4">
                            <div class="col-9">
                                <div class="input-group">
                                    <span class="input-group-text bg-white border-end-0">
                                        <i class="bi bi-search text-muted"></i>
                                    </span>
                                    <input id="buscador"
                                           name="buscador"
                                           type="text"
                                           value="${terminoBuscado}"
                                           placeholder="Buscar por nombre, apellido o matrícula"
                                           class="form-control border-start-0 p-2">
                                </div>
                            </div>
                            <div class="col-3">
                                <button class="btn btn-success w-100 h-100 fw-medium" type="submit">Buscar</button>
                            </div>
                        </div>
                    </form>

                    <!-- Tabla de Estudiantes -->
                    <div id="Estudiantes" class="row">
                        <div class="col-12">
                            <c:choose>
                                <c:when test="${not empty listaEstudiantes}">
                                    <table class="table align-middle bg-white border-1">
                                        <thead>
                                        <tr class="border-bottom">
                                            <th class="fs-6 text-secondary">Matrícula</th>
                                            <th class="fs-6 text-secondary">Nombre</th>
                                            <th class="fs-6 text-secondary">Carrera</th>
                                            <th class="fs-6 text-secondary">Docente asignado</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="estudiante" items="${listaEstudiantes}">
                                            <tr class="border-bottom">
                                                <td class="py-3 fs-6"><c:out value="${estudiante.matricula}" /></td>
                                                <td class="py-3 fs-6"><c:out value="${estudiante.nombre} ${estudiante.apellido}" /></td>
                                                <td class="py-3 fs-6"><c:out value="${estudiante.carrera}" /></td>
                                                <td class="py-3 fs-6"><c:out value="${estudiante.docenteAsignado}" /></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <div class="text-center text-secondary fs-5 py-4">
                                        <c:out value="${mensajeVacio}" default="No hay estudiantes registrados." />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
