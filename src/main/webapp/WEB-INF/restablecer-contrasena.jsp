<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Contraseña</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<form action="${pageContext.request.contextPath}/servlet-restablecer" method="POST">
    <div class="container min-vh-100 d-flex align-items-center justify-content-center py-4">
        <div class="col-12 col-md-8 col-lg-6">

            <div class="text-center mb-3">
                <img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png" class="img-fluid" alt="Logo UTEZ" style="width: 55%; max-width: 260px;">
            </div>

            <div class="bg-white shadow-sm border rounded overflow-hidden">
                <div class="p-3 text-white text-center" style="background-color: #002E60;">
                    <h2 class="h3 m-0 fw-bold">Restablecer Contraseña</h2>
                </div>

                <div class="p-4">
                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger text-center py-2 mb-3" role="alert">
                            <c:out value="${mensajeError}" />
                        </div>
                    </c:if>
                    <c:if test="${not empty mensajeExito}">
                        <div class="alert alert-success text-center py-2 mb-3" role="alert">
                            <c:out value="${mensajeExito}" />
                        </div>
                    </c:if>

                    <p class="text-secondary text-center mb-4">
                        Hemos enviado un código a tu correo. Ingrésalo a continuación junto con tu nueva contraseña.
                    </p>

                    <!-- Correo bloqueado (viene del servlet anterior) -->
                    <div class="mb-3">
                        <label class="form-label fw-bold text-muted">Correo vinculado</label>
                        <input class="form-control bg-light text-muted" type="email" name="correo" value="${correo}" readonly required>
                    </div>

                    <!-- Input para el PIN con formato centrado -->
                    <div class="mb-3">
                        <label class="form-label fw-bold">PIN de recuperación</label>
                        <input class="form-control text-center fs-4 tracking-widest fw-bold" type="text" name="token" placeholder="----" maxlength="4" pattern="\d{4}" title="Debe ser un código de 4 dígitos" autocomplete="off" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Nueva contraseña</label>
                        <input class="form-control" type="password" name="nuevaContrasena" placeholder="Ingresa la nueva contraseña" minlength="8" required>
                    </div>

                    <div class="mb-4">
                        <label class="form-label fw-bold">Confirmar contraseña</label>
                        <input class="form-control" type="password" name="confirmarContrasena" placeholder="Confirma la nueva contraseña" minlength="8" required>
                    </div>

                    <div class="text-center mb-3">
                        <button class="btn text-white fw-bold w-100" style="background-color: #429983;" type="submit">Cambiar Contraseña</button>
                    </div>

                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/index.jsp" class="text-decoration-none">Cancelar y volver</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>