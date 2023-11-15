public class PlayerData {
    private String id;
    private String action;
    private String matchId;
    private double amount;
    private String kellele;

    public PlayerData(String id, String action, String matchId, double amount, String kellele) {
        if (!action.equals("BET")) {
            // VISKA VÄLJA MINGI VIGA VÕI ANNA TEADA, ET ON VALESTI
        }
        this.id = id;
        this.action = action;
        this.matchId = matchId;
        this.amount = amount;
        this.kellele = kellele;
    }

    public PlayerData(String id, String action, double amount) {
        if (action.equals("BET")) {
            // VISKA VÄLJA MINGI VIGA VÕI ANNA TEADA, ET ON VALESTI
        }
        this.id = id;
        this.action = action;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getMatchId() {
        return matchId;
    }

    public double getAmount() {
        return amount;
    }

    public String getKellele() {
        return kellele;
    }
}
