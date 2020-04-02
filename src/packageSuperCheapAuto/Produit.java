package packageSuperCheapAuto;

public class Produit {

	private int code;
	private String nom;
	private int quantiteStock;
	private double prix;
	private int points;

	public Produit(int code, String nom, int quantiteStock, double prix, int points) {
		this.code = code;
		this.quantiteStock = quantiteStock;
		this.prix = prix;
		this.points = points;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public int getCode() {
		return code;
	}

	public int getQteStock() {
		return quantiteStock;
	}

	public double getPrix() {
		return prix;
	}

	public int getPoints() {
		return points;
	}

	public boolean modifierQuantiteStock(int quantiteAchetee) {
		if (quantiteAchetee <= quantiteStock) {
			this.quantiteStock -= quantiteAchetee;
			return true;
		}
		
		return false;
	}

//	public void reduireQuantiteEnStock() {
//		quantiteStock = quantiteStock - 1;
//	}
}