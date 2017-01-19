package taller;

import Matematicas.PruebaMatematica;
import Matematicas.VariableAleatoria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.tools.JavaCompiler;


public class Panel123 extends JPanel {
	///
    private JComboBox cb_meses;
    private JComboBox dias;
    
    
    private JComboBox cb_d_llamadas;
    private JComboBox cb_d_siniestros;
    private JComboBox cb_d_estimacion;
    private JComboBox cb_d_investigacion;
    private JComboBox cb_d_negociacion;
    ///
    private JLabel fondo;
    private ArrayList<JLabel> operadoresllamadas;
    private ArrayList<JLabel> listaSiniestros;
    private ArrayList<JLabel> listaGastos;
    private JLabel investigacion;
    private JLabel fraudes;
    private JLabel negociacion;
    private JLabel taller1;
    private JLabel taller2;
    private JLabel taller3;
    private JLabel nota;
    private int gastos;
    private int investigaciones;
    private int llamadas;
    private int siniestros;
    private int numhil=20;
     double porciontiempo=0;
    Timer timer = new Timer();
    Monitor mono;
    private VariableAleatoria va;
    JSlider jSlider1;
    
    /////////////////////////////////////////////////////////////variablesssssssssssss
    private int tiempoEntreLlegadas;
 
    private int cantidadC;
    //////////////////////////////////////////////////////////////////////////////////
    
    private ArrayList<Cliente> clientes=new ArrayList<Cliente>();
    
    private ArrayList<JLabel> lisLabel =new ArrayList<JLabel>();
    ThreadGroup grupoHilo;
   
    
    public Panel123(Monitor mon,int gastos,int investigaciones,int llamadas,int siniestros){    	
    	setLayout(null);
    	this.gastos = gastos;
    	this.investigaciones = investigaciones;
    	this.llamadas = llamadas;
    	this.siniestros = siniestros;
    	this.mono = mon;
		//setBackground(Color.black);
		//setUndecorated(true);
		va = new VariableAleatoria();
	    tiempoEntreLlegadas=10;
	    cantidadC=0;
		
		operadoresllamadas=new ArrayList<JLabel>();
		listaSiniestros=new ArrayList<JLabel>();
		listaGastos = new ArrayList<JLabel>();
		
		
	    /////////////////////////////////////////////////////labelss
	    for (int i = 0; i < 60; i++) {
	    	
	    	JLabel lab=new JLabel();
	    	//lab.setIcon(new ImageIcon("Imagenes/CaminaD.gif"));
                lab.setIcon(new ImageIcon(getClass().getResource("/images/CaminaD.gif")));
                //lab.setIcon(imagen);
		    lab.setBounds(-50, 315, lab.getIcon().getIconWidth(),lab.getIcon().getIconHeight());
		    this.add(lab);
		    lisLabel.add(lab );
	    	
		}
        
		crearBotones();
		llamadas();
		siniestros();
		gastos();
		fijos();
		crearFondo();
		
		setVisible(true);
    }//Fin Constructor;
    
    public void crearFondo(){
		//fondo.setIcon(new ImageIcon(escala));
                 //Dimension tamanio= getSize();
     ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/images/fondosimulacion.jpg"));
       // g.drawImage(imagenFondo.getImage(),0,0,tamanio.width,tamanio.height,null);
     //setOpaque(false);
      //super.paintComponent(g);
    	fondo=new JLabel();
    	//fondo.setLayout(null);
    	fondo.setIcon(imagenFondo);
    	fondo.setBounds(0,-28,1300,750);
    	fondo.setVisible(true);
        add(fondo);
        
        //nota=new JLabel();
    	//fondo.setLayout(null);
        //ImageIcon imagenNota = new ImageIcon(getClass().getResource("/images/nota.png"));
    	//nota.setIcon(imagenNota);
       //nota.setBounds(950,30,250,200);
    	//nota.setVisible(true);
      // fondo.add(nota);
    }
	
