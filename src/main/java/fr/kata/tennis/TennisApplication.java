package fr.kata.tennis;

import fr.kata.tennis.contants.Constants;
import fr.kata.tennis.service.ScoreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * Classe principale de l'application Spring Boot pour le jeu de tennis.
 * Cette classe implémente CommandLineRunner pour exécuter la logique métier au démarrage.
 */
@SpringBootApplication
public class TennisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TennisApplication.class, args);
	}

	/**
	 * Méthode exécutée au démarrage de l'application.
	 * Lit une séquence de points depuis l'entrée utilisateur et affiche le score.
	 *
	 * @param args Les arguments de la ligne de commande.
	 * @throws Exception Si une erreur survient lors de la lecture de l'entrée utilisateur.
	 */
	@Override
	public void run(String... args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Entrez une chaîne contenant uniquement 'A' ou 'B' : ");
			String input = scanner.nextLine().toUpperCase();

			if (input.chars().allMatch(c -> c == Constants.PLAYER_ONE || c == Constants.PLAYER_TWO)) {
				ScoreService.afficherScore(input);
			} else {
				System.out.println("Erreur : La chaîne ne doit contenir que les lettres 'A' ou 'B'.");
			}
		}
	}
}