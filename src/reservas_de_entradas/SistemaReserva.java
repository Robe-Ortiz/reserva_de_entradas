package reservas_de_entradas;

import java.util.concurrent.ConcurrentHashMap;

//Singleton
public class SistemaReserva {
	
	private static SistemaReserva instancia;
	
	private ConcurrentHashMap<String, Evento> eventos = new ConcurrentHashMap<>();
	
	private SistemaReserva() {
	}
	
	public static synchronized SistemaReserva getInstancia() {
		if(instancia == null)	
			instancia = new SistemaReserva();
		
		return instancia;
	}
	
	public boolean eventoExiste(String nombreEvento) {
		String nombreEventoCopy = nombreEvento.toLowerCase();
		return eventos.containsKey(nombreEventoCopy);
	}
	
	
	public void agregarEvento(String nombreEvento,int cantidadDeEntradasDisponibles) {
		String nombreEventoCopy = nombreEvento.toLowerCase();
		eventos.putIfAbsent(nombreEventoCopy, new Evento(nombreEventoCopy, cantidadDeEntradasDisponibles));
	}
	
	public boolean reservar(String nombreEvento, int cantidadDeEntradasParaReservar) {	
		String nombreEventoCopy = nombreEvento.toLowerCase();
		Evento evento = eventos.get(nombreEventoCopy);
		if(evento == null) {
			return false;
		}		
		return eventos.get(nombreEventoCopy).reservarEntradas(cantidadDeEntradasParaReservar);
	}
	
	public int entradasRestantes(String nombreEvento) {
		String nombreEventoCopy = nombreEvento.toLowerCase();
		int entradasRestantes = eventos.get(nombreEventoCopy).getEntradasDisponibles();
			
		System.out.printf("Entradas restantes para %s: %d\n",nombreEvento, entradasRestantes);
		return entradasRestantes;
	}
	
}
