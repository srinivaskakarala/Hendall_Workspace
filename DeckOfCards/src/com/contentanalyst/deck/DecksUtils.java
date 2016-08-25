package com.contentanalyst.deck;

import java.util.Collection;

public class DecksUtils {

	public static boolean hasData(Collection collection) {
		if (collection == null || collection.size() == 0)
			return false;
		return true;
	}

}
