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

        <!-- Menú Lateral Estático -->
        <div id="menu" class="border-end" style="width: 180px; flex-shrink: 0; position: sticky; top: 0; height: 100vh; overflow-y: auto;">
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
                        <!-- Título separador para la sección de documentos -->
                        <div class="w-100 mt-5 mb-4 border-bottom pb-2">
                                <h3 class="fw-bold" style="color: #002E60;">Documentos del Estudiante</h3>
                        </div>

                        <!-- Iterador de Documentos -->
                        <c:forEach var="doc" items="${listaDocumentos}">

                                <!-- ==========================================
                                ESTADO 1: DOCUMENTO YA SUBIDO (VERDE)
                                ========================================== -->
                                <c:if test="${doc.estado == 'Completado'}">
                                        <div class="card shadow-sm mb-4 border-0" style="background-color: #f8f9fa;">
                                                <div class="card-body row g-0">
                                                        <!-- Izquierda: Icono y Nombre -->
                                                        <div class="col-3 d-flex flex-column align-items-center justify-content-center pe-3 border-end">
                                                                <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: #429983;">
                                                                        <i class="bi bi-file-earmark-text-fill" style="font-size: 4rem;"></i>
                                                                </div>
                                                                <div class="w-100 text-center py-2 rounded text-white fw-bold shadow-sm" style="background-color: #429983;">
                                                                                ${doc.nombre_archivo}
                                                                </div>
                                                        </div>

                                                        <!-- Derecha: Observaciones y Botones -->
                                                        <div class="col-9 ps-4 d-flex flex-column justify-content-between">

                                                                <!-- FORMULARIO PARA MODIFICAR (Solo envuelve el textarea) -->
                                                                <form action="<%=request.getContextPath()%>/servlet-modificar-observacion" method="POST" id="formModificar_${doc.id_tipo_doc}">
                                                                        <!-- CORRECCIÓN: Usar idAsignacion en lugar de matricula -->
                                                                        <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                        <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">

                                                                        <div>
                                                                                <label class="fw-bold fs-5 mb-1">Observaciones</label>
                                                                                <!-- CORRECCIÓN: Quitamos disabled/readonly y agregamos name="observaciones" -->
                                                                                <textarea class="form-control mb-2 bg-light" name="observaciones" rows="2">${doc.observaciones}</textarea>
                                                                                <span class="text-muted small fw-bold">Fecha de entrega: 10/04/26</span>
                                                                        </div>
                                                                </form>

                                                                <div class="row g-2 mt-3">
                                                                        <div class="col-4">
                                                                                <!-- CORRECCIÓN: Botón enlazado al form de arriba mediante el ID -->
                                                                                <button type="submit" form="formModificar_${doc.id_tipo_doc}" class="btn w-100 fw-bold text-white shadow-sm" style="background-color: #D4AC0D;">
                                                                                        Modificar
                                                                                </button>
                                                                        </div>

                                                                        <div class="col-4">
                                                                                <!-- FORMULARIO PARA ELIMINAR -->
                                                                                <form action="<%=request.getContextPath()%>/servlet-eliminar-documento" method="POST">
                                                                                        <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                                        <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                                        <button type="submit" class="btn btn-danger w-100 fw-bold shadow-sm">
                                                                                                Eliminar
                                                                                        </button>
                                                                                </form>
                                                                        </div>

                                                                        <div class="col-4">
                                                                                <a href="servlet-ver-documento?idArchivo=${doc.id_archivo}" target="_blank" class="btn w-100 fw-bold text-white shadow-sm" style="background-color: #002E60;">
                                                                                        Abrir archivo
                                                                                </a>
                                                                        </div>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                </c:if>

                                <!-- ==========================================
                                ESTADO 2: DOCUMENTO NO SUBIDO (AMARILLO)
                                ========================================== -->
                                <c:if test="${doc.estado == 'Pendiente'}">
                                        <div class="card shadow-sm mb-4 border-0" style="background-color: #f8f9fa;">
                                                <div class="card-body row g-0">
                                                        <!-- Izquierda: Icono y Nombre -->
                                                        <div class="col-3 d-flex flex-column align-items-center justify-content-center pe-3 border-end">
                                                                <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: #D4AC0D;">
                                                                        <i class="bi bi-file-earmark-text-fill" style="font-size: 4rem;"></i>
                                                                </div>
                                                                <div class="w-100 text-center py-2 rounded text-white fw-bold shadow-sm" style="background-color: #D4AC0D;">
                                                                                ${doc.nombre_archivo}
                                                                </div>
                                                        </div>

                                                        <!-- Derecha: Observaciones y Formulario -->
                                                        <div class="col-9 ps-4 d-flex flex-column justify-content-between">
                                                                <form action="${pageContext.request.contextPath}/servlet-subir-documentos" method="POST" enctype="multipart/form-data" class="d-flex flex-column h-100">

                                                                        <!-- CORRECCIÓN: Borré el input de "idUsuarioModificador" que daba error de EL. El Servlet lo leerá de la sesión. -->
                                                                        <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                                        <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                        <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                        <input type="hidden" name="estado" value="Completado">

                                                                        <div>
                                                                                <label class="fw-bold fs-5 mb-1">Observaciones</label>
                                                                                <textarea class="form-control mb-2" name="observaciones" rows="2" placeholder="Agrega un comentario..."></textarea>
                                                                                <span class="text-muted small fw-bold">Fecha de entrega: Pendiente</span>
                                                                        </div>

                                                                        <div class="row mt-3">
                                                                                <div class="col-12">
                                                                                        <div class="input-group shadow-sm">
                                                                                                <input type="file" class="form-control" name="archivoPDF" accept=".pdf" required>
                                                                                                <button type="submit" class="btn text-white fw-bold px-5" style="background-color: #429983;">
                                                                                                        Subir
                                                                                                </button>
                                                                                        </div>
                                                                                </div>
                                                                        </div>
                                                                </form>
                                                        </div>
                                                </div>
                                        </div>
                                </c:if>

                        </c:forEach>
                </div>
        </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
