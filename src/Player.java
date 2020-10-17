import java.util.Random;

public class Player implements Comparable<Player> {

	private String name;
	private Integer noOcurred;
	private Boolean isSix;
	private Integer totalPoints;
	private Integer maxResultToAchieve;
	private Integer[] lastTwo;
	private boolean won;

	Player(String name, Integer maxRes) {
		this.name = name;
		this.maxResultToAchieve = maxRes;
		this.lastTwo = new Integer[2];
		this.lastTwo[0] = this.lastTwo[1] =0; 
		this.totalPoints = 0;

	}

	public Integer roll() {
		return new Random().nextInt(2) + 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNoOcurred() {
		return noOcurred;
	}

	public void setNoOcurred(Integer noOcurred) {
		this.noOcurred = noOcurred;
	}

	public Boolean getIsSix() {
		return isSix;
	}

	public void setIsSix(Boolean isSix) {
		this.isSix = isSix;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getMaxResultToAchieve() {
		return maxResultToAchieve;
	}

	public void setMaxResultToAchieve(Integer maxResultToAchieve) {
		this.maxResultToAchieve = maxResultToAchieve;
	}

	public Integer[] getLastTwo() {
		return lastTwo;
	}

	public void setLastTwo(Integer[] lastTwo) {
		this.lastTwo = lastTwo;
	}

	public void insertInLastTwo(Integer n) {
		this.lastTwo[0] = this.lastTwo[1];
		this.lastTwo[1] = n;
	}

	/* helper fucntion */

	public Boolean hasOneOccuredTwoTimes() {
		if (this.lastTwo[0] == this.lastTwo[1] && this.lastTwo[0].intValue() == 1) {
			return true;
		}
		return false;
	}

	public Boolean hasWon() {
		if (this.totalPoints.intValue() >= this.maxResultToAchieve.intValue()) {
			return true;
		}
		return false;
	}

	public void addScoreAndAppendArray(Integer n) {
		this.totalPoints = this.totalPoints + n;
		this.insertInLastTwo(n);
	}
	
	@Override
	public String toString() {
		return "Player \'"+this.name + "\' has points = "+ this.totalPoints;
	}

	@Override
	public int compareTo(Player p) {
		if (this.totalPoints != p.getTotalPoints()) {
			return this.totalPoints - p.getTotalPoints();
		}
		return this.name.compareTo(p.getName());
	}

	public void setWon(boolean b) {
		this.won = b;
	}
	public boolean getWon() {
		return this.won;
	}

}
