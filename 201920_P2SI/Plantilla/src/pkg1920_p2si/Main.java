/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;

import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author fidel
 */
public class Main{
    
    public static int NUM_PORCENTAJE = 50;
    public static int NUM_CLASIFICADORES = 10;
    public static int NUM_PRUEBAS = 10;

    
    public static int Byte2Unsigned(byte b) {
        return b & 0xFF;
    }
    
    public static void guardarClasificador(ArrayList<ClasificadorFuerte> fuertes, String fileName) throws IOException{
        
        PrintWriter writer;
        FileWriter  file = null;
        
        try{
            
            file   = new FileWriter(fileName);
            writer = new PrintWriter(fileName); 
            

            ArrayList<ClasificadorDebil> auxDebiles = new ArrayList();
            
           
            for( int i = 0; i < fuertes.size(); i++ ){
                
                
                auxDebiles = fuertes.get(i).getClasificadoresDebiles();
                
                for( int j = 0; j < auxDebiles.size(); i++ ){
                                
                    writer.println(auxDebiles.get(j).getUmbral());
                    writer.println(auxDebiles.get(j).getPixel());
                    writer.println(auxDebiles.get(j).getDireccion());
                    writer.println(auxDebiles.get(j).getError());
                    writer.println(auxDebiles.get(j).getConfianza());
                    
                    if(j < auxDebiles.size() - 1) writer.println("---");

                }
                
                
            
            }
            
        }catch( IOException e ){
            
            System.err.print("Error al guardar en fichero : " + e.toString());
           
        }finally{
            
            if(file != null) file.close();
        }
        
        
        
    }
    
    
    public static ArrayList<ClasificadorFuerte> leerClasificadorFuerte(String fileName) throws IOException{
    
        
        File file;
        FileReader      reader = null;
        BufferedReader  buffReader = null;
        
        ArrayList<ClasificadorFuerte> fuertes = new ArrayList(10);

        
        for( int i = 0; i <= 9; i++ ){
            
            ClasificadorFuerte clasificadorF = new ClasificadorFuerte();
            //fuertes.add(clasificadorF);
            
            try{

                file = new File(fileName);
                reader = new FileReader(fileName);
                buffReader = new BufferedReader(reader);

                String linea = buffReader.readLine();

                while( linea != null ){

                    if( "---".equals(linea) ){

                        ClasificadorDebil cDebil = new ClasificadorDebil();
                        cDebil.setPixel(Integer.valueOf(buffReader.readLine()));
                        cDebil.setUmbral(Integer.valueOf(buffReader.readLine()));
                        cDebil.setDireccion(Integer.valueOf(buffReader.readLine()));
                        cDebil.setError(Integer.valueOf(buffReader.readLine()));
                        cDebil.setConfianza(Integer.valueOf(buffReader.readLine()));

                        clasificadorF.addClasificadorDebil(cDebil);

                    }
                    
                    linea = buffReader.readLine();

                }


            }catch( IOException e ){
                
                System.err.println(" Error al leer el fichero : " + e.toString());

            }finally{
                    
                if( reader != null ) reader.close();
                
            }

            
            
        }

        return fuertes;
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
                
        //-----------------------------------------------------------
        //::::::::::::::::::::::::: ADABOOST ::::::::::::::::::::::::
        //-----------------------------------------------------------
        
        //int numPruebas  = 1000;
        //int numDebiles  = 100;
        int numAciertos = 0;
        
        long init, fin;

        
        ArrayList<Imagen>               imagenes;
        ArrayList<ClasificadorFuerte>   ClasificadoresFuertes;
        ArrayList<Integer>              mejoresH;
        
  
        Adaboost adaboost = new Adaboost(NUM_PORCENTAJE, NUM_CLASIFICADORES, NUM_PRUEBAS);
        
        
        if( args[0].equals("-t") ){
        
                    System.out.println("\nOpción entrenar : -t\n");

            
            init = System.currentTimeMillis();
            
            ClasificadoresFuertes = adaboost.getClasificadoresFuertes();
            
            //guardarClasificador(ClasificadoresFuertes, "clasificadorFuerte.txt");
            
            //mejoresH = adaboost.aplicarClasificadorFuerte( adaboost.getTest(), ClasificadoresFuertes);
            
            //numAciertos=  adaboost.cuentaAciertos( adaboost.getTest(), adaboost.getCorrecto(), mejoresH);
            
            fin = System.currentTimeMillis();

            
                    System.out.println("\nFuerte Guardado en " + "" );
                    System.out.println("\nreal vs fuerte");
                    System.out.println(adaboost.getCorrecto() );
                    //System.out.println(mejoresH);
                    System.out.println("\n** Resultados imagenes de testeo: ");
                    System.out.println( "Aciertos:  " + numAciertos );
                    System.out.println( "Fallos:    " + (adaboost.getTest().size() - numAciertos) );
                    //System.out.println("Porcentaje: " + (numAciertos*100/mejoresH.size()) +"%");
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
