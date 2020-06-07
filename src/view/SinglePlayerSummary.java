package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SinglePlayerSummary extends JPanel
{
	public SinglePlayerSummary(String name, int points, int bet)
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(new LineBorder(Color.decode("#415e4a"), 5), name, TitledBorder.LEFT,
						TitledBorder.TOP, new Font("Arial", Font.ITALIC, 16)),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		setBackground(Color.WHITE);
		setFocusable(true);

		add(new JLabel("Points: " + points));
		add(new JLabel("Bettings: " + bet));
	}
}
