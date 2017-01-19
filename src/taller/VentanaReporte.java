package taller;

import java.awt.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

class VentanaReporte extends JFrame
{
	private	JTabbedPane tabbedPane;
	private	JPanel panel1;
	private	JPanel panel2;
	private double arreglo[];
	Monitor monitor;
	private double total;
	private double rechazadosllamadas=0.0;
	private double promRechazadosLLamadas=0.0;
	private double fraude=0.0;
	private double promFraude=0.0;
	private double negociacion=0.0;
	private double promNegociacin=0.0;
	private double taller=0.0;
	private double promTaller=0.0;
	private JPanel panelGraficos=new JPanel();
         private DefaultTableModel modeloTablaGraficos;
          private JScrollPane scrolGraficos;
    private JTable tablaGraficos;
         

	
	public VentanaReporte(Monitor m)
	{
		monitor = m;
		
total = monitor.getPersonasSistema();
		
		rechazadosllamadas=(monitor.rechazadasEnLlamadas);
		fraude=(monitor.clientesFraude);
		negociacion=(monitor.clientesNegociacion);
		taller=(monitor.clientesTaller);
		
		

		
		calcularPromedio();
		
		setTitle("Ventana Reportes Estadisticos");
		setSize( 600, 400 );
		setLocationRelativeTo(null); 
		setBackground( Color.gray );

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
                panelGraficos.setBounds(0, 0, 1024, 480);
                panelGraficos.setLayout(null);
                 panelGraficos.setVisible(false);
     //ayudaGraficos.setBounds(950, 430, 80, 20);
      //ayudaGraficos.setVisible(true);
      //ayudaGraficos.setEnabled(true);
      // panelGraficos.add(ayudaGraficos);
     
     
             add(panelGraficos);

		createPage1();
		createPage2();

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Barra", panel1 );
		tabbedPane.addTab( "Torta", panel2 );
		topPanel.add( tabbedPane, BorderLayout.CENTER );
	}
	public void calcularPromedio()
	{
		total=total-1;
		promRechazadosLLamadas=rechazadosllamadas/total;
		promFraude=fraude/total;
		promNegociacin=negociacion/total;
		promTaller=taller/total;
	}
	
	

