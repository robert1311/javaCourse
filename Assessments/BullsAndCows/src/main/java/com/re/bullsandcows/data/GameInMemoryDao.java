/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.data;

import com.re.bullsandcows.entity.Game;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rober
 */
@Repository
@Profile("test")
public class GameInMemoryDao implements GameDao {

    List<Game> games = new ArrayList<>();
    
    Game game1;
    Game game2;
    
    
    
    public GameInMemoryDao(){
    
        game1 = new Game();
        game1.setAnswer("1234");
        game1.setCurrentRound(3);
        game1.setGameId(99);
        game1.setIsFinished(true);
        games.add(game1);
        
        game2 = new Game();
        game2.setAnswer("9365");
        game2.setCurrentRound(2);
        game2.setGameId(100);
        game2.setIsFinished(false);
        games.add(game2);
    }
    
    @Override
    public List<Game> getAllGames() {
        return games;
        
    }

    @Override
    public Game getGame(int gameId) {
        for(Game game: games){
            if(game.getGameId() == gameId){
                return game;
            }
        }
        return null;
    }

    @Override
    public Game addGame(Game game) {
        if(games.contains(game)){
            return game;
        } else {
            game.setCurrentRound(1);
            return game;
        }
    }

    @Override
    public void updateGame(Game game) {
        if(games.contains(game)){
            games.add(game);
        }
        
    }

    @Override
    public void deleteGame(int gameId) {
        games.removeIf(g -> g.getGameId() == gameId);
    }
    
}
