/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poker;

import java.util.ArrayList;

/**
 *
 * @author Juan Lux
 */
public class Poker {
//◘ INICIO PROGRAMA

/* ___INFO___
	en los metodos
		coger		-> obtener los datos
		agarrar		-> coger y eliminar del lugar donde estaba
		atributos	-> nCarta	= nuevaCarta
					-> numCarta	= numeroCarta
					-> pasCarta	= pasarCarta	(variable que sevolverá el return)
*/
/* ___Conseguido___
	Carta:
	
	Baraja:
		almacena	→ cartas
		desOrdena	→ lista de cartas
	
	Crupier:
		guarda		→ cartas
		coge		→ cartas de mano
		deja		→ cartas en mesa
	
	Mano:
		almacena	→ cartas
		saca		→ cartas
	
	Mesa:
		almacena	→ cartas de crupier
	
	Jugador:
		coge		→ Cartas
		guarda		→ cartas
	
*/
/*Quehaceres
	Jugador:
		tendrá alegría que dependerá de la comprobación de sus cartas.
		la alegría aumentará en 1 en una decisión de si o no aleatoria.
		a mejor jugada más posibilidades de mostrar su alegría (llamando al metodo más veces)
		
		1. Jugadores: comprobar cartas → y luego añadir alegría.
		2. Jugadores: modificar sus cartas según la alegría de los demás y sus cartas.
	
	Perdedor mostrará su "enfado" su la jugada que pensaba es muy drástica
		1 Mostrar dedo al ser muy grande. etc. y al acercarse: "¡FUUCK! por poco"
	
	Crupier:
	felicidad: se decojonará de las desgracias humanas :V
*/

	//• Variables
		/**Mesa del poker con 3 huecos*/
		private Mesa	mesa;
		/**Quien controla el poker*/
		private Crupier	crupier = new Crupier();
		/**Baraja del poker de 52 cartas*/
		private Baraja	baraja = new Baraja();
		/**Lista de jugadores*/
		private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		
		/**Número de veces que se jugará al poker antes de decidir el ganador*/
		private int ronda = 0;
		
		/**total rondas*/
		private int totalRondas;
		
		/**Número de veces que se jugará al poker antes de decidir el ganador*/
		private int[] vecesGanadas;
		
		/**Jugador de pruena Nº4*/
		private Jugador jugador4;
	
	//• Constructor
	/** Juega al juego del Poker
	 * 
	 * @param numJugadores Nº de <b>jugadores</b>
	 * @param numRondas Nº de <b>rodas</b>
	 */	public Poker(int numJugadores, int numRondas)
		{
			this.vecesGanadas = new int[numJugadores];
		// se crea la mesa
			this.mesa = new Mesa(numJugadores);
			
		// se crean los jugadores
			for( int i=1 ; i<=numJugadores ; i++ )
			{
				this.vecesGanadas[i -1] = 0;
				this.jugadores.add(new Jugador("PNJ "+ i));
			}
			
		// se crea el número de rondas
			this.ronda = numRondas;
			this.totalRondas = numRondas;
			// maximo 10 jugadores
			if(this.ronda >0 && 2 <= this.coger_numJugadores() && this.coger_numJugadores() <=10 )
			{
				this.mensajeMuchasRondas("inicio");
		// se crea el juego
				this.jugar();
				this.mensajeMuchasRondas("final");
			}
			else if(this.ronda <=0)
			{
				// Da la alerta si no hay suficientes rondas para jugar
				this.error("Poker", "constructor Poker(numJugadores, numRondas)",
						"Por favor introduce un numero de rondas mayor a 0", "Rondas: "+ this.ronda);
			}
			else if(2 > this.coger_numJugadores() || this.coger_numJugadores() > 10)
			{
				// Da la alerta si el numero de jugadores no está entre 2 y 10
				this.error("Poker", "constructor Poker(numJugadores, numRondas)",
						"Por favor introduce un numero de jugadores entre 2 y 10", "Nº de jugadores: "+ this.coger_numJugadores());
			}
			
		}
			// otros constructores
			
	
	//• Accesos •
		//Coger
        
