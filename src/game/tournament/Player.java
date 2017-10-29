/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tournament;

import game.player.IMove;

/**
 *
 * @author lukstankovic
 */
public class Player implements Comparable<Player>{
	private int wins = 0;
	private int looses = 0;
	private int ties = 0;
	private int points = 0;
	private IMove ai;

	public Player(IMove ai) {
		this.ai = ai;
	}
	
	public void addWin() {
		wins++;
		points += 3;
	}
	
	public void addTie() {
		ties++;
		points += 1;
	}
	
	public void addLoose() {
		looses++;
	}

	public IMove getAi() {
		return ai;
	}

	@Override
	public String toString() {
		return ai.getName() + " w:" + wins + ", t:" + ties + ", l:" + looses + ", p:" + points;
	}

	@Override
	public int compareTo(Player o) {
		if (this.wins == this.points) {
			return o.wins - this.wins;
		}
		return o.points - this.points;
	}
	
	
}
