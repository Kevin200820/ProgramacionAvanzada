package Controlador;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Modelo.Evaluacion;

import java.io.*;

public class ControladorExcel {

    private final String plantilla = "examen.xlsx";

    public void generarOActualizarExcel(Evaluacion ev) {

        String nombre = ev.getId() + ".xlsx";

        try {

            Workbook wb;
            File file = new File(nombre);

            if (file.exists()) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } else {
                wb = new XSSFWorkbook(new FileInputStream(plantilla));
            }

            Sheet hoja = wb.getSheet("ReporteProductoIntegrador");

            
            setCelda(hoja, 1, 1, ev.getAsignatura().getNombre());
            setCelda(hoja, 2, 1, ev.getProfesor().getNombre());
            setCelda(hoja, 3, 1, ev.getGrupo().getNombre());

            FileOutputStream fos = new FileOutputStream(nombre);
            wb.write(fos);
            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCelda(Sheet hoja, int fila, int columna, String valor) {

        Row row = hoja.getRow(fila);
        if (row == null) row = hoja.createRow(fila);

        Cell cell = row.getCell(columna);
        if (cell == null) cell = row.createCell(columna);

        cell.setCellValue(valor);
    }
}