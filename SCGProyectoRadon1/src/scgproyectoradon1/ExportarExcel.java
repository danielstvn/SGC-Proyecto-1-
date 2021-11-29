/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scgproyectoradon1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Daniel E Guancha
 */
public class ExportarExcel {
    /**
     * Metodo para exportar los datos de la tabla principal, recibe 
     * los siguientes parametros:
     * 
     * @param tablaDatos : Contiene los datos a exportar
     * @param rutaNombre : Contiene el nombre del archivo con el cual se exportara
     * @param nombreHoja : Indica el nombre que llevara la hoja de excel
     * @param fileFilter : Indica el tico de extención con el que se guardara el documento
     */
    public void exportar( JTable tablaDatos, File rutaNombre,String nombreHoja,FileFilter fileFilter) {
        try {
            Workbook libro = new XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet hoja = libro.createSheet(nombreHoja);
            Row filaCol = hoja.createRow(0);
            
            int x=0,y=0;
            
            for (int i = 1; i <tablaDatos.getColumnCount(); i++) {
                Cell celda = filaCol.createCell(i-1);
                celda.setCellValue(tablaDatos.getColumnName(i));
                
            }
            for (int j = 0; j < tablaDatos.getRowCount(); j++) {
                Row fila = hoja.createRow(j+1); // se crea la fila
                for (int k = 1; k < tablaDatos.getColumnCount(); k++) {
                    Cell celda = fila.createCell(k-1);
                    
                    if(tablaDatos.getValueAt(j, k) != null){
                        if(k>=4){
                            celda.setCellValue((double) tablaDatos.getValueAt(j, k));
                        }else{
                            celda.setCellValue(tablaDatos.getValueAt(j, k).toString());
                        }
 
                    }
                    
                    
                    
                    
                }
            }
            
            
            String extension [] = fileFilter.getDescription().split(" ");
            
            FileOutputStream salida = new FileOutputStream(rutaNombre+extension[1],true);
            libro.write(salida);
            libro.close();
            salida.close();
          
            
            
        } catch (FileNotFoundException e) {
        } catch (IOException io){
            
        }
    }
    
}
