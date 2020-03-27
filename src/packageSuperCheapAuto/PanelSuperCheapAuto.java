package packageSuperCheapAuto;

import java.util.Random;

import javax.swing.JPanel;

public class PanelSuperCheapAuto extends JPanel {

	private PanelCarteDeMembre panelCarteDeMembre;
	private PanelCommande panelCommande;
	private PanelBoutonsAchatTerminer panelBoutonsAchatTerminer;


	public PanelSuperCheapAuto() {

		setSize(455, 551);
		setLayout(null);
	
		panelCarteDeMembre = new PanelCarteDeMembre();
		add(panelCarteDeMembre);
		
		panelCommande = new PanelCommande();
		add(panelCommande);
		
		panelBoutonsAchatTerminer = new PanelBoutonsAchatTerminer();
		add(panelBoutonsAchatTerminer);
		
		//A EFFACER ces tests apres lol :P
		// Initialise un objet random a qqpart; un est suffisant
		Random random = new Random();

		// Genere un int random de 0 a 899999, et ajoute 100000 ensuite
		int x = random.nextInt(899999) + 100000;
		System.out.println(x);
		
		// Méthode keySet retourne un Set de toutes les clés (ici Set<String>)
		System.out.println("hashmap.keySet() : ");

		Inventaire inv = new Inventaire();
		
		var ensembleCles = Inventaire.getListe().keySet();
		for (var cle : ensembleCles)
			System.out.println(cle); // for amélioré, Set met en oeuvre l’interface Iterable
		
		// Méthode values retourne une Collection de toutes les valeurs (ici Collection<Point>)
		System.out.println("hashmap.values() : ");
		var ensembleValeurs = Inventaire.getListe().values();
		for (var valeur : ensembleValeurs)
			System.out.println(valeur.getNom()); // for amélioré, Set met en oeuvre l’interface Iterable

	}
}
