/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.data;

import com.re.bullsandcows.entity.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rober
 */
@Repository
@Profile("database")
public class GameDatabaseDao implements GameDao {

     private final JdbcTemplate jdbc;
    
    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbc){
        this.jdbc= jdbc;
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGame(int gameId) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameId "
                    + "= ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), 
                    gameId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game(currentRound, answer) "
                + "VALUES(?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(INSERT_GAME, 
                    Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, game.getCurrentRound());
            statement.setString(2, game.getAnswer());
            return statement;
        }, keyHolder);
        game.setGameId(keyHolder.getKey().intValue());
//        jdbc.update(INSERT_GAME, 
//                game.getCurrentRound(),
//                game.getAnswer());
//        int newId = jdbc.queryForObject("SELECT_LAST_INSERT_ID()", Integer.class);
//        game.setGameId(newId);
        return game;
    }

    @Override
    @Transactional
    public void updateGame(Game game) {
        final String sql = "UPDATE game SET "
                + "currentRound = ?, "
                + "answer = ?, "
                + "isFinished = ? "
                + "WHERE gameId = ?";
        
        jdbc.update(sql,
                game.getCurrentRound(),
                game.getAnswer(),
                game.isIsFinished(),
                game.getGameId()
                );
    }

    @Override
    public void deleteGame(int gameId) {
        final String DELETE_ROUND_BY_GAME = "DELETE FROM round WHERE gameId"
                + " = ?";
        jdbc.update(DELETE_ROUND_BY_GAME, gameId);
        
        final String DELETE_GAME = "DELETE FROM game WHERE gameId = ?";
        jdbc.update(DELETE_GAME, gameId);
        
        
        
        
        
                
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setCurrentRound(rs.getInt("currentRound"));
            game.setAnswer(rs.getString("answer"));
            game.setIsFinished(rs.getBoolean("isFinished"));

            return game;
        }
    }
}
