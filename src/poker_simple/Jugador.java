package poker_simple;
// Necesario para usar ArrayList
	import java.util.ArrayList;

public class Jugador {
/*
	• Variables/Atributos •
*/
	
	private String			nombre;
	private ArrayList<Carta> mano = new ArrayList<Carta>();
	private int				puntos;
	
/*
	• Constructor •
*/
	public Jugador(String nuevoNombre)
	{
		this.nombre = nuevoNombre;
	}

/*
	• (get & set) / (ver, agarrar y guardar) •
*/
	/*NOTA
		ver y agarrar: es lo mismo pero agarrar elimina de su origen (get)
		guardar: almacena los datos (set)
	*/
	
/** mira el tamaño actual de la mano
 * @return Tamaño de la carta
 */	public int verTamañoMano()
	{
		return this.mano.size();
	}
	
/** Mira la carta
 * @param indice lugar de la carta con respecto a la mano
 * @return Carta deseada
 */	public Carta verCarta( int indice )
	{
		return this.mano.get(indice);
	}
	
/** Coge la carta (y la elimina de la mano)
 * @param indice lugar de la carta con respecto a la mano
 * @return Carta deseada
 */	public Carta agarrarCarta( int indice )
	{
		// necesitamos un backup eliminar la carta
		Carta cartaBackup = this.mano.get(indice);
		
		// ahora eliminamos la carta
		this.mano.remove(indice);
		
		// y devolvemos la carta.
		return cartaBackup;
	}
	
/** Ver el nombre del Jugador
 * @return nombre.
 */	public String verNombre()
	{
		return this.nombre;
	}
	
/** Ver los puntos del Jugador
 * @return puntos.
 */	public int verPuntos()
	{
		this.actualizarPuntuación();
		return this.puntos;
	}


/*
	• Metodos •
*/
	// Guarda la carta en la mano
	public void guardarCarta(Carta cartaRecibida)
	{
		this.mano.add(cartaRecibida);
		
		// cuando la mano tega 5 cartas podrá actualizar los puntos
		if(this.mano.size() > 4)
		{
			this.actualizarPuntuación();
		}
	}
	
	
/** descarta las cartas dependiendo de la jugada de la mano.
 * @return Devuelve los valores de las cartas que quiere descartar
 */	public ArrayList<String> descartar()
	{
		// Necesitamos saber que jugada lleva en la mano
		String[] jugada = this.comprobarJugada();
		
		// Necesitamos almacenar el valor de las cartas que queremos descartar.
		// para identificarlas.
		/*NOTA
			No me permite poner "ArrayList<int>"
		*/
		ArrayList<String> descartarCartas = new ArrayList<String>();
		
		/*NOTA
			Trío y pareja tienen el mismo patrón de descartar:
			descartar cartas que no estén repetidas.
		*/
		if( jugada[0] == "Trío"
			|| jugada[0] == "pareja")
		{
			// for que recorrerá la mano
			for( int i=0 ; i<this.mano.size() ; i++ )
			{
				/* if
					Si el número de la carta es diferente al número de la jugada
					quiere decir que no es una carta que forma el trío
				*/
				if( this.mano.get(i).verNúmero() != jugada[1] )
				{
					// Como no es una carta interesante la añadimos a la lista para descartar
					descartarCartas.add( Integer.toString( this.mano.get(i).verValor() ) );
				}
			}
		}
		else if( jugada[0] == "doble parejas" )
		{
			for( int i=0 ; i<this.mano.size() ; i++ )
			{
				// En doble pareja es igual al trío y pareja pero cambia un poco el if
				// (hay que comparar 2 cartas)
				
				if( this.mano.get(i).verNúmero() != jugada[1]
					&& this.mano.get(i).verNúmero() != jugada[2])
				{
					// Si entra en este if es que la carta no coincide con la
					// primera pareja ni con la segunda pareja
					descartarCartas.add( Integer.toString( this.mano.get(i).verValor() ) );
				}
			}
		}
		else
		{
			// Como no tiene Trío, pareja o doble pareja quiere decir que no posee jugada
			// por lo que le decimos que las descarte todas (es la forma simple, claro)
			// Se podría poner al menos que no descarte la más alta (opcional no incluido)
			
			for( int i=0 ; i<this.mano.size() ; i++ )
			{
				descartarCartas.add( Integer.toString( this.mano.get(i).verValor() ) );
			}
		}
		
		// por último devolvemos las cartas (valor del 2 → 14) que queremos descartar.
		return descartarCartas;
	}
	
/** pasarJugada[] Nos servirá para<br/>
	0) almacena la jugada										(Trío, Doble Parejas, Parejas, nada)<br/>
	1) almacena la carta de la jugada							(As, 2, 3, 4, etc.)<br/>
	2) almacena la segunda carta de la jugada si es necesario	(As, 2, 3, etc.)<br/>
	3) almacena los puntos de la jugada							(0, 100, 200, 300)
*/	private String[] comprobarJugada()
	{
		String pasarJugada[]	 = new String[4];
		
		// Cambiaremos esta array en cada if para ir viendo si tiene la jugada
		// por ahora almacenamos si la jugada contiene trío
		String comprobarJugada[] = this.hayTrío();
		
		if(comprobarJugada[0] == "trío")
		{
			// la jugada contiene trío → guardamos la información
			pasarJugada[0] = comprobarJugada[0];	// Trío
			pasarJugada[1] = comprobarJugada[1];	// Número del trío
			pasarJugada[2] = "vacío";
			pasarJugada[3] = "300";					// Puntos por trío
		}
		else
		{
			// Ahora comprobamos si hay parejas en la mano
			comprobarJugada = this.hayParejas();
			
			if(comprobarJugada[0] == "doble parejas")
			{
				pasarJugada[0] = comprobarJugada[0];	//Doble parejas
				pasarJugada[1] = comprobarJugada[1];	//Número pareja 1
				pasarJugada[2] = comprobarJugada[2];	//Número pareja 2
				pasarJugada[3] = "200";					//Puntos por doble pareja
			}
			else if(comprobarJugada[0] == "pareja")
			{
				/*NOTA
					Como en el metodo hayParejas() ya almacenamos los 2 tipos
					de parejas no hace falta llamar de nuevo al metodo.
				*/
				
				pasarJugada[0] = comprobarJugada[0];	//Parejas
				pasarJugada[1] = comprobarJugada[1];	//Número pareja 1
				pasarJugada[2] = "vacío";
				pasarJugada[3] = "100";					//Puntos pod pareja
			}
			else
			{
				// No tiene ninguna jugada
				comprobarJugada = this.cartaMasAlta();
				
				pasarJugada[0] = comprobarJugada[0];	// No tiene jugada
				pasarJugada[1] = comprobarJugada[1];	// Carta alta
				pasarJugada[2] = "vacío";
				pasarJugada[3] = "0";					// No tiene puntos
			}
		}
		
		return pasarJugada;
	}
	