		//Poner
		
		
	//• Metodos •
		/** Inicia el juego del Poker */
		private void jugar()
		{
				// solo visual
				int totalRondas = this.ronda;
				int rondasRestantes;
				this.coger_Jugador(1).imprimir();
			while(this.ronda > 0)
			{
				rondasRestantes = (totalRondas - this.ronda) +1;
				System.out.println("╔═══════════════════╗");
				System.out.println("║          Ronda ("+ rondasRestantes +"/"+ totalRondas +")         ║");
				System.out.println("╚═══════════════════╝");
				
				this.mezclarBaraja();	// El Crupier coge la baraja y la mezcla
				this.repartir();			// El Crupier reparte las cartas al los jugadores dejando las cartas en la mesa
				this.nuevaRonda();
				this.calcularGanador();
				this.devolverCartas();
				this.ronda--;
				/*LOOL: Juego acabado solo resta imprimir y mejorar (recuerda mejorar imprimir de Persona)*/
			}
			// devolver cartas
		}
		
		/** Devuelve todas las cartas a la baraja */
		private void devolverCartas()
		{
			this.jugadoresDevolverCartas();
			this.crupierCogerCartas();
			this.crupierDevolverCartas();
		}
		
		/** Todos los jugadores depositan sus cartas en la mesa */
		private void jugadoresDevolverCartas()
		{
			// recorre los jugadores
			for( int i=1 ; i<=this.coger_numJugadores() ; i++ )
			{
				while(this.coger_Jugador(i).coger_Mano().coger_Tamaño() >= 1)
				{
					this.mesa.dejarCarta_Hueco(i, this.coger_Jugador(i).coger_Mano().agarrar_Carta());
				}
			}
		}
		
		/** El Crupier coge todas las cartas de los jugadores */
		private void crupierCogerCartas()
		{
			for( int i=1 ; i<=this.mesa.coger_TamañoHuecos() ; i++)
			{
				while(this.mesa.coger_TamañoHueco(i) >= 1)
				{
					this.crupier.guardar_Carta(this.mesa.agarrarCarta_Hueco(i));
				}
			}
		}
		
		/** El Crupier devuelve las cartas a la baraja */
		private void crupierDevolverCartas()
		{	while(this.crupier.coger_Mano().coger_Tamaño() >= 1)
			{ this.baraja.guardar_Carta(this.crupier.agarrar_Carta()); }
		}
		
		/**Busca e imprime el ganador*/
		private void calcularGanador()
		{
				
			System.out.println("╔════════════════════════╗");
			System.out.println("║                                      ║");
			System.out.println("║          Calculando ganador          ║");
			System.out.println("║                                      ║");
			System.out.println("╚════════════════════════╝");
			
				System.out.println("╔══════════• Puntos •══════════");
				for( int i=1 ; i<=this.coger_numJugadores() ; i++ )
				{
					System.out.println("║ "+ this.coger_Jugador(i).coger_Nombre() +" ("+ this.coger_Jugador(i).mirar_Puntos() +"p)");
				}
			int ganador = 1;
			int puntosGanadores = this.coger_Jugador(1).mirar_Puntos();
			for( int i=2 ; i<=this.coger_numJugadores() ; i++ )
			{
				if(puntosGanadores < this.coger_Jugador(i).mirar_Puntos())
				{
					ganador = i;
					puntosGanadores = this.coger_Jugador(i).mirar_Puntos();
				}
			}
			
			int ganador2 = 1;
			puntosGanadores = this.coger_Jugador(1).mirar_Puntos();
			if(ganador == 1)
			{
				ganador2 = 2;
				puntosGanadores = this.coger_Jugador(2).mirar_Puntos();
			}
			for( int i=2 ; i<=this.coger_numJugadores() ; i++ )
			{
				if(puntosGanadores < this.coger_Jugador(i).mirar_Puntos() && ganador != i)
				{
					ganador2 = i;
					puntosGanadores = this.coger_Jugador(i).mirar_Puntos();
				}
			}
			this.aumentarGanador(ganador, "ganador1");
			this.aumentarGanador(ganador2, "ganador2");
			
			System.out.println("╔═════════════════════════╗");
			System.out.println("║                                       ║");
			System.out.println("║          :( segunda posición          ║");
			System.out.println("║                                       ║");
			System.out.println("↓                                        ↓");
			this.coger_Jugador(ganador2).imprimir();
			System.out.println("╔═════════════════╗");
			System.out.println("║                           ║");
			System.out.println("║          GANADOR          ║");
			System.out.println("║                           ║");
			System.out.println("↓                            ↓");
			this.coger_Jugador(ganador).imprimir();
			
			// ronda final
			if(this.ronda == 1)
			{
				int valorGanador1 = this.coger_valorVecesGanadas(1);
				int posiciónGanador1 = 1;
				
				int valorGanador2 = 0;
				int posiciónGanador2 = 2;
				
				for( int i=2 ; i<=this.coger_numJugadores() ; i++ )
				{
					if(this.coger_valorVecesGanadas(i) > valorGanador1)
					{
						valorGanador2 = valorGanador1;
						posiciónGanador2 = posiciónGanador1;
						
						valorGanador1 = this.coger_valorVecesGanadas(i);
						posiciónGanador1 = i;
					}
					else if(this.coger_valorVecesGanadas(i) > valorGanador2)
					{
						valorGanador2 = this.coger_valorVecesGanadas(i);
						posiciónGanador2 = i;
					}
				}
				System.out.println("\n══════• Ganadores finales del juego •═══════════════\n");
				System.out.println("╔══════════════════════════╗");
				System.out.println("║                                         ║");
				System.out.println("║          ¡¡GANADOR del POKER!!          ║");
				System.out.println("║                                         ║");
				System.out.println("║                                          ↓");
				System.out.println("↓ Puntuación: "+ this.coger_valorVecesGanadas(posiciónGanador1));
				this.coger_Jugador(posiciónGanador1).imprimir();
				
				System.out.println("╔═════════════════════════╗");
				System.out.println("║                                       ║");
				System.out.println("║          :( segunda posición          ║");
				System.out.println("║                                       ║");
				System.out.println("║                                        ↓");
				System.out.println("↓ Puntuación: "+ this.coger_valorVecesGanadas(posiciónGanador2));
				this.coger_Jugador(posiciónGanador2).imprimir();
			}
		}
		
