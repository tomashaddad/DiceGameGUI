package view.summaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/* Card border creation is needed in both DiceSummaryPanel (for house cards) and in the JList player
 * renderer class */

public final class CardBorder
{
	private CardBorder() { } // prevent instantiation
	
	public static Border createBorder(String title, Color colour, int justification, Font font)
	{
		Border margin = BorderFactory.createEmptyBorder(10, 5, 10, 5);
		Border name = BorderFactory.createTitledBorder(new LineBorder(colour, 5),
					  title, justification, TitledBorder.TOP, font);
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		return BorderFactory.createCompoundBorder(margin,
			   BorderFactory.createCompoundBorder(name, padding));
	}
}
