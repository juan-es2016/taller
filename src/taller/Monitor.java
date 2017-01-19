package taller;
/**
 * @(#)Monitor.java
 *
 *
 * @author 
 * @version 1.00 2009/5/20
 */


import java.util.*;								
public class Monitor
{

    public static int clientesEnLlamadas;
    public static int clientesEstimacion;
    public static int rechazadasEnLlamadas;
    public static int clientesSiniestros;
    public static int clientesInvestigacion;
    public static int clientesFraude;
    public static int tamanioColasGastos[];
    public static int clientesGastos[];
    public static int clientesNegociacion;
    public static int clientesTaller;
    public static int personasSistema;
    public static int personasPagadas;
    
    public static int colaInvestigacion;
    
    public static int Qfiscal;
    public static int Dfiscal;
    public static int Dpolicial;
    public static int flagrante;
    public static int imputacion;
    public static int total;
    public static int rechaso;
    public static int salida;
    public static int m_atencionLlamada;
    public static int m_atencionSiniestros;
    public static int m_atencionEstimacionGastos;
    public static int m_atencionInvestigacion;
    public static int m_atencionNegociacion;
     public static int tiempo;
    public static int velo;
    
    public static ArrayList<Double> t_atencionLlamada;
    public static ArrayList<Double> t_atencionSiniestros;
    public static ArrayList<Double> t_atencionEstimacionGastos;
    public static ArrayList<Double> t_atencionInvestigacion;
    public static ArrayList<Double> t_atencionNegociacion;
    public static ArrayList<Double> t_atencionTaller;   
    
    
    public Monitor()
    {
        clientesEnLlamadas=0;
        rechazadasEnLlamadas=0;
        clientesSiniestros=0;
        clientesInvestigacion=0;
        clientesFraude=0;
        tamanioColasGastos=new int[20];
        clientesGastos=new int[20];
        clientesNegociacion=0;
        clientesTaller=0;
        personasSistema=1;
        
        colaInvestigacion=0;
        
        m_atencionLlamada = 0;
        m_atencionSiniestros = 0;
        m_atencionEstimacionGastos = 0;
        m_atencionInvestigacion = 0;
        m_atencionNegociacion = 0;
        tiempo=0;
        
        t_atencionLlamada = new ArrayList<Double>();
        t_atencionSiniestros = new ArrayList<Double>();
        t_atencionEstimacionGastos = new ArrayList<Double>();
        t_atencionInvestigacion = new ArrayList<Double>();
        t_atencionNegociacion = new ArrayList<Double>();
        
    }
    //METODOS SET
    public static synchronized void velocidad(double ve){
        velo=(int)ve;
    }
     public static synchronized void incrementarQfiscal(){
        Qfiscal ++;
    }
      public static synchronized void incrementarDfiscal(){
        Dfiscal ++;
    }
       public static synchronized void incrementarDpolicial(){
        Dpolicial ++;
    }
       public static synchronized void incrementarFlagrante(){
        flagrante ++;
    }
        public static synchronized void incrementarImputacion(){
        imputacion ++;
    }
          public static synchronized void incrementarRechaso(){
        rechaso ++;
    }
              public static synchronized void total(){
        total=Qfiscal+Dfiscal+Dpolicial+flagrante;
    }
        public static synchronized void incrementarSalidas(){
        salida ++;
    }
    public static synchronized void incrementarLlamadas(){
        clientesEnLlamadas ++;
    }
    public static synchronized void incrementarEstimacion(){
        clientesEstimacion ++;
    }
    public static synchronized void incrementarRechazadasLlamadas(){
    	rechazadasEnLlamadas ++;
    }
    public static synchronized void incrementarSiniestros(){
    	clientesSiniestros ++;
    }
    public static synchronized void incrementarInvestigacion(){
    	clientesInvestigacion ++;
    }
    public static synchronized void incrementarFraude(){
    	clientesFraude ++;
    }
    public static synchronized void incrementarColaGastos (int mesaG){
    	tamanioColasGastos[mesaG] ++;
    }
    public static synchronized void incrementarClientesGastos(int mesaG){
    	clientesGastos [mesaG] ++;
    }
    public static  synchronized void incrementarNegociacion(){
    	clientesNegociacion ++;
    }
    public static synchronized void incrementarTaller(){
    	clientesTaller ++;
    }
    public  static synchronized void incrementarPersonasSistema(){
        personasSistema ++;
    }
    public  static synchronized void casosFinalisados(){
        personasPagadas = clientesTaller+clientesNegociacion;
    }
    public  static synchronized void decrementarColaGastos (int mesaG){
    	tamanioColasGastos[mesaG] --;
    }
    public static synchronized void decrementarPersonasSistema(){
        personasSistema --;
    }
    