	/** Devuelve el valor de la posición el elegida
	 * @param posición Posición de (1 → x)
	 * @return Veces que ha ganado
	 */	private int coger_valorVecesGanadas(int posición)
		{ return this.vecesGanadas[posición -1]; }
		
	/** Aumenta la puntuación final del jugador.
	 * @param posJugador posición del jugador (1 → x)
	 * @param tipoGanador String de "ganador1" o "ganador2"
	 */	private void aumentarGanador(int posJugador, String tipoGanador)
		{	if(tipoGanador == "ganador1")
			{
				this.vecesGanadas[posJugador -1]++;
				this.vecesGanadas[posJugador -1]++;
			}
			else if(tipoGanador == "ganador2")
			{ this.vecesGanadas[posJugador -1]++; }
		}
		
		/**El crupier coge la baraja y la mezcla*/
		private void mezclarBaraja()
		{
				// visual
				System.out.println("╔══════════════════════╗");
				System.out.println("║          Mezclando cartas         ║");
				System.out.println("╚══════════════════════╝");
			this.baraja = this.crupier.mezclar(this.baraja);
				this.baraja.imprimir();
		}
		

// Acciones del juego
	/** Reparte las cartas a los jugadores.<br/>
	 * <ol>
	 *		<li>El crupier coge las cartas de la baraja.</li>
	 *		<li>Reparte las cartas a la vez en la mesa.</li>
	 *		<li>Cada jugador coge sus cartas.</li>
	 * </ol>
	 */	private void repartir()
		{
			// visual
			System.out.println("╔════════════════════════════════════╗");
			System.out.println("║          El crupier coje las cartas necesarias         ║");
			System.out.println("╚════════════════════════════════════╝");
		/*
			calculo ahora el número de cartas necesarias
			podría meter lo en el for de comparación pero de esta forma
			el for no ha de mirar tantas comprobaciones
		*/	int totalCartas = (this.coger_numJugadores() *5);
			
			// El Crupier coge las 5 cartas de la baraja por jugador
			for( int i=1 ; i<=totalCartas ; i++ )
			{ this.crupier.guardar_Carta(this.baraja.agarrar_Carta()); }
			
				// visual
				this.crupier.imprimir();
				// visual
				System.out.println("╔═════════════════════════════════════════╗");
				System.out.println("║          El crupier reparte las cartas a los jugadores         ║");
				System.out.println("╚═════════════════════════════════════════╝");
			
			// El Crupier pasa 5 veces a cada hueco de la mesa para depositar la carta.
			for( int i=1 ; i<=5 ; i++ )
			{
				// reparte 1 carta a cada hueco de la mesa
				for( int j=1 ; j<=this.coger_numJugadores() ; j++ )
				{
					/* activar para ver al crupier repartir las cartas en la mesa
						System.out.println("Hueco ["+ j +"] "+ this.crupier.coger_Mano().mirarCarta(1).imprimir());
					*/
					this.mesa.dejarCarta_Hueco(j, this.crupier.agarrar_Carta());
				}
			}
				//visual
				this.crupier.imprimir();
				this.mesa.imprimir();
			
			// Cada jugador coge las cartas de su hueco de la mesa.
				System.out.println("╔════════════════════════════════════════════════╗");
				System.out.println("║          Los jugadores recogen las cartas de sus huecos de la mesa         ║");
				System.out.println("╚════════════════════════════════════════════════╝");
			jugadoresCogerCartas();
			this.mesa.imprimir();
			this.imprimirJugadores();
		}
		
