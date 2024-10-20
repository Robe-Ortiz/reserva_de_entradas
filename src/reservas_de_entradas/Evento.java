package reservas_de_entradas;

public class Evento {

	private String nombre;
	private int entradasDisponibles;
	
	public Evento(String nombre, int entradasDisponibles) {
		String nombreCopy = nombre.toLowerCase();
		this.nombre = nombreCopy;
		this.entradasDisponibles = entradasDisponibles;
	}

	public int getEntradasDisponibles() {
		return entradasDisponibles;
	}	
	
	public synchronized boolean reservarEntradas(int numeroDeEntradasDeseadas) {
		if(entradasDisponibles < numeroDeEntradasDeseadas)
			return false;
		
		entradasDisponibles -= numeroDeEntradasDeseadas;
		return true;
	}
	
	
}
