package fr.kata.tennis.service;

import fr.kata.tennis.data.Score;
import fr.kata.tennis.enums.TennisScoreEnum;

import java.util.Optional;

/**
 * Service pour gérer le calcul des scores dans un jeu de tennis.
 */
public class ScoreService {



    /**
     * Fonction principale pour calculer le prochain score en fonction du joueur qui marque un point.
     *
     * @param score  Le score actuel.
     * @param player Le joueur qui marque le point ('A' ou 'B').
     * @return Le nouveau score après traitement.
     */
    public static final Score nextScore(Score score, char player){
        return Optional.ofNullable(new Score(TennisScoreEnum.ZERO,TennisScoreEnum.ZERO)).get();
    }

    /**
     * Affiche le score en fonction d'une séquence de points marqués par les joueurs.
     * Arrête l'exécution si un joueur gagne ou si un caractère invalide est rencontré.
     *
     * @param sequence La séquence de points marqués par les joueurs (chaîne de 'A' et 'B').
     */
    public static void afficherScore(String sequence) {
        for (char player : sequence.toCharArray()) {
            System.out.println("Invalid character in the sequence: " + player);
            }
        }

}