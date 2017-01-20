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
    public double tiempoRecepcionDenuncia;
    public int tiempoRecoleccionPruebas;
    public double tiempoImputacion;
    public double tiempoJuicio;
    public double tiempoInvestigacion;
    public double tiempoProceso;
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
	    
	    
		System.out.println("denuncias "+tiempoRecepcionDenuncia);
	    System.out.println("tiempo pruebas "+tiempoRecoleccionPruebas);
	    System.out.println("tiempo imputacion "+tiempoImputacion);
	    System.out.println("tiempo juicio "+tiempoJuicio);
	    System.out.println("tiempo investigacion "+tiempoInvestigacion);
	    System.out.println("tiempo del proceso "+tiempoProceso);
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
		
	    tiempoRecepcionDenuncia=atencionDenuncia()*monitor.velo/100;
	    tiempoRecoleccionPruebas=recoleccionPruebas()*monitor.velo/100;
	    tiempoImputacion=imputacion()*monitor.velo/100;
	   tiempoJuicio=juicio()*monitor.velo/100;
	    tiempoInvestigacion=(va.uniforme(2, 4)*100)*monitor.velo/100;
	    tiempoProceso=proceso()*monitor.velo/100;
	    tiempoTaller=(va.transformadaInversaTaller())*100*monitor.velo/100;
	}
	private int atencionDenuncia(){
		int res=monitor.m_atencionLlamada;
		if (res==0){res=(int)(va.exponencial(3.4))*100;}
		else if (res==1){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		else if (res==2){res=(int)(va.normal(0.5, 4.5, 10))*100;}  //normal
		else if (res==3){res=(va.transformadaInversaSiniestros())*10;}  //trns inversa
		else if (res==4){res=(int)(va.exponencial(3.4))*100;}  //bermolli
		return res;
	}
	private int recoleccionPruebas(){
		int res=monitor.m_atencionSiniestros;
		if (res==0){res=(va.transformadaInversaSiniestros())*10;}  //Trans. Inversa
		else if (res==1){res=(int)(va.normal(2.2, 1.33, 100))*100;}  //normal
		else if (res==2){res=(int)(va.exponencial(3.4))*100;}  //esponencial
		else if (res==3){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		return res;
	}
	private int juicio(){
		int res=monitor.m_atencionLlamada;
		if (res==0){res=(int)(va.normal(2.2, 1.33, 100))*100;}
		else if (res==1){res=(int)(va.exponencial(3.4))*100;}  //exponencial
		else if (res==2){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		else if (res==3){res=(va.transformadaInversaSiniestros())*10;}  //trns inversa
		return res;
	}
	private int imputacion(){
		int res=monitor.m_atencionLlamada;
		if (res==0){res=(int)(va.uniforme(8, 10)*100);}  //uniforme
		else if (res==1){res=(int)(va.normal(2.2, 1.33, 100))*100;}  //normal
		else if (res==2){res=(int)(va.exponencial(3.4))*100;}  //exponencial
		else if (res==3){res=(va.transformadaInversaSiniestros())*10;}  //trns inversa
		return res;
	}
	private int proceso(){
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
            case 1: {	imagen.setLocation(110,80);
            			monitor.incrementarTotal();
                                monitor.incrementarQfiscal();
            			dormir((int)tiempoRecepcionDenuncia);  /////////////////TIEMPO DE UNA LLAMADA
            			monitor.insertarTiempoLlamanda(tiempoRecepcionDenuncia);
            			moverD(290);
                                moverS(250);
        	}
            break;
            case 2: {	imagen.setLocation(110,230);
            			monitor.incrementarTotal();
                                monitor.incrementarDfiscal();
            			dormir((int)tiempoRecepcionDenuncia);
            			monitor.insertarTiempoLlamanda(tiempoRecepcionDenuncia);
            			moverD(290);
                                moverS(250);
                                
                              
    		}
            break;
            case 3: {	imagen.setLocation(110,380);
            			monitor.incrementarTotal();
                                monitor.incrementarDpolicial();
            			dormir((int)tiempoRecepcionDenuncia);
            			monitor.insertarTiempoLlamanda(tiempoRecepcionDenuncia);
            			moverD(290);
                                moverN(250);
			}
            break;
            case 4: {	imagen.setLocation(110,530);
            			monitor.incrementarTotal();
                                monitor.incrementarFlagrante();
            			dormir((int)tiempoRecepcionDenuncia);
            			monitor.insertarTiempoLlamanda(tiempoRecepcionDenuncia);
                                aux=1;
			}
            break;
            case 5: {	imagen.setLocation(60,170);
            			monitor.incrementarTotal();
            			dormir((int)tiempoRecepcionDenuncia);
            			monitor.insertarTiempoLlamanda(tiempoRecepcionDenuncia);
            			moverN(135);
			}
            break;
            case 6: {	imagen.setLocation(120,170);
            			monitor.incrementarTotal();
            			dormir((int)tiempoRecepcionDenuncia);
            			monitor.insertarTiempoLlamanda(tiempoRecepcionDenuncia);
            			moverN(135);
            }
            break;
            case 7: {	imagen.setLocation(180,170);
            			monitor.incrementarTotal();
            			dormir((int)tiempoRecepcionDenuncia);
            			monitor.insertarTiempoLlamanda(tiempoRecepcionDenuncia);
            			moverN(135);
            }
            break;
            default: 
            break;
        }
	
	if(aux==1){	////////////////////////////CASO FLAGRANTE
            	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
                 moverD(370);
            	monitor.incrementarRechazadasLlamadas();
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
                moverN(480);
            	monitor.incrementarEstimacion();
            	decisionFiscal(1);
                
            }else
              {			
            	moverD(450);
            	dormir(100);  /////////////////////////ESPERA EL TIEMPO DE RECOLECCION DE PRUEBAS
            	
        		//imagen.setLocation(690,5);
        		monitor.incrementarLlamadas();
        		dormir(125); /////////////////////colaaaaaaaaa
        		actuaciones();
            }
        
		    
	}
	
	private synchronized void actuaciones(){
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/bola.png")));
		System.out.println("entre1y"+ (siniestros-1));
		System.out.println(va.uniforme(1, (siniestros-1)));
		int i= va.uniforme(1, (siniestros-1)); ///////////////////PROB UNIFORME
		switch (i){
            case 1: {	imagen.setLocation(500,250);
            			dormir(tiempoRecoleccionPruebas); ////////
            			monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(500,250);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            			monitor.incrementarSiniestros();
            			moverD(620);
        			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
        	}
            break;
            case 2: {	imagen.setLocation(400,40);
						dormir(tiempoRecoleccionPruebas);           ////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(370,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
            }
            break;
            case 3: {	imagen.setLocation(460,40);
						dormir(tiempoRecoleccionPruebas);           ////////////recabacion de pruebas
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(430,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
            }
            break;
            case 4: {	imagen.setLocation(520,40);
						dormir(tiempoRecoleccionPruebas);           ///////////////////////////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(490,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
            case 5: {	imagen.setLocation(580,40);
						dormir(tiempoRecoleccionPruebas);           ///////////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(550,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
			case 6: {	imagen.setLocation(640,40);
						dormir(tiempoRecoleccionPruebas);           ///////////////////////////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(610,50);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
						monitor.incrementarSiniestros();
						moverS(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
						moverI(580);
			}
			break;
			case 7: {	imagen.setLocation(340,170);
						dormir(tiempoRecoleccionPruebas);           ///////////////////////////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(310,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
			case 8: {	imagen.setLocation(400,170);
						dormir(tiempoRecoleccionPruebas);           ///////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(370,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
            case 9: {	imagen.setLocation(460,170);
						dormir(tiempoRecoleccionPruebas);           //////////////////////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(430,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
			case 10: {	imagen.setLocation(520,170);
						dormir(tiempoRecoleccionPruebas);           /////////
						monitor.insertarTiempoSiniestros((double)tiempoRecoleccionPruebas);
            			imagen.setLocation(490,180);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						monitor.incrementarSiniestros();
						moverN(105);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(580);
			}
			break;
		}
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
		decisionFiscal(0);
	}

	
	private synchronized void decisionFiscal(int identificador){
             int i= va.uniforme(1, (gastos-1));
             int aux=0;
            if(identificador==1){
           
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		moverD(550);
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
		moverN(310);
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		moverD(600);
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
		moverN(250);
                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		moverD(700);
       
            }else{
	    ///////////////////////////PROB UNIFORME
	     switch (i){
            case 1: {	
		        		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
		        		moverN(75);
                                        imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		        		moverD(700);
                                        monitor.incrementarRechaso();
                                        aux=1;
            }
            break;
            case 2: {	
                                        
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(700);
                                     
                                        monitor.incrementarImputacion();
                                        aux=0;
            }
            break;
	        case 3: {	
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(480);
                                        imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(700);
			    		//imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		//moverD(525);
                                        monitor.incrementarSalidas();
                                        aux=2;
                                        
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
		dormir((int)tiempoImputacion);
		monitor.insertarTiempoEstimacion(tiempoImputacion);
		salidaDesicionFiscal(i,aux);
	
     }
	
	private synchronized void investigacion(){
		
		int t=5;
		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
		monitor.incrementarInvestigacion();
		
		monitor.incrementarColaInvestigacion();
		
		
		moverS(520);
		dormir((int)tiempoJuicio);
		monitor.insertarTiempoInvestigacion(tiempoJuicio);
		int i=va.bernoulli(0.7); ////////////////////////////////////////////PROB BERMOULLIIIIIII
		switch (i){
            case 0: {	////FRAUDEEEEEEEEEEEE
            			imagen.setLocation(120, 520);
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/bolaRoja.png")));
            			monitor.incrementarFraude();
            			moverN(420);
            			moverI(90);
            			dormir((int)tiempoInvestigacion);
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
						decisionFiscal(0);                    ////////********************aqui aumentar otro met de gastoss
			}
            break;
		}
	}
	
	private synchronized void salidaDesicionFiscal(int pos,int aux){
            int auxgastos=0;
            auxgastos=aux;
		switch (pos){
	        case 1: {	imagen.setLocation(778,75);
	        			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		        		moverD(950);
	        }
	        break;
	        case 2: {	imagen.setLocation(778, 275);
                                        imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(840);
                                        imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
			    		moverN(140);
	        			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(950);
                                    
                                        
			 }
	        break;
	        case 3: {	imagen.setLocation(778,480);
                                                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
						moverD(700);
						imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
						
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
	
		negociacion_taller(auxgastos);
	}
	
	private synchronized void negociacion_taller(int auxi){
		int i= va.bernoulli(0.5); 
////////////////////////////////PROB BERMOULLIIIIIII
         if(auxi==1 || auxi==0){////salir
            		
            			imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            			moverD(1200);
            			dormir((int)tiempoTaller);    ////////////REPARANDO
            			monitor.insertarTiempoTaller(tiempoTaller);
            			
            			monitor.incrementarTaller();
            			dormir(200);
         }else{
			
                                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
            			//moverN(370);
                                imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
            			moverD(890);
            			//dormir(450);
            			monitor.incrementarNegociacion();

            			dormir((int)tiempoProceso);

                                salidaAlternativa();
       }
	
    }
  
        private synchronized void salidaAlternativa(){
            int i= va.uniforme(1, (llamadas-1));
	    ///////////////////////////PROB UNIFORME
	     switch (i){
            case 1: {	
		        		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
		        		moverN(270);
                                        imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		        		moverD(1150);
                                        monitor.incrementarAbreviado();
            }
            break;
            case 2: {	
                                        
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaN.gif")));
			    		moverN(375);
                                        imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
		        		moverD(1150);
                                        monitor.incrementarConciliacion();
            }
            break;
	        case 3: {	
                                        imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(1150);
			    		
                                        monitor.incrementarOportunidades();
                                        
			}
			break;
	        case 4: {	
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(550);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(1150);
                                          monitor.incrementarSuspencion();
			}
			break;
			case 5: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(480);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(519);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(578);
			}
			break;
			case 6: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(280);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(519);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(378);
			}
			break;
			case 7: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(678);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(449);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(778);
			}
			break;
			case 8: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(480);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(449);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(578);
			}
			break;
			case 9: {	imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaI.gif")));
			    		moverI(280);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaS.gif")));
			    		moverS(449);
			    		imagen.setIcon(new ImageIcon(getClass().getResource("/images/caminaD.gif")));
			    		moverD(378);
			}
			break;
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
