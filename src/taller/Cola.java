package taller;

/**
 * @(#)Cola.java
 *
 *
 * @author 
 * @version 1.00 2009/5/20
 */


import java.util.*;

public class Cola{
    LinkedList<Cliente> cola;
    //
    public Cola(){
        cola=new LinkedList<Cliente>();
    }
    //
    public synchronized void encolar(Cliente c){
        cola.add(c);
    }
    //
    public synchronized Cliente decolar(){
        return cola.removeFirst();
    }
    //
    public synchronized Cliente primero(){
        return cola.getFirst();
    }
    //
    public boolean vacia(){
        return cola.isEmpty();
    }
    //
    public int tamano(){
        return cola.size();
    }
    //
    public synchronized LinkedList<Cliente> list(){
        return cola;
    }
    //
    public synchronized int posicion(Cliente c){
        return cola.indexOf(c);
    }
    //
    public synchronized  Cliente get(int pos){
        return cola.get(pos);
    }
    //
    private synchronized void dormir(){
        try{Thread.sleep(10);}catch(Exception e){};
    }
    //
    public synchronized Cliente getUltimo(){
        return this.get(tamano()-1);
    }
        
}