		/** Imprime todos los jugadores */
		private void imprimirJugadores()
		{	for( int i=1 ; i<=this.coger_numJugadores() ; i++ )
			{ this.coger_Jugador(i).imprimir(); }
		}
		
		/**Juega una ronda al Poker.*/
		private void nuevaRonda()
		{
				// visual
				System.out.println("╔═══════════════════════════════╗");
				System.out.println("║          Los jugadores miran sus cartas         ║");
				System.out.println("╚═══════════════════════════════╝");
			this.jugadoresCalcularPuntos();
				// visual
				this.imprimirJugadores();
			
			// turno de los jugadores
			for( int i=1 ; i<=this.coger_numJugadores() ; i++ )
			{
				turnoJugador(i);
			}
			this.jugadoresCalcularPuntos();
				// visual
				System.out.println("╔═════════════════════════════════════╗");
				System.out.println("║          Los jugadores miran de nuevo sus cartas         ║");
				System.out.println("╚═════════════════════════════════════╝");
				this.imprimirJugadores();
		}
		
		/** Cada jugador se calcula los puntos de las cartas */
		private void jugadoresCalcularPuntos()
		{	for( int i=1 ; i<=this.coger_numJugadores(); i++ )
			{ this.coger_Jugador(i).calcularPuntos(); }
		}
		
	/**Devuelve el jugador de la lista de jugadores mediante el número del jugador.
	 * @param numJugador Número del jugador.
	 * @return Jugador seleccionado.
	 */	private Jugador coger_Jugador(int numJugador)
		{ return this.jugadores.get( (numJugador -1) ); }
		
// Metodos de ayuda
		
	// Ayuda a → repartir()
		/**Los jugadores cogen las cartas de sus huecos de la mesa*/
		private void jugadoresCogerCartas()
		{
			// for para recorrer los jugadores
			// while para coger las cartas
			for( int i=1 ; i<=this.coger_numJugadores() ; i++ )
			{
				// mientras que el tamaño del hueco i sea más grande a 0
				while(this.mesa.coger_TamañoHueco(i) > 0)
				{
					/* activar para ver a los jugadores antes de coger la carta de la mesa
						System.out.println("El jugador "+ this.cojer_Jugador(i).coger_Nombre() +" coge la"
							+ " carta "+ this.mesa.mirarCarta_Hueco(i, 1).imprimir() +" del hueco["+ i +"]");
					*/
					//ejemplo: jugador 2 guarda la carta del hueco 2 de la mesa hasta que se vacíe
					this.coger_Jugador(i).guardar_Carta(this.mesa.agarrarCarta_Hueco(i));
				}
			}
		}
		
	// Ayuda a → nuevaRonda()
		/** Cada jugador descartará las cartas y recibirá las nuevas*/
		private void turnoJugador(int numJugador)
		{
				// visual
				System.out.println("╔═══════════════════════════╗");
				System.out.println("║          Turno del jugador "+ this.coger_Jugador(numJugador).coger_Nombre() +"         ║");
				System.out.println("╚═══════════════════════════╝");
				this.coger_Jugador(numJugador).imprimir();
				System.out.print("\n");
			
			this.descartar(numJugador);
			this.guardarCartasDescartadas(numJugador);
			this.jugadorCogerCartas(numJugador);
		}
		
