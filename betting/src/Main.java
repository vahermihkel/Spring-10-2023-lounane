import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<MatchData> matchDataList = new ArrayList<>();
        List<PlayerData> playerDataList = new ArrayList<>();
        List<IllegalPlayer> illegalPlayers = new ArrayList<>();
        List<LegalPlayer> legalPlayers = new ArrayList<>();

        BufferedReader pd = new BufferedReader(new FileReader("player_data.txt"));
        String pd_rida = pd.readLine();
        while (pd_rida != null) {
            String[] read = pd_rida.split(",");
            String id = read[0];
            String action = read[1];
            String matchId = read[2];
            double amount = Double.parseDouble(read[3]);
            String kellele = read[2].isEmpty() ? "" : read[4];
            PlayerData playerData = new PlayerData(id, action, matchId, amount, kellele);
            playerDataList.add(playerData);
            pd_rida = pd.readLine();
        }
        pd.close();

        BufferedReader md = new BufferedReader(new FileReader("match_data.txt"));
        String md_rida = md.readLine();
        while (md_rida != null) {
            String[] read = md_rida.split(",");
            String id = read[0];
            double a = Double.parseDouble(read[1]);
            double b = Double.parseDouble(read[2]);
            String result = read[3];
            MatchData matchData = new MatchData(id, a, b, result);
            matchDataList.add(matchData);
            md_rida = md.readLine();
        }
        md.close();

        for (PlayerData mangija: playerDataList) {
            // võtan kõige esimese mängija ja otsin üles kõik tema IDga seotud tegevused
            //      ja eemaldan need ning saan ka kokkuarvutusi korraga teha
            // võtab järgmise mängija ja siis on kõik esimese mängija tegevused läinud
            legalPlayers.add(new LegalPlayer(mangija.getId(), 2000, 80.0));
        }

        PrintWriter pw = new PrintWriter(new FileWriter("valjund1.txt"));
        for (LegalPlayer mangija: legalPlayers) {
            pw.print(mangija.getPlayerId());
            pw.println();
        }
        pw.println();
        pw.print("Uus rida");
        pw.close();
    }
}
