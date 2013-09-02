import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
            //parte NORTH
        createNORTH();
            //parte CENTER
        createCENTER();
           
        //junta as diferentes partes
        assemble(container);

    }
    
	private void assemble(Container container) {
		container.add(toolBar, BorderLayout.NORTH);
		// TODO
         //container.add(mainPane, BorderLayout.CENTER);
		this.setVisible(true); 
	}

	private void createCENTER() {
		// criar left + right panel
	}

	private void createNORTH() {
        //cria botoes com icones
        createButtons();
        //adiciona action listeners 
        createActionListeners();
        //cria toolbar
        createToolBar();		
	}
	

    private void createActionListeners() {
    	buttonAutomatico.addActionListener(this);
    	buttonManual.addActionListener(this);
    	
    	buttonAutomatico.setEnabled(true);
    	buttonManual.setEnabled(true);
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
    
    private void createButtons() {
        buttonManual = new JButton("Modo Manual"); //, new ImageIcon(getClass().getResource("images/manual.png")));
        buttonAutomatico = new JButton("Modo Autom�tico");//, new ImageIcon(getClass().getResource("images/automatico.png")));
    }
    
    private void createToolBar() {
        //met un toolbar
        toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        toolBar.setRollover(true);
        toolBar.add(buttonManual);
        toolBar.add(buttonAutomatico);
        
        toolBar.setFloatable(false); //l'utilisateur ne peut pas deplacer le ToolBar
        toolBar.setBorder(new EtchedBorder());
    }

}