	private String[] hayTrío()
	{
		/*NOTA
			Puede que de error ya que no comprobamos si tiene la
			jugada poker (4 cartas de úmeros iguales y una suelta)
		*/
		/* Necesitamos una array que será el resultado a la
			pregunta ¿Hay Trío en esta mano? a la que por defecto
			le decimos que no
		*/
		String trío[] = {"no", "no"};
		
		/*for
			Como comprobamos 3 cartas a la vez utilizaremos 3 for.
		*/
		/* Tabla para imaginarse el for
			el . (punto) significa hasta donde debe llegar cada
			variable del for
			53655	- simular 5 cartas (numeros)
			i .
			 j .
			  k .
			01234	- numero actual
			
			En este caso tendremos trío en
			i=0, j=3, k=4
		*/
		
		
		/*NOTA
			Cada variable (i, j, k) no debe tener nunca el mismo valor.
			De lo contrario estaríamos comparando la misma carta
		*/
		
		//i= (0 → 2)
		for( int i=0 ; i<this.mano.size() -2 ; i++ )
		{
			//j= (1 → 3)
			for( int j=i +1 ; j<this.mano.size() -1 ; j++ )
			{
				//k= (2 → 4)
				for( int k=j +1 ; k<this.mano.size() ; k++ )
				{
					// for completado, ahora a comprarar 3 cartas por su valor
					
					// Si (i == j) Y (j == k)
					if(this.mano.get(i).verValor() == this.mano.get(j).verValor()
						&& this.mano.get(j).verValor() == this.mano.get(k).verValor())
					{
						// hay trío → guardalo
						
						// guardamos la jugada
						trío[0] = "trío";
						// guardamos el valor del trío
						trío[1] = this.mano.get(i).verNúmero();
					}
					
				}
			}
		}
		
		// devolvemos el array con el resultado
		return trío;
	}
	
