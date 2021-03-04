/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.data;

import com.re.bullsandcows.entity.Game;
import java.util.List;

/**
 *
 * @author rober
 */
public interface GameDao {
    List<Game> getAllGames();
    Game getGame(int gameId);
    Game addGame(Game game);
    void updateGame(Game game);
    void deleteGame(int gameId);
}
