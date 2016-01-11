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
/** Jugador del Poker<br/>
 * <i>en este caso serán PNJ</i><br/>
 * <i>PNJ: "Personaje No Jugador" → Controlados por la máquina</i>.
 * @author Juan Lux
 */
public class Jugador extends Persona {

//◘ INICIO PROGRAMA
/* Cosas que hacer
	• Calcular puntos (en curso...)
	• Calcular la media de los puntos (puede que sea un atributo)
*/
	//• Variables
		/**Puntos de jugador*/
		private int puntos;
		/**Media de puntos del jugador*/
		private int puntosMedia;
	
	//• Constructor
		public Jugador(String nNombre)
		{ this.nombre = nNombre; }
		
			// otros constructores
			
	
	//• Accesos •
		//Coger
	/**Mira los puntos del jugador.
	 * @return Puntos actuales.
	 */	public int mirar_Puntos()
		{ return this.puntos; }
        
		//Poner
		
		
	//• Metodos •
	/** Aumenta los puntos del jugador
	 * @param másPuntos Puntos a sumar.
	 */ public void añadirPuntos(int másPuntos)
		{ this.puntos = this.puntos + másPuntos; }
		
		/** Guarda la carta en la mano del jugador.
		 * @param nCarta Carta para el jugador
		 */ @Override
		public void guardar_Carta(Carta nCarta)
		{
			super.guardar_Carta(nCarta);
			this.puntos = 0;
			
			// calcula los puntos (cartas del 1 al x == 1<=x)
			for( int i=1 ; i<=this.coger_Mano().coger_Tamaño() ; i++ )
			{ this.añadirPuntos( this.coger_Mano().mirarCarta(i).coger_Valor() ); }
			
			this.calcularMedia();
			this.pokerFace();
		}
		
	/** El jugador calcula los puntos de las cartas y la jugada de la mano.*/
		public void calcularPuntos()
		{
			//error si no hay cartas
			if(this.coger_Mano().coger_Tamaño() <=0)
			{
				this.error("Jugador", "calcularPuntos()",
						"La mano del jugador "+ this.coger_Nombre() +" está vacía", "Tamaño: "+ this.coger_Mano().coger_Tamaño());
			}
			else
			{
				this.puntos = 0;

				// calcula los puntos for(1→x)
				for( int i=1 ; i<=this.coger_Mano().coger_Tamaño() ; i++ )
				{ this.añadirPuntos(this.coger_Mano().mirarCarta(i).coger_Valor()); }

				String[] jugada = this.coger_Mano().observarJugada(); //[0]jugada [1]puntos [2]nombreCarta1 [3]nombreCarta2 (si necesita)
				this.añadirPuntos(Integer.parseInt(jugada[1]));
				this.calcularMedia();

				this.pokerFace();
			}
		}
		
	/**Observará la jugada que tiene en la mano.
	 * @return Array con la jugada actual<ul>
	 *		<li>[0]jugada</li>
	 *		<li>[1]puntos</li>
	 *		<li>[2]nombreCarta1</li>
	 *		<li>[3]nombreCarta2 (si necesita)</li>
	 * </ul>
	 */	public String[] comprobarJugada()
		{
			String[] jugada = this.coger_Mano().observarJugada();
			
			return jugada;
		}
		
