package packageSuperCheapAuto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Clients {
	
	private static HashMap<String, Client> listeClients = new HashMap<String, Client>();
	
	public Clients() {

		initFeuilleExcelClient();
	}
	
	public static void initFeuilleExcelClient () {
        try
        {
        InputStream inputStream = new FileInputStream ( "Clients.xlsx");
        XSSFWorkbook classeur = ( XSSFWorkbook ) WorkbookFactory.create(inputStream);
        XSSFSheet feuille = classeur.getSheetAt(0); 				// Feuille commence a 0, alors chercher Feuille 0 
        XSSFRow rangee = feuille.getRow(1); 						// Valeur rangee = Premier rangee de la feuille
        XSSFCell cellule  = rangee.getCell(0); 						// Valeur cellule = chercher la premiere cellule de la valeur rangee
        
        int nombreDeClients = feuille.getPhysicalNumberOfRows() - 1;//Returns the number of physically defined rows (NOT the number of rows in the sheet)
        															//Moins 1 pour ne pas compter la rangee du titre dans le fichier Excel
        
        for (int i = 1; i < nombreDeClients; i++) {
			        	
        	rangee = feuille.getRow(i);
        	cellule = rangee.getCell(0);
 
        	//Conversion de cellule type NUMERIC a STRING
        	//https://stackoverflow.com/questions/39993683/alternative-to-deprecated-getcelltype
        	cellule.setCellType(CellType.STRING);
        	
        	//Conversion de type XSSF Cell a String
        	DataFormatter dataFormatter = new DataFormatter();
        	String numeroClient = dataFormatter.formatCellValue(cellule);
        	String nomClient = dataFormatter.formatCellValue(rangee.getCell(1));
        	
        	//Conversion de XSSFCell avec la methode statique parseInt
        	int nombrePointsBonis = Integer.parseInt(dataFormatter.formatCellValue(rangee.getCell(2)));
        	
        	//Conversion de XSSFCell avec la methode statique parseDouble
        	
//        	DecimalFormat decimalFormat = new DecimalFormat("#.000000000");
//        	System.out.println(numberFormat.format(number));
        	double soldeCarteCredit = Double.parseDouble(dataFormatter.formatCellValue(rangee.getCell(3)));
        	
        	DecimalFormat decimalFormat = new DecimalFormat("#.000000000");
        	        	
        	Clients.ajouterClient(new Client(numeroClient, nomClient, nombrePointsBonis, soldeCarteCredit));
        	

        	
		}
               
        // utiliser un flux d'écriture pour enregistrer les changements 
        OutputStream out = new FileOutputStream ( "Clients.xlsx");
        classeur.write(out);  
        out.close();
        inputStream.close();
          
        }
        catch ( Exception f)
        {
          f.printStackTrace();
        }
	}
	
	public static void sauvegarderFeuilleExcel() {
		
        try
        {
        InputStream inputStream = new FileInputStream ( "Clients.xlsx");
        XSSFWorkbook classeur = ( XSSFWorkbook ) WorkbookFactory.create(inputStream);
        XSSFSheet feuille = classeur.getSheetAt(0); 				// Feuille commence a 0, alors chercher Feuille 0 
        
        int nombreDeClients = feuille.getPhysicalNumberOfRows() - 1;//Returns the number of physically defined rows (NOT the number of rows in the sheet)
        															//Moins 1 pour ne pas compter la rangee du titre dans le fichier Excel
        
        for (int i = 1; i < listeClients.size(); i++) {
        	
			XSSFRow nouvelleRangee = feuille.getRow(i);
			XSSFCell nouvelleCelluleNumeroCarteMembre = nouvelleRangee.getCell(0);
			XSSFCell nouvelleCelluleNomClient = nouvelleRangee.getCell(1);
			XSSFCell nouvelleCellulePointsBonis = nouvelleRangee.getCell(2);
			XSSFCell nouvelleCelluleSoldeCredit = nouvelleRangee.getCell(3);
			
//			nouvelleCelluleNumeroCarteMembre.setCellValue(liste);
 
//        	//Conversion de cellule type NUMERIC a STRING
//        	//https://stackoverflow.com/questions/39993683/alternative-to-deprecated-getcelltype
////        	cellule.setCellType(CellType.STRING);
//        	
//        	//Conversion de type XSSF Cell a String
//        	DataFormatter dataFormatter = new DataFormatter();
//        	String numeroClient = dataFormatter.formatCellValue(cellule);
//        	String nomClient = dataFormatter.formatCellValue(rangee.getCell(1));
//        	
//        	//Conversion de XSSFCell avec la methode statique parseInt
//        	int nombrePointsBonis = Integer.parseInt(dataFormatter.formatCellValue(rangee.getCell(2)));
//        	
//        	//Conversion de XSSFCell avec la methode statique parseDouble
//        	
////        	DecimalFormat decimalFormat = new DecimalFormat("#.000000000");
////        	System.out.println(numberFormat.format(number));
//        	double soldeCarteCredit = Double.parseDouble(dataFormatter.formatCellValue(rangee.getCell(3)));
//        	
//        	DecimalFormat decimalFormat = new DecimalFormat("#.000000000");
//        	        	
//        	Clients.ajouterClient(new Client(numeroClient, nomClient, nombrePointsBonis, soldeCarteCredit));
//        	

        	
		}
               
        // utiliser un flux d'écriture pour enregistrer les changements 
        OutputStream out = new FileOutputStream ( "Clients.xlsx");
        classeur.write(out);  
        out.close();
        inputStream.close();
          
        }
        catch ( Exception f)
        {
          f.printStackTrace();
        }
	}

	public static HashMap<String, Client> getListe() {
		return listeClients;
	}

	public static Client getClient(String numeroClient) {
		return listeClients.get(numeroClient);
	}

	public static void ajouterClient(Client client) {
		listeClients.put(client.getNumeroClient(), client);
	}

}