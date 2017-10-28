/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tournament;

import game.Game;
import game.player.IMove;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author lukstankovic
 */
public class Tournament {
	ArrayList<Player> players;

	public Tournament(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void playTournament() {
		for (Player p1 : players) {
			for (Player p2 : players) {
				if (p1 != p2) {
					Game game = new Game(20, p1.getAi(), p2.getAi());
					IMove winner = game.playGame();
					if (winner == p1.getAi()) {
						p1.addWin();
						p2.addLoose();
					} else if (winner == p2.getAi()) {
						p1.addLoose();
						p2.addWin();
					} else {
						p1.addTie();
						p2.addTie();
					}
				}
			}
		}
		
		Collections.sort(players);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Player player : players) {
			sb.append(player).append("\n");
		}
		return sb.toString();
	}
	
	
}
