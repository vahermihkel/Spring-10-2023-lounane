public class IllegalPlayer {
    private String playerId;
    private String illegalAction;
    private String matchId;
    private double amount;
    private String result;

    public IllegalPlayer(String playerId, String illegalAction, String matchId, double amount, String result) {
        this.playerId = playerId;
        this.illegalAction = illegalAction;
        this.matchId = matchId;
        this.amount = amount;
        this.result = result;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getIllegalAction() {
        return illegalAction;
    }

    public String getMatchId() {
        return matchId;
    }

    public double getAmount() {
        return amount;
    }

    public String getResult() {
        return result;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setIllegalAction(String illegalAction) {
        this.illegalAction = illegalAction;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
