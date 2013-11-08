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
    public static String message_from_ARM="";
	
	public Protocolo() {
		super();
	}

	// funcao processReceiveCommand que cria os comandos no protocolo definido para
	// enviar para o ARM
	// Protocolo:
	/**
	 * Botao Protocolo
	 * 
	 */
	//public static String processReceiveCommand(int nbotao, int vel, boolean isPressed) {
	
	
	
	public static String processReceiveCommand(int nbotao, int vel, boolean isPressed) {
		if(nbotao>=4 && nbotao<=7 )
			// botões JOG
		{
		
		char[] palavra = new char[10];
		
		palavra[0] = ':';						//inicio de mensagem
		palavra[1] = '2';						//funcao do botao = funcao '2'
		palavra[2] = '0';						//nbotao
		palavra[3] = (char) (nbotao + 48);		//nbotao
		
		palavra[7] = (isPressed)? '1' : '0';	//ispressed
		palavra[8] = '%';						//fim de mensagem %
		palavra[9] = '!';						//fim de mensagem !
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
		
		
		else if (nbotao==8)
		{ //botão zpeçaX
			char[] palavra = new char[9];
			
			palavra[0] = ':';						//inicio de mensagem
			palavra[1] = '2';						//funcao do botao = funcao '2'
			palavra[2] = '0';						//nbotao
			palavra[3] = (char) (nbotao + 48);		//nbotao
			
			
			palavra[7] = '%';						//fim de mensagem %
			palavra[8] = '!';						//fim de mensagem !
			if (vel < 10) {							//velocidade velocidade velocidade
				palavra[4] = '0';
				palavra[5] = '0';
				palavra[6] = (char) (vel + 48);
			} else if (vel <= 99) {
				palavra[4] = '0';
				palavra[5] = (char) ((int) (vel / 10) + 48);
				palavra[6] = (char) ((vel % 10) + 48);
			} else {
				
				palavra[4] = (char) ((int) (vel / 100) + 48);
				palavra[5] = (char) ( (int) ((vel%100)/10)+48);
				palavra[6] = (char) ((vel % 10) + 48);
				
			}
			System.out.println(palavra);
			return  new String(palavra);
			
		}
			
			else
		{ //botões Play, Pause, Stop, ZeroZ
			char[] palavra = new char[6];
			
			palavra[0] = ':';						//inicio de mensagem
			palavra[1] = '2';						//funcao do botao = funcao '2'
			palavra[2] = '0';						//nbotao
			palavra[3] = (char) (nbotao + 48);		//nbotao
			palavra[4] = '%';						//fim de mensagem %
			palavra[5] = '!';
			System.out.println(palavra);
			return  new String(palavra);
			
		}
	
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
	
	char calculateLRC(char[] frame, int start, int end) {
		char accum;
		char ff;
		char um;
		int i;
		accum = 0;
		ff = (char) 0xff;
		um = (char) 0x01;

		for (i = start; i < end; i++) {
			accum = (char) ((int) (accum + frame[i]) % ff);
		}
		accum = (char) (ff - accum);
		accum = (char) (accum + um);
		return accum;
	}
	
	public static int countOccurrences(String haystack, char needle)
	{
	    int count = 0;
	    for (int i=0; i < haystack.length(); i++)
	    {
	        if (haystack.charAt(i) == needle)
	        {
	             count++;
	        }
	    }
	    return count;
	}
	
	public static void traduz_uplink(String s) {
		
		message_from_ARM=message_from_ARM.concat(s);
		
		
		while(Protocolo.countOccurrences(message_from_ARM, ':')>1)
		{
			
			String stemp=message_from_ARM.substring((message_from_ARM.indexOf(':')+1));
			
			String message=message_from_ARM.substring(message_from_ARM.indexOf(':'),stemp.indexOf(':')+1);
			if (message.length()==19 || message.length()==3)
			{
				update_variables(message);
			}
			else
			{
				System.out.println("Erro no recebimento da MSG");
			}
			
			
			message_from_ARM=message_from_ARM.substring(stemp.indexOf(':')+1);
		
		}
	}

	private static void update_variables(String msg) {
		//System.out.println(msg);
		double X;
		double Z;
		int linha_G;
		int fim_de_curso;
		int status;
		int okG;
		int okZPX;
		int okZPZ;
		if(msg.charAt(1)=='3')
		{
			
				X= (double) Integer.parseInt(msg.substring(2,8))/100;
				Z= (double) Integer.parseInt(msg.substring(8,14))/100;
				linha_G= Integer.parseInt(msg.substring(14,17));
				status= Integer.parseInt(msg.substring(17,18));
				fim_de_curso= Integer.parseInt(msg.substring(18,19));
				IHM.atualizaXZ(X, Z);
				System.out.println(X);
				System.out.println(Z);
				System.out.println(linha_G);
				IHM.atualizaCodigoGEmExecucao(linha_G);
				System.out.println(status);
				System.out.println(fim_de_curso);
		}
		else
		{
		  if(msg.charAt(1)=='4'&&msg.charAt(2)=='1')
		  {
			  IHM.liberarBotoes();
			  okG=Integer.parseInt(msg.substring(2,3));
			  
			  System.out.println(okG);
			
		  }
		  else
		  {
			  if(msg.charAt(1)=='5')
			  {
				  okZPX=Integer.parseInt(msg.substring(2,3));
				  System.out.println(okZPX);
				  IHM.atualizaZeroPecaX(okZPX);
			  }
			  else
			  {
				  if(msg.charAt(1)=='6')
				  {
					  okZPZ=Integer.parseInt(msg.substring(2,3));
					  System.out.println(okZPZ);
					  IHM.atualizaZeroPecaZ(okZPZ);
				  }
				  else
				  {
					  if(msg.charAt(1)=='7')
					  {
						  fim_de_curso=Integer.parseInt(msg.substring(2,3));
						  System.out.println(fim_de_curso);
					  }
					  else
					  {
						  System.out.println("ERRO PROTOCOLO");
					  }
				  }
			  }
		  }
		}
		
	}
}
