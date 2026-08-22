<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<<<<<<< HEAD
    <title>Detalles de Estudiante</title>
    <!-- Bootstrap y Iconos -->
=======
    <title>Detalles de Estudiante - Docente</title>
    <!-- Bootstrap CSS -->
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

    <!-- Menú Lateral -->
    <div id="menu" class="border-end" style="width: 180px; flex-shrink: 0; position: sticky; top: 0; height: 100vh; overflow-y: auto;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>

    <!-- Contenido Principal -->
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column">
<<<<<<< HEAD

        <!-- Encabezado -->
        <div class="text-center w-100 mb-4 text-white py-3 px-4" style="background-color: #002E60;">
            <div class="row align-items-center justify-content-between">
=======
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
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                <div class="col text-center">
                    <h1 class="m-0 fs-2 fw-semibold">Perfil del Estudiante</h1>
                </div>
            </div>
        </div>

<<<<<<< HEAD
        <div class="w-100 px-4 mx-auto" style="max-width: 950px;">

            <!-- ==========================================
                 1. FORMULARIO DE DATOS PERSONALES
            ========================================== -->
            <form action="${pageContext.request.contextPath}/servlet-modificar-estudiante" method="POST" id="formPerfil">
                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
=======
        <!-- Contenedor principal de información -->
        <div class="mx-auto" style="max-width: 950px; width: 100%;">

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
                    <label class="fw-bold fs-5 mb-1">Matrícula:</label>
                    <input class="form-control" type="text" disabled readonly value="${datosEstudiante.matricula}">
                </div>
            </div>
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f

                <div class="row g-3 mb-3">
                    <div class="col-4">
                        <label class="fw-bold mb-1">Nombre(s):</label>
                        <input class="form-control input-perfil bg-light" type="text" name="nombre" value="${datosEstudiante.nombre}" readonly required>
                    </div>
                    <div class="col-4">
                        <label class="fw-bold mb-1">Apellido(s):</label>
                        <input class="form-control input-perfil bg-light" type="text" name="apellido" value="${datosEstudiante.apellido}" readonly required>
                    </div>
                    <div class="col-4">
                        <label class="fw-bold mb-1">Matrícula:</label>
                        <input class="form-control bg-light" type="text" value="${datosEstudiante.matricula}" disabled>
                    </div>
                </div>

                <div class="row g-3 mb-3">
                    <div class="col-4">
                        <label class="fw-bold mb-1">Carrera:</label>
                        <input class="form-control input-perfil bg-light" type="text" name="carrera" value="${datosEstudiante.carrera}" readonly required>
                    </div>
                    <div class="col-4">
                        <label class="fw-bold mb-1">Cuatrimestre:</label>
                        <select class="form-select input-perfil bg-light" name="cuatrimestre" disabled required>
                            <option value="6" ${datosEstudiante.cuatrimestre == 6 ? 'selected' : ''}>6</option>
                            <option value="11" ${datosEstudiante.cuatrimestre == 11 ? 'selected' : ''}>11</option>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="fw-bold mb-1">Grupo:</label>
                        <input class="form-control input-perfil bg-light" type="text" name="grupo" value="${datosEstudiante.grupo}" readonly required>
                    </div>
                </div>

<<<<<<< HEAD
                <div class="row g-3 mb-3">
                    <div class="col-6">
                        <label class="fw-bold mb-1">Correo:</label>
                        <input class="form-control input-perfil bg-light" type="email" name="correo" value="${datosEstudiante.correo}" readonly required>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold mb-1">Estado:</label>
                        <input class="form-control bg-light text-muted fw-bold" type="text" value="${datosEstudiante.estado}" disabled>
                        <small class="text-secondary"><i class="bi bi-info-circle me-1"></i>Solo el administrador puede cambiar el estado.</small>
                    </div>
                </div>

                <div class="d-flex justify-content-end gap-2 mt-3 mb-4">
                    <button type="button" id="btnEditar" class="btn text-white fw-bold px-4" onclick="toggleEdicion(true)" style="background-color: #002E60">
                        <i class="bi bi-pencil-square me-1"></i> Editar datos
                    </button>
                    <button type="button" id="btnCancelar" class="btn btn-secondary fw-bold d-none" onclick="toggleEdicion(false)">
                        Cancelar
                    </button>
                    <button type="submit" id="btnGuardar" class="btn text-white fw-bold d-none" style="background-color: #429983;">
                        <i class="bi bi-save me-1"></i> Guardar Cambios
                    </button>
                </div>
            </form>

            <!-- ==========================================
                 2. SECCIÓN DE DOCUMENTOS
            ========================================== -->
            <div class="w-100 border-bottom pb-2 mb-4">
                <h3 class="fw-bold" style="color: #002E60;">Documentos del Estudiante</h3>