	public void createPage1()
	{
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,1) );
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.setValue((Number)(monitor.clientesEnLlamadas),"Depto de Llamadas", "departamentos");
		dataset.setValue((Number)(monitor.clientesSiniestros),"Depto de Siniestros", "departamentos");
		dataset.setValue((Number)(monitor.clientesInvestigacion),"Depto de Investigacion", "departamentos");
		dataset.setValue((Number)(monitor.clientesNegociacion),"En Negociacion", "departamentos");
		dataset.setValue((Number)(monitor.clientesTaller),"En Taller", "departamentos");
		
		JFreeChart chart1 = ChartFactory.createBarChart3D ("Promedio de atencion en cada Departamento",
				"Atencion en Departamentos",
				"Tiempo Promedio",
			dataset,
			PlotOrientation.VERTICAL,
		    true, 
		    false,
		    false);        
		    panel1.add(new ChartPanel(chart1));
	}

	public void createPage2()
	{
		/*panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1) );
		DefaultPieDataset dataset1 = new DefaultPieDataset();
        	dataset1.setValue("Descartados por Desorden de Poliza="+Double.toString(promRechazadosLLamadas*100)+"%",new Double(promRechazadosLLamadas*100));
        	dataset1.setValue("Descartados por Fraude="+Double.toString(promFraude*100)+"%",new Double (promFraude*100));
        	dataset1.setValue("En Negociacion="+Double.toString(promNegociacin*100)+"%",new Double(promNegociacin*100));
        	dataset1.setValue("En Taller="+Double.toString(promTaller*100)+"%",new Double(promTaller*100));
        JFreeChart chart2 = ChartFactory.createPieChart3D("PROMEDIO GENERAL RECHAZOS Y PAGOS", dataset1, true, false, false);
        panel2.add(new ChartPanel(chart2));*/
             panelGraficos.removeAll();

                //panelVariables.setVisible(false);
                //panelTabla.setVisible(false);
              panelGraficos.setVisible(true);
                // panelBotonesSimular.setVisible(false);
                //panelSimular.setVisible(false);
                 //variables.setSelected(false);
             //graficos.setSelected(true);
             //simulacion2.setSelected(false);
             
            String []cabecera ={"GRAFICOS"};
            String [][]datos=new String[20][20];
                
             modeloTablaGraficos=new DefaultTableModel(datos,cabecera);
            tablaGraficos=new JTable(modeloTablaGraficos);
            
            
             
             JPanel  panel =new JPanel();
            panel.setBounds(0,0,300,300);
            double[]d =new double[4];
             d[0]=monitor.Qfiscal;
             d[1]=monitor.Dfiscal;
             d[2]=monitor.Dpolicial;
             d[3]=monitor.flagrante;
             //d[4]=admin.Premium;
             String[] l=new String[4];
             l[0]="QuerellaFisacal";
             l[1]="DenunciaFiscal";
             l[2]="DenunciaPolicia";
             l[3]="infragante";
             //l[4]="PREMIUM";
             
             
             //double[] datos={45, 23};
            //String[] labels={"atendidos", "no atendidos"};
             
             
             GraficoTorta grafico=new GraficoTorta(400,300 , d, l , "FISCAL");
             panel.add(grafico.getContentPane());
             tablaGraficos.add(panel);
             
             JPanel panel2=new JPanel();
             panel2.setBounds(305, 0, 300, 300);
            double[]d2 =new double[3];
             d2[0]=monitor.rechaso;
             d2[1]=monitor.salida;
             d2[2]=monitor.imputacion;
            // d2[3]=2;
             //d2[4]=6;
             String[] l2=new String[3];
             l2[0]="casos rechazados";
             l2[1]="casos que tomaron salida alternativa";
             l2[2]="casos imputados";
             //l2[3]="FAX";
             //l2[4]="NINGUNA";
             
            GraficoTorta grafico2=new GraficoTorta(400, 300, d2, l2 , "TIPOS DE RESERVA");
            panel2.add(grafico2.getContentPane());
            tablaGraficos.add(panel2);
        
            
            JPanel panel3=new JPanel();
             panel3.setBounds(610, 0, 300, 300);
            double[]d3 =new double[5];
             d3[0]=monitor.total-monitor.rechaso-monitor.salida;
             d3[1]=monitor.total;
             d3[2]=6;
             d3[3]=3;
             d3[4]=2;
             String[] l3=new String[5];
             l3[0]="1 DIA";
             l3[1]="2 DIAS";
             l3[2]="3 DIAS";
             l3[3]="4 DIAS";
             l3[4]="5 DIAS";
             
            GraficoTorta grafico3=new GraficoTorta(400, 300, d3, l3 , "DIAS DE HOSPEDAJE");
            panel3.add(grafico3.getContentPane());
            tablaGraficos.add(panel3);
            
            tablaGraficos.getTableHeader().setReorderingAllowed(true);//seleccion
            tablaGraficos.setColumnSelectionAllowed(true);//seleccion
            scrolGraficos=new JScrollPane(tablaGraficos);
            scrolGraficos.setBounds(0,0,1024,350);
            panelGraficos.removeAll();
            panelGraficos.add(scrolGraficos);
             //ayudaGraficos.setBounds(930, 360, 80, 20);
             //ayudaGraficos.addActionListener(new EventoAyudaGraficos());
            // ayudaGraficos.setVisible(true);
              //ayudaGraficos.setEnabled(true);
              //panelGraficos.add(ayudaGraficos);
              
              //imprimirGraficos.setBounds(950, 400, 33, 33);
     //imprimirGraficos.setVisible(true);
     //imprimirGraficos.setEnabled(true);
     	//imprimirGraficos.setBorderPainted(false);
		//imprimirGraficos.setContentAreaFilled(false);
		//imprimirGraficos.addActionListener(new EventoImprimirGraficos());
		//panelGraficos.add(imprimirGraficos);
             
               JLabel reportePreg =new JLabel("querella ante el fiscal:"+" "+(int)d[0]);
             reportePreg.setBounds(10, 360, 300, 20);
             panelGraficos.add(reportePreg);
             JLabel reporteEconomico =new JLabel("denuncia ante el fiscal:"+" "+(int)d[1]);
            reporteEconomico.setBounds(10, 380, 300, 20);
             panelGraficos.add(reporteEconomico);
             JLabel reporteNegocios =new JLabel("denuncia ante la policia:"+" "+(int)d[2]);
             reporteNegocios.setBounds(10, 400, 300, 20);
             panelGraficos.add(reporteNegocios);
             JLabel reporteEjecutiva =new JLabel("delito fragante:"+" "+(int)d[3]);
             reporteEjecutiva.setBounds(10, 420, 300, 20);
             panelGraficos.add(reporteEjecutiva);
             //JLabel reportePremium =new JLabel("delito fragante:"+" "+(int)d[4]);
             //reportePremium.setBounds(10, 440, 300, 20);
             //panelGraficos.add(reportePremium);
             
             JLabel reporteEmail =new JLabel("RESERV. E-MAIL:"+" "+(int)d2[2]);
             reporteEmail.setBounds(330, 350, 300, 20);
             panelGraficos.add(reporteEmail);
             JLabel reporteFax =new JLabel("RESERV. FAX:"+" "+(int)d2[3]);
             reporteFax.setBounds(330, 370, 300, 20);
             panelGraficos.add(reporteFax);
             JLabel reporteTelefono =new JLabel("RESERV. TELEFONICA:"+" "+(int)d2[0]);
             reporteTelefono.setBounds(330, 390, 300, 20);
             panelGraficos.add(reporteTelefono);
             JLabel reporteCarta =new JLabel("RESERV. CARTA:"+" "+(int)d2[1]);
             reporteCarta.setBounds(330, 410, 420, 20);
             panelGraficos.add(reporteCarta);
             JLabel reporteNinguno =new JLabel("SIN RESERV. :"+" "+(int)d2[4]);
             reporteNinguno.setBounds(330, 430, 300, 20);
             panelGraficos.add(reporteNinguno);
             
            JLabel reporte1dia =new JLabel("1 DIA:"+" "+(int)d3[0]);
             reporte1dia.setBounds(650, 350, 300, 20);
             panelGraficos.add(reporte1dia);
             JLabel reporte2dias =new JLabel("2 DIAS:"+" "+(int)d3[1]);
             reporte2dias.setBounds(650, 370, 300, 20);
             panelGraficos.add(reporte2dias);
             JLabel reporte3dias =new JLabel("3 DIAS:"+" "+(int)d3[2]);
             reporte3dias.setBounds(650, 390, 300, 20);
             panelGraficos.add(reporte3dias);
             JLabel reporte4dias =new JLabel("4 DIAS:"+" "+(int)d3[3]);
             reporte4dias.setBounds(650, 410, 420, 20);
             panelGraficos.add(reporte4dias);
             JLabel reporte5dias =new JLabel("5 DIAS:"+" "+(int)d3[4]);
             reporte5dias.setBounds(650, 430, 300, 20);
             panelGraficos.add(reporte5dias);
             
	}
}