	/** Posición las cartas que quiere reemplazar de su mano
	 * @return ArrayLista(String): Posiciones y/o "noDescartar".
	 */	public ArrayList<String> descartar()
		{
			int tamañoMano = this.coger_Mano().coger_Tamaño();
			// pasPosCartas = pasarPosicionCartas
			ArrayList<String> pasPosCartas = new ArrayList<String>();
			
			String[] jugada = this.comprobarJugada();
			int valor = 0;			//saber si vale la pena o no almacenar la carta.
			int valorMínimo = 0;	//minimo valor que permite la jugada quedarse con la carta.
			int posición = 10;		// para saber la posición de la carta en la mano.
			
			if(jugada[0] == "poker" || jugada[0] == "trío" || jugada[0] == "una")
			{
				if(jugada[0] == "poker"){valorMínimo = 7; }
				else if(jugada[0] == "trío"){valorMínimo = 10; }
				else if(jugada[0] == "una"){valorMínimo = 11; }
				
				for( int i=1 ; i<=tamañoMano ; i++ )
				{
					if( this.coger_Mano().mirarCarta(i).coger_Número() != jugada[2] )
					{
						valor = this.coger_Mano().mirarCarta(i).coger_Valor();
						
						// descarta si tiene un valor bajo
						if(valor < valorMínimo)
						{ pasPosCartas.add(Integer.toString( this.coger_Mano().mirarCarta(i).coger_Posición() )); }
					}
				}
			}
			else if(jugada[0] == "dos")
			{
				valorMínimo = 9;
				
				for( int i=1 ; i<=tamañoMano ; i++ )
				{
					// descarta si no coincide con las 2 parejas
					if(this.coger_Mano().mirarCarta(i).coger_Número() != jugada[2]
							&& this.coger_Mano().mirarCarta(i).coger_Número() != jugada[3])
					{
						valor = this.coger_Mano().mirarCarta(i).coger_Valor();
						
						if(valor < valorMínimo)
						{ pasPosCartas.add(Integer.toString( this.coger_Mano().mirarCarta(i).coger_Posición() )); }
					}
				}
			}
			else if(jugada[0] == "nada")
			{
				int valorMáximo;
				/**Según los puntos que tenga el jugador desarta unas u otras cartas*/
				if(21 <= this.puntos && this.puntos <= 33)
				{//puntos bajos
					valorMínimo = 1;
					valorMáximo = 4;
				}
				else if(34 <= this.puntos && this.puntos <= 46)
				{//puntos medianos
					valorMínimo = 5;
					valorMáximo = 11;
				}
				else if(47 <= this.puntos && this.puntos <= 59)
				{//puntos altos
					valorMínimo = 12;
					valorMáximo = 14;
				}
				else
				{// resto de puntos (puntos erroneos)
					// (no queremos en la baraja cartas extrañas)
					// cartas con valor 0 son cartas mal creadas
					valorMínimo = -1;
					valorMáximo = -1;
				}
				
				for( int i=1 ; i<=tamañoMano ; i++ )
				{
					valor = this.coger_Mano().mirarCarta(i).coger_Valor();
					
					if(valorMínimo <= valor && valor <= valorMáximo)
					{ pasPosCartas.add(Integer.toString( this.coger_Mano().mirarCarta(i).coger_Posición() )); }	
				}
			}
			return pasPosCartas;
		}
	
// Metodos ayudantes
	// Ayuda a → comprobarJugada()
	/**Calcula la felicidad del Jugador e intenta disimilar un poco<br/>
	 * <i>(más adelante modificarlo para añadir dificultad en la felicidad)</i>
	 */	private void pokerFace()
		{
			this.felicidad = (this.puntos /10);
			this.felicidad = (this.felicidad /3);
			int felicidadMostrada = 0;
			int aleatorio, aleatorioG;
			for( int i=0 ; i<this.felicidad ; i++ )
			{
				aleatorio = (int)( Math.random()*(this.felicidad +1));
				aleatorioG = (int)( Math.random()*101);
				
				//Diferente calculo en la felicidad si tiene Color o más
				if(this.puntos>=500)
				{ if(aleatorioG<80 )
					{ felicidadMostrada++; }
				}
				else if(aleatorio<( (this.felicidad +1) /2) )
				{ felicidadMostrada++; }
				/* INFO
					ir abajo para más información de la felicidad.
				*/
			}
			this.felicidad = felicidadMostrada;
		}
	 
