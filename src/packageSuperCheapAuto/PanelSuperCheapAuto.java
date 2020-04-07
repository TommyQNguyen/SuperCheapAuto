package packageSuperCheapAuto;

import javax.swing.JPanel;

public class PanelSuperCheapAuto extends JPanel {

	private PanelCarteDeMembre panelCarteDeMembre;
	private PanelCommande panelCommande;
	private PanelBoutonsAchatTerminer panelBoutonsAchatTerminer;
	private PanelPaiement panelPaiement;


	public PanelSuperCheapAuto() {

		setSize(455, 548);
		setLayout(null);
	
		panelCarteDeMembre = new PanelCarteDeMembre();
		add(panelCarteDeMembre);
		
		panelCommande = new PanelCommande();
		add(panelCommande);
		
		panelBoutonsAchatTerminer = new PanelBoutonsAchatTerminer();
		add(panelBoutonsAchatTerminer);
		
		panelPaiement = new PanelPaiement();
		add(panelPaiement);
	}
}
