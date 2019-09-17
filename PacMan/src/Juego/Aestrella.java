package Juego;

import java.util.Date;
import java.util.Random;
import java.util.*;

public class Aestrella {
	
    public boolean pacman;
    public int numeroFantasma = 0;  
    
    //Camino
    char camino[][];    
    
    //Casillas expandidas
    int camino_expandido[][];    
    
    //Número de nodos expandidos
    int expandidos;     
    
    //Coste del camino
    double coste_total;

	
    Aestrella(boolean esPacman){
    	pacman = esPacman;         
    }
	
    void setNumFantasma(int num){
	numeroFantasma = num;
    }
	
    public int run(int controlador, Laberinto laberinto){
	int mov = 0;
		
	switch(controlador){
            case Juego.ALEATORIO:
			mov = aleatorio(laberinto);
			break;
            case Juego.AESTRELLA:
			mov = A(laberinto);
			break;
	}
		
	return mov;
    }
	
    // Genera un movimiento aleatorio a partir del mapa proporcionado
    int aleatorio(Laberinto laberinto){
       	boolean correcto = false;
	Random rnd = new Random();
	Date d = new Date();
	int mov = -1;
		
	rnd.setSeed(d.getTime());
		
	while (!correcto){
            Laberinto aux = new Laberinto(laberinto);
		
            mov = (int)(rnd.nextDouble() * 4) + 5;                        
            if (pacman){
		if (aux.moverPacman(mov) != -1)
                    correcto = true;
            }
            else {
                if (aux.moverFantasma(numeroFantasma,mov) != -1)
                    correcto = true;
            }
	}
		
	return mov;
    }
	
    // Genera un movimiento a partir del algoritmo A*
    int A(Laberinto laberinto){
	int mov = 0;
		
	if (pacman)
            mov = AestrellaPacman(laberinto);                        
        else                                      			
            mov = AestrellaFantasma(laberinto);
                		
	return mov;
	}
	
    //Inicializa las matrices camino y camino expandido
    void inic (int tam){
        
        camino= new char[tam][tam];    
        //Casillas expandidas
        camino_expandido= new int[tam][tam];    
        
        //Inicializa las variables camino y camino_expandidos donde el A* debe incluir el resultado
        for(int i=0;i<tam;i++)
                for(int j=0;j<tam;j++){
                    camino[j][i] = '.';
                    camino_expandido[j][i] = -1;
                }
        //Número de nodos expandidos
        expandidos=0;
    }   
           
    //////////////////////////////
    // A ESTRELLA PARA FANTASMA //
    //////////////////////////////
    int AestrellaFantasma(Laberinto laberinto){
        int result=0; //Devuelve el movimiento a realizar         
        boolean encontrado=false;        
                    
        inic(laberinto.tam());        
        
        //AQUI ES DONDE SE DEBE IMPLEMENTAR A*

        
        //Si ha encontrado la solución, es decir, el camino, muestra las matrices camino y camino_expandidos y el número de nodos expandidos
        if(encontrado){
            //Mostrar la solucion
            System.out.println("NO MODIFICAR ESTE FORMATO DE SALIDA");
            System.out.println("Coste del camino: "+coste_total);
            System.out.println("Nodos expandidos: "+expandidos);            
            System.out.println("Camino"); 
            mostrarCamino(camino, laberinto.tam());
            System.out.println("Camino explorado");
            mostrarCaminoExpandido(camino_expandido,laberinto.tam());            
        }

        return result;    
        
    }
	     
    //////////////////////////////
    // A ESTRELLA PARA PACMAN //
    //////////////////////////////
    int AestrellaPacman(Laberinto laberinto){
	int result=0; //Devuelve el movimiento a realizar   
        
         //AQUI ES DONDE SE DEBE IMPLEMENTAR EL CODIGO PARA PACMAN
        
        return result;
    }
    
    
    //Muestra la matriz que contendrá el camino después de calcular A*
    public void mostrarCamino(char camino[][], int tam){
        for (int i=0; i<tam; i++){ 
            for(int j=0;j<tam; j++){
                System.out.print(camino[i][j]+" ");
            }
            System.out.println();   
        }
    }
    
    //Muestra la matriz que contendrá el orden de los nodos expandidos despuÃ©s de calcular A*
    public void mostrarCaminoExpandido(int camino_exp[][], int tam){
        for (int i=0; i<tam; i++){
            for(int j=0;j<tam; j++){
                if(camino_exp[i][j]>-1 && camino_exp[i][j]<10)
                    System.out.print(" ");
                System.out.print(camino_exp[i][j]+" ");
            }
            System.out.println();   
        }
    } 
}
    