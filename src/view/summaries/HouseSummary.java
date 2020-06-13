package view.summaries;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import view.dice.DieGraphic;

@SuppressWarnings("serial")
public class HouseSummary extends JPanel
{
	public HouseSummary()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(10, 5, 10, 5), // margin
					BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(new LineBorder(DieGraphic.CASINO_RED, 5),
								"HOUSE", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)), // name and border
						BorderFactory.createEmptyBorder(10, 10, 10, 10) // inner
					)
				)
			);

		setBackground(Color.WHITE);
		setFocusable(true);
	}
}