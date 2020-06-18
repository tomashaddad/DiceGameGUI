package constants;

/* Utility class to hold event names */

public final class Events
{
	private Events() { } // prevent instantiation
	
	public static final String PLAYER_ADDED			= "Player added";
	public static final String PLAYER_REMOVED		= "Player removed";
	public static final String BET_SET				= "Bet set";
	public static final String BET_FAILED 			= "Bet failed";
	public static final String BET_RESET			= "Bet reset";
	public static final String PLAYER_SELECTED		= "Player selected";
	public static final String HOUSE_SELECTED		= "House selected";
	public static final String ROLL_FAILED 			= "Roll failed";
	public static final String PLAYER_DIE_UPDATED  	= "Player die updated";
	public static final String HOUSE_DIE_UPDATED  	= "House die updated";
	public static final String PLAYER_ROLLING 		= "Player rolling";
	public static final String PLAYER_ROLLED 		= "Player rolled";
	public static final String HOUSE_ROLLING 		= "House rolling";
	public static final String HOUSE_ROLLED 		= "House rolled";
}
