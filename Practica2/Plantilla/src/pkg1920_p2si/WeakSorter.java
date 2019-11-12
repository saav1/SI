/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;

/**
 *
 * @author STALYN ALEJANDRO ALCOCER AVLENCIA
 */
public class WeakSorter {
    private int pixel;
    private int umbral;
    private int direccion;
    
    public double error;
    public double confianza;
    
    public WeakSorter(){
        pixel = (int) Math.random() * 784;
        umbral = (int) (Math.random() * 256) - 127;
        int valor = (int) Math.random() * 2;
        if(valor == 0) direccion = -1;
        else direccion = 1;
        
        error = 0.0;
        confianza = 0.0;
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
    
    public double getError(){ 
        return error; 
    }
    
    public double getConfianza(){ 
        return confianza; 
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
    
    public void setError(double error){
        this.error = error;
    }
    
    public void setConfinaza(double confianza){
        this.confianza = confianza;
    }
    
    
    public void Entrenar(ArrayList<Imagen> entrenamiento, double[] D, int numero){
    
        int[] binario = new int[entrenamiento.size()];
        
        for(int j = 0;  j < entrenamiento.size(); j++)
        {
            Imagen img = (Imagen) entrenamiento.get(j);
            byte imageData[] = img.getImageData();
            
            if(direccion == 1)
            {
                if(imageData[pixel] < umbral)
                {
                    binario[j] = 1;
                }
                else
                {
                    binario[j] = -1;
                }
            }
            else
            {
                if(imageData[pixel] >= umbral)
                {
                    binario[j] = 1;
                }
                else
                {
                    binario[j] = -1;
                }
            }
        }
        
        int es;
        
        for( int i = 0 ; i < entrenamiento.size() ; i++)
        {
            if(numero == entrenamiento.get(i).getDigitoPertenece())
            {
                es = 1;
            }
            else
            {
                es = -1;
            }
            
            ///Tenemos que sumar el peso de donde ha fallado para calcular el error
            if(binario[i] == 1 && es == -1) this.error += D[i];
            if(binario[i] == -1 && es == 1) this.error += D[i];
            
            double numerador = (double)(1 - error)/error;
            confianza = (double) 0.5 * Math.log(numerador);
        }
        
    }

}


























