package poker;

import java.util.ArrayList;

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

/** La mesa con la que se jugará a poker<br/>
 * La mesa tendrá 3 huecos<br/>
 * Cada jugador tendrá su hueco para coger y dejar sus cartas.<br/>
 * <i>(si quieres añadir más huecos añadese los en el constructor)</i><br/>
 * <i>(no se crear variables con nombres automáticos)</i>
 * @author Juan Lux
 */
public class Mesa {

//◘ INICIO PROGRAMA
	//• Variables
		/**Almacenará los huecos de la mesa*/
		private ArrayList<ArrayList> huecos = new ArrayList<ArrayList>();
	
	//• Constructor
	/** Crea una mesa de x huecos
	 * @param numHuecos Numero de huecos de la mesa
	 */	public Mesa(int numHuecos)
		{ for( int i=1 ; i<=numHuecos ; i++ )
			{ this.huecos.add(new ArrayList<Carta>()); }
		}
			// otros constructores
			
	
	//• Accesos •
		//Coger
	/** Devolverá el hueco seleccionado.
	 * @param numHueco <i>(del 1→x)</i> Número del hueco.
	 * @return Hueco seleccionado.
	 */	private ArrayList<Carta> coger_Hueco(int numHueco)
		{ return this.huecos.get( (numHueco -1) ); }
	 
	/** Devolverá el tamaño del hueco especificado.
	 * @param numHueco <i>(del 1→x)</i> Número del hueco.
	 * @return Tamaño del hueco.
	 */	public int coger_TamañoHueco(int numHueco)
		{ return this.huecos.get( (numHueco -1) ).size(); }
	 
	/** Devolverá cuantos huecos hay en la mesa.
	 * @return Tamaño de la mesa.
	 */	public int coger_TamañoHuecos()
		{ return this.huecos.size(); }
        
		//Poner		
	//• Metodos •
	/** Dejará en el hueco especificado la carta recibida.
	 * @param numHueco <i>(del 1→x)</i> Hueco que almacenará la carta.
	 * @param nCarta Carta que guardará en el hueco de la mesa.
	 */	public void dejarCarta_Hueco(int numHueco, Carta nCarta)
		{ this.huecos.get( (numHueco -1) ).add(nCarta); }
		
	/** Agarra una carta del hueco especificado<br/>
	 * <i>agarrar: devolverá la carta y la eliminará de su origen.</i>
	 * @param numHueco <i>(del 1→x)</i> Número del hueco a coger carta.
	 * @return Carta del hueco escogido.
	 */	public Carta agarrarCarta_Hueco(int numHueco)
		{
			Carta pasCarta;
				// si no le quedan cartas dará el mensaje de 
				if(this.coger_TamañoHueco(numHueco) <= 0)
				{
					this.error("Mesa", "agarrarCarta_Hueco(numHueco)", "Hueco vacío: No se pueden coger más cartas.", "numHueco");
					pasCarta = new Carta("Sin","Carta");
				}
				
				else
				{ pasCarta = this.coger_Hueco(numHueco).get(0); }
			
			this.coger_Hueco(numHueco).remove(0);
			return pasCarta;
		}
	 
	/** Mira una carta del hueco especificado<br/>
	 * <i>mirar: devolverá la carta y NO la eliminará.</i>
	 * @param numHueco <i>(del 1→x)</i> Número del hueco a mirar carta.
	 * @param posCarta <i>(del 1→x)</i> Número de la posición de la carta del hueco de la mesa.
	 * @return Carta en la posición especifica del hueco escogido.
	 */	public Carta mirarCarta_Hueco(int numHueco, int posCarta)
		{
			Carta pasCarta;
				// si no le quedan cartas dará el mensaje de 
				if(this.coger_TamañoHueco(numHueco) <= 0)
				{
					this.error("Mesa", "mirarCarta_Hueco(numHueco, posCarta)",
							"Hueco["+ numHueco +"] vacío: No hay cartas a mostrar.", "Tamaño del hueco");
					pasCarta = new Carta("Sin","Carta");
				}
				
				else
				{ pasCarta = this.coger_Hueco(numHueco).get( (posCarta -1) ); }
			
			return pasCarta;
		}
		
	/** Imprime la mesa mostrando las <b>cartas</b> que contienen los huecos<br/>
	 * mostrando si el hueco tiene cartas o está vacío.
	 */	public void imprimir()
		{
			System.out.println("\n___ Mesa ___");
			
			// imprime los huecos (del 1→x)
			for( int i=1 ; i<=this.coger_TamañoHuecos() ; i++ )
			{
				System.out.println("\nhueco: "+ i + this.comprobar_huecoVacio( this.coger_Hueco(i) ));
				
				// imprime las cartas de los huecos
				for( int j=0 ; j<this.coger_TamañoHueco(i) ; j++ )
				{
					// coje el hueco i y muestra la carta j
					System.out.println(this.coger_Hueco(i).get(j).imprimir());
				}
			}
		}
		
	 /** Metodo que compruba si el hueco de la mesa está vacío.
	  * @param hueco El hueco de la mesa a comprobar.
	  * @return String "(vacio)" si no hay cartas en el hueco
	  */private String comprobar_huecoVacio(ArrayList<Carta> hueco)
		{
			if(hueco.isEmpty()) { return " (vacio)"; }
				else { return ""; }
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
		// igualar → Viene de igualar 2 cartas
		
		//Override
		
	
// ↓↓ No tocar estas llaves ↓↓
}
