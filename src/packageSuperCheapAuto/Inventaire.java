package packageSuperCheapAuto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Inventaire {
	
	private static HashMap<String, Produit> listeProduits = new HashMap<String, Produit>();
	
	public Inventaire() {
		
		try {
			
		InputStream inp = new FileInputStream ( "Produits.xlsx");
		XSSFWorkbook classeur = ( XSSFWorkbook ) WorkbookFactory.create(inp);
		XSSFSheet feuille = classeur.getSheetAt(0); 			// Feuille commence a 0, alors chercher Feuille 1 

		int codeDuProduit;
		String nomDuProduit;
		int quantiteEnStock;
		double coutDuProduit;
		int nbDePointsDuProduit;

        DataFormatter dataFormatter = new DataFormatter();
			   
        int nombreDeProduits = feuille.getPhysicalNumberOfRows();	//Returns the number of physically defined rows (NOT the number of rows in the sheet)
        															//Moins 1 pour ne pas compter la rangee du titre dans le fichier Excel
        for (int i = 1; i < nombreDeProduits; i++) {
        	XSSFRow rangee = feuille.getRow(i); 					// Commence a la deuxieme rangee de la feuille pour exclure le titre
        	XSSFCell celluleCodeDuProduit  = rangee.getCell(0); 	// Valeur cellule = chercher la premiere cellule de la valeur rangee
        	XSSFCell celluleNomDuProduit = rangee.getCell(1); 
        	XSSFCell celluleQteEnStock = rangee.getCell(2); 
        	XSSFCell celluleCoutDuProduit = rangee.getCell(3); 
        	XSSFCell celluleNbDePointsProduit = rangee.getCell(4); 

        	
        	codeDuProduit = Integer.parseInt(dataFormatter.formatCellValue(celluleCodeDuProduit));	//Conversion de XSSFCell avec static parseInt()

        	nomDuProduit = dataFormatter.formatCellValue(celluleNomDuProduit);						//Conversion de type XSSF Cell a String

        	quantiteEnStock = Integer.parseInt(dataFormatter.formatCellValue(celluleQteEnStock));

//        	DecimalFormat decimalFormat = new DecimalFormat("#.000000000");
//        	System.out.println(numberFormat.format(number));
        	coutDuProduit = Double.parseDouble(dataFormatter.formatCellValue(celluleCoutDuProduit));

        	nbDePointsDuProduit = Integer.parseInt(dataFormatter.formatCellValue(celluleNbDePointsProduit));

        	Inventaire.ajouterProduit(new Produit(codeDuProduit, nomDuProduit, quantiteEnStock, coutDuProduit, nbDePointsDuProduit));
        }
        
        // Utiliser un flux d'�criture pour enregistrer les changements 
        OutputStream out = new FileOutputStream ( "Produits.xlsx");
        classeur.write(out);  
        out.close();
        inp.close();
		}
		
		catch ( Exception f)
		{
			f.printStackTrace();
		}
	}

	public static HashMap<String, Produit> getListe() {
		return listeProduits;
	}

	public static Produit getProduit(String nom) {
		return listeProduits.get(nom);
	}

	public static void ajouterProduit(Produit produit) {
		listeProduits.put(produit.getNom(), produit);		// Prend le nom du produit pour en faire la cle
	}

}