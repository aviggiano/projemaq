package connections;

import java.util.ArrayList;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ARM {
	private int velocidadeJog; // valor em % de Vmax
	
    private int baudRate;
    private String commPortName;
    private SerialPort serialPort;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
	
	public ARM() {
		velocidadeJog = 0;
		
	   	baudRate = 9600;
	   	commPortName = "COM1";
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
	
	public void writeRegister (int nbotao, int vel) {
		this.write(Protocolo.writeRegister(nbotao, vel));
	}
	
	public static void read (String s) {

			// Protocolo.doSomething(ARM.nextMessage);
	}

	public void connect() throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(this.commPortName);
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

	public void read() {
		// TODO
	}

	public void write(String s) {
		try {
			outputStream.write(stringToByteArray(s));
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] stringToByteArray(String s) {
		ArrayList<Byte> bytes = new ArrayList<>();
		for (Byte b : s.getBytes()) {
			bytes.add(b);
		}

		byte[] byteArray = new byte[bytes.size()];
		for (int i = 0; i < bytes.size(); i++) {
			byteArray[i] = bytes.get(i);
		}

		return byteArray;
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
					System.out.print(s);
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
