package taller;

/**
 *
 * @author  CARESCA
 * 
 */             
    
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Cursor;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.Date;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

public class VentanaSimulacion extends JFrame implements ActionListener{
	
	
	
	JSlider slider;
    JLabel label;
    
	private Panel123 panel;
	private JMenuBar menus;
    private JMenu Archivo;
    private JMenu Editar;
    private JMenu Ayuda;
    private JMenuItem Nuevo;
    private JMenuItem Abrir;
    private JMenuItem Guardar;
    private JMenuItem Imprimir;
    private JMenuItem Salir;
    private JMenuItem Reportes;
    private JMenuItem Tablas;
    private JMenuItem Acerca_De;
    private JMenuItem Somos;
    
    private int gastos;
    private int investigaciones;
    private int llamadas ;
    private int siniestros;
	private static int slindervalue;
	private boolean listo=false;
	private Thread hilo;
    private boolean run=false;
    private int contador;
    private JPanel panel1 = new JPanel(); 
    private JPanel p_infoAtencion = new JPanel();
    private JPanel p_controles = new JPanel();
    private JPanel p_reportes = new JPanel();
    private JPanel p_simulacion = new JPanel();

    private JPanel p_infoGeneral = new JPanel();
    private JPanel p_cronometro = new JPanel();
    
    private DefaultTableModel modelo;

    /*
    public static JLabel label_llamadas = new JLabel     ("Dpto Llamadas      : 0");
    public static JLabel label_rechazados = new JLabel   ("Rechazados         : 0");
    public static JLabel label_siniestros = new JLabel   ("Dpto Siniestros    : 0");
    public static JLabel label_investigacion = new JLabel("Dpto Investigacion : 0");    
    public static JLabel label_gastos = new JLabel       ("Dpto Estimacion    : 0");
    public static JLabel label_negociacion = new JLabel  ("En Negociacion     : 0"); 
    public static JLabel label_taller = new JLabel       ("En taller          : 0"); 
     */   
    public static JLabel label_sistema = new JLabel("total.Denun : 0");
    //public static JLabel label_atendidas = new JLabel("casos Correctos : 0");

    private static JLabel tiempo=new JLabel(); 
    private JButton simular = new JButton("Iniciar Simular");
    private JButton pausa = new JButton("Detener Simulacion");
    private JButton reportes = new JButton("Estadisticos");
    private JButton tablas = new JButton("Tablas");
    private JButton salir = new JButton("Volver");
    
    
    
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
      
    private Date date = new Date();
      
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////
      
    private double datosParam[]={45,2,0.1,1,3};
    public static Monitor monitor = new Monitor();
    private Thread thread;
    
    
    public VentanaSimulacion(int gastos,int investigaciones,int llamadas,int siniestros) {
		this.gastos=gastos;
		this.investigaciones=investigaciones;
		this.llamadas = llamadas;
		this.siniestros = siniestros;
               
               
     //this.add(fondo,BorderLayout.CENTER);
     this.pack();
     this.setExtendedState(MAXIMIZED_BOTH);
     
    	
        establecerPropiedades();
        establecerComponentes();
        anadirEventos();
        //anadirInterfazAnimacion();
    }
  
    public void establecerPropiedades(){

      setTitle(" SIMULACION DE TRAMITES BUROCRATICOS TALLER DE SIMULACION 2/2016");
      setLayout(null);
      setBounds(0, 0, 1400, 710);
      //setUndecorated(true);
      //getRootPane().setWindowDecorationStyle(1);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      setVisible(true);
      
      crearMenu();
      
    }      
    public class MyChangeAction implements ChangeListener{
        public void stateChanged(ChangeEvent arg0) {
    		// TODO Auto-generated method stub
    		int value = slider.getValue()*2;
    		slindervalue=102-value;
    	      String str = Integer.toString(value);
    	      label.setText(str);
    	      monitor.velocidad(slindervalue);
    		  panel.actualizarvelocidad();
    	};}
      
