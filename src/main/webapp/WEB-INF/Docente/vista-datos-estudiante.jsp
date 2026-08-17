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
        <!-- Encabezado -->
        <div class="text-center w-100 mb-4 text-white m-0 py-3 px-4" style="background-color: #002E60;">
            <div class="row align-items-center justify-content-between">
                <!-- Botón Regresar -->
                <div class="col-auto">
                    <a href="${pageContext.request.contextPath}/servlet-lista-estudiantes" class="btn btn-outline-light d-flex align-items-center gap-2">
                        <i class="bi bi-arrow-left fs-5"></i>
                    </a>
                </div>
                <!-- Título Centrado -->
                <div class="col text-center">
                    <h1 class="m-0 fs-2 fw-semibold">Perfil del Estudiante</h1>
                </div>
            </div>
        </div>

        <!-- Contenedor principal de información -->
        <div class="mx-auto" style="max-width: 950px;">

            <!-- Datos personales: Nombre, Apellidos, Matrícula -->
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

            <!-- Carrera, Cuatrimestre y Grupo -->
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

            <!-- Correo y Estado -->
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

            <!-- Separador: Documentos -->
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

                            <!-- Formulario principal para modificar observaciones/reemplazar -->
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

                                    <!-- VISTA EDICIÓN (Oculto por defecto) -->
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
                                                    <span class="badge text-white" style="background-color: #429983;"><i class="bi bi-check-circle-fill me-1"></i> Revisado</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge text-white" style="background-color: #D4AC0D;"><i class="bi bi-hourglass-split me-1"></i> Pendiente</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                        <!-- Textarea bloqueado por defecto -->
                                        <textarea id="obs_${doc.id_tipo_doc}" class="form-control mb-1 bg-white" style="resize: none;" name="observaciones" rows="2" readonly>${doc.observaciones}</textarea>
                                        <span class="text-muted small fw-bold">Fecha de entrega: <c:out value="${doc.fecha_limite}" default="Sin asignar" /></span>
                                    </div>

                                    <div class="row g-2 mt-2">

                                        <!-- BOTONES: MODO VISTA -->
                                        <div id="botones_vista_${doc.id_tipo_doc}" class="col-12 d-flex gap-2 align-items-stretch">
                                            <c:choose>
                                                <c:when test="${doc.tieneCalendario && doc.puedeSubir}">

                                                    <!-- Marcar / Desmarcar Revisado -->
                                                    <c:choose>
                                                        <c:when test="${!doc.revisado}">
                                                            <button type="submit" name="accion" value="marcar" class="btn fw-bold text-white shadow-sm d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="background-color: #429983; flex: 1 1 0px; font-size: 0.88rem;">
                                                                <i class="bi bi-check2-circle me-1"></i> Revisar
                                                            </button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button type="submit" name="accion" value="desmarcar" class="btn fw-bold text-white shadow-sm d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="background-color: #D4AC0D; flex: 1 1 0px; font-size: 0.88rem;">
                                                                <i class="bi bi-arrow-counterclockwise me-1"></i> Pendiente
                                                            </button>
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <!-- Editar y Eliminar -->
                                                    <button type="button" class="btn fw-bold text-white shadow-sm d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="background-color: #3C5A80; flex: 1 1 0px; font-size: 0.88rem;" onclick="activarEdicion(${doc.id_tipo_doc})">
                                                        <i class="bi bi-pencil-square me-1"></i> Editar
                                                    </button>
                                                    <button type="submit" form="formEliminar_${doc.id_tipo_doc}" class="btn fw-bold shadow-sm d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="flex: 1 1 0px; font-size: 0.88rem; background-color: #C85252; color: white">
                                                        Eliminar
                                                    </button>
                                                </c:when>

                                                <c:otherwise>
                                                    <!-- Plazo Vencido (Exactamente 50% de ancho) -->
                                                    <button type="button" class="btn btn-secondary fw-bold shadow-sm disabled d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="opacity: 0.85; pointer-events: none; flex: 1 1 0px; font-size: 0.88rem;">
                                                        <i class="bi bi-lock-fill me-1"></i> Plazo vencido
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>

                                            <!-- Abrir Archivo -->
                                            <a href="servlet-ver-documento?idArchivo=${doc.id_archivo}" target="_blank" class="btn fw-bold text-white shadow-sm d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="background-color: #002E60; flex: 1 1 0px; font-size: 0.88rem;">
                                                Abrir archivo
                                            </a>
                                        </div>

                                        <!-- BOTONES: MODO EDICIÓN -->
                                        <div id="botones_edicion_${doc.id_tipo_doc}" class="col-12 d-flex gap-2 align-items-stretch d-none">
                                            <button type="submit" class="btn fw-bold text-white shadow-sm d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="background-color: #429983; flex: 1 1 0px; font-size: 0.88rem;">
                                                <i class="bi bi-check-circle me-1"></i> Guardar Cambios
                                            </button>
                                            <button type="button" class="btn btn-secondary fw-bold shadow-sm d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="flex: 1 1 0px; font-size: 0.88rem;" onclick="cancelarEdicion(${doc.id_tipo_doc})">
                                                Cancelar
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </form>

                            <!-- Formulario independiente para el botón Eliminar -->
                            <form action="<%=request.getContextPath()%>/servlet-eliminar-documento" method="POST" id="formEliminar_${doc.id_tipo_doc}" class="d-none">
                                <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                            </form>

                        </div>
                    </div>
                </c:if>

                <!-- ==========================================
                ESTADO 2: DOCUMENTO NO SUBIDO (AMARILLO / PENDIENTE)
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

                                    <c:when test="${!doc.tieneCalendario}">
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function activarEdicion(id) {
        document.getElementById('obs_' + id).removeAttribute('readonly');
        document.getElementById('vista_icono_' + id).classList.add('d-none');
        document.getElementById('vista_subir_' + id).classList.remove('d-none');
        document.getElementById('botones_vista_' + id).classList.add('d-none');
        document.getElementById('botones_edicion_' + id).classList.remove('d-none');
    }

    function cancelarEdicion(id) {
        document.getElementById('obs_' + id).setAttribute('readonly', true);
        document.getElementById('vista_icono_' + id).classList.remove('d-none');
        document.getElementById('vista_subir_' + id).classList.add('d-none');
        document.getElementById('botones_vista_' + id).classList.remove('d-none');
        document.getElementById('botones_edicion_' + id).classList.add('d-none');
    }
</script>
</body>
</html>