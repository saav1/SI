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
    
    
    public ClasificadorDebil(int pixel, int umbral, int direccion){
        this.pixel      = pixel;
        this.umbral     = umbral;
        this.direccion  = direccion;
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
