/**
 *
 */
public class Team 
{
	private String name = "";
	private int roundsPlayed = 0;  
	private int goalsInFavor = 0;
	private int goalsAgainst = 0;
	private int goalsDifference = 0;
	private int roundsWon = 0;
	private int roundsTied = 0;
	private int roundsLost = 0;
	private int points = 0;
	
	public Team(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}
