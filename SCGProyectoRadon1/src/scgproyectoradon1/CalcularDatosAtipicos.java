/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scgproyectoradon1;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Daniel E Guancha
 */
public class CalcularDatosAtipicos {
    // variables
    private double Q1;
    private double Q3;
    private double mediana;
    private double limiteInferior;
    private double limiteSuperior;
    private double rangoQ;

    /**
     * Metodo para calcular los datos atipicos 
     * @param datosAtipicos : ArrayList de tipo Datos, contiene los datos obtenidos de la busqueda
     * @return : retorna un ArryList de tipo Double, con los posibles datos atipicos
     */
    public ArrayList<Double> Calcular_Datos_Atipicos(ArrayList<Datos> datosAtipicos) {
        
        ArrayList<Double> listaConcentracion = new ArrayList<>();
        
        for (Datos con : datosAtipicos) {
            listaConcentracion.add(con.getConcentracion());
        }
              
        Collections.sort(listaConcentracion);
        
        // calculo de la mediana del conjunto de datos
        
        
        int nElementos = datosAtipicos.size();
        if(nElementos % 2 ==0){
            mediana = (listaConcentracion.get((nElementos/2)-1)+listaConcentracion.get(nElementos/2))/2;
        }else{
            mediana = listaConcentracion.get(nElementos/2);
        }
        
        
        // calculo del cuartil 1 Q1
        
        
        int posQ1 = (nElementos+1)/4;
        if(posQ1 % 2 ==0){
            Q1 = listaConcentracion.get(posQ1-1);
        }else
        {
            double aux_pos=(nElementos+1)/4;
            int li,ls;
            li = (int)Math.floor(aux_pos);
            ls = (int)Math.ceil(aux_pos);
            
            Q1 = (listaConcentracion.get(li-1)+listaConcentracion.get(ls-1))/2;
        }
        
        // calculo del cuartil 3 Q3
        
        
        
        int posQ3 = 3*(nElementos+1)/4;
        if(posQ3 % 2 ==0){
            Q3 = listaConcentracion.get(posQ3-1);
        }else
        {
            double aux_pos=3*(nElementos+1)/4;
            int li,ls;
            li = (int)Math.floor(aux_pos);
            ls = (int)Math.ceil(aux_pos);
            
            Q3 = (listaConcentracion.get(li-1)+listaConcentracion.get(ls-1))/2;
        }
        
        // hallar el rango del cuartil
        
        rangoQ = Q3-Q1;
        
        // limites externos del conjunto de datos
        
        
        limiteInferior = Q1 - (rangoQ*3);
        limiteSuperior = Q3 + (rangoQ*3);
        
        // en base a este rango identificamos los valores atipicos en el conjunto de datos
        
        ArrayList<Double> listaValoresAtipicos = new ArrayList<>();
       
     
        for (double valor : listaConcentracion) {
            if(valor <= limiteInferior || valor >= limiteSuperior )listaValoresAtipicos.add(valor);
        }
        
        System.out.println("Q1 " +Q1);
        System.out.println("Q2 " +mediana);
        System.out.println("Q3 " +Q3);
       
        // retornar la lista de datos atipicos encontrados
       return listaValoresAtipicos;
       
        
    }
    
}
