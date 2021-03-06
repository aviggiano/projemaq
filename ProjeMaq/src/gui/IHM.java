package gui;

import antlrp.*;
import gnu.io.CommPortIdentifier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
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

import org.jfree.util.StringUtils;

import com.sun.corba.se.spi.extension.ZeroPortPolicy;

import connections.ARM;
import connections.Protocolo;

public class IHM extends JFrame implements ActionListener, MouseListener {

	public static final int INFO = 0;
	public static final int ERRO = 1;

	public static enum parametros {
		VELOCIDADE(0), DIAMETRO(1), X(2), Z(3), BLANK(4), ZERO_PECA_X(5), ZERO_PECA_Z(
				6);
		int index;

		parametros(int index) {
			this.index = index;
		}
	};

	// variaveis
	private JFileChooser fileChooser;

	protected static JScrollPane scrollPaneConsole;
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
	private static JButton buttonStop;
	private static JButton buttonPlay;
	private static JButton buttonPause;
	private JPanel panel2;
	private JButton buttonZerarX;
	private JButton buttonZerarZ;
	private JButton buttonXplus;
	private JButton buttonXless;
	private JButton buttonZplus;
	private JButton buttonZless;
	private JPanel panel3;
	private JScrollPane panel4;
	private JScrollPane panel5;
	private static JTextPane textPaneAvisosDoSistema;
	private JScrollPane panel6;
	private JPanel panel7;
	private JButton buttonConsole;

	private JLabel labelAtencao;

	private Font font = new Font("Arial", Font.PLAIN, 36);;

	private BufferedImage imageIconWarning;
	private ARM arm;
	private ArrayList<String> linhasDoArquivo;
	private JLabel labelDiametroDaPeca;
	private JTextField textFieldDiametroDaPeca;
	private JLabel labelUnidadeDoDiametro;
	private static JList listCodigoGEmExecucao;
	private JScrollPane listScrollerCodigoG;
	private static DefaultListModel listModelParametros;
	private JList<String> listParametros;
	private JScrollPane listScrollerParametros;
	private JButton buttonARM;
	private static XZPlot xzPlot;
	private static boolean zeroPecaX = false;
	private static boolean zeroPecaZ = false;
	private DefaultListModel listModelCodigoGEmExecucao;
	static final int VEL_MIN = 0;
	static final int VEL_MAX = 100;
	static final int VEL_INIT = 50;

	/*
	 * Constructor
	 */
	public IHM() {
		initExternalClasses();

		// set title, size and location
		setTitleSizeAndLocation();
		// set look and feel
		setLookAndFeel("Windows");
		// pega o container do JFrame
		Container container = getContentPane();

		// criar as diferentes partes do container
		create1();
		create2();
		create3();
		create4();
		create5();
		create6();
		create7();

		// junta as diferentes partes
		assemble(container);
	}

