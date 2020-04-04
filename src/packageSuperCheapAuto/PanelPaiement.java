package packageSuperCheapAuto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class PanelPaiement extends JPanel {

	private JScrollPane scrollPaneFacture;
	private JTable table;
	private DefaultTableModel modele;
	private JRadioButton rdbtnPaiementComptant;
	private JRadioButton rdbtnPaiementCredit;
	private ButtonGroup rdbtnGroup;
	private JLabel lblMontantDonne;
	private JLabel lblMontantRemis;
	private JTextField textField_MontantDonne;
	private JTextField textField_MontantRemis;
	private JButton btnPayez;
	private JButton btnAnnulerCommande;
	static Commande commande;

	
	PanelCarteDeMembre panelCarteDeMembre;	// Pour utiliser le getCommande de PanelCarteDeMembre et attribuer son constructeur et la valeur
											// de l'objet getCommande a commande dans ce panel-ci. 
	DecimalFormat decimalFormat = new DecimalFormat("#.00");	// Pour mettre le calcul du prix a 2 decimales 

	public PanelPaiement() {

		setBackground(Color.RED);
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBounds(10, 284, 435, 257);
		setLayout(null);
		
		scrollPaneFacture = new JScrollPane();
		scrollPaneFacture.setBounds(10, 10, 415, 110);
		add(scrollPaneFacture);
		table = new JTable();
		scrollPaneFacture.setViewportView(table);

		modele = new DefaultTableModel();
		modele.addColumn("Produit");
		modele.addColumn("Quantite");
		modele.addColumn("Prix");

		// Lier le modele a la table
		table.setModel(modele);
		
		rdbtnPaiementComptant = new JRadioButton("Paiement comptant");
		rdbtnPaiementComptant.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnPaiementComptant.setForeground(Color.WHITE);
		rdbtnPaiementComptant.setBackground(Color.RED);
		rdbtnPaiementComptant.setBounds(10, 135, 143, 21);
		add(rdbtnPaiementComptant);
		
		rdbtnPaiementCredit = new JRadioButton("Paiement credit");
		rdbtnPaiementCredit.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnPaiementCredit.setForeground(Color.WHITE);
		rdbtnPaiementCredit.setBackground(Color.RED);
		rdbtnPaiementCredit.setBounds(165, 135, 121, 21);
		add(rdbtnPaiementCredit);
		
		rdbtnGroup = new ButtonGroup();
		rdbtnGroup.add(rdbtnPaiementComptant);
		rdbtnGroup.add(rdbtnPaiementCredit);
		
		lblMontantDonne = new JLabel("Montant donne:");
		lblMontantDonne.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMontantDonne.setForeground(Color.WHITE);
		lblMontantDonne.setBounds(10, 206, 100, 13);
		add(lblMontantDonne);
		
		lblMontantRemis = new JLabel("Montant remis: ");
		lblMontantRemis.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMontantRemis.setForeground(Color.WHITE);
		lblMontantRemis.setBounds(10, 234, 100, 13);
		add(lblMontantRemis);
		
		textField_MontantDonne = new JTextField();
		textField_MontantDonne.setBounds(190, 204, 96, 19);
		add(textField_MontantDonne);
		textField_MontantDonne.setColumns(10);
		
		textField_MontantRemis = new JTextField();
		textField_MontantRemis.setBounds(190, 232, 96, 19);
		add(textField_MontantRemis);
		textField_MontantRemis.setColumns(10);
		
		btnPayez = new JButton("Payez");
		btnPayez.setForeground(Color.BLACK);
		btnPayez.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPayez.setBounds(292, 130, 133, 78);
		add(btnPayez);
		
		btnAnnulerCommande = new JButton("Annuler");
		btnAnnulerCommande.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnnulerCommande.setBounds(292, 211, 133, 40);
		add(btnAnnulerCommande);
		
		// L'ecouteur du bouton Achat 
		PanelBoutonsAchatTerminer.getJButtonBtnAchat().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			String cleProduit = (String)PanelCommande.getComboBoxArticle().getSelectedItem();	// Le produit choisi dans le JComboBox
			int quantiteItem = Inventaire.getListe().get(cleProduit).getQteStock();				// Quantite et prix des produits dans la Hashmap
			double prixItem = Inventaire.getListe().get(cleProduit).getPrix();	
			final int quantiteAchetee = 1;														// Quand client ajoute un produit dans la liste
			
			if (Inventaire.getListe().get(cleProduit).modifierQuantiteStock(quantiteAchetee)) {	// Si le nombre de produit acheter < produit en stock,
				modele.addRow(new Object[] {cleProduit, quantiteAchetee , prixItem});			// ajouter une ligne dans la JTable
				PanelCommande.getTextfieldQteStock().setText(String.valueOf(quantiteItem - 1));	// Mettre a jour la quantite en stock du JTextField 
				
				commande.ajouterItem(new Item(cleProduit, quantiteAchetee, commande.getNumeroCommande())); // Ajouter l'item dans la commande	
			}
			else if (Inventaire.getListe().get(cleProduit).modifierQuantiteStock(quantiteAchetee) == false) {
				JOptionPane.showMessageDialog(null, "Ce produit est en rupture de stock.");		// Cas ou quantite achetee > quantite en stock
			}

			// TEst pour voir si numero client et commande dans le vecteur quand clic bouton Achat
			System.out.println("numero client: " + commande.getNumeroClient());
			System.out.println("numero commande: " + commande.getNumeroCommande());
			System.out.println("quantite en stock de hashmap produit: " + Inventaire.getListe().get(cleProduit).getQteStock());
			System.out.println("nombre points Bonis: " + Clients.getClient(commande.getNumeroClient()).getPointsBonis());
			}
		});
		
		
		// L'ecouteur du bouton Terminer
		PanelBoutonsAchatTerminer.getJButtonBtnTerminer().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("nombre points bonis: " + Clients.getClient(commande.getNumeroClient()).getPointsBonis());
	        	
	        	// Ajout de Sous-total et Grand total dans la JTable
				modele.addRow(new Object[] {"Sous-total", null , decimalFormat.format(commande.calculerSousTotal())});
				modele.addRow(new Object[] {"Grand total", null , decimalFormat.format(commande.calculerGrandTotal())});
			}
		});
		
		// Ecouteur pour le bouton radio paiement credit
		rdbtnPaiementCredit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textField_MontantDonne.setEditable(false); 	// Si on choisit Crédit, les champs texte pour 
				textField_MontantRemis.setEditable(false);	// entrer le paiement et le change deviennent inutilisables
			}
		});
		
		// Ecouteur pour le bouton radio paiement comptant
		rdbtnPaiementComptant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				textField_MontantDonne.setEditable(true);	// Si on choisit Comptant, les champs texte pour  
				textField_MontantRemis.setEditable(true);	// entrer le paiement et le change deviennent utilisables
			}
		});
		
		btnPayez.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (rdbtnPaiementComptant.isSelected()) {

				double montantDonneParClient = Double.parseDouble(textField_MontantDonne.getText());
				double differenceMontantDonneRemis;

				System.out.println("points bonis: " + Clients.getClient(commande.getNumeroClient()).getPointsBonis());
				
					if (Clients.getClient(commande.getNumeroClient()).assezArgent(commande, montantDonneParClient) == true) {
					
						differenceMontantDonneRemis = Clients.getClient(commande.getNumeroClient()).payerCommandeComptant(commande, montantDonneParClient);
						
						textField_MontantRemis.setText(String.valueOf(differenceMontantDonneRemis));
						JOptionPane.showMessageDialog(null, "Merci pour votre argent comptant!!");

					}
					else if (Clients.getClient(commande.getNumeroClient()).assezArgent(commande, montantDonneParClient) == false) {
						JOptionPane.showMessageDialog(null, "Pas assez d'argent comptant!!");
					}
				}
				else if (rdbtnPaiementCredit.isSelected()) {
				
					if (Clients.getClient(commande.getNumeroClient()).payerCommandeCarteCredit(commande) == true) {
						JOptionPane.showMessageDialog(null, "Merci pour votre argent!");
					}
					else if (Clients.getClient(commande.getNumeroClient()).payerCommandeCarteCredit(commande) == false) {
						JOptionPane.showMessageDialog(null, "Limite atteinte (2000.00$). Commande refusee.");
					}	
				}			
				System.out.println("nb points bonis: " + Clients.getClient(commande.getNumeroClient()).getPointsBonis());
			}
		});
		
		// Ecouteur pour le bouton Annuler 
		btnAnnulerCommande.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (commande.estPayee() == true) {
					PanelCarteDeMembre.setTextfieldNumeroCarteMembre("");	// Vide les champs client
					PanelCarteDeMembre.setTextfieldNomDuClient("");
					PanelCarteDeMembre.setTextfieldNbPointsBonis(0);
					
					modele.setRowCount(0); 									// Vide La JTable 
					
					textField_MontantDonne.setText("");						// Vide les champs de paiement
					textField_MontantRemis.setText("");
										
				}
				else if (commande.estPayee() == false) {
					
					commande.getItems().clear();							// Vide le vecteur d'Items
					modele.setRowCount(0);									// Vide la JTable avec la facture
					Inventaire.initFeuilleExcelProduit();					// Remet les produits dans l'inventaire
					Clients.initFeuilleExcelClient();						// Si jamais le client ne veut pas qu'on garde ses donnees
				}
			}
		});
	}
	
	public static void setCommande(Commande commandeTemporaire) {
		
		commande = commandeTemporaire;
	}

}
