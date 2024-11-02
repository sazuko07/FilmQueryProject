package com.skilldistillery.filmqueryproject.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmqueryproject.entities.*;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId) throws SQLException;
 public Actor findActorById(int actorId);
  public List<Actor> findActorsByFilmId(int filmId);
 public Film findFilmByKeyword(String title) throws SQLException;
 public Film createNewFilm(String title, String description) throws SQLException;



}
