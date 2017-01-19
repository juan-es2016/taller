package taller;

/**
 *
 * @author	@CARESCA
 * @version 
 */
 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Tablas extends JFrame{
	
	private Cursor cursor;
	private JPanel panel;	
	private JScrollPane scroll;
	private JLabel tex1;
	private JLabel tex2;
	private JLabel tex3;
	private JTable tabla;
	private DefaultTableModel modelo;	
	private JButton boton;
	private Monitor monitor;
	
	int diasMes=26;
	int atendidos=(monitor.personasSistema);
	
	int aceptadosllamadas=(monitor.Qfiscal);
	int rechazadosllamadas=(monitor.Dfiscal);
	int siniestros=(monitor.Dpolicial);
	int sospechosos=(monitor.flagrante);
	int fraude=(monitor.rechaso);
        int estimacion=(monitor.salida);
	int negociacion=(monitor.imputacion);
	//int taller=(monitor.imputacion);
	
	int total=aceptadosllamadas+rechazadosllamadas+siniestros+sospechosos;
	int rechazados=total-fraude-estimacion;
	int aceptados=total;
	
	int mesSimulacion;
	String mesSelec;
	
    public Tablas(Monitor m, int mes) {
    	
		monitor = m;
		mesSimulacion=mes;
		mesSelec="";
		establecerPropiedades();
    	establecerComponentes();
    	anadirTabla();
    }
    
    public void establecerPropiedades(){
    	
    	setTitle("Tablas de Reportes sobres la simulacion");
    	setLayout(null);
    	setBounds(380, 120, 490, 403);
    	//setUndecorated(true);
    	//getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    	setResizable(false);
    	setVisible(true);
    }
    public void establecerComponentes(){
    	
    	panel = new JPanel();
    	panel.setBounds(5, 10, 475, 360);
    	panel.setBorder(new TitledBorder("  Reportes Generales de la Simulacion del Mes Seleccionado  "));
    	panel.setLayout(null);
    	add(panel);
    	
    	cursor = new Cursor(Cursor.HAND_CURSOR);
    	
    	boton = new JButton("Aceptar"); 
    	boton.setBounds(200, 313, 90, 30);
    	boton.setCursor(cursor);
    	panel.add(boton);
    	
    	eventoSalir sal = new eventoSalir();
    	boton.addActionListener(sal);
    	
    }
    public void anadirTabla(){

    	
    	if(mesSimulacion==0){mesSelec="Enero";}
    	else if(mesSimulacion==1){mesSelec="Febrero";}
    	else if(mesSimulacion==2){mesSelec="Marzo";}
    	else if(mesSimulacion==3){mesSelec="Abril";}
    	else if(mesSimulacion==4){mesSelec="Mayo";}
    	else if(mesSimulacion==5){mesSelec="Junio";}
    	else if(mesSimulacion==6){mesSelec="Julio";}
    	else if(mesSimulacion==7){mesSelec="Agosto";}
    	else if(mesSimulacion==8){mesSelec="Septiembre";}
    	else if(mesSimulacion==9){mesSelec="Octubre";}
    	else if(mesSimulacion==10){mesSelec="Noviembre";}
    	else if(mesSimulacion==11){mesSelec="Diciembre";}
    	
    	String[]   cabe ={" Descripcion ", " Resultado "};
			String[][] datos = {
							{"MES de la Simulacion ","  "+mesSelec},
							{"querellas ante en fiscal  ","  "+Integer.toString(aceptadosllamadas)+" personas"},
							{"denuncias nate el fiscal ","  "+Integer.toString(rechazadosllamadas)+" personas"},
							{"denuncia ante la policia ","  "+Integer.toString(siniestros)+" personas"},
							{"delitos fragantes ","  "+Integer.toString(sospechosos)+" personas"},
							{"casos que fueron rechazados","  "+Integer.toString(fraude)+" personas"},
							{"casos que tomaron salidas alternativas"," "+Integer.toString(estimacion)+" personas"},
							{"casos que fueron imputados formalmente","  "+Integer.toString(negociacion)+" personas"},
							//{"Clientes que Atendidos a Taller","  "+Integer.toString(taller)+" personas"},
							{" "," "},
							{"TOTAL CASOS CON SENTENCIA","  "+Integer.toString(rechazados)+" personas"},
							{"Total CASOS DENUNCIADOS","  "+Integer.toString(aceptados)+" personas"},
							};
		
		modelo = new DefaultTableModel(datos,cabe);
		
		
		tabla = new JTable(modelo){
			public boolean isCellEditable(int f, int c) {
				boolean res = false;
				return res;
			}
		};
		
		tabla.getTableHeader().setReorderingAllowed(false);
				
		scroll = new JScrollPane(tabla);
		scroll.setBounds(10, 95, 455, 205);
		panel.add(scroll);
		
		tex1 = new JLabel("                Los datos a continuacion, muestran los datos generados en un Mes");
		tex2 = new JLabel("     en  base a la simulacion de un dia del mes escogido en la Animacion de la");
		tex3 = new JLabel("     anterior ventana");
		
		tex1.setBounds(10,40,455,10);
		tex2.setBounds(10,55,455,10);
		tex3.setBounds(10,70,455,10);
    	
		panel.add(tex1);
		panel.add(tex2);
		panel.add(tex3);
    }
    
    class eventoSalir implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		setVisible(false);	
    	}
    }
    
}