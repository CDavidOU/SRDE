<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/29/2026
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalles de Estudiante</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div id="contenido" class="d-flex min-vh-100">

        <!-- Menú Lateral -->
        <div id="menu" class="border-end" style="width: 180px; flex-shrink: 0;">
                <jsp:include page="../Plantillas/menu.jsp" />
        </div>

        <div id="cambiantes" class="flex-grow-1 d-flex flex-column">
                <!-- Encabezado corregido -->
                <div class="text-center w-100 mb-4 text-white m-0 py-3 px-4" style="background-color: #002E60;">
                        <div class="row align-items-center justify-content-between">
                                <!-- Botón Regresar -->
                                <div class="col-auto">
                                        <a href="javascript:history.back()" class="btn btn-outline-light d-flex align-items-center gap-2">
                                                <i class="bi bi-arrow-left fs-5"></i>
                                        </a>
                                </div>

                                <!-- Título Centrado -->
                                <div class="col text-center">
                                        <h1 class="m-0 fs-2 fw-semibold">Perfil del Estudiante</h1>
                                </div>
                        </div>
                </div>

                <!-- Aqui hacemos el contenedor de la informacion -->
                <div class="mx-auto" style="max-width: 950px;">

                        <!-- Aquie esta nombre, apellidos, matricula -->
                        <div id="personales" class="row g-3 mb-3">
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Nombre(s):</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.nombre}">
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Apellido(s):</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.apellido}">
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Matricula:</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.matricula}">
                                </div>
                        </div>

                        <!--Aqui ira Carrera,Cuatrimestre y grupo -->
                        <div class="row g-3 mb-3">
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Carrera:</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.carrera}">
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Cuatrimestre:</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.cuatrimestre}">
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Grupo:</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.grupo}">
                                </div>
                        </div>
                        <!--Aqui ira Correo y estado-->
                        <div class="row g-3 mb-3">
                                <div class="col-6">
                                        <label class="fw-bold fs-5 mb-1">Correo:</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.correo}">
                                </div>
                                <div class="col-6">
                                        <label class="fw-bold fs-5 mb-1">Estado:</label>
                                        <input class="form-control" type="text" disabled readonly value="${datosEstudiante.estado}">
                                </div>
                        </div>

<%--                        <!--Aqui se mostraran los archivos-->--%>
<%--                        <c:forEach>--%>
<%--                                <c:if test=""><!-- Aqui documento ya subido  -->--%>
<%--                                        <div class="container">--%>
<%--                                                <div class="row">--%>
<%--                                                        <div class="col-4">--%>
<%--                                                                <i class="bi bi-document fs-3 m-2"style="background-color: #429983"></i>--%>
<%--                                                                <p class="fw-bold text-white p-2 " style="background-color: #429983">Nombre del documento</p>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="col-8">--%>
<%--                                                                <p class="fw-bold p-2">Observaciones</p>--%>
<%--                                                                <input class="form-control" type="text" disabled readonly value="">--%>
<%--                                                                <p>Fecha de entrega : 10/04/21</p>--%>
<%--                                                        </div>--%>
<%--                                                </div>--%>
<%--                                                <div class="row">--%>
<%--                                                        <div class="col-4">--%>
<%--                                                                <a href="" class="btn btn-warning">Modificar</a>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="col-4">--%>
<%--                                                                <a href="" class="btn btn-danger">Eliminar</a>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="col-4">--%>
<%--                                                                <a href="" class="btn" style="background-color: #002E60">Abrir archivo</a>--%>
<%--                                                        </div>--%>
<%--                                                </div>--%>
<%--                                        </div>--%>
<%--                                </c:if>--%>
<%--                                <c:if test=""> <!-- Aqui documento no sbuido pero a tiempo-->--%>
<%--                                        <div class="container">--%>
<%--                                                <div class="row">--%>
<%--                                                        <div class="col-4">--%>
<%--                                                                <i class="bi bi-document fs-3 m-2 br" style="background-color: darkkhaki"></i>--%>
<%--                                                                <p class="fw-bold text-white p-2 " style="background-color: darkkhaki">Nombre del documento</p>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="col-8">--%>
<%--                                                                <p class="fw-bold p-2">Observaciones</p>--%>
<%--                                                                <input class="form-control" type="text" disabled readonly value="">--%>
<%--                                                                <p>Fecha de entrega: </p>--%>
<%--                                                        </div>--%>
<%--                                                </div>--%>
<%--                                                <div class="row">--%>
<%--                                                        <div class="col-4">--%>
<%--                                                                <a href="" class="btn" style="background-color: #429983">Subir</a>--%>
<%--                                                        </div>--%>
<%--                                                </div>--%>
<%--                                        </div>--%>
<%--                                </c:if>--%>
<%--                        </c:forEach>--%>
                </div>
        </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
