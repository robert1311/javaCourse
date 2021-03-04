/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.data;

import com.re.bullsandcows.data.GameDatabaseDao.GameMapper;
import com.re.bullsandcows.entity.Game;
import com.re.bullsandcows.entity.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class RoundDatabaseDao implements RoundDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Round> getAllRounds() {
        final String GET_ALL_ROUNDS = "SELECT * FROM round";
        List<Round> rounds = jdbc.query(GET_ALL_ROUNDS, new RoundMapper());
        addGameToRounds(rounds);
        return rounds;
    }
    
    private void addGameToRounds(List<Round> rounds){
        for(Round round : rounds){
            round.setGame(getGameForRound(round));
        }
    }

    @Override
    public Round getRound(int roundId) {
        try{
            final String GET_ROUND_BY_ID = "SELECT r.* FROM round r WHERE "
                    + "r.roundId = ?";
            Round round =  jdbc.queryForObject(GET_ROUND_BY_ID, new RoundMapper(), 
                    roundId);
            round.setGame(getGameForRound(round));
            return round;
        } catch(DataAccessException e){
            return null;
        }
    }
    
    private Game getGameForRound(Round round){
        final String GET_GAME_FOR_ROUND = "SELECT g.* FROM game g "
                + "JOIN round r on g.gameId = r.gameId WHERE r.RoundId = ?";
        return  jdbc.queryForObject(GET_GAME_FOR_ROUND, new GameMapper(), 
                round.getRoundId());
    }

    @Override
    @Transactional
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(gameId, roundNumber, "
                + "guess, timeOfGuess, result) VALUES(?,?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
            sql,
            Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, round.getGame().getGameId());
            statement.setInt(2, round.getRoundNumeber());
            statement.setString(3, round.getGuess());
            statement.setTimestamp(4, Timestamp.valueOf(round.getTimeOfGuess()
                    .withNano(0)));
            statement.setString(5, round.getResult());
            return statement;
        }, keyHolder);
        
        round.setRoundId(keyHolder.getKey().intValue());
        
        return round;
    }

    @Override
    @Transactional
    public void updateRound(Round round) {
        final String UPDATE_ROUND = "UPDATE round SET roundNumber = ?, "
                + "guess = ? WHERE roundId = ?";
        jdbc.update(UPDATE_ROUND, 
                round.getRoundNumeber(),
                round.getGuess(), 
                round.getResult());
    }

    @Override
    public void deleteRound(int roundId) {
        final String DELETE_ROUND = "DELETE FROM round WHERE roundId = ?";
        jdbc.update(DELETE_ROUND, roundId);
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        final String SELECT_ROUNDS_FOR_GAME = "SELECT r.* FROM round r "
                + "JOIN game g on r.gameId = g.gameId WHERE g.gameId = ?"
                + " ORDER BY r.timeOfGuess";
        List<Round> rounds =  jdbc.query(SELECT_ROUNDS_FOR_GAME, new RoundMapper(), 
                game.getGameId());
        addGameToRounds(rounds);
        
        return rounds;
    }
    
    public static final class RoundMapper implements RowMapper<Round>{
        
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGuess(rs.getString("guess"));
            round.setResult(rs.getString("result"));
            round.setRoundNumeber(rs.getInt("roundNumber"));
            round.setTimeOfGuess(rs.getTimestamp("timeOfGuess").toLocalDateTime());
            return round;
        }
    }
    
}
