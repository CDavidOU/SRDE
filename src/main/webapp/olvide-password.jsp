<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar Contraseña</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<form action="${pageContext.request.contextPath}/servlet-enviar-pin" method="POST">
    <div class="container min-vh-100 d-flex align-items-center justify-content-center py-4">
        <div class="col-12 col-md-8 col-lg-6">

            <div class="text-center mb-3">
                <img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png" class="img-fluid" alt="Logo UTEZ" style="width: 55%; max-width: 260px;">
            </div>

            <div class="bg-white shadow-sm border rounded overflow-hidden">
                <div class="p-3 text-white text-center" style="background-color: #002E60;">
                    <h2 class="h3 m-0 fw-bold">Recuperar Contraseña</h2>
                </div>

                <div class="p-4">
                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger text-center py-2 mb-3" role="alert">
                            <c:out value="${mensajeError}" />
                        </div>
                    </c:if>

                    <p class="text-secondary text-center mb-4">
                        Ingresa tu correo institucional y te enviaremos un <b>PIN de 4 dígitos</b> para recuperar tu acceso.
                    </p>

                    <div class="mb-4">
                        <label class="form-label fw-bold">Correo Institucional</label>
                        <input class="form-control" type="email" name="correo" placeholder="ejemplo@utez.edu.mx" required>
                    </div>

                    <div class="text-center mb-3">
                        <button class="btn text-white fw-bold w-100" style="background-color: #429983;" type="submit">Enviar PIN</button>
                    </div>

                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/index.jsp" class="text-decoration-none">Volver a iniciar sesión</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>