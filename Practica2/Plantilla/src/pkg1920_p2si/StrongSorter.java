/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1920_p2si;
import java.util.*;
/**
 *
 * @author STALYN ALEJANDRO ALCOCER VALENCIA
 */
public class StrongSorter {
    private ArrayList<WeakSorter> ws;
    private double confianza;
    private double error;
    
    public StrongSorter(){
        ws = new ArrayList<WeakSorter>();
    }
    
    public double getConfianza(){
        return confianza;
    }
    
    public double getError(){
        return error;
    }
    
    public ArrayList<WeakSorter> getClasificadores(){
        return ws;
    }
    
    public void addClasificador(WeakSorter ws){
        this.ws.add(ws);
    }
    
}
