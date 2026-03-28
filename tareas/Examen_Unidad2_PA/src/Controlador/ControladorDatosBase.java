package Controlador;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ControladorDatosBase {

    private final String ruta = "Datosbase.xlsx";

   
    private String limpiar(Object valor) {
        if (valor == null) return "";
        return valor.toString().trim().toUpperCase();
    }

    public ArrayList<String> obtenerProfesores() {

        ArrayList<String> lista = new ArrayList<>();

        try (Workbook wb = new XSSFWorkbook(new FileInputStream(ruta))) {

            Sheet hoja = wb.getSheet("ListasAsistencia");

            for (Row fila : hoja) {

                if (fila.getRowNum() == 0) continue;

                Cell c = fila.getCell(1); 

                if (c != null) {

                    String nombre = limpiar(c);

                    if (!nombre.isEmpty() && !lista.contains(nombre)) {
                        lista.add(nombre);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

  
    public ArrayList<String> obtenerAsignaturasPorProfesor(String profesor) {

        ArrayList<String> lista = new ArrayList<>();

        try (Workbook wb = new XSSFWorkbook(new FileInputStream(ruta))) {

            Sheet hoja = wb.getSheet("ListasAsistencia");

            for (Row fila : hoja) {

                if (fila.getRowNum() == 0) continue;

                String profExcel = limpiar(fila.getCell(1));
                String matExcel = limpiar(fila.getCell(2));

                if (profExcel.equals(limpiar(profesor))) {

                    if (!matExcel.isEmpty() && !lista.contains(matExcel)) {
                        lista.add(matExcel);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public ArrayList<String> obtenerGrupos(String profesor, String materia) {

        ArrayList<String> lista = new ArrayList<>();

        try (Workbook wb = new XSSFWorkbook(new FileInputStream(ruta))) {

            Sheet hoja = wb.getSheet("ListasAsistencia");

            for (Row fila : hoja) {

                if (fila.getRowNum() == 0) continue;

                String profExcel = limpiar(fila.getCell(1));
                String matExcel = limpiar(fila.getCell(2));
                String grupoExcel = limpiar(fila.getCell(0));

                if (profExcel.equals(limpiar(profesor)) &&
                    matExcel.equals(limpiar(materia))) {

                    if (!grupoExcel.isEmpty() && !lista.contains(grupoExcel)) {
                        lista.add(grupoExcel);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public ArrayList<String> obtenerAlumnos(String profesor, String materia, String grupo) {

        ArrayList<String> lista = new ArrayList<>();

        try (Workbook wb = new XSSFWorkbook(new FileInputStream(ruta))) {

            Sheet hoja = wb.getSheet("ListasAsistencia");

            for (Row fila : hoja) {

                if (fila.getRowNum() == 0) continue;

                String profExcel = limpiar(fila.getCell(1));
                String matExcel = limpiar(fila.getCell(2));
                String grupoExcel = limpiar(fila.getCell(0));
                String alumno = limpiar(fila.getCell(4)); 

                if (profExcel.equals(limpiar(profesor)) &&
                    matExcel.equals(limpiar(materia)) &&
                    grupoExcel.equals(limpiar(grupo))) {

                    if (!alumno.isEmpty()) {
                        lista.add(alumno);
                        System.out.println("Alumno encontrado: " + alumno); 
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}