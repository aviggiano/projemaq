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
	private JScrollPane panel4;
	private JTextArea textAreaCodigoGEmExecucao;
	private JScrollPane panel5;
	private JTextArea textAreaConsole;
	private JPanel panel6;
	private JLabel labelParametros;
	private JLabel labelZeroX;
	private JLabel labelZeroZ;
	private JButton buttonConsole;

	private JLabel labelAtencao;

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
		
		buttonConsole = new JButton("Console", new ImageIcon(getClass().getResource("img/terminal.png")));
		
		try {
			imageIconWarning = ImageIO.read(this.getClass().getResource("img/warning.png"));
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
		textAreaConsole = new JTextArea("IHM inicializada com sucesso.", 10, 68);
        font = new Font("Arial", Font.PLAIN, 12);
        textAreaConsole.setFont(font);
        textAreaConsole.setEditable(false);
        
        scrollPaneConsole = new JScrollPane(textAreaConsole);
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
        textFieldVelocidade = new JTextField("  50");
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
        buttonCarregarCodigoG = new JButton("Carregar código G", new ImageIcon(getClass().getResource("img/filesave.png")));
        buttonCarregarCodigoG.addActionListener(this);
        buttonCarregarCodigoG.setEnabled(true);
        
        // Enviar código G
        buttonEnviarCodigoG = new JButton("    Enviar código G", new ImageIcon(getClass().getResource("img/extract-archive.png")));
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
		// TODO Auto-generated method stub
		if (actionEvent.getSource() == buttonPlay) {
			
		}
		
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
}