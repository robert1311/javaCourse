/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.controller;

import com.re.bullsandcows.data.GameDao;
import com.re.bullsandcows.data.RoundDao;
import com.re.bullsandcows.entity.Game;
import com.re.bullsandcows.entity.Guess;
import com.re.bullsandcows.entity.Round;
import com.re.bullsandcows.service.BullsAndCowsServiceLayer;
import com.re.bullsandcows.service.GameFinishedException;
import com.re.bullsandcows.service.InvalidGuessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rober
 */
@RestController
@RequestMapping("api/bullsAndCows")
public class BullsAndCowsController {
    
    @Autowired
    BullsAndCowsServiceLayer service;
    
    
    @GetMapping("/game")
    public  List<Game> allGames(){
        return service.getAllGames();
        
    }
    
    @GetMapping("game/{gameId}")
    public ResponseEntity getGameById(@PathVariable int gameId){
        Game game = service.findGameById(gameId);
        if(game == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        Game info = game;
        if(!game.isIsFinished()){
        info.setAnswer("");
        }
        return ResponseEntity.ok(info);
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createNewGame() throws InvalidGuessException {
        
        Game newGame = service.createGame();
        Game info = newGame;
        if(!newGame.isIsFinished()){
        info.setAnswer("");
        }
        return ResponseEntity.ok(info);
    }
    
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsForGame(@PathVariable int gameId){
        return service.getAllRoundsForGame(gameId);
    }
    
    @PostMapping("guess")
    public Round MakeGuessReturnResult(@RequestBody Guess guess)
        throws InvalidGuessException, GameFinishedException {
        Round result = service.makeGuess(guess.getGameId(), guess.getGuess());
        
        return result;
    }
}
