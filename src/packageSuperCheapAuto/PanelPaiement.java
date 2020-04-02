package packageSuperCheapAuto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		// Remplir le JScrollPane au complet avec du blanc
		// table.setFillsViewportHeight(true);

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
		// Ajouter des elements
		modele.addRow(new Object[] { "Andreanne", "AAA", 88 });
		modele.addRow(new Object[] { "Denise", "DDD", 55 });
		
//		commande = panelCarteDeMembre.getCommandeTemporaire(); // A tester..? 
		
		PanelBoutonsAchatTerminer.getJButtonBtnAchat().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			String cleProduit = (String)PanelCommande.getComboBoxArticle().getSelectedItem();	// Le produit choisi dans le JComboBox
			int quantiteItem = Inventaire.getListe().get(cleProduit).getQteStock();				// Quantite et prix des produits dans la Hashmap
			double prixItem = Inventaire.getListe().get(cleProduit).getPrix();	
			final int quantiteAchetee = 1;														// Quand client ajoute un produit dans la liste
			
			if (Inventaire.getListe().get(cleProduit).modifierQuantiteStock(1)) {				// Si le nombre de produit acheter < produit en stock,
				modele.addRow(new Object[] {cleProduit, quantiteAchetee , prixItem});			// ajouter une ligne dans la JTable
				PanelCommande.getTextfieldQteStock().setText(String.valueOf(quantiteItem - 1));	// Changer le quantite en stock dans le GUI 
				commande.ajouterItem(new Item(cleProduit, quantiteAchetee, commande.getNumeroCommande()));
			}
			else {
				JOptionPane.showMessageDialog(null, "Ce produit est en rupture de stock.");		// Cas ou quantite achetee > quantite en stock
			}

			// TEst pour voir si numero client et commande dans le vecteur quand clic bouton Achat
			System.out.println("numero client: " + commande.getNumeroClient());
			System.out.println("numero commande: " + commande.getNumeroCommande());
			System.out.println("quantite en stock de hashmap produit: " + Inventaire.getListe().get(cleProduit).getQteStock());
			
			

			}
		});
		
		PanelBoutonsAchatTerminer.getJButtonBtnTerminer().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//test pour voir contenu du vecteur entier sur click du bouton Terminer
			for (int i = 0; i < commande.getItems().size(); i++) {
				System.out.println("nom du produit dans vecteur: " + commande.getItems().elementAt(i).getNomProduit());
				System.out.println("quantite commandee dans vecteur: " + commande.getItems().elementAt(i).getQuantite());
				System.out.println("numero commande associe dans vecteur: " + commande.getItems().elementAt(i).getNumeroCommandeAssociee());
//				System.out.println(commande.getItems().elementAt(i).toString());

			}

			}
		});
		
	}
	
	public static void setCommande(Commande commandeTemporaire) {
		
		commande = commandeTemporaire;
	}

}
