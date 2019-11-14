/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;

import java.util.ArrayList;

/**
 *
 * @author fidel
 */
public class Main {
    
    
    public static int Byte2Unsigned(byte b) {
        return b & 0xFF;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Cargador MNIST de SI
        MNISTLoader ml = new MNISTLoader();
        ml.loadDBFromPath("/home/saav1/Escritorio/SI/Practica2/Plantilla/mnist_1000");
        
        //Accedo a las imagenes de dígito 1
        ArrayList d0imgs = ml.getImageDatabaseForDigit(2);
        
        //Y cojo la tercera imagen del dígito 1
        Imagen img = (Imagen) d0imgs.get(1);
        
        //La invierto para ilustrar como acceder a los pixels
        byte imageData[] = img.getImageData();
        for (int i = 0; i < imageData.length; i++){
            
            imageData[i] = (byte) (255 - imageData[i]);
            System.out.print(Byte2Unsigned(imageData[i])+ ",");
            if(i != 0 && i % 28 == 0) System.out.println();
            
        }
        
        //Muestro la imagen invertida
        MostrarImagen imgShow = new MostrarImagen();
        imgShow.setImage(img);
        //imgShow.mostrar();
        
        System.out.println();

           
        
        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //:::::::::::::::::::PROGRAMA::::::::::::::::::::::::::::::::::::::
        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        Imagen carga = new Imagen();
        
        carga.CargaImagen();
        carga.addImagenes(ml);                 
        //carga.addEntrenamiento();

        double contador = 0;
        
        try
        {
            if(args[0].equals("-train"))
            {
                for( int i = 0 ; i < 8 ; i++)
                {
                    System.out.println("[1]");
                    StrongSorter ss = new StrongSorter();
                    System.out.println("[1.1]");
                    ss.AlgoritmoAdaboost(carga.getImagenesEntrenamiento(), 10, 10, i);
                    contador = 0;
                    System.out.println("[2]");
                    for(int k = 0; k < carga.getImagenesEntrenamiento().size() ; k++)
                    {

                        int coincide;
                        if( i == carga.getImagenesEntrenamiento().get(k).getDigitoPertenece()) coincide = 1;
                        else coincide = 0;

                    }

                    float resultado = (float)( (contador) / carga.getImagenesEntrenamiento().size() * 100 );
                    System.out.print("Recuento que coincide: " + resultado);
                    System.out.print("     " + resultado +"%");
                }
            }
            else if(args[0].equals("-run"))
            {
            System.out.println("RUN : NOT IMPLEMENTED YET!");
            }
        }
        catch(Exception e)
        {
            ;
        }
        
        
    }
}