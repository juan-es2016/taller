package Matematicas;


/**
 * 
 * @autores CARESCA 
 * @version ()
 */
public class PruebaMatematica
{
    private VariableAleatoria var;

    /**
     * Constructor de objetos de la clase PruebaMatematica
     */
    public PruebaMatematica()
    {
        var = new VariableAleatoria();
    }

    /**
     * Metodo contadorVariables generadas con llamada al
     *        metodo var.exponencial.
     *        
     * @return     imprime valores da una variable exponencial
     */
    public void contadorVariable(double num)
    {
        for(int i=0; i<20; i++)
        {
            System.out.println(var.exponencial(3));
        }
    }
}