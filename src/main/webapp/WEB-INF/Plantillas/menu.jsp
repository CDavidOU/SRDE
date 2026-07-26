<%--
  Created by IntelliJ IDEA.
  User: Carlos
  Date: 25/07/2026
  Time: 08:51 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<img src="${pageContext.request.contextPath}/imagenes/UtezLogo.png" class="img-thumbnail border-0" alt="Logo" style="height: 120px; width: 100%; object-fit: contain;">
<div class="list-group">
    <a href="${pageContext.request.contextPath}/servlet-inicio" class="list-group-item list-group-item-action active d-flex align-items-center" aria-current="true">

        <img src="${pageContext.request.contextPath}/imagenes/user.png" class="rounded me-3" alt="Icono usuario" style="width: 40px; height: 40px;">

        <div class="d-flex flex-column text-start w-100">
            <span class="fw-bold" style="font-size: 0.9rem;">Docente</span>
            <input type="text" value="${sessionScope.docenteLogueado.nombre}" class="form-control form-control-sm p-0 text-white bg-transparent border-0" disabled>
        </div>

    </a>

    <a href="#" class="list-group-item list-group-item-action">Periodos</a>
    <a href="#" class="list-group-item list-group-item-action">Estudiantes</a>
    <a href="${pageContext.request.contextPath}/index.jsp" class="list-group-item list-group-item-action">Salir</a>
    <a href="#" class="list-group-item list-group-item-action">Notificaciones</a>
</div>