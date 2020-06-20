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
public class ClasificadorDebil {
    
    private int pixel;
    private int umbral;         //[0..255] => [blanco..negro]
    private int direccion;      //Nos sirve para clasificar el pixel.
    
    private int error;
    private float confianza;
    
    
    public ClasificadorDebil(){
        this.pixel      = 0;
        this.umbral     = 0;
        this.direccion  = 0;
        this.error      = 0;
        this.confianza  = 0;
    }
        
    public ClasificadorDebil(int pixel, int umbral, int direccion, int error, float confianza){
        this.pixel      = pixel;
        this.umbral     = umbral;
        this.direccion  = direccion;
        this.error      = error;
        this.confianza  = confianza;
    }
    
    public boolean hipotesis( Imagen imagen ){
        
        boolean boo = false;
        
        if( this.direccion == 1 ){
                
            if( this.umbral < imagen.getImageData()[this.pixel] ){
                    
                boo = true;

            }
            
        }
        else{
            
            if( this.umbral >= imagen.getImageData()[this.pixel] ){
                
                boo = true;
                
            }
        
        }
        
        return boo;
    }
    
    
    public ArrayList aplicarDebil( ArrayList entrenamiento ){
        
        ArrayList<Boolean> clas = new ArrayList();
        
        for( int i = 0; i < entrenamiento.size(); i++ ){
            
            Imagen imagen = (Imagen)entrenamiento.get(i);
           
            boolean boo = hipotesis(imagen);
            
            clas.add(boo);
            
            
        }
        
        return clas;
        
    }
    
    public int getPixel(){ 
        return pixel; 
    }
    
    public int getUmbral(){ 
        return umbral; 
    }

    public int getDireccion(){ 
        return direccion; 
    }
    
    public int getError(){
        return error;
    }
    
    public float getConfianza(){
        return confianza;
    }
    
    public void setError( ArrayList<Imagen> entrenamiento, ArrayList correcto ){
        
        ArrayList auxDebil = this.aplicarDebil(entrenamiento);
      
        
        for( int i = 0; i < auxDebil.size(); i++ ){
            
            if( auxDebil.get(i).equals(correcto.get(i)) ) this.error += entrenamiento.get(i).getPeso();
            
        }
        
        this.confianza = 0.5f * (float)Math.log10(1.0f - this.error) / this.error;
    }
    
    public void setError( int error ){
        this.error = error;
    }
    
    
    public void setConfianza( float confianza ){
        this.confianza = confianza;
    }
    
    public void setPixel( int pixel ){
        this.pixel = pixel;
    }
    
    public void setUmbral(int umbral){
        this.umbral = umbral;
    }
    
    public void setDireccion(int direccion){
        this.direccion = direccion;
    }
    
}
