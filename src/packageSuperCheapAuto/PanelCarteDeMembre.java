package packageSuperCheapAuto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class PanelCarteDeMembre extends JPanel 
{
	private JLabel lblNumeroCarteMembre;
	private JLabel lblNomDuClient;
	private JLabel lblNombreDePointsBonis;
	private static JTextField textField_numeroCarteMembre;
	private static JTextField textField_nomDuClient;
	private static JTextField textField_nombrePointsBonis;
	private static final String ACTION_ENTER = "entrer";
	private Clients clients;		// Initialise une instance pour ensuite appeler le constructeur quelques lignes apres

	public PanelCarteDeMembre() 
	{
		clients = new Clients();	// Initialisation du constructeur de Clients() pour importer les donnees Excel
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBackground(Color.GREEN);
		setBounds(10, 10, 435, 114);
		
		lblNumeroCarteMembre = new JLabel("Numero de la carte de membre: ");
		lblNumeroCarteMembre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroCarteMembre.setBounds(10, 1, 216, 37);
		add(lblNumeroCarteMembre);
		
		textField_numeroCarteMembre = new JTextField();
		textField_numeroCarteMembre.setBounds(329, 10, 96, 19);
		add(textField_numeroCarteMembre);
		textField_numeroCarteMembre.setColumns(10);
		
		lblNomDuClient = new JLabel("Nom du client:");
		lblNomDuClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNomDuClient.setBounds(10, 48, 121, 19);
		add(lblNomDuClient);
		
		textField_nomDuClient = new JTextField();
		textField_nomDuClient.setBounds(329, 49, 96, 19);
		add(textField_nomDuClient);
		textField_nomDuClient.setColumns(10);
		textField_nomDuClient.setEditable(false);
		
		lblNombreDePointsBonis = new JLabel("Nombre de points bonis a ce jour: ");
		lblNombreDePointsBonis.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreDePointsBonis.setBounds(10, 85, 239, 19);
		add(lblNombreDePointsBonis);
		
		textField_nombrePointsBonis = new JTextField();
		textField_nombrePointsBonis.setBounds(329, 86, 96, 19);
		add(textField_nombrePointsBonis);
		textField_nombrePointsBonis.setColumns(10);
		textField_nombrePointsBonis.setEditable(false);
        
		// Permet de faire une action apres avoir appuye sur Enter
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK), ACTION_ENTER); 
		getActionMap().put(ACTION_ENTER, new Ecouteur(ACTION_ENTER));
       
		textField_numeroCarteMembre.addActionListener(new Ecouteur(ACTION_ENTER));
      }
	
	private class Ecouteur extends AbstractAction {
		private String entrer;

		public Ecouteur(String entrer) {
			this.entrer = entrer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			setBackground(Color.PINK);
			
			String cleNumeroCarteMembre = textField_numeroCarteMembre.getText();
			
			if (Clients.getListe().containsKey(cleNumeroCarteMembre)) {								// Si le numero de carte de membre entree existe
			textField_nomDuClient.setText(Clients.getListe().get(cleNumeroCarteMembre).getNom());	// Remplir les champs Nom et Point(s) bonis		
			textField_nombrePointsBonis.setText(String.valueOf(Clients.getListe().get(cleNumeroCarteMembre).getPointsBonis()));
			}
			else if (Clients.getListe().containsKey(cleNumeroCarteMembre) != true) {				// Si le numero de carte n'existe PAS
				JOptionPane.showMessageDialog(null, "Ce numero n'existe pas.");						// Afficher message d'erreur
				textField_numeroCarteMembre.setText("");											// Effacer le numero non-existant du champ
			}			
		}
	}
	
	
	public static void setTextfieldNumeroCarteMembre (String numeroCarteMembre) {	// Methodes setTextField pour selectionner nouveau client
		textField_numeroCarteMembre.setText(numeroCarteMembre);						//  apres l'avoir cree a partir de la classe DialogNouveauClient
	}
	
	public static void setTextfieldNomDuClient (String nomDuClient) {
		textField_nomDuClient.setText(nomDuClient);
	}
	
	
	public static void setTextfieldNbPointsBonis (int nombrePointsBonis) {
		textField_nombrePointsBonis.setText(String.valueOf(nombrePointsBonis));		// Conversion de int en String pour le JTextField
	}
	
	}
	
	

