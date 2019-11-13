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
        ml.loadDBFromPath("./mnist_1000");
        
        //Accedo a las imagenes de dígito 1
        ArrayList d0imgs = ml.getImageDatabaseForDigit(1);
        
        //Y cojo la tercera imagen del dígito 1
        Imagen img = (Imagen) d0imgs.get(50);
        
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
        
        
        System.out.println("\n\n\nRecuento");

           
        
        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        //:::::::::::::::::::PROGRAMA::::::::::::::::::::::::::::::::::::::
        //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        CargaImagen carga = new CargaImagen();
                 System.out.println("\n\n[1]");
        carga.addImagenes(ml);
                 System.out.println("\n\n[1]");
                 
        carga.addEntrenamiento(50);
                         System.out.println("\n\n[1]");

        carga.addTest(20);
        
                 System.out.println("\n\n[1]");
        double contador = 0;
         System.out.println("\n\n[1]");

        //if(args.length >= 2)
        //{
            //if(args[0].equals("-train"))
            //{
                for( int i = 0 ; i < 8 ; i++)
                {
                    StrongSorter ss = new StrongSorter();
                    ss.AlgoritmoAdaboost(carga.getImagenesEntr(), 100, i);
                    contador = 0;
                    
                    for(int k = 0; k < carga.getImagenesEntr().size() ; k++)
                    {
                        int coincide;
                        int res = ss.Clasifica(carga.getImagenesEntr().get(k));
                        if( i == carga.getImagenesEntr().get(k).getDigitoPertenece()) coincide = 1;
                        else coincide = 0;
                        
                        if(res == coincide) contador++;
                    }
                    
                    float resultado = (float)( (contador) / carga.getImagenesEntr().size() * 100 );
                    System.out.print("Recuento de aciertos de entrenamiento para " + i + ":      ");
                    System.out.println("Recuento de aciertos de test para: " + i + ":     ");
                    System.out.print("     " + resultado +"%");
                    contador = 0;
                    for(int k = 0; k < carga.getImagenesTest().size(); k++){    
                        int coincide;
                            int res = ss.Clasifica(carga.getImagenesTest().get(k));
                            if(i == carga.getImagenesTest().get(k).getDigitoPertenece()){
                                coincide = 1;
                            }
                            else{
                                coincide = 0;
                            }
                            if(res == coincide){
                                contador++;
                            }
                    }
                    System.out.println("                                            " + (float) (((contador)/(carga.getImagenesTest().size()))*100) +"%");
                }
                    
                    
                
            /*}
            else if(args[0].equals("-run"))
            {
            }*/
        //}

        
        
        
    }
    
}
