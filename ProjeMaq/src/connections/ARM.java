package connections;
import gui.IHM;
import helpers.Protocolo;

public class ARM {
	private int velocidadeJog; // valor em % de Vmax
	private CommunicationPort communicationPort;
	
	private static String lastMessage;
	private static String nextMessage;
	
	public ARM() {
		velocidadeJog = 0;
		communicationPort = new CommunicationPort();
	}
	
	public int getVelocidadeJog() {
		return velocidadeJog;
	}
	
	public void setVelocidadeJog (int velocidadeJog) {
		this.velocidadeJog = velocidadeJog;
	}
	
	public void writeRegister (int nbotao, int vel) {
		communicationPort.write(Protocolo.writeRegister(nbotao, vel));
	}
	
	public static void read (String s) {
		ARM.nextMessage = s;
		if (ARM.lastMessage != ARM.nextMessage) {
			ARM.lastMessage = ARM.nextMessage;

			IHM.append(ARM.nextMessage);
			// Protocolo.doSomething(ARM.nextMessage);
		}
	}
}
