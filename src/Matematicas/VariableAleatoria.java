package Matematicas;

import java.util.*;

/**
 * Write a description of class VariableAleatoria here.
 * 
 * @author CARESCA
 * @version (a version number or a date)
 */
public class VariableAleatoria
{
	private Random nroAzar = new Random();
	private double miRandom;
	private GeneCongruMixto gene;

	/**
	 * Constructor for objects of class VariableAleatoria
	 */
	public VariableAleatoria()
	{
		miRandom = 0.0;
		gene = new GeneCongruMixto();
	}
	
	public int transformadaInversaSiniestros(){
		int i=0;
		if(Math.random()>=0 && Math.random()<0.17){i=10;}
		else if(Math.random()>=0.17 && Math.random()<0.45){i=20;}
		else if(Math.random()>=0.45 && Math.random()<0.84){i=30;}
		else if(Math.random()>=0.84 && Math.random()<0.95){i=40;}
		else if(Math.random()>=0.95 && Math.random()<1){i=50;}
		return i;
	}
	
	public int transformadaInversaNegociacion(){
		int i=0;
		if(Math.random()>=0 && Math.random()<0.50){i=10;}
		else if(Math.random()>=0.17 && Math.random()<0.80){i=13;}
		else if(Math.random()>=0.95 && Math.random()<1){i=15;}
		return i;
	}
	
	public int transformadaInversaTaller(){
		int i=0;
		if(Math.random()>=0 && Math.random()<0.15){i=10;}
		else if(Math.random()>=0.15 && Math.random()<0.85){i=15;}
		else if(Math.random()>=0.85 && Math.random()<0.95){i=20;}
		else if(Math.random()>=0.95 && Math.random()<1){i=23;}
		return i;
	}
	
	//	Distribuciones Continuas para calcular comportamiento
	public int uniforme(int ini, int fin){
		return (int)(ini+Math.random()*(ini+fin));
	}
	
	public double exponencial(double media){
		return (-1*media*Math.log(Math.random()));
	}
	
	public int erlang(int alpha, int teta){
		double varErlang=1;
		for(int i=0; i<alpha; i++)
			varErlang*=Math.random();
		return (int)(-1*teta*Math.log(varErlang));
	}
	
	public double normal(double media,double desvi, int k){
		for(int i=0; i<k; i++)
			miRandom+=Math.random();
		miRandom=((miRandom-k/2.0)/(Math.sqrt(k/12.0)));
		return miRandom*media*desvi;
	}
	
	public  int vaNormal(double media,double desviacion) {
		double res;
		double aux1;
		double aux2;
		aux1 = Math.random();
		aux2 = Math.random();
		res = media+(desviacion*(Math.sqrt(-2*Math.log(aux1))*Math.cos(2*Math.PI*aux2)));
		return (int)res;
	}
	
	/*public double chiCuadrada(int sigma){
		double varChi=0;
		for(int i=1; i<=sigma; i++)
			varChi+=Math.pow(((normal(i)-0.5)/12.0),2);
		return varChi;
	}*/
	
	//Distribuciones Discretas para tomar desiciones 
	public int bernoulli(double exito){//exito entre 0-1
		if(Math.random()<=(1-exito)) return 0;
		else return 1;
	}
	
	public int binomial(int n, double exito){
		int bin=0;
		for(int i=0; i<n; i++)
			bin+=bernoulli(exito);
		return bin;
	}

	public  int poisson(double lambda) {
		int res=0;
		double elambda = Math.exp(-1*lambda);
		double producto = 1;
		int cont =  0;
		
		while (producto >= elambda) {
			producto = producto* numAzar();
			res = cont;
			cont++; 
		}
		return res;
	}

	private double numAzar()
    {
    	double res;
		return nroAzar.nextDouble();
    }
  
}

