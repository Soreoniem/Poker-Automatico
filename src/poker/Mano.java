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
/**
 * Mano de la persona.
 * @author Juan Lux
 */
public class Mano {

//◘ INICIO PROGRAMA
	//• Variables
		/** Las cartas de la mano */
		private ArrayList<Carta> cartas = new ArrayList<Carta>();
	
	//• Constructor
		
			// otros constructores
			
	
	//• Accesos •
		//Coger
	/** Devuelve el número de cartas que posee la mano
	 * @return número de cartas
	 */	public int coger_Tamaño()
		{ return this.cartas.size(); }
        
		//Poner
		
	//• Metodos •
	/** Almacena la carta dentro de la mano
	 * @param nCarta Carta que se le da a la mano
	 */	public void guardar_Carta(Carta nCarta)
		{ this.cartas.add(nCarta); }
		
	/** Coge la primera carta de la mano<br/>
	 * Agarrar: <i>elimina la carta de su origen</i>
	 * @return Carta cogida de la mano
	 */	public Carta agarrar_Carta()
		{
			Carta pasCarta;
			if(this.coger_Tamaño() > 0)
			{ pasCarta = this.cartas.get(0); }
			else
			{
				// Si hay error dará una carta nueva llamada Sin Carta
				// que logicamente la clase Carta dará un aviso de carta erronea
				this.error("Mano", "agarrar_Carta()", "Mano vacía: No se pueden obtener más cartas.", "coger_Tamaño()");
				pasCarta = new Carta("Sin","Carta");
			}
			this.cartas.remove(0);
			return pasCarta;
		}
		
	/** Coge una carta específica de la mano por su posición<br/>
	 * Agarrar: <i>elimina la carta de su origen</i>
	 * @param posición <i>(del 1→5)</i> Posición de la carta de la mano.
	 * @return Carta cogida de la mano
	 */	public Carta agarrar_Carta(int posición)
		{
			posición--;
			Carta pasCarta = this.cartas.get(posición);
			this.cartas.remove(posición);
			return pasCarta;
		}
		
	/**Muestra la carta que tiene en la mano.<br/>
	 * <i>(no elimina la carta de la mano)</i>
	 * @param posCarta <i>(del 1→5)</i> Posición de la carta de la mano.
	 * @return Carta elegida
	 */	public Carta mirarCarta(int posCarta)
		{ return this.cartas.get(posCarta -1); }
		