    public void crearMenu(){
    	menus = new JMenuBar();
    	setJMenuBar(menus);
        Archivo = new JMenu("Archivo");
        Editar =new JMenu("Editar");
        Ayuda = new JMenu("Ayuda");
        
        Nuevo = new JMenuItem("Nuevo");
        Abrir = new JMenuItem("Abrir");
        Guardar = new JMenuItem("Guardar");
        Imprimir = new JMenuItem("Imprimir");
        Salir = new JMenuItem("Salir");
        Reportes =new JMenu("Reportes");
		Tablas=new JMenuItem("Tablas de la simulacion");
        Acerca_De = new JMenuItem("Acerca de este");
        Somos=new JMenuItem("Quienes Somos");
        
        Archivo.add(Nuevo);
        Archivo.add(Abrir);
        Archivo.add(Guardar);
        Archivo.add(Imprimir);
		Archivo.add(Salir);
        Editar.add(Reportes);
        Editar.add(Tablas);
		Ayuda.add(Acerca_De);
		Ayuda.add(Somos);
		menus.add(Archivo);
        menus.add(Editar);
        menus.add(Ayuda);
        
      	Nuevo.addActionListener(this);
		Abrir.addActionListener(this);
		Guardar.addActionListener(this);
		Imprimir.addActionListener(this);
	    Salir.addActionListener(this);
	    Reportes.addActionListener(this);
	    Tablas.addActionListener(this);
	    Acerca_De.addActionListener(this);
	    Somos.addActionListener(this);

    }
    
    public void establecerComponentes(){
        
      panel1.setLayout(null);
      
      p_infoAtencion.setLayout(null);
      p_controles.setLayout(null);
      p_reportes.setLayout(null);
      p_simulacion.setLayout(null);
      p_infoGeneral.setLayout(null);
      p_cronometro.setLayout(null);
      ////////////////////////////////////////////////////////////////////////////////
      slider = new JSlider(2,50,25);
      
      slider.setValue(25);
      slider.addChangeListener(new MyChangeAction());
      slider.setLayout(null);
      label = new JLabel("");
      JLabel labelvel = new JLabel("Velocidad");
      labelvel.setBounds(5,25,100,30);
      label.setLayout(null);
      panel1.setBounds(0, 0, 1390, 752); 
      //todo
      add(panel1);
      
     
    //}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
      
    
      JPanel pane = new JPanel();
      pane.add(slider);
      pane.add(label);
      pane.setVisible(true);
      pane.setBounds(-10, 80, 210, 50);
      //add(pane);
      panel1.add(labelvel);
      panel1.add(pane);


      //p_infoAtencion.setBounds(0, 10, 150,180);   //INFORMACION DE aTENCION
      p_infoGeneral.setBounds(0, 195, 150, 75);
      p_cronometro.setBounds(0, 275, 150, 65);
      p_controles.setBounds(0, 345, 150, 165);  //CONTROLES
      p_reportes.setBounds(0, 520, 150, 120);
      p_simulacion.setBounds(155, 0, 1210, 700); //SIMULACION   1280,750);
        
      //p_infoAtencion.setBorder(new TitledBorder ("Informacion de Atencion"));
      p_controles.setBorder(new TitledBorder ("Controles"));
      p_simulacion.setBorder(new TitledBorder (""));   ///simulacion
      p_infoGeneral.setBorder(new TitledBorder("Informacion General"));
      p_cronometro.setBorder(new TitledBorder("Cronometro"));
      p_reportes.setBorder(new TitledBorder("Reportes"));
      
      panel1.add(p_infoAtencion);
      panel1.add(p_controles);
      panel1.add(p_reportes);
      panel1.add(p_simulacion);
      panel1.add(p_infoGeneral);
      panel1.add(p_cronometro);

      /*
      label_llamadas.setBounds(10, 25, 230, 20);
      label_rechazados.setBounds(10, 50, 230, 20);
      label_siniestros.setBounds(10, 75, 230, 20);
      label_investigacion.setBounds(10, 100, 230, 20);
      label_negociacion.setBounds(10, 125, 230, 20);
      label_taller.setBounds(10, 150, 140, 20);
      
      p_infoAtencion.add(label_llamadas);
      p_infoAtencion.add(label_rechazados);
      p_infoAtencion.add(label_siniestros);
      p_infoAtencion.add(label_investigacion);
      p_infoAtencion.add(label_negociacion);
      p_infoAtencion.add(label_taller);
      */
      label_sistema.setBounds(10, 20, 230, 20);
      p_infoGeneral.add(label_sistema); 
     // label_atendidas.setBounds(10, 40, 230, 20);
      //p_infoGeneral.add(label_atendidas);
                
      tiempo.setText(" Tiempo Simulado: ");
      tiempo.setBounds(5, 25, 230, 20);
      p_cronometro.add(tiempo);
    
      
      ///////////////BOTONES
      simular.setBounds(10, 25, 130, 35);
      simular.setCursor(cursor);
      p_controles.add(simular);
      
      pausa.setBounds(10, 70, 130, 35);
      pausa.setCursor(cursor);
      pausa.setEnabled(false);
      p_controles.add(pausa);
      
      salir.setBounds(10, 115, 130, 35);
      salir.setCursor(cursor);
      p_controles.add(salir);
      
      reportes.setBounds(10, 25, 130, 35);
      reportes.setCursor(cursor);
      reportes.setEnabled(false);
      p_reportes.add(reportes);
      
      tablas.setBounds(10, 70, 130, 35);
      tablas.setCursor(cursor);
      tablas.setEnabled(false);
      p_reportes.add(tablas);
      
      crearPanel();
  	  setVisible(true);
    }
      ////////////////////////*****************************///////////////////////////////     
    
