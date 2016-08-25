package com.contentanalyst.deck;

public class Card {
	private String cardValue;
	private String suit;
	
	//if Needed;
	private String cardImage;
	
	public Card(){
		
	}

	public Card(String cardValue, String suit, String cardImage) {
		super();
		this.cardValue = cardValue;
		this.suit = suit;
		this.cardImage = cardImage;
	}

	public String getCardValue() {
		return cardValue;
	}

	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getCardImage() {
		return cardImage;
	}

	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}

	@Override
	public String toString() {
		return "Card [cardValue=" + cardValue + ", suit=" + suit +"]";
	}
	
	

}
