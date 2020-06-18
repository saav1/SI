/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;
import java.io.*;
/**
 *
 * @author stalynalejandro
 */
public class Adaboost {
    
    private int porcentaje;
    private int debiles;
    private int pruebas;
        
    private ArrayList test          = new ArrayList();    
    private ArrayList entrenamiento = new ArrayList();
    private ArrayList correcto      = new ArrayList();
    
    
    public MNISTLoader ml = new MNISTLoader();

    
    Adaboost(int porcentaje, int debiles, int pruebas){
        
        this.porcentaje = porcentaje;
        this.debiles    = debiles;
        this.pruebas    = pruebas;
        this.ml.loadDBFromPath("./mnist_1000");
        
        clasificarImagenes();
        clasificarCorrecto();
    }
    
    
    private void clasificarImagenes(){
        
        for( int i = 0; i <= 9 ; i++ ){
        
            ArrayList imagenes = this.ml.getImageDatabaseForDigit(i);
            
            int numImagenesPorDigito = imagenes.size();
            
            System.out.println(" - digitos: " + numImagenesPorDigito);
            
            
            for( int j = 0; j < numImagenesPorDigito; j++ ){
                
                Imagen img = (Imagen)imagenes.get(j);
                
                if(j < ((this.porcentaje * numImagenesPorDigito)/100)) entrenamiento.add(img);
                else{
                    test.add(img);
                    correcto.add(i);
                }
                
            }
            
            System.out.println("entrenamiento : " + entrenamiento.size());
            System.out.println("test          : " + test.size() );
            
            
        }
        
    }
    
    
    private void clasificarCorrecto(){
        
        
        for( int i = 0 ; i <= 9; i++ ){
            
            for( int j = 0; j < entrenamiento.size(); i++ ){
            

            }
            
            
        }
        
    }
    
    

    

    
    
    
    public ArrayList<Integer> aplicarClasificadorFuerte( ArrayList test, ArrayList<ClasificadorFuerte> fuertes  ){
        ArrayList<Integer> h = new ArrayList();
        return h;
    }
    
    
    
    
    public ClasificadorFuerte aplicarAdaboost( ArrayList<Imagen> entrenamiento, ArrayList correctos){
        
        ClasificadorFuerte fuerte = new ClasificadorFuerte();
        
        //Aquí hay que implementar el pseudocódigo de ADABOOST
    
        return fuerte;
        
    }
    
    
    public int cuentaAciertos( ArrayList test, ArrayList correcto, ArrayList<Integer> mejoresH ){
        return 0;
    }
    
    
    public ArrayList<ClasificadorFuerte> getClasificadoresFuertes(){
        
        ArrayList<ClasificadorFuerte> fuertes = new ArrayList();
        
        for( int i = 0; i <= 9; i++ ){
           
            fuertes.add( aplicarAdaboost(getEntrenamiento(), (ArrayList) getCorrecto().get(i)) );
            
        }
        
        return fuertes;
    }
    
    
    
    public ArrayList getEntrenamiento(){
        return this.entrenamiento;
    }
    
    public ArrayList getCorrecto(){
        return this.correcto;
    }
    
    public ArrayList getTest(){
        return this.test;
    }
    
    
    
}
