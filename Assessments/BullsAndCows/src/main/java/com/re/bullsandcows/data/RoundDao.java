/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.data;

import com.re.bullsandcows.entity.Game;
import com.re.bullsandcows.entity.Round;
import java.util.List;

/**
 *
 * @author rober
 */
public interface RoundDao {
    List<Round> getAllRounds();
    Round getRound(int roundId);
    Round addRound(Round round);
    void updateRound(Round round);
    void deleteRound(int roundId);
    List<Round> getRoundsForGame(Game game);
}
