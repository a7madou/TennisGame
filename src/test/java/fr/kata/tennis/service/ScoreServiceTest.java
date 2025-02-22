package fr.kata.tennis.service;

import fr.kata.tennis.data.Score;
import fr.kata.tennis.enums.TennisScoreEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoreServiceTest {

    @Test
    void testNextScore_PlayerOneWins() {
        Score score = new Score(TennisScoreEnum.FORTY, TennisScoreEnum.THIRTY);
        Score newScore = ScoreService.nextScore(score, 'A');
        assertEquals(TennisScoreEnum.WIN, newScore.getScorePlayer1());
        assertEquals(TennisScoreEnum.LOOSE, newScore.getScorePlayer2());
    }

    @Test
    void testNextScore_PlayerTwoWins() {
        Score score = new Score(TennisScoreEnum.THIRTY, TennisScoreEnum.FORTY);
        Score newScore = ScoreService.nextScore(score, 'B');
        assertEquals(TennisScoreEnum.LOOSE, newScore.getScorePlayer1());
        assertEquals(TennisScoreEnum.WIN, newScore.getScorePlayer2());
    }

    @Test
    void testNextScore_Deuce() {
        Score score = new Score(TennisScoreEnum.FORTY, TennisScoreEnum.FORTY);
        Score newScore = ScoreService.nextScore(score, 'A');
        assertEquals(TennisScoreEnum.ADVANTAGE, newScore.getScorePlayer1());
        assertEquals(TennisScoreEnum.FORTY, newScore.getScorePlayer2());
    }

    @Test
    void testNextScore_AdvantagePlayerOne() {
        Score score = new Score(TennisScoreEnum.ADVANTAGE, TennisScoreEnum.FORTY);
        Score newScore = ScoreService.nextScore(score, 'A');
        assertEquals(TennisScoreEnum.WIN, newScore.getScorePlayer1());
        assertEquals(TennisScoreEnum.LOOSE, newScore.getScorePlayer2());
    }

    @Test
    void testNextScore_AdvantagePlayerTwo() {
        Score score = new Score(TennisScoreEnum.FORTY, TennisScoreEnum.ADVANTAGE);
        Score newScore = ScoreService.nextScore(score, 'B');
        assertEquals(TennisScoreEnum.LOOSE, newScore.getScorePlayer1());
        assertEquals(TennisScoreEnum.WIN, newScore.getScorePlayer2());
    }

    @Test
    void testAfficherScore_InvalidCharacter() {
        ScoreService.afficherScore("ABC");
        // Vérifiez la sortie console pour "Invalid character in the sequence: C"
    }

    @Test
    void testAfficherScore_PlayerOneWins() {
        ScoreService.afficherScore("AAAA");
        // Vérifiez la sortie console pour "Player A wins the game"
    }

    @Test
    void testAfficherScore_PlayerTwoWins() {
        ScoreService.afficherScore("BBBB");
        // Vérifiez la sortie console pour "Player B wins the game"
    }
}