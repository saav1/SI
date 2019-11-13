/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fidel
 */
public class Imagen {
    
    private BufferedImage biImage;
    private byte [] imageData;

    //-----------------------------------------------------    
    private int digito;
    private int porcentaje;
    private ArrayList<Imagen> totales;
    private ArrayList<Imagen> entrenamiento;
    private ArrayList<Imagen> test;
    
    public void CargaImagen(){
        totales = new ArrayList<Imagen>();
        entrenamiento = new ArrayList<Imagen>();
        test = new ArrayList<Imagen>();
    } 
    
    public ArrayList<Imagen> getTotalImagenes(){
        return totales;
    }
    
    public ArrayList<Imagen> getImagenesEntrenamiento(){
        return entrenamiento;
    }

    public ArrayList<Imagen> getImagenesTest(){
        return test;
    }
    
    public void addImagenes(MNISTLoader loader){
        
        for(int i = 0; i <= 9 ; i++)
        {
            ArrayList digitoImagen = loader.getImageDatabaseForDigit(i);
            for(int j = 0 ; j < digitoImagen.size(); j++)
            {
                Imagen numero = (Imagen) digitoImagen.get(j);   //Se extrae imamgen por imagen.
                numero.setDigitoPertenece(i);                   //Se le indica a la imagen el digito que está mostrando.
                totales.add(numero);    //Se añado todas las imágenes al array 'totales'.
            }
        }
    }
    
    public void addEntrenamiento(){
        
        System.out.println("Totales size: " + totales.size());
        for(int i = 0; i < totales.size(); i++)
        {
            
            System.out.println("Totales :" + totales.get(i).getDigitoPertenece());
        }
        
    }
    
    
    //-----------------------------------------------------
    
    Imagen(){
        biImage = null;
    }
    
    
    Imagen(File file){
        try {
            biImage = ImageIO.read(file);
            imageData = ((DataBufferByte)biImage.getRaster().getDataBuffer()).getData();

        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void loadFromPath(String filename){
        try
        {
            File file = new File(filename);
            biImage = ImageIO.read(file);
            imageData = ((DataBufferByte)biImage.getRaster().getDataBuffer()).getData();

            
            //Conversion rápida de color a grises, por si es necesario...
            BufferedImage biColor = ImageIO.read(file);
            biImage = new BufferedImage(biColor.getWidth(), biColor.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            biImage.getGraphics().drawImage(biColor, 0, 0, null);
                           
        } catch (IOException e)
        {
                System.out.println(e.getMessage());
        }
    }
    
    byte [] getImageData(){
        return imageData;
    }
    
    BufferedImage getBufferedImage(){
        return biImage;
    }
    
    public int getDigitoPertenece(){
        return digito;
    }
    public void setDigitoPertenece(int dig){
        digito = dig;
    }
    
}
