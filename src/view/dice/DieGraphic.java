package view.dice;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DieGraphic extends JPanel
{
	private int value;
	
	public static final Color CASINO_GREEN = Color.decode("#35654d");
	public static final Color CASINO_RED = Color.decode("#972a27");
	
	// these values are arbitrary
	private static final double BORDER_MULTIPLIER = 0.03;
	private static final double ARC_MULTIPLIER = 0.3;
	private static final double DOT_MULTIPLIER = 0.15;
	private static final double SCALING_MULTIPLIER = 0.9;

	public DieGraphic(int value, Color colour)
	{
		this.value = value;
		setBackground(colour);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D die = (Graphics2D) g;
		die.setStroke(new BasicStroke(getBorderThickness()));
		
		// needed to make corner arcs smooth
		die.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		double translation = sideLength()*(1-SCALING_MULTIPLIER)/2; // leftover pixels after scaling /2
		die.translate(translation, translation);
		die.scale(SCALING_MULTIPLIER, SCALING_MULTIPLIER);
		
		drawEmptyDie(die);
		drawDieDots(die);
	}

	private void drawEmptyDie(Graphics2D g)
	{
		// The graphic is centred according to its top-left corner.
		
		int correctedXPos = (getWidth() - sideLength()) / 2;
		int correctedYPos = (getHeight() - sideLength()) / 2;
		
		// Necessary to draw white background
		
		g.setColor(Color.WHITE);
		
		g.fillRoundRect(correctedXPos, correctedYPos,
				sideLength(), sideLength(),
				getCornerArc(), getCornerArc());

		g.setColor(Color.BLACK);
		
		g.drawRoundRect(correctedXPos, correctedYPos,
				sideLength(), sideLength(),
				getCornerArc(), getCornerArc());
	}
	
	private void drawDieDots(Graphics2D g)
	{
		Point[] dots = DotPositions.getDotPositions(value);
		 
		/* Dividing the die side length by four (dieGridSize) splits the die into a 4x4
		 * square. The intersections of those squares form a 3x3 grid where the dots are
		 * placed. Since graphics are drawn with the top left as the origin, the dots
		 * need to be translated by their radius to centre them at their respective positions. */
		
		int dieGridSize = sideLength() / 4;
		int dotRadius = getDotSize() / 2;
		int centreX = getWidth() / 2;
		int centreY = getHeight() / 2;
		
		for (Point dot : dots)
		{	
			int dx = (int) (dot.getX()*dieGridSize - dotRadius);
			int dy = (int) (dot.getY()*dieGridSize - dotRadius);
			g.fillOval(centreX + dx, centreY + dy, getDotSize(), getDotSize());
		}
	}

	/* Sets the template die side length to the minimum side length of its container
	 * All dice dimensional attributes (below) are dependent on this! */
	private int sideLength()
	{
		return getWidth() <= getHeight() ? getWidth() : getHeight();
	}

	private int getBorderThickness()
	{
		return (int) (sideLength() * BORDER_MULTIPLIER);
	}

	private int getCornerArc()
	{
		return (int) (sideLength() * ARC_MULTIPLIER);
	}
	
	private int getDotSize()
	{
		return (int) (sideLength() * DOT_MULTIPLIER);
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
}
