package poker;
/* Cambio de valor

	String	→ Int
		int entero = Integer.parseInt(enteroString);

	Int		→ String
		String enteroString = Integer.toString(entero);
*/
// • Tipos de variables
// double ~Variable~ = 51.2

// • System.out.println("texto" + ~variable~);
// • Enter
// System.out.print("\n\n\n\n\n\n\n\n\n");
//• Entrada por teclado
// int
// int num = teclado.nextInt();
//char
//char num = teclado.next().charAt(0);
// • Raices
// Math.pow()
// • Array aleatoria hasta 32
// tabla[i] = (int)Math.round(Math.random()*32);
// matriz tipo1
// int[][] origen = new int [10][10];
// matriz tipo2
// int[][] origen = {{ 0,  1,  2,  3,  4,  5},
//                   { 6,  7,  8,  9, 10, 11},
//                   {12, 13, 14, 15, 16, 17}};
// Caracteres especiales
// \u00e1 ─ á
// \u00e9 ─ é
// \u00ed ─ í
// \u00f3 ─ ó
// \u00fa ─ ú
// \u00c1 ─ Á
// \u00c9 ─ É
// \u00cd ─ Í
// \u00d3 ─ Ó
// \u00da ─ Ú
// \u00f1 ─ ñ
// \u00d1 ─ Ñ
/*
	MATH
		Math.PI;	-	-	-	-	-	(número PI)
		Math.sqrt();	-	-	-	-	(Raíz cuadrada)
		Math.pow( num , 2);		-	-	(num elevado a 2)
		Math.rint(double);	-	-	-	(Pone el decimal a 0: 5.0, 3.0 etc)
		Math.ritn(double *100) /100;	(double con solo 2 decimales)

	(int) num;	Pasa num a int
*/
/**
 * Crea una Carta
 * Palo: Corazones, Picas, Treboles y Diamantes
 * Valor: Calcula automaticamente el valor de la carta
 * @author Juan Lux
 */
public class Carta {

//◘ INICIO PROGRAMA
	//• Variables
		/**Número de la carta<br/><i>As → Rey</i>*/
        private String	número;
		
		/**Palo de la carta<br/><i>Corazones, Picas, Diamantes y Treboles</i>*/
		private String	palo;
		
		/**Valor de la carta<br/>Automático: <i>Recuerda que el As(valor 14) es más valioso que el Rey(valor 13)</i>*/
		private int		valor;
		
		/**Total de cartas creadas.*/
		private static int numCartasCreadas = 0;
		
		/**Posición de la carta respecto a la baraja.*/
		private int posición = 0;
	
	//• Constructor
		public Carta(String nNúmero, String nPalo)
		{
            this.número = nNúmero;
            this.palo = nPalo;
			comprobarPalo(nPalo);
			
			this.valor = poner_Valor(nNúmero);
			
			numCartasCreadas++;
			this.posición = numCartasCreadas;
        }
		
			// otros constructores
			
	//• Accesos •
		//___Coger___
	/**Devuelve el Número de la carta.
	 * @return <ol>
	 *		<li>As</li><li>2</li><li>3</li><li>4</li><li>5</li><li>6</li><li>7</li>
	 *		<li>8</li><li>9</li><li>10</li><li>Sota</li><li>Reina</li><li>Rey</li>
	 * </ol>
	 */	public String coger_Número()
        { return this.número; }
	
	/**Devuelve el Palo de la carta.
	 * @return <ol>
	 *		<li>Corazones</li><li>Picas</li><li>Diamantes</li><li>Treboles</li>
	 * </ol>
	 */	public String coger_Palo()
        { return this.palo; }
		
	/**Devuelve el Valor de la carta
	 * @return 2 → 14
	 */	public int coger_Valor()
        { return this.valor; }
		
	/**Devuelve el la Posición de la carta
	 * @return 1 → 52
	 */	public int coger_Posición()
        { return this.posición; }
        
		//___Poner___
		
	//• Metodos •
	/**Calcula el valor de la carta recibida
	 * @param nValor Calcular valor de esta carta
	 * @return valor de la carta<br/>Si no tiene valor no es una carta de Poker
	 */ public int poner_Valor(String nValor)
		{
			int numero = 0;
			String[][] valores = {	{"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Sota", "Reina", "Rey"},
									{"14", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",   "12",    "13"}
			};
			
			/*	Asigna los valores a la carta.
				Si cambia el tamaño de la baraja hay que cambiar este for (no lo he hecho automático: podría hacerlo) */
			for( int i=0 ; i<13 ; i++ )
			{
				/*Comprueba número de la carta y le asigna su valor*/
				if(nValor.equals( valores[0][i] ) )		// equals: NetBeans me recomendaba usarlo (if anterior: nValor == valores[0][i])
				{ numero = Integer.parseInt(valores[1][i]); }
			}
			
			// Error si el numero == 0 == el número no está en la lista de la baraja
			if(numero == 0)
			{
				error("Carta", "poner_Valor(String)",
						"El valor de la carta no es correcto", nValor);
			}
			
			return numero;
		}
		
	/**Comprueba si la carta creada tiene un palo correcto
	 * @param nPalo El palo a comprobar
	 */	private void comprobarPalo(String nPalo)
		{
			String[] comprobarPalos = {"Corazones", "Picas", "Diamantes", "Treboles"};
			String resultado = "noPalo";
			
			for( int i=0 ; i<comprobarPalos.length ; i++ )
			{
				if(nPalo == comprobarPalos[i])
				{ resultado = "hayPalo"; }
			}
			
			if(resultado == "noPalo")
			{
				this.error("Carta", "comprobarPalo(String)", 
						"El palo de la carta no es correcto", nPalo);
			}
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
		
	 /** Imprime la carta en un String
	  * @return Numero, palo y valor
	  */
		public String imprimir()
		{
			String tabulador = "	";
			if(this.coger_Palo() == "Picas"){ tabulador = "		"; }
			return "Carta[	"+ coger_Número() +"	de	"+ coger_Palo() +","+ tabulador + coger_Valor() +" ]";
		}
		// igualar → Viene de igualar 2 cartas
		
		//Override
		
	
// ↓↓ No tocar estas llaves ↓↓
}
