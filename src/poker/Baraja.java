package poker;
import  java.util.*;
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

/* Arrays y Matrices
	• Array aleatoria hasta 32
	tabla[i] = (int)Math.round(Math.random()*32);
	matriz tipo1
	int[][] origen = new int [10][10];
	matriz tipo2
	int[][] origen = {{ 0,  1,  2,  3,  4,  5},
                  { 6,  7,  8,  9, 10, 11},
                  {12, 13, 14, 15, 16, 17}};
*/

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

/** Baraja de 52 cartas<br/>
 * Palos: (Corazones, Picas, Diamantes y Treboles).<br/>
 * Números: (As → Rey = 13 Cartas)
 * @author Juan Lux
 */
public class Baraja {

//◘ INICIO PROGRAMA
	//• Variables
		/**Donde se almacenan las cartas de la baraja*/
		private ArrayList<Carta> listaCartas = new ArrayList<Carta>();
	
	//• Constructor
		public Baraja()
        {// Crear 52 cartas
			
			// Arrays de Números y Palos (valor se calcula en carta)
			String[] palos = {"Corazones", "Picas", "Diamantes", "Treboles"};
			String[] números = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Sota", "Reina", "Rey"};
			
			// Crea una baraja con las cartas en orden
			for ( int i=0 ; i<palos.length ; i++)
			{
				for ( int j=0 ; j<números.length ; j++ )
				{
					this.listaCartas.add(new Carta(números[j], palos[i]));
				}
			}
        }
		
		
			// otros constructores
			
	
	//• Accesos •
		//Coger
		
		
	//• Metodos •
	/** Mezcla la baraja.<br/>
	 * <i>(no importa el número de la baraja)</i>
	 */	public void mezclar()
		{
			/* __INFO__
				listaCartasAleatorias	- Añadirá las cartas de listaCartas de forma aleatoia.
				aleatorio				- variable de numeros aleatorios.
				for i					- será el número máximo del aleatorio.
											Inicio: tamaño máximo de la lista de cartas.
											Irá disminuyendo al igual que la lista de cartas.
				for						- reará una nueva lista aleatoria.
											Añadirá las cartas de listaCartas a listaCartasAleatorias y las eliminará
											Acabará cuando la lista de cartas esté vacía.
				final: Se añadirán la lista de cartas aleatorias a la lista de cartas
			*/
			
			ArrayList<Carta> listaCartasAleatorias = new ArrayList<Carta>();
			int aleatorio;
			
			for( int i=this.listaCartas.size() ; this.listaCartas.isEmpty() == false ; i-- )
			{
				aleatorio = (int)(Math.random()*i);
				
				listaCartasAleatorias.add( this.listaCartas.get(aleatorio) );
				this.listaCartas.remove(aleatorio);
			}
			
			this.listaCartas = listaCartasAleatorias;
		}
		
	/** Coge la primera carta de la baraja.<br/>
	 * <i>También elimina la carta de la baraja</i>
	 * @return Carta de la Baraja
	 */	public Carta agarrar_Carta()
        {
			Carta pasarCarta = this.listaCartas.get(0);	// coge la carta
			this.listaCartas.remove(0);					// elimina la carta
			return pasarCarta;							// devuelve la carta de origen elimiando
		}
	 
	/** Tamaño actual de las cartas almacenadas
	 * @return Nº total de cartas almacenadas
	 */	public int tamaño()
		{ return this.listaCartas.size(); }
		
	/** Guarda la carta en la última posición.<br/>
	 */	public void guardar_Carta(Carta nCarta)
        { this.listaCartas.add(nCarta); }
		
		/**Imprime las <b>cartas actuales</b> de la baraja*/
		public void imprimir()
		{
			System.out.println("Baraja de "+ this.tamaño() +" cartas");
			for( int i=0 ; i<this.listaCartas.size() ; i++ )
			{
				System.out.println(this.listaCartas.get(i).imprimir());
			}
		}
		//Override
		
	
// ↓↓ Llave de clase: ¡No tocar! ↓↓
}
