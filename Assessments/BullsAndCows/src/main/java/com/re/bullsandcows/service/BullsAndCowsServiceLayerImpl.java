/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.service;

import com.re.bullsandcows.data.GameDao;
import com.re.bullsandcows.data.RoundDao;
import com.re.bullsandcows.entity.Game;
import com.re.bullsandcows.entity.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rober
 */
@Service
public class BullsAndCowsServiceLayerImpl implements BullsAndCowsServiceLayer {

    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        for(Game game: games){
            if(!game.isIsFinished()){
            game.setAnswer("");
            }
        }
        return games;
    }

    @Override
    public Game createGame() {
        Game newGame = new Game();
        newGame.setCurrentRound(1);
        List<Integer> digits = IntStream.range(0, 10).boxed().collect(
                Collectors.toList());
        Collections.shuffle(digits);
        
        String answer = "";
        for(int i = 0; i < 4;i++){
            answer += String.valueOf(digits.get(i)) + "";
        }
        newGame.setAnswer(answer);
        newGame = gameDao.addGame(newGame);
        return newGame;
    }

    @Override
    public Round makeGuess(int gameId, String guess) 
            throws InvalidGuessException, GameFinishedException {
        int bulls = 0;//e
        int cows = 0;//p
        Game game = gameDao.getGame(gameId);
        
        if(game.isIsFinished()){
            throw new GameFinishedException();
        }
        int numericGuess;
        try{
        numericGuess = Integer.parseInt(guess);
        } catch(NumberFormatException e){
            throw new InvalidGuessException();
        }
        Boolean isDistinct = hasDistinctDigits(numericGuess);
        if(guess.isBlank() | guess.length() != 4 | !isDistinct){
            throw new InvalidGuessException();
        }
        
        char[] answer = game.getAnswer().toCharArray();
        
        for(int i = 0; i < 4;i++){
            
            if(guess.indexOf(answer[i]) > -1){
                
                if(guess.charAt(i) == answer[i]){
                    bulls++;
                } else {
                    cows++;
                }
            }
        }
        
        Round round = new Round();
        round.setGame(game);
        round.setGuess(guess);
        round.setTimeOfGuess(LocalDateTime.now().withNano(0));
        List<Round> gameRounds = roundDao.getRoundsForGame(game);
        round.setRoundNumeber(gameRounds.size() + 1);
        String result = "e:" + bulls + ":p:" + cows;
        round.setResult(result);
        roundDao.addRound(round);
        game.setCurrentRound(round.getRoundNumeber() + 1);
        
        if(bulls == 4){
            game.setIsFinished(true);
            
        } else {
            game.setIsFinished(false);
        }
        gameDao.updateGame(game);
        return round;
    }
    
    private static boolean hasDistinctDigits(int number) {
     int numMask = 0;
     int numDigits = (int) Math.ceil(Math.log10(number+1));
     for (int digitIdx = 0; digitIdx < numDigits; digitIdx++) {
         int curDigit = (int)(number / Math.pow(10,digitIdx)) % 10;
         int digitMask = (int)Math.pow(2, curDigit);             
         if ((numMask & digitMask) > 0) return false;
         numMask = numMask | digitMask;
     }
     return true;
 }

    @Override
    public Game findGameById(int gameId) {
        return gameDao.getGame(gameId);
    }

    @Override
    public List<Round> getAllRoundsForGame(int gameId) {
        Game game = gameDao.getGame(gameId);
        return roundDao.getRoundsForGame(game);
    }
    
}
