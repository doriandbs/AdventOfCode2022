import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("EXERCICE 1 : ");
        day1();
        System.out.println("EXERCICE 2 : ");
        day2();

    }

    private static void day1() throws IOException {
        List<List<Integer>> elfes = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String ligne;
            List<Integer> elfeActuel = new ArrayList<>();
            while ((ligne = reader.readLine()) != null) {
                if (ligne.isEmpty()) {
                    elfes.add(elfeActuel);
                    elfeActuel = new ArrayList<>();
                } else {
                    elfeActuel.add(Integer.parseInt(ligne));
                }
            }
            elfes.add(elfeActuel);


        List<Integer> totalCaloriesParElfe = new ArrayList<>();
        for (List<Integer> elfe : elfes) {
            int sum = 0;
            for (int calories : elfe) {
                sum += calories;
            }
            totalCaloriesParElfe.add(sum);
        }

        int maxCalories = 0;
        int elfeIndex = 0;
        for (int i = 0; i < totalCaloriesParElfe.size(); i++) {
            if (totalCaloriesParElfe.get(i) > maxCalories) {
                maxCalories = totalCaloriesParElfe.get(i);
                elfeIndex = i;
            }
        }
        System.out.println("L'elfe : " + (elfeIndex + 1) + " a : " + maxCalories + " calories.");

        List<Integer> caloriesTri = new ArrayList<>(totalCaloriesParElfe);
        Collections.sort(caloriesTri, Collections.reverseOrder());

        int topTrois = caloriesTri.get(0) + caloriesTri.get(1) + caloriesTri.get(2);

        int premierElfe = totalCaloriesParElfe.indexOf(caloriesTri.get(0)) + 1;
        int secondElfe = totalCaloriesParElfe.indexOf(caloriesTri.get(1)) + 1;
        int troisiemeElfe = totalCaloriesParElfe.indexOf(caloriesTri.get(2)) + 1;

        System.out.println("Les trois elfes qui portent le plus de calories sont les elfes numéro : " +
                premierElfe + ", " + secondElfe + ", et " + troisiemeElfe + ".");
        System.out.println("Ils portent un total de : " + topTrois + " calories.");

    }



    private static void day2() throws IOException {
             BufferedReader br = new BufferedReader(new FileReader("input2.txt"));
            String ligne;
            int scoreTotal = 0;

            while ((ligne = br.readLine()) != null) {
                char mouvementAdversaire = ligne.charAt(0);
                char mouvementJoueur = ligne.charAt(2);

                int scoreRonde = calculerScore(mouvementAdversaire, mouvementJoueur);

                scoreTotal += scoreRonde;
            }
            br.close();

            System.out.println("Score total : " + scoreTotal);

    }

    private static int calculerScore(char adversaire, char joueur) {
        int score = switch (joueur) {
            case 'X' -> 1;
            case 'Y' -> 2;
            case 'Z' -> 3;
            default -> throw new IllegalArgumentException("Mouvement du joueur invalide : " + joueur);
        };

        if (adversaire == 'A' && joueur == 'Y' ||
                adversaire == 'B' && joueur == 'Z' ||
                adversaire == 'C' && joueur == 'X') {
            score += 6;
        } else if (adversaire == 'A' && joueur == 'Z' ||
                adversaire == 'B' && joueur == 'X' ||
                adversaire == 'C' && joueur == 'Y') {
        } else if (adversaire == 'A' && joueur == 'X' ||
                adversaire == 'B' && joueur == 'Y' ||
                adversaire == 'C' && joueur == 'Z') {
            score += 3;
        } else {
            throw new IllegalArgumentException("Combinaison de mouvements invalide : " + adversaire + " " + joueur);
        }

        return score;
    }
}

