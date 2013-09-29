package connections;
import helpers.Protocolo;



public class ARM {
	private int velocidadeJog; // valor em % de Vmax
	private Protocolo protocolo;
	
	public ARM() {
		velocidadeJog = 0;
		protocolo = new Protocolo();
	}
	
	public int getVelocidadeJog() {
		return velocidadeJog;
	}
	
	public void setVelocidadeJog (int velocidadeJog) {
		this.velocidadeJog = velocidadeJog;
	}
	
	public void writeregister (int nbotao, int vel) {
		protocolo.writeregister(nbotao, vel);
	}
}
