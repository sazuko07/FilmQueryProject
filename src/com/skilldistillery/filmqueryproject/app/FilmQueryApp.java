package com.skilldistillery.filmqueryproject.app;
import com.skilldistillery.filmqueryproject.entities.*;
import java.sql.SQLException;
import java.util.List;
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
			Film film = searchById();
			System.out.println(film);
			displayMenu();
		} else if (userInputInt == 2) {
			System.out.println("Please enter your search term: ");
			userInput = scanner.next();
			List<Film> films = searchByKeyword();
			System.out.println(films);
			displayMenu();
		} else if (userInputInt == 3) {
			System.out.println("You can put a new film in the database, with");
			System.out.println(" a title and movie description");
			System.out.println();
			System.out.println("please enter a title: ");
			String title = scanner.nextLine();
			System.out.println("please enter a description: ");
			String description = scanner.nextLine();
			Film addNewFilm = new Film(title, description);
			db.createNewFilm(addNewFilm);
			displayMenu();
		} else if (userInputInt == 4) {
			System.out.println("Program will now exit");
			scanner.close();
			System.exit(0);
		}
//		startUserInterface(userInput);

		scanner.close();
	}



	public Film searchById() throws SQLException {
		Film newFilm = db.findFilmById(userInputInt);
		return newFilm;
	}

	public List<Film> searchByKeyword() throws SQLException {
		List<Film> films = db.findFilmByKeyword(userInput);
		return films;
	}


	private void startUserInterface(Scanner input) {

	}
	public void displayMenu() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("what would you like to do next?");
		System.out.println();
		System.out.println("please choose among these 4 options: ");
		System.out.println("1. Search by ID");
		System.out.println("2. Search by keyword");
		System.out.println("3. Add new film");
		System.out.println("4. exit program");
		System.out.println();
		System.out.println("your choice: ");
		userInputInt = scanner.nextInt();
		if (userInputInt == 1) {
			System.out.println("Please enter the film Id: ");
			userInputInt = scanner.nextInt();
			searchById();
			displayMenu();
		} else if (userInputInt == 2) {
			System.out.println("Please enter your search term: ");
			userInput = scanner.next();
			searchByKeyword();
			displayMenu();
		} else if (userInputInt == 3) {
			System.out.println("You can put a new film in the database, with");
			System.out.println(" a title and movie description");
			System.out.println();
			System.out.println("please enter a title: ");
			String title = scanner.nextLine();
			System.out.println("please enter a description: ");
			String description = scanner.nextLine();
			Film addNewFilm = new Film(title, description);
			db.createNewFilm(addNewFilm);
			displayMenu();
		} else if (userInputInt == 4) {
			System.out.println("Program will now exit");
			scanner.close();
			System.exit(0);
		}
	}
}
