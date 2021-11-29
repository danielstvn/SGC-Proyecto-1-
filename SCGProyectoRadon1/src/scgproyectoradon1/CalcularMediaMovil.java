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
public class CalcularMediaMovil {
    
    private int periodos;
    private int tipo;
    private ArrayList<Datos> datosRadon;
    private ArrayList<Double> listaMediaMovil;

    /**
     * 
     * Constructor que recibe los parametros iniciales:
     * 
     * @param datosRadon : ArrayList de tipo Datos, contien los datos obtenidos en la busqueda 
     * @param tipo: indica el tipo de busqueda, minutos, horas, dias
     * @param periodos : indica la cantidad de periodos a buscar 1.....n
     */
    public CalcularMediaMovil( ArrayList<Datos> datosRadon, int tipo,int periodos) {
        this.periodos = periodos;
        this.tipo = tipo;
        this.datosRadon = datosRadon;
        
        listaMediaMovil = new ArrayList<>();
    }
    
    /**
     * Metodo para calcular la media movil teniendo en cuanta los parametros iniciales
     * cargados al constructor
     */
    public void mediaMovil() {
        
        ArrayList<Double> listaConcentracion = new ArrayList<>();
        
        
        for (Datos dato : datosRadon) {
            listaConcentracion.add(dato.getConcentracion());
            
        }

        
        switch (tipo){
            case 2: periodos = periodos*4;
            break;
            case 3: periodos = periodos*96;
            break;
            default: periodos = periodos;
            break;
        }
        
        int a = 0;
        int b = periodos;
        
         System.out.println("periodos " +b);
         
         System.out.println("lista concentracion " + listaConcentracion.size());
        

        double sumatoria = 0;
        
        while (b <= listaConcentracion.size()) {            
            for (int i = a; i < b; i++) {
                sumatoria += listaConcentracion.get(i);
            }
            
            listaMediaMovil.add(sumatoria/periodos);   
            
            sumatoria = 0;
            a ++;
            b ++;
        }
        
     
    }
     
    /**
     * Metodo que devuelve los datos con la media vil calculada
     * @return : devuelve un ArrayList de tipo Datos , con la media movil calculada
     */
    public ArrayList<Datos> getDatosMediaMovil(){
         
        mediaMovil();
        
        ArrayList<Datos> datosRadonMediaMovil = new ArrayList<>();
        
        System.out.println("tamaño radon " + datosRadon.size());
        System.out.println("tamaño promedios " + listaMediaMovil.size());
        
        for (int i = 0; i < datosRadon.size(); i++) {
            if(i < periodos-1){
                datosRadonMediaMovil.add(new Datos(datosRadon.get(i).getCodestacion(),datosRadon.get(i).getFecha(),
                        datosRadon.get(i).getHora(),datosRadon.get(i).getConcentracion(),0));
            }else{
                datosRadonMediaMovil.add(new Datos(datosRadon.get(i).getCodestacion(),datosRadon.get(i).getFecha(),
                        datosRadon.get(i).getHora(),datosRadon.get(i).getConcentracion(),listaMediaMovil.get(i-periodos+1)));
            }
            
        }
         
        return datosRadonMediaMovil;
    }


}
