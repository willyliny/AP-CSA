public class Scoreboard {
    
    private String info;
    
    private String team1;
    private String team2;

    private int score1;
    private int score2;

    private int turn; // 0 is team1, 1 is team2
    Scoreboard(String team1, String team2)
    {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = 0;
        this.score2 = 0;
        this.turn = 0;
    }

    public String getScore()
    {
        if(turn == 0)
        {
            return this.score1 + "-" + this.score2 + "-" + this.team1;
        }
        else
        {
            return this.score1 + "-" + this.score2 + "-" + this.team2;
        }
    }

    public void recordPlay(int score)
    {
        if(score != 0)
        {
            if(this.turn == 0)
            {
                this.score1 += score;
            }
            else
            {
                this.score2 += score;
            }
        }
        else
        {
            this.turn = (this.turn == 0 ? 1 : 0);
        }
        
    }

}
