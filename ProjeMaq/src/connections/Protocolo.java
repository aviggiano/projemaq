package connections;

import gui.IHM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Protocolo {

	public static final String TERMINADOR_DE_MENSAGEM = "%!";
	public static final int UPLINK_MESSAGE_SIZE = 19;
	public static final int EVENTUAL_MESSAGE_SIZE = 3;
	public static final int CODIGO_G_MESSAGE_SIZE = 27;
	public static String message_from_ARM = "";

	public Protocolo() {
		super();
	}

	//Função que gera o protocolo a ser enviado para o ARM quando o usuário clica nos botões da IHM
	public static String processReceiveCommand(int nbotao, int vel,
			boolean isPressed) {
		if (nbotao >= 4 && nbotao <= 7)
		// botões JOG
		{

			char[] palavra = new char[10];

			palavra[0] = ':'; // inicio de mensagem
			palavra[1] = '2'; // funcao do botao = funcao '2'
			palavra[2] = '0'; // nbotao
			palavra[3] = (char) (nbotao + 48); // nbotao

			palavra[7] = (isPressed) ? '1' : '0'; // ispressed
			palavra[8] = '%'; // fim de mensagem %
			palavra[9] = '!'; // fim de mensagem !
			if (vel < 10) { // velocidade velocidade velocidade
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
			
			return new String(palavra);
		}

		else if (nbotao == 8) { // botão Zero Peça X
			char[] palavra = new char[9];

			palavra[0] = ':'; // inicio de mensagem
			palavra[1] = '2'; // funcao do botao = funcao '2'
			palavra[2] = '0'; // nbotao
			palavra[3] = (char) (nbotao + 48); // nbotao

			palavra[7] = '%'; // fim de mensagem %
			palavra[8] = '!'; // fim de mensagem !
			if (vel < 10) { // velocidade velocidade velocidade
				palavra[4] = '0';
				palavra[5] = '0';
				palavra[6] = (char) (vel + 48);
			} else if (vel <= 99) {
				palavra[4] = '0';
				palavra[5] = (char) ((int) (vel / 10) + 48);
				palavra[6] = (char) ((vel % 10) + 48);
			} else {

				palavra[4] = (char) ((int) (vel / 100) + 48);
				palavra[5] = (char) ((int) ((vel % 100) / 10) + 48);
				palavra[6] = (char) ((vel % 10) + 48);

			}
			
			return new String(palavra);

		}

		else { // botões Play, Pause, Stop, ZeroZ
			char[] palavra = new char[6];

			palavra[0] = ':'; // inicio de mensagem
			palavra[1] = '2'; // funcao do botao = funcao '2'
			palavra[2] = '0'; // nbotao
			palavra[3] = (char) (nbotao + 48); // nbotao
			palavra[4] = '%'; // fim de mensagem %
			palavra[5] = '!';
			
			return new String(palavra);

		}

	}

	//Confere o tamanho das mensagens enviadas pelo ARM
	public Map<String, String> uplink(String mensagem) throws Exception {
		if (mensagem.length() != UPLINK_MESSAGE_SIZE) {
			throw new Exception(
					"Tamanho do uplink incompatível com o protocolo.");
		}
		Map<String, String> ans = new HashMap<String, String>();

		ans.put("X", mensagem.substring(2, 8));
		ans.put("Z", mensagem.substring(8, 14));
		ans.put("G", mensagem.substring(14, 17));
		ans.put("S", mensagem.substring(17, 18));
		ans.put("F", mensagem.substring(18));

		return ans;
	}

	//Confere o tamanho das mensagens enviadas pelo ARM
	public boolean eventuais(String mensagem) throws Exception {
		if (mensagem.length() != EVENTUAL_MESSAGE_SIZE) {
			throw new Exception(
					"Tamanho da mensagem eventual incompatÃ­vel com o protocolo.");
		}
		return (mensagem.charAt(2) == '1');
	}

	//Confere o tamanho das mensagens enviadas pelo ARM
	public Map<String, String> decodificaCodigoG(String mensagem)
			throws Exception {
		if (mensagem.length() != CODIGO_G_MESSAGE_SIZE) {
			throw new Exception(
					"Tamanho do código G incompatível com o protocolo.");
		}
		Map<String, String> ans = new HashMap<String, String>();

		
		ans.put("N", mensagem.substring(2, 8));
		ans.put("G", mensagem.substring(8, 14));
		ans.put("X", mensagem.substring(14, 17));
		ans.put("Z", mensagem.substring(17, 18));
		ans.put("P", mensagem.substring(18));
		ans.put("Q", mensagem.substring(18));
		ans.put("F", mensagem.substring(18));
		

		return ans;
	}

	//Função auxiliar que conta o número de ocorrÊncias de um char em uma String
	public static int countOccurrences(String haystack, char needle) {
		int count = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle) {
				count++;
			}
		}
		return count;
	}

	//Função auxiliar que conta o número de ocorrências de uma substring em uma string
	public static int countSubstring(String subStr, String str) {
		return (str.length() - str.replace(subStr, "").length())
				/ subStr.length();
	}

	//Função que traduz a mensagem enviada pelo ARM
	public static void traduz_uplink(String s) {

		message_from_ARM = message_from_ARM.concat(s);

		

		while (Protocolo.countOccurrences(message_from_ARM, ':') > 0
				&& Protocolo.countSubstring(TERMINADOR_DE_MENSAGEM,
						message_from_ARM) > 0) {
			

			String message = message_from_ARM.substring(
					message_from_ARM.indexOf(':'),
					message_from_ARM.indexOf(TERMINADOR_DE_MENSAGEM) + 2);

			if (Protocolo.countOccurrences(message, ':') > 1) {
				String temp = message_from_ARM.substring((message_from_ARM
						.indexOf(':') + 1));
				message_from_ARM = message_from_ARM
						.substring(temp.indexOf(':') + 1);
				
				

			} else {
				if (Protocolo.countOccurrences(message, '%') > 1) {

					
					message_from_ARM = message_from_ARM
							.substring(message_from_ARM.indexOf('%') + 1);
					if (Protocolo.countOccurrences(message_from_ARM, ':') == 0) {
						message_from_ARM = "";
					} else {
						message_from_ARM = message_from_ARM
								.substring(message_from_ARM.indexOf(':'));

					}

					System.out.println("Erro no recebimento da MSG (% sem !)"
							+ message_from_ARM);
				} else {
					if (Protocolo.countOccurrences(message, '!') > 1) {
						message_from_ARM = message_from_ARM
								.substring(message_from_ARM.indexOf('!') + 1);
						if (Protocolo.countOccurrences(message_from_ARM, ':') == 0) {
							message_from_ARM = "";
						} else {
							message_from_ARM = message_from_ARM
									.substring(message_from_ARM.indexOf(':'));

						}

						System.out
								.println("Erro no recebimento da MSG (! perdido)"
										+ message_from_ARM);
					} else {
						update_variables(message);

						message_from_ARM = message_from_ARM
								.substring(message_from_ARM
										.indexOf(TERMINADOR_DE_MENSAGEM) + 2);
					}
				}
			}

			
		}
	}

	//Depois de decodificada pela função traduz_uplink as variáveis são atualizadas pela função update_variables
	private static void update_variables(String msg) {
		
		double X;
		double Z;
		int linha_G;
		int fim_de_curso;
		int status;
		int okG;
		int okZPX;
		int okZPZ;
		
		if (msg.charAt(1) == '3' && msg.length() == 25) {
			
			X = Double.parseDouble(msg.substring(2, 10));
			
			Z = Double.parseDouble(msg.substring(10, 18));
			linha_G = Integer.parseInt(msg.substring(18, 21));
			status = Integer.parseInt(msg.substring(21, 22));
			fim_de_curso = Integer.parseInt(msg.substring(22, 23));
			IHM.atualizaXZ(X, Z);
			IHM.atualizaCodigoGEmExecucao(linha_G);

		} else {
			if (msg.charAt(1) == '4' && msg.charAt(2) == '1') {
				IHM.liberarBotoes();
				okG = Integer.parseInt(msg.substring(2, 3));

			

			} else {
				if (msg.charAt(1) == '5') {
					okZPX = Integer.parseInt(msg.substring(2, 3));
					
					IHM.atualizaZeroPecaX(okZPX);
				} else {
					if (msg.charAt(1) == '6') {
						okZPZ = Integer.parseInt(msg.substring(2, 3));
						
						IHM.atualizaZeroPecaZ(okZPZ);
					} else {
						if (msg.charAt(1) == '7') {
							fim_de_curso = Integer
									.parseInt(msg.substring(2, 3));
							
						} else {
							System.out.println("ERRO PROTOCOLO");
						}
					}
				}
			}
		}

	}
}
