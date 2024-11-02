package com.skilldistillery.filmqueryproject.app;

import java.sql.SQLException;

import java.util.Scanner;

import com.skilldistillery.filmqueryproject.database.DatabaseAccessor;
import com.skilldistillery.filmqueryproject.database.DatabaseAccessorObject;
import com.skilldistillery.filmqueryproject.entities.Film;

public class FilmQueryApp {
	public String userInput = " ";
	public int userInputInt;
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		Film findFilmByKeyword = new Film("%");
		Film findFilmById = new Film();
		Film createNewFilm = new Film();

		System.out.println("Hello, user! This program is designed to search our film database");
		System.out.println(" in order to help you find the film you're looking for! you");
		System.out.println(" can search by the film Id or a search keyword!");
		System.out.println();
		System.out.println(" P.S.A. - We now have included a new feature: You can now");
		System.out.println(" add a new film to the database! (instructions will be given");
		System.out.println(" when you select the option from the menu)");
		System.out.println();
		System.out.println("please choose among these 4 options: ");
		System.out.println("1. Search by ID");
		System.out.println("2. Search by keyword");
		System.out.println("3. Add new film");
		System.out.println("4. exit program");
		System.out.println();
		System.out.println("your choice: ");
		userInputInt = scanner.nextInt();
		scanner.nextLine();
		if (userInputInt == 1) {
			System.out.println("Please enter the film Id: ");
			userInputInt = scanner.nextInt();
			searchById();
			cont1nue();
		} else if (userInputInt == 2) {
			System.out.println("Please enter your search term: ");
			userInput = scanner.nextLine();
			searchByKeyword();
			cont1nue();
		} else if (userInputInt == 3) {
			System.out.println("You can put a new film in the database, with");
			System.out.println(" a title and movie description");
			System.out.println();
			System.out.println("please enter a title: ");
			String title = scanner.nextLine();
			scanner.nextLine();
			System.out.println("please enter a description: ");
			String description = scanner.nextLine();
			scanner.nextLine();
			userInput(title, description);
			addNewFilm();
		} else if (userInputInt == 4) {
			System.out.println("Program will now exit");
			System.exit(0);
		}
//		startUserInterface(userInput);

		scanner.close();
	}

	private void userInput(String title, String description) {
	// TODO Auto-generated method stub
	
}

	public void searchById() throws SQLException {

		db.findFilmById(userInputInt);

	}

	public void searchByKeyword() throws SQLException {
		db.findFilmByKeyword(userInput);

	}
	public void addNewFilm() throws SQLException {
		db.createNewFilm(userInput, userInput);
	}

	private void startUserInterface(Scanner input) {

	}
	public void cont1nue() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("what would you like to do next?");
		System.out.println();
		System.out.println("please choose among these 3 options: ");
		System.out.println("1. Search by ID");
		System.out.println("2. Search by keyword");
		System.out.println("3. exit program");
		System.out.println();
		System.out.println("your choice: ");
		userInputInt = scanner.nextInt();
		if (userInputInt == 1) {
			System.out.println("Please enter the film Id: ");
			userInputInt = scanner.nextInt();
			searchById();

		} else if (userInputInt == 2) {
			System.out.println("Please enter your search term: ");
			userInput = scanner.nextLine();
			searchByKeyword();
		} else if (userInputInt == 3) {
			System.out.println("Program will now exit");
			System.exit(0);
		}
	}
}
