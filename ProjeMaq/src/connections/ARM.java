package connections;

import java.util.ArrayList;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gui.IHM;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//teste git 11Oct2013 antonio
public class ARM {
	private int velocidadeJog; // valor em % de Vmax
	private double diametroDaPeca;
	//eu modifiquei aqui hahahaha
    private int baudRate;
    private String commPortName;
    private SerialPort serialPort;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    
	public ARM() {
		velocidadeJog = 0;
		
	   	baudRate = 38400;
	   	commPortName = "COM1";
	   	
	   	try {
			connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public ARM (String commPortName) {
	    	this();
	    	this.commPortName = commPortName;
	 }

	
	public int getVelocidadeJog() {
		return velocidadeJog;
	}
	
	public void setVelocidadeJog (int velocidadeJog) {
		this.velocidadeJog = velocidadeJog;
	}
	
	/**
	 * Funcao utilizada para os botoes diferentes de X+-Z+-, onde isPressed nao tem significado. 
	 * Perceber que essa implementacao, apesar de generica, e' bastante error-prone. 
	 * O programador desavisado pode esquecer de adicionar a variavel isPressed e obter comportamentos inesperados.
	 * 
	 * @param nbotao o numero correspondente do botao, segundo o protocolo.
	 * @param vel a velocidade atual da peca.
	 */
	public void processReceiveCommand (int nbotao, int vel) {
		this.write(Protocolo.processReceiveCommand(nbotao, vel, true));
	}
	
	public void processReceiveCommand (int nbotao, int vel, boolean isPressed) {
		this.write(Protocolo.processReceiveCommand(nbotao, vel, isPressed));
	}
	
	public void processReceiveGCode (String mensagem) {
		System.out.println(mensagem);
		this.write(mensagem);
	}
	
	public void write (String s) {
		try {
			//feito por Renan:
			outputStream.write(stringToByteArray(s + Protocolo.TERMINADOR_DE_MENSAGEM));
			//outputStream.write(stringToByteArray(s + Protocolo.TERMINADOR_DE_MENSAGEM));
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void read (String s) {
		IHM.append("ARM > IHM: " + s);
		// Protocolo.doSomething(s);
	}

	public void connect() throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(this.commPortName);
		
		System.out.println(portIdentifier);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port '" + portIdentifier.getName()
					+ "' is currently in use");
		} else {
			int timeout = 2000;
			CommPort commPort = portIdentifier.open(this.getClass().getName(),
					timeout);
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
				inputStream = serialPort.getInputStream();
				outputStream = serialPort.getOutputStream();

				(new Thread(new SerialReader(inputStream))).start();
				//(new Thread(new SerialWriter(outputStream))).start();
			} else {
				System.out.println("Error: Only serial ports are handled.");
			}
		}
	} // connect

	public void disconnect() {
		try {
			serialPort.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // disconnect

	public byte[] stringToByteArray(String s) {
		byte[] byteArray = new byte[s.length()];
		for (int i = 0; i < s.length(); i++) {
			byteArray[i] = (byte) s.charAt(i);
		}
		System.out.println(byteArray);
		return byteArray;
	}
	
	
	public double getDiametroDaPeca() {
		return diametroDaPeca;
	}

	public void setDiametroDaPeca(double diametroDaPeca) {
		this.diametroDaPeca = diametroDaPeca;
	}

	public static class SerialReader implements Runnable {

		InputStream in;

		public SerialReader(InputStream in) {
			this.in = in;
		}

		public void run() {
			byte[] buffer = new byte[1024];
			int len = -1;
			try {
				while ((len = this.in.read(buffer)) > -1) {
					String s = new String(buffer, 0, len);
					System.out.print(">> " + s);
					ARM.read(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	public static class SerialWriter implements Runnable {

		static OutputStream out;

		public SerialWriter(OutputStream out) {
			SerialWriter.out = out;
		}

		public void run() {
			try {
				int c = 0;
				while ((c = System.in.read()) > -1) {
					SerialWriter.out.write(c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	*/

}
