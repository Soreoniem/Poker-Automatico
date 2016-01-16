package poker_simple;
public class Carta {
/*
	• Variables/Atributos •
*/
	/** As → Rey */
	private String número;
	
	/** Corazones, Picas, Diamantes, Tréboles */
	private String palo;
	
	/** As → Rey = 2 → 13 (As == 14) */
	private int valor;

/*
	• Constructor •
*/
	public Carta(String nuevoNúmero, String nuevoPalo)
	{
		this.número = nuevoNúmero;
		this.palo = nuevoPalo;
		
		this.valor = darValor(nuevoNúmero);
	}

/*
	• (get & set) / (ver y guardar) •
*/
	
	// observar el número de la carta
	public String verNúmero()
	{ return this.número; }
	
	// observar el valor de la carta
	public int verValor()
	{ return this.valor; }

/*
	• Metodos •
*/
	private int darValor(String numeroCarta)
	{
		// valor predeterminado de la carta es 0
		int valorCarta = 0;
		
		// Matriz: El numero de arriba se le asigna el valor de abajo
		// Si no está se le asigna el valor 0
		String[][] arrayValores =	{ {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Sota", "Reina", "Rey"},
									{"14", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"} };
		
		for( int i=0 ; i<13 ; i++ )
		{
			if(numeroCarta == arrayValores[0][i])
			{
				valorCarta = Integer.parseInt( arrayValores[1][i] );
			}
		}
		
		// Si el numero de la carta no es correcto imprime un mensaje de error
		if(valorCarta == 0)
		{
			error("Carta", "darValor(String)",
					"El valor de la carta no es correcto", numeroCarta);
		}
		
		return valorCarta;
	}

	public void imprimir()
	{
		System.out.println("Carta[ "+ this.número +"	de "+ this.palo +",	"+ this.valor +"	]");
	}
	
/** Metodo para imprimir un error controlado.
 * 
 * @param errClase Clase donde se ha dado el error.
 * @param errMetodo Metodo donde ha dado el error.
 * @param errMensaje Mensaje de error.
 * @param errVariable Qué variable ha dado el error.
 */	private void error(String errClase, String errMetodo, String errMensaje, String errVariable)
	{
		System.out.println("\n•Error en "+ errClase +" '"+ errMetodo +"':");
		System.out.println(errMensaje +": "+ errVariable +"\n");
	}
}
