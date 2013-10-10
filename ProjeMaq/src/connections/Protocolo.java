package connections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Protocolo {
	
    public static final String TERMINADOR_DE_MENSAGEM = "!%"; 
    public static final int UPLINK_MESSAGE_SIZE = 19;
    public static final int EVENTUAL_MESSAGE_SIZE = 3;
    public static final int CODIGO_G_MESSAGE_SIZE = 27;
	
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
	public static String writeRegister(int nbotao, int vel, boolean isPressed) {
		char[] palavra = new char[10];
		palavra[0] = ':';						//inicio de mensagem
		palavra[1] = '2';						//funcao do botao = funcao '2'
		palavra[2] = '0';						//nbotao
		palavra[3] = (char) (nbotao + 48);		//nbotao
		palavra[7] = (isPressed)? '1' : '0';	//ispressed
		palavra[8] = '\r';						//fim de mensagem \r
		palavra[9] = '\n';						//fim de mensagem \n
		if (vel < 10) {							//velocidade velocidade velocidade
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
		return  new String(palavra);
	}
	
	public Map<String, String> uplink(String mensagem) throws Exception {
		if (mensagem.length() != UPLINK_MESSAGE_SIZE) {
			throw new Exception("Tamanho do uplink incompatível com o protocolo.");
		}
		Map<String, String> ans = new HashMap<String, String>();
		
		ans.put("X", mensagem.substring(2,   8));
		ans.put("Z", mensagem.substring(8,  14));
		ans.put("G", mensagem.substring(14, 17));
		ans.put("S", mensagem.substring(17, 18));
		ans.put("F", mensagem.substring(18));
		
		return ans;
	}
	
	public boolean eventuais (String mensagem) throws Exception {
		if (mensagem.length() != EVENTUAL_MESSAGE_SIZE) {
			throw new Exception("Tamanho da mensagem eventual incompatível com o protocolo.");
		}
		return (mensagem.charAt(2) == '1');
	}
	
	public Map<String, String> decodificaCodigoG (String mensagem) throws Exception {
		if (mensagem.length() != CODIGO_G_MESSAGE_SIZE) {
			throw new Exception("Tamanho do código G incompatível com o protocolo.");
		}
		Map<String, String> ans = new HashMap<String, String>();
		
		//TODO substrings erradas
		ans.put("N", mensagem.substring(2,   8));
		ans.put("G", mensagem.substring(8,  14));
		ans.put("X", mensagem.substring(14,  17));
		ans.put("Z", mensagem.substring(17, 18));
		ans.put("P", mensagem.substring(18));
		ans.put("Q", mensagem.substring(18));
		ans.put("F", mensagem.substring(18));
		// [paridade paridade paridade] no final da mensagem inteira --> tratar
		
		return ans;
	}
}
