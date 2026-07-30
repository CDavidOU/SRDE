<%--
  Created by IntelliJ IDEA.
  User: car15
  Date: 29/07/2026
  Time: 08:40 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Periodo</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
        .accordion-button:not(.collapsed) {
            background-color: transparent;
            color: inherit;
            box-shadow: none;
        }
        .accordion-button:focus {
            box-shadow: none;
        }
    </style>
</head>
<body>

<div id="contenido" class="d-flex min-vh-100">

    <!-- Menú Lateral -->
    <div id="menu" class="bg-white border-end" style="width: 180px; flex-shrink: 0;">
        <jsp:include page="../Plantillas/menu.jsp" />
    </div>

    <!-- Contenido Principal -->
    <div id="cambiantes" class="flex-grow-1 d-flex flex-column bg-light">

        <!-- Encabezado -->
        <div class="w-100 text-center mb-4">
            <h1 class="text-white m-0 py-3 fs-2 fw-semibold" style="background-color: #002E60;">Periodo</h1>
        </div>

        <div id="datos" class="p-4 flex-grow-1 d-flex justify-content-center">

            <!-- Contenedor principal alineado -->
            <div class="w-100" style="max-width: 850px;">

                <!-- Buscador superior -->
                <div class="row g-2 mb-4 justify-content-center">
                    <div class="col-9">
                        <div class="input-group">
                            <span class="input-group-text bg-white border-end-0">
                                <i class="bi bi-search"></i>
                            </span>
                            <select class="form-select border-start-0 p-2" id="filtroPeriodo">
                                <option value="" selected disabled>Periodo:</option>
                                <option value="Mayo-Junio">Mayo-Junio</option>
                                <option value="Febrero-Marzo">Febrero-Marzo</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-3">
                        <button class="btn w-100 py-2 text-white fw-medium rounded-3" style="background-color: #429983;" type="button">
                            Buscar
                        </button>
                    </div>
                </div>

                <!-- Lista de Periodos (Acordeón) -->
                <div class="accordion d-flex flex-column gap-3" id="acordeonPeriodos">

                    <!-- PERIODO 1: MAYO-JUNIO (Desplegado) -->
                    <div class="card border border-secondary border-opacity-25 rounded-3 shadow-sm bg-white">

                        <!-- Cabecera del Periodo -->
                        <div class="card-header bg-white border-0 py-2 px-3 d-flex align-items-center justify-content-between">
                            <span class="fw-normal fs-5 text-dark">PERIODO : Mayo-Junio</span>
                            <button class="btn p-0 border-0 fs-4 text-dark" type="button" data-bs-toggle="collapse" data-bs-target="#periodoMayoJunio" aria-expanded="true">
                                <!-- Icono flecha hacia abajo -->
                                <i class="bi bi-chevron-down"></i>
                            </button>
                        </div>

                        <!-- Contenido Desplegable (Lista de Alumnos) -->
                        <div id="periodoMayoJunio" class="collapse show" data-bs-parent="#acordeonPeriodos">
                            <div class="card-body pt-0 px-3 pb-3 d-flex flex-column gap-2">

                                <!-- Alumno 1 -->
                                <div class="border border-secondary border-opacity-25 rounded-2 p-2 d-flex align-items-center justify-content-between bg-white">
                                    <span class="fs-5 text-secondary ps-2">Ismael Medina Villagomez</span>
                                    <div class="d-flex align-items-center gap-2">
                                        <button class="btn text-white px-3 py-1 d-flex align-items-center gap-2" style="background-color: #002E60;" type="button">
                                            Detalles <i class="bi bi-file-earmark-text-fill"></i>
                                        </button>
                                        <button class="btn btn-link text-dark p-0 fs-5"></button>
                                    </div>
                                </div>

                                <!-- Alumno 2 -->
                                <div class="border border-secondary border-opacity-25 rounded-2 p-2 d-flex align-items-center justify-content-between bg-white">
                                    <span class="fs-5 text-secondary ps-2">Carlos David Ortega Urias</span>
                                    <div class="d-flex align-items-center gap-2">
                                        <button class="btn text-white px-3 py-1 d-flex align-items-center gap-2" style="background-color: #002E60;" type="button">
                                            Detalles <i class="bi bi-file-earmark-text-fill"></i>
                                        </button>
                                        <button class="btn btn-link text-dark p-0 fs-5"></button>
                                    </div>
                                </div>

                                <!-- Alumno 3 -->
                                <div class="border border-secondary border-opacity-25 rounded-2 p-2 d-flex align-items-center justify-content-between bg-white">
                                    <span class="fs-5 text-secondary ps-2">Samantha Terrones Moreno</span>
                                    <div class="d-flex align-items-center gap-2">
                                        <button class="btn text-white px-3 py-1 d-flex align-items-center gap-2" style="background-color: #002E60;" type="button">
                                            Detalles <i class="bi bi-file-earmark-text-fill"></i>
                                        </button>
                                        <button class="btn btn-link text-dark p-0 fs-5"></button>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>

                    <!-- PERIODO 2: Febrero-Marzo (Cerrado por defecto) -->
                    <div class="card border border-secondary border-opacity-25 rounded-3 shadow-sm bg-white">

                        <!-- Cabecera del Periodo -->
                        <div class="card-header bg-white border-0 py-2 px-3 d-flex align-items-center justify-content-between">
                            <span class="fw-normal fs-5 text-dark">PERIODO : Febrero-Marzo</span>
                            <button class="btn p-0 border-0 fs-4 text-dark collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#periodoFebMar" aria-expanded="false">
                                <!-- Icono flecha hacia abajo -->
                                <i class="bi bi-chevron-down"></i>
                            </button>
                        </div>

                        <!-- Contenido Desplegable -->
                        <div id="periodoFebMar" class="collapse" data-bs-parent="#acordeonPeriodos">
                            <div class="card-body pt-0 px-3 pb-3 d-flex flex-column gap-2">

                                <div class="border border-secondary border-opacity-25 rounded-2 p-2 d-flex align-items-center justify-content-between bg-white">
                                    <span class="fs-5 text-secondary ps-2">Juan Perez Gomez</span>
                                    <div class="d-flex align-items-center gap-2">
                                        <button class="btn text-white px-3 py-1 d-flex align-items-center gap-2" style="background-color: #002E60;" type="button">
                                            Detalles <i class="bi bi-file-earmark-text-fill"></i>
                                        </button>
                                        <button class="btn btn-link text-dark p-0 fs-5"></button>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>

                </div>

            </div>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>