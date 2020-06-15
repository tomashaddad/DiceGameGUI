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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import constants.CasinoColour;

@SuppressWarnings("serial")
public class JListPlayerRenderer extends JPanel implements ListCellRenderer<PlayerListItem>
{
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
		String title = item.getPlayer().getPlayerName();
		int points = item.getPlayer().getPoints();
		int bet = item.getPlayer().getBet();
		int winnings = item.getWinnings();
		
		pointsLabel.setText("Points: " + points);
		betLabel.setText("Bet: " + bet);
		winningsLabel.setText("Win/loss last round: " + winnings);
		
		Border playerCardBorder = CardBorder.createBorder(
				title, CasinoColour.CASINO_GREEN, TitledBorder.LEFT,
				new Font("Arial", Font.BOLD + Font.ITALIC, 16));
		
		setBorder(playerCardBorder);
		setBackground(isSelected ? CasinoColour.PLAYER_SELECT : Color.WHITE);

		return this;
	}
}
