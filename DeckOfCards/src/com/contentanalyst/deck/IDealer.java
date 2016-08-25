package com.contentanalyst.deck;

import com.contentanalyst.exception.NoCardsInDeckException;

public interface IDealer {

	public void shuffel() throws NoCardsInDeckException;

	public Card dealOneCard() throws NoCardsInDeckException;
}
