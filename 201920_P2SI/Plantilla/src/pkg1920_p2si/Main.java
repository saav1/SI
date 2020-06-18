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
    
    public static void guardarClasificador(ArrayList<ClasificadorFuerte> fuerte, String file){
        //Guardar el clasificador en un .txt
        
    }
    
    
    public static ArrayList<ClasificadorFuerte> leerClasificadorFuerte(String file){
        ArrayList<ClasificadorFuerte> clasFuerte = new ArrayList();
        return clasFuerte;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        //Cargador MNIST de SI
        MNISTLoader ml = new MNISTLoader();
        ml.loadDBFromPath("./mnist_1000");
        
        //Accedo a las imagenes de dígito 1
        ArrayList d0imgs = ml.getImageDatabaseForDigit(1);
        
        //Y cojo la tercera imagen del dígito 1
        Imagen img = (Imagen) d0imgs.get(2);
        
        //La invierto para ilustrar como acceder a los pixels
        byte imageData[] = img.getImageData();
        for (int i = 0; i < imageData.length; i++){
            
            imageData[i] = (byte) (255 - imageData[i]);
            System.out.print(Byte2Unsigned(imageData[i])+ ",");
        }
        
        //Muestro la imagen invertida
        MostrarImagen imgShow = new MostrarImagen();
        imgShow.setImage(img);
        imgShow.mostrar();
        */
        
        //-----------------------------------------------------------
        //::::::::::::::::::::::::: ADABOOST ::::::::::::::::::::::::
        //-----------------------------------------------------------
        
        int porcentaje  = 50;
        int numPruebas  = 1000;
        int numDebiles  = 100;
        int numAciertos = 0;
        
        long init, fin;

        
        ArrayList<Imagen>               imagenes;
        ArrayList<ClasificadorFuerte>   ClasificadoresFuertes;
        ArrayList<Integer>              mejoresH;
        
  
        Adaboost adaboost = new Adaboost(porcentaje, numDebiles, numPruebas);
        
        
        if( args[0].equals("-t") ){
        
                    System.out.println("Entrenando para todos los dígitos\n");

            
            init = System.currentTimeMillis();
            
            ClasificadoresFuertes = adaboost.getClasificadoresFuertes();
            
            guardarClasificador(ClasificadoresFuertes, "s");
            
            mejoressH = adaboost.aplicarClasificadorFuerte( adaboost.getTest(), ClasificadoresFuertes);
            
            numAciertos = adaboost.cuentaAciertos( adaboost.getTest(), adaboost.getCorrecto(), mejoresH);
            
            fin = System.currentTimeMillis();

            
                    System.out.println("\nFuerte Guardado en " + "" );
                    System.out.println("\nreal vs fuerte");
                    System.out.println(adaboost.getCorrecto() );
                    System.out.println(mejoresH);
                    System.out.println("\n** Resultados imagenes de testeo: ");
                    System.out.println( "Aciertos:  " + numAciertos );
                    System.out.println( "Fallos:    " + (adaboost.getTest().size() - numAciertos) );
                    System.out.println("Porcentaje: " + (numAciertos*100/mejoresH.size()) +"%");
                    System.out.println("\nLa tarea ha requerido "+ ( init - fin ) +" ms");

            
                
        }else{
            
                    System.out.println("Cargar Clasificador fuerte y ejecutar sobre una imagen.\n");
                    System.out.println("Clasificador Fuerte : " + " .txt");
                    System.out.println("Imagen " + " ");
            
            
            int digitoEvaluar = 1;
            
            ClasificadoresFuertes = leerClasificadorFuerte(".txt");
            
            imagenes = adaboost.ml.getImageDatabaseForDigit(digitoEvaluar);
            
            mejoresH = adaboost.aplicarClasificadorFuerte(imagenes, ClasificadoresFuertes );
        
            //Calcular número de aciertos.

                    System.out.println("\n**generada vs real ");
                    System.out.println(mejoresH);
                    System.out.println(imagenes);

                    System.out.println("\n** Resultados **");
                    System.out.println( "Aciertos:  " + numAciertos );
                    System.out.println( "Fallos:    " + (imagenes.size() - numAciertos) );
                    System.out.println("Porcentaje: " + (numAciertos*100/mejoresH.size()) +"%");

            
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
