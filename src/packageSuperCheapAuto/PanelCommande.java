package packageSuperCheapAuto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PanelCommande extends JPanel {
	
	private JLabel lblCommande;
	private JLabel lblArticle;
	private JComboBox<String> comboBoxArticle;
	private JLabel lblPrixUnitaire;
	private JTextField textField_prixUnitaire;
	private JLabel lblQuantiteEnStock;
	private JTextField textField_quantiteEnStock;
	Inventaire inventaire;

	/**
	 * Create the panel.
	 */
	public PanelCommande() {
		
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBackground(Color.ORANGE);
		setBounds(10, 137, 269, 136);
		setLayout(null);
		
		lblCommande = new JLabel("COMMANDE");
		lblCommande.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCommande.setBounds(10, 0, 82, 27);
		add(lblCommande);
		
		lblArticle = new JLabel("Article");
		lblArticle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblArticle.setBounds(10, 32, 52, 15);
		add(lblArticle);
		
		comboBoxArticle = new JComboBox();
		comboBoxArticle.setBounds(78, 30, 172, 21);
		add(comboBoxArticle);
		inventaire = new Inventaire();								// Constructeur et initialisation
		Set<String> ensembleCles = Inventaire.getListe().keySet();	// Set met en oeuvre l’interface Iterable
		for (String cleNomDuProduit : ensembleCles)					// Boucle for amélioré (Lire documentation)
			comboBoxArticle.addItem(cleNomDuProduit); 			
		
		lblPrixUnitaire = new JLabel("Prix unitaire: ");
		lblPrixUnitaire.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrixUnitaire.setBounds(10, 86, 100, 13);
		add(lblPrixUnitaire);
		
		textField_prixUnitaire = new JTextField();
		textField_prixUnitaire.setEditable(false);
		textField_prixUnitaire.setBounds(10, 107, 69, 19);
		add(textField_prixUnitaire);
		textField_prixUnitaire.setColumns(10);
		
		lblQuantiteEnStock = new JLabel("Quantite en stock: ");
		lblQuantiteEnStock.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantiteEnStock.setBounds(144, 86, 125, 13);
		add(lblQuantiteEnStock);
		
		textField_quantiteEnStock = new JTextField();
		textField_quantiteEnStock.setEditable(false);
		textField_quantiteEnStock.setBounds(190, 107, 60, 19);
		add(textField_quantiteEnStock);
		textField_quantiteEnStock.setColumns(10);
		
		comboBoxArticle.addActionListener(new ActionListener() {	//Ecouteur pour afficher le prix et la quantite en stock du produit

			@Override
			public void actionPerformed(ActionEvent e) {

				String item = String.valueOf(comboBoxArticle.getSelectedItem());
				
				textField_prixUnitaire.setText(String.valueOf(Inventaire.getListe().get(item).getPrix()));
				textField_quantiteEnStock.setText(String.valueOf(Inventaire.getListe().get(item).getQteStock()));

			}
		});
	}
}