=======
            <!-- Separador: Documentos con Selector de Período Escolar -->
            <div class="w-100 mt-5 mb-4 border-bottom pb-2 d-flex justify-content-between align-items-center">
                <h3 class="fw-bold m-0" style="color: #002E60;">Documentos de Estadía</h3>

                <!-- Permite al docente cambiar de período para consultar TSU o Ingeniería -->
                <c:if test="${not empty listaPeriodosEstudiante}">
                    <form action="${pageContext.request.contextPath}/servlet-lista-estudiantes-detalle" method="GET" class="d-flex align-items-center gap-2">
                        <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                        <label class="fw-bold text-secondary mb-0 me-1">Período:</label>
                        <select name="idPeriodo" class="form-select form-select-sm border-primary fw-bold" onchange="this.form.submit()">
                            <c:forEach var="p" items="${listaPeriodosEstudiante}">
                                <option value="${p.id_periodo}" ${p.id_periodo == periodoSeleccionado ? 'selected' : ''}>
                                        ${p.nombre_periodo} (${p.nivel_estadia})
                                </option>
                            </c:forEach>
                        </select>
                    </form>
                </c:if>
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
            </div>

            <c:forEach var="doc" items="${listaDocumentos}">
                <%-- Solo puede modificar si el alumno está ACTIVO, tiene calendario y está a tiempo --%>
                <c:set var="estaVencido" value="${!doc.puedeSubir && doc.tieneCalendario}" />
                <c:set var="alumnoActivo" value="${datosEstudiante.estado eq 'Activo'}" />
                <c:set var="puedeModificar" value="${alumnoActivo && doc.tieneCalendario && doc.puedeSubir}" />

                <%-- Selección del color institucional --%>
                <c:choose>
                    <c:when test="${doc.estado eq 'Completado' && doc.revisado}">
                        <c:set var="colorCard" value="#429983" />
                    </c:when>
                    <c:when test="${doc.estado eq 'Completado' && !doc.revisado}">
                        <c:set var="colorCard" value="#002E60" />
                    </c:when>
                    <c:when test="${estaVencido}">
                        <c:set var="colorCard" value="#C85252" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="colorCard" value="#D4AC0D" />
                    </c:otherwise>
                </c:choose>

                <!-- Tarjeta del Documento -->
                <div class="card shadow-sm mb-4 border-0" style="background-color: #f8f9fa;">
                    <div class="card-body row g-0">

<<<<<<< HEAD
                        <!-- Icono y Nombre -->
                        <div class="col-3 d-flex flex-column align-items-center justify-content-center pe-3 border-end">
                            <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: ${colorCard};">
                                <i class="bi bi-file-earmark-text-fill" style="font-size: 3.5rem;"></i>
