package taller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class JSomos extends JDialog
{
	JLabel programa = new JLabel("	ESTUDIANTES DE LA UMSS", JLabel.CENTER);
	JLabel autor1 = new JLabel("DESARROLLADO POR", JLabel.CENTER);
	JLabel autor2 = new JLabel(" Balderrama Salinas peter", JLabel.CENTER);
	JLabel autor3 = new JLabel("C�rdenas Escalier Carla Esther", JLabel.CENTER);
	JLabel autor4 = new JLabel("Peredo Gonzales Edgar", JLabel.CENTER);
	JLabel doc1 = new JLabel("DOCENTE:	Ing. Villarroel Tapia Henry Frank", JLabel.CENTER);
	JLabel derechos1 = new JLabel("Cochabamba - Bolivia", JLabel.CENTER);
	JLabel derechos2 = new JLabel("2011", JLabel.CENTER);

	JButton aceptar = new JButton("Aceptar");

	JPanel principal = new JPanel(new BorderLayout());
	JPanel info = new JPanel(new GridLayout(8, 1));
	JPanel boton = new JPanel(new FlowLayout());

 public  JSomos() {
	
		super(new Frame(), "Somos...", true);

		info.add(programa);
		info.add(autor1);
		info.add(autor2);
		info.add(autor3);
		info.add(autor4);
		info.add(doc1);
		info.add(derechos1);
		info.add(derechos2);
		boton.add(aceptar);
		principal.add("Center", info);
		principal.add("South", boton);
		getContentPane().add(principal);
		pack();
		setResizable(false);
		Dimension pantalla, cuadro;
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		cuadro = this.getSize();
		this.setLocation(((pantalla.width - cuadro.width)/2), (pantalla.height - cuadro.height)/2);
		aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});

	}
 

}

