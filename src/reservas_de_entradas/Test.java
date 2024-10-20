package reservas_de_entradas;

public class Test {
	
	public static void main(String[] args) {
		
		SistemaReserva sistemaReserva = SistemaReserva.getInstancia();
		
		sistemaReserva.agregarEvento("DEVFEST", 10);
		sistemaReserva.agregarEvento("K-POP", 20);
				
		Thread hilo1 = new Thread(new Usuario(sistemaReserva,"Robe","DevFest",2));
		Thread hilo2 = new Thread(new Usuario(sistemaReserva,"Rafa","K-POP",11));
		Thread hilo3 = new Thread(new Usuario(sistemaReserva,"Fabio","DevFest",8));
		Thread hilo4 = new Thread(new Usuario(sistemaReserva,"Pedro","DevFest",2));
		Thread hilo5 = new Thread(new Usuario(sistemaReserva,"Jonhy Melavo","Coldplay",2));
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		hilo5.start();
				
		try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
			hilo4.join();
			hilo5.join();
		} catch (InterruptedException e) {
			System.err.println("Error");
		}
		
		
		sistemaReserva.entradasRestantes("devfest");
		sistemaReserva.entradasRestantes("k-pop");
	}


}
