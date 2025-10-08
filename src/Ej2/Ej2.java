package Ej2;

import java.sql.*;
import java.util.Scanner;

public class Ej2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String user = "postgres";
        String password = "abc123.";
        String url = "jdbc:postgresql://localhost:5432/f1";
        try {
            Connection connection= DriverManager.getConnection(url,user,password);
            System.out.println("empecemos con la f1");
            int opcion=10000;
            while (opcion!=0){
                System.out.println("1 Listar todos los equipos y sus directores\n" +
                        "2 Obtener los pilotos y sus equipos actuales\n" +
                        "3 Resultados de una carrera específica\n" +
                        "4 Piloto más viejo\n" +
                        "5 Número de victorias por equipo\n" +
                        "0 Cerrar");
                opcion= scanner.nextInt();
                scanner.nextLine();
                switch (opcion){
                    case 0:
                        System.out.println("adios");
                        connection.close();
                        break;
                    case 1:
                        String listarSql="select * from equipos";
                        Statement statement= connection.createStatement();
                        ResultSet resultSet=statement.executeQuery(listarSql);
                        while (resultSet.next()){
                            String nombreEquipo= resultSet.getString("nombre");
                            String sede=resultSet.getString("sede");
                            String directorEquipos=resultSet.getString("director");
                            System.out.println("equipo: " + nombreEquipo + " sede: " + sede + " director: " + directorEquipos);
                        }
                        break;
                    case 2:
                        String listarPilotosXEquipos = "SELECT pilotos.nombre AS \"Piloto\", equipos.nombre AS \"Equipo\""
                                + "FROM pilotos "
                                + "INNER JOIN equipos ON pilotos.equipo_id = equipos.equipo_id;";
                        Statement statement1= connection.createStatement();
                        ResultSet resultSet1=statement1.executeQuery(listarPilotosXEquipos);
                        while (resultSet1.next()){
                            String piloto = resultSet1.getString("Piloto");
                            String equipo = resultSet1.getString("Equipo");
                            System.out.println(equipo + "\t" + piloto);
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("error en los datos insertados");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar");
        }
    }
}
