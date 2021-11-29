/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scgproyectoradon1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel E Guancha
 */
public class Conexion {
        protected Connection conexion = null;
        
    /**
     * Metodo para realizar la conexión con la base de datos
     */
    public void conectarBD(){
       
       
        try {
            // conexion a la base de datos
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SGC_2021", "postgres", "postgres");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión con la base de datos \n"+e);
        }
       
        
    }
    /**
     * Metodo para desconetar la base de datos
     */
    public void desconectarBD(){
        try {
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Cerrar la conexion con la base de datos \n"+e);
        }
    }
    
    /**
     * Metodo para obtener los datos de la base de datos
     * @param estacion : indica la estacion que se desea buscar
     * @param fechaInicio : indica la fecha desde la cual se quiere iniciar la busqueda
     * @param fechaFin : indica la fecha hasta la cual se requiere realizar la busqueda
     * @return : retorna un ArrayList de tipo Datos; con los datos obtenidos en la consulta
     */
    public ArrayList<Datos> getDatos (String estacion, String fechaInicio, String fechaFin){
        // conectar con base de datos
        conectarBD();
        
        // obtener los datos
        ArrayList<Datos> datosBD = new ArrayList<>();
        try {
            Statement sentencia = conexion.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_SENSITIVE);  
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM rad_telemetrico_sum15 "
                                                        + "WHERE codestacion='"+estacion+"' "
                                                        + "AND fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' ORDER BY fecha" );
                                                      
            
            // llenar los datos de concetracion en un arraylist
            while (resultado.next()) {
                datosBD.add(new Datos(resultado.getString("codestacion"),resultado.getString("fecha"), resultado.getString("hora"), resultado.getDouble("concentracion"),0));         
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los datos de BD \n"+e);
        }
        
        // desconectar la base de datos
        desconectarBD();
            
        return datosBD;
    }
    
    /**
     * Metodo para obtener los datos Atipicos en la base de datos
     * @param listaDatosAtipicos : contiene una lista de datos atipicos, los cuales se buscaran en la base de datos
     * @param estacion: indica la estacion que se requiere buscar
     * @param fechaInicio : indica la fecha de inicio  desde la cual se realizara la busqueda
     * @param fechaFin : indica la fecha hasta la cual se realizara la busqueda
     * @return : retorna un ArrayList de tipo Datos, con los datos atipicos encontrados
     */
    public ArrayList<Datos> getDatosAtipicos(ArrayList<Double> listaDatosAtipicos, String estacion, String fechaInicio, String fechaFin) {
        
        // conectar con base de datos
        conectarBD();
        
        // sentencia para obtener la informacion de los datos atipicos
        ArrayList<Datos> datosAnomalosBD = new ArrayList<>();
        
        System.out.println("tamaño 1 " + listaDatosAtipicos.size());
        
        Set<Double> hashSet = new HashSet<Double>(listaDatosAtipicos);
        
        listaDatosAtipicos.clear();
        listaDatosAtipicos.addAll(hashSet);
        for (Double value : listaDatosAtipicos) {
            try {
                
                    Statement sentencia = conexion.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_SENSITIVE);
                    ResultSet resultado = sentencia.executeQuery("SELECT * FROM rad_telemetrico_sum15 WHERE concentracion="+value+""
                                                                + "AND codestacion='"+estacion+"' "
                                                                + "AND fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' ORDER BY fecha");

                    while (resultado.next()) {
                        datosAnomalosBD.add(new  Datos(resultado.getString("codestacion"),resultado.getString("fecha"),resultado.getString("hora"), resultado.getDouble("concentracion"),0));
                        
                    }
                
                    

                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al obtener los datos atipicos de BD \n"+e);
            }
            
        }
    
        // desconectar la base de datos
        desconectarBD();
        
        return datosAnomalosBD;
    }
    
    /**
     * Metodo para eliminar datos atipicos en la base de datos:
     * @param estacion : indica  la estación la cual se desea consultar
     * @param fechaInicio : indica la fecha desde la cual se deasea realizar la consulta
     * @param fechaFin : indica la fecha hasta la cual se desea realizar la consulta
     * @param value :  indica el valor que se desea eliminar en la consulta
     */
    public void eliminarDatosAtipicos(String estacion, String fechaInicio, String fechaFin ,double value) {
        try {
            conectarBD();
            Statement sentencia = conexion.createStatement();
            sentencia.executeQuery("DELETE FROM rad_telemetrico_sum15 WHERE concentracion="+value+""
                                    + "AND codestacion='"+estacion+"' "
                                    + "AND fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' ORDER BY fecha");
            desconectarBD();
   
        } catch (SQLException e) {
            System.out.println(e);
            
        }
        
        
        
        
             
    }
    
    /**
     * Metodo que obtiene las estaciones registradas en la base de datos
     * @return : retorna una lista de las estaciones disonibles
     */
    public ArrayList <String> getEstaciones (){
        // conetar con la base de datos
        conectarBD();
        ArrayList <String> estaciones = new ArrayList <String>();
        
        // sentancia para consultar las estaciones en la base de datos
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT DISTINCT codestacion FROM rad_telemetrico_sum15");
            
            while (resultado.next()) {
                    estaciones.add(resultado.getString("codestacion"));         
                }
        } catch (SQLException e) {
            
        }
        
        // desconectar la base de datos
        
        desconectarBD();
        
        return estaciones;
        
    }
    /**
     * Metodo para validar la fecha de busqueda en la base de datos
     * @param fecha : fecha que se desea validar en la base de datos
     * @return : retorna Verdadero si la fecha esta en la base de datos
     * de lo contrario retornara Falso
     */
    public boolean validarFecha(String fecha){
        
        // conectar con la base de datos
        conectarBD();
        
        System.out.println("fecha entrante "+ fecha);
        // sentancia para consultar las estaciones en la base de datos
        try {
            Statement sentencia = conexion.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_SENSITIVE);
            ResultSet resultado = sentencia.executeQuery("SELECT fecha FROM rad_telemetrico_sum15 WHERE fecha='"+fecha+"' LIMIT 1");
            
           if(resultado.next()){
               // desconectar db
               desconectarBD();
               return true;
           }
           else {
               // desconectar db
               desconectarBD();
               return false;
           }
  
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        
        
        
    }
    
}
