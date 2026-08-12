<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/29/2026
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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

                                                        <!-- FORMULARIO ÚNICO: Le agregamos enctype por si reemplazan el PDF -->
                                                        <form action="<%=request.getContextPath()%>/servlet-modificar-observacion" method="POST" id="formModificar_${doc.id_tipo_doc}" enctype="multipart/form-data" class="col-12 d-flex m-0">

                                                                <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                                <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">

                                                                <!-- LADO IZQUIERDO: Logo o Input de Archivo -->
                                                                <div class="col-3 d-flex flex-column align-items-center justify-content-center pe-3 border-end">

                                                                        <!-- VISTA NORMAL (Logo Verde) -->
                                                                        <div id="vista_icono_${doc.id_tipo_doc}" class="w-100">
                                                                                <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: #429983;">
                                                                                        <i class="bi bi-file-earmark-text-fill" style="font-size: 4rem;"></i>
                                                                                </div>
                                                                                <div class="w-100 text-center py-2 rounded text-white fw-bold shadow-sm" style="background-color: #429983;">
                                                                                                ${doc.nombre_archivo}
                                                                                </div>
                                                                        </div>

                                                                        <!-- VISTA EDICIÓN (Input para reemplazar - Oculto por defecto) -->
                                                                        <div id="vista_subir_${doc.id_tipo_doc}" class="w-100 d-none text-center">
                                                                                <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: #D4AC0D;">
                                                                                        <i class="bi bi-upload" style="font-size: 3rem;"></i>
                                                                                </div>
                                                                                <label class="fw-bold text-secondary small mb-1">Reemplazar archivo:</label>
                                                                                <input type="file" class="form-control form-control-sm border-warning" name="nuevoArchivoPDF" accept=".pdf">
                                                                        </div>
                                                                </div>

                                                                <!-- LADO DERECHO: Observaciones y Botones -->
                                                                <div class="col-9 ps-4 d-flex flex-column justify-content-between">
                                                                        <div>
                                                                                <div class="d-flex justify-content-between align-items-center mb-1">
                                                                                        <label class="fw-bold fs-5 mb-0">Observaciones</label>
                                                                                        <c:choose>
                                                                                                <c:when test="${doc.revisado}">
                                                                                                        <span class="badge bg-success"><i class="bi bi-check-circle-fill me-1"></i> Revisado</span>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                        <span class="badge bg-warning text-dark"><i class="bi bi-hourglass-split me-1"></i> Pendiente</span>
                                                                                                </c:otherwise>
                                                                                        </c:choose>
                                                                                </div>

                                                                                <!-- Textarea bloqueado por defecto (readonly) -->
                                                                                <textarea id="obs_${doc.id_tipo_doc}" class="form-control mb-2 bg-white" style="resize: none;" name="observaciones" rows="2" readonly>${doc.observaciones}</textarea>

                                                                                <div class="d-flex justify-content-between">
                                                                                        <span class="text-muted small fw-bold">
                                                                                                Subido el:
                                                                                                <c:choose>
                                                                                                        <c:when test="${not empty doc.fechaSubida}">
                                                                                                                <fmt:parseDate value="${doc.fechaSubida}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                                                                                                <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${parsedDateTime}" />
                                                                                                        </c:when>
                                                                                                        <c:otherwise>
                                                                                                                No disponible
                                                                                                        </c:otherwise>
                                                                                                </c:choose>
                                                                                        </span>
                                                                                        <span class="text-muted small fw-bold">
                                                                                                Límite: <c:out value="${doc.fecha_limite}" default="Sin asignar" />
                                                                                        </span>
                                                                                </div>
                                                                        </div>

                                                                        <div class="row g-2 mt-3">

                                                                                <!-- BOTONES: MODO VISTA -->
                                                                                <div id="botones_vista_${doc.id_tipo_doc}" class="col-12 d-flex gap-2">
                                                                                        <c:if test="${!doc.revisado}">
                                                                                                <button type="submit" form="formRevisar_${doc.id_tipo_doc}" class="btn btn-success fw-bold shadow-sm flex-grow-1">
                                                                                                        <i class="bi bi-check2-circle me-1"></i> Marcar Revisado
                                                                                                </button>
                                                                                        </c:if>

                                                                                        <c:choose>
                                                                                                <c:when test="${doc.tieneCalendario && doc.puedeSubir}">
                                                                                                        <button type="button" class="btn fw-bold text-white shadow-sm flex-grow-1" style="background-color: #D4AC0D;" onclick="activarEdicion(${doc.id_tipo_doc})">
                                                                                                                <i class="bi bi-pencil-square me-1"></i> Editar
                                                                                                        </button>
                                                                                                        <button type="submit" form="formEliminar_${doc.id_tipo_doc}" class="btn btn-danger fw-bold shadow-sm flex-grow-1">
                                                                                                                Eliminar
                                                                                                        </button>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                        <!-- BOTÓN FALSO PARA MANTENER LA SIMETRÍA -->
                                                                                                        <button type="button" class="btn btn-secondary fw-bold shadow-sm flex-grow-1 disabled" style="opacity: 0.8; pointer-events: none;">
                                                                                                                <i class="bi bi-lock-fill me-1"></i> Plazo vencido
                                                                                                        </button>
                                                                                                </c:otherwise>
                                                                                        </c:choose>

                                                                                        <a href="servlet-ver-documento?idArchivo=${doc.id_archivo}" target="_blank" class="btn fw-bold text-white shadow-sm flex-grow-1" style="background-color: #002E60;">
                                                                                                Abrir archivo
                                                                                        </a>
                                                                                </div>

                                                                                <!-- BOTONES: MODO EDICIÓN (Ocultos por defecto) -->
                                                                                <div id="botones_edicion_${doc.id_tipo_doc}" class="col-12 d-flex gap-2 d-none">
                                                                                        <button type="submit" class="btn fw-bold text-white shadow-sm flex-grow-1" style="background-color: #429983;">
                                                                                                <i class="bi bi-check-circle me-1"></i> Guardar Cambios
                                                                                        </button>
                                                                                        <button type="button" class="btn btn-secondary fw-bold shadow-sm flex-grow-1" onclick="cancelarEdicion(${doc.id_tipo_doc})">
                                                                                                Cancelar
                                                                                        </button>
                                                                                </div>

                                                                        </div>
                                                                </div>
                                                        </form>

                                                        <!-- Formulario oculto independiente para el botón Eliminar -->
                                                        <form action="<%=request.getContextPath()%>/servlet-eliminar-documento" method="POST" id="formEliminar_${doc.id_tipo_doc}" class="d-none">
                                                                <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                        </form>

                                                        <!-- Formulario oculto independiente para Marcar como Revisado -->
                                                        <form action="<%=request.getContextPath()%>/servlet-revisar-documento" method="POST" id="formRevisar_${doc.id_tipo_doc}" class="d-none">
                                                                <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                        </form>

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
                                                                <c:choose>
                                                                        <c:when test="${doc.tieneCalendario && doc.puedeSubir}">
                                                                                <form action="${pageContext.request.contextPath}/servlet-subir-documentos" method="POST" enctype="multipart/form-data" class="d-flex flex-column h-100 justify-content-between m-0">

                                                                                        <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                                                        <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                                        <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                                        <input type="hidden" name="estado" value="Completado">

                                                                                        <div>
                                                                                                <label class="fw-bold fs-5 mb-1">Observaciones</label>
                                                                                                <textarea class="form-control mb-1" style="resize: none;" name="observaciones" rows="2" placeholder="Agrega un comentario..."></textarea>
                                                                                                <span class="text-muted small fw-bold">Fecha de entrega: <c:out value="${doc.fecha_limite}" default="Pendiente" /></span>
                                                                                        </div>

                                                                                        <div class="row mt-2">
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
                                                                        </c:when>

                                                                        <c:when test="${!doc.tieneCalendario}" >
                                                                                <div>
                                                                                        <label class="fw-bold fs-5 mb-1">Observaciones</label>
                                                                                        <textarea class="form-control mb-1 bg-white" rows="2" style="resize: none;" readonly placeholder="Sin observaciones"></textarea>
                                                                                        <span class="text-muted small fw-bold">Fecha de entrega: Sin asignar</span>
                                                                                </div>
                                                                                <div class="text-warning small fw-bold mt-2">
                                                                                        <i class="bi bi-exclamation-triangle-fill me-1"></i> Calendario no asignado.
                                                                                </div>
                                                                        </c:when>

                                                                        <c:otherwise>
                                                                                <div>
                                                                                        <label class="fw-bold fs-5 mb-1">Observaciones</label>
                                                                                        <textarea class="form-control mb-1 bg-white" rows="2" style="resize: none;" readonly placeholder="Plazo de entrega finalizado"></textarea>
                                                                                        <span class="text-muted small fw-bold">Fecha límite: <c:out value="${doc.fecha_limite}" /></span>
                                                                                </div>
                                                                                <div class="text-danger small fw-bold mt-2">
                                                                                        <i class="bi bi-clock-history me-1"></i> La fecha límite de entrega ya venció.
                                                                                </div>
                                                                        </c:otherwise>
                                                                </c:choose>
                                                        </div>
                                                </div>
                                        </div>
                                </c:if>

                        </c:forEach>
                </div>
        </div>
</div>

<!-- Scripts al final del body -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
        function activarEdicion(id) {
                // 1. Desbloquear la caja de texto
                document.getElementById('obs_' + id).removeAttribute('readonly');

                // 2. Intercambiar el logo verde por el input de subir archivo
                document.getElementById('vista_icono_' + id).classList.add('d-none');
                document.getElementById('vista_subir_' + id).classList.remove('d-none');

                // 3. Ocultar botones de vista y mostrar los de guardar/cancelar
                document.getElementById('botones_vista_' + id).classList.add('d-none');
                document.getElementById('botones_edicion_' + id).classList.remove('d-none');
        }

        function cancelarEdicion(id) {
                // 1. Volver a bloquear la caja de texto
                document.getElementById('obs_' + id).setAttribute('readonly', true);

                // 2. Regresar el logo verde y ocultar el input
                document.getElementById('vista_icono_' + id).classList.remove('d-none');
                document.getElementById('vista_subir_' + id).classList.add('d-none');

                // 3. Restaurar los botones originales
                document.getElementById('botones_vista_' + id).classList.remove('d-none');
                document.getElementById('botones_edicion_' + id).classList.add('d-none');
        }
</script>
</body>
</html>