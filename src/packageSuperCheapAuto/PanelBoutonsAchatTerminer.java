package packageSuperCheapAuto;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelBoutonsAchatTerminer extends JPanel {

	private JButton btnAchat;
	private JButton btnTerminer;

	public PanelBoutonsAchatTerminer() 
	{
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBounds(289, 137, 156, 136);
		setLayout(null);
		
		btnAchat = new JButton("Achat");
		btnAchat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAchat.setBounds(10, 10, 136, 59);
		add(btnAchat);
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTerminer.setBounds(10, 69, 136, 57);
		add(btnTerminer);
	}
}