	private void create7() {

		buttonConsole = new JButton("Console", new ImageIcon(getClass()
				.getResource("../img/terminal.png")));
		buttonConsole.addActionListener(this);
		buttonConsole.setEnabled(true);

		try {
			imageIconWarning = ImageIO.read(this.getClass().getResource(
					"../img/warning.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelAtencao = new JLabel("Atenção: máquina ligada!");

		panel7 = new JPanel();
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));

		panel7.add(new JLabel("  "));
		panel7.add(new JLabel(new ImageIcon(imageIconWarning)));
		panel7.add(new JLabel("  "));
		panel7.add(labelAtencao);

		panel7.add(Box.createHorizontalGlue());

		panel7.add(buttonConsole);
	}

	private void create6() {
		String[] parametros = { " ", "Diâmetro da peça:", "X:", "Z:", " ",
				"Zero peça X não definido", "Zero peça Z não definido" };
		listModelParametros = new DefaultListModel();
		for (String p : parametros) {
			listModelParametros.addElement(p);
		}

		listParametros = new JList<String>(listModelParametros);

		listParametros.setFont(listParametros.getFont().deriveFont(22.0f));

		listParametros.setLayoutOrientation(JList.VERTICAL);
		listParametros.setVisibleRowCount(-1);
		listScrollerParametros = new JScrollPane(listParametros);
		listScrollerParametros.setPreferredSize(new Dimension(400, 300));
		listScrollerParametros.setBorder(new TitledBorder("Parâmetros"));

		panel6 = listScrollerParametros;
	}

	private void create5() {
		textPaneAvisosDoSistema = new JTextPane();
		textPaneAvisosDoSistema.setText("IHM incializada com sucesso.");
		textPaneAvisosDoSistema.setPreferredSize(new Dimension(800, 500));

		font = new Font("Arial", Font.PLAIN, 24);
		textPaneAvisosDoSistema.setFont(font);
		textPaneAvisosDoSistema.setEditable(false);

		scrollPaneConsole = new JScrollPane(textPaneAvisosDoSistema);
		scrollPaneConsole.setBorder(new TitledBorder("Avisos do sistema"));

		panel5 = scrollPaneConsole;

	}

	private void create4() {
		listModelCodigoGEmExecucao = new DefaultListModel();

		listCodigoGEmExecucao = new JList<String>(listModelCodigoGEmExecucao);
		listCodigoGEmExecucao.setLayoutOrientation(JList.VERTICAL);
		listCodigoGEmExecucao.setVisibleRowCount(-1); // ?

		listCodigoGEmExecucao.setFont(listCodigoGEmExecucao.getFont()
				.deriveFont(22.0f));

		listScrollerCodigoG = new JScrollPane(listCodigoGEmExecucao);
		listScrollerCodigoG.setPreferredSize(new Dimension(400, 300));
		listScrollerCodigoG.setBorder(new TitledBorder("Código G em execução"));
		panel4 = listScrollerCodigoG; // scrollPaneCodigoGEmExecucao;
	}

	private void create3() {
		// grafico XZ
		xzPlot = new XZPlot("XZ Series Demo");
		xzPlot.setVisible(true);

		panel3 = new JPanel();
		// panel3.add(xzPlot);
	}

	private void create2() {
		// Diametro
		labelDiametroDaPeca = new JLabel("Diâmetro da Peça:");
		labelDiametroDaPeca.setFont(new Font("Arial", 1, 20));
		textFieldDiametroDaPeca = new JTextField("            ");
		((AbstractDocument) textFieldDiametroDaPeca.getDocument())
				.setDocumentFilter(new DocumentFilter() {
					@Override
					public void insertString(FilterBypass fb, int off,
							String str, AttributeSet attr)
							throws BadLocationException {
						// remove non-digits
						fb.insertString(off, str.replaceAll("([^0-9.]?)", ""),
								attr);
					}

					@Override
					public void replace(FilterBypass fb, int off, int len,
							String str, AttributeSet attr)
							throws BadLocationException {
						// remove non-digits
						fb.replace(off, len, str.replaceAll("([^0-9.]?)", ""),
								attr);
					}
				});
		textFieldDiametroDaPeca.setFont(new Font("Arial", 1, 20));
		textFieldDiametroDaPeca.addActionListener(this); // interpreta o <enter>
															// do usuario
		// interpreta o <tab>
		textFieldDiametroDaPeca.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField tField = (JTextField) input;
				return (Double.parseDouble(tField.getText().trim()) >= 0 && Double
						.parseDouble(tField.getText().trim()) <= 100);
			}
		});
		// interpreta o <tab>
		textFieldDiametroDaPeca.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// nao faz nada
			}

			@Override
			public void focusLost(FocusEvent e) {
				atualizaDiametroDaPeca();
			}
		});
		labelUnidadeDoDiametro = new JLabel("mm");
		labelUnidadeDoDiametro.setFont(new Font("Arial", 1, 20));

		// Vel Slider
		labelVelocidade = new JLabel("Velocidade:");
		labelVelocidade.setFont(new Font("Arial", 1, 20));

		final JSlider velocidadeJog = new JSlider(JSlider.HORIZONTAL, VEL_MIN,
				VEL_MAX, VEL_INIT);
		// velocidadeJog.addChangeListener(this);

		// Turn on labels at major tick marks.
		velocidadeJog.setMajorTickSpacing(10);
		velocidadeJog.setMinorTickSpacing(1);
		velocidadeJog.setPaintTicks(true);
		velocidadeJog.setPaintLabels(true);
		labelUnidadeDaVelocidade = new JLabel("% Vmax");
		labelUnidadeDaVelocidade.setFont(new Font("Arial", 1, 20));

		velocidadeJog.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					arm.setVelocidadeJog(velocidadeJog.getValue());
					append("Velocidade da movimentação manual: "
							+ arm.getVelocidadeJog() + " % Vmax.", INFO);

				}
			}
		});

		// botao Setar Zero Peça Eixo X
		buttonZerarX = new JButton("Zerar eixo X");
		buttonZerarX.addActionListener(this);
		buttonZerarX.setEnabled(true);
		buttonZerarX.setFont(new Font("Arial", 1, 20));

		// botao Setar Zero Peça Eixo Z
		buttonZerarZ = new JButton("Zerar eixo Z");
		buttonZerarZ.addActionListener(this);
		buttonZerarZ.setEnabled(true);
		buttonZerarZ.setFont(new Font("Arial", 1, 20));

		// botao X+
		buttonXplus = new JButton("X+", new ImageIcon(getClass().getResource(
				"../img/next.png")));
		buttonXplus.addActionListener(this);
		buttonXplus.addMouseListener(this);
		buttonXplus.setEnabled(true);
		buttonXplus.setFont(new Font("Arial", 1, 20));

		// botao X-
		buttonXless = new JButton("X-", new ImageIcon(getClass().getResource(
				"../img/previous.png")));
		buttonXless.addActionListener(this);
		buttonXless.addMouseListener(this);
		buttonXless.setEnabled(true);
		buttonXless.setFont(new Font("Arial", 1, 20));

		// botao Z+
		buttonZplus = new JButton("Z+ ", new ImageIcon(getClass().getResource(
				"../img/up.png")));
		buttonZplus.addActionListener(this);
		buttonZplus.addMouseListener(this);
		buttonZplus.setEnabled(true);
		buttonZplus.setFont(new Font("Arial", 1, 20));

		// botao Z-
		buttonZless = new JButton("Z-  ", new ImageIcon(getClass().getResource(
				"../img/down.png")));
		buttonZless.addActionListener(this);
		buttonZless.addMouseListener(this);
		buttonZless.setEnabled(true);
		buttonZless.setFont(new Font("Arial", 1, 20));

		buttonsJog();

		JPanel panelZerar = new JPanel();
		panelZerar.setLayout(new GridLayout(1, 2));
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

		JPanel panelDiametro = new JPanel();
		panelDiametro.add(labelDiametroDaPeca);
		panelDiametro.add(textFieldDiametroDaPeca);
		panelDiametro.add(labelUnidadeDoDiametro);

		JPanel panelVelocidade = new JPanel();
		panelVelocidade.add(labelVelocidade);
		panelVelocidade.add(velocidadeJog);
		panelVelocidade.add(labelUnidadeDaVelocidade);

		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(panelDiametro);
		panel2.add(panelVelocidade);
		panel2.add(panelZerar);
		panel2.add(new JLabel(" "));
		panel2.add(panelSetinhas);
	}

	private void create1() {
		// Conectar ARM
		buttonARM = new JButton("     Conectar ARM", new ImageIcon(getClass()
				.getResource("../img/preferences-system.png")));
		buttonARM.addActionListener(this);
		buttonARM.setEnabled(true);
		buttonARM.setFont(new Font("Arial", 1, 20));

		// Carregar código G
		buttonCarregarCodigoG = new JButton("Carregar código G", new ImageIcon(
				getClass().getResource("../img/filesave.png")));
		buttonCarregarCodigoG.addActionListener(this);
		buttonCarregarCodigoG.setEnabled(true);
		buttonCarregarCodigoG.setFont(new Font("Arial", 1, 20));

		// Enviar código G
		buttonEnviarCodigoG = new JButton("    Enviar código G", new ImageIcon(
				getClass().getResource("../img/extract-archive.png")));
		buttonEnviarCodigoG.addActionListener(this);
		buttonEnviarCodigoG.setEnabled(false);
		buttonEnviarCodigoG.setFont(new Font("Arial", 1, 20));

		// Stop
		buttonStop = new JButton("", new ImageIcon(getClass().getResource(
				"../img/player_stop.png")));
		buttonStop.addActionListener(this);
		buttonStop.setEnabled(false);
		buttonStop.setFont(new Font("Arial", 1, 20));

		// Play
		buttonPlay = new JButton("", new ImageIcon(getClass().getResource(
				"../img/player_play.png")));
		buttonPlay.addActionListener(this);
		buttonPlay.setEnabled(false);
		buttonPlay.setFont(new Font("Arial", 1, 20));

		// Pause
		buttonPause = new JButton("", new ImageIcon(getClass().getResource(
				"../img/player_pause.png")));
		buttonPause.addActionListener(this);
		buttonPause.setEnabled(false);
		buttonPause.setFont(new Font("Arial", 1, 20));

		JPanel panelBotoesDeControle = new JPanel();
		panelBotoesDeControle.setLayout(new GridLayout(1, 3));
		panelBotoesDeControle.add(buttonStop);
		panelBotoesDeControle.add(buttonPlay);
		panelBotoesDeControle.add(buttonPause);

		panel1 = new JPanel();
		// panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel1.setLayout(new GridLayout(6, 0));

		panel1.add(buttonARM);
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

		JPanel centercenter = new JPanel();
		centercenter.add(panel6);
		centercenter.add(panel4);

		JPanel centersouth = new JPanel();
		centersouth.add(panel5, BorderLayout.CENTER);
		// centersouth.add(panel6, BorderLayout.WEST);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.add(centercenter, BorderLayout.CENTER);
		center.add(centersouth, BorderLayout.SOUTH);

		center.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		JPanel south = panel7;

		south.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		container.add(west, BorderLayout.WEST);
		container.add(center, BorderLayout.CENTER);
		// container.add(south, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == buttonARM) {
			// if (!ARM.IS_CONNECTED)
			conexaoARM();
			// else
			// deconexaoARM();
		} else if (actionEvent.getSource() == buttonCarregarCodigoG) {
			carregarArquivo();
		} else if (actionEvent.getSource() == buttonEnviarCodigoG) {
			enviarArquivo();
			// Como a IHM sabe se o ARM recebeu o código? ===== feito na fct
			// libera botoes
			// buttonPlay.setEnabled(true);
			// buttonStop.setEnabled(true);
			// buttonPause.setEnabled(true);
		} else if (actionEvent.getSource() == buttonStop) {
			append("STOP", INFO);
			arm.processReceiveCommand(3, 0);
			buttonZless.setEnabled(true);
			buttonXless.setEnabled(true);
			buttonZplus.setEnabled(true);
			buttonXplus.setEnabled(true);
			buttonEnviarCodigoG.setEnabled(true);
			buttonCarregarCodigoG.setEnabled(true);
		} else if (actionEvent.getSource() == buttonPlay) {
			if (!zeroPecaSetado() && !desejaDarPlay()) {
				return;
			}
			append("PLAY", INFO);
			arm.processReceiveCommand(1, 0);
			buttonZless.setEnabled(false);
			buttonXless.setEnabled(false);
			buttonZplus.setEnabled(false);
			buttonXplus.setEnabled(false);
			buttonEnviarCodigoG.setEnabled(false);
			buttonCarregarCodigoG.setEnabled(false);
		} else if (actionEvent.getSource() == buttonPause) {
			append("PAUSE", INFO);
			arm.processReceiveCommand(2, 0);
		} else if (actionEvent.getSource() == textFieldVelocidade) {
			atualizaVelocidadeJog();
		} else if (actionEvent.getSource() == textFieldDiametroDaPeca) {
			atualizaDiametroDaPeca();
		} else if (actionEvent.getSource() == buttonZerarX) {
			append("Comando enviado: Zerar eixo X.", INFO);
			arm.processReceiveCommand(8, (int) (10 * arm.getDiametroDaPeca()));
		} else if (actionEvent.getSource() == buttonZerarZ) {
			append("Comando enviado: Zerar eixo Z.", INFO);
			arm.processReceiveCommand(9, 0);
		} else if (actionEvent.getSource() == buttonXless) {
			System.out.println("X-");
		} else if (actionEvent.getSource() == buttonXplus) {
			// System.out.println("X+");
		} else if (actionEvent.getSource() == buttonZless) {
			System.out.println("Z-");
		} else if (actionEvent.getSource() == buttonZplus) {
			System.out.println("Z+");
		} else if (actionEvent.getSource() == buttonConsole) {
			createConsolePopup();
		}

	}

	private boolean zeroPecaSetado() {
		return zeroPecaX && zeroPecaZ;
	}

	private boolean desejaDarPlay() {
		JPanel panelPopUp = new JPanel();

		JLabel desejaDarPlay = new JLabel(
				"Zero peça não definido. Deseja prosseguir?");
		panelPopUp.add(desejaDarPlay);

		panelPopUp.setLayout(new BoxLayout(panelPopUp, BoxLayout.Y_AXIS));

		return (0 == JOptionPane.showConfirmDialog(this, panelPopUp,
				"Deseja prosseguir?", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE));
	}

	private void conexaoARM() {
		JPanel panelPopUp = new JPanel();

		JLabel labelCOMPort = new JLabel("COM Port");
		JComboBox<String> comboBoxCOMPort = new JComboBox<String>(
				availableCOMPorts());

		JLabel labelBaudRate = new JLabel("Baud");
		int defaultBaudRate = 38400;
		JTextField textFieldBaudRate = new JTextField(
				String.valueOf(defaultBaudRate));

		panelPopUp.add(labelCOMPort);
		panelPopUp.add(comboBoxCOMPort);
		panelPopUp.add(labelBaudRate);
		panelPopUp.add(textFieldBaudRate);

		panelPopUp.setLayout(new BoxLayout(panelPopUp, BoxLayout.Y_AXIS));

		int reponse = JOptionPane.showConfirmDialog(this, panelPopUp,
				"Conexão com o ARM", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (reponse == 0) {
			int baudRate = Integer.parseInt(textFieldBaudRate.getText());
			String comPort = comboBoxCOMPort.getSelectedItem().toString();
			arm = new ARM(baudRate, comPort);

			try {
				arm.connect();
				arm.setVelocidadeJog(50);
				append("Velocidade da movimentação manual: "
						+ arm.getVelocidadeJog() + " % Vmax.", INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			append("Conexão cancelada.", ERRO);
			try {
				arm.disconnect();
			} catch (Exception ex) {
				append(ex);
				System.out.println(ex.getMessage());
			}

		}
	}

	public String[] availableCOMPorts() {
		ArrayList<String> ports = new ArrayList<String>();

		Enumeration portList = CommPortIdentifier.getPortIdentifiers();
		System.out.println(portList);
		while (portList.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) portList
					.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				ports.add(portId.getName());
			}
		}

		return ports.toArray((new String[ports.size()]));
	}

	private void createConsolePopup() {
		JPanel panelPopUp = new JPanel();
		JLabel labelEnviarComando = new JLabel("Digite o comando em código G");
		JTextField comando = new JTextField(30);

		panelPopUp.add(labelEnviarComando);
		panelPopUp.add(new JLabel(" "));
		panelPopUp.add(comando);

		panelPopUp.setLayout(new BoxLayout(panelPopUp, BoxLayout.Y_AXIS));

		int reponse = JOptionPane.showConfirmDialog(this, panelPopUp,
				"Enviar comando em código G", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (reponse == 0) {
			arm.processReceiveGCode(comando.getText()
					+ Protocolo.TERMINADOR_DE_MENSAGEM);
			append("Comando enviado com sucesso.", INFO);
		} else {
			append("Envio cancelado.", ERRO);
		}
	}

	// Funcao que captura o evento de segurar o clique de X+, X-, Z+, Z-
	private void buttonsJog() {
	}

	private void atualizaDiametroDaPeca() {
		double diametro = (textFieldDiametroDaPeca.getText().isEmpty()) ? 0
				: Double.parseDouble(textFieldDiametroDaPeca.getText().trim());
		if (diametro > 100) {
			textFieldDiametroDaPeca.setText("0");
			diametro = 0;
		}
		arm.setDiametroDaPeca(diametro);
		append("Diâmetro da peça: " + arm.getDiametroDaPeca() + " mm.", INFO);
		textFieldDiametroDaPeca
				.setText(String.valueOf(arm.getDiametroDaPeca()));

		atualizaDiametroDaPeca(diametro);
	}

	public static void atualizaDiametroDaPeca(double diametro) {
		listModelParametros.setElementAt("Diâmetro da peça: " + diametro
				+ " mm", parametros.DIAMETRO.index);
	}

	private void atualizaVelocidadeJog() {
		int vel = (textFieldVelocidade.getText().isEmpty()) ? 0 : Integer
				.parseInt(textFieldVelocidade.getText().trim());
		if (vel > 100) {
			textFieldVelocidade.setText("0");
			vel = 0;
		}
		arm.setVelocidadeJog(vel);
		append("Velocidade da movimentação manual: " + arm.getVelocidadeJog()
				+ " % Vmax.", INFO);
		textFieldVelocidade.setText(String.valueOf(arm.getVelocidadeJog()));

		atualizaVelocidadeJog(vel);
	}

	private static void atualizaVelocidadeJog(int vel) {
		listModelParametros.setElementAt("Velocidade da peça: " + vel
				+ " % Vmax", 0);
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
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null,
						ex);
			}
		}
	}

	private void setTitleSizeAndLocation() {
		this.setTitle("PMR2450 - Torno CNC");
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		setLocation(0, 0);

		// exit on close
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				try {
					arm.disconnect();
				} catch (Exception ex) {
				}
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
					textPaneAvisosDoSistema.getStyledDocument().getLength(),
					"\n" + text, null);

			scroll();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public static void scroll() {
		// if (shouldScroll()) {
		scrollPaneConsole.getVerticalScrollBar().setValue(
				scrollPaneConsole.getVerticalScrollBar().getMaximum());
		// }
	}

	public static boolean shouldScroll() {
		int minimumValue = scrollPaneConsole.getVerticalScrollBar().getValue()
				+ scrollPaneConsole.getVerticalScrollBar().getVisibleAmount();
		int maximumValue = scrollPaneConsole.getVerticalScrollBar()
				.getMaximum();
		return maximumValue == minimumValue;
	}

	public static void append(String text, final int tipo) {
		Style style = textPaneAvisosDoSistema.addStyle("style", null);

		if (tipo == INFO) {
			StyleConstants.setForeground(style, Color.BLUE);
		} else if (tipo == ERRO) {
			StyleConstants.setForeground(style, Color.RED);
		}
		try {
			textPaneAvisosDoSistema.getStyledDocument().insertString(
					textPaneAvisosDoSistema.getStyledDocument().getLength(),
					"\n" + text, style);

			scroll();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> readData(File f) {
		ArrayList<String> lines = new ArrayList<>();
		try {
			RandomAccessFile fichier = new java.io.RandomAccessFile(f, "r");

			String line = "";
			while ((line = fichier.readLine()) != null)
				// tant que la ligne n'est pas nulle
				lines.add(line);

			return lines;
		}

		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void carregarArquivo() {
		int returnValue = 0;
		try {
			fileChooser = new JFileChooser("."); // ele abre no current
													// directory
			returnValue = fileChooser.showOpenDialog(this);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				// verificaGramatica(file.getAbsolutePath());

				linhasDoArquivo = readData(file);

				append("\nCódigo G carregado com sucesso.", INFO);
				append(linhasDoArquivo);

				listCodigoGEmExecucao.setListData(linhasDoArquivo.toArray());
				listScrollerCodigoG.revalidate();
				listScrollerCodigoG.repaint();

				atualizaCodigoGEmExecucao(0);
				buttonEnviarCodigoG.setEnabled(true);
			} else {
				append("\nProblema na seleção do arquivo.", ERRO);
			}
		} catch (Exception ex) {
			append("Erro na leitura do código G. Corrija e tente novamente.",
					ERRO);
			append(ex.getMessage(), ERRO);
			System.out.println(ex);
		}

	}

	private void verificaGramatica(String absolutePath) throws Exception {
		LoadGcodeHandler loadCodeHandler = new LoadGcodeHandler();
		loadCodeHandler.loadProgram(absolutePath);
	}

	private void enviarArquivo() {
		if (linhasDoArquivo == null) {
			append("É necessário carregar um código G antes de enviá-lo à máquina.",
					ERRO);
			return;
		}

		// escreve linha por linha no microcontrolador - Renan modificou 05/12
		String linhaComProtocolo = "";
		for (String linha : linhasDoArquivo) {
			linhaComProtocolo = linhaComProtocolo.concat(":1 ");
			linhaComProtocolo = linhaComProtocolo.concat(linha);
			linhaComProtocolo = linhaComProtocolo.concat("%!");
			System.out.println("lalala" + linhaComProtocolo);
			arm.write(linhaComProtocolo);
			linhaComProtocolo = "";
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton bt1 = (JButton) e.getSource();

		if (!bt1.isEnabled()) {
			return;
		}
		if (e.getSource() == buttonXplus) {
			append("X+ pressed", INFO);
			arm.processReceiveCommand(4, arm.getVelocidadeJog(), true);
		} else if (e.getSource() == buttonZplus) {
			append("Z+ pressed", INFO);
			arm.processReceiveCommand(5, arm.getVelocidadeJog(), true);
		} else if (e.getSource() == buttonXless) {
			append("X- pressed", INFO);
			arm.processReceiveCommand(6, arm.getVelocidadeJog(), true);
		} else if (e.getSource() == buttonZless) {
			append("Z- pressed", INFO);
			arm.processReceiveCommand(7, arm.getVelocidadeJog(), true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JButton bt2 = (JButton) e.getSource();

		if (!bt2.isEnabled()) {
			return;
		}
		if (e.getSource() == buttonXplus) {

			append("X+ released", INFO);
			arm.processReceiveCommand(4, arm.getVelocidadeJog(), false);
		} else if (e.getSource() == buttonZplus) {
			append("Z+ released", INFO);
			arm.processReceiveCommand(5, arm.getVelocidadeJog(), false);
		} else if (e.getSource() == buttonXless) {
			append("X- released", INFO);
			arm.processReceiveCommand(6, arm.getVelocidadeJog(), false);
		} else if (e.getSource() == buttonZless) {
			append("Z- released", INFO);
			arm.processReceiveCommand(7, arm.getVelocidadeJog(), false);
		}
	}

	public static void atualizaXZ(double x, double z) {
		listModelParametros.setElementAt("X: " + x + " mm", parametros.X.index);
		listModelParametros.setElementAt("Z: " + z + " mm", parametros.Z.index);
		xzPlot.add(x, z);
	}

	public void test() {
	}

	public static void atualizaZeroPecaX(int zpx) {
		if (zpx == 1) {
			zeroPecaX = true;
			listModelParametros.setElementAt("Zero peça X definido",
					parametros.ZERO_PECA_X.index);
		}
	}

	public static void atualizaZeroPecaZ(int zpz) {
		if (zpz == 1) {
			zeroPecaZ = true;
			listModelParametros.setElementAt("Zero peça Z definido",
					parametros.ZERO_PECA_Z.index);
		}
	}

	public static void atualizaCodigoGEmExecucao(int nLinha) {
		listCodigoGEmExecucao.setSelectedIndex(nLinha); // linha 0 ===
														// indice 0
	}

	public static void liberarBotoes() {
		buttonPlay.setEnabled(true);
		buttonStop.setEnabled(true);
		buttonPause.setEnabled(true);
	}
}