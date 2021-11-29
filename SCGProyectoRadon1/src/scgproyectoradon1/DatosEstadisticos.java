/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scgproyectoradon1;

/**
 *
 * @author Daniel E Guancha
 */
public class DatosEstadisticos {
    
    private String fecha;
    private Double minimo;
    private Double maximo;
    private Double rango;
    private Double promedio;

    public DatosEstadisticos(String fecha, Double minimo, Double maximo, Double rango, Double promedio) {
        this.fecha = fecha;
        this.minimo = minimo;
        this.maximo = maximo;
        this.rango = rango;
        this.promedio = promedio;
    }

    public String getFecha() {
        return fecha;
    }

    public Double getMinimo() {
        return minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public Double getRango() {
        return rango;
    }

    public Double getPromedio() {
        return promedio;
    }

   
    
    
}
