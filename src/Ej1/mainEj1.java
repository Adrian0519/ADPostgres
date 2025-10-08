package Ej1;


import java.sql.*;
import java.util.Scanner;

public class mainEj1 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String user = "postgres";
        String password = "abc123.";
        String url = "jdbc:postgresql://localhost:5432/";
        String databaseNombre = "listalibros";

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
            connection.close();
        } catch (SQLException e) {
            System.out.println("no se creo las BD de forma correcta");
        }
        Connection connection2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/listalibros", user, password);
        try {
            Statement statement1 = connection2.createStatement();
            statement1.executeUpdate("CREATE SCHEMA objetos");
            statement1.executeUpdate("CREATE TYPE objetos.tipo_autor AS (nombre_autor varchar(255), fechaNacimiento varchar(100))");
            System.out.println("creada la tabla autor");
            Statement statement2 = connection2.createStatement();
            statement2.executeUpdate("CREATE TABLE objetos.libros (id serial PRIMARY KEY, titulo VARCHAR, autor objetos.tipo_autor, año_publicacion integer)");
            System.out.println("Creados los libros");
            connection2.close();
        } catch (SQLException e) {
            System.out.println("Los esquemas ya estaban creados");
        }
        int opcion = 1000;
        while (opcion != 0) {
            System.out.println("1 insertar");
            System.out.println("2 listar");
            System.out.println("3 actualizar");
            System.out.println("4 eliminar");
            System.out.println("0 salir");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Nombre del autor");
                    String nombre = scanner.nextLine();
                    System.out.println("fecha del autor");
                    int fecha = scanner.nextInt();
                    scanner.nextLine();
                    Autor autor = new Autor(nombre, fecha);
                    System.out.println("titulo");
                    String titulo = scanner.nextLine();
                    System.out.println("año");
                    int año = scanner.nextInt();
                    try {
                        PreparedStatement preparedStatement = connection2.prepareStatement("INSERT INTO objetos.libros (titulo, autor, año_publicacion) VALUES(?, ROW(?, ?), ?)");
                        preparedStatement.setString(1, titulo);
                        preparedStatement.setString(2, autor.getNombre());
                        preparedStatement.setInt(3, autor.getFecha());
                        preparedStatement.setInt(4, año);
                        preparedStatement.executeUpdate();
                        System.out.println("insertado");
                    } catch (SQLException e) {
                        System.out.println("error en la inseccion");
                    }
                    break;
                case 2:
                    String sql = """
                            SELECT titulo, 
                            (autor).nombre_autor AS nombre_autor,
                            (autor).fechaNacimiento AS fechaNacimiento,
                            año_publicacion 
                            FROM objetos.libros
                            """;
                    PreparedStatement preparedStatement = connection2.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String titulo1 = resultSet.getString("titulo");
                        String nombreAutor = resultSet.getString("nombre_autor");
                        String fechaNacimiento = resultSet.getString("fechanacimiento"); // Es VARCHAR, no int
                        int añoPublicacion = resultSet.getInt("año_publicacion");

                        System.out.println("Título: " + titulo1 +
                                " | Autor: " + nombreAutor +
                                " | Nacimiento: " + fechaNacimiento +
                                " | Año publicación: " + añoPublicacion);
                    }
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
