package constants;

public final class Events
{
	private Events()
	{
		throw new IllegalStateException("Utility class");
	}
	
	public static final String PLAYER_ADDED			= "Player added";
	public static final String PLAYER_REMOVED		= "Player removed";
	public static final String PLAYER_SELECTED		= "Player selected";
	public static final String HOUSE_SELECTED		= "House selected";
	public static final String PLAYER_DIE_UPDATED  	= "Player die updated";
	public static final String HOUSE_DIE_UPDATED  	= "House die updated";
	public static final String PLAYER_ROLLING 		= "Player rolling";
	public static final String PLAYER_ROLLED 		= "Player rolled";
	public static final String HOUSE_ROLLING 		= "House rolling";
	public static final String HOUSE_ROLLED 		= "House rolled";
}
