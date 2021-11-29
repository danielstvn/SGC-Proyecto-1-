/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scgproyectoradon1;

import java.util.ArrayList;

/**
 *
 * @author Daniel E Guancha
 */
public class Estadisticas {

    private String estacion;
    private String fechaDesde;
    private String fechaHasta;
    
    private double minimo;
    private double maximo;
    private double rango;
    private double promedio;
    
    

    private  Conexion conexion = new Conexion();
    
    /**
     * Constructor con parametros iniciales:
     * 
     * @param estacion : contiene el codigo de la estación
     * @param fechaDesde : contiene la fecha inical
     * @param fechaHasta : contiene la fecha final
     */
    public Estadisticas(String estacion, String fechaDesde, String fechaHasta) {
        this.estacion = estacion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        
        CalcularEstadisticas();
    }
    
    /**
     * Metodo para calcular estadisticas
     */
    public void CalcularEstadisticas(){
        
        ArrayList<Double> listaConcentracion = new ArrayList<>();
        
        
        for (Datos dato : conexion.getDatos(estacion, fechaDesde, fechaHasta)) {
            listaConcentracion.add(dato.getConcentracion());
        }
        
        maximo = 0;
        for (Double valor : listaConcentracion) {
            if(valor > maximo){
                maximo = valor;
            }
        }
        minimo = maximo;
        for (Double valor : listaConcentracion) {
            if(valor < minimo){
                minimo = valor;
            }
        }
        
        double sumatoria = 0;
        for (Double valor : listaConcentracion) {
            sumatoria += valor;
        }
        
        promedio = sumatoria/listaConcentracion.size();
        
        rango = maximo - minimo;
        
        
    }

    /**
     * MEtodo que devuelve el valor minimo 
     * @return : retorna un valor minimo estadistico
     */
    public double getMinimo() {
        return minimo;
    }

    /**
     * Metodo que devuelve el valor maximo
     * @return : retorna un valor maximo estadistico
     */
    public double getMaximo() {
        return maximo;
    }
    
    

    /**
     * Metodo que devuelve un rango
     * @return : retorna un valor de un rango estadistico
     */
    public double getRango() {
        return rango;
    }

    /**
     * Metodo que devuelve el promedio
     * @return :retorna el valor promedio de los datos
     */
    public double getPromedio() {
        return promedio;
    }
    
    
}
