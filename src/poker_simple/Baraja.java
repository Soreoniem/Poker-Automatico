package poker_simple;
// Si hacemos * escogerá todo incluyendo ArrayList
	import java.util.*;
/* __Ejemplo__
	Si escribimos dentro de la clase baraja "this."
	nos aparecerá una lista de todas las cosas que podemos hacer
	y en una de ellas podemos leer "ArrayList<E>" que quiere decir que podemos usar
	ArrayList y requiere de un parametro.
*/

public class Baraja {
/*
	• Variables/Atributos •
*/
	// No la llamo "baraja" por que baraja no puede tener una "baraja" dentro si no una lista de cartas.
	private ArrayList<Carta> listaCartas = new ArrayList<Carta>();
	
/*
	• Constructor •
*/
	public void nuevaBarajaPoker()
	{// Poker tiene 52 Cartas
		
				/* NO OBLIGATORIO (ya que es un juego de poker)
					pero se necesita por si se quiere elegir otro tipo de baraja
					requerirá cambiar la clase carta para adaptarse a cualquier baraja
						ejemplo: Bastos, Copas, Espadas, Oros, etc. */
				this.listaCartas.clear();
		
		// Necesitamos los posibles Palos y Numeros del poker
		String[] palos = {"Corazones", "Picas", "Diamantes", "Treboles"};
		String[] números = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Sota", "Reina", "Rey"};

		/*
			Necesitamos crear todas las combinaciones
			por eso usamos 2 for (1 serán los palos, 2 serán los números)
		*/
		for ( int i=0 ; i<palos.length ; i++)
		{
			for ( int j=0 ; j<números.length ; j++ )
			{
				// Añadimos a la lista una nueva "lista.añadir( carta(3, Diamantes) )"
				this.listaCartas.add( new Carta(números[j], palos[i]) );
			}
		}
	}

/*
	• (get & set) / (ver, agarrar y guardar) •
*/
	/*NOTA
		ver y agarrar: es lo mismo pero agarrar elimina de su origen (get)
		guardar: almacena los datos (set)
	*/
/** Coge una carta de la baraja y la elimina de la baraja
 * @return primera carta de la baraja
 */	public Carta agarrarCarta()
	{
		// necesitamos un backup para luego eliminar la carta de la baraja
		Carta backupCarta = this.listaCartas.get(0);
		
		// Ahora eliminamos la carta de la baraja
		this.listaCartas.remove(0);
		
		// luego le pasamos el backup
		return backupCarta;
	}
	
	/** Añade una nueva carta a la baraja
	 * @param nuevaCarta Carta a añadir.
	 */
	public void guardarCarta(Carta nuevaCarta)
	{
		this.listaCartas.add(nuevaCarta);
	}
	
/*
	• Metodos •
*/
	/** Mezcla la lista de cartas */
	public void mezclar()
	{
		
		// Necesitamos otra lista de cartas para almacenar las cartas de forma aleatoria
			// y poder vaciar la actual
		ArrayList<Carta> listaCartasAleatorias = new ArrayList<Carta>();
		
		// Tambien necesitamos el aleatorio para cojer una carta de la baraja
		int aleatorio;

		// Utilizaremos 1 for para recorrer la lista de cartas
		// Como la lista de cartas irá disminuyendo el for será al revés
			/* ejemplo:
				for normal	(0 → 10)
				for revés	(10 → 0)
			*/
		
		/* info sobre el for:
			i inicialmente valdrá el tamaño de la baraja (Poker = 52)
			el for se ejecutará hasta que no hayan cartas (hasta que listaCartas == vacío)
			y por último la 'i' disminuirá (quedarán menos cartas)
		*/
		for( int i=this.listaCartas.size() ; this.listaCartas.isEmpty() == false ; i-- )
		{
			// Numero aleatorio del 0 al tamaño máximo de la lista de cartas (0 → i)
			aleatorio = (int)(Math.random()*i);

			// El aleatorio será nuestro indice para coger una carta
			
			// cogemos la carta aleatoria y la almacenamos en la nueva lista de cartas (listaCartasAleatorias)
			listaCartasAleatorias.add( this.listaCartas.get(aleatorio) );
			
			// por último eliminamos la carta de la lista de cartas (no aleatoria)
			this.listaCartas.remove(aleatorio);
		}

		// ya solo queda actualizar la baraja con la nueva lista
		this.listaCartas = listaCartasAleatorias;
	}
	
	public void imprimir()
	{
		System.out.println("[ inicio ]");
		System.out.println("Nº de cartas: "+ this.listaCartas.size());
		
		// imprimir todas las cartas de la baraja
		for( int i=0 ; i<this.listaCartas.size() ; i++ )
		{
			this.listaCartas.get(i).imprimir();
		}
		System.out.println("[ fin ]");
	}
}
