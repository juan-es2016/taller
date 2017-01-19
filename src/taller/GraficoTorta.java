package taller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
import java.awt.Dimension;

import javax.swing.JFrame;
	import javax.swing.JPanel;
	import org.jfree.chart.*;
	import org.jfree.chart.plot.PiePlot3D;
	import org.jfree.data.general.DefaultPieDataset;
	import org.jfree.data.general.PieDataset;
	import org.jfree.ui.ApplicationFrame;
	import org.jfree.ui.RefineryUtilities;
	import org.jfree.util.Rotation;

	public class GraficoTorta extends JFrame
	{
	   
		JPanel jpanel;
	    public GraficoTorta( int posX, int posY, double[] datos, String[] labels, String titulo)
	    {
	        
	    	super("");
	    	try {
	    		
	        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
	        
	        for(int i=0;i<datos.length;i++)
	        	defaultpiedataset.setValue(labels[i],datos[i]);

	        JFreeChart jfreechart = ChartFactory.createPieChart3D(titulo, defaultpiedataset, true, true, false);
	        PiePlot3D pieplot3d = (PiePlot3D)jfreechart.getPlot();
	        pieplot3d.setDarkerSides(true);
	        pieplot3d.setStartAngle(0D);
	        pieplot3d.setDirection(Rotation.CLOCKWISE);
	        pieplot3d.setForegroundAlpha(0.75F);
	        pieplot3d.setNoDataMessage("No hay Datos que Mostrar");
	        
	        jpanel =new ChartPanel(jfreechart);
	        jpanel.setPreferredSize(new Dimension(300, 300));
	        setContentPane(jpanel);
	        pack();
	        RefineryUtilities.centerFrameOnScreen(this);
	    	} 
	    	catch (Exception e) {}
	    }

	}




