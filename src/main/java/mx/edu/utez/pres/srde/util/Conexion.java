package mx.edu.utez.pres.srde.util;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class Conexion {
    public static Connection getConexion() {
        Connection con = null;
        Properties props = new Properties();
        try {
            // 1. Leer las credenciales del archivo properties
            try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream("application.properties")) {
                if (input == null) {
                    System.out.println("Error: No se encontró application.properties");
                    return null;
                }
                props.load(input);
            }
            // 2. Resolver la ruta absoluta de la carpeta wallet en el ClassPath
            URL walletFolderUrl = Conexion.class.getClassLoader().getResource("Wallet_SRDE");
            if (walletFolderUrl == null) {
                System.out.println("Error: Carpeta 'wallet' no encontrada en resources");
                return null;
            }
            String absoluteWalletPath = Paths.get(walletFolderUrl.toURI()).toAbsolutePath().toString();
            // 3. Inicializar el Driver
            Class.forName("oracle.jdbc.OracleDriver");
            // 4. Inyectar propiedades de red y seguridad a JDBC
            Properties jdbcProps = new Properties();
            jdbcProps.setProperty("user", props.getProperty("db.user"));
            jdbcProps.setProperty("password", props.getProperty("db.password"));
            jdbcProps.setProperty("oracle.net.tns_admin", absoluteWalletPath); // Configura la Wallet
            // 5. Conectar mediante el alias del servicio
            String url = "jdbc:oracle:thin:@" + props.getProperty("db.service");
            con = DriverManager.getConnection(url, jdbcProps);
            System.out.println("¡Conexión segura y portable establecida con éxito!");
        } catch (Exception e) {
            System.out.println("Error crítico en la conexión: " + e.getMessage());
        }
        return con;
    }
}