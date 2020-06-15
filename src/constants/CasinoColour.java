package constants;

import java.awt.Color;

public class CasinoColour
{
	private CasinoColour()
	{
		throw new IllegalStateException("Utility class");
	}
	
	public static final Color CASINO_GREEN = Color.decode("#35654d");
	public static final Color CASINO_RED = Color.decode("#972a27");
	public static final Color HOUSE_SELECT = Color.decode("#e8dcdc");
	public static final Color PLAYER_SELECT = Color.decode("#e6ebe6");
}