    ///////////////////////////////////////////////////////////////////distribucion
    
    public static synchronized void atencionLlamadas(int x){
    	m_atencionLlamada = x;
    }
    public static  synchronized void atencionSiniestros(int x){
    	m_atencionSiniestros = x;
    }
    public static synchronized void atencionInvestigacion(int x){
    	m_atencionInvestigacion = x;
    }
    public  static synchronized void atencionEstimacion(int x){
    	m_atencionEstimacionGastos = x;
    }
    public  static synchronized void atencionNegociacion(int x){
    	m_atencionNegociacion = x;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    
    public  static synchronized void incrementarColaInvestigacion (){
    	colaInvestigacion ++;
    }
    public static synchronized void decrementarColaInvestigacion(){
        colaInvestigacion --;
    }
    public static synchronized void setHora( int r){
       
     tiempo =r;
    }
    
    public static synchronized int Hora(){
        
        return tiempo;
       }
    
    //
    
    
 ///////////////////////////////////////////////////////////////////////ArrayList
    
    
    public void insertarTiempoLlamanda(Double t){
        t_atencionLlamada.add(t);
    }
    public void insertarTiempoSiniestros(Double t){
        t_atencionSiniestros.add(t);
    }
    public void insertarTiempoEstimacion(Double t){
        t_atencionEstimacionGastos.add(t);
    }
    public void insertarTiempoInvestigacion(Double t){
        t_atencionInvestigacion.add(t);
    }
    public void insertarTiempoNegociacion(Double t){
        t_atencionNegociacion.add(t);
    }
    public void insertarTiempoTaller(Double t){
        t_atencionTaller.add(t);
    }
    
    //
    public ArrayList<Double> obtenerTiempoLlamanda(){
        return t_atencionLlamada;
    }
    public ArrayList<Double> obtenerTiempoSiniestros(){
    	return t_atencionSiniestros;
    }
    public ArrayList<Double> obtenerTiempoEstimacion(){
    	return t_atencionEstimacionGastos;
    }
    public ArrayList<Double> obtenerTiempoInvestigacion(){
    	return t_atencionInvestigacion;
    }
    public ArrayList<Double> obtenerTiempoNegociacion(){
    	return t_atencionNegociacion;
    }
    public ArrayList<Double> obtenerTiempoTaller(){
    	return t_atencionTaller;
    }
    
    /////
    
    
    //METODOS GET
    public  static synchronized int getClientesEnLlamadas(){
        return clientesEnLlamadas;
    }
    public  static synchronized int getClientesRechazadasEnLlamadas(){
    	return rechazadasEnLlamadas;
    }
    public  static synchronized int getClientesSiniestros(){
    	return clientesSiniestros;
    }
    public  static synchronized int getClientesInvestigacion(){
    	return clientesInvestigacion;
    }
    public  static synchronized int getClientesFraude(){
    	return clientesFraude;
    }
    public  static synchronized int getTamanioColasGastos(int pos){
    	int tam = tamanioColasGastos[pos];
        return tam;
    }
    public  static synchronized int getClientesGastos(){
        int total = 0;
        for (int i = 0; i<clientesGastos.length; i++)
            total = total + clientesGastos[i];
        return total;
    }
    public  static synchronized int getClientesGastos(int pos){
        int cant = clientesGastos[pos];
        return cant;
    }    
    public  static synchronized int getClientesNegociacion(){
    	return clientesNegociacion;
    }
    public  static synchronized int getClientesTaller(){
    	return clientesTaller;
    }
    public  static synchronized int getPersonasSistema(){
        return personasSistema;
    }

    
    //
    public  static synchronized void reiniciar(){
        clientesEnLlamadas=0;
        rechazadasEnLlamadas=0;
        clientesSiniestros=0;
        clientesInvestigacion=0;
        clientesFraude=0;
        
        for (int i = 0; i<tamanioColasGastos.length; i++)
        	tamanioColasGastos[i] = 0;
        
        for (int i = 0; i<clientesGastos.length; i++)
        	clientesGastos[i] = 0;        
        
        clientesNegociacion=0;
        clientesTaller=0;
    }
    
}

