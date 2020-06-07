package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PlayerCard extends JPanel
{
	String name;
	String initialPoints;
	
	public PlayerCard(String name, String initialPoints)
	{
		this.name = name;
		this.initialPoints = initialPoints;
		
		JPanel entry = new JPanel();
		Border titleBorder  = BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 5), name);
		Border marginBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		entry.setBorder(BorderFactory.createCompoundBorder(titleBorder, marginBorder));
		entry.setBackground(Color.WHITE);
		entry.setFocusable(true);
		entry.setPreferredSize(new Dimension(0, 200));
		
	}
}
