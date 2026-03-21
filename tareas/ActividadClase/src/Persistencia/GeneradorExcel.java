package Persistencia;

import Modelo.ModeloVenta.VentaJSON;
import Modelo.Producto;
import Modelo.Ticket;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class GeneradorExcel {

   
    private CellStyle estiloTitulo(Workbook wb) {
        CellStyle s = wb.createCellStyle();
        Font f = wb.createFont();
        f.setBold(true); f.setFontHeightInPoints((short) 14);
        f.setColor(IndexedColors.DARK_RED.getIndex());
        s.setFont(f);
        return s;
    }

    private CellStyle estiloEncabezado(Workbook wb) {
        CellStyle s = wb.createCellStyle();
        Font f = wb.createFont();
        f.setBold(true); f.setColor(IndexedColors.WHITE.getIndex());
        s.setFont(f);
        s.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        s.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        s.setAlignment(HorizontalAlignment.CENTER);
        s.setBorderBottom(BorderStyle.THIN);
        s.setBorderTop(BorderStyle.THIN);
        return s;
    }

    private CellStyle estiloMoneda(Workbook wb) {
        CellStyle s = wb.createCellStyle();
        DataFormat df = wb.createDataFormat();
        s.setDataFormat(df.getFormat("$#,##0.00"));
        return s;
    }

    private CellStyle estiloAlternado(Workbook wb) {
        CellStyle s = wb.createCellStyle();
        s.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        s.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return s;
    }

    private void autoSize(Sheet sheet, int cols) {
        for (int i = 0; i < cols; i++) sheet.autoSizeColumn(i);
    }

    private void celda(Row row, int col, String val, CellStyle style) {
        Cell c = row.createCell(col);
        c.setCellValue(val != null ? val : "");
        if (style != null) c.setCellStyle(style);
    }

    private void celda(Row row, int col, double val, CellStyle style) {
        Cell c = row.createCell(col);
        c.setCellValue(val);
        if (style != null) c.setCellStyle(style);
    }

    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
    }

    public String generarListadoGeneral(List<Producto> productos) {
        new java.io.File("reportes").mkdirs();
        String filename = "reportes/listado_general_" + timestamp() + ".xlsx";

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Todos los Productos");

         
            Row rTitulo = sheet.createRow(0);
            Cell tCell  = rTitulo.createCell(0);
            tCell.setCellValue("LISTADO GENERAL DE PRODUCTOS — SURTI-TIENDA®");
            tCell.setCellStyle(estiloTitulo(wb));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

            
            Row rFecha = sheet.createRow(1);
            rFecha.createCell(0).setCellValue("Generado: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));

           
            String[] cols = {"ID", "Nombre", "Categoría", "Precio", "Estado", "Info Extra", "URL Imagen"};
            Row rHead = sheet.createRow(3);
            CellStyle enc = estiloEncabezado(wb);
            for (int i = 0; i < cols.length; i++) celda(rHead, i, cols[i], enc);

        
            CellStyle mon = estiloMoneda(wb);
            CellStyle alt = estiloAlternado(wb);
            int rowNum = 4;
            for (Producto p : productos) {
                Row row = sheet.createRow(rowNum);
                CellStyle fondo = (rowNum % 2 == 0) ? alt : null;
                celda(row, 0, p.getId(),          fondo);
                celda(row, 1, p.getNombre(),       fondo);
                celda(row, 2, p.getCategoria(),    fondo);
                celda(row, 3, p.getPrecio(),       mon);
                celda(row, 4, p.getEstado(),       fondo);
                celda(row, 5, p.getInfoExtra(),    fondo);
                celda(row, 6, p.getUrlImagen(),    fondo);
                rowNum++;
            }

     
            Row rTotal = sheet.createRow(rowNum + 1);
            Font fb = wb.createFont(); fb.setBold(true);
            CellStyle cs = wb.createCellStyle(); cs.setFont(fb);
            celda(rTotal, 1, "Total productos:", cs);
            celda(rTotal, 2, productos.size(),   cs);

            autoSize(sheet, 7);
            try (FileOutputStream fos = new FileOutputStream(filename)) { wb.write(fos); }
            System.out.println("Excel generado: " + filename);
            return filename;
        } catch (Exception e) {
            System.err.println("Error generando listado general: " + e.getMessage());
            return null;
        }
    }

    public String generarListadoPorCategoria(List<Producto> productos) {
        new java.io.File("reportes").mkdirs();
        String filename = "reportes/listado_categorias_" + timestamp() + ".xlsx";

        Map<String, List<Producto>> porCat = productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria));

        try (Workbook wb = new XSSFWorkbook()) {
            CellStyle enc = estiloEncabezado(wb);
            CellStyle mon = estiloMoneda(wb);
            CellStyle alt = estiloAlternado(wb);

            
            Sheet resumen = wb.createSheet("Resumen");
            Row rH = resumen.createRow(0);
            celda(rH, 0, "Categoría",        enc);
            celda(rH, 1, "# Productos",       enc);
            celda(rH, 2, "Precio promedio",   enc);
            int ri = 1;
            for (Map.Entry<String, List<Producto>> e : porCat.entrySet()) {
                Row row = resumen.createRow(ri++);
                double prom = e.getValue().stream()
                               .mapToDouble(Producto::getPrecio).average().orElse(0);
                celda(row, 0, e.getKey(),           null);
                celda(row, 1, e.getValue().size(),  null);
                celda(row, 2, prom,                 mon);
            }
            autoSize(resumen, 3);

           
            for (Map.Entry<String, List<Producto>> entry : porCat.entrySet()) {
                String nombreHoja = entry.getKey().length() > 31
                        ? entry.getKey().substring(0, 31) : entry.getKey();
                Sheet sheet = wb.createSheet(nombreHoja);

            
                Row rTit = sheet.createRow(0);
                Cell tC  = rTit.createCell(0);
                tC.setCellValue("Categoría: " + entry.getKey());
                tC.setCellStyle(estiloTitulo(wb));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        
                String[] cols = {"ID", "Nombre", "Precio", "Estado", "Info Extra", "URL Imagen"};
                Row rHead = sheet.createRow(2);
                for (int i = 0; i < cols.length; i++) celda(rHead, i, cols[i], enc);

          
                int rowNum = 3;
                for (Producto p : entry.getValue()) {
                    Row row = sheet.createRow(rowNum);
                    CellStyle fondo = (rowNum % 2 == 0) ? alt : null;
                    celda(row, 0, p.getId(),        fondo);
                    celda(row, 1, p.getNombre(),     fondo);
                    celda(row, 2, p.getPrecio(),     mon);
                    celda(row, 3, p.getEstado(),     fondo);
                    celda(row, 4, p.getInfoExtra(),  fondo);
                    celda(row, 5, p.getUrlImagen(),  fondo);
                    rowNum++;
                }

                Row rTot = sheet.createRow(rowNum + 1);
                celda(rTot, 1, "Total:", null);
                celda(rTot, 2, entry.getValue().size(), null);
                autoSize(sheet, 6);
            }

            try (FileOutputStream fos = new FileOutputStream(filename)) { wb.write(fos); }
            System.out.println("Excel por categoría generado: " + filename);
            return filename;
        } catch (Exception e) {
            System.err.println("Error generando por categoría: " + e.getMessage());
            return null;
        }
    }

    
    public String generarReporteVentas(List<VentaJSON> ventas) {
        new java.io.File("reportes").mkdirs();
        String filename = "reportes/reporte_ventas_" + timestamp() + ".xlsx";

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Historial de Ventas");
            CellStyle enc = estiloEncabezado(wb);
            CellStyle mon = estiloMoneda(wb);
            CellStyle alt = estiloAlternado(wb);

         
            String[] cols = {"Folio","Fecha","ID Producto","Producto","Cantidad","Subtotal","Total Venta"};
            Row rHead = sheet.createRow(0);
            for (int i = 0; i < cols.length; i++) celda(rHead, i, cols[i], enc);

            int rowNum = 1;
            for (VentaJSON v : ventas) {
                boolean primera = true;
                for (Ticket t : v.items) {
                    Row row = sheet.createRow(rowNum);
                    CellStyle fondo = (rowNum % 2 == 0) ? alt : null;
                    celda(row, 0, primera ? v.folio : "",          fondo);
                    celda(row, 1, primera ? v.fecha : "",          fondo);
                    celda(row, 2, t.getId(),                       fondo);
                    celda(row, 3, t.getNombre(),                   fondo);
                    celda(row, 4, t.getCantidad(),                 fondo);
                    celda(row, 5, t.getSubtotal(),                 mon);
                    celda(row, 6, primera ? v.total : 0,           mon);
                    primera = false;
                    rowNum++;
                }
            }

            autoSize(sheet, 7);
            try (FileOutputStream fos = new FileOutputStream(filename)) { wb.write(fos); }
            System.out.println("Reporte ventas generado: " + filename);
            return filename;
        } catch (Exception e) {
            System.err.println("Error generando reporte ventas: " + e.getMessage());
            return null;
        }
    }
}