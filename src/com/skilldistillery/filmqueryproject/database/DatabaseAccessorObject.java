package com.skilldistillery.filmqueryproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import com.skilldistillery.filmqueryproject.entities.*;
;

public class DatabaseAccessorObject implements DatabaseAccessor {
  private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
  
  public DatabaseAccessorObject() {
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
  }
  @Override
  public Film createNewFilm(String title, String description) {
		Connection conn = null;
		String name = "student";																																																																				
		String pwd = "student";
		try {
			conn = DriverManager.getConnection(URL, name, pwd);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, description, language_id) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			
			ps.setString(1, film.getDescription());
			ps.setString(2, film.getDescription());
			ps.setInt(3, 1);

			int updateCount = ps.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					System.out.println("You have successfully added a new film to the database!");
				} else {														
					// something went wrong with the INSERT
					film = null;
				}
				conn.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film);
		}

		return film;
  }

  @Override
  public Film findFilmByKeyword(String title) throws SQLException {
	  Film film = null;
		String name = "student";
		String pwd = "student";
	
			Connection conn = DriverManager.getConnection(URL, name, pwd);
			//syntax for SQL works on SQL workbench, but not here for some reason
			String sql = "SELECT film.id, title, description, release_year,  language_id, first_name, last_name "
					+ "FROM film "
					+ "JOIN film_actor ON film.id = film_actor.film_id "
					+ "JOIN actor ON actor.id = film_actor.actor_id "
					+ "WHERE title LIKE ? OR description LIKE ?";
			System.out.println("query");
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" +title +"%");
			ps.setString(2, "%" +title +"%");
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				int id = rs.getInt("id");
			    String title1 = rs.getString("title"); 
			    String description1 = rs.getString("description");
			    Integer releaseYear = rs.getInt("release_year");
			    int languageId = rs.getInt("language_id");
//			    int rentalDuration = rs.getInt("rental_duration");
//			    double rentalRate = rs.getDouble("rental_rate");
//			    int length = rs.getInt("length");
//			    double replacementCost = rs.getDouble("replacement_cost");
//			    String rating = rs.getString("rating");
//			    String specialFeatures = rs.getString("special_features");
			    film = new Film(id, title1, description1, releaseYear, languageId/*, 
				rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures*/);
			    System.out.println(film);
			    if (!rs.next()) {
			    	System.out.println("your search doesnt match any film title or description");
			    }
			}
			ps.close();
			conn.close();
	    return film;
  }
  
  @Override
  public Film findFilmById(int filmId) throws SQLException {
	Film film = null; 
	String name = "student";
	String pwd = "student";

		Connection conn = DriverManager.getConnection(URL, name, pwd);
		String sql = "SELECT * FROM film WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,  filmId);
		
		ResultSet rs = ps.executeQuery();
		
		
		while (rs.next()) {
			int id = rs.getInt("id");
		    String title = rs.getString("title"); 
		    String description = rs.getString("description");
		    Integer releaseYear = rs.getInt("release_year");
		    int languageId = rs.getInt("language_id");
		    int rentalDuration = rs.getInt("rental_duration");
		    double rentalRate = rs.getDouble("rental_rate");
		    int length = rs.getInt("length");
		    double replacementCost = rs.getDouble("replacement_cost");
		    String rating = rs.getString("rating");
		    String specialFeatures = rs.getString("special_features");
			film = new Film(id, title, description, releaseYear, languageId
			/*rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures*/);
			System.out.println(film);
			if (!rs.next()) {
		    	System.out.println("your search doesnt match any film ID");
		    }
		}
		ps
		.close();
		conn.close();
    return film;
  }
  @Override
  public Actor findActorById(int actorId) {
	  return null;
  }
  @Override
  public List<Actor> findActorsByFilmId(int filmId) {
	  return null;
  }
}
