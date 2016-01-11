package poker;
import  java.util.ArrayList;
public class Pruebas_Poker {

    public static void main(String[] args) {
		
		/*
			ArrayList al = new ArrayList(); 
			System.out.println("\nTamo inicial: " + al.size()); 
			System.out.println("\n¿Vacío?: " + al.isEmpty()); 

			// add elements to the array list 
			al.add("C");
			al.add("A");
			al.add("E");
			al.add("B");
			al.add("D");
			al.add("F");
			al.add("A2");
			System.out.println("\n Después de añadir: " + al.size()); 
			System.out.println("\n¿Vacío?: " + al.isEmpty()); 
			// display the array list 
			System.out.println("Contents of al: " + al); 
			// Remove elements from the array list 
			al.remove("F"); 
			al.remove(2); 
			System.out.println("Size of al after deletions: " + 
			al.size()); 
			System.out.println("Contents of al: " + al);
		*/
		int numeroJugadores	= 3;
		int numeroRondas	= 2;
		new Poker(numeroJugadores, numeroRondas);
    }
    
}
