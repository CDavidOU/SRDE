<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambio de Contraseña</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body class="bg-light">

<div class="d-flex">

    <!-- 1. Menu lateral -->
    <div id="menu" class="bg-white border-end min-vh-100" style="width: 180px;">
        <jsp:include page="/WEB-INF/Plantillas/menu.jsp" />
    </div>

    <!-- 2. Contenido de la derecha -->
    <div class="w-100">

        <!-- Banner Azul del titulo -->
        <div class="text-center mb-5">
            <h1 class="m-0 text-white" style="background: #002E60;padding: 10px 0;">Cambiar Contraseña</h1>
        </div>

        <!-- Formulario centrado -->
        <form action="${pageContext.request.contextPath}/servlet-cambiar-contra" method="post">
            <div class="row justify-content-center">
                <div class="col-5">

                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger text-center py-2 mb-3" role="alert">
                                ${mensajeError}
                        </div>
                    </c:if>

                    <div class="mb-3">
                        <label class="fw-bold mb-1 fs-5">Contraseña actual:</label>
                        <input class="form-control" type="password" name="password" placeholder="Ingresa tu contraseña"/>
                    </div>

                    <div class="mb-3">
                        <label class="fw-bold mb-1 fs-5">Cambiar contraseña:</label>
                        <input class="form-control" type="password" name="newPassword" placeholder="Ingresa la nueva contraseña"/>
                    </div>

                    <div class="mb-4">
                        <label class="fw-bold mb-1 fs-5">Confirmar contraseña:</label>
                        <input class="form-control" type="password" name="confirmPassword" placeholder="Ingresa nuevamente la contraseña"/>
                    </div>

                    <div class="text-center">
                        <button class="btn text-white fw-bold px-4" style="background-color: #429983;" type="submit">Confirmar</button>
                    </div>

                </div>
            </div>
        </form>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>