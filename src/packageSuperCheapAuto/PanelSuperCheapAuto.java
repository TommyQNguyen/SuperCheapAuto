package packageSuperCheapAuto;

import java.util.Random;

import javax.swing.JPanel;

public class PanelSuperCheapAuto extends JPanel {

	private PanelCarteDeMembre panelCarteDeMembre;
	private PanelCommande panelCommande;
	private PanelBoutonsAchatTerminer panelBoutonsAchatTerminer;


	/**
	 * Create the panel.
	 */
	public PanelSuperCheapAuto() {

		setSize(455, 551);
		setLayout(null);
	
		panelCarteDeMembre = new PanelCarteDeMembre();
		add(panelCarteDeMembre);
		
		panelCommande = new PanelCommande();
		add(panelCommande);
		
		panelBoutonsAchatTerminer = new PanelBoutonsAchatTerminer();
		add(panelBoutonsAchatTerminer);
		
		// initialize a Random object somewhere; you should only need one
		Random random = new Random();

		// generate a random integer from 0 to 899, then add 100
		int x = random.nextInt(899999) + 100000;
		System.out.println(x);
		
		

		
	}
}
