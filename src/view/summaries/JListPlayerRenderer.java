package view.summaries;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class JListPlayerRenderer extends JPanel implements ListCellRenderer<Player>
{
	private String name = "Placeholder";
	
	private JLabel points = new JLabel("");
	private JLabel bet = new JLabel("");
	
	public JListPlayerRenderer()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBackground(Color.WHITE);
		setFocusable(true);

		add(points);
		add(bet);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player player, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		name = player.getPlayerName();
		points.setText("Points: " + Integer.toString(player.getPoints()));
		bet.setText("Bet: " + Integer.toString(player.getBet()));
		
		if (isSelected)
			setBackground(Color.decode("#e6ebe6"));
		else
			setBackground(Color.WHITE);
		
		
		if (isSelected && cellHasFocus)
			setBorderColour(Color.decode("#415e4a"));
		else
			setBorderColour(Color.decode("#415e4a"));
		
		setEnabled(list.isEnabled());
		setFont(list.getFont());		
		setOpaque(true);
		return this;		
	}
	
	private void setBorderColour(Color colour)
	{
		Font titleFont = new Font("Arial", Font.ITALIC, 16);
		
		setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 5, 10, 5), // margin
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(new LineBorder(colour, 5),
							name, TitledBorder.LEFT, TitledBorder.TOP, titleFont), // name and border
					BorderFactory.createEmptyBorder(10, 10, 10, 10) // inner
				)
			)
		);
	}
}
