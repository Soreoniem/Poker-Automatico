package poker_simple;
// poder usar ArrayList
	import java.util.ArrayList;

/* Poker Simple:
	No hay crupier
	No hay clase mano
	No hay rondas (solo 1)
	No hay mesa
*/
public class Poker {
/*
	• Variables/Atributos •
*/
	
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private Baraja baraja = new Baraja();
	
/*
	• Constructor •
*/
	public Poker()
	{
	// necesitamos jugadores
		this.crearJugadores();
		
			//__visual__
			System.out.println("__Inicio del juego__");
			this.imprimirJugadores();
			this.imprimirBaraja("sin nada");
		
		// Como es solo 1 juego del poker (no variaciones) no necesitamos el metodo "jugar(tipo de poker)"
		
			//__visual__
			System.out.println("\n__Iniciamos el juego__");
	// Añadimos las cartas del poker a la baraja. (ordenadas)
		this.baraja.nuevaBarajaPoker();
		
			//__visual__
			this.imprimirBaraja("ordenada");
		
	// Mezclamos la baraja
		this.baraja.mezclar();
		
			//__visual__
			this.imprimirBaraja("desOrdenada");
	
	//Ya está todo listo para que los jugadores cojan las cartas
		this.repartir();
		
			//__visual__
			System.out.println("\n__Cartas Repartidas a los jugadores__");
			this.imprimirJugadores();
		
	// (EL MÁS DIFÍCIL) Ahora que los jugadores tienen sus cartas en la mano le diremos
	//	a cada jugador que descarte las cartas que vea convenientes
			
			//__visual__
			System.out.println("\n__Los jugadores descartan las cartas__");
		this.descartar();
		
	// Ya solo resta calcular el ganador
		this.mostrarGanador();
	}
	
/*
	• Metodos •
*/
	// Crea 4 jugadores
	private void crearJugadores()
	{
		// necesitamos 4 jugadores → necesitamos 1 for (1 → 4)
		for( int i=1 ; i<=4 ; i++ )
		{
			// añadimos a jugadores(lista) un nuevo jugador
			this.jugadores.add(new Jugador("Jugador "+ i));
		}
		
		/* NOTA
			El jugador 1 será el número 0 de la lista de jugadores
			Si queremos recuperar el jugador 3 → jugadores.get(2)
		*/
	}
	
	// Reparte 5 cartas a cada jugador (total jugadores: 4)
	private void repartir()
	{
		// Necesitamos coger 5 cartas para cada jugador
		// 4 jugadores * 5 cartas = 20 cartas necesarias de la baraja
		
		/* necesitaremos tambien eliminar la carta de la baraja
		*/
		// Necesitamos 2 for (1.recorrerá los jugadores, 2.recorrerá las 5 cartas)
		for( int i=0 ; i<4 ; i++ )
		{
			for( int j=0 ; j<5 ; j++ )
			{
				//Cogemos 1 carta de la baraja por el índice
				// y un jugador de la lista de jugadores guarda la carta en su mano.
				this.jugadores.get(i).guardarCarta( this.baraja.agarrarCarta() );
			}
		}
	}
	
	// Los jugadores descartar las cartas que creen que no son las que necesita.
	private void descartar()
	{
		for( int i=0 ; i<this.jugadores.size() ; i++ )
		{
				//__visual__
				System.out.println("\n__Antes de descartar ("+ this.jugadores.get(i).verNombre() +")__");
				this.imprimirJugador(i);
			
			this.descartarJugador(i);
				//__visual__
				System.out.println("\n__Después de descartar ("+ this.jugadores.get(i).verNombre() +")__");
				this.imprimirJugador(i);

		}
	}
	
	private void descartarJugador( int numJugador )
	{
		// Necesitamos saber que cartas quiere descartar el jugador
		ArrayList<String> descartarPorValores = this.jugadores.get( numJugador ).descartar();
		
		/*NOTA
			No necesitamos un contador para saber cuantas cartas necesitamos coger
			ya que el metodo .size() de descartarPorValores nos devuelve el número
			de cartas descartadas
		*/
		
		for( int i=0 ; i<this.jugadores.get( numJugador ).verTamañoMano() ; i++ )
		{
			for( int j=0 ; j<descartarPorValores.size() ; j++ )
			{
				// como el tamaño de la carta puede disminuir hay que hacer que la j
				// tambien disminuya pero solo si descarta la carta
				
				// Si el valor de la carta i es igual al valor j → hay que descartar
				if( this.jugadores.get( numJugador ).verCarta(i).verValor() == Integer.parseInt( descartarPorValores.get(j) ) )
				{
					// hemos encontraro una coincidencia para descartar
					// cogemos la carta i y la metemos de nuevo en la baraja
					
					this.baraja.guardarCarta( this.jugadores.get( numJugador ).agarrarCarta(i) );
					
					// Al guardar la carta el tamaño de la mano disminuirá
					// por lo que la 'i' también debe hacerlo
					i--;
					
					// como ya sabemos que esta carta es la descartada ya podemos
					// salir del for 'j' por lo que pondremos un valor para salir
					j = descartarPorValores.size();
				}
			}
		}
		// Ahora que el jugador ha descartado las carta ya podemos darle las nuevas.
		for( int i=0 ; i<descartarPorValores.size() ; i++ )
		{
			this.jugadores.get( numJugador ).guardarCarta( this.baraja.agarrarCarta() );
		}
		
		// Ahora el jugador ya tiene las nuevas cartas
	}
	
	// Calcula y muestra al ganador del juego
	private void mostrarGanador()
	{
		// necesitamos un for para recorrer los jugadores y ver su puntuación
		// y también obtener el número del jugador que ha ganado.
		
		// almacenamos la información del primer jugador '0' para iniciar el for
		String ganador	= this.jugadores.get(0).verNombre();
		int puntos		= this.jugadores.get(0).verPuntos();
		
		for( int i=1 ; i<this.jugadores.size() ; i++ )
		{
			// Comprobamos si el jugador actual tiene más puntos
			if(this.jugadores.get(i).verPuntos() > puntos)
			{
				// Los puntos del jugador 'i' son más grandes que los puntos del jugador anterior
				ganador = this.jugadores.get(i).verNombre();
				puntos = this.jugadores.get(i).verPuntos();
			}
		}
		
		// ¡¡FIN DEL POKER!!
		// Ahora que ya tenemos al gandor solo resta imprimir
		System.out.println("\n Participantes");
		this.imprimirJugadores();
		System.out.println("••• ¡¡GANADOR!! •••");
		
		// El jugador ganador y si hubiese empate
		for( int i=0 ; i<this.jugadores.size() ; i++ )
		{
			if(this.jugadores.get(i).verPuntos() == puntos)
			{
				this.imprimirJugador(i);
			}
		}
	}
	
	// Metodo para no estár constantemente imprimiendo los jugadores.
	private void imprimirJugadores()
	{
		System.out.println("\n[_• Jugadores •_]");
		for( int i=0 ; i<this.jugadores.size() ; i++ )
		{
			System.out.print("\n");
			this.jugadores.get(i).imprimir();
		}
	}
	
/** Imprime solo 1 jugador
 * @param jugadorID id del jugador
 */	private void imprimirJugador(int jugadorID)
	{
		System.out.print("\n");
		this.jugadores.get(jugadorID).imprimir();
	}
	
	// Metodo que ayuda a imprimir la baraja
	private void imprimirBaraja(String mensaje)
	{
		System.out.println("\n[_• Baraja ("+ mensaje +") •_]");
		this.baraja.imprimir();
	}
}