	// Comprobamos si hay parejas o dobles parejas
	private String[] hayParejas()
	{
		/*NOTA
			Como ya hemos comprobado si hay trío
		*/
		// Hacemos los pasos parecidos a los de hayTrío()
		// {"parejas" o "doble parejas", "número de la 1ª pareja", "número de la 2ª pareja" }
		String[] parejas = {"no", "no", "no"};
		
		// 2 cartas a comprobar == 2 for 
		/*NOTA
			para más información sobre como funcionan los for ir al metodo hayTrío()
		*/
		
		for( int i=0 ; i<this.mano.size() -1 ; i++ )
		{
			for( int j=i +1 ; j<this.mano.size() ; j++ )
			{
				// Comprobando las parejas
				if(this.mano.get(i).verValor() == this.mano.get(j).verValor())
				{
					/*NOTA
						Si en la array parejas sige habiendo un "no" es que
						es la primera pareja de la mano
					*/
					
					// Primera pareja
					if(parejas[0] == "no")
					{
						parejas[0] = "pareja";
						parejas[1] = this.mano.get(i).verNúmero();
					}
					// Segunda pareja
					else
					{
						parejas[0] = "doble parejas";
						parejas[2] = this.mano.get(i).verNúmero();
					}
				}
			}
		}
		
		return parejas;
	}
	
	//private String[] cartaMasAlta()
	private String[] cartaMasAlta()
	{
		// iniciamos el string como en las demás jugadas
		String[] cartaMasAlta = { "Carta más alta", this.mano.get(0).verNúmero() };
		// para la carta más alta debemos comprobar 2 cartas = 2 for
		// Comprobaremos la carta actual con la anterior = (i == i-1)
		// para eso la i debe iniciarse en 1 en lugar de 0
		// y giardaremos la primera carta como referencia a la más alta
		
		Carta cartaAlta = this.mano.get(0);
		
		// for que recorrerá la mano excepto la primera carta
		for( int i=1 ; i<this.mano.size() ; i++ )
		{
			//if: comprobar la carta actual con la anterior
			if( cartaAlta.verValor() < this.mano.get(i).verValor() )
			{
				// la carta alta es más pequeña que la carta actual de la mano
				// → actualizamos la nueva carta más alta
				
				cartaAlta = this.mano.get(i);
				
				cartaMasAlta[1] = this.mano.get(i).verNúmero();
			}
		}
		
		return cartaMasAlta;
	}
	
	public void imprimir()
	{
		// Nombre del jugador
		System.out.println("[ Jugador: "+ this.nombre +" ]");
		
		//Cartas: jugada
			// este if se ha añadido por un error al imprimir la jugada
			//cuando tiene menos de 5 cartas en la mano
		if(this.mano.size() == 5)
		{
			String[] jugada = this.comprobarJugada();
			String dobleParejaJugada2 = "";

				// si es doble pareja imprimiremos la segunda carta
				if( jugada[0] == "doble parejas" ){ dobleParejaJugada2 = " y "+ jugada[2]; }

			System.out.println("Jugada: "+ jugada[0] +" de "+ jugada[1] + dobleParejaJugada2 + " ("+ this.puntos +"p)");
		}
		
		// Cartas: tamaño
		System.out.println("Cartas: Nº "+ this.mano.size());
		
		// Cartas
		for( int i=0 ; i<this.mano.size() ; i++ )
		{
			this.mano.get(i).imprimir();
		}
	}
	
	private void actualizarPuntuación()
	{
		this.puntos = 0;
		// Para calcular la puntuacón hay que recorrer las cartas de la mano (for)
		// y añadir los puntos
		
		for( int i=0 ; i<this.mano.size() ; i++ )
		{
			this.puntos = this.puntos + this.mano.get(i).verValor();
		}
		
		// Y por último añadirle los puntos de la jugada
		String[] jugada = this.comprobarJugada();
		
		this.puntos = this.puntos + Integer.parseInt( jugada[3] );
	}
}
