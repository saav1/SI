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
public class ClasificadorFuerte {
    
    private ArrayList<ClasificadorDebil> clasificadores;

    public double error;        //Suma de pesos en todas las imágenes donde el clasificador no acierta.
    public double confianza;    //Cuanto menor sea el erro, mayor será la confianza.
    
    
    //Util
    private int getRandomBetween(int min, int max){
        return ((int) (Math.random() * ((max - min) + 1)) + min);
    }
    
    //Init
    public ClasificadorFuerte(){
        clasificadores = new ArrayList();
    }
    
    //Devuelve el array de clasificadores débiles. 
    public ArrayList<ClasificadorDebil> getClasificadoresDebiles(){
        return clasificadores;
    }
    
    //Añade un clasificador debil al arrayList.
    public void addClasificadorDebil(ClasificadorDebil debil){
        clasificadores.add(debil);
    }
    
    
    //Genera un clasificador débil dada una dimensión.
    public ClasificadorDebil generarClasificadorAzar(int DIM){
        
        int pixel      = getRandomBetween(0, DIM);
        int umbral     = getRandomBetween(0, 255);
        int direccion  = getRandomBetween(0, 1);

        return new ClasificadorDebil(pixel, umbral, direccion);

    }
    
    /***
     * @param debil                 : ClasificadorDebil
     * @param numClasificadores     : int
     * @param numPruebas            : int
     * @param D                     : array de booleanos
     */
    public double ObtenerErrorClasificadorDebil(ClasificadorDebil debil, ArrayList<Imagen> entrenamiento, ArrayList<Integer> correcto, ArrayList<Double> D ){
        
        double error = 0.0;
        
        for(int i = 0; i < entrenamiento.size(); i++){
            
            int res = aplicarClasificadorDebil(debil, entrenamiento.get(i));
            
            if( res != correcto.get(i) ){ 
            
                error += D.get(i); 
            
            }

        }
        
        
        return error;
    }
    
    
    public int aplicarClasificadorDebil( ClasificadorDebil debil, Imagen imagen ){
        
        int valido = -1;
        

        if(debil.getDireccion() == 0){
            
            if( imagen.getImageData()[debil.getPixel()] <= debil.getUmbral() ){     //El clasificador débil ACIERTA
                valido = 1;
            }
            
        }else{ //dirección == 1
            
            if( imagen.getImageData()[debil.getPixel()] > debil.getUmbral() ){      //El clasificador débil FALLA
                valido = -1;
            }
             
        }
        
        return valido;
    }
    
    
}
