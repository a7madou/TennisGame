package fr.kata.tennis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumération représentant les scores possibles dans un jeu de tennis.
 */
@Getter
@AllArgsConstructor
public enum TennisScoreEnum {

    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    DEUCE("Deuce"),
    ADVANTAGE("Advantage"),
    WIN("Win"),
    LOOSE("Loose");

    /**
     * La représentation textuelle du score.
     */
    private final String score;

    /**
     * Calcule le prochain score dans une séquence de jeu de tennis.
     *
     * @param current Le score actuel.
     * @return Le prochain score dans la séquence.
     */
    public static TennisScoreEnum next(TennisScoreEnum current) {
        switch (current) {
            case ZERO: return FIFTEEN;
            case FIFTEEN: return THIRTY;
            case THIRTY: return FORTY;
            case FORTY: return WIN;
            case DEUCE: return ADVANTAGE;
            case ADVANTAGE: return WIN;
            default: return WIN;
        }
    }
}