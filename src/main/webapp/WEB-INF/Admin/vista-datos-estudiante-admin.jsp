<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<<<<<< HEAD
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
=======
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
<!doctype html>
<html lang="es">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
<<<<<<< HEAD
        <title>Perfil de Estudiante - Administración</title>
        <!-- Bootstrap y Iconos -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

        <!-- Menú Lateral -->
        <div id="menu" class="border-end" style="width: 180px; flex-shrink: 0; position: sticky; top: 0; height: 100vh; overflow-y: auto;">
=======
        <title>Detalles de Estudiante - Administración</title>
        <!-- Bootstrap CSS & Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
                .input-gris { background-color: #EFEFEF !important; border: none !important; color: #333 !important; }
                .input-edicion { background-color: #FFFFFF !important; border: 1px solid #0557B3 !important; color: #000 !important; }
                .card-documento { background-color: #EBF0F2; border-radius: 8px; }
                .box-icono-doc { height: 100%; min-height: 150px; display: flex; flex-direction: column; justify-content: space-between; }
        </style>
</head>
<body class="bg-white">
<div id="contenido" class="d-flex min-vh-100">

        <!-- Menú Lateral Estático -->
        <div id="menu" class="border-end bg-white" style="width: 180px; flex-shrink: 0; position: sticky; top: 0; height: 100vh; overflow-y: auto;">
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                <jsp:include page="../Plantillas/menu.jsp" />
        </div>

        <!-- Contenido Principal -->
        <div id="cambiantes" class="flex-grow-1 d-flex flex-column">
<<<<<<< HEAD

                <!-- Encabezado -->
                <div class="text-center w-100 mb-4 text-white py-3 px-4" style="background-color: #002E60;">
                        <div class="row align-items-center justify-content-between">
=======
                <!-- Encabezado azul oscuro -->
                <div class="text-center w-100 mb-4 text-white m-0 py-3 px-4" style="background-color: #002E60;">
                        <div class="row align-items-center justify-content-between">
                                <div class="col-auto">
                                        <a href="${pageContext.request.contextPath}/servlet-admin-estudiantes" class="btn btn-outline-light d-flex align-items-center gap-2">
                                                <i class="bi bi-arrow-left fs-5"></i>
                                        </a>
                                </div>
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                                <div class="col text-center">
                                        <h1 class="m-0 fs-2 fw-bold">Perfil del estudiante</h1>
                                </div>
                                <div class="col-auto" style="width: 42px;"></div>
                        </div>
                </div>

<<<<<<< HEAD
                <div class="w-100 px-4 mx-auto" style="max-width: 950px;">

                        <!-- ==========================================
                             1. FORMULARIO DE DATOS PERSONALES
                        ========================================== -->
                        <form action="${pageContext.request.contextPath}/servlet-modificar-estudiante" method="POST" id="formPerfil">
                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">

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

                                <div class="row g-3 mb-3">
                                        <div class="col-6">
                                                <label class="fw-bold mb-1">Correo:</label>
                                                <input class="form-control input-perfil bg-light" type="email" name="correo" value="${datosEstudiante.correo}" readonly required>
                                        </div>
                                        <div class="col-6">
                                                <label class="fw-bold mb-1">Estado:</label>
                                                <select class="form-select input-perfil bg-light" name="estado" disabled required>
                                                        <option value="Activo" ${datosEstudiante.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                                                        <option value="Inactivo" ${datosEstudiante.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                                                </select>
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
                        </div>
=======
                <!-- Contenedor principal -->
                <div class="mx-auto px-3" style="max-width: 850px; width: 100%;">

                        <!-- FORMULARIO DE EDICIÓN DEL PERFIL -->
                        <form action="${pageContext.request.contextPath}/servlet-modificar-estudiante" method="POST" id="formPerfilEstudiante" class="mb-5">

                                <input type="hidden" name="matriculaOriginal" value="${datosEstudiante.matricula}">

                                <!-- Fila 1 -->
                                <div class="row g-4 mb-3">
                                        <div class="col-4">
                                                <label class="fw-bold small mb-1 text-dark">Nombre(s):</label>
                                                <input class="form-control form-control-sm input-gris campo-estudiante" name="nombre" type="text" disabled readonly value="${datosEstudiante.nombre}">
                                        </div>
                                        <div class="col-4">
                                                <label class="fw-bold small mb-1 text-dark">Apellido(s):</label>
                                                <input class="form-control form-control-sm input-gris campo-estudiante" name="apellido" type="text" disabled readonly value="${datosEstudiante.apellido}">
                                        </div>
                                        <div class="col-4">
                                                <label class="fw-bold small mb-1 text-dark">Matrícula:</label>
                                                <input class="form-control form-control-sm input-gris campo-estudiante" name="matricula" type="text" disabled readonly value="${datosEstudiante.matricula}">
                                        </div>
                                </div>

                                <!-- Fila 2 -->
                                <div class="row g-4 mb-3">
                                        <div class="col-4">
                                                <label class="fw-bold small mb-1 text-dark">Carrera:</label>
                                                <input class="form-control form-control-sm input-gris campo-estudiante" name="carrera" type="text" disabled readonly value="${datosEstudiante.carrera}">
                                        </div>
                                        <div class="col-4">
                                                <label class="fw-bold small mb-1 text-dark">Cuatrimestre:</label>
                                                <input class="form-control form-control-sm input-gris campo-estudiante" name="cuatrimestre" type="text" disabled readonly value="${datosEstudiante.cuatrimestre}">
                                        </div>
                                        <div class="col-4">
                                                <label class="fw-bold small mb-1 text-dark">Grupo:</label>
                                                <input class="form-control form-control-sm input-gris campo-estudiante" name="grupo" type="text" disabled readonly value="${datosEstudiante.grupo}">
                                        </div>
                                </div>

                                <!-- Fila 3 -->
                                <div class="row g-4 mb-3">
                                        <div class="col-6">
                                                <label class="fw-bold small mb-1 text-dark">Correo electrónico:</label>
                                                <input class="form-control form-control-sm input-gris campo-estudiante" name="correo" type="text" disabled readonly value="${datosEstudiante.correo}">
                                        </div>
                                        <div class="col-6">
                                                <label class="fw-bold small mb-1 text-dark">Estado:</label>
                                                <select class="form-select form-select-sm input-gris campo-estudiante" name="estado" disabled required>
                                                        <option value="Activo" ${datosEstudiante.estado == 'Activo' || datosEstudiante.estado == 'activo' ? 'selected' : ''}>Activo</option>
                                                        <option value="Inactivo" ${datosEstudiante.estado == 'Inactivo' || datosEstudiante.estado == 'inactivo' ? 'selected' : ''}>Inactivo</option>
                                                </select>
                                        </div>
                                </div>

                                <!-- Fila 4 -->
                                <div class="row g-4 mb-3">
                                        <div class="col-12">
                                                <label class="fw-bold small mb-1 text-dark">Docente Asesor Actual:</label>

                                                <input id="inputDocenteLectura" class="form-control form-control-sm input-gris" type="text" disabled readonly
                                                       value="${not empty datosEstudiante.docenteAsignado ? datosEstudiante.docenteAsignado : 'Sin asignar'}">

                                                <select id="selectDocenteEdicion" name="idDocente" class="form-select form-select-sm border-primary text-dark d-none">
                                                        <option value="0">-- Sin asignar --</option>
                                                        <c:forEach var="docente" items="${listaDocentesActivos}">
                                                                <option value="${docente.id}" ${not empty datosEstudiante.idDocente && docente.id == datosEstudiante.idDocente ? 'selected' : ''}>
                                                                                ${docente.nombre} ${docente.apellido}
                                                                </option>
                                                        </c:forEach>
                                                </select>
                                        </div>
                                </div>

                                <!-- Botones Perfil -->
                                <div class="row mt-3">
                                        <div class="col-12 d-flex justify-content-end gap-2">
                                                <button type="button" id="btnEditarPerfil" class="btn btn-sm text-white fw-bold px-3 shadow-sm" onclick="activarEdicionPerfil()" style="background-color: #002E60">
                                                        <i class="bi bi-pencil-square me-1"></i> Editar datos
                                                </button>
                                                <button type="button" id="btnCancelarPerfil" class="btn btn-sm btn-secondary fw-bold shadow-sm d-none" onclick="cancelarEdicionPerfil()">
                                                        Cancelar
                                                </button>
                                                <button type="submit" id="btnGuardarPerfil" class="btn btn-sm text-white fw-bold shadow-sm d-none" style="background-color: #429983;">
                                                        <i class="bi bi-save me-1"></i> Guardar Cambios
                                                </button>
                                        </div>
                                </div>
                        </form>

                        <!-- Título Separador Documentos -->
                        <div class="w-100 mb-4 border-bottom pb-2 d-flex justify-content-between align-items-center">
                                <h2 class="fw-bold m-0 fs-3" style="color: #002E60;">Documentos del Estudiante</h2>

                                <c:if test="${not empty listaPeriodosEstudiante}">
                                        <form action="${pageContext.request.contextPath}/servlet-datos-estudiante" method="GET" class="d-flex align-items-center gap-2">
                                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                <label class="fw-bold text-secondary small mb-0 me-1">Período:</label>
                                                <select name="idPeriodo" class="form-select form-select-sm border-primary fw-bold" onchange="this.form.submit()">
                                                        <c:forEach var="p" items="${listaPeriodosEstudiante}">
                                                                <option value="${p.id_periodo}" ${p.id_periodo == periodoSeleccionado ? 'selected' : ''}>
                                                                                ${p.nombre_periodo}
                                                                </option>
                                                        </c:forEach>
                                                </select>
                                        </form>
                                </c:if>
                        </div>

                        <c:if test="${empty listaDocumentos}">
                                <div class="alert alert-warning text-center fw-bold shadow-sm py-2 my-3 small">
                                        <i class="bi bi-exclamation-triangle-fill fs-6 me-2"></i>
                                        No hay documentos cargados ni configurados para este estudiante en el período seleccionado.
                                </div>
                        </c:if>
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f

                        <c:forEach var="doc" items="${listaDocumentos}">
                                <%-- Permisos: Admin siempre puede, Docente solo si está a tiempo --%>
                                <c:set var="esAdmin" value="${not empty sessionScope.adminLogueado}" />
                                <c:set var="estaVencido" value="${!doc.puedeSubir && doc.tieneCalendario}" />
                                <c:set var="puedeModificar" value="${esAdmin || (!estaVencido && doc.tieneCalendario)}" />

<<<<<<< HEAD
                                <%-- Color del Icono:
                                     - Verde (#429983): Si ya está revisado
                                     - Azul (#002E60): Subido pero falta revisar
                                     - Rojo (#C85252): Sin archivo y ya venció fecha
                                     - Amarillo (#D4AC0D): Sin archivo pero aún tiene tiempo
                                --%>
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

                                                <!-- Icono y Nombre -->
                                                <div class="col-3 d-flex flex-column align-items-center justify-content-center pe-3 border-end">
                                                        <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: ${colorCard};">
                                                                <i class="bi bi-file-earmark-text-fill" style="font-size: 3.5rem;"></i>
=======
                                <!-- ESTADO 1: DOCUMENTO COMPLETADO (YA SUBIDO) -->
                                <c:if test="${doc.estado == 'Completado'}">
                                        <div class="card shadow-sm mb-4 border-0" style="background-color: #f8f9fa;">
                                                <div class="card-body row g-0">

                                                        <form action="${pageContext.request.contextPath}/servlet-modificar-observacion" method="POST" id="formModificar_${doc.id_tipo_doc}" enctype="multipart/form-data" class="col-12 d-flex m-0">

                                                                <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                                <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">

                                                                <div class="col-3 d-flex flex-column align-items-center justify-content-center pe-3 border-end">
                                                                        <div id="vista_icono_${doc.id_tipo_doc}" class="w-100">
                                                                                <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: #429983;">
                                                                                        <i class="bi bi-file-earmark-text-fill" style="font-size: 4rem;"></i>
                                                                                </div>
                                                                                <div class="w-100 text-center py-2 rounded text-white fw-bold shadow-sm text-truncate" style="background-color: #429983;">
                                                                                        <c:out value="${doc.nombre_archivo}" default="Documento" />
                                                                                </div>
                                                                        </div>

                                                                        <div id="vista_subir_${doc.id_tipo_doc}" class="w-100 d-none text-center">
                                                                                <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: #D4AC0D;">
                                                                                        <i class="bi bi-upload" style="font-size: 3rem;"></i>
                                                                                </div>
                                                                                <label class="fw-bold text-secondary small mb-1">Reemplazar archivo:</label>
                                                                                <input type="file" class="form-control form-control-sm border-warning" name="nuevoArchivoPDF" accept=".pdf">
                                                                        </div>
                                                                </div>

                                                                <div class="col-9 ps-4 d-flex flex-column justify-content-between">
                                                                        <div>
                                                                                <div class="d-flex justify-content-between align-items-center mb-1">
                                                                                        <label class="fw-bold fs-5 mb-0">Observaciones</label>
                                                                                        <c:choose>
                                                                                                <c:when test="${doc.revisado}">
                                                                                                        <span class="badge bg-success"><i class="bi bi-check-circle-fill me-1"></i> Revisado</span>
                                                                                                </c:when>
                                                                                                <c:otherwise>
                                                                                                        <span class="badge text-white" style="background-color: #D4AC0D;"><i class="bi bi-hourglass-split me-1"></i> Pendiente</span>
                                                                                                </c:otherwise>
                                                                                        </c:choose>
                                                                                </div>

                                                                                <textarea id="obs_${doc.id_tipo_doc}" class="form-control mb-2 bg-white" style="resize: none;" name="observaciones" rows="2" readonly>${doc.observaciones}</textarea>

                                                                                <div class="d-flex justify-content-between">
                                            <span class="text-muted small fw-bold">
                                                Subido el:
                                                <c:choose>
                                                        <c:when test="${not empty doc.fechaSubida}">
                                                                <fmt:parseDate value="${doc.fechaSubida}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                                                <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${parsedDateTime}" />
                                                        </c:when>
                                                        <c:otherwise>No disponible</c:otherwise>
                                                </c:choose>
                                            </span>
                                                                                        <span class="text-muted small fw-bold">
                                                Límite: <c:out value="${doc.fecha_limite}" default="Sin asignar" />
                                            </span>
                                                                                </div>
                                                                        </div>

                                                                        <div class="row g-2 mt-3">
                                                                                <div id="botones_vista_${doc.id_tipo_doc}" class="col-12 d-flex gap-2">
                                                                                        <c:choose>
                                                                                                <c:when test="${not empty sessionScope.adminLogueado || not empty sessionScope.docenteLogueado || (doc.tieneCalendario && doc.puedeSubir)}">
                                                                                                        <c:choose>
                                                                                                                <c:when test="${!doc.revisado}">
                                                                                                                        <button type="submit" name="accion" value="marcar" class="btn fw-bold text-white shadow-sm flex-grow-1 text-truncate" style="background-color: #429983;">
                                                                                                                                <i class="bi bi-check2-circle me-1"></i> Revisar
                                                                                                                        </button>
                                                                                                                </c:when>
                                                                                                                <c:otherwise>
                                                                                                                        <button type="submit" name="accion" value="desmarcar" class="btn fw-bold text-white shadow-sm flex-grow-1 text-truncate" style="background-color: #D4AC0D;">
                                                                                                                                <i class="bi bi-arrow-counterclockwise me-1"></i> Pendiente
                                                                                                                        </button>
                                                                                                                </c:otherwise>
                                                                                                        </c:choose>

                                                                                                        <button type="button" class="btn fw-bold text-white shadow-sm flex-grow-1 text-truncate" style="background-color: #345177;" onclick="activarEdicion(${doc.id_tipo_doc})">
                                                                                                                <i class="bi bi-pencil-square me-1"></i> Editar
                                                                                                        </button>
                                                                                                        <button type="submit" form="formEliminar_${doc.id_tipo_doc}" class="btn fw-bold shadow-sm flex-grow-1 text-truncate" style="background-color: #C85252; color: white;">
                                                                                                                <i class="bi bi-trash-fill me-1"></i> Eliminar
                                                                                                        </button>
                                                                                                </c:when>

                                                                                                <c:otherwise>
                                                                                                        <button type="button" class="btn btn-secondary fw-bold shadow-sm flex-grow-1 disabled" style="opacity: 0.8; pointer-events: none;">
                                                                                                                <i class="bi bi-lock-fill me-1"></i> Plazo vencido
                                                                                                        </button>
                                                                                                </c:otherwise>
                                                                                        </c:choose>

                                                                                        <a href="servlet-ver-documento?idArchivo=${doc.id_archivo}" target="_blank" class="btn fw-bold text-white shadow-sm flex-grow-1 text-center text-truncate" style="background-color: #002E60;">
                                                                                                Abrir archivo
                                                                                        </a>
                                                                                </div>

                                                                                <div id="botones_edicion_${doc.id_tipo_doc}" class="col-12 d-flex gap-2 d-none">
                                                                                        <button type="submit" name="accion" value="guardar_edicion" class="btn fw-bold text-white shadow-sm flex-grow-1" style="background-color: #429983;">
                                                                                                <i class="bi bi-check-circle me-1"></i> Guardar Cambios
                                                                                        </button>
                                                                                        <button type="button" class="btn btn-secondary fw-bold shadow-sm flex-grow-1" onclick="cancelarEdicion(${doc.id_tipo_doc})">
                                                                                                Cancelar
                                                                                        </button>
                                                                                </div>
                                                                        </div>
                                                                </div>
                                                        </form>

                                                        <form action="${pageContext.request.contextPath}/servlet-eliminar-documento" method="POST" id="formEliminar_${doc.id_tipo_doc}" class="d-none" onsubmit="return confirm('¿Seguro que deseas eliminar este documento?');">
                                                                <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                        </form>
                                                </div>
                                        </div>
                                </c:if>

                                <!-- ESTADO 2: DOCUMENTO PENDIENTE (NO SUBIDO) -->
                                <c:if test="${doc.estado == 'Pendiente'}">
                                        <div class="card shadow-sm mb-4 border-0" style="background-color: #f8f9fa;">
                                                <div class="card-body row g-0">
                                                        <div class="col-3 d-flex flex-column align-items-center justify-content-center pe-3 border-end">
                                                                <div class="w-100 text-center rounded p-3 mb-2 text-white shadow-sm" style="background-color: #D4AC0D;">
                                                                        <i class="bi bi-file-earmark-text-fill" style="font-size: 4rem;"></i>
                                                                </div>
                                                                <div class="w-100 text-center py-2 rounded text-white fw-bold shadow-sm text-truncate" style="background-color: #D4AC0D;">
                                                                        <c:out value="${doc.nombre_archivo}" default="Documento" />
                                                                </div>
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                                                        </div>
                                                        <div class="w-100 text-center py-2 rounded text-white fw-bold small shadow-sm" style="background-color: ${colorCard};">
                                                                <c:out value="${doc.nombre_archivo}" />
                                                        </div>
                                                </div>

<<<<<<< HEAD
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

                                                                        <!-- Botones de Acción -->
                                                                        <div class="d-flex gap-2">
                                                                                <c:choose>
                                                                                        <c:when test="${puedeModificar}">
                                                                                                <!-- Botón Revisar / Pendiente -->
                                                                                                <button type="submit" name="accion" value="${doc.revisado ? 'desmarcar' : 'marcar'}" class="btn text-white fw-bold flex-grow-1" style="background-color: ${doc.revisado ? '#D4AC0D' : '#429983'};">
                                                                                                        <i class="bi ${doc.revisado ? 'bi-arrow-counterclockwise' : 'bi-check2-circle'} me-1"></i>
                                                                                                                ${doc.revisado ? 'Pendiente' : 'Revisar'}
                                                                                                </button>

                                                                                                <!-- Botón Editar Observación/Archivo -->
                                                                                                <button type="button" class="btn text-white fw-bold flex-grow-1" style="background-color: #345177;" onclick="editarDoc(${doc.id_tipo_doc})">
                                                                                                        <i class="bi bi-pencil-square me-1"></i> Editar
                                                                                                </button>

                                                                                                <!-- Botón Guardar cambios de edición (Oculto) -->
                                                                                                <button type="submit" id="btnGuardarDoc_${doc.id_tipo_doc}" class="btn btn-success fw-bold flex-grow-1 d-none">
                                                                                                        Guardar
                                                                                                </button>

                                                                                                <!-- Botón Eliminar (Conectado al formulario oculto) -->
                                                                                                <button type="submit" form="formEliminar_${doc.id_tipo_doc}" class="btn btn-danger fw-bold flex-grow-1" onclick="return confirm('¿Seguro que deseas eliminar este documento?');">
                                                                                                        Eliminar
                                                                                                </button>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                                <button type="button" class="btn btn-secondary fw-bold flex-grow-1 disabled">
                                                                                                        <i class="bi bi-lock-fill me-1"></i> Plazo vencido
                                                                                                </button>
                                                                                        </c:otherwise>
                                                                                </c:choose>

                                                                                <a href="servlet-ver-documento?idArchivo=${doc.id_archivo}" target="_blank" class="btn text-white fw-bold flex-grow-1" style="background-color: #002E60;">
                                                                                        Abrir archivo
                                                                                </a>
                                                                        </div>
                                                                </form> <!-- CIERRE DEL FORMULARIO PRINCIPAL -->

                                                                <!-- FORMULARIO INDEPENDIENTE PARA ELIMINAR -->
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
=======
                                                        <div class="col-9 ps-4 d-flex flex-column justify-content-between">
                                                                <c:choose>
                                                                        <c:when test="${not empty sessionScope.adminLogueado || not empty sessionScope.docenteLogueado || (doc.tieneCalendario && doc.puedeSubir)}">
                                                                                <form action="${pageContext.request.contextPath}/servlet-subir-documentos" method="POST" enctype="multipart/form-data" class="d-flex flex-column h-100 justify-content-between m-0">

>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                                                                                        <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                                                        <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                                        <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                                        <input type="hidden" name="estado" value="Completado">

                                                                                        <label class="fw-bold mb-1">Observaciones</label>
                                                                                        <textarea name="observaciones" class="form-control mb-2" rows="2" style="resize: none;" placeholder="Comentario opcional..."></textarea>

                                                                                        <div class="d-flex justify-content-between small text-muted fw-bold mb-2">
                                                                                                <span>Fecha de entrega: <c:out value="${doc.fecha_limite}" default="Sin asignar" /></span>
                                                                                                <c:if test="${esAdmin && estaVencido}">
                                                                                                        <span class="text-danger"><i class="bi bi-shield-lock"></i> Plazo vencido (Permiso Admin)</span>
                                                                                                </c:if>
                                                                                        </div>

                                                                                        <div class="input-group shadow-sm">
                                                                                                <input type="file" name="archivoPDF" class="form-control" accept=".pdf" required>
                                                                                                <button type="submit" class="btn text-white fw-bold px-4" style="background-color: #429983;">
                                                                                                        Subir
                                                                                                </button>
                                                                                        </div>
                                                                                </form>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                                <div>
                                                                                        <label class="fw-bold mb-1">Observaciones</label>
                                                                                        <textarea class="form-control mb-2 bg-white" rows="2" style="resize: none;" readonly placeholder="Sin observaciones"></textarea>
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
<!-- JavaScript básico -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
        // Activar/desactivar edición del formulario de estudiante
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
        }

        // Activar edición de un documento específico
        function editarDoc(id) {
                document.getElementById('obs_' + id).removeAttribute('readonly');
                document.getElementById('campoReemplazar_' + id).classList.remove('d-none');
                document.getElementById('btnGuardarDoc_' + id).classList.remove('d-none');
=======
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
        const textoOriginal = {};

        function activarEdicionPerfil() {
                const campos = document.querySelectorAll('.campo-estudiante');
                campos.forEach(campo => {
                        campo.removeAttribute('disabled');
                        campo.removeAttribute('readonly');
                        campo.classList.remove('input-gris');
                        campo.classList.add('input-edicion');
                });

                document.getElementById('inputDocenteLectura').classList.add('d-none');
                document.getElementById('selectDocenteEdicion').classList.remove('d-none');

                document.getElementById('btnEditarPerfil').classList.add('d-none');
                document.getElementById('btnCancelarPerfil').classList.remove('d-none');
                document.getElementById('btnGuardarPerfil').classList.remove('d-none');
        }

        function cancelarEdicionPerfil() {
                const campos = document.querySelectorAll('.campo-estudiante');
                campos.forEach(campo => {
                        campo.setAttribute('disabled', true);
                        campo.setAttribute('readonly', true);
                        campo.classList.add('input-gris');
                        campo.classList.remove('input-edicion');
                });

                document.getElementById('inputDocenteLectura').classList.remove('d-none');
                document.getElementById('selectDocenteEdicion').classList.add('d-none');

                document.getElementById('btnEditarPerfil').classList.remove('d-none');
                document.getElementById('btnCancelarPerfil').classList.add('d-none');
                document.getElementById('btnGuardarPerfil').classList.add('d-none');
        }

        function activarEdicion(id) {
                const textarea = document.getElementById('obs_' + id);
                textoOriginal[id] = textarea.value;

                textarea.removeAttribute('readonly');
                document.getElementById('vista_icono_' + id).classList.add('d-none');
                document.getElementById('vista_subir_' + id).classList.remove('d-none');
                document.getElementById('botones_vista_' + id).classList.add('d-none');
                document.getElementById('botones_edicion_' + id).classList.remove('d-none');
        }

        function cancelarEdicion(id) {
                const textarea = document.getElementById('obs_' + id);
                if (textoOriginal[id] !== undefined) {
                        textarea.value = textoOriginal[id];
                }

                textarea.setAttribute('readonly', true);
                document.getElementById('vista_icono_' + id).classList.remove('d-none');
                document.getElementById('vista_subir_' + id).classList.add('d-none');
                document.getElementById('botones_vista_' + id).classList.remove('d-none');
                document.getElementById('botones_edicion_' + id).classList.add('d-none');
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
        }
</script>
</body>
</html>