	public void crearPanel(){
		panel = new Panel123(monitor,gastos,investigaciones,llamadas,siniestros);
		panel.setLayout(null);
		panel.setBounds(-10, -10, 1190,700);
		p_simulacion.add(panel);
    }
       
    public static void actualizarInformacion(){
        /*	
    	label_llamadas.setText	   ("Dpto Llamadas      : "+monitor.clientesEnLlamadas);
            label_rechazados.setText   ("Rechazados         : "+monitor.rechazadasEnLlamadas );
            label_siniestros.setText   ("Dpto Siniestros    : "+monitor.clientesSiniestros);
            label_investigacion.setText("Dpto Investigacion : "+monitor.clientesInvestigacion);    
            label_gastos.setText       ("Dpto Estimacion    : "+monitor.clientesGastos);
            label_negociacion.setText  ("En Negociacion     : "+monitor.clientesNegociacion);
            label_taller.setText       ("En Taller          : "+monitor.clientesTaller);
            */
            tiempo.setText(" Tiempo Simulado:" +monitor.tiempo/3600+":"+monitor.tiempo%60);
            label_sistema.setText      ("total.Denun        : "+monitor.personasSistema);
            //label_atendidas.setText    ("Casos Correctos    : "+monitor.personasPagadas);
            
    }

    public int getMes(){
    	return panel.getMesSeleccionado();
    }
    
    public void anadirEventos(){
      eventoEstadisticos eveRep = new eventoEstadisticos();
      reportes.addActionListener(eveRep);
      
      eventoTablas eveTab = new eventoTablas();
      tablas.addActionListener(eveTab);
      
      eventoSalir eveSal = new eventoSalir();
      salir.addActionListener(eveSal);
      
      eventoSimular eveSim = new eventoSimular();
      simular.addActionListener(eveSim);
      
      eventoPausa evePau = new eventoPausa();
      pausa.addActionListener(evePau);
    }
    
