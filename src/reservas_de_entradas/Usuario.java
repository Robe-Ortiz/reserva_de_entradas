package reservas_de_entradas;

public class Usuario implements Runnable {

	private SistemaReserva sistemaReserva;
	private String nombreUsuario;
	private String nombreEvento;
	private int cantidadEntradas;

	public Usuario(SistemaReserva sistemaReserva, String nombreUsuario, String nombreEvento, int cantidadEntradas) {
		this.sistemaReserva = sistemaReserva;
		this.nombreUsuario = nombreUsuario;
		this.nombreEvento = nombreEvento.toLowerCase();
		this.cantidadEntradas = cantidadEntradas;
	}
	
	private void realizarReserva() {
		boolean reservaEfectuada = sistemaReserva.reservar(nombreEvento, cantidadEntradas);
		
		//Esto sirve para sincronizar el acceso a escribir en consola.
		synchronized(System.out) {
			if(!sistemaReserva.eventoExiste(nombreEvento)) {
				System.err.printf("Lo sentimos %s no existe el evento %s. Imposible realizar reserva\n",nombreUsuario,nombreEvento);
			} else if (reservaEfectuada) {
				System.out.printf("%s reservó %d entradas para el evento %s\n", nombreUsuario, cantidadEntradas,
						nombreEvento);
			} else {
				System.out.printf("%s no pudo reservar %d entrada%s para el evento %s (No hay suficientes entradas)\n",
						nombreUsuario, cantidadEntradas, cantidadEntradas > 1 ? "s" : "", nombreEvento);
			}
		}
	}
	
	@Override
	public void run() {
		realizarReserva();
	}

}