	/**Mira el tipo de jugada que lleva y obtiene:<br/>
	 * <b>[0]</b> El nombre de la jugada.<br/>
	 * <b>[1]</b> Los puntos obtenidos por la jugada.<br/>
	 * <b>[2]</b> El nombre de la carta más alta de la jugada.<br/>
	 * <b>[3]</b> El otro nombre de la carta más alta si lo lleva.<br/>
	 * <ol>
	 *		<li>Escalera Real: <b>[0]</b>"escaleraReal" <b>[1]</b>900 <b>[2]</b> no tiene: es el As.</li>
	 *		<li>Escalera de Color: <b>[0]</b>"escaleraColor" <b>[1]</b>800 <b>[2]</b></li>
	 *		<li>Poker:         <b>[0]</b>"poker"        <b>[1]</b>700 <b>[2]</b></li>
	 *		<li>Full:          <b>[0]</b>"full"         <b>[1]</b>600 <b>[2]</b> y <b>[3]</b></li>
	 *		<li>Color:         <b>[0]</b>"color"        <b>[1]</b>500 <b>[2]</b></li>
	 *		<li>Escalera:      <b>[0]</b>"escalera"     <b>[1]</b>400 <b>[2]</b></li>
	 *		<li>Trío:          <b>[0]</b>"trío"         <b>[1]</b>300 <b>[2]</b></li>
	 *		<li>Doble parejas: <b>[0]</b>"dos"          <b>[1]</b>200 <b>[2]</b> y <b>[3]</b></li>
	 *		<li>Parejas:       <b>[0]</b>"una"          <b>[1]</b>100 <b>[2]</b></li>
	 *		<li>nada:          <b>[0]</b>"nada"         <b>[1]</b>0</li>
	 * </ol>
	 * Ranquing de Manos:<br/>
	 * <a href="http://www.poquer.com.es/ranking.html">Poker.es</a>
	 * <a href="https://es.wikipedia.org/wiki/Manos_de_p%C3%B3quer">Wikipedia.es</a>
	 * @return String[] Array:<br/>
	 * [0] Nombre de la jugada.<br/>
	 * [1] puntos.<br/>
	 * [2] Nombre de la carta más alta.<br/>
	 * [3] Nombre de las segundas cartas más alta.<br/>
	 * ejemplo: [0] "full" [1]"600" [2]"5" [3]"As"<br/>
	 * (primero el trío de 5s y luego la pareja de Ases)
	 */	public String[] observarJugada()
		{
			String[] jugada = hayEscaleraReal();
		/**Coge la jugada los puntos y el nombre de la jugada.<br/>
		 * ejemplo: "600" "Full" de "5" y "6".<br/>
		 * jugada[0] = tipo de jugada. → "Full"<br/>
		 * jugada[1] = puntos obtenidos por la jugada. → "600"
		 * jugada[2] = obtiene el primer nombre → "trio de 5s"
		 * jugada[3] = obtiene el segundo nombre → "pareja de 6s"
		 */	String[] pasJugada = new String[4];
			
			if(jugada[0] == "escaleraReal")			// 900p
			{
				pasJugada[0] = "escaleraReal";
				pasJugada[1] = "900";
				pasJugada[2] = "As";
				pasJugada[3] = "vacío";
			}
			else
			{
				jugada = hayEscaleraColor();
				if(jugada[0] == "escaleraColor")	// 800p
				{
					pasJugada[0] = "escaleraColor";
					pasJugada[1] = "800";
					pasJugada[2] = jugada[1];
					pasJugada[3] = "vacío";
				}
				else
				{
					jugada = hayPoker();
					if(jugada[0] == "poker")
					{
						pasJugada[0] = "poker";
						pasJugada[1] = "700";
						pasJugada[2] = jugada[1];
						pasJugada[3] = "vacío";
					}
					else
					{
						jugada = hayFull();
						if(jugada[0] == "full")
						{
							pasJugada[0] = "full";		// full de..
							pasJugada[1] = "600";
							pasJugada[2] = jugada[1];	// trios  (nombre)
							pasJugada[3] = jugada[2];	// pareja (nombre)
						}
						else
						{
							jugada = hayColor();
							if(jugada[0] == "color")
							{
								pasJugada[0] = "color";
								pasJugada[1] = "500";
								pasJugada[2] = jugada[1];
								pasJugada[3] = "vacío";
							}
							else
							{
								jugada = hayEscalera();
								if(jugada[0] == "escalera")
								{
									pasJugada[0] = "escalera";
									pasJugada[1] = "400";
									pasJugada[2] = jugada[1];
									pasJugada[3] = "vacío";
								}
								else
								{
									jugada = hayTrío();
									if(jugada[0] == "trío")
									{
										pasJugada[0] = "trío";
										pasJugada[1] = "300";
										pasJugada[2] = jugada[1];
										pasJugada[3] = "vacío";
									}
									else
									{
										jugada = hayParejas();
										if(jugada[0] == "dos")
										{
											pasJugada[0] = "dos";
											pasJugada[1] = "200";
											pasJugada[2] = jugada[1];	// primera pareja
											pasJugada[3] = jugada[2];	// segunda pareja
										}
										else if(jugada[0] == "una")
										{
											pasJugada[0] = "una";
											pasJugada[1] = "100";
											pasJugada[2] = jugada[1];
											pasJugada[3] = "vacío";
										}
										else
										{
											pasJugada[0] = "nada";
											pasJugada[1] = "0";
											pasJugada[2] = "vacío";
											pasJugada[3] = "vacío";
										}
									}
								}
							}
						}
					}
				}
			}
			return pasJugada;
		}
		
	// fase final solo queda que en vez de dar Strings de int
	/**Comprueba si hay parejas o doble parejas
	 * @return String: "una", "dos" o "nada".
	 */	private String[] hayParejas()
		{
			String[] parejas = {"nada", "nada", "nada"}; //"si hay pareja", "primera pareja", "segunda pareja".
			
			for( int i=0 ; i<(this.cartas.size() -1) ; i++ )
			{
				for( int j=(i +1) ; j<this.cartas.size() ; j++ )
				{
					// comprueba las parejas
					if(this.cartas.get(i).coger_Valor() == this.cartas.get(j).coger_Valor())
					{
						// = "nada" ha encontrado la 1ª pareja.
						if(parejas[0] == "nada")
						{
							parejas[0] = "una";
							parejas[1] = this.cartas.get(i).coger_Número();
						}
						// = "una" ha encotrado la 2ª pareja.
						else
						{
							parejas[0] = "dos";
							parejas[2] = this.cartas.get(i).coger_Número();
						}
					}
				}
			}
			return parejas;
		}
		
	/**Comprueba si hay tríos en la mano.
	 * @return String[0]: "trío" o "nada".<br/>
	 *		   String[1]: "Número" el trio o "nada" si no hay trío.
	 */	private String[] hayTrío()
		{
			String[] trío = {"nada", "nada"};
			
			for( int i=0 ; i<(this.cartas.size() -2) ; i++ )
			{
				for( int j=(i +1) ; j<(this.cartas.size() -1) ; j++ )
				{
					for( int k=(j +1) ; k<this.cartas.size() ; k++ )
					{
						if(this.cartas.get(i).coger_Valor() == this.cartas.get(j).coger_Valor()
								&& this.cartas.get(j).coger_Valor() == this.cartas.get(k).coger_Valor())
						{
							trío[0] = "trío";
							trío[1] = this.cartas.get(i).coger_Número();
						}
					}
				}
			}
			return trío;
		}
		
	/**Comprobará si hay escalera en la mano.
	 * @return String[0]: "escalera" o "nada".<br/>
	 *		   String[1]: "Número" carta más alta de la escalera o "nada" si no hay Escalera.
	 */	private String[] hayEscalera()
		{
			String[] escalera = {"escalera", "nada"}; // inicia en "escalera" para que pueda entrar al if
			this.ordenarMano_porValor();
			
			// ¿As es el más grande?
			if(this.mirarCarta(1).coger_Número() == "As")
			{
				escalera[1] = "As";
				// ¿Puede hacer escalera con as?
				if(this.mirarCarta(2).coger_Número() == "Rey"
						|| this.mirarCarta(this.cartas.size()).coger_Número() == "2")
				{
					for( int i=2 ; i<this.coger_Tamaño() ; i++ )
					{
						/* Ejemplo:
							Si escalera sigue siendo escalera y
							Si i=2 (valor 5) = i=1 ( (valor 6 -1) )
						*/
						if(escalera[0] == "escalera" && this.mirarCarta(i +1).coger_Valor() == (this.mirarCarta(i).coger_Valor() -1) )
						{ escalera[0] = "escalera"; }
						
						else
						{
							escalera[0] = "nada";
							escalera[1] = "nada";
						}
					}
				}
				else // As incomparible
				{
					escalera[0] = "nada";
					escalera[1] = "nada";
				}
			}
			else
			{
				// no hay As → comprobar escalera
				escalera[1] = this.mirarCarta(1).coger_Número();
				for( int i=1 ; i<this.coger_Tamaño() ; i++ )
				{
					/* Ejemplo:
						Si escalera sigue siendo escalera y
						Si i=1 (valor 5) = i=0 ( (valor 6 -1) )
					*/
					if(escalera[0] == "escalera" && this.mirarCarta( (i +1) ).coger_Valor() == (this.mirarCarta(i).coger_Valor() -1) )
					{ escalera[0] = "escalera"; }
					else
					{
						escalera[0] = "nada";
						escalera[1] = "nada";
					}
				}
			}
			return escalera;
		}
		
	/**Comprobará si hay Color en la mano.
	 * @return String[0]: "color" o "nada".<br/>
	 *		   String[1]: "Número" de la carta más alta o "nada" si no hay Color.
	 */	private String[] hayColor()
		{
			String[] color = {"color", "nada"};
			int valor = this.cartas.get(0).coger_Valor();
			color[1] = this.cartas.get(0).coger_Número();
			
			for( int i=1 ; i<this.cartas.size() ; i++ )
			{
				if(color[0] == "color" && this.cartas.get(i).coger_Palo() == this.cartas.get((i -1)).coger_Palo())
				{
					color[0] = "color";
					
					// mira la carta más alta del color y la guarda
					if(valor < this.cartas.get(i).coger_Valor())
					{
						valor = this.cartas.get(i).coger_Valor();
						color[1] = this.cartas.get(i).coger_Número();
					}
				}
				else
				{
					color[0] = "nada";
					color[1] = "nada";
				}
			}
			return color;
		}
		
	/**Comprobará si hay full en la mano.
	 * @return String[0]: "full" o "nada".<br/>
	 *		   String[1]: "Número del trío" o nada si no hay Trío.
	 *		   String[2]: "Número de la pareja" o nada si no hay Full.
	 */	private String[] hayFull()
		{
			String[] full = {"nada", "nada", "nada"}; //"si hay full", "numero del trío", "numero del doble"
			ArrayList<Carta> backupCartas = new ArrayList<Carta>();
			String[] jugada = hayTrío();
			
			// Si hay trío guarda el trío en el backup
			if(jugada[0] == "trío")
			{
				this.ordenarMano_porValor();
				//i (1→3) de 5
				for( int i=1 ; i<=(coger_Tamaño() -2) ; i++ )
				{
					//j (2→4) de 5
					for( int j=(i +1) ; j<=(coger_Tamaño() -1) ; j++ )
					{
						//k (3→5) de 5
						for( int k=(j +1) ; k<=coger_Tamaño() ; k++ )
						{
							if(this.mirarCarta(i).coger_Valor() == this.mirarCarta(j).coger_Valor()
									&& this.mirarCarta(j).coger_Valor() == this.mirarCarta(k).coger_Valor())
							{
								// hay trío → guardalo
								full[1] = this.mirarCarta(i).coger_Número();
								backupCartas.add(this.agarrar_Carta(k));
								backupCartas.add(this.agarrar_Carta(j));
								backupCartas.add(this.agarrar_Carta(i));
							}
						}
					}
				}
				jugada = hayParejas();
				// ¿En el resto de cartas hay pareja?.
				if(jugada[0] != "nada")
				{
					full[0] = "full";
					full[2] = this.mirarCarta(1).coger_Número();
				}
				else
				{
					full[0] = "nada";
					full[1] = "nada";
					full[2] = "nada";
				}
			}
			else
			{
				full[0] = "nada";
				full[1] = "nada";
				full[2] = "nada";
			}
			
			// recuperar las cartas guardadas (si las hay)
			while(backupCartas.isEmpty() == false)
			{
				this.guardar_Carta(backupCartas.get(0));
				backupCartas.remove(0);
			}
			return full;
		}
		
	/**Comprueba si hay Poker en la mano.
	 * @return String[0]: "poker" o "nada".<br/>
	 *		   String[1]: "Número de la carta" o "nada" si no hay poker;
	 */	private String[] hayPoker()
		{
			String[] poker = {"nada", "nada"};
			
			for( int i=0 ; i<(this.coger_Tamaño() -3) ; i++ )
			{
				for( int j=(i +1) ; j<(this.coger_Tamaño() -2) ; j++ )
				{
					for( int k=(j +1) ; k<(this.coger_Tamaño() -1) ; k++ )
					{
						for( int l=(k +1) ; l<this.coger_Tamaño() ; l++ )
						{
							if(this.cartas.get(i).coger_Valor() == this.cartas.get(j).coger_Valor()
									&& this.cartas.get(j).coger_Valor() == this.cartas.get(k).coger_Valor()
									&& this.cartas.get(k).coger_Valor() == this.cartas.get(l).coger_Valor())
							{
								poker[0] = "poker";
								poker[1] = this.cartas.get(i).coger_Número();
							}
						}
					}
				}
			}
			return poker;
		}
		
	/**Comprobará si hay Escalera de color en la mano.
	 * @return String[0]: "escaleraColor" o "nada".<br/>
	 *		   String[1]: carta más alta de la escalera o "nada" si no hay escalera.
	 */	private String[] hayEscaleraColor()
		{
			String[] escaleraColor = {"escaleraColor", "nada"}; // inicia en "escalera" para que pueda entrar al if
			this.ordenarMano_porValor();
			
			// ¿As es el más grande?
			if(this.mirarCarta(1).coger_Número() == "As")
			{
				// ¿Puede hacer escalera con as?
				if(this.mirarCarta(2).coger_Número() == "Rey"
						|| this.mirarCarta(this.cartas.size()).coger_Número() == "2")
				{
					for( int i=2 ; i<this.coger_Tamaño() ; i++ )
					{
						/* Ejemplo:
							Si escalera sigue siendo escalera y
							Si i=2 (valor 5) = i=1 ( (valor 6 -1) )
						*/
						if(escaleraColor[0] == "escaleraColor" && this.mirarCarta( (i +1) ).coger_Valor() == (this.mirarCarta(i).coger_Valor() -1)
								&& this.mirarCarta( (i +1) ).coger_Palo() == (this.mirarCarta(i).coger_Palo()))
						{
							escaleraColor[0] = "escaleraColor";
							escaleraColor[1] = "As";
						}
						
						else
						{
							escaleraColor[0] = "nada";
							escaleraColor[1] = "nada";
						}
					}
				}
				else // As incomparible
				{
					escaleraColor[0] = "nada";
					escaleraColor[1] = "nada";
				}
			}
			else
			{
				// no hay As → comprobar escalera
				escaleraColor[1] = this.mirarCarta(1).coger_Número();
				for( int i=1 ; i<this.coger_Tamaño() ; i++ )
				{
					/* Ejemplo:
						Si escalera sigue siendo escalera y
						Si i=1 (valor 5) = i=0 ( (valor 6 -1) )
					*/
					if(escaleraColor[0] == "escaleraColor" && this.mirarCarta( (i +1) ).coger_Valor() == (this.mirarCarta(i).coger_Valor() -1)
								&& this.mirarCarta( (i +1) ).coger_Palo() == (this.mirarCarta(i).coger_Palo()))
					{ escaleraColor[0] = "escaleraColor"; }
					else
					{
						escaleraColor[0] = "nada";
						escaleraColor[1] = "nada";
					}
				}
			}
			return escaleraColor;
		}
		
	/**Comprobará si hay Escalera Real en la mano.
	 * @return String[0]: "escaleraReal" o "nada".<br/>
	 *		   String[1]: No necesario: ya se sabe que es As.
	 */	private String[] hayEscaleraReal()
		{
			String[] escaleraReal = {"escaleraReal"};
			this.ordenarMano_porValor();
			
			// ¿As es el más grande?
			if(this.mirarCarta(1).coger_Número() == "As")
			{
				for( int i=1 ; i<this.coger_Tamaño() ; i++ )
				{
					/* Ejemplo:
						Si escalera sigue siendo escalera y
						Si i=1 (valor 13 rey) = i=0 ( (valor 14 -1 as) )
					*/
					if(escaleraReal[0] == "escaleraReal" && this.mirarCarta( (i +1) ).coger_Valor() == (this.mirarCarta(i).coger_Valor() -1)
							&& this.mirarCarta( (i +1) ).coger_Palo() == (this.mirarCarta(i).coger_Palo()))
					{ escaleraReal[0] = "escaleraReal"; }

					else
					{ escaleraReal[0] = "nada"; }
				}
			}
			else
			{ // no hay As → no hay escalera real
				escaleraReal[0] = "nada";
			}
			return escaleraReal;
		}
		
// Metodos de ayuda
	// Ayuda a → HayEscalera();
	/**Ordena las cartas de la mano.<br/>
	 * Ordena de mayor a menor valor
	 */ public void ordenarMano_porValor()
		{
			// ordenará las cartas en esta lista de cartas
			ArrayList<Carta> cartasOrdenadas = new ArrayList<Carta>();
			int posCartaAlta; // posición de la carta más alta (no importa que esté repetido)
			
			while(this.cartas.isEmpty() == false)
			{
				// del 1 → 5
				posCartaAlta = this.cartaMásAlta(this.cartas);
				cartasOrdenadas.add( this.agarrar_Carta(posCartaAlta) );
			}
			
			// las cartas vuelven a la mano ordenadas
			this.cartas = cartasOrdenadas;
		}
		
	/**Devuelve la posición de la primera carta más alta.
	 * @param	lista Lista de cartas a comprobar el valor mayor.
	 * @return	<i>(1 → 5)</i> Posición de la carta más alta.
	 */	private int cartaMásAlta(ArrayList<Carta> lista)
		{
			// si el tamaño de las cartas es 1 la carta alta está en lista[0]
			if(lista.size() == 1)
			{ return 1; }
			
			// Si tiene 2 cartas o más
			else
			{
				Carta nCarta = lista.get(0);	//Coge la carta [0]
				int posición = 0;				//Almacena la posición de la carta más alta.
				
				/* _for_
					Mira el valor (i) con el anterior (i -1)
					Obtiene la carta y la posición del valor más grande
					Si es igual lo ignora (solo interesa la más grande)
				*/
				for( int i=1 ; i<lista.size() ; i++ ){
					if( nCarta.coger_Valor()<lista.get(i).coger_Valor() )
					{
						nCarta = lista.get(i);
						posición = i;
					}
				}
				// devuelve del 1 al 5
				return (posición +1);
			}
		}
		
		/**Imprime las <b>cartas</b> que tenga la mano.*/
		public void imprimir()
		{
			for( int i=0 ; i<this.cartas.size() ; i++ )
			{ System.out.println(this.cartas.get(i).imprimir()); }
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
