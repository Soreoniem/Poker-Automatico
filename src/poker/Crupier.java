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

/** Quien controlará el juego del Poker
 * @author Juan Lux
 */
public class Crupier extends Persona {

//◘ INICIO PROGRAMA
	//• Variables
	
	//• Constructor
		public Crupier()
		{ this.nombre = "Crupier"; }
		
			// otros constructores
			
	
	//• Accesos •
		//Coger
        
		//Poner
		
		
	//• Metodos •
		/*
			El crupier mezcla la baraja.
			parece absurdo el código pero debe ser así
			(una baraja no se puede llamar a si misma para mezclarse)
		*/
	/** Mezclará la baraja
	 * 
	 * @param nBaraja Baraja que mezclará Crupier.
	 * @return Baraja mezclada de forma aleatoria.
	 */	public Baraja mezclar(Baraja nBaraja)
		{
			nBaraja.mezclar();
			return nBaraja;
		}
		
	/** Metodo para imprimir un error controlado.
	 * 
	 * @param errClase Clase donde se ha dado el error.
	 * @param errMetodo Metodo donde ha dado el error.
	 * @param errMensaje Mensaje de error.
	 * @param errVariable Qué variable ha dado el error.
	 */	@Override
		public void error(String errClase, String errMetodo, String errMensaje, String errVariable)
		{
			System.out.println("\n•Error en "+ errClase +" '"+ errMetodo +"':");
			System.out.println(errMensaje +": "+ errVariable +"\n");
		}
		
	/**Imprime el <b>nombre</b>,<br/>
	 * el <b>número de cartas</b><br/>
	 * y <b>las cartas</b> de la mano
	 * NOTA: Hacer el metodo abstracto si hay tiempo
	 */	@Override
		public void imprimir()
		{
			String frase;
			System.out.println("\nNombre: "+ coger_Nombre());
			System.out.println("Nº Cartas: "+ coger_Mano().coger_Tamaño());
			coger_Mano().imprimir();
		}
	
// ↓↓ No tocar estas llaves ↓↓
}
