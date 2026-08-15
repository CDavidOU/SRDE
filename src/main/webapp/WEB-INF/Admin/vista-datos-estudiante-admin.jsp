<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!doctype html>
<html lang="es">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalles de Estudiante - Administración</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
                                <!-- Título Centrado -->
                                <div class="col text-center">
                                        <h1 class="m-0 fs-2 fw-semibold">Perfil del Estudiante</h1>
                                </div>
                        </div>
                </div>

        <!-- Contenedor principal de información -->
        <div class="w-100 px-4 mx-auto" style="max-width: 950px;">
                <form action="${pageContext.request.contextPath}/servlet-modificar-estudiante" method="POST" id="formPerfilEstudiante">

                        <!-- La matrícula no debe editarse porque es la llave primaria, la enviamos oculta -->
                        <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">

                        <!-- Datos personales -->
                        <div class="row g-3 mb-3">
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Nombre(s):</label>
                                        <input class="form-control input-perfil bg-light" type="text" name="nombre" value="${datosEstudiante.nombre}" readonly required>
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Apellido(s):</label>
                                        <input class="form-control input-perfil bg-light" type="text" name="apellido" value="${datosEstudiante.apellido}" readonly required>
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Matrícula:</label>
                                        <input class="form-control bg-light" type="text" value="${datosEstudiante.matricula}" disabled>
                                        <!-- Se muestra disabled por seguridad visual, pero el valor real viaja en el input oculto de arriba -->
                                </div>
                        </div>

                        <!-- Carrera, Cuatrimestre y Grupo -->
                        <div class="row g-3 mb-3">
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Carrera:</label>
                                        <input class="form-control input-perfil bg-light" type="text" name="carrera" value="${datosEstudiante.carrera}" readonly required>
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Cuatrimestre:</label>
                                        <select class="form-select input-perfil bg-light" name="cuatrimestre" disabled required>
                                                <!-- Comparamos con enteros (6 y 11) porque en tu Bean es un int -->
                                                <option value="6" ${datosEstudiante.cuatrimestre == 6 ? 'selected' : ''}>6</option>
                                                <option value="11" ${datosEstudiante.cuatrimestre == 11 ? 'selected' : ''}>11</option>
                                        </select>
                                </div>
                                <div class="col-4">
                                        <label class="fw-bold fs-5 mb-1">Grupo:</label>
                                        <input class="form-control input-perfil bg-light" type="text" name="grupo" value="${datosEstudiante.grupo}" readonly required>
                                </div>
                        </div>

                        <!-- Correo y Estado -->
                        <div class="row g-3 mb-3">
                                <div class="col-6">
                                        <label class="fw-bold fs-5 mb-1">Correo:</label>
                                        <input class="form-control input-perfil bg-light" type="email" name="correo" value="${datosEstudiante.correo}" readonly required>
                                </div>
                                <div class="col-6">
                                        <label class="fw-bold fs-5 mb-1">Estado:</label>
                                        <select class="form-select input-perfil bg-light" name="estado" disabled required>
                                                <option value="Activo" ${datosEstudiante.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                                                <option value="Inactivo" ${datosEstudiante.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                                        </select>
                                </div>
                        </div>

                        <!-- Botones de Acción In-line -->
                        <div class="row mt-4 mb-2">
                                <div class="col-12 d-flex justify-content-end gap-2">
                                        <!-- VISTA MODO NORMAL -->
                                        <button type="button" id="btnEditarPerfil" class="btn text-white fw-bold shadow-sm px-4" onclick="activarEdicionPerfil()" style="background-color: #002E60">
                                                <i class="bi bi-pencil-square me-2"></i> Editar datos
                                        </button>

                                        <!-- VISTA MODO EDICIÓN (Ocultos por defecto) -->
                                        <button type="button" id="btnCancelarPerfil" class="btn btn-secondary fw-bold shadow-sm d-none" onclick="cancelarEdicionPerfil()">
                                                Cancelar
                                        </button>
                                        <button type="submit" id="btnGuardarPerfil" class="btn text-white fw-bold shadow-sm d-none" style="background-color: #429983;">
                                                <i class="bi bi-save me-1"></i> Guardar Cambios
                                        </button>
                                </div>
                        </div>
                </form>



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

                                                        <!-- FORMULARIO ÚNICO DE MODIFICACIÓN -->
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

                                                                                        <!-- PERMISOS ADMINISTRATIVOS: Ignora si venció la fecha o si no hay calendario -->
                                                                                        <c:choose>
                                                                                                <c:when test="${not empty sessionScope.adminLogueado || not empty sessionScope.docenteLogueado || (doc.tieneCalendario && doc.puedeSubir)}">
                                                                                                        <c:choose>
                                                                                                                <c:when test="${!doc.revisado}">
                                                                                                                        <button type="submit" name="accion" value="marcar" class="btn btn-success fw-bold shadow-sm flex-grow-1">
                                                                                                                                <i class="bi bi-check2-circle me-1"></i> Marcar Revisado
                                                                                                                        </button>
                                                                                                                </c:when>
                                                                                                                <c:otherwise>
                                                                                                                        <button type="submit" name="accion" value="desmarcar" class="btn fw-bold text-white shadow-sm flex-grow-1" style="background-color: #D4AC0D;">
                                                                                                                                <i class="bi bi-arrow-counterclockwise me-1"></i> Quitar Revisado
                                                                                                                        </button>
                                                                                                                </c:otherwise>
                                                                                                        </c:choose>

                                                                                                        <button type="button" class="btn fw-bold text-white shadow-sm flex-grow-1" style="background-color: #D4AC0D;" onclick="activarEdicion(${doc.id_tipo_doc})">
                                                                                                                <i class="bi bi-pencil-square me-1"></i> Editar
                                                                                                        </button>
                                                                                                        <button type="submit" form="formEliminar_${doc.id_tipo_doc}" class="btn btn-danger fw-bold shadow-sm flex-grow-1">
                                                                                                                Eliminar
                                                                                                        </button>
                                                                                                </c:when>

                                                                                                <c:otherwise>
                                                                                                        <!-- SOLO APLICA A ESTUDIANTES FUERA DE PLAZO -->
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

                                                        <!-- Formulario independiente solo para Eliminar -->
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
                                                                        <%-- Si es Admin o Docente, SIEMPRE puede subir el archivo a nombre del alumno --%>
                                                                        <c:when test="${not empty sessionScope.adminLogueado || not empty sessionScope.docenteLogueado || (doc.tieneCalendario && doc.puedeSubir)}">
                                                                                <form action="${pageContext.request.contextPath}/servlet-subir-documentos" method="POST" enctype="multipart/form-data" class="d-flex flex-column h-100 justify-content-between m-0">

                                                                                        <input type="hidden" name="matricula" value="${datosEstudiante.matricula}">
                                                                                        <input type="hidden" name="idAsignacion" value="${datosEstudiante.idAsignacion}">
                                                                                        <input type="hidden" name="idTipoDoc" value="${doc.id_tipo_doc}">
                                                                                        <input type="hidden" name="estado" value="Completado">

                                                                                        <div>
                                                                                                <label class="fw-bold fs-5 mb-1">Observaciones</label>
                                                                                                <textarea class="form-control mb-1" style="resize: none;" name="observaciones" rows="2" placeholder="Agrega un comentario..."></textarea>
                                                                                                <span class="text-muted small fw-bold">Fecha de entrega: <c:out value="${doc.fecha_limite}" default="Pendiente / Vencida" /></span>
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

        // Guardar los valores originales por si el usuario le da a "Cancelar"
        let valoresOriginales = {};

        function activarEdicionPerfil() {
                const inputs = document.querySelectorAll('.input-perfil');
                inputs.forEach(input => {
                        valoresOriginales[input.name] = input.value;
                        input.removeAttribute('readonly'); // Para los inputs de texto
                        input.removeAttribute('disabled'); // Para el select
                        input.classList.remove('bg-light');
                });

                document.getElementById('btnEditarPerfil').classList.add('d-none');
                document.getElementById('btnCancelarPerfil').classList.remove('d-none');
                document.getElementById('btnGuardarPerfil').classList.remove('d-none');
        }

        function cancelarEdicionPerfil() {
                const inputs = document.querySelectorAll('.input-perfil');
                inputs.forEach(input => {
                        if(valoresOriginales[input.name] !== undefined){
                                input.value = valoresOriginales[input.name];
                        }
                        input.setAttribute('readonly', 'true'); // Para los inputs de texto

                        // Si es el select, le volvemos a poner el disabled
                        if(input.tagName === 'SELECT') {
                                input.setAttribute('disabled', 'true');
                        }

                        input.classList.add('bg-light');
                });

                document.getElementById('btnEditarPerfil').classList.remove('d-none');
                document.getElementById('btnCancelarPerfil').classList.add('d-none');
                document.getElementById('btnGuardarPerfil').classList.add('d-none');
        }

</script>
</body>
</html>