	 // Ayuda a → calcularPuntos() y comprobarJugada()
		/** Jugador: Calcula la media de los valores de las cartas */
		private void calcularMedia()
		{
			double media = (double)this.puntos /(double)this.coger_Mano().coger_Tamaño();
			this.puntosMedia = (int)Math.round(media);
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
	 * y <b>las cartas</b> de la mano.
	 */	@Override
		public void imprimir()
		{
			
			System.out.println("\nNombre: "+ coger_Nombre() +" (felicidad: "+ this.felicidad +")");
			System.out.println("Nº Cartas: "+ coger_Mano().coger_Tamaño() + " ("+ this.imprimirJugada() +")");
			coger_Mano().imprimir();
		}
		
		/** Devuelve la frase con la jugada que tiene en la mano.<br/>
		 * <i>Ejemplo 1: As de Trables</i><br/>
		 * <i>Ejemplo 2: Trío de Reyes</i>
		 * @return Frase de la jugada.
		 */
		public String imprimirJugada()
		{
			// necesito iniciar los strings y el if por si imprimo un jugador sin cartas
			String[] jugada = {"nada", "nada", "nada", "nada"};
			String frase = "sin cartas";
			if(this.coger_Mano().coger_Tamaño() > 4)
			{
				jugada = this.coger_Mano().observarJugada(); //[0]jugada [1]puntos [2]nombreCarta1 [3]nombreCarta2 (si necesita)

				this.coger_Mano().ordenarMano_porValor();

				// añado el plural de Ases y Reyes
				if(jugada[2] == "As" || jugada[2] == "Rey"){jugada[2] = jugada[2] +"es";}
				else {jugada[2] = jugada[2] +"s";}
				
				if(jugada[3] == "As" || jugada[3] == "Rey"){jugada[3] = jugada[3] +"es";}
				else {jugada[3] = jugada[3] +"s";}


				if(jugada[0] == "una")
				{ frase = "Pareja de "+ jugada[2]; }
				else if(jugada[0] == "dos")
				{ frase = "Doble pareja de "+ jugada[2] +" y "+ jugada[3]; }
				else if(jugada[0] == "trío")
				{ frase = "Trío de "+ jugada[2]; }
				else if(jugada[0] == "escalera")
				{ frase = "Escalera de "+ jugada[2]; }
				else if(jugada[0] == "color")
				{ frase = "color de "+ jugada[2]; }
				else if(jugada[0] == "full")
				{ frase = "Full de "+ jugada[2] +" y "+ jugada[3]; }
				else if(jugada[0] == "poker")
				{ frase = "Poker de "+ jugada[2]; }
				else if(jugada[0] == "escaleraColor")
				{ frase = "Escalera de color de "+ jugada[2]; }
				else if(jugada[0] == "escaleraReal")
				{ frase = "¡Escalera Real! de "+ this.mano.mirarCarta(1).coger_Palo(); }
				else
				{
					frase = this.mano.mirarCarta(1).coger_Número() +" de "+ this.mano.mirarCarta(1).coger_Palo();
				}
			}
			
			return frase;
		}
		
		//Override
		
	/* INFO FELICIDAD
		(sin disimular)
			nada:		 0 → 1
			pareja:		 3 → 5
			dobles:		 7 → 8
			trío:		10 → 11
			escalera:	14 → 15
			color:		17 → 18
			full:		20 → 22
			poker:		23 → 25
			escaColor:	27 → 28
			escaReal:	32
		
		(disimulando)
			nada:       0-0  → 0-1
			pareja:     0-3  → 0-4
			dobles:     1-6  → 1-7
			trío:       1-9  → 1-9		(trío		bajo: 9  es difícil que salga)
			escalera:   3-10 → 3-12		(escalera	alta: 12 es difícil que salga)
			color:      8-17 → 11-18	(color		alto: 18 es difícil que salga)
			full:       8-20 → 11-20	(full		bajo: 10 o menos y 20 es difícil que salga)
										(full		alto: 12 o menos es difícil que salga)
			poker:     13-22 → 15-23	(poker		bajo: 13 es difícil que salga)
										(poker		alto: 15 es difícil que salga)
			escaColor: 15-26 → 17-27	(escaColor	bajo: 17 o menos es difícil que salga)
										(escaColor	alto: 27 es difícil que salga)
			escaReal:  20-30			(escaReal	30 es difícil que salga)
	*/
// ↓↓ No tocar estas llaves ↓↓
}
