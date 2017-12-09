/**
 * A football team
 */
public class Team 
{
	// Attributes of a team in a tournament
	private String name = "";
	
	private int goalsInFavor = 0;
	private int goalsAgainst = 0;
	private int goalsDifference = 0;
	private int roundsWon = 0;
	private int roundsTied = 0;
	private int roundsLost = 0;
	private int points = 0;
	
	/**
	 * Creates a team
	 * @param name the name of the team
	 */
	public Team(String name)
	{
		this.name = name;
	}
	/**
	 * Gets the selected team name
	 * @return the team's name
	 */
	public String getName()
	{
		return this.name;
	}
	
	public int getGoalsInFavor()
	{
		return this.goalsInFavor;
	}
	
	public int getGoalsAgainst()
	{
		return this.goalsAgainst;
	}
	
	public int getPoints()
	{
		return this.points;
	}
	
	public int getRoundsWon()
	{
		return this.roundsWon;
	}
	
	public int getRoundsTied()
	{
		return this.roundsTied;
	}
	
	public int getRoundsLost()
	{
		return this.roundsLost;
	}

	public void addGoals(int homeGoals, int visitGoals)
	{
		this.goalsInFavor += homeGoals;
		this.goalsAgainst += visitGoals;
		
		if (homeGoals > visitGoals)
			++roundsWon;
		else if (homeGoals == visitGoals)
			++roundsTied;
		else
			++roundsLost;
	}
	
	public void calculateStatistics()
	{
		this.goalsDifference = this.goalsInFavor - this.goalsAgainst;
		this.points = this.roundsWon * 3 + this.roundsTied;
	}

}
