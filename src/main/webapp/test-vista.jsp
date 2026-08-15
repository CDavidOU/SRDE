<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Redactar Notificación</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Iconos de Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<div class="d-flex">
    <!-- 1. Menú Lateral (Se mantiene igual que en tu vista de lista) -->
    <div id="menu" class="flex-shrink-0" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>

    <!-- 2. Área Principal -->
    <div class="flex-grow-1 bg-white">

        <!-- Cabecera / Header Superior -->
        <div class="w-100 text-center text-white py-3 fw-bold fs-3" style="background-color: #002B66;">
            Enviar Notificación a Docente
        </div>

        <!-- Contenedor del Formulario -->
        <div class="container my-5 px-5" style="max-width: 800px;">
            <div class="card shadow-sm border-0" style="background-color: #f8f9fa;">
                <div class="card-body p-4 p-md-5">

                    <form action="${pageContext.request.contextPath}/servlet-crear-notificacion" method="POST">

                        <!-- Destinatario -->
                        <div class="mb-4">
                            <label class="form-label fw-bold text-dark" style="color: #002B66;">
                                <i class="bi bi-person-badge-fill me-2"></i>Docente Destinatario
                            </label>
                            <select class="form-select border-secondary shadow-sm" name="idDocenteSelect" required>
                                <option value="" disabled selected>Selecciona al docente que recibirá el aviso...</option>
                                <c:forEach var="docente" items="${docentesDisponibles}">
                                    <option value="${docente.id}">${docente.nombre} ${docente.apellido}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="row">
                            <!-- Tipo de Documento -->
                            <div class="col-md-6 mb-4">
                                <label class="form-label fw-bold text-dark" style="color: #002B66;">
                                    <i class="bi bi-file-earmark-text-fill me-2"></i>Tipo de Documento
                                </label>
                                <select class="form-select border-secondary shadow-sm" name="tipoDoc" required>
                                    <option value="" disabled selected>Seleccionar documento...</option>
                                    <c:forEach var="tipo" items="${listaTiposDocs}">
                                        <option value="${tipo.id_tipo_doc}">${tipo.nombre_doc}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Fecha Límite -->
                            <div class="col-md-6 mb-4">
                                <label class="form-label fw-bold text-dark" style="color: #002B66;">
                                    <i class="bi bi-calendar-event-fill me-2"></i>Fecha Límite
                                </label>
                                <input type="date" class="form-control border-secondary shadow-sm" name="fechaLimite" required>
                            </div>
                        </div>

                        <!-- Instrucciones / Características (Comentario) -->
                        <div class="mb-4">
                            <label class="form-label fw-bold text-dark" style="color: #002B66;">
                                <i class="bi bi-chat-left-text-fill me-2"></i>Instrucciones y Características
                            </label>
                            <textarea class="form-control border-secondary shadow-sm" name="comentario" rows="5"
                                      placeholder="Ej. El documento debe entregarse firmado en tinta azul y con copias a color. Hora límite de entrega: 14:00 hrs." required></textarea>
                            <div class="form-text mt-2 text-muted">
                                <i class="bi bi-info-circle me-1"></i>
                                Especifica aquí la <strong>hora exacta</strong> y cualquier formato especial que requiera el documento.
                            </div>
                        </div>

                        <!-- Botones de Acción -->
                        <hr class="my-4">
                        <div class="d-flex justify-content-end gap-3">
                            <a href="${pageContext.request.contextPath}/servlet-notificaciones" class="btn btn-outline-secondary px-4 fw-semibold">
                                Cancelar
                            </a>
                            <button type="submit" class="btn text-white px-4 fw-semibold shadow-sm" style="background-color: #002B66;">
                                <i class="bi bi-send-fill me-2"></i>Enviar Notificación
                            </button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>