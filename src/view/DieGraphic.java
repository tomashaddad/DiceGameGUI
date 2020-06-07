package view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DieGraphic extends JPanel
{
	private int value;
	
	private final Color CASINO_GREEN = Color.decode("#35654d");
	
	// these values are arbitrary
	private final double BORDER_MULTIPLIER = 0.03;
	private final double ARC_MULTIPLIER = 0.3;
	private final double SIDELENGTH_MULTIPLIER = 0.9;
	private final double DOT_MULTIPLIER = 0.15;

	public DieGraphic(int value)
	{
		this.value = value;
		setBackground(CASINO_GREEN);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D die = (Graphics2D) g;
		die.setStroke(new BasicStroke(getBorderThickness()));
		
		// needed to make corner arcs smooth
		die.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		drawEmptyDie(die);
		drawDieDots(die);
	}

	// TODO: Achieving even padding? Inner padding is twice the outer padding ...
	
	private void drawEmptyDie(Graphics2D g)
	{
		/* The border's thickness is not accounted for in the graphic's margins, so the die's
		 * length must be shrunk by some multiplier in order to show all borders */
		int correctedSideLength = (int) (sideLength()*SIDELENGTH_MULTIPLIER);

		/* The graphic is centred according to its top-left corner.  */
		int correctedXPos = (getWidth() - correctedSideLength) / 2;
		int correctedYPos = (getHeight() - correctedSideLength) / 2;
		
		g.setColor(Color.WHITE);
		
		g.fillRoundRect(correctedXPos, correctedYPos,
				correctedSideLength, correctedSideLength,
				getCornerArc(), getCornerArc());

		g.setColor(Color.BLACK);
		
		g.drawRoundRect(correctedXPos, correctedYPos,
				correctedSideLength, correctedSideLength,
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

	/* Sets the template die side length to the minimum side length of its container */
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
