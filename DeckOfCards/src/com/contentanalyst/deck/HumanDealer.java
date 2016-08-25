package com.contentanalyst.deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.contentanalyst.exception.NoCardsInDeckException;

public class HumanDealer implements IDealer {

	private List<Card> totalCards;

	public HumanDealer() {
		totalCards = new ArrayList<Card>();
		Deck deck = new Deck();
		populateTotalCards(deck);
	}

	private void populateTotalCards(Deck deck) {
		totalCards.addAll(deck.getCards());
	}

	public HumanDealer(int numberOfDecks) {
		addDecks(numberOfDecks);
	}

	@Override
	public void shuffel() throws NoCardsInDeckException {

		if (!DecksUtils.hasData(totalCards)) {
			throw new NoCardsInDeckException("No Cards in deck");
		}

		for (int i = 0; i < totalCards.size(); i++) {
			Random random = new Random();
			int randomIndex = random.nextInt(totalCards.size());
			Card tmpCard = totalCards.get(randomIndex);
			totalCards.set(randomIndex, totalCards.get(i));
			totalCards.set(i, tmpCard);

		}
		System.out.println("-------------------------After Shuffeling--------------------------");
		printCards();
	}

	private void printCards() {
		for (int i = 0; i < totalCards.size(); i++) {
			System.out.println(i + " ---   " + totalCards.get(i).getCardValue() + " -- " + totalCards.get(i).getSuit());
		}

	}

	@Override
	public Card dealOneCard() throws NoCardsInDeckException {
		if (!DecksUtils.hasData(totalCards)) {
			throw new NoCardsInDeckException("No Cards in deck");
		}
		Card card = totalCards.get(0);
		System.out.println("first card " + card);
		totalCards.remove(0);
		return card;

	}

	public void addDecks(int numberOfDecks) {
		for (int i = 0; i < numberOfDecks; i++) {
			Deck deck = new Deck();
			populateTotalCards(deck);
		}
	}

	public List<Card> getTotalCards() {
		return totalCards;
	}

	public void setTotalCards(List<Card> totalCards) {
		this.totalCards = totalCards;
	}

	public static void main(String[] args) {
		try {
			IDealer dealer = new HumanDealer();
			dealer.shuffel();
			System.out.println("=================");
			for (int i = 0; i < 53; i++) {
				dealer.dealOneCard();
			}
		} catch (NoCardsInDeckException e) {
			e.printStackTrace();
		}

	}

}
