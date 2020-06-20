/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;
import java.io.*;
/**
 *
 * @author stalynalejandro
 */
public class Adaboost {
    
    private int porcentaje;
    private int debiles;
    private int pruebas;
        
    private ArrayList test                             = new ArrayList();    
    private ArrayList<ArrayList> entrenamiento         = new ArrayList();
    private ArrayList<ArrayList> correcto              = new ArrayList();
    
    
    //Array auxiliar
    private ArrayList tipo = new ArrayList();
    
    
    public MNISTLoader ml = new MNISTLoader();

    /**
     * @param porcentaje
     */
    Adaboost(int porcentaje, int debiles, int pruebas){
        
        this.porcentaje = porcentaje;
        this.debiles    = debiles;
        this.pruebas    = pruebas;
        this.ml.loadDBFromPath("./mnist_1000");
        
        clasificarImagenes();
    }
    
    
    private void clasificarImagenes(){
        
        int tipo = 0;
        
        for( int i = 0; i <= 9 ; i++ ){
        
            ArrayList imagenes = this.ml.getImageDatabaseForDigit(i);
           
            ArrayList imagenesEntrenar = new ArrayList();


            
            int numImagenesPorDigito = imagenes.size();
   
            this.correcto.add(i, imagenes);
            
            
            
            for( int j = 0; j < numImagenesPorDigito; j++ ){
                
                Imagen img = (Imagen)imagenes.get(j);
 
                
                if(j < ((this.porcentaje * numImagenesPorDigito)/100)){
                    
                    imagenesEntrenar.add(img);

                    this.entrenamiento.add(i, imagenesEntrenar);
                    
                }else{
                    
                    test.add(img);
                    
                }

            }
            
            System.out.println("\n\nDIGITO: " + i);
            System.out.println("correcto      : " + this.correcto.get(i).size() );
            System.out.println("entrenamiento : " + entrenamiento.get(i).size());
            System.out.println("test          : " + test.size() );
            System.out.println("...........................................");



        }
    
        /*
        System.out.println("entrenamiento : " + entrenamiento.get(0).size());
        System.out.println("test          : " + test.size() );
    
        System.out.println("correcto : " + this.correcto.get(0).size() );
        */
    }
    
    

    
    public ArrayList<ClasificadorFuerte> getClasificadoresFuertes(){
        
        ArrayList<ClasificadorFuerte> fuertes = new ArrayList();
        
        for( int i = 0; i <= 9; i++ ){
           
            fuertes.add( aplicarAdaboost((ArrayList)getEntrenamiento().get(i), (ArrayList) getCorrecto().get(i), i) );
            
        }
        
        return fuertes;
    }
    
    
    
    public ArrayList<Integer> aplicarClasificadorFuerte( ArrayList test, ArrayList<ClasificadorFuerte> fuertes  ){
       
        ArrayList<Integer> h = new ArrayList();
        h.add(1);
        return h;
    }
    
        
    public ClasificadorFuerte aplicarAdaboost( ArrayList<Imagen> entrenamiento, ArrayList correctos, int digito){
        
        //Aquí hay que implementar el pseudocódigo de ADABOOST
        ClasificadorFuerte fuerte = new ClasificadorFuerte();
        
        float numerador = 0;
        
        //Inicialización de la DISTRIBUCIÓN DE PESOS.
        float pesoInicial = 1.0f / entrenamiento.size();
        
        //Función de ORDEN SUPERIOR.
        entrenamiento.forEach((img)->{ img.setPeso(pesoInicial); });
        
        System.out.println("\n\nDIGITO : " + digito);
        System.out.println("Entrenamiento : " + entrenamiento.size());
        System.out.println("Correctos     : " + entrenamiento.size());

        
        //Clasificadores a entrenar
        for( int i = 0; i < Main.NUM_CLASIFICADORES; i++ ){
            
            ClasificadorDebil cDebil = new ClasificadorDebil();
            
            cDebil.setError(entrenamiento, correctos);
            
            
            //Entreno a los clasificadores debiles
            
            for( int j = 0 ; j < Main.NUM_PRUEBAS; j++ ){
                ClasificadorDebil auxDebil = new ClasificadorDebil();
                auxDebil.setError(entrenamiento, correctos);
                
                if(auxDebil.getError() < cDebil.getError()) cDebil = auxDebil;
            }
            
            
            //Guardo el mejor clasificador Debil
            fuerte.addClasificadorDebil(cDebil);
            
            ArrayList<Boolean> clasificados = cDebil.aplicarDebil(entrenamiento);
            
            
            //Actualizar los pesos de las imágenes.
            float z = 0.0f;
            float num = 0.0f;
            float peso = 0.0f;
            
            for( int j = 0; j < entrenamiento.size(); j++ ){
                
                peso = entrenamiento.get(j).getPeso();
                
                if( clasificados.get(j).equals(correctos.get(j))) num = peso * (float)Math.pow(Math.E, -cDebil.getConfianza());
                else num = peso * (float)Math.pow(Math.E, cDebil.getConfianza());
                
                
                entrenamiento.get(j).setPeso(num);
                
                z += num;
            }
            
            
            //Normalizar pesos.
            for( int j = 0; j < entrenamiento.size(); j++ ){
                
                peso = entrenamiento.get(j).getPeso() / z;
                entrenamiento.get(j).setPeso(peso);
                
            }
            
            
        }
        
               
        System.out.println("\t\t\tFuerte Size : " + fuerte.getClasificadoresDebiles().size());

    
        return fuerte;
        
    }
    
    
    public int cuentaAciertos( ArrayList test, ArrayList correcto, ArrayList<Integer> mejoresH ){
        return 0;
    }
    

    
    
    
    public ArrayList getEntrenamiento(){
        return this.entrenamiento;
    }
    
    public ArrayList getCorrecto(){
        return this.correcto;
    }
    
    public ArrayList getTest(){
        return this.test;
    }
    
    
    
}
