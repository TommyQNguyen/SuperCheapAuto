package packageSuperCheapAuto;

public class Item {
	private String nomProduit;
	private int quantite;
	private int numeroCommande;

	public Item(String nomProduit, int quantite, int numeroCommande) {
		this.nomProduit = nomProduit;
		this.quantite = quantite;
		this.numeroCommande = numeroCommande;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public int getNumeroCommandeAssociee() {
		return numeroCommande;
	}

	public Produit getProduit() {
		return Inventaire.getProduit(nomProduit);
	}

	public String toString() {
		return this.nomProduit + "\r" + this.quantite + "\r" + getProduit().getPrix() * this.quantite;
	}
	


}