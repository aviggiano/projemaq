import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

public class IHM extends JFrame implements ActionListener {
	
    // variaveis
    private JFileChooser fileChooser;
    
    private JMenuBar barreDeMenus;
  
    protected JTextArea textArea;
    protected JScrollPane scrollPane;
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
		// TODO Auto-generated method stub
		
	}

	private void create7() {
		// TODO Auto-generated method stub
		
	}

	private void create6() {
		// TODO Auto-generated method stub
		
	}

	private void create5() {
		// TODO Auto-generated method stub
		
	}

	private void create3() {
		// TODO Auto-generated method stub
		
	}

	private void create4() {
		// TODO Auto-generated method stub
		
	}

	private void create2() {
		// botao Modo Automatico
        buttonAutomatico = new JButton("Automático"); //, new ImageIcon(getClass().getResource("images/manual.png")));
        buttonAutomatico.addActionListener(this);
        buttonAutomatico.setEnabled(true);
        
        // Carregar código G
        buttonCarregarCodigoG = new JButton("Carregar código G");
        buttonCarregarCodigoG.addActionListener(this);
        buttonCarregarCodigoG.setEnabled(true);
        
        // Enviar código G
        buttonEnviarCodigoG = new JButton("Enviar código G");
        buttonEnviarCodigoG.addActionListener(this);
        buttonEnviarCodigoG.setEnabled(true);  
        
        // Stop
        buttonStop = new JButton("Stop");
        buttonStop.addActionListener(this);
        buttonStop.setEnabled(true);
        
        // Play
        buttonPlay = new JButton("Play");
        buttonPlay.addActionListener(this);
        buttonPlay.setEnabled(true);
        
        // Pause
        buttonPause = new JButton("Pause");
        buttonPause.addActionListener(this);
        buttonPause.setEnabled(true);
        
        // layout parte 2
        JPanel panelBotoesDeControle = new JPanel();
        panelBotoesDeControle.setLayout(new FlowLayout());
        panelBotoesDeControle.add(buttonStop);
        panelBotoesDeControle.add(buttonPlay);
        panelBotoesDeControle.add(buttonPause);
        
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        
        panel2.add(buttonAutomatico);
        panel2.add(buttonCarregarCodigoG);
        panel2.add(buttonEnviarCodigoG);
        panel2.add(panelBotoesDeControle);
	}

	private void create1() {
		// botao Modo Manual
        buttonManual = new JButton("Manual"); //, new ImageIcon(getClass().getResource("images/manual.png")));
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
        labelVelocidade = new JLabel("Velocidade");
        textFieldVelocidade = new JTextField("50");
        labelUnidadeDaVelocidade = new JLabel("% Vmax");
        
        // botao Ligar Eixo Arvore
        buttonLigarEixoArvore = new JButton("Ligar Eixo Árvore");
        
        // layout parte 1
        JPanel panelPassoDoDeslocamento = new JPanel();
        panelPassoDoDeslocamento.setLayout(new FlowLayout());
        panelPassoDoDeslocamento.add(labelPassoDoDeslocamento);
        panelPassoDoDeslocamento.add(spinnerPassoDoDeslocamento);
        panelPassoDoDeslocamento.add(labelUnidadePassoDoDeslocamento);
        
        JPanel panelVelocidade = new JPanel();
        panelVelocidade.setLayout(new FlowLayout());
        panelVelocidade.add(labelVelocidade);
        panelVelocidade.add(textFieldVelocidade);
        panelVelocidade.add(labelUnidadeDaVelocidade);

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        
        panel1.add(buttonManual);
        panel1.add(panelPassoDoDeslocamento);
        panel1.add(panelVelocidade);
        panel1.add(buttonLigarEixoArvore);
	}

	private void assemble(Container container) {
		container.add(panel1, BorderLayout.WEST);
		container.add(panel2, BorderLayout.EAST);
		// TODO
         //container.add(mainPane, BorderLayout.CENTER);
		this.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
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
        frameSize = new Dimension ((int)(screenSize.width), (int)(screenSize.height));
        
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