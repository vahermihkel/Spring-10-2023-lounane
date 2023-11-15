public class MatchData {
    private String id;
    private double aCoef;
    private double bCoed;
    private String result;

    public MatchData(String id, double aCoef, double bCoed, String result) {
        this.id = id;
        this.aCoef = aCoef;
        this.bCoed = bCoed;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public double getaCoef() {
        return aCoef;
    }

    public double getbCoed() {
        return bCoed;
    }

    public String getResult() {
        return result;
    }
}
