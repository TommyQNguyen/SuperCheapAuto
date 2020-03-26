package packageSuperCheapAuto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PanelCarteDeMembre extends JPanel 
{
	private JLabel lblNumeroCarteMembre;
	private JLabel lblNomDuClient;
	private JLabel lblNombreDePointsBonis;
	private JTextField textField_numeroCarteMembre;
	private JTextField textField_nomDuClient;
	private JTextField textField_nombrePointsBonis;
	private static final String ACTION_ENTER = "entrer";

	public PanelCarteDeMembre() 
	{
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBackground(Color.GREEN);
		setBounds(10, 10, 435, 114);
		
		lblNumeroCarteMembre = new JLabel("Numero de la carte de membre: ");
		lblNumeroCarteMembre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroCarteMembre.setBounds(10, 1, 216, 37);
		add(lblNumeroCarteMembre);
		
		textField_numeroCarteMembre = new JTextField();
		textField_numeroCarteMembre.setBounds(329, 10, 96, 19);
		add(textField_numeroCarteMembre);
		textField_numeroCarteMembre.setColumns(10);
		
		lblNomDuClient = new JLabel("Nom du client:");
		lblNomDuClient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNomDuClient.setBounds(10, 48, 121, 19);
		add(lblNomDuClient);
		
		textField_nomDuClient = new JTextField();
		textField_nomDuClient.setBounds(329, 49, 96, 19);
		add(textField_nomDuClient);
		textField_nomDuClient.setColumns(10);
		textField_nomDuClient.setEditable(false);
		
		lblNombreDePointsBonis = new JLabel("Nombre de points bonis a ce jour: ");
		lblNombreDePointsBonis.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreDePointsBonis.setBounds(10, 85, 239, 19);
		add(lblNombreDePointsBonis);
		
		textField_nombrePointsBonis = new JTextField();
		textField_nombrePointsBonis.setBounds(329, 86, 96, 19);
		add(textField_nombrePointsBonis);
		textField_nombrePointsBonis.setColumns(10);
		textField_nombrePointsBonis.setEditable(false);
		
		
		
				
		// Lire des données provenant du fichier Excel  
        // Créer un flux de lecture
        try
        {
        InputStream inputStream = new FileInputStream ( "Clients.xlsx");
        XSSFWorkbook classeur = ( XSSFWorkbook ) WorkbookFactory.create(inputStream);
        XSSFSheet feuille = classeur.getSheetAt(0); 				// Feuille commence a 0, alors chercher Feuille 1 
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
        	//System.out.println(cellule);
        	
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

//        	decimalFormat.format(soldeCarteCredit);
//        	System.out.println(decimalFormat.format(0.0));
        	        	
        	Clients.ajouterClient(new Client(numeroClient, nomClient, nombrePointsBonis, soldeCarteCredit));
        	
//        	System.out.println(Clients.getListe().get(numeroClient).getSoldeCarteCredit());
        	
        	
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
        
        
		// KeyStroke avec KeyEvent et une classe AbstractAction
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK), ACTION_ENTER);
		getActionMap().put(ACTION_ENTER, new Ecouteur(ACTION_ENTER));
       
		textField_numeroCarteMembre.addActionListener(new Ecouteur(ACTION_ENTER));
      }
	
	private class Ecouteur extends AbstractAction {
		private String entrer;

		public Ecouteur(String entrer) {
			this.entrer = entrer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			setBackground(Color.PINK);
			
			String cleNumeroCarteMembre = textField_numeroCarteMembre.getText();
			
			if (Clients.getListe().containsKey(cleNumeroCarteMembre)) {
			textField_nomDuClient.setText(Clients.getListe().get(cleNumeroCarteMembre).getNom());			
			textField_nombrePointsBonis.setText(String.valueOf(Clients.getListe().get(cleNumeroCarteMembre).getPointsBonis()));
			}
			else if (Clients.getListe().containsKey(cleNumeroCarteMembre) != true) {
				JOptionPane.showMessageDialog(null, "Ce numero n'existe pas.");
				textField_numeroCarteMembre.setText("");
			}			
		}
	}
	
	public JTextField getTextfieldNumeroCarteMembre () {
		return textField_numeroCarteMembre;
	}
	
	public JTextField getTextfieldNomDuClient () {
		return textField_nomDuClient;
	}
	
	public JTextField getTextfieldNombrePointsBonis () {
		return textField_nombrePointsBonis;
	}
	
	
	}
	
	

