package Ej1;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class mainEj1 {
    public static void main(String[] args) throws SQLException {
        String user="postgres";
        String password="abc123.";
        String url="jdbc:postgresql://localhost:5432/";
        String databaseNombre = "listaLibros";

        Connection connection = DriverManager.getConnection(url,user,password);
        try {
            Statement statement=connection.createStatement();
            statement.executeQuery("drop database " + databaseNombre);
        } catch (SQLException e) {
            System.out.println("no se puede tirar la bd");
        }
        try {
            Statement statement= connection.createStatement();
            statement.executeQuery("create database " + databaseNombre + " ;");
            connection.close();
            System.out.println("exito");
        } catch (SQLException e) {
            System.out.println("no se creo ");
        }

    }
}
