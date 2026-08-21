package mx.edu.utez.pres.srde.util;

import java.util.regex.Pattern;

public class Validador {

    // 1. Solo letras (con acentos y ñ), espacios (2 a 50 caracteres)
    private static final Pattern PATRON_NOMBRE =
            Pattern.compile("^[a-zA-ZÁÉÍÓÚáéíóúÑñüÜ\\s]{2,50}$");

    // 2. Alfanumérico sin espacios (Matrículas: 5 a 15 caracteres)
    private static final Pattern PATRON_MATRICULA =
            Pattern.compile("^[a-zA-Z0-9]{5,15}$");

    // 3. Formato estándar de correo institucional o personal
    private static final Pattern PATRON_CORREO =
            Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    // ==========================================
    // MÉTODOS DE VALIDACIÓN
    // ==========================================

    public static boolean esNombreValido(String texto) {
        return texto != null && PATRON_NOMBRE.matcher(texto.trim()).matches();
    }

    public static boolean esMatriculaValida(String texto) {
        return texto != null && PATRON_MATRICULA.matcher(texto.trim()).matches();
    }

    public static boolean esCorreoValido(String texto) {
        return texto != null && PATRON_CORREO.matcher(texto.trim()).matches();
    }

    // 4. Limpieza y validación de teléfono (10 dígitos exactos)
    public static String procesarTelefono(String input) {
        if (input == null) return null;

        String soloNumeros = input.replaceAll("[^0-9]", "");

        if (soloNumeros.matches("^[0-9]{10}$")) {
            return soloNumeros;
        }
        return null;
    }

    // 5. Sanitizador para textos libres (Observaciones / Comentarios)
    // Reemplaza caracteres peligrosos de scripts HTML por entidades seguras
    public static String sanitizarTextoLibre(String input) {
        if (input == null) return "";

        return input.trim()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}