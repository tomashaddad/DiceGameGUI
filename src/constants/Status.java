package constants;

public final class Status
{
	private Status()
	{
		throw new IllegalStateException("Utility class");
	}

	public static final String NEW_PLAYER 	= "New";
	public static final String ROLLING 		= "Rolling";
	public static final String ROLLED 		= "Rolled";
}
