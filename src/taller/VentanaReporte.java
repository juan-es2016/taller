package taller;

import java.awt.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
         private Cursor cursor;
        private JButton boton;
        
        //int =(monitor.personasSistema);
	int Qfiscal=(monitor.Qfiscal);
	int Dfiscal=(monitor.Dfiscal);
	int Dpolicial=(monitor.Dpolicial);
	int flagrante=(monitor.flagrante);
	int rechasados=(monitor.rechaso);
        int salidasAlternativas=(monitor.salida);
	int imputacion=(monitor.imputacion);
	//int taller=(monitor.imputacion);
        int Pabreviado=(monitor.abreviado);
	int conciliacion=(monitor.conciliacion);
	int Coportunidad=(monitor.oportunidades);
	int Scondicional=(monitor.suspencion);
	
	int totalRegistrados=Qfiscal+Dfiscal+Dpolicial+flagrante;
	int imputados=totalRegistrados-rechasados-salidasAlternativas;
	//int aceptados=total;

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
		setSize( 950, 550 );
		setLocationRelativeTo(null); 
		setBackground( Color.gray );

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
                panelGraficos.setBounds(0, 0, 1024, 480);
                panelGraficos.setLayout(null);
                 panelGraficos.setVisible(false);
                 boton = new JButton("volver"); 
    	boton.setBounds(400,450, 90, 30);
    	boton.setCursor(cursor);
    	//panelGraficos.add(boton);
        add(boton);
        eventoSalir sal = new eventoSalir();
    	boton.addActionListener(sal);

             add(panelGraficos);

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


	public void createPage2()
	{
	
             panelGraficos.removeAll();

              panelGraficos.setVisible(true);
     
             
            String []cabecera ={"GRAFICOS"};
            String [][]datos=new String[20][20];
                
             modeloTablaGraficos=new DefaultTableModel(datos,cabecera);
            tablaGraficos=new JTable(modeloTablaGraficos);
            
            
             
             JPanel  panel =new JPanel();
            panel.setBounds(0,0,300,300);
            double[]d =new double[4];
             d[0]=Pabreviado;
             d[1]=conciliacion;
             d[2]=Coportunidad;
             d[3]=Scondicional;
             //d[4]=admin.Premium;
             String[] l=new String[4];
             l[0]="proceso abreviado";
             l[1]="conciliacion";
             l[2]="criterio de oportunidades";
             l[3]="suspecion condicional";
 
             
             GraficoTorta grafico=new GraficoTorta(400,300 , d, l , "FISCAL");
             panel.add(grafico.getContentPane());
             tablaGraficos.add(panel);
             
             JPanel panel2=new JPanel();
             panel2.setBounds(305, 0, 300, 300);
            double[]d2 =new double[3];
             d2[0]=rechasados;
             d2[1]=salidasAlternativas;
             d2[2]=imputacion;
     
             String[] l2=new String[3];
             l2[0]="casos rechazados";
             l2[1]="casos que tomaron salida alternativa";
             l2[2]="casos imputados";
      
            GraficoTorta grafico2=new GraficoTorta(400, 300, d2, l2 , "TOTALES");
            panel2.add(grafico2.getContentPane());
            tablaGraficos.add(panel2);

            JPanel panel3=new JPanel();
             panel3.setBounds(610, 0, 300, 300);
            double[]d3 =new double[2];
             d3[0]=imputados;
             d3[1]=totalRegistrados;

             String[] l3=new String[2];
             l3[0]="CASOS QUE FUERON IMPUTADOS";
             l3[1]="TOTAL CASOS REGISTRADOS";

            GraficoTorta grafico3=new GraficoTorta(400, 300, d3, l3 , "TOTAL CASOS");
            panel3.add(grafico3.getContentPane());
            tablaGraficos.add(panel3);
            
            tablaGraficos.getTableHeader().setReorderingAllowed(true);//seleccion
            tablaGraficos.setColumnSelectionAllowed(true);//seleccion
            scrolGraficos=new JScrollPane(tablaGraficos);
            scrolGraficos.setBounds(0,0,1024,350);
            panelGraficos.removeAll();
            panelGraficos.add(scrolGraficos);
 
               JLabel reportePreg =new JLabel("CONCILIACION: "+" "+(int)d[0]);
             reportePreg.setBounds(10, 360, 300, 20);
             panelGraficos.add(reportePreg);
             JLabel reporteEconomico =new JLabel("CRITERIO DE OPORTUNIDADES:"+" "+(int)d[1]);
            reporteEconomico.setBounds(10, 380, 300, 20);
             panelGraficos.add(reporteEconomico);
             JLabel reporteNegocios =new JLabel("SUSPENCION CONDICIONAL :"+" "+(int)d[2]);
             reporteNegocios.setBounds(10, 400, 300, 20);
             panelGraficos.add(reporteNegocios);
             JLabel reporteEjecutiva =new JLabel("PROCESO ABREVIADO:"+" "+(int)d[3]);
             reporteEjecutiva.setBounds(10, 420, 300, 20);
             panelGraficos.add(reporteEjecutiva);
    
             JLabel reporteEmail =new JLabel("CASOS RECHAZADOS"+" "+(int)d2[0]);
             reporteEmail.setBounds(330, 350, 300, 20);
             panelGraficos.add(reporteEmail);
             JLabel reporteFax =new JLabel("SALIDAS ALTERNATIVAS"+" "+(int)d2[1]);
             reporteFax.setBounds(330, 370, 300, 20);
             panelGraficos.add(reporteFax);
             JLabel reporteTelefono =new JLabel("IMPUTADOS"+" "+(int)d2[2]);
             reporteTelefono.setBounds(330, 390, 300, 20);
             panelGraficos.add(reporteTelefono);

            JLabel reporte1dia =new JLabel(" IMPUTADOS"+" "+(int)d3[0]);
             reporte1dia.setBounds(650, 350, 300, 20);
             panelGraficos.add(reporte1dia);
             JLabel reporte2dias =new JLabel(" REGISTRADOS"+" "+(int)d3[1]);
             reporte2dias.setBounds(650, 370, 300, 20);
             panelGraficos.add(reporte2dias);

             
	}
        class eventoSalir implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		setVisible(false);	
    	 }
        }
}
