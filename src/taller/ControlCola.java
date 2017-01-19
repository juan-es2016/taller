package taller;

/**
 * @(#)ControlCola.java
 *
 *
 * @author 
 * @version 1.00 2008/11/25
 */
import javax.swing.JLabel;

public class ControlCola {

    private static int anchoPersona;
    
    public ControlCola(){
    	
    }
    public ControlCola(Cola c) {
    	anchoPersona=10;
    }
   	
    //-----------------------------------------------------------------------------------------------------------
    //controla que solo avance el primero de la cola, el resto se queda esperando
    public synchronized void avanzaElPrimero(Cliente c,Cola cola){
    	Cliente aux;
    	aux=c;
    	while(aux!= cola.get(0)){
    		try {
				wait();
			}
			catch (Exception ex) {
			}
    	}
    }
    //despues de realizar su proceso despierta a los demas hilos
    public synchronized void liberarPrimero(Cola cola)
    {	
    	Cliente aux=cola.decolar();
    	notifyAll();
    }
   //funcion extra que verifica la posicion del cliente
    public synchronized void verificarPos(Cola cola, int Xfin, String direccion){
    	Cliente c1=new Cliente();
    	JLabel l1=new JLabel();
    	
    	if(direccion == "este"){    
	    	for (int i =cola.tamano()-1; i>=0; i=i-1){
	    		c1=cola.get(i);
	    		//l1=c1.getPunto();
	    		l1.setLocation(Xfin-(20*i),l1.getY());
	    	}	
	    }
    	else{
    		for (int i =cola.tamano()-1; i>=0; i=i-1){
	    		c1=cola.get(i);
	    		///l1=c1.getPunto();
	    		l1.setLocation(Xfin+(20*i),l1.getY());
	    	}
    	}
    	
    }
    //funcion que realiza el movimiento del label
    public void mover(JLabel punto,int pos){
    	int x=punto.getX();
    	int y=punto.getY();
    	int paso=10;
    	if(x>pos)
    		paso=-10;
    	while (x!=pos){
    		x=x+paso;
    		punto.setLocation(x,y);
    		try {
				Thread.sleep(70);
			}
			catch (Exception ex) {
			}
    	}
    }
}