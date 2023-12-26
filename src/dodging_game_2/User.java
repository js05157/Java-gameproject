package dodging_game_2;

public class User {

	private String name;
	private int score;
	private int highScore;

	public User(String name) {
		this.name = name;
		this.score = 0;
		this.highScore = 0;
	}

	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}

	public int gethighScore() {
		return highScore;
	}

	public void setScore(int score) {
		this.score = score;
		
		if(score > highScore)
		{
			highScore = score;
		}
	}
}
