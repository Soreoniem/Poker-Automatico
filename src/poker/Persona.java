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

/*
	nVariable = nueva variable → nNumero = nuevo numero
*/
/** Clase abstracta Persona que de ella heredan Crupier y Jugador<br/>
 * Crupier: Quien controla el juego del Poker.<br/>
 * Jugador: Quien juega al Poker.
 * @author Juan Lux
 */
public abstract class Persona {

//◘ INICIO PROGRAMA
	//• Variables
		/**Nombre*/
        protected String	nombre;
		/**Mano*/
		protected Mano		mano = new Mano();
		/**Felicidad que siente*/
		protected int		felicidad = 0;
	
	//• Constructor
		// Mano empieza vacía
		
			// otros constructores
			
	
	//• Accesos •
		//Coger
		/**Devuelve el nombre.
		 * @return String nombre
		 */
		public String coger_Nombre()
		{ return this.nombre; }
		
	/**Devuelve la mano para interactuar con ella.
	 * @return Mano
	 */	public Mano coger_Mano()
		{ return this.mano; }
		
	/**Mira la felicidad.
	 * @return Nº de felicidad
	 */	public int mirar_Felicidad()
		{ return this.felicidad; }
        
		//Poner
		
		
	//• Metodos •
	/** Guarda una carta en la mano.
	 * @param nCarta Carta que guardará
	 */	public void guardar_Carta(Carta nCarta)
		{ this.coger_Mano().guardar_Carta(nCarta); }
		
	/**Coge la primera carta de la mano.<br/>
	 * <i>(Eliminará la carta de la mano)</i>
	 * @return Carta cogida de la mano.
	 */	public Carta agarrar_Carta()
		{ return this.coger_Mano().agarrar_Carta(); }
		
	/**Imprime el <b>nombre</b>,<br/>
	 * el <b>número de cartas</b><br/>
	 * y <b>las cartas</b> de la mano
	 */	abstract void imprimir();
		
		
	/** Metodo para imprimir un error controlado.
	 * 
	 * @param errClase Clase donde se ha dado el error.
	 * @param errMetodo Metodo donde ha dado el error.
	 * @param errMensaje Mensaje de error.
	 * @param errVariable Qué variable ha dado el error.
	 */	abstract void error(String errClase, String errMetodo, String errMensaje, String errVariable);
		
		//Metodos compartidos
	
// ↓↓ No tocar estas llaves ↓↓
}