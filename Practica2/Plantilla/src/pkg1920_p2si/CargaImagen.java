/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;

/**
 *
 * @author saav1
 */
public class CargaImagen {
    private ArrayList<Imagen> totales;
    private ArrayList<Imagen> entrenamiento;
    private ArrayList<Imagen> test;
    int porcentaje;
    
    public CargaImagen(){
        totales = new ArrayList<Imagen>();
        entrenamiento = new ArrayList<Imagen>();
        test = new ArrayList<Imagen>();
    }
    
    public ArrayList<Imagen> getTotalImagenes(){
        return totales;
    }
    
    public ArrayList<Imagen> getImagenesEntr(){
        return entrenamiento;
    }
    
    public ArrayList<Imagen> getImagenesTest(){
        return test;
    }
    
    public void addImagenes(MNISTLoader loader){
        for(int i = 0; i < 8; i++){
            ArrayList imgs = loader.getImageDatabaseForDigit(i);
            for(int j = 0; j < imgs.size(); j++){
                Imagen img = (Imagen) imgs.get(j);
                img.setDigitoPertenece(i);
                totales.add(img);
            }
        }
    }
    
    public void addEntrenamiento(int porcentaje){
        this.porcentaje = porcentaje;
        double size1 = (4001.0 * (porcentaje/100.0));
        int size2 = (int)size1;
        int valor;
        
        for(int i = 0; i < size2; i++){
            valor = (int) Math.random() * 400;
            if(!entrenamiento.contains(totales.get(valor))) entrenamiento.add(totales.get(valor));
            else size2 = size2 + 1;
        }
    }

    public void addTest(int resto){
        double size1 = (4001.0 * (resto/100.0));
        int size2 = (int) size1;
        int valor;
        for(int i = 0 ; i < size2 ; i++){
            valor = (int) Math.random() * 4001;
            if(!entrenamiento.contains(totales.get(valor))) test.add(totales.get(valor));
            else size2 = size2 + 1;
        }
    }
    
}
