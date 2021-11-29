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
public class Datos {
    private String codestacion;
    private String fecha;
    private String hora;
    private double concentracion;
    private double promediomovil;
    
    private String estacion;
    private String fechaDesde;
    private String fechaHasta;
    
    protected ArrayList<Datos> datosRadon;

    /**
     * Constructor de la clase Datos
     */
    public Datos() {
        datosRadon = new ArrayList<>();
    }
    
    /**
     * Constructor con parametros iniciales:
     * 
     * @param codestacion : Contiene el nombre de la estacion consultada
     * @param fecha : contiene la fecha de la obtención de los datos
     * @param hora : contiene la hora de la obtención de los datos
     * @param concentracion : Contiene el valor registrado de la estacion buscada
     * @param promediomovil : Contiene el valor de la media movil de la concenración
     */
    public Datos(String codestacion, String fecha, String hora, double concentracion, double promediomovil) {
        this.codestacion = codestacion;
        this.fecha = fecha;
        this.hora = hora;
        this.concentracion = concentracion;
        this.promediomovil = promediomovil;
    }
    /**
     * Metodo que devuelve el codigo de la estación
     * @return : retorna el codigo de la estación
     */ 
    public String getCodestacion() {
        return codestacion;
    }
    
    /**
     * Metodo que devuelve la fecha
     * @return : retorna la fecha
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Metodo que devuelve la hora
     * @return : retorna la hora
     */

    public String getHora() {
        return hora;
    }
    
    /**
     * Metodo que devuelve la concentración
     * @return :retorna la concentración
     */
    public double getConcentracion() {
        return concentracion;
    }

    /**
     * Metodo que devuelve el promedio movil
     * @return : retorna la media movil
     */
    public double getPromediomovil() {
        return promediomovil;
    }

    /**
     * Metodo que devuelve una lista de tipo Datos
     * @return : retorna un ArrayList de tipo Datos, con los datos almacenados
     */
    public ArrayList<Datos> getDatosRadon() {
        return datosRadon;
    }

    /**
     * Metodo que recibe una lista de Tipo Datos
     * @param datosRadon : recibe una lista de datos de tipo Datos
     */
    public void setDatosRadon(ArrayList<Datos> datosRadon) {
        this.datosRadon = datosRadon;
    }

    /**
     * Metodo que devuelve la estacion
     * @return : retorna el codigo de la estación
     */
    public String getEstacion() {
        return estacion;
    }
    
    /**
     * Metodo que devuelve la fecha inicial
     * @return : retorna la fecha inicial
     */

    public String getFechaDesde() {
        return fechaDesde;
    }
    

    /**
     * Metodo que devuelve la fecha final
     * @return : retorna la fecha final
     */
    public String getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Metodo que recibe la estación
     * @param estacion : recibe el codigo de la estación
     */
    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    /**
     * Metodo que recibe la fecha inicial
     * @param fechaDesde : recibe la fecha inicial
     */
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Metodo que recibe la fecha final
     * @param fechaHasta : recibe la fecha final
     */
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    
    
    
    
         
    
    
}
