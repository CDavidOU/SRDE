<%--
  Created by IntelliJ IDEA.
  User: jaca8
  Date: 7/29/2026
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card shadow-sm border-0 m-4">
        <div class="card-body">

                <!-- Buscador de Estudiantes -->
                <form action="${pageContext.request.contextPath}/servlet-lista-estudiantes" method="GET" class="mb-4">
                        <div class="input-group">
                <span class="input-group-text bg-white border-end-0">
                    <i class="bi bi-search"></i>
                </span>
                                <input type="text" name="buscar" class="form-control border-start-0" placeholder="Buscar por nombre, apellido o matrícula...">
                                <button type="submit" class="btn btn-success px-4 fw-bold">Buscar</button>
                        </div>
                </form>

                <!-- Tabla de Estudiantes -->
                <div class="table-responsive">
                        <table class="table table-hover align-middle">
                                <tbody>
                                <!-- Iterador de Estudiantes -->
                                <c:forEach var="estudiante" items="${listaEstudiantes}">
                                        <tr>
                                                <!-- Matrícula del Estudiante -->
                                                <td class="fw-bold">${estudiante.matricula}</td>

                                                <!-- Nombre Completo del Estudiante -->
                                                <td>${estudiante.nombre} ${estudiante.apellido}</td>

                                                <!-- Botón de Acciones / Detalles (Solo uno para evitar duplicados) -->
                                                <td class="text-end">
                                                        <a href="${pageContext.request.contextPath}/servlet-detalles-estudiante?matricula=${estudiante.matricula}"
                                                           class="btn text-white fw-bold shadow-sm" style="background-color: #002E60;">
                                                                Detalles
                                                        </a>
                                                </td>

                                                <!-- Ícono de Ordenamiento / Estado -->
                                                <td class="text-center" style="width: 40px;">
                                                        <a href="#" class="text-secondary">
                                                                <i class="bi bi-arrow-down-up"></i>
                                                        </a>
                                                </td>
                                        </tr>
                                </c:forEach>
                                </tbody>
                        </table>
                </div>
        </div>
</div>