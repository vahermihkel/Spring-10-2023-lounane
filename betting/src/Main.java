import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<MatchData> matchDataList = new ArrayList<>();
        List<PlayerData> playerDataList = new ArrayList<>();
        Set<String> playerIds = new TreeSet<>();
        List<PlayerData> illegalPlayers = new ArrayList<>();
        List<LegalPlayer> legalPlayers = new ArrayList<>();
        double kasiino_seis = 0;

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
            playerIds.add(id);
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

        for (String id: playerIds) {
            try {
                List<PlayerData> mangijaTegevused = playerDataList.stream()
                        .filter(e -> e.getId().equals(id))
                        .toList();

//            List<PlayerData> mangijaTegevused2 = new ArrayList<>();
//            for (PlayerData p :playerDataList) {
//                if (p.getId().equals(id)) {
//                    mangijaTegevused2.add(p);
//                }
//            }

                double rahaline_seis = 0;
                double mangija_tulem = 0;
                double all_games = 0;
                double won_games = 0;
                for (PlayerData tegevus: mangijaTegevused) {
                    switch (tegevus.getAction()) {
                        case "DEPOSIT" -> rahaline_seis += tegevus.getAmount();
                        case "WITHDRAW" -> {
                            rahaline_seis -= tegevus.getAmount();
                            if (rahaline_seis < 0) {
                                illegalPlayers.add(tegevus);
                                throw new RuntimeException("Withdrawed too much");
                            }
                        }
                        case "BET" -> {
                            if (tegevus.getAmount() > rahaline_seis) {
                                illegalPlayers.add(tegevus);
                                throw new RuntimeException("Bet too much");
                            }
                            MatchData matchData;
                            try {
                                matchData = matchDataList.stream()
                                        .filter(e -> e.getId().equals(tegevus.getMatchId()))
                                        .findFirst()
                                        .get();
                            } catch (Exception e) {
                                illegalPlayers.add(tegevus);
                                throw new RuntimeException("Not found match id");
                            }
                            all_games++;
                            if (matchData.getResult().equals(tegevus.getKellele())) {
                                won_games++;
                                if (matchData.getResult().equals("A")) {
                                    mangija_tulem += tegevus.getAmount()*matchData.getaCoef();
                                } else if (matchData.getResult().equals("B")) {
                                    mangija_tulem += tegevus.getAmount()*matchData.getbCoed();
                                } else {
                                    // VIGA
                                }
                            } else if (!matchData.getResult().equals("DRAW") &&
                                    !matchData.getResult().equals(tegevus.getKellele())
                            ) {
                                mangija_tulem -= tegevus.getAmount();
                            }
                        }
                        default -> {
                        }
                        // VISKA VÄLJA ERROR VÕI ÜTLE SÕNUMINA, ET MIDAGI ON VALESTI
                    }
                }
                rahaline_seis += mangija_tulem;
                kasiino_seis -= mangija_tulem;
                legalPlayers.add(new LegalPlayer(id, rahaline_seis, won_games/all_games));

                // võtan kõige esimese mängija ja otsin üles kõik tema IDga seotud tegevused
                //      ja eemaldan need ning saan ka kokkuarvutusi korraga teha
                // võtab järgmise mängija ja siis on kõik esimese mängija tegevused läinud
            } catch (RuntimeException e) {
                System.out.println("Juhtus viga: " + e.getMessage());
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter("valjund1.txt"));
        for (LegalPlayer mangija: legalPlayers) {
            pw.print(mangija.getPlayerId());
            pw.print(" ");
            pw.print(mangija.getFinalAmount());
            pw.print(" ");
            pw.print(mangija.getWinPerc());
            pw.println();
        }
        pw.println();
        for (PlayerData tegevus: illegalPlayers) {
            pw.print(tegevus.getId());
            pw.print(" ");
            pw.print(tegevus.getAction());
            pw.print(" ");
            pw.print(tegevus.getMatchId());
            pw.print(" ");
            pw.print(tegevus.getAmount());
            pw.print(" ");
            pw.print(tegevus.getKellele());
            pw.println();
        }
        pw.println();
        pw.print(kasiino_seis);
        pw.close();
    }
}
