package Ej1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class mainEj1 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        String user = "postgres";
        String password = "abc123.";
        String url = "jdbc:postgresql://localhost:5432/";
        String databaseNombre = "listaLibros";

        Connection connection = DriverManager.getConnection(url, user, password);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop database " + databaseNombre);
            System.out.println("tirada");
        } catch (SQLException e) {
            System.out.println("no se puede tirar la bd");
        }
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create database " + databaseNombre + " ;");
            System.out.println("exito");
        } catch (SQLException e) {
            System.out.println("no se creo las BD de forma correcta");
        }
        try {
            Statement statement1= connection.createStatement();
            statement1.executeUpdate("CREATE SCHEMA objetos");
            statement1.executeUpdate("CREATE TYPE objetos.tipo_autor AS (nombre_autor varchar(255), fechaNacimiento varchar(100))");
            System.out.println("creada la tabla autor");
            Statement statement2= connection.createStatement();
            statement2.executeUpdate("CREATE TABLE objetos.libros (id serial PRIMARY KEY, titulo VARCHAR, autor objetos.tipo_autor, año_publicacion integer)");
            System.out.println("Creados los libros");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Los esquemas ya estaban creados");
        }
        int opcion=1000;
        while (opcion != 0) {
            System.out.println("1 insertar");
            System.out.println("2 listar");
            System.out.println("3 actualizar");
            System.out.println("4 eliminar");
            System.out.println("0 salir");
            opcion= scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("adios");
                    break;
                default:
                    System.out.println("numero incorrecto");
                    break;
            }
        }


    }
}
