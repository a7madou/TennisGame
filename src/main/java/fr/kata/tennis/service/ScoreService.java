package fr.kata.tennis.service;

import fr.kata.tennis.contants.Constants;
import fr.kata.tennis.data.Score;
import fr.kata.tennis.enums.TennisScoreEnum;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Service pour gérer le calcul des scores dans un jeu de tennis.
 */
public class ScoreService {

    private static final Predicate<Score> isDeuce = score -> score.getScorePlayer1() == TennisScoreEnum.FORTY && score.getScorePlayer2() == TennisScoreEnum.FORTY;
    private static final Predicate<Score> isAdvantagePlayer1 = score -> score.getScorePlayer1() == TennisScoreEnum.ADVANTAGE;
    private static final Predicate<Score> isAdvantagePlayer2 = score -> score.getScorePlayer2() == TennisScoreEnum.ADVANTAGE;

    /**
     * Gère le cas où les deux joueurs sont à DEUCE.
     * Si le joueur donné marque un point, il obtient l'avantage.
     *
     * @param score  Le score actuel.
     * @param player Le joueur qui marque le point ('A' ou 'B').
     * @return Le nouveau score après traitement du DEUCE.
     */
    private static final BiFunction<Score, Character, Score> handleDeuce = (score, player) ->
            player == Constants.PLAYER_ONE ? new Score(TennisScoreEnum.ADVANTAGE, TennisScoreEnum.FORTY) :
                    new Score(TennisScoreEnum.FORTY, TennisScoreEnum.ADVANTAGE);

    /**
     * Gère le cas où un joueur a l'avantage.
     * Si le joueur adverse marque un point, le score revient à DEUCE.
     * Si le joueur avec l'avantage marque un point, il gagne le jeu.
     *
     * @param score  Le score actuel.
     * @param player Le joueur qui marque le point ('A' ou 'B').
     * @return Le nouveau score après traitement de l'avantage.
     */
    private static final BiFunction<Score, Character, Score> handleAdvantage = (score, player) -> {
        if (isAdvantagePlayer1.test(score)) {
            return player == Constants.PLAYER_TWO ? new Score(TennisScoreEnum.DEUCE, TennisScoreEnum.DEUCE) :
                    new Score(TennisScoreEnum.WIN, TennisScoreEnum.LOOSE);
        } else {
            return player == Constants.PLAYER_ONE ? new Score(TennisScoreEnum.DEUCE, TennisScoreEnum.DEUCE) :
                    new Score(TennisScoreEnum.LOOSE, TennisScoreEnum.WIN);
        }
    };

    /**
     * Gère les scores réguliers (avant DEUCE ou ADVANTAGE).
     * Met à jour le score du joueur donné et vérifie si cela entraîne une victoire.
     *
     * @param score  Le score actuel.
     * @param player Le joueur qui marque le point ('A' ou 'B').
     * @return Le nouveau score après mise à jour.
     */
    private static final BiFunction<Score, Character, Score> handleRegularScore = (score, player) -> {
        TennisScoreEnum newScorePlayer1 = score.getScorePlayer1();
        TennisScoreEnum newScorePlayer2 = score.getScorePlayer2();

        if (player == Constants.PLAYER_ONE) {
            newScorePlayer1 = TennisScoreEnum.next(score.getScorePlayer1());
            if (newScorePlayer1 == TennisScoreEnum.WIN) {
                newScorePlayer2 = TennisScoreEnum.LOOSE;
            }
        } else {
            newScorePlayer2 = TennisScoreEnum.next(score.getScorePlayer2());
            if (newScorePlayer2 == TennisScoreEnum.WIN) {
                newScorePlayer1 = TennisScoreEnum.LOOSE;
            }
        }

        return new Score(newScorePlayer1, newScorePlayer2);
    };

    /**
     * Fonction principale pour calculer le prochain score en fonction du joueur qui marque un point.
     * Délègue le traitement à handleDeuce, handleAdvantage, ou handleRegularScore selon l'état du score.
     *
     * @param score  Le score actuel.
     * @param player Le joueur qui marque le point ('A' ou 'B').
     * @return Le nouveau score après traitement.
     */
    public static final BiFunction<Score, Character, Score> nextScore = (score, player) -> {
        if (isDeuce.test(score)) {
            return handleDeuce.apply(score, player);
        } else if (isAdvantagePlayer1.test(score) || isAdvantagePlayer2.test(score)) {
            return handleAdvantage.apply(score, player);
        } else {
            return handleRegularScore.apply(score, player);
        }
    };

    /**
     * Affiche le score en fonction d'une séquence de points marqués par les joueurs.
     * Arrête l'exécution si un joueur gagne ou si un caractère invalide est rencontré.
     *
     * @param sequence La séquence de points marqués par les joueurs (chaîne de 'A' et 'B').
     */
    public static void afficherScore(String sequence) {
        Score score = new Score(TennisScoreEnum.ZERO, TennisScoreEnum.ZERO);

        for (char player : sequence.toCharArray()) {
            if (player == Constants.PLAYER_ONE || player == Constants.PLAYER_TWO) {
                score = nextScore.apply(score, player);

                if (isWinning(score)) {
                    System.out.println("Player " + player + " wins the game");
                    return;
                } else {
                    System.out.println(score);
                }
            } else {
                System.out.println("Invalid character in the sequence: " + player);
                return;
            }
        }
    }

    /**
     * Vérifie si un joueur a gagné le jeu.
     *
     * @param score Le score actuel.
     * @return true si un joueur a gagné, false sinon.
     */
    public static boolean isWinning(Score score) {
        return score.getScorePlayer1() == TennisScoreEnum.WIN || score.getScorePlayer2() == TennisScoreEnum.WIN;
    }
}