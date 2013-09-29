package helpers;


public class Protocolo {
	public Protocolo() {
		super();
	}

	// funcao writeregister que cria os comandos no protocolo definido para
	// enviar para o ARM
	// Protocolo:
	/**
	 * Botao Protocolo
	 * 
	 */
	public static String writeRegister(int nbotao, int vel) {
		char[] palavra = new char[9];
		palavra[0] = ':';
		palavra[1] = '2';
		palavra[2] = '0';
		palavra[3] = (char) (nbotao + 48);
		palavra[7] = (char) 13;
		palavra[8] = (char) 10;
		if (vel < 10) {
			palavra[4] = '0';
			palavra[5] = '0';
			palavra[6] = (char) (vel + 48);
		} else if (vel <= 99) {
			palavra[4] = '0';
			palavra[5] = (char) ((int) (vel / 10) + 48);
			palavra[6] = (char) ((vel % 10) + 48);
		} else {
			palavra[4] = '1';
			palavra[5] = '0';
			palavra[6] = '0';

		}
		
		System.out.println(palavra);
		return palavra.toString();
	}
}
