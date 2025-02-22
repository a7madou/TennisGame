package fr.kata.tennis.data;

import fr.kata.tennis.enums.TennisScoreEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Représente le score d'un match de tennis pour deux joueurs.
 */
@Data
@AllArgsConstructor
public class Score {

    /**
     * Le score du joueur 1.
     */
    private final TennisScoreEnum scorePlayer1;

    /**
     * Le score du joueur 2.
     */
    private final TennisScoreEnum scorePlayer2;

    /**
     * Retourne une représentation textuelle du score.
     * Gère les cas particuliers comme DEUCE, ADVANTAGE, et les scores réguliers.
     *
     * @return Une chaîne de caractères représentant le score.
     */
    @Override
    public String toString() {
        if (scorePlayer1 == TennisScoreEnum.DEUCE && scorePlayer2 == TennisScoreEnum.DEUCE) {
            return "Deuce";
        } else if (scorePlayer1 == TennisScoreEnum.ADVANTAGE) {
            return "Avantage Player A";
        } else if (scorePlayer2 == TennisScoreEnum.ADVANTAGE) {
            return "Avantage Player B";
        } else {
            return "Player A : " + scorePlayer1.getScore() + " / Player B : " + scorePlayer2.getScore();
        }
    }
}