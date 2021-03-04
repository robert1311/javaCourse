/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.data;

import com.re.bullsandcows.entity.Game;
import com.re.bullsandcows.entity.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rober
 */
@Repository
@Profile("test")
public class RoundInMemoryDao implements RoundDao {
    Game game1;
    Game game2;
    List<Game> games = new ArrayList<>();
    Round round1;
    Round round2;
    Round round3;
    List<Round> rounds = new ArrayList<>();
    public RoundInMemoryDao(){
        game1 = new Game();
        game1.setAnswer("1234");
        game1.setCurrentRound(3);
        game1.setGameId(99);
        game1.setIsFinished(false);
        games.add(game1);
        
        game2 = new Game();
        game2.setAnswer("9365");
        game2.setCurrentRound(2);
        game2.setGameId(100);
        game2.setIsFinished(false);
        games.add(game2);
        
        round1 = new Round();
        round1.setGuess("8264");
        round1.setResult("e:2:p:0");
        round1.setRoundId(99);
        round1.setRoundNumeber(1);
        round1.setGame(game1);
        rounds.add(round1);
        
        round2 = new Round();
        round2.setGuess("6359");
        round2.setResult("e:1:p:3");
        round2.setRoundId(100);
        round2.setRoundNumeber(1);
        round2.setGame(game2);
        rounds.add(round2);
        
        round3 = new Round();
        round3.setGuess("1234");
        round3.setResult("e:4:p:0");
        round3.setRoundId(101);
        round3.setRoundNumeber(2);
        round3.setGame(game1);
        rounds.add(round3);
        
        game1.setIsFinished(true);
        games.add(game1);
    }

    @Override
    public List<Round> getAllRounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round getRound(int roundId) {
        for(Round round: rounds){
            if(round.getRoundId() == roundId){
                return round;
            }
        }
        return null;
    }

    @Override
    public Round addRound(Round round) {
        if(rounds.contains(round)){
            return round;
        } else {
            return null;
        }
    }

    @Override
    public void updateRound(Round round) {
        if(rounds.contains(round)){
            rounds.add(round);
        } else {
            
        }
    }

    @Override
    public void deleteRound(int roundId) {
//       for(Round round: rounds){
//            if(round.getRoundId() == roundId){
//                return round;
//            }
//        }
//        return null;
    }

    @Override
    public List<Round> getRoundsForGame(Game game) {
        return rounds
                .stream()
                .filter(r -> r.getGame().getGameId() == game.getGameId())
                .collect(Collectors.toList());
    }
    
    
}
