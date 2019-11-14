/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;

/**
 * @author STALYN ALEJANDRO ALCOCER VALENCIA
 */
public class StrongSorter {
    
    private ArrayList<WeakSorter> wsList;
    private double confianza;
    private double error;
    
    private StrongSorter strongSorter;
    
    public StrongSorter(){
        
        wsList = new ArrayList<WeakSorter>();
        strongSorter = this;
    }
    
    public StrongSorter(ArrayList<WeakSorter> listaClasificadorDebil,ArrayList<Double> listaConfianza){
    
            wsList = listaClasificadorDebil;
            confianza = 0;
            for(Double d : listaConfianza){
                if(d > confianza) confianza = d;
            }
    
    }
    
    
    public void addClasificadorToLista(WeakSorter weaksorter){
        this.wsList.add(weaksorter);
    }
   
    
    public void inicializaPesos(double[] D){
        for(int i = 0; i < D.length; i++) D[i] = 1.0/D.length;
    }
    
    
    public WeakSorter mejorClasificador(ArrayList<WeakSorter> clasificador){
        WeakSorter mejor = null;
       
        for(WeakSorter ws: clasificador){
            if( (mejor == null) || ws.getError() < mejor.getError()) mejor = ws;
        }
        return mejor;
    }
    
    
    /**
     * 
     * @param listaEntrenamiento Conjunto de imágenes de entrenamiento.
     * @param numDebiles         Número de clasificadores débiles que deseamos generar para añadir a nuestro clasificador fuerte.
     * @param digitoEntrenar     El dígito que vamos a entrenar. Tenemos del 0 al 9.
     */
    public StrongSorter AlgoritmoAdaboost(ArrayList<Imagen> listaEntrenamiento, int numPruebasAleatorias, int numeroClasificadoresDebiles, int digitoEntrenar){
        
        int res;
        double sumatorio = 0;
        
        double N = (double)listaEntrenamiento.size();
        double[] D = new double[(int)N];     //Vector de pesos inicializado a núm. de imágenes que hay.

        for(int i = 0 ; i < listaEntrenamiento.size(); i++) D[i] = 1/N; //Inicializo los pesos de las imágenes a 1.
                                                                        //Nuestro vector de pesos va a determinar cuál es el peso de cada imágen
                                                                        //Si nuestro clasificador falla, el peso de la imágen aumenta.
                                                                
        ArrayList<WeakSorter> listaDebiles = null;
        ArrayList<Double> listaConfianza = null;
        
        for(int i = 0; i < numeroClasificadoresDebiles; i++){   //Número de Clasificadores: 100. Después extraeré el mejor de estos estos.

            WeakSorter bestwS = null;
            double minError = Double.POSITIVE_INFINITY;
            
            //ENTRENAR Ht TENIENDO EN CUENTA Dt

            listaDebiles = new ArrayList<WeakSorter>();
            listaConfianza = new ArrayList<>();
            
            int[] acierto = new int[(int)N];

            for(int j = 0; j < numPruebasAleatorias; j++){
              
                WeakSorter wS = generarClasificadoresAzar(28*28);
                minError = obtenerErrorClasificador(wS, listaEntrenamiento, digitoEntrenar, D, acierto);
                
                if(wS.getError() < minError)
                {
                    minError = wS.getError();
                    bestwS = wS;
                }
            }
            
            double auxC = bestwS.getConfianza();
            double numerador = (double)(1 - minError)/minError;
            auxC = (double) 0.5 * Math.log(numerador);
            
            listaDebiles.add(bestwS);
            listaConfianza.add(auxC);
            
            int z = 0;
            double[] auxD = D;
            
            actualizarDt(listaEntrenamiento, bestwS, D, acierto ,digitoEntrenar);
        }
        
        return new StrongSorter(listaDebiles, listaConfianza);
    }
    
    public void actualizarDt(ArrayList<Imagen> lista, WeakSorter best, double[] D, int[] acierto ,int digito){
        int res = -1;
        
        for(int i = 0; i < D.length; i++){
            if(digito == lista.get(i).getDigitoPertenece()) res = 1;
            else res = -1;
            
            if(acierto[i] == -1 && res == 1) D[i] = (double)(D[i] * Math.pow(Math.E, best.getConfianza()));
            else
            {
                if(acierto[i] == 1 && res == -1)  D[i] = (double)(D[i] * Math.pow(Math.E, best.getConfianza()));
                else  D[i] = (double)(D[i] * Math.pow(Math.E, -1 * best.getConfianza()));
            }
        }
        
        double z = 0;
        for( int i = 0; i < D.length; i++){
            z += D[i];
        }
        
        if(z != 1){
            for(int i = 0; i < D.length; i++){
                D[i] = D[i]/z;
            }
        }
        
    }
    
    
    public WeakSorter generarClasificadoresAzar(int DIM){
        return new WeakSorter(DIM);
    }
    
    public double obtenerErrorClasificador(WeakSorter ws, ArrayList<Imagen> lista , int y, double[] D, int[] acierto){
        double error = 0.0;
        
        for(int i = 0; i < D.length; i++){
            int res = aplicarClasificadorDebil(ws, lista.get(i), acierto);
            acierto[i] = res;
            if( res != y) error += D[i];
        }   
        return error;
    }
    
    
    public int aplicarClasificadorDebil(WeakSorter ws, Imagen imagen, int[] acierto){
        int valido = -1;
        
        byte[] imagenData = imagen.getImageData();
        
        if(ws.getDireccion() == 1)
        {
            if(imagenData[ws.getPixel()] >= ws.getUmbral())
            {
                valido = 1;
            }
        }
        else
        {
            if(imagenData[ws.getPixel()] < ws.getUmbral())
            {
                valido = 1;
            }
        }
        
        return valido;
        
    }
       
    //::::::::::::::::::::::::::::GET::::::::::::::::::::::::::::::::::::::://
    public StrongSorter getClasificadorFuerte(){
        return strongSorter;
    }
    
    public ArrayList<WeakSorter> getListaClasificadores(){
        return wsList;
    }
   
    public double getConfianza(){
        return confianza;
    }
    
    public double getError(){
        return error;
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::://
}
