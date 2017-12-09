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
	public int getGoalsInFavor()
	{
		return this.goalsInFavor;
	}
	public int getGoalsAgainst()
	{
		return this.goalsAgainst;
	}
	public int getGoalsDifference()
	{
		return this.goalsDifference;
	}
	public int getRoundsWon()
	{
		return this.roundsWon;
	}
	public int getRoundsLost()
	{
		return this.roundsLost;
	}
	public int getRoundsTied()
	{
		return this.roundsTied;
	}
	public void addResults(int goalsMaked, int goalsReceived)
	{
		this.goalsInFavor += goalsMaked;
		this.goalsAgainst += goalsReceived;
		this.setMatchWinner(goalsMaked, goalsReceived);
	}
	public void setMatchWinner(int goalsMaked, int goalsReceived)
	{
		if (goalsMaked > goalsReceived) {
			this.roundsWon += 1;
		} else if (goalsMaked < goalsReceived) {
			this.roundsLost += 1;
		}else {
			this.roundsTied += 1;
		}
	}
	public void setPoints()
	{
		this.points = this.goalsInFavor * 3 + this.roundsTied;
	}
	public void setGoalsDifference()
	{
		this.goalsDifference = this.goalsInFavor - this.goalsAgainst;
	}
	public void setRoundsPlayed(int totalTeams)
	{
		this.roundsPlayed = (totalTeams - 1) * 2;
	}
}
