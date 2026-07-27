<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<form action="${pageContext.request.contextPath}/servlet-inicio" method="POST">
    <div class="container min-vh-100 d-flex align-items-center justify-content-center py-4">

        <!-- Ancho de la tarjeta en pantalla -->
        <div class="col-12 col-md-8 col-lg-6">

            <!-- LOGO: Controlamos la altura para que no se vea gigante -->
            <div class="text-center mb-3">
                <img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png"
                     class="img-fluid"
                     alt="Logo UTEZ"
                     style="width: 55%; max-width: 260px;">
            </div>

            <!-- TARJETA UNIFICADA (Fondo blanco, sombra y borde) -->
            <div class="bg-white shadow-sm border rounded overflow-hidden">

                <!-- Encabezado Azul pegado arriba -->
                <div class="p-3 text-white text-center" style="background-color: #002E60;">
                    <h2 class="h3 m-0 fw-bold">Iniciar Sesión</h2>
                </div>

                <!-- Contenido interno con espacio uniforme (p-4) -->
                <div class="p-4">

                    <!-- Mensaje de error -->
                    <c:if test="${not empty mensajeError}">
                        <div class="alert alert-danger text-center py-2 mb-3" role="alert">
                                ${mensajeError}
                        </div>
                    </c:if>

                    <!-- Campo Usuario -->
                    <div class="mb-3">
                        <label class="form-label fw-bold">Usuario</label>
                        <input class="form-control" type="email" name="correoUsuario" placeholder="Ingresa tu usuario" required>
                    </div>

                    <!-- Campo Contraseña -->
                    <div class="mb-3">
                        <label class="form-label fw-bold">Contraseña</label>
                        <input class="form-control" type="password" name="password" placeholder="Ingresa tu contraseña" minlength="8" required>
                    </div>

                    <!-- Olvidaste contraseña -->
                    <div class="mb-4">
                        <a href="servlet-restablecer" class="text-decoration-none">¿Olvidaste tu contraseña?</a>
                    </div>

                    <div class="text-center mb-3">
                        <button class="btn text-white fw-bold w-100" style="background-color: #429983;" type="submit">Iniciar</button>
                    </div>

                    <!-- Botón pruebas (opcional) -->
                    <div class="text-center">
                        <a class="btn btn-sm btn-outline-secondary" href="test-vista.jsp">Pruebas</a>
                    </div>

                </div>
            </div>

        </div>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>