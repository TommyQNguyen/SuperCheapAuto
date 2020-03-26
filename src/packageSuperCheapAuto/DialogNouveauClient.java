package packageSuperCheapAuto;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JButton;

public class DialogNouveauClient extends JDialog {
	private JLabel lblNomDuClient;
	private JTextField textField_nomDuClientNouveau;
	private JLabel lblNumeroCarteMembreGenere;
	private JTextField textField_numeroCarteMembreGenere;
	private JButton btnOk;
	private JButton btnAnnuler;
	private PanelCarteDeMembre panelCarteDeMembre;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DialogNouveauClient dialog = new DialogNouveauClient();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the dialog.
	 */
	public DialogNouveauClient() {}
	
	public DialogNouveauClient(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		
		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		setBounds(100, 100, 450, 218);
		getContentPane().setLayout(null);
		
		lblNomDuClient = new JLabel("Nom du client:");
		lblNomDuClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDuClient.setBounds(10, 25, 119, 32);
		getContentPane().add(lblNomDuClient);
		
		textField_nomDuClientNouveau = new JTextField();
		textField_nomDuClientNouveau.setBounds(283, 25, 130, 27);
		getContentPane().add(textField_nomDuClientNouveau);
		textField_nomDuClientNouveau.setColumns(10);
		
		lblNumeroCarteMembreGenere = new JLabel("Numero de Carte de membre genere: ");
		lblNumeroCarteMembreGenere.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumeroCarteMembreGenere.setBounds(10, 67, 287, 32);
		getContentPane().add(lblNumeroCarteMembreGenere);
		
		textField_numeroCarteMembreGenere = new JTextField();
		textField_numeroCarteMembreGenere.setEditable(false);
		textField_numeroCarteMembreGenere.setBounds(317, 73, 96, 28);
		getContentPane().add(textField_numeroCarteMembreGenere);
		textField_numeroCarteMembreGenere.setColumns(10);

		Random random = new Random();														// Initialise une instance de Random; une seule est suffisante
		int numeroCarteMembreGenere = random.nextInt(899999) + 100000;						// Genere un entier aleatoire de 0 a 899999, et ajouter 100000
		
		while ( Clients.getListe().containsKey(String.valueOf(numeroCarteMembreGenere)) ) {	// Tant que la cle existe dans la table,
		numeroCarteMembreGenere = random.nextInt(899999) + 100000;							// generer un entier aleatoire
		}
		textField_numeroCarteMembreGenere.setText(String.valueOf(numeroCarteMembreGenere));	// Inserer dans la zone le nouveau numero de carte aleatoire
		
		btnOk = new JButton("OK");
		btnOk.setBounds(84, 122, 85, 42);
		getContentPane().add(btnOk);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(250, 122, 85, 42);
		getContentPane().add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		

		// Lire des données provenant du fichier Excel  
		// Créer un flux de lecture

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InputStream inp = new FileInputStream ( "Clients.xlsx");
					XSSFWorkbook classeur = ( XSSFWorkbook ) WorkbookFactory.create(inp);
					XSSFSheet feuille = classeur.getSheetAt(0); 			// Feuille commence a 0, alors chercher Feuille 1 

					// Modifier le contenu d'une cellule vide ( 25 dans la cellule G3 )
					XSSFRow nouvelleRangee = feuille.createRow(feuille.getPhysicalNumberOfRows());
					XSSFCell nouvelleCelluleNumeroCarteMembre = nouvelleRangee.createCell(0);
					XSSFCell nouvelleCelluleNomClient = nouvelleRangee.createCell(1);
					XSSFCell nouvelleCellulePointsBonis = nouvelleRangee.createCell(2);
					XSSFCell nouvelleCelluleSoldeCredit = nouvelleRangee.createCell(3);

					nouvelleCelluleNumeroCarteMembre.setCellValue(textField_numeroCarteMembreGenere.getText());
					nouvelleCellulePointsBonis.setCellValue(0);  
					nouvelleCelluleNomClient.setCellValue(textField_nomDuClientNouveau.getText());
					nouvelleCelluleSoldeCredit.setCellValue(0.0);
					
					String nouveauNumeroCarteMembre = textField_numeroCarteMembreGenere.getText();
					String nouveauNomDuClient = textField_nomDuClientNouveau.getText();

					Clients.ajouterClient(new Client(textField_numeroCarteMembreGenere.getText(), 	// Ajout du nouveau client aussi dans la HashMap
							textField_nomDuClientNouveau.getText(),
							0, 
							0.0));
					
//					panelCarteDeMembre.getTextfieldNumeroCarteMembre().setText(nouveauNumeroCarteMembre);

					// Utiliser un flux d'écriture pour enregistrer les changements 
					OutputStream out = new FileOutputStream ( "Clients.xlsx");
					classeur.write(out);  
					out.close();
					inp.close();
				}
				catch ( Exception f)
				{
					f.printStackTrace();
				}

				dispose();
//				panelCarteDeMembre.getTextfieldNumeroCarteMembre().setText(nouveau);
			}

	        });

	}
}


