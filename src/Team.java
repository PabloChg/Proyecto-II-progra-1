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
	
	private String matches[] ;

	private String teamNames[];

	private Team [] teams;
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

	public void setMatches(String [] matches) 
	{
		this.matches = matches;
	}
	/**
	 * 
	 */
	public void setTeamNames(String [] teams)
	{
		this.teamNames = teams;
		this.setTeams();
	}
	public void setTeams() {
		
		this.teams = new Team[this.teamNames.length];

		// Create a team with each name read from the file
		for (int index = 0; index < this.teamNames.length; ++index )
		{
			Team team = new Team(this.teamNames[index]);

			teams[index] = team;
		}
	}
	/**
	 * 
	 */
	public void matchesResults() 
	{
		for (int index = 0; index < this.matches.length; index++) {
			String [] match = matches[index].split(",");
			this.matchAnalyzer(match, 1);
			this.matchAnalyzer(match, 3);
			matchConclusions(this.matchAnalyzer(match, 1), this.matchAnalyzer(match, 3), match);
		}
		this.setPoints();
	}
	/**
	 * 
	 */
	public int matchAnalyzer(String [] singleMatch, int namePosition)
	{
		int teamsPosition = 0;
		for (int index = 0; index < this.teamNames.length; index++) {
			if (singleMatch[namePosition].equals(this.teamNames[index])) {
				teamsPosition = index;
			}
		}
		return teamsPosition;
	}
	/**
	 * 
	 */
	public void matchConclusions(int homeTeam, int visitTeam, String[] match)
	{
		int homeGoals = Integer.parseInt(match[2]);
		int visitGoals = Integer.parseInt(match[4]);

		this.teams[homeTeam].goalsInFavor += homeGoals;
		this.teams[visitTeam].goalsInFavor += visitGoals;

		this.teams[homeTeam].goalsAgainst += visitGoals;
		this.teams[visitTeam].goalsAgainst += homeGoals;

		System.out.println(this.teams[homeTeam].name +"vs" + this.teams[visitTeam].name);

		if (homeGoals == visitGoals) {
			this.setRoundsTied(homeTeam, visitTeam);
		}else if (homeGoals > visitGoals){
			this.setRoundsTied(homeTeam, visitTeam);
		} else {
			this.setRoundsTied(visitTeam, homeTeam);
		}
	}
	public void setRoundsTied(int homeTeam, int visitTeam)
	{
		this.teams[homeTeam].roundsTied += 1;
		this.teams[visitTeam].roundsTied += 1;
	}
	public void setRounds(int winner, int loser)
	{
		this.teams[winner].roundsWon += 1;
		this.teams[loser].roundsLost += 1;
	}
	public void setPoints() {
		for (int index = 0; index < this.teams.length; index++) {
			this.teams[index].goalsDifference = this.teams[index].goalsInFavor - this.teams[index].goalsAgainst ;
			this.teams[index].points = (this.teams[index].goalsInFavor ) * 3;
		}
	}
}