    public void crearBotones(){
    	
    	jSlider1 =new JSlider(SwingConstants.HORIZONTAL,0,200,10);
		cb_meses = new JComboBox();
		cb_meses.addItem("Enero");
		cb_meses.addItem("Febrero");
		cb_meses.addItem("Marzo");
		cb_meses.addItem("Abril");
		cb_meses.addItem("Mayo");
		cb_meses.addItem("Junio");
		cb_meses.addItem("Julio");
		cb_meses.addItem("Agosto");
		cb_meses.addItem("Septiembre");
		cb_meses.addItem("Octubre");
		cb_meses.addItem("Noviembre");
		cb_meses.addItem("Diciembre");
		cb_meses.setBounds(20, 20, 80, 20);
		//add(cb_meses);
		
		dias = new JComboBox();
		dias.addItem("Lunes");
		dias.addItem("Martes");
		dias.addItem("Miercoles");
		dias.addItem("Jueves");
		dias.addItem("Viernes");
		dias.setBounds(110, 20, 80, 20);
		//add(dias);
		
		cb_d_llamadas = new JComboBox();
		cb_d_llamadas.addItem("Exponencial");
		cb_d_llamadas.addItem("Uniforme");
		cb_d_llamadas.addItem("Normal");
		cb_d_llamadas.addItem("Trans. Inversa");
		cb_d_llamadas.setBounds(200, 60, 80, 20);
		//add(cb_d_llamadas);

		cb_d_siniestros = new JComboBox();
		cb_d_siniestros.addItem("Trans. Inversa");
		cb_d_siniestros.addItem("Normal");
		cb_d_siniestros.addItem("Exponencial");
		cb_d_siniestros.addItem("Uniforme");
		cb_d_siniestros.setBounds(470, 28, 80, 20);
		//add(cb_d_siniestros);

		cb_d_negociacion = new JComboBox();
		cb_d_negociacion.addItem("Trans. Inversa");
		cb_d_negociacion.addItem("Normal");
		cb_d_negociacion.addItem("Exponencial");
		cb_d_negociacion.addItem("Uniforme");
		cb_d_negociacion.setBounds(780, 45, 80, 20);
		//add(cb_d_negociacion);

		cb_d_investigacion = new JComboBox();
		cb_d_investigacion.addItem("Normal");
		cb_d_investigacion.addItem("Exponencial");
		cb_d_investigacion.addItem("Uniforme");
		cb_d_investigacion.addItem("Trans. Inversa");
		cb_d_investigacion.setBounds(30, 540, 80, 20);
		//add(cb_d_investigacion);
		
		cb_d_estimacion = new JComboBox();
		cb_d_estimacion.addItem("Uniforme");
		cb_d_estimacion.addItem("Normal");
		cb_d_estimacion.addItem("Exponencial");
		cb_d_estimacion.addItem("Uniforme");
		cb_d_estimacion.addItem("Trans. Inversa");
		cb_d_estimacion.setBounds(247, 630, 80, 20);
		//add(cb_d_estimacion);
		
    
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void llamadas(){
    	int x=75;
    	int y=75;
    	for(int i=0;i<llamadas;i++){	
    		JLabel llamadas1=new JLabel(new ImageIcon(getClass().getResource("/images/oficina.gif")));
                //ImageIcon imagenNota = new ImageIcon(getClass().getResource("/images/oficina.png"));
    		llamadas1.setLayout(null);
    		llamadas1.setBounds(x,y,50,56);
    		this.add(llamadas1);
    		//x=x;  
    		operadoresllamadas.add(llamadas1);
    		if(i==1){
    			y=225;
    			//x=45;
    		}else if(i==2){
                y=375;}
                else y=525;
    	}	
	}
    
    public void siniestros(){
    	int x=500;
	    int y=330;
	    for(int i=0;i<siniestros;i++){	
	    	//JLabel siniestros1=new JLabel(new ImageIcon("/images/agente0.png"));
                JLabel siniestros1=new JLabel(new ImageIcon(getClass().getResource("/images/agente0.png")));
	    	siniestros1.setLayout(null);
	    	
	         siniestros1.setBounds(x,y,50,56);
	         this.add(siniestros1);
	         x=x+50+10;  
	         operadoresllamadas.add(siniestros1);
	    	 if(i%5==0 && i!=0){
	    		 y=y+130;
	    		 x=325;
	    	 }
	    }	
	}
    
    public void gastos(){
    	int x=768;
	      int y=75;
	    for(int i=1;i<=gastos;i++){	
	    	JLabel estimaciones=new JLabel(new ImageIcon(getClass().getResource("/images/estimacion.gif")));
	    	estimaciones.setLayout(null);
	    	
	         estimaciones.setBounds(x,y,50,56);
	         this.add(estimaciones);
	         //x=x-200;  
	         operadoresllamadas.add(estimaciones);
	    	 //if(i%3==0){
	    		// y=y-70;
	    		// x=830;
	    	 //}
                 if(i==1){
    			y=225;
    			//x=45;
    		}else if(i==2){
                y=375;}
                else y=525;
	    }	
    }
    
    public void fijos(){
    	//investigacion=new JLabel(new ImageIcon(getClass().getResource("/images/sospechas1.gif")));//sospechas1
    	//investigacion.setLayout(null);
    	//investigacion.setBounds(60,569,120,60);
    	//add(investigacion);
		
		//fraudes=new JLabel(new ImageIcon(getClass().getResource("/images/fraudes.gif")));
		//fraudes.setLayout(null);
		//fraudes.setBounds(37,440,50,78);
		//add(fraudes);
		
		negociacion=new JLabel(new ImageIcon(getClass().getResource("/images/negocio0.gif")));//negocio1.gif
		negociacion.setLayout(null);
		negociacion.setBounds(1200,80,100,90);
		add(negociacion);
		
		//taller1=new JLabel(new ImageIcon(getClass().getResource("/images/auto2.gif")));
    	//taller1.setLayout(null);
    	//taller1.setBounds(1105,365,132,65);
		//add(taller1);
		
		//taller2=new JLabel(new ImageIcon(getClass().getResource("/images/auto2.gif")));
    	//taller2.setLayout(null);
    	//taller2.setBounds(1105,470,132,65);
		//add(taller2);
		
		//taller3=new JLabel(new ImageIcon(getClass().getResource("/images/auto2.gif")));
    	//taller3.setLayout(null);
    	//taller3.setBounds(1105,575,132,65);
		//add(taller3);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void setIndiceCBD(int n){
    	cb_meses.setSelectedIndex(n);
    	
    }
    
    public void pararClientes(){
    	
        for(int i=0; i<mono.personasSistema; i++)
        {
            //if(Clientes[i].isAlive())
              //  Clientes[i].stop();
        }
    }
    //
    public void suspenderClientes(){
    	for(int i=0; i<clientes.size(); i++){
        	if(clientes.get(i).isAlive())
        	{
        		clientes.get(i).suspend();
                clientes.get(i).parar();
        		}
    	}
    	    timer.cancel();
    	
	
    }
    //
    public void actualizarvelocidad(){
    	for(int i=0; i<clientes.size(); i++){//c es de tipo arrayList de clientes
        	if(clientes.get(i).isAlive())//isAlive metodo que pregunta si el hilo esta vivo
        	{
        		 clientes.get(i).actualizarvelo();
        		}
    	}
    	   }
    public void resumeClientes(){
    	for(int i=0; i<clientes.size(); i++){
        	if(clientes.get(i).isAlive())
        	{
        		clientes.get(i).resume();
                clientes.get(i).continuar();
        		}
    	}
    	    timerTask.run();
    	
	
    }
    
    //VentanaSimulacion simu= new VentanaSimulacion();
    public int getMesSeleccionado(){
    	return cb_meses.getSelectedIndex();
    }
    
    public void empezarSimulacion(){
    	int messelecionado = cb_meses.getSelectedIndex();
    	
    	Monitor.atencionLlamadas(cb_d_llamadas.getSelectedIndex());//exponencial,uniforme...
    	Monitor.atencionSiniestros(cb_d_siniestros.getSelectedIndex());
    	Monitor.atencionInvestigacion(cb_d_investigacion.getSelectedIndex());
    	Monitor.atencionEstimacion(cb_d_estimacion.getSelectedIndex());
    	Monitor.atencionNegociacion(cb_d_negociacion.getSelectedIndex());
    	

    	cb_d_llamadas.setEnabled(false);
    	cb_d_siniestros.setEnabled(false);
    	cb_d_investigacion.setEnabled(false);
    	cb_d_negociacion.setEnabled(false);
    	cb_d_estimacion.setEnabled(false);
    	
    	cb_meses.setEnabled(false);
    	dias.setEnabled(false);
    	
    	grupoHilo=new ThreadGroup("hilopadre");

    	int N_Clientes=0;
    	while (N_Clientes<35){
    		String nom="clien"+N_Clientes;
    		Cliente cli=new Cliente(grupoHilo,nom,
                   mono,lisLabel.get(N_Clientes),llamadas,siniestros,//llamada ,siniestro,gasto son nº emplead
                   investigaciones,gastos);
			clientes.add(cli );
    		

			N_Clientes++;
    	}

    	
    	switch(messelecionado)
    	{
    	case 0:		
    		System.out.println("va.uniforme(4, 7)");
    		cantidadC = va.uniforme(4, 7);
    		tiempoEntreLlegadas= va.uniforme(1000, 1030);
    		
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 1:	
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 2:	
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 3:		
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 4:		
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 5:		
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    		
    	case 6:		
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    		
    	case 7:			
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 8:		
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 9:		
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 10:	
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
    	case 11:	
    		cantidadC = va.uniforme(10, 12);
    		tiempoEntreLlegadas = va.uniforme(1000, 1030);
	    	timer.scheduleAtFixedRate(timerTask, 0, tiempoEntreLlegadas);   
	    	break;
	    	
        		
    	}
    	
    		
    }
    private TimerTask timerTask = new TimerTask(){
		int u=0;
        public void run() {
       	 	//codigo a correr
        	if(cantidadC>u){
        		System.out.println(""+u);
           	 	clientes.get(u).start();
           	   System.out.println("hilossss");
           	  int r= grupoHilo.activeCount();//devuelve nº de hilos activos
           	System.out.println("hilossss"+r);
           	 	u=u%149+1;
           	 	mono.setHora((int)(tiempoEntreLlegadas*porciontiempo));
           	 porciontiempo=porciontiempo+1;
           	}else{
           		cancel();
           		
           	}
        }
       
        
        
    };
    public double  getTiempo() {
    	System.out.println("reee"+tiempoEntreLlegadas*porciontiempo);
    	
    	return tiempoEntreLlegadas*porciontiempo;
    	
    	
		
	}
    public static void main(String[] args)
    {
        Monitor mo=new Monitor();
    	Panel123 inicio =new Panel123(mo,1,2,3,4);
    	
	}
        
	
}