/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;
/**
 *
 * @author stalynalejandro
 */
public class Adaboost {
    
    private int porcentaje;
    private int debiles;
    private int pruebas;
    
    Adaboost(int porcentaje, int debiles, int pruebas){
        
        this.porcentaje = porcentaje;
        this.debiles    = debiles;
        this.pruebas    = pruebas;
    }
    
    public ArrayList<ClasificadorFuerte> getClasificadoresFuertes(){
        
        ArrayList<ClasificadorFuerte> fuertes = new ArrayList();
        
        for( int i = 0; i <= 9; ++i ){
           
            fuertes.add( aplicarAdaboost(getAprendizaje(), getCorrectos().get(i)) );
            
        }
        
    
    }
    
    public ClasificadorFuerte aplicarAdaboost( ArrayList<Imagen> entrenamiento, ArrayList<Boolean> correctos){
        
        //Aquí hay que implementar el pseudocódigo de ADABOOST
    
    }
    
    
}