    class eventoSimular implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	
        	if(e.getSource().equals(simular)&&simular.isEnabled()){
    			System.out.println("Simular");
    			monitor.velocidad(50);
      		  	panel.actualizarvelocidad();
    			panel.empezarSimulacion();
    		}
        	
        	
             if(simular.getText() == "Iniciar Simular"){
            	
            	simular.setText("Terminar Simulacion");
            	pausa.setEnabled(true);
            	//run();
            	
                /*
                inter.start();
                pausa.setEnabled(true);
                simular.setText("Terminar Simulacion");
                hilo=new Thread(new Runnable(){
	            	int seg=0;
	            	int min=0;
	            	int hor=0;
	            	public void run(){
		                while(!listo){
		                    try{
		                        Thread.sleep(1000);
		                    }catch(InterruptedException e){}
		                    seg=contador%60;
		                    min=(contador/60)%60;
		                    hor=(contador/3600);
		                    tiempo.setText(" Tiempo Simulado: "+hor+":"+min+":"+seg);
		                    //listo=escenario.listo();
		                    contador++;
		                }
	            	}
	            });
                hilo.start();*/
           }else{
        	   System.out.println("start");
                //inter.stop();
                monitor.reiniciar();
                panel.suspenderClientes();
                //anadirInterfazAnimacion();
                tiempo.setText(" Tiempo Simulado:" +monitor.tiempo/3600+":"+monitor.tiempo%60);
                simular.setText("Iniciar Simular");
                pausa.setText("Detener Simulacion");
                pausa.setEnabled(false);
            }   
        }
    }
    
    class eventoPausa implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(pausa.getText() == "Detener Simulacion"){
                //inter.suspend();
                pausa.setText("Continuar Simulacion");
            //     hilo.suspend();
                reportes.setEnabled(true);           //Estadistico
                tablas.setEnabled(true);
                panel.suspenderClientes();
                System.out.println("paneltiem"+panel.getTiempo());
            }
            else{
                pausa.setText("Detener Simulacion");
                reportes.setEnabled(false);
                tablas.setEnabled(false);
                
                panel.resumeClientes();
                //inter.resume(); 
                //hilo.resume();
            }    
        }
    }
        
    class eventoTablas implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Tablas t = new Tablas(monitor,getMes());  
        }
    }
    
    class eventoEstadisticos implements ActionListener{
        public void actionPerformed(ActionEvent e){
            VentanaReporte rep= new VentanaReporte(monitor);
            rep.setVisible(true);
        }
    }
    
    class eventoSalir implements ActionListener{
        public void actionPerformed(ActionEvent e){
            simulacion simu=new simulacion();
        	setVisible(false);
        	 monitor.reiniciar();
                 simu.setVisible(true);//////////
                        
        }
    }
    
	public void actionPerformed(ActionEvent e) {
		String opcion = e.getActionCommand();
		if(e.getSource().equals(Salir)){
			int o=JOptionPane.showConfirmDialog(this, "Esta seguro que desea salir?");
			if(o==JOptionPane.OK_OPTION){
				System.exit(0);
			}
		}else if (opcion.equals("Acerca de este")) {
			JOptionPane.showMessageDialog(this,"Para mas informacin contactarse con: \n"+"El grupo#4 -_- " +
						" \n","Acerca de Grupo#2", JOptionPane.INFORMATION_MESSAGE);
	     }else if (opcion.equals("Nuevo")) {
	        new VentanaSimulacion(gastos,investigaciones,llamadas,siniestros);
	        
	     }
	     else if(e.getSource().equals(Abrir)){
			JFileChooser fc=new JFileChooser();
			int action=fc.showOpenDialog(this);
			if(action==JFileChooser.APPROVE_OPTION){
				//Serializador<Panel2D>s=new Serializador<Panel2D>();
				//getContentPane().remove(panel);
				//panel=s.abrir(fc.getSelectedFile());
				repaint();
			}
		}else if(e.getSource().equals(Guardar)){
			JFileChooser fc=new JFileChooser();
			int action=fc.showSaveDialog(this);
			//System.out.println(tabla.getSelectedComponent());
			if(action==JFileChooser.APPROVE_OPTION){
				//Serializador<Panel2D>s=new Serializador<Panel2D>();
				//s.guardar(panel, fc.getSelectedFile());
				
				
			}
		}else if(e.getSource().equals(Imprimir)){
			Properties propiedades = new Properties();
			Toolkit toolkit = getToolkit();
			JFrame f = new JFrame();
			PrintJob pr = toolkit.getPrintJob(f, "_ImpresionTrabajo", propiedades);
				
		}else if(e.getSource().equals(Tablas)){
			new Tablas(monitor, getMes()).setVisible(true);
		
		}else if(e.getSource().equals(Somos)){
			new JSomos().setVisible(true);
		}
	     
	}
   

}