		/** @return Numero de jugadores*/
		private int coger_numJugadores()
		{ return this.jugadores.size(); }
		
	/** Descarta las cartas del jugador especificado.
	 * @param numJugador Número deñ jugador que descartará
	 */	private void descartar(int numJugador)
		{
				//visual
				System.out.println("╔══════════════════════════════╗");
				System.out.println("║          Jugador "+ this.coger_Jugador(numJugador).coger_Nombre() +" (descartando)");
			/*Info descartar por jugada
				escaReal	→ no hagas nada
				escaColor	→ no hagas nada
				poker		→ descartar la carta restante
				full		→ no hagas nada
				color		→ no hagas nada
				escalera	→ no hagas nada
				trío		→ descartar las 2 cartas restantes
				doblePar	→ descartar la carta restante
				parejas		→ descartar las 3 restantes
				nada		→ descarta segun tus puntos
			*/
			
			int miFelicidad = this.coger_Jugador(numJugador).mirar_Felicidad();
			boolean felicidadAplastante = false;
			boolean descartarTodo = false;
			
			for( int i=1 ; i<this.coger_numJugadores() ; i++ )
			{
				if(miFelicidad < (this.coger_Jugador(i).mirar_Felicidad() -1))
				{ descartarTodo = true; }
				
				if(miFelicidad < (this.coger_Jugador(i).mirar_Felicidad() -4))
				{ felicidadAplastante = true; }
			}
			String[] jugada = this.coger_Jugador(numJugador).comprobarJugada();
			
			
			ArrayList<String> aDescartar = new ArrayList<String>();
			
			//Si es descartar todo y tiene parejas o menos
				// o la diferencia de felicidad es muy alta
			if((descartarTodo == true && (jugada[0] == "nada" || jugada[0] == "una"))
					|| felicidadAplastante == true)
			{
					// visual
					if(felicidadAplastante == false)
					{ System.out.println("║ *no me fío, descartaré todas por precaución*"); }
					else
					{ System.out.println("║ *Demasiadas risas ¡he de descartar todas!*"); }
				// descarta todas sus cartas (sabe que no va a ganar)
				// Hay alguno demasiado feliz
				for( int i=1 ; i<=this.coger_Jugador(numJugador).coger_Mano().coger_Tamaño() ; i++ )
				{
					aDescartar.add( Integer.toString(this.coger_Jugador(numJugador).coger_Mano().mirarCarta(i).coger_Posición()) );
				}
			}
			// descarte normal
			else
			{
					// visual
					System.out.println("║ *parece que voy bien: descartaré tranquilamente*");
				aDescartar = this.coger_Jugador(numJugador).descartar();
			}

			// si hay cartas a descartar
			if(aDescartar.size()>0)
			{
				int cartasRestantes = aDescartar.size();
				// 1→5
				for( int i=1 ; i<=this.coger_Jugador(numJugador).coger_Mano().coger_Tamaño() ; i++ )
				{
					//0 → x
					for( int j=0 ; j<aDescartar.size() ; j++ )
					{
						//Si la posición de la mano es igual a la posición aDescartar → descarta la carta
						if(this.coger_Jugador(numJugador).coger_Mano().mirarCarta(i).coger_Posición() == Integer.parseInt(aDescartar.get(j)))
						{
								// visual
								System.out.println("║ Descartando: "+ this.coger_Jugador(numJugador).coger_Mano().mirarCarta(i).imprimir());
							// en el hueco del jugador deposita la carta que quiere descartar
							this.mesa.dejarCarta_Hueco(numJugador, this.coger_Jugador(numJugador).coger_Mano().agarrar_Carta(i));
							i--;
							cartasRestantes--;
							j = aDescartar.size();
							if(cartasRestantes == 0)
							{
								i = (this.coger_Jugador(numJugador).coger_Mano().coger_Tamaño() +1);
							}
						}
					}
				}
			}
		}
		
