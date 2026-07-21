<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesion</title>
</head>
<body>

<form method="POST" action="/login">
    <label>Usuario</label>
    <input type="text" name="usuario" min="2" required><br>
    <label>Contrasena</label>
    <input type="password" name="contrasena" min="8" required>
    <a href="servlet-restablecer">Olvidaste tu contrasena?</a>
    <a href="servlet-inicio">Iniciar</a>
</form>
<br/>
</body>
</html>