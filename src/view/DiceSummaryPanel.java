package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class DiceSummaryPanel extends JPanel
{
	public DiceSummaryPanel(GameEngine model)
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.decode("#88949e"));
		
		JLabel label = new JLabel("PLAYER SUMMARY");
		label.setOpaque(true);
		label.setBackground(Color.decode("#4C5760"));
		label.setForeground(Color.WHITE);
		label.setBorder(BorderFactory.createLineBorder(Color.decode("#4C5760"), 5));
		
		add(label);
	}
}