	/** El rupie guardará en la baraja las cartas descartadas por el jugador escogido.
	 * @param numJugador Jugador escogido
	 */	private void guardarCartasDescartadas(int numJugador)
		{
				// visual
				this.mesa.imprimir();
			if(this.mesa.coger_TamañoHueco(numJugador) > 0)
			{
					// visual
					System.out.println("\n─────• Crupier (coger cartas de la mesa) •─────");
					// mira los jugadores que hay por delante del jugador actual
					int jugadoresPorDelante = 0;
					for( int i=1 ; i<=this.coger_numJugadores() ; i++ )
					{	if(this.coger_Jugador(i).mirar_Puntos() > this.coger_Jugador(numJugador).mirar_Puntos())
						{ jugadoresPorDelante++; }
					}
					if(this.mesa.coger_TamañoHueco(numJugador) >3 && jugadoresPorDelante <2)
					{ System.out.println("Crupier: *jejeje*"); }
					
				// El Crupier coge las cartas del hueco de la mesa
				for( int i=this.mesa.coger_TamañoHueco(numJugador) ; i>0 ; i-- )
				{
					this.crupier.guardar_Carta(this.mesa.agarrarCarta_Hueco(numJugador));
				}
					// visual
					this.crupier.imprimir();
					this.mesa.imprimir();
				
					System.out.println("\n─────• Crupier (guardar en la baraja) •─────");
					System.out.println("Tamaño de la baraja (antes): Nº"+ this.baraja.tamaño());
				int numCartas = 0;
				// El Crupier deposita las cartas al final de la baraja
				for( int i=this.crupier.coger_Mano().coger_Tamaño() ; i>0 ; i-- )
				{
					this.baraja.guardar_Carta(this.crupier.coger_Mano().agarrar_Carta());
					numCartas++;
				}
					// visual
					System.out.println("Tamaño de la baraja (después): Nº"+ this.baraja.tamaño());
					this.crupier.imprimir();
				
					System.out.println("\n─────• Crupier (coger nuevas cartas) •─────");
				// El Crupier dará al jugador las cartas restantes (numCartas)
				for( int i=1 ; i<=numCartas ; i++ )
				{
					// Puede que sea un poco absurdo pero es así el juego
					// se podría hacer directamente dejarCarta_Hueco( baraja.agarrar_Carta )
					this.crupier.coger_Mano().guardar_Carta(this.baraja.agarrar_Carta());
					System.out.println("nueva "+ this.crupier.coger_Mano().mirarCarta( this.crupier.coger_Mano().coger_Tamaño() ).imprimir());
					this.mesa.dejarCarta_Hueco(numJugador, this.crupier.coger_Mano().agarrar_Carta());
				}
					this.mesa.imprimir();
			}
		}
	 
	/** El jugador cogerá las cartas que haya en su hueco.
	 * @param numJugador Jugador que cogerá las cartas
	 */	private void jugadorCogerCartas(int numJugador)
		{
				//visual
				System.out.println("╔═════════════════════════════════════╗");
				System.out.println("║          Jugador "+ this.coger_Jugador(numJugador).coger_Nombre() +" (cogiendo nuevas cartas)");
			
			while(this.mesa.coger_TamañoHueco(numJugador) >0)
		   { this.coger_Jugador(numJugador).coger_Mano().guardar_Carta(this.mesa.agarrarCarta_Hueco(numJugador)); }
				// visual
				this.mesa.imprimir();
				this.coger_Jugador(numJugador).imprimir();
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
		
	/** Muestra un mensaje si el numero de rondas es 10 o más
	 * @param posicion "inicio": pone una barra abajo y final pone una barra arriba
	 */	private void mensajeMuchasRondas(String posicion)
		{
			if(posicion == "final") { System.out.println("═══════════════• Final del juego •══════════════════════════"); }
			
			if(this.totalRondas >3)
			{
				System.out.println("(Este mensaje aparecerá el inicio y final del juego)");
				System.out.println("Has escogido muchas rodas, estate preparado para el código por que habrá mucho");
				System.out.println("Nº Rondas elegidas: "+ this.ronda);
			}
			
			if(posicion == "inicio") { System.out.println("═══════════════• Inicio del juego •═════════════════════════"); }
		}
		
		//Override
		
	
// ↓↓ No tocar estas llaves ↓↓

}
