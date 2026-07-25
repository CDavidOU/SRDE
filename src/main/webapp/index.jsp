<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesion</title>
</head>
<body>

<form method="POST" action="servlet-inicio">
    <p style="color: red">${mensajeError}</p>
    <label>Usuario</label>
    <input type="email" name="correoUsuario" required><br>
    <label>Contrasena</label>
    <input type="password" name="password" minlength="8" required>
    <a href="servlet-restablecer">Olvidaste tu contrasena?</a>
    <button type="submit">Iniciar</button>
</form>
</body>
</html>