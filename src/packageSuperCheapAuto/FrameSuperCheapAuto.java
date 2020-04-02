package packageSuperCheapAuto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class FrameSuperCheapAuto extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSuperCheapAuto frame = new FrameSuperCheapAuto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameSuperCheapAuto() {
		setBounds(100, 100, 471, 610);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PanelSuperCheapAuto panelSuperCheapAuto = new PanelSuperCheapAuto();
		getContentPane().add(panelSuperCheapAuto);
		
		// Creer la barre complete et l'ajouter au frame
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuOptions = new JMenu("Options");
		menuOptions.setMnemonic(KeyEvent.VK_O); //Utiliser touches Alt + f
		menuBar.add(menuOptions);

		JMenuItem menuItemNouveauClient = new JMenuItem("Nouveau client");
		menuItemNouveauClient.setMnemonic(KeyEvent.VK_N);
		menuItemNouveauClient.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		menuOptions.add(menuItemNouveauClient);
		menuItemNouveauClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				// Exemple de popup complexe personnalisable
				
				var dialog = new DialogNouveauClient(FrameSuperCheapAuto.this, "Inscription d'un nouveau Client", true);
				dialog.setLocationRelativeTo(FrameSuperCheapAuto.this);
				dialog.setVisible(true);
			

//				getContentPane().setBackground(Color.red);
			}
		});
	}
}
