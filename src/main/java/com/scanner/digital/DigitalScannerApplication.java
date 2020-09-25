package com.scanner.digital;

import com.scanner.digital.extractor.NumberExtractor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DigitalScannerApplication implements CommandLineRunner {

	private static FileScanner fileScanner = new FileScanner(new NumberExtractor());

	public static void main(String[] args) {
		SpringApplication.run(DigitalScannerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		renderCommandPrompt();
		readInput(new Scanner(System.in));
	}

	private static void readInput(Scanner userInput) {

		final String token = userInput.next();

		if ("Q".equals(token)) { System.exit(0); }
		else {
			if (!fileScanner.scanFileContents(token)) System.out.println("Please try again");
		}

		renderCommandPrompt();

		if (userInput.hasNext()) { readInput(userInput); }
	}

	private static void renderCommandPrompt() {
		System.out.println("\nPlease provide a file path and hit Enter\nOr hit 'Q' and hit Enter to exit from the program");
	}

}
