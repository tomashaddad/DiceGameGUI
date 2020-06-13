package view.dice;

import java.awt.Point;

public class DotPositions
{
	/*
	 * (-1, 1)   (0,-1)   (1,-1)
	 * 
	 * (-1, 0)   (0, 0)   (1, 0)
	 * 
	 * (-1, 1)   (0, 1)   (1, 1)
	 * 
	 * */
	
	private static final Point TOP_LEFT = new Point(-1, -1);
	private static final Point TOP_RIGHT = new Point(1, -1);
	private static final Point LEFT = new Point(-1, 0);
	private static final Point CENTER = new Point(0, 0);
	private static final Point RIGHT = new Point(1, 0);
	private static final Point BOTTOM_LEFT = new Point(-1, 1);
	private static final Point BOTTOM_RIGHT = new Point(1, 1);
	
	private static final Point[] ONE = new Point[]
			{CENTER};
	
	private static final Point[] TWO = new Point[]
			{BOTTOM_LEFT, TOP_RIGHT};
	
	private static final Point[] THREE = new Point[]
			{BOTTOM_LEFT, CENTER, TOP_RIGHT};
	
	private static final Point[] FOUR = new Point[]
			{TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT};
	
	private static final Point[] FIVE = new Point[]
			{TOP_LEFT, TOP_RIGHT, CENTER, BOTTOM_LEFT, BOTTOM_RIGHT};
	
	private static final Point[] SIX = new Point[]
			{TOP_LEFT, TOP_RIGHT, LEFT, RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT};
	
	private static final Point[][] dicePoints = {ONE, TWO, THREE, FOUR, FIVE, SIX};
	
	public static Point[] getDotPositions(int dieValue)
	{
		return dicePoints[dieValue - 1];
	}
}
