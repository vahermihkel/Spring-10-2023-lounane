public class LegalPlayer {
    private String playerId;
    private double finalAmount;
    private double winPerc;

    public LegalPlayer(String playerId, double finalAmount, double winPerc) {
        this.playerId = playerId;
        this.finalAmount = finalAmount;
        this.winPerc = winPerc;
    }

    public String getPlayerId() {
        return playerId;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public double getWinPerc() {
        return winPerc;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public void setWinPerc(double winPerc) {
        this.winPerc = winPerc;
    }
}
