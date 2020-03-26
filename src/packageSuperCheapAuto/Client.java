package packageSuperCheapAuto;

public class Client {

	private String numeroClient;
	private String nom;
	private int pointsBonis;
	private double soldeCarteCredit;

	public Client(String numeroClient, String nom, int pointsBonis, double soldeCarteCredit) {
		this.numeroClient = numeroClient;
		this.pointsBonis = pointsBonis;
		this.nom = nom;
		this.soldeCarteCredit = soldeCarteCredit;
	}

	public String getNumeroClient() {
		return numeroClient;
	}

	public String getNom() {
		return nom;
	}

	public int getPointsBonis() {
		return pointsBonis;
	}
	
	public void ajouterPointsBonis(int pointsBonis) {
		this.pointsBonis += pointsBonis;
	}
	
	public double getSoldeCarteCredit() {
		return soldeCarteCredit;
	}

	public void setSoldeCarteCredit(double soldeCarteCredit) {
		this.soldeCarteCredit = soldeCarteCredit;
	}

	public boolean assezArgent(Commande commande, double montant) {
		double total = commande.calculerGrandTotal();
		if (montant >= total)
			return true;

		return false;
	}

	public double payerCommandeComptant(Commande commande, double montant) {
		double total = commande.calculerGrandTotal();
		System.out.println(total);

		double change = montant - total;
		int cents = 0;
		System.out.println(change);

		// multiplie par 100 pour avoir cents
		cents = arrondirCent(change);
		System.out.println(cents);

		int reste = cents % 5;
		System.out.println(reste);

		if (reste >= 3)
			cents = cents + (5 - reste);
		else
			cents = cents - reste;

		System.out.println(cents);

		// remettre en dollars
		change = cents / 100.0;
		System.out.println(change);
		int nbPoints = commande.calculerPointsBonis();
		if (change > 0) {
			ajouterPointsBonis(nbPoints);
			commande.setPayee(true);
		}

		return change;
	}

	public boolean payerCommandeCarteCredit(Commande commande) {
		double total = commande.calculerGrandTotal();
		System.out.println(total);
		
		// Permettre un solde maximum de 2000$ sur la carte de crédit
		if (soldeCarteCredit + total <= 2000) {
			int totalEnCents = arrondirCent(total);
			
			// remettre en dollars
			soldeCarteCredit += totalEnCents / 100.0;
			
			int pointsBonis = commande.calculerPointsBonis();
			ajouterPointsBonis(pointsBonis);
			commande.setPayee(true);
			
			return true;
		}
		
		return false;
	}


	public static int arrondirCent(double montant) {
		double montantEnCentsAvecPoussieres = montant * 100;
		int montantEnCents = (int) Math.round(montantEnCentsAvecPoussieres);
		return montantEnCents;
	}
	
}