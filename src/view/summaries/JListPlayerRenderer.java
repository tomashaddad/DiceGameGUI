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

import model.PlayerListItem;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class JListPlayerRenderer extends JPanel implements ListCellRenderer<PlayerListItem>
{
	private String name;
	private JLabel pointsLabel = new JLabel("");
	private JLabel betLabel = new JLabel("");
	private JLabel winningsLabel = new JLabel("");
	
	public JListPlayerRenderer()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBackground(Color.WHITE);
		setFocusable(true);

		add(pointsLabel);
		add(betLabel);
		add(winningsLabel);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends PlayerListItem> list, PlayerListItem item, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		name = item.getPlayer().getPlayerName();
		int points = item.getPlayer().getPoints();
		int bet = item.getPlayer().getBet();
		int winnings = item.getWinnings();
		
		pointsLabel.setText("Points: " + points);
		betLabel.setText("Bet: " + bet);
		winningsLabel.setText("Win/loss last round: " + winnings);
		
		if (isSelected)
			setBackground(Color.decode("#e6ebe6"));
		else
			setBackground(Color.WHITE);
		
		setBorderColour(Color.decode("#415e4a"));

		setEnabled(list.isEnabled());
		setFont(list.getFont());		
		setOpaque(true);
		return this;		
	}
	
	public void setBorderColour(Color colour)
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
