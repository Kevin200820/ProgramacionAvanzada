package Main;

import Modelo.BaseDatos;
import Modelo.ConexionBD;
import Modelo.Configurador;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE MATERIAS ===");

        // 1. Cargar configuración desde el XML
        Map<String, String> conf = Configurador.cargarConfiguracion();
        Connection con = null;

        try {
            String motor = conf.get("motor");
            if (motor == null) {
                throw new Exception("No se pudo obtener el motor del archivo XML. Verifica que configurador.xml esté en la raíz.");
            }

            System.out.println("Conectando a: " + motor.toUpperCase() + "...");

            // 2. Selección del motor de base de datos
            if (motor.equalsIgnoreCase("mysql")) {
                con = ConexionBD.conectarMySQL(conf.get("host"), conf.get("db"), conf.get("user"), conf.get("pass"));
            } else if (motor.equalsIgnoreCase("sqlserver")) {
                // CORRECCIÓN: Se envía el puerto 1433 en lugar de 0 para evitar el SocketTimeoutException
                con = ConexionBD.conectarSQLServer(conf.get("host"), 1433, conf.get("db"), conf.get("user"), conf.get("pass"));
            }

            // 3. Ejecución de la consulta si la conexión fue exitosa
            if (con != null) {
                System.out.println("¡Conexión exitosa!");
                BaseDatos bd = new BaseDatos(con);
                
                // Consultamos la tabla 'Asignatura' que ya tienes creada en SSMS
                ArrayList<String[]> listaMaterias = bd.consultar("Asignatura", null, null);

                System.out.println("\nLISTADO DE MATERIAS ENCONTRADAS:");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("CARRERA | PLAN | ID MATERIA | NOMBRE DE MATERIA");
                System.out.println("----------------------------------------------------------------------");
                
                for (String[] fila : listaMaterias) {
                    // Imprimimos las 4 columnas: Carrera, Plan_Estudio, IdMateria, Materia
                    System.out.println(fila[0] + " | " + fila[1] + " | " + fila[2] + " | " + fila[3]);
                }
                System.out.println("----------------------------------------------------------------------");
            }

        } catch (Exception e) {
            // Aquí se capturan errores como "Login failed" o "Timeout"
            System.err.println("Fallo en la ejecución: " + e.getMessage());
        } finally {
            // Cerramos la conexión siempre al terminar
            ConexionBD.cerrar(con);
        }
    }
}<ñ>