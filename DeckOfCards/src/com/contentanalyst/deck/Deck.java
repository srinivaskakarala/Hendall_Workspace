package com.contentanalyst.deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		buildCards();
	}

	private void buildCards() {
		for (int i = 0; i < Constants.CARDS_VALUE.length; i++) {
			for (int j = 0; j < Constants.SUITS.length; j++) {
				Card card = new Card( Constants.CARDS_VALUE[i], Constants.SUITS[j],null);
				cards.add(card);
			}
		}

	}
	
	
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		for(int k=0;k<50;k++){
			System.out.print(rand.nextInt(21)+ " , ");
			
		}
		
	}

}
