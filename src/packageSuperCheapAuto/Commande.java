package packageSuperCheapAuto;

import java.util.*;

public class Commande {
	private static int COMPTEUR_NUMERO_COMMANDE;
	
	private int numeroCommande;
	private String numeroClient;
	private Vector<Item> items;
	private boolean estPayee;

	public Commande(String numeroClient) {
		this.numeroCommande = COMPTEUR_NUMERO_COMMANDE++;
		this.numeroClient = numeroClient;
		items = new Vector<Item>();
	}

	public boolean estPayee() {
		return estPayee;
	}

	public void setPayee(boolean estPayee) {
		this.estPayee = estPayee;
	}

	public int getNumeroCommande() {
		return numeroCommande;
	}

	public String getNumeroClient() {
		return numeroClient;
	}

	public Vector<Item> getItems() {
		return items;
	}

	public void ajouterItem(Item item) {
		items.add(item);
	}

	public double calculerSousTotal() {
		double total = 0;
		for (int i = 0; i < items.size(); i++) {
			var item = items.get(i);
			int quantite = item.getQuantite();
			var produit = Inventaire.getProduit(item.getNomProduit());
			double prix = produit.getPrix();
			total += quantite * prix;
		}

		return total;
	}

	public double calculerTPS() {
		double total = calculerSousTotal();
		return total * 0.05;
	}
	
	// � partir du 1er janvier 2013, la taxe de vente du Qu�bec est harmonis�e avec
	// le r�gime de la TPS.
	// Ainsi, la TVQ est calcul�e sur le prix excluant la TPS. Pour contrer les
	// pertes de revenu encourues
	// par le gouvernement qu�b�cois suite � cette modification, le taux de la TVQ a
	// �t� port� � 9,975 %.
	// Pour les consommateurs, la modification n'a aucun impact en terme de montant
	// � d�bourser,
	// puisque le taux combin� est identique � celui de 2012, soit 14,975 %
	public double calculerTVQ() {
		double total = calculerSousTotal();
		return total * 0.09975;
	}

	public double calculerGrandTotal() {
		double total = calculerSousTotal() + calculerTPS() + calculerTVQ();

		return total;
	}

	public int calculerPointsBonis() {
		int total = 0;
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			int qte = item.getQuantite();
			Produit p = Inventaire.getProduit(item.getNomProduit());
			int points = p.getPoints();
			total += qte * points;
		}

		return total;
	}

}