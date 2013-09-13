import org.jfree.ui.RefineryUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class IHM extends JFrame implements ActionListener {
	
    // variaveis
    private JFileChooser fileChooser;
    
    private JMenuBar barreDeMenus;
  
    protected JScrollPane scrollPaneConsole;
    protected JScrollPane scrollPaneCodigoGEmExecucao;
    protected Toolkit toolkit = Toolkit.getDefaultToolkit();
    protected Dimension screenSize = toolkit.getScreenSize();
    protected Dimension frameSize;
    private JToolBar toolBar;
    
    private JButton buttonManual;
    private JButton buttonAutomatico;
	private JLabel labelPassoDoDeslocamento;
	private SpinnerNumberModel spinnerNumberModel;
	private JSpinner spinnerPassoDoDeslocamento;
	private JLabel labelUnidadePassoDoDeslocamento;
	private JLabel labelVelocidade;
	private JTextField textFieldVelocidade;
	private JLabel labelUnidadeDaVelocidade;
	private JButton buttonLigarEixoArvore;
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
	private JPanel panel4;
	private JTextArea textAreaCodigoGEmExecucao;
	private JScrollPane panel5;
	private JTextArea textAreaConsole;
	private JPanel panel6;
	private JLabel labelParametros;
	private JLabel labelZeroX;
	private JLabel labelZeroZ;
	private JPanel panel7;
	private JButton buttonEmergencia;

	private JLabel labelAtencao;

	private JPanel panel8;

	private Font font;

	private JLabel labelConsoleInterativo;

	private JTextField textFieldConsoleInterativo;

	private JButton buttonConsoleInterativo;

	private BufferedImage imageIconWarning;
    
    /*
     * Constructor
     */   
    public IHM () {
        //set title, size and location
        setTitleSizeAndLocation();
        //set look and feel
        setLookAndFeel("Windows"); 
        //pega o container do JFrame
        Container container = getContentPane();

        //criar as diferentes partes do container
        create1();
        create2();
        create3();
        create4();
        create5();
        create6();
        create7();
        create8();
        
        //junta as diferentes partes
        assemble(container);

    }
    
	private void create8() {
		buttonEmergencia = new JButton("Emergência");
		buttonEmergencia.setForeground(Color.RED);
		buttonEmergencia.setBackground(Color.RED);
		try {
			imageIconWarning = ImageIO.read(this.getClass().getResource("img/warning.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelAtencao = new JLabel("Atenção: máquina ligada!");
		
		panel8 = new JPanel();
		panel8.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel8.add(buttonEmergencia);
		buttonEmergencia.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel8.add(new JLabel(new ImageIcon(imageIconWarning)));
		panel8.add(labelAtencao);
		
	}

	private void create7() {
		labelParametros = new JLabel("Parâmetros");
		labelZeroX = new JLabel("Zero X: ");
		labelZeroZ = new JLabel("Zero Z: ");

		// layout parte 7
		panel7 = new JPanel();
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
		panel7.add(labelParametros);
		panel7.add(labelZeroX);
		panel7.add(labelZeroZ);
	}

	private void create6() {
		textAreaConsole = new JTextArea("IHM inicializada com sucesso.", screenSize.height/60, screenSize.width/40);
        font = new Font("Arial", Font.PLAIN, 12);
        textAreaConsole.setFont(font);
        textAreaConsole.setEditable(false);
        scrollPaneConsole = new JScrollPane(textAreaConsole);
        scrollPaneConsole.setBorder(new TitledBorder("Avisos do sistema"));
		
		// layout parte 6
		panel6 = new JPanel(new GridBagLayout());
		panel6.add(scrollPaneConsole);
		
	}

	private void create5() {
		textAreaCodigoGEmExecucao = new JTextArea("Código G em execução: dsadsaoddoldoqwldwqodkwqodwoq", 
				screenSize.height/50, screenSize.width/50);
        font = new Font("Arial", Font.PLAIN, 12);
        textAreaCodigoGEmExecucao.setFont(font);
        textAreaCodigoGEmExecucao.setEditable(false);
        scrollPaneCodigoGEmExecucao = new JScrollPane(textAreaCodigoGEmExecucao);
		
		
		// layout parte 5
		panel5 = scrollPaneCodigoGEmExecucao;
		
	}

	private void create4() {
		// grafico XZ
		XZPlot xzPlot = new XZPlot("XZ Series Demo");
        xzPlot.setVisible(true);
        
        // layout parte 4
        panel4 = new JPanel();
        panel4.add(xzPlot);
        
	}

	private void create3() {
		// console interativo
		textFieldConsoleInterativo = new JTextField("				");
		// botao console interativo
		buttonConsoleInterativo = new JButton("Enviar comando");
		buttonConsoleInterativo.addActionListener(this);
		buttonConsoleInterativo.setEnabled(true);
		
		// botao Setar Zero Peça Eixo X
		buttonZerarX = new JButton("Zerar eixo X");
		buttonZerarX.addActionListener(this);
		buttonZerarX.setEnabled(true);
        
		// botao Setar Zero Peça Eixo Z
		buttonZerarZ = new JButton("Zerar eixo Z");
		buttonZerarZ.addActionListener(this);
		buttonZerarZ.setEnabled(true);
        
		// botao X+
		buttonXplus = new JButton ("X+", new ImageIcon(getClass().getResource("img/next.png")));
		buttonXplus.addActionListener(this);
		buttonXplus.setEnabled(true);

		// botao X-
		buttonXless = new JButton ("X-", new ImageIcon(getClass().getResource("img/previous.png")));
		buttonXless.addActionListener(this);
		buttonXless.setEnabled(true);

		// botao Z+
		buttonZplus = new JButton ("Z+ ", new ImageIcon(getClass().getResource("img/up.png")));
		buttonZplus.addActionListener(this);
		buttonZplus.setEnabled(true);

		// botao Z-
		buttonZless = new JButton ("Z-  ", new ImageIcon(getClass().getResource("img/down.png")));
		buttonZless.addActionListener(this);
		buttonZless.setEnabled(true);
		
		// layout parte 3
		JPanel panelConsole = new JPanel();
		panelConsole.setBorder(new TitledBorder("Console interativo"));
		panelConsole.setLayout(new GridBagLayout());
		panelConsole.add(textFieldConsoleInterativo);
		panelConsole.add(buttonConsoleInterativo);
		
		JPanel panelZerar = new JPanel();
		panelZerar.setLayout(new GridBagLayout());//(panelZerar, BoxLayout.X_AXIS));
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
		
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3,0));//(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(panelConsole);
		panel3.add(panelZerar);
		panel3.add(panelSetinhas);
		
	}

	private void create2() {
		// botao Modo Automatico
        buttonAutomatico = new JButton("Automático", new ImageIcon(getClass().getResource("img/computer.png")));
        buttonAutomatico.addActionListener(this);
        buttonAutomatico.setEnabled(true);

        
        // Carregar código G
        buttonCarregarCodigoG = new JButton("Carregar código G", new ImageIcon(getClass().getResource("img/filesave.png")));
        buttonCarregarCodigoG.addActionListener(this);
        buttonCarregarCodigoG.setEnabled(true);
        
        // Enviar código G
        buttonEnviarCodigoG = new JButton("	    Enviar código G", new ImageIcon(getClass().getResource("img/extract-archive.png")));
        buttonEnviarCodigoG.addActionListener(this);
        buttonEnviarCodigoG.setEnabled(true);  
        
        // Stop
        buttonStop = new JButton("", new ImageIcon(getClass().getResource("img/player_stop.png")));
        buttonStop.addActionListener(this);
        buttonStop.setEnabled(true);
        
        // Play
        buttonPlay = new JButton("", new ImageIcon(getClass().getResource("img/player_play.png")));
        buttonPlay.addActionListener(this);
        buttonPlay.setEnabled(true);
        
        // Pause
        buttonPause = new JButton("", new ImageIcon(getClass().getResource("img/player_pause.png")));
        buttonPause.addActionListener(this);
        buttonPause.setEnabled(true);
        
        // layout parte 2
        JPanel panelBotoesDeControle = new JPanel();
        panelBotoesDeControle.setLayout(new GridLayout(1,3));
        panelBotoesDeControle.add(buttonPlay);
        panelBotoesDeControle.add(buttonStop);
        panelBotoesDeControle.add(buttonPause);
        
        panel2 = new JPanel();
        //panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setLayout(new GridLayout(4,0));
        
        panel2.add(buttonAutomatico);
        panel2.add(buttonCarregarCodigoG);
        panel2.add(buttonEnviarCodigoG);
        panel2.add(panelBotoesDeControle);
        
	}

	private void create1() {
		// botao Modo Manual
        buttonManual = new JButton("Manual", new ImageIcon(getClass().getResource("img/stock_person.png")));
		buttonManual.addActionListener(this);
        buttonManual.setEnabled(true);
        
        // Passo da Setinha
        labelPassoDoDeslocamento = new JLabel("Passo do deslocamento:");
        double initialValue = 1;
        double min = 0;
        double max = 10;
        double step = 1;
        spinnerNumberModel =  new SpinnerNumberModel(initialValue, min, max, step);
        spinnerPassoDoDeslocamento = new JSpinner(spinnerNumberModel);
        labelUnidadePassoDoDeslocamento = new JLabel("mm");
        
        // Velocidade
        labelVelocidade = new JLabel("Velocidade:");
        textFieldVelocidade = new JTextField("  50");
        labelUnidadeDaVelocidade = new JLabel("% Vmax");
        
        // botao Ligar Eixo Arvore
        buttonLigarEixoArvore = new JButton("Ligar Eixo Árvore", new ImageIcon(getClass().getResource("img/preferences-system.png")));
        
        // layout parte 1
        JPanel panelPassoDoDeslocamento = new JPanel();
        panelPassoDoDeslocamento.setLayout(new FlowLayout());
        panelPassoDoDeslocamento.add(labelPassoDoDeslocamento);
        panelPassoDoDeslocamento.add(spinnerPassoDoDeslocamento);
        panelPassoDoDeslocamento.add(labelUnidadePassoDoDeslocamento);
        
        JPanel panelVelocidade = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel p1 = new JPanel();
        p1.add(labelVelocidade);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p2.add(textFieldVelocidade);
        p2.add(labelUnidadeDaVelocidade);
        panelVelocidade.add(p1);
        panelVelocidade.add(p2);
        
        /*
        panelVelocidade.add(labelVelocidade);
        panelVelocidade.add(textFieldVelocidade);
        panelVelocidade.add(labelUnidadeDaVelocidade);
*/

        panel1 = new JPanel();
        //panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setLayout(new GridLayout(4,0));
        
        panel1.add(buttonManual);
        panel1.add(panelPassoDoDeslocamento);
        panel1.add(panelVelocidade);
        panel1.add(buttonLigarEixoArvore);
        
	}

	private void assemble(Container container) {
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		JPanel panel1and2 = new JPanel();
		panel1and2.add(panel1);
		panel1and2.add(panel2);
		
		west.add(panel1and2);
		west.add(panel3);

		panel1and2.setBorder((Border)BorderFactory.createLineBorder(Color.black));
		panel3.setBorder((Border)BorderFactory.createLineBorder(Color.black));
		
		JPanel centercenter = panel4;//new JPanel();
		panel4.add(panel5);
		
		JPanel centersouth = new JPanel();
		centersouth.add(panel6, BorderLayout.CENTER);
		centersouth.add(panel7, BorderLayout.EAST);
		
		//centersouth.add(panel6, BorderLayout.CENTER);
		//centersouth.add(panel7, BorderLayout.EAST);
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.add(centercenter, BorderLayout.CENTER);
		center.add(centersouth, BorderLayout.SOUTH);
		
		center.setBorder((Border)BorderFactory.createLineBorder(Color.black));

		JPanel south = panel8;
		
		south.setBorder((Border)BorderFactory.createLineBorder(Color.black));
		
		container.add(west, BorderLayout.WEST);
		container.add(center, BorderLayout.CENTER);
		container.add(south, BorderLayout.SOUTH);
		
		this.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
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
        setTitle("PMR2450 - Torno CNC");
        frameSize = new Dimension ((int)(screenSize.width*0.9), (int)(screenSize.height*0.9));
        
        setSize(frameSize); 
        setLocation(0,0); // localisation standard
        // exit on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}