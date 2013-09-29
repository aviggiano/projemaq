package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.AttributeSet;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import connections.ARM;

public class IHM extends JFrame implements ActionListener {
	
	public static final int INFO = 0;
	public static final int ERRO = 1;
	
    // variaveis
    private JFileChooser fileChooser;
    
    protected JScrollPane scrollPaneConsole;
    protected JScrollPane scrollPaneCodigoGEmExecucao;
    protected Toolkit toolkit = Toolkit.getDefaultToolkit();
    protected Dimension screenSize = toolkit.getScreenSize();
    protected Dimension frameSize;
    
	private JLabel labelVelocidade;
	private JTextField textFieldVelocidade;
	private JLabel labelUnidadeDaVelocidade;
	private JPanel panel1;
	private JButton buttonCarregarCodigoG;
	private JButton buttonEnviarCodigoG;
	private JButton buttonStop;
	private JButton buttonPlay;
	private JButton buttonPause;
	private JPanel panel2;
	private JButton buttonZerarX;
	private JButton buttonZerarZ;
	private JButton buttonXplus;
	private JButton buttonXless;
	private JButton buttonZplus;
	private JButton buttonZless;
	private JPanel panel3;
	private JScrollPane panel4;
	private JTextArea textAreaCodigoGEmExecucao;
	private JScrollPane panel5;
	private static JTextPane textPaneAvisosDoSistema;
	private JPanel panel6;
	private JButton buttonConsole;

	private JLabel labelAtencao;

	private Font font;

	private BufferedImage imageIconWarning;
	private ARM arm;
    
    /*
     * Constructor
     */   
    public IHM () {
    	//init external classes
    	initExternalClasses();
        //set title, size and location
        setTitleSizeAndLocation();
        //set look and feel
        setLookAndFeel("Windows"); 
        //pega o container do JFrame
        Container container = getContentPane();

        //criar as diferentes partes do container
        
        /**
         *  ________	 ___________   _____	
         * |		|	|			| |		|
         * |		|	|			| |		|
         * |	1	|	|	3		| |	4	|
         * |________|	|___________| |_____|
         *  ________	 ___________________
         * |		|	|					|
         * |	2	|	|		5			|
         * |________|	|___________________|
         * 
         *  ________________________________
         * |				6				|
         * |________________________________|
         */
        
        create1();
        create2();
        create3();
        create4();
        create5();
        create6();
        
        //junta as diferentes partes
        assemble(container);

    }
    
	private void create6() {
		
		buttonConsole = new JButton("Console", new ImageIcon(getClass().getResource("../img/terminal.png")));
		
		try {
			imageIconWarning = ImageIO.read(this.getClass().getResource("../img/warning.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelAtencao = new JLabel("Atenção: máquina ligada!");
		
		panel6 = new JPanel();
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		
		
		panel6.add(new JLabel("  "));
		panel6.add(new JLabel(new ImageIcon(imageIconWarning)));
		panel6.add(new JLabel("  "));
		panel6.add(labelAtencao);
		
		panel6.add(Box.createHorizontalGlue());
		
		panel6.add(buttonConsole);
	}

	private void create5() {
		textPaneAvisosDoSistema = new JTextPane();
		textPaneAvisosDoSistema.setText("IHM incializada com sucesso.");
		textPaneAvisosDoSistema.setPreferredSize(new Dimension (750, 150));
		
        font = new Font("Arial", Font.PLAIN, 12);
        textPaneAvisosDoSistema.setFont(font);
        textPaneAvisosDoSistema.setEditable(false);
        
        scrollPaneConsole = new JScrollPane(textPaneAvisosDoSistema);
        scrollPaneConsole.setBorder(new TitledBorder("Avisos do sistema"));
		
		panel5 = scrollPaneConsole;
		
	}

	private void create4() {
		textAreaCodigoGEmExecucao = new JTextArea("", 
				19, 30);
        font = new Font("Arial", Font.PLAIN, 12);
        textAreaCodigoGEmExecucao.setFont(font);
        textAreaCodigoGEmExecucao.setEditable(false);
        scrollPaneCodigoGEmExecucao = new JScrollPane(textAreaCodigoGEmExecucao);
        scrollPaneCodigoGEmExecucao.setBorder(new TitledBorder("Código G em execução"));

		panel4 = scrollPaneCodigoGEmExecucao;
		
	}

	private void create3() {
		// grafico XZ
		XZPlot xzPlot = new XZPlot("XZ Series Demo");
        xzPlot.setVisible(true);

        panel3 = new JPanel();
        panel3.add(xzPlot);
        
	}

	private void create2() {

        // Velocidade
        labelVelocidade = new JLabel("Velocidade:");
        textFieldVelocidade = new JTextField("50  ");
        ((AbstractDocument) textFieldVelocidade.getDocument()).setDocumentFilter(new DocumentFilter(){
        	@Override
        	public void insertString(FilterBypass fb, int off
        	                    , String str, AttributeSet attr) 
        	                            throws BadLocationException 
        	{
        	    // remove non-digits
        	    fb.insertString(off, str.replaceAll("\\D++", ""), attr);
        	} 
        	@Override
        	public void replace(FilterBypass fb, int off
        	        , int len, String str, AttributeSet attr) 
        	                        throws BadLocationException 
        	{
        	    // remove non-digits
        	    fb.replace(off, len, str.replaceAll("\\D++", ""), attr);
        	}
        });
        textFieldVelocidade.addActionListener(this); // interpreta o <enter> do usuario
        // interpreta o <tab>
        textFieldVelocidade.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
                JTextField tField = (JTextField) input;
                return (Integer.parseInt(tField.getText().trim()) >=0 && Integer.parseInt(tField.getText().trim()) <= 100);  
			}
            });  
        // interpreta o <tab>
        textFieldVelocidade.addFocusListener(new FocusListener() {
        	@Override
            public void focusGained(FocusEvent e) {
              // nao faz nada
            }

        	@Override
            public void focusLost(FocusEvent e) {
              atualizaVelocidadeJog();
            }
          });

        labelUnidadeDaVelocidade = new JLabel("% Vmax");
		
		// botao Setar Zero Peça Eixo X
		buttonZerarX = new JButton("Zerar eixo X");
		buttonZerarX.addActionListener(this);
		buttonZerarX.setEnabled(true);
        
		// botao Setar Zero Peça Eixo Z
		buttonZerarZ = new JButton("Zerar eixo Z");
		buttonZerarZ.addActionListener(this);
		buttonZerarZ.setEnabled(true);
        
		// botao X+
		buttonXplus = new JButton ("X+", new ImageIcon(getClass().getResource("../img/next.png")));
		buttonXplus.addActionListener(this);
		buttonXplus.setEnabled(true);

		// botao X-
		buttonXless = new JButton ("X-", new ImageIcon(getClass().getResource("../img/previous.png")));
		buttonXless.addActionListener(this);
		buttonXless.setEnabled(true);

		// botao Z+
		buttonZplus = new JButton ("Z+ ", new ImageIcon(getClass().getResource("../img/up.png")));
		buttonZplus.addActionListener(this);
		buttonZplus.setEnabled(true);

		// botao Z-
		buttonZless = new JButton ("Z-  ", new ImageIcon(getClass().getResource("../img/down.png")));
		buttonZless.addActionListener(this);
		buttonZless.setEnabled(true);
				
		buttonsJog();
		
		JPanel panelZerar = new JPanel();
		panelZerar.setLayout(new GridLayout(1,2));
		panelZerar.add(buttonZerarX);
		panelZerar.add(buttonZerarZ); 
		
		JPanel panelSetinhas = new JPanel();
		JPanel panelVertical = new JPanel();
		panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.Y_AXIS));
		panelVertical.add(buttonZplus);
		panelVertical.add(buttonZless);
		panelSetinhas.add(buttonXless, BorderLayout.WEST);
		panelSetinhas.add(panelVertical, BorderLayout.CENTER);
		panelSetinhas.add(buttonXplus, BorderLayout.EAST);
		
		JPanel panelVelocidade = new JPanel();
		panelVelocidade.add(labelVelocidade);
        panelVelocidade.add(textFieldVelocidade);
        panelVelocidade.add(labelUnidadeDaVelocidade);
        
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(panelVelocidade, BorderLayout.NORTH);
		panel2.add(panelZerar);
		panel2.add(new JLabel(" "));
		panel2.add(panelSetinhas);
		
	}

	private void create1() {
        
        // Carregar código G
        buttonCarregarCodigoG = new JButton("Carregar código G", new ImageIcon(getClass().getResource("../img/filesave.png")));
        buttonCarregarCodigoG.addActionListener(this);
        buttonCarregarCodigoG.setEnabled(true);
        
        // Enviar código G
        buttonEnviarCodigoG = new JButton("    Enviar código G", new ImageIcon(getClass().getResource("../img/extract-archive.png")));
        buttonEnviarCodigoG.addActionListener(this);
        buttonEnviarCodigoG.setEnabled(true);  
        
        // Stop
        buttonStop = new JButton("", new ImageIcon(getClass().getResource("../img/player_stop.png")));
        buttonStop.addActionListener(this);
        buttonStop.setEnabled(true);
        
        // Play
        buttonPlay = new JButton("", new ImageIcon(getClass().getResource("../img/player_play.png")));
        buttonPlay.addActionListener(this);
        buttonPlay.setEnabled(true);
        
        // Pause
        buttonPause = new JButton("", new ImageIcon(getClass().getResource("../img/player_pause.png")));
        buttonPause.addActionListener(this);
        buttonPause.setEnabled(true);


        JPanel panelBotoesDeControle = new JPanel();
        panelBotoesDeControle.setLayout(new GridLayout(1,3));
        panelBotoesDeControle.add(buttonStop);
        panelBotoesDeControle.add(buttonPlay);
        panelBotoesDeControle.add(buttonPause);
        
        panel1 = new JPanel();
        //panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel1.setLayout(new GridLayout(6,0));
        
        panel1.add(buttonCarregarCodigoG);
        panel1.add(buttonEnviarCodigoG);
        panel1.add(panelBotoesDeControle);
        
	}
	
	private void assemble(Container container) {
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		
		west.add(panel1);
		west.add(Box.createVerticalGlue());
		west.add(panel2);
		west.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		JPanel centercenter = panel3;//new JPanel();
		panel3.add(panel4);
		
		JPanel centersouth = new JPanel();
		centersouth.add(panel5, BorderLayout.CENTER);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.add(centercenter, BorderLayout.CENTER);
		center.add(centersouth, BorderLayout.SOUTH);
		
		center.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		JPanel south = panel6;
		
		south.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		container.add(west, BorderLayout.WEST);
		container.add(center, BorderLayout.CENTER);
		container.add(south, BorderLayout.SOUTH);
		
		this.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == buttonCarregarCodigoG) {
			carregarArquivo();
		}
		else if (actionEvent.getSource() == buttonEnviarCodigoG) {
			
		}
		else if (actionEvent.getSource() == buttonStop) {
			arm.writeRegister(3,0);
		}
		else if (actionEvent.getSource() == buttonPlay) {
			arm.writeRegister(1,0);
		} 
		else if (actionEvent.getSource() == buttonPause) {
			arm.writeRegister(2,0);
		}
		else if (actionEvent.getSource() == textFieldVelocidade) {
			atualizaVelocidadeJog();
		}
		else if (actionEvent.getSource() == buttonZerarX) {
			arm.writeRegister(8,0);
		}
		else if (actionEvent.getSource() == buttonZerarZ) {
			arm.writeRegister(9,0);
		}
		else if (actionEvent.getSource() == buttonXless) {
			System.out.println("X-");
		}
		else if (actionEvent.getSource() == buttonXplus) {
			System.out.println("X+");
		}
		else if (actionEvent.getSource() == buttonZless) {
			System.out.println("Z-");
		}
		else if (actionEvent.getSource() == buttonZplus) {
			System.out.println("Z+");
		}
		else if (actionEvent.getSource() == buttonConsole) {
			
		}		
		
	} 
	//Funcao que captura o evento de segurar o clique de X+, X-, Z+, Z-
	private void buttonsJog() {
		int timerDelay = 100;
		//Botao X+
		final Timer timer_Xplus = new Timer(timerDelay , new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button X + Pressed!");
				arm.writeRegister(4, arm.getVelocidadeJog());
			}
	    });
		
		final ButtonModel bModel_Xplus = buttonXplus.getModel();
		bModel_Xplus.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent cEvt) {
				if (bModel_Xplus.isPressed() && !timer_Xplus.isRunning()) {
					timer_Xplus.start();
				} else if (!bModel_Xplus.isPressed() && timer_Xplus.isRunning()) {
					timer_Xplus.stop();
				}
	        }
		});
		
		//Botao X-
		final Timer timer_Xless = new Timer(timerDelay , new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button X - Pressed!");
				arm.writeRegister(6, arm.getVelocidadeJog());
			}
	    });
		
		final ButtonModel bModel_Xless = buttonXless.getModel();
		bModel_Xless.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent cEvt) {
				if (bModel_Xless.isPressed() && !timer_Xless.isRunning()) {
					timer_Xless.start();
				} else if (!bModel_Xless.isPressed() && timer_Xless.isRunning()) {
					timer_Xless.stop();
				}
	        }
		});
		
		//Botao Z+
		final Timer timer_Zplus = new Timer(timerDelay , new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Z + Pressed!");
				arm.writeRegister(5, arm.getVelocidadeJog());
			}
	    });
		
		final ButtonModel bModel_Zplus = buttonZplus.getModel();
		bModel_Zplus.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent cEvt) {
				if (bModel_Zplus.isPressed() && !timer_Zplus.isRunning()) {
					timer_Zplus.start();
				} else if (!bModel_Zplus.isPressed() && timer_Zplus.isRunning()) {
					timer_Zplus.stop();
				}
	        }
		});
		
		//Botao Z-
		final Timer timer_Zless = new Timer(timerDelay , new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Z- Pressed!");
				arm.writeRegister(7, arm.getVelocidadeJog());
			}
	    });
		
		final ButtonModel bModel_Zless = buttonZless.getModel();
		bModel_Zless.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent cEvt) {
				if (bModel_Zless.isPressed() && !timer_Zless.isRunning()) {
					timer_Zless.start();
				} else if (!bModel_Zless.isPressed() && timer_Zless.isRunning()) {
					timer_Zless.stop();
				}
	        }
		});
	}
	
    private void atualizaVelocidadeJog() {
    	int vel = ( textFieldVelocidade.getText().isEmpty()) ? 
    				0
    			:
    				Integer.parseInt(textFieldVelocidade.getText().trim());
    	if (vel > 100) {
    		textFieldVelocidade.setText("0");
    		vel = 0;
    	}
		arm.setVelocidadeJog(vel);
		append("Velocidade da movimentação manual: " + arm.getVelocidadeJog(), INFO);
		textFieldVelocidade.setText(String.valueOf(arm.getVelocidadeJog()));
	}

	private void setLookAndFeel(String nomeDoLookAndFeel) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (nomeDoLookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            //mainPane.append(e.toString());
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void setTitleSizeAndLocation() {
        this.setTitle("PMR2450 - Torno CNC");
        frameSize = new Dimension ((int)(screenSize.width*0.8), (int)(screenSize.height*0.80));
        this.setSize(frameSize);
        
        //this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
         
        setLocation(0,0); // centralizar?
        
        // exit on close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    private void initExternalClasses() {
    	arm = new ARM();
    }

    public static void append(ArrayList<String> textArray) {
    	for (String i : textArray) {
    		append(i); 
    	}
    }
    
    public static void append(Object text) {
    	try {
			textPaneAvisosDoSistema.getStyledDocument().insertString(
					textPaneAvisosDoSistema.getStyledDocument().getLength(), "\n" + text, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    	
    }
    
    public static void append(String text, final int tipo) {
    	Style style = textPaneAvisosDoSistema.addStyle("style", null);
        
    	if (tipo == INFO) { 
    		StyleConstants.setForeground(style, Color.BLUE);
    	}
    	else if (tipo == ERRO) {
    		StyleConstants.setForeground(style, Color.RED);
    	}
    	try {
			textPaneAvisosDoSistema.getStyledDocument().insertString(
					textPaneAvisosDoSistema.getStyledDocument().getLength(), "\n" + text, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    }
    
    public ArrayList<String> readData(File f) {
		ArrayList<String> lines = new ArrayList<>();
		try {
			RandomAccessFile fichier = new java.io.RandomAccessFile(f, "r");

			String line = "";
			while ((line = fichier.readLine()) != null) // tant que la ligne n'est pas nulle
				lines.add(line);
			
			return lines;
		}

		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    private void carregarArquivo () {
        int returnValue = 0;
        try{
            fileChooser = new JFileChooser("."); // ele abre no current directory
            returnValue = fileChooser.showOpenDialog(this);
            
            if (returnValue == JFileChooser.APPROVE_OPTION) { 
                File file = fileChooser.getSelectedFile();
                ArrayList<String> linhasDoArquivo = readData(file);
                
                append("\nArquivo selecionado com sucesso.", INFO);
                append(linhasDoArquivo);
            }
            else {
                append("\nProblema na seleção do arquivo.", ERRO); 
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
}