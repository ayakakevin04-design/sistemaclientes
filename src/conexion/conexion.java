/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class conexion {
    private static final String URL = "jdbc:postgresql://aws-0-us-east-2.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USUARIO = "postgres.cshdlluanoftgluhkmly";
    private static final String CLAVE = "-qP2h?U5UXwyk?i";
   
public static Connection conectar(){
    Connection cn = null;
    try {
        cn = DriverManager.getConnection(URL, USUARIO, CLAVE);
        System.out.println("Conexion exitosa");
    } catch (SQLException e) {
        System.out.println("Error:" + e.getMessage());
    }
    return cn;
}

public static boolean validarLogin(String usuario, String clave) {
    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
    try (Connection cn = conectar();
         java.sql.PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setString(1, usuario);
        ps.setString(2, clave);
        java.sql.ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        System.out.println("Error en login: " + e.getMessage());
        return false;
    }
}
}
