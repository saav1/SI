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
    
    public void AlgoritmoAdaboost(ArrayList<Imagen> entrenamiento, int cuantosDebiles, int numero){
        int res;
        double sumatorio = 0;
        double[] D = new double[entrenamiento.size()];
        inicializaPesos(D);
        
        for(int i = 0; i < 200; i++){
            ArrayList<WeakSorter> debiles = new ArrayList<WeakSorter>();
            WeakSorter mejor = null;
            
            for(int j = 0; j < cuantosDebiles; j++){
                WeakSorter auxWeak = new WeakSorter();
                auxWeak.Entrenar(entrenamiento, D, numero);
                debiles.add(auxWeak);
            }
            
            mejor = mejorClasificador(debiles);
            int[] binario = new int[entrenamiento.size()];
            
            //mejor.AplicaPixel(entrenamiento, binario);
            
            //Actualizar Dt + 1
            //ActualizaDT(entrenamiento, mejor, D, binario, numero);
            
            strongSorter.addClasificadorToLista(mejor);
            
        }
    }
    
    public int Clasifica(Imagen img){
        WeakSorter weak = null;
        int aplicado = 0;
        double confianza = 0.0;
        double suma = 0.0;
        
        for( int i = 0; i < strongSorter.getListaClasificadores().size() ; i++ ){
            weak = strongSorter.getListaClasificadores().get(i);
            //aplicado = weak.AplicaImagen();
            confianza = weak.getConfianza();
            suma = suma + (aplicado * confianza);
        }
    
        
        if(suma > 0) return 1;
        else if (suma < 0) return 0;
        else return 0;
    }
   
    //String result = (time < 18) ? "Good day." : "Good evening.";
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
