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
	private int roundsPlayed = 0;
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

	/**
	 * get the total goals made by the current team
	 * @return total goals
	 */
	public int getGoalsInFavor()
	{
		return this.goalsInFavor;
	}
	/**
	 * Get the total goals made by the rivals team 
	 * @return goals made by the rivals
	 */
	public int getGoalsAgainst()
	{
		return this.goalsAgainst;
	}
	/**
	 * Get the total points of the current team
	 * @return points
	 */
	public int getPoints()
	{
		return this.points;
	}
	/**
	 * Gets all the rounds won
	 * @return round won by the team
	 */
	public int getRoundsWon()
	{
		return this.roundsWon;
	}
	/**
	 * Gets all the rounds tied
	 * @return rounds tied
	 */
	public int getRoundsTied()
	{
		return this.roundsTied;
	}
	/**
	 * Gets all the rounds lost
	 * @return rounds lost
	 */
	public int getRoundsLost()
	{
		return this.roundsLost;
	}
	/**
	 * Gets the goal difference
	 * @return goals difference
	 */
	public int getGoalsDifference()
	{
		return this.goalsDifference;
	}
	/**
	 * Total amount of games played by the team
	 * @return games played
	 */
	public int getRoundsPlayed() 
	{
		return this.roundsPlayed;
	}
	/**
	 * Calculates the goals made by the team and the rival, the winner and the loser
	 * @param homeGoals made by the current team
	 * @param visitGoals made by the current team rival
	 */
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
	/**
	 * calculates the final teams statistics
	 */
	public void calculateStatistics()
	{
		this.goalsDifference = this.goalsInFavor - this.goalsAgainst;
		this.points = this.roundsWon * 3 + this.roundsTied;
		this.roundsPlayed = this.roundsWon + this.roundsLost + this.roundsTied;
	}

}
