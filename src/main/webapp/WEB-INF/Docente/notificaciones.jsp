<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 8/1/2026
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Notificaciones</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Iconos de Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<div class="d-flex">
    <!-- 1. Menú Lateral -->
    <div id="menu" class="flex-shrink-0">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>
    <!-- 2. Área Principal -->
    <div class="flex-grow-1 bg-white">

        <!-- Cabecera / Header Superior -->
        <div class="w-100 text-center text-white py-3 fw-bold fs-3" style="background-color: #002B66;">
            Notificaciones
        </div>

        <!-- Contenedor de Contenido -->
        <div class="container my-5 px-5" style="max-width: 900px;">

            <!-- Mensaje si la lista está vacía -->
            <c:if test="${empty listaNotificaciones}">
                <div class="alert alert-info text-center fs-5 shadow-sm">
                    <em>${mensajeNoti}</em>
                </div>
            </c:if>

            <!-- Recorrido de Notificaciones -->
            <c:forEach items="${listaNotificaciones}" var="noti">
                <div class="card mb-3 border shadow-sm">
                    <div class="card-body d-flex align-items-center justify-content-between p-3">

                        <!-- Icono y Contenido -->
                        <div class="d-flex align-items-start">
                            <i class="bi bi-bell-fill fs-2 me-3 text-dark mt-1"></i>
                            <div>
                                <h5 class="fw-bold mb-1" style="color: #002B66;">Notificación</h5>
                                <p class="mb-1 text-muted fw-semibold">
                                    <i class="bi bi-calendar-event me-1"></i>Fecha límite: ${noti.fechaLimite}
                                </p>
                                <p class="mb-0 text-secondary fs-6">${noti.descripcion}</p>
                            </div>
                        </div>

                        <!-- Formulario para Eliminar/Archivar -->
                        <div>
                            <form method="POST" action="servlet-mostrar-notificaciones" class="m-0">
                                <input type="hidden" name="idNoti" value="${noti.idCalendario}">
                                <button type="submit"
                                        class="btn text-white px-3 py-2 fw-semibold"
                                        style="background-color: #C85252;"
                                        onclick="return confirm('¿Deseas archivar esta notificación?');">
                                    <i class="bi bi-trash3-fill me-1"></i> Eliminar
                                </button>
                            </form>
                        </div>

                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>

</body>
</html>