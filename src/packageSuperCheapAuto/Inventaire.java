package packageSuperCheapAuto;

import java.util.*;

public class Inventaire {
	private static HashMap<String, Produit> listeProduits = new HashMap<String, Produit>();

	public static HashMap<String, Produit> getListe() {
		return listeProduits;
	}

	public static Produit getProduit(String nom) {
		return listeProduits.get(nom);
	}

	public static void ajouterProduit(Produit produit) {
		listeProduits.put(produit.getNom(), produit);
	}

}