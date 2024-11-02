package com.skilldistillery.filmqueryproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
  public Film findFilmByKeyword(String title) throws SQLException {
	  Film film = null;
		String name = "student";
		String pwd = "student";
	
			Connection conn = DriverManager.getConnection(URL, name, pwd);
			//syntax for SQL works on SQL workbench, but not here for some reason
			String sql = "SELECT title, description, release_year,  language_id, first_name, last_name "
					+ "FROM film "
					+ "INNER JOIN film_actor"
					+ "INNER JOIN actor"
					+ "WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,  "%" + title +"%");
			ps.setString(2, "%" + title +"%");
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				int id = rs.getInt("id");
			    String title1 = rs.getString("title"); 
			    String description1 = rs.getString("description");
			    Integer releaseYear = rs.getInt("release_year");
			    int languageId = rs.getInt("language_id");
			    int rentalDuration = rs.getInt("rental_duration");
			    double rentalRate = rs.getDouble("rental_rate");
			    int length = rs.getInt("length");
			    double replacementCost = rs.getDouble("replacement_cost");
			    String rating = rs.getString("rating");
			    String specialFeatures = rs.getString("special_features");
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