=======
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
                                                <%-- EL DOCENTE SOLO TIENE ACCESO SI EL CALENDARIO ESTÁ ACTIVO --%>
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
                                                    <!-- Plazo Vencido o Período Histórico (Bloqueado para Docente) -->
                                                    <button type="button" class="btn btn-secondary fw-bold shadow-sm disabled d-flex align-items-center justify-content-center text-nowrap py-2 px-1" style="opacity: 0.85; pointer-events: none; flex: 1 1 0px; font-size: 0.88rem;">
                                                        <i class="bi bi-lock-fill me-1"></i> Plazo vencido
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>

                                            <!-- Abrir Archivo (Siempre visible para consulta) -->
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
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                            </div>
                            <div class="w-100 text-center py-2 rounded text-white fw-bold small shadow-sm" style="background-color: ${colorCard};">
                                <c:out value="${doc.nombre_archivo}" />
                            </div>
                        </div>

                        <!-- Información y Acciones -->
                        <div class="col-9 ps-4 d-flex flex-column justify-content-between">

                            <!-- CASO 1: ARCHIVO YA SUBIDO -->
                            <c:if test="${doc.estado eq 'Completado'}">
                                <form action="${pageContext.request.contextPath}/servlet-modificar-observacion" method="POST" id="formDoc_${doc.id_tipo_doc}" enctype="multipart/form-data">
                                    <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                    <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                    <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">

                                    <div class="d-flex justify-content-between align-items-center mb-1">
                                        <label class="fw-bold mb-0">Observaciones</label>
                                        <span class="badge ${doc.revisado ? 'bg-success' : 'text-white'}" style="${!doc.revisado ? 'background-color: #002E60;' : ''}">
                                            <i class="bi ${doc.revisado ? 'bi-check-circle-fill' : 'bi-hourglass-split'} me-1"></i>
                                            ${doc.revisado ? 'Revisado' : 'Pendiente de revisión'}
                                        </span>
                                    </div>

                                    <textarea id="obs_${doc.id_tipo_doc}" name="observaciones" class="form-control mb-2 bg-white" rows="2" style="resize: none;" readonly>${doc.observaciones}</textarea>

                                    <!-- Campo para reemplazar PDF (Oculto hasta pulsar Editar) -->
                                    <div id="campoReemplazar_${doc.id_tipo_doc}" class="mb-2 d-none">
                                        <label class="small fw-bold text-muted">Reemplazar archivo PDF:</label>
                                        <input type="file" name="nuevoArchivoPDF" class="form-control form-control-sm" accept=".pdf">
                                    </div>

                                    <div class="d-flex justify-content-between small text-muted fw-bold mb-3">
                                        <span>Subido el: <c:out value="${doc.fechaSubida}" default="No disponible" /></span>
                                        <span>Límite: <c:out value="${doc.fecha_limite}" default="Sin asignar" /></span>
                                    </div>

                                    <div class="d-flex gap-2">
                                        <c:choose>
                                            <c:when test="${puedeModificar}">
                                                <!-- Botón Revisar / Pendiente -->
                                                <button type="submit" name="accion" value="${doc.revisado ? 'desmarcar' : 'marcar'}" class="btn text-white fw-bold flex-grow-1" style="background-color: ${doc.revisado ? '#D4AC0D' : '#429983'};">
                                                    <i class="bi ${doc.revisado ? 'bi-arrow-counterclockwise' : 'bi-check2-circle'} me-1"></i>
                                                        ${doc.revisado ? 'Pendiente' : 'Revisar'}
                                                </button>

                                                <!-- Botón Editar -->
                                                <button type="button" class="btn text-white fw-bold flex-grow-1" style="background-color: #345177;" onclick="editarDoc(${doc.id_tipo_doc})">
                                                    <i class="bi bi-pencil-square me-1"></i> Editar
                                                </button>

                                                <!-- Botón Guardar cambios de edición (Oculto) -->
                                                <button type="submit" id="btnGuardarDoc_${doc.id_tipo_doc}" class="btn btn-success fw-bold flex-grow-1 d-none">
                                                    Guardar
                                                </button>

                                                <!-- Botón Eliminar: CONECTADO AL FORMULARIO INDEPENDIENTE -->
                                                <button type="submit" form="formEliminar_${doc.id_tipo_doc}" class="btn btn-danger fw-bold flex-grow-1" onclick="return confirm('¿Seguro que deseas eliminar este documento?');">
                                                    Eliminar
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn btn-secondary fw-bold flex-grow-1 disabled">
                                                    <i class="bi bi-lock-fill me-1"></i> ${!alumnoActivo ? 'Estudiante inactivo' : 'Plazo vencido'}
                                                </button>
                                            </c:otherwise>
                                        </c:choose>

                                        <a href="servlet-ver-documento?idArchivo=${doc.id_archivo}" target="_blank" class="btn text-white fw-bold flex-grow-1" style="background-color: #002E60;">
                                            Abrir archivo
                                        </a>
                                    </div>
                                </form>

                                <!-- Formulario independiente de eliminación (POST estándar) -->
                                <form action="${pageContext.request.contextPath}/servlet-eliminar-documento" method="POST" id="formEliminar_${doc.id_tipo_doc}" class="d-none">
                                    <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                    <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                    <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                </form>
                            </c:if>

                            <!-- CASO 2: ARCHIVO PENDIENTE DE SUBIR -->
                            <c:if test="${doc.estado eq 'Pendiente'}">
                                <c:choose>
                                    <c:when test="${puedeModificar}">
                                        <form action="${pageContext.request.contextPath}/servlet-subir-documentos" method="POST" enctype="multipart/form-data">
                                            <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                            <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                            <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                            <input type="hidden" name="estado" value="Completado">

                                            <label class="fw-bold mb-1">Observaciones</label>
                                            <textarea name="observaciones" class="form-control mb-2" rows="2" style="resize: none;" placeholder="Comentario opcional..."></textarea>

                                            <div class="d-flex justify-content-between small text-muted fw-bold mb-2">
                                                <span>Fecha de entrega: <c:out value="${doc.fecha_limite}" default="Sin asignar" /></span>
                                            </div>

                                            <div class="input-group shadow-sm">
                                                <input type="file" name="archivoPDF" class="form-control" accept=".pdf" required>
                                                <button type="submit" class="btn text-white fw-bold px-4" style="background-color: #429983;">
                                                    Subir
                                                </button>
                                            </div>
                                        </form>
                                    </c:when>
                                    <c:when test="${!alumnoActivo}">
                                        <div>
                                            <label class="fw-bold mb-1">Observaciones</label>
                                            <textarea class="form-control mb-2 bg-white" rows="2" style="resize: none;" readonly placeholder="Sin observaciones"></textarea>
                                            <span class="text-muted small fw-bold">Fecha de entrega: <c:out value="${doc.fecha_limite}" default="Sin asignar" /></span>
                                        </div>
                                        <div class="text-danger small fw-bold mt-2">
                                            <i class="bi bi-person-x-fill me-1"></i> El estudiante se encuentra inactivo. No se pueden modificar documentos.
                                        </div>
                                    </c:when>
                                    <c:when test="${!doc.tieneCalendario}">
                                        <div>
                                            <label class="fw-bold mb-1">Observaciones</label>
                                            <textarea class="form-control mb-2 bg-white" rows="2" style="resize: none;" readonly placeholder="Sin observaciones"></textarea>
                                            <span class="text-muted small fw-bold">Fecha de entrega: Sin asignar</span>
                                        </div>
                                        <div class="text-warning small fw-bold mt-2">
                                            <i class="bi bi-exclamation-triangle-fill me-1"></i> Calendario no asignado.
                                        </div>
                                    </c:when>

                                    <c:otherwise>
                                        <div>
                                            <label class="fw-bold mb-1">Observaciones</label>
                                            <textarea class="form-control mb-2 bg-white" rows="2" style="resize: none;" readonly placeholder="Plazo de entrega finalizado"></textarea>
                                            <span class="text-muted small fw-bold">Fecha límite: <c:out value="${doc.fecha_limite}" default="Sin asignar" /></span>
                                        </div>
                                        <div class="text-danger small fw-bold mt-2">
                                            <i class="bi bi-clock-history me-1"></i> La fecha límite de entrega ya venció.
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>

                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>

<<<<<<< HEAD
<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function toggleEdicion(activar) {
        const inputs = document.querySelectorAll('.input-perfil');
        inputs.forEach(input => {
            if (activar) {
                input.removeAttribute('readonly');
                input.removeAttribute('disabled');
                input.classList.remove('bg-light');
            } else {
                input.setAttribute('readonly', 'true');
                if (input.tagName === 'SELECT') input.setAttribute('disabled', 'true');
                input.classList.add('bg-light');
            }
        });
        document.getElementById('btnEditar').classList.toggle('d-none', activar);
        document.getElementById('btnCancelar').classList.toggle('d-none', !activar);
        document.getElementById('btnGuardar').classList.toggle('d-none', !activar);
=======
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
    function activarEdicion(id) {
        document.getElementById('obs_' + id).removeAttribute('readonly');
        document.getElementById('vista_icono_' + id).classList.add('d-none');
        document.getElementById('vista_subir_' + id).classList.remove('d-none');
        document.getElementById('botones_vista_' + id).classList.add('d-none');
        document.getElementById('botones_edicion_' + id).classList.remove('d-none');
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    }

    function editarDoc(id) {
        document.getElementById('obs_' + id).removeAttribute('readonly');
        document.getElementById('campoReemplazar_' + id).classList.remove('d-none');
        document.getElementById('btnGuardarDoc_' + id).classList.remove('d-none');
    }
</script>
</body>
</html>