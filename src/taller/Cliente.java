package taller;

import Matematicas.PruebaMatematica;
import Matematicas.VariableAleatoria;
import static java.lang.Thread.sleep;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Cliente extends Thread implements Runnable{
	
	private JLabel imagen;
	private boolean pausa;
	private boolean play;
	private int velocidad;
	
	private int gastos;
    private int investigaciones;
    private int llamadas;
    private int siniestros;
    
    private VariableAleatoria va;
    private Monitor monitor;
    boolean encola = true;
    int tam = 0;
    
    //////////////////////////////////VARIABLESSSSSSS
    public double tiempoContactoLlamada;
    public int tiempoVerificacionSiniestros;
    public double tiempoEstimacionGastos;
    public double tiempoInvestigacion;
    public double tiempoFraudes;
    public double tiempoNegociacion;
    public double tiempoTaller;
    /////////////////////////////////////////////////
    
	public Cliente(){}
    
    public Cliente(ThreadGroup gru,String nam,Monitor m,JLabel ima,int llam,int sin,int inv,int g){
		super(gru,nam);
    	velocidad=9;
		imagen=ima;
		play =true;
		pausa=false;
		
		llamadas=llam;
		siniestros=sin;
		investigaciones=inv;
		gastos=g;
		
		va=new VariableAleatoria();
		monitor=m;
		
		distribuciones();
	    
	    
		System.out.println("llamadas "+tiempoContactoLlamada);
	    System.out.println("siniestros "+tiempoVerificacionSiniestros);
	    System.out.println("gastos "+tiempoEstimacionGastos);
	    System.out.println("investigacion "+tiempoInvestigacion);
	    System.out.println("fraudes "+tiempoFraudes);
	    System.out.println("negociacion "+tiempoNegociacion);
	    System.out.println("taller "+tiempoTaller);
	    System.out.println("*****************************************");
	}
	
	public void run(){
		VentanaSimulacion.actualizarInformacion();
		//thread.setPriority(Thread.MIN_PRIORITY+1);
	    imagen.setIcon(new ImageIcon(getClass().getResource("/images/bola.png")));
	   // monitor.incrementarPersonasSistema();
	    llamada();
		//monitor.decrementarPersonasSistema();
	    //monitor.casosFinalisados();
	    velocidad= monitor.velo;
	}
	

   /////////////////////***********************************/////////////////////////////
   //////////////////////**************************************///////////////////////
    
	private void distribuciones(){
		
	    tiempoContactoLlamada=atencionLlamada()*monitor.velo/100;
	    tiempoVerificacionSiniestros=atencionSiniestros()*monitor.velo/100;
	    tiempoEstimacionGastos=atencionestimacion()*monitor.velo/100;
	    tiempoInvestigacion=atencionInvestigacion()*monitor.velo/100;
	    tiempoFraudes=(va.uniforme(2, 4)*100)*monitor.velo/100;
	    tiempoNegociacion=atencionNegociacion()*monitor.velo/100;
	    tiempoTaller=(va.transformadaInversaTaller())*100*monitor.velo/100;
	}
	private int atencionLlamada(){
		int res=monitor.m_atencionLlamada;
		if (res==0){res=(int)(va.exponencial(3.4))*100;}
		else if (res==1){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		else if (res==2){res=(int)(va.normal(0.5, 4.5, 10))*100;}  //normal
		else if (res==3){res=(va.transformadaInversaSiniestros())*10;}  //trns inversa
		else if (res==4){res=(int)(va.exponencial(3.4))*100;}  //bermolli
		return res;
	}
	private int atencionSiniestros(){
		int res=monitor.m_atencionSiniestros;
		if (res==0){res=(va.transformadaInversaSiniestros())*10;}  //Trans. Inversa
		else if (res==1){res=(int)(va.normal(2.2, 1.33, 100))*100;}  //normal
		else if (res==2){res=(int)(va.exponencial(3.4))*100;}  //esponencial
		else if (res==3){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		return res;
	}
	private int atencionInvestigacion(){
		int res=monitor.m_atencionLlamada;
		if (res==0){res=(int)(va.normal(2.2, 1.33, 100))*100;}
		else if (res==1){res=(int)(va.exponencial(3.4))*100;}  //exponencial
		else if (res==2){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		else if (res==3){res=(va.transformadaInversaSiniestros())*10;}  //trns inversa
		return res;
	}
	private int atencionestimacion(){
		int res=monitor.m_atencionLlamada;
		if (res==0){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		else if (res==1){res=(int)(va.normal(2.2, 1.33, 100))*100;}  //normal
		else if (res==2){res=(int)(va.exponencial(3.4))*100;}  //exponencial
		else if (res==3){res=(va.transformadaInversaSiniestros())*10;}  //trns inversa
		return res;
	}
	private int atencionNegociacion(){
		int res=monitor.m_atencionLlamada;
		if (res==0){res=(va.transformadaInversaNegociacion())*100;}   //Trans. Inversa
		else if (res==1){res=(int)(va.exponencial(3.4))*100;}  //normal
		else if (res==2){res=(int)(va.exponencial(3.4))*100;}  //exponencial
		else if (res==3){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		return res;
	}
	//////////////////////////////////////////////////////////////////
	private synchronized void llamada() {
		System.out.println("llamada");
		int i= va.uniforme(1, (llamadas-1)); /////////////////PROB UNIFORME
               int aux=0;
               System.out.println(i);
		switch (i){
            case 1: {	imagen.setLocation(60,100);
            			monitor.incrementarPersonasSistema();
                                monitor.incrementarQfiscal();
            			dormir((int)tiempoContactoLlamada);  ////////////////////////////////////////TIEMPO DE UNA LLAMADA
            			monitor.insertarTiempoLlamanda(tiempoContactoLlamada);
            			moverS(130);
        	}
            break;
            case 2: {	imagen.setLocation(120,70);
            			monitor.incrementarPersonasSistema();
                                monitor.incrementarDfiscal();
            			dormir((int)tiempoContactoLlamada);
            			monitor.insertarTiempoLlamanda(tiempoContactoLlamada);
            			moverS(130);
                              
    		}
            break;
            case 3: {	imagen.setLocation(180,70);
            			monitor.incrementarPersonasSistema();
                                monitor.incrementarDpolicial();
            			dormir((int)tiempoContactoLlamada);
            			monitor.insertarTiempoLlamanda(tiempoContactoLlamada);
            			moverS(130);
                                  //aux=1;
			}
            break;
            case 4: {	imagen.setLocation(240,70);
            			monitor.incrementarPersonasSistema();
                                monitor.incrementarFlagrante();
            			dormir((int)tiempoContactoLlamada);
            			monitor.insertarTiempoLlamanda(tiempoContactoLlamada);
            			moverS(130);
                                aux=1;
			}
            break;
            case 5: {	imagen.setLocation(60,170);
            			monitor.incrementarPersonasSistema();
            			dormir((int)tiempoContactoLlamada);
            			monitor.insertarTiempoLlamanda(tiempoContactoLlamada);
            			moverN(135);
			}
            break;
            case 6: {	imagen.setLocation(120,170);
            			monitor.incrementarPersonasSistema();
            			dormir((int)tiempoContactoLlamada);
            			monitor.insertarTiempoLlamanda(tiempoContactoLlamada);
            			moverN(135);
            }
            break;
            case 7: {	imagen.setLocation(180,170);
            			monitor.incrementarPersonasSistema();
            			dormir((int)tiempoContactoLlamada);
            			monitor.insertarTiempoLlamanda(tiempoContactoLlamada);
            			moverN(135);
            }
            break;
            default: 
            break;
        }
	 moverD(271);
	 //int k= va.bernoulli(0.9); //////////////////////////////PROB UNIFORME
         //aux=llamadas-1;
	if(aux==1){	/////////////////////////////RECHAZADADOS EN LLAMADAS
            	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
            	monitor.incrementarRechazadasLlamadas();
            	moverS(200);
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
            	moverI(80);
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
            	moverS(292);
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            	moverD(620);
                dormir(125);
                monitor.incrementarLlamadas();
                dormir(125);
                moverS(390);
            			monitor.incrementarEstimacion();
            			gastos(1);
                
            	//imagen.setIcon(new ImageIcon("Imagenes/bola.png"));
            }else
              {			
            	moverN(100);
            	dormir(100);  /////////////////////////ESPERA EN LA PUERTA DE SINIESTROS
            	
        		imagen.setLocation(690,5);
        		monitor.incrementarLlamadas();
        		dormir(125); /////////////////////colaaaaaaaaa
        		siniestros();
            }
        
		    
	}
	
	private synchronized void siniestros(){
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/bola.png")));
		System.out.println("entre1y"+ (siniestros-1));
		System.out.println(va.uniforme(1, (siniestros-1)));
		int i= va.uniforme(1, (siniestros-1)); ///////////////////PROB UNIFORME
		switch (i){
            case 1: {	imagen.setLocation(340,40);
            			dormir(tiempoVerificacionSiniestros); ////////////REVISION EN SINIESTROS
            			monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(310,50);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
            			monitor.incrementarSiniestros();
            			moverS(105);
        				imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
        				moverD(580);
        	}
            break;
            case 2: {	imagen.setLocation(400,40);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(370,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
            }
            break;
            case 3: {	imagen.setLocation(460,40);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(430,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
            }
            break;
            case 4: {	imagen.setLocation(520,40);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(490,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
            case 5: {	imagen.setLocation(580,40);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(550,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
			case 6: {	imagen.setLocation(640,40);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(610,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
						moverI(580);
			}
			break;
			case 7: {	imagen.setLocation(340,170);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(310,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
			case 8: {	imagen.setLocation(400,170);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(370,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
            case 9: {	imagen.setLocation(460,170);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(430,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
			case 10: {	imagen.setLocation(520,170);
						dormir(tiempoVerificacionSiniestros);           ///////////////////////////////////////REVISION EN SINIESTROS
						monitor.insertarTiempoSiniestros((double)tiempoVerificacionSiniestros);
            			imagen.setLocation(490,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
		}
		//monitor.incrementarSiniestros();
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
		moverS(290);
		
		gastos_investigacion();
	}
	
	private synchronized void gastos_investigacion(){
		//int i= va.bernoulli(0.3); //0.3///////////////////////PROB BERMOULLIIIIIII
		switch (0){
            case 0: {	////AL DEPTO DE GASTOS
            			moverS(390);
            			monitor.incrementarEstimacion();
            			gastos(0);
            }
            break;
            case 1: {	////AL DEPTO DE INVESTIGACION
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
            			moverI(138);
            			investigacion();
            }
            break;
		}
	}
	
	private synchronized void gastos(int identificador){
             int i= va.uniforme(1, (gastos-1));
             int aux=0;
            if(identificador==1){
              	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(280);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(589);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(378);
                                        monitor.incrementarImputacion();
                                        aux=0;
            }else{
	    ///////////////////////////PROB UNIFORME
	     switch (i){
            case 1: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		        		moverD(678);
		        		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
		        		moverS(589);
		        		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		        		moverD(778);
                                        monitor.incrementarRechaso();
                                        aux=1;
            }
            break;
            case 2: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(480);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(589);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(578);
                                        monitor.incrementarSalidas();
                                        aux=0;
            }
            break;
	        case 3: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(280);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(589);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(378);
                                        monitor.incrementarImputacion();
                                        aux=0;
                                        
			}
			break;
	        case 4: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(678);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(519);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(778);
                                        aux=0;
			}
			break;
			case 5: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(480);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(519);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(578);
                                        aux=0;
			}
			break;
			case 6: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(280);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(519);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(378);
                                        aux=0;
			}
			break;
			case 7: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(678);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(449);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(778);
                                        aux=0;
			}
			break;
			case 8: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(480);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(449);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(578);
                                        aux=0;
			}
			break;
			case 9: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(280);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(449);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(378);
                                        aux=0;
			}
			break;
		}
            }
		dormir((int)tiempoEstimacionGastos);
		monitor.insertarTiempoEstimacion(tiempoEstimacionGastos);
		salidaGastos(i,aux);
	
     }
	
	private synchronized void investigacion(){
		
		int t=5;
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
		monitor.incrementarInvestigacion();
		
		monitor.incrementarColaInvestigacion();
		
		
		moverS(520);
		dormir((int)tiempoInvestigacion);
		monitor.insertarTiempoInvestigacion(tiempoInvestigacion);
		int i=va.bernoulli(0.7); ////////////////////////////////////////////PROB BERMOULLIIIIIII
		switch (i){
            case 0: {	////FRAUDEEEEEEEEEEEE
            			imagen.setLocation(120, 520);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/bolaRoja.png")));
            			monitor.incrementarFraude();
            			moverN(420);
            			moverI(90);
            			dormir((int)tiempoFraudes);
            			moverN(400);
            			moverI(70);
            			moverN(290);
            			moverI(-20);
            }
            break;
            case 1: {	////AL DEPTO DE GASTOS
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
            			monitor.incrementarEstimacion();
            			imagen.setLocation(160, 520);
            			moverN(450);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            			moverD(230);
						gastos(0);                    ////////********************aqui aumentar otro met de gastoss
			}
            break;
		}
	}
	
	private synchronized void salidaGastos(int pos,int aux){
            int auxgastos=0;
            auxgastos=aux;
		switch (pos){
	        case 1: {	imagen.setLocation(778, 589);
	        			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
		        		moverI(700);
	        }
	        break;
	        case 2: {	imagen.setLocation(578, 589);
	        			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(500);
			 }
	        break;
	        case 3: {	imagen.setLocation(378, 589);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
						moverI(300);
	        }
			break;
	        case 4: {	imagen.setLocation(778, 519);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(700);
			}
			break;
			case 5: {	imagen.setLocation(578, 519);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(500);
			 }
			break;
			case 6: {	imagen.setLocation(378, 519);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
						moverI(300);
			}
			break;
	        case 7: {	imagen.setLocation(778, 449);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(700);
			}
			break;
			case 8: {	imagen.setLocation(578, 449);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(500);
			 }
			break;
			case 9: {	imagen.setLocation(378, 449);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
						moverI(300);
			}
			break;
		}
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
		moverN(402);
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		moverD(775);
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
		moverN(299);
		negociacion_taller(auxgastos);
	}
	
	private synchronized void negociacion_taller(int auxi){
		int i= va.bernoulli(0.5); 
////////////////////////////////PROB BERMOULLIIIIIII
  if(auxi==1){////AL TALLERRR
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            			moverD(845);
            			imagen.setLocation(920, 299);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
            			moverS(600);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            			moverD(1030);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
            			moverN(350);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            			moverD(1065);
            			dormir((int)tiempoTaller);    ////////////REPARANDO
            			monitor.insertarTiempoTaller(tiempoTaller);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/feliz.jpg")));
            			imagen.setLocation(1170, 210);
            			monitor.incrementarTaller();
            			dormir(200);
            			imagen.setVisible(false);
   }else{
			////AL DEPTO DE NEGOCIACION
            			moverN(140);
            			dormir(500);
            			monitor.incrementarNegociacion();
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/feliz.jpg")));
            			imagen.setLocation(920, 90);
            			dormir((int)tiempoNegociacion);
            			monitor.insertarTiempoNegociacion(tiempoNegociacion);
            			imagen.setVisible(false);
       }
	
    }
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////MOVVVVVIMIENTOOOOOOOO
	
	private void moverD(int pos){
		while(play && imagen.getX()<pos){
			try {
				sleep(velocidad*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			imagen.setLocation(imagen.getX()+10, imagen.getY());
		}
	}
	private void moverI(int pos){
		while(play && imagen.getX()>pos){
			try {
				
				sleep(velocidad*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			imagen.setLocation(imagen.getX()-10, imagen.getY());
		}
	}
	private void moverS(int pos){
		while(play && imagen.getY()<pos){
			try {
				sleep(velocidad*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			imagen.setLocation(imagen.getX(), imagen.getY()+10);
		}
	}
	private void moverN(int pos){
		while(play && imagen.getY()>pos){
			try {
				sleep(velocidad*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			imagen.setLocation(imagen.getX(), imagen.getY()-10);
		}
	}
	
	private void dormir(int x){
		try {
			velocidad= monitor.velo;
			sleep(velocidad*x);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
	public void parar() {
		play=false;
		
	}
	public void continuar(){
		play=true;
	}
	public void actualizarvelo(){
		
		velocidad= monitor.velo;
	}


}
