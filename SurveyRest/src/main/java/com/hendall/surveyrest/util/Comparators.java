package com.hendall.surveyrest.util;

import java.util.Comparator;

import com.hendall.surveyrest.datamodel.Answer;



public class Comparators {
	public static Comparator<Answer> COMPONENTS_ORDER_BY_ID = new Comparator<Answer>() {
        @Override
        public int compare(Answer o1, Answer o2) {
            return (Integer.valueOf(o1.getHtmlControlId())).compareTo((Integer.valueOf(o2.getHtmlControlId())));
        }
    };
    public static Comparator<Answer> COMPONENTS_ORDER_BY_OBESRVATON_NUMBER = new Comparator<Answer>() {
        @Override
        public int compare(Answer o1, Answer o2) {
            return (Integer.valueOf(o1.getObservationNumber())).compareTo((Integer.valueOf(o2.getObservationNumber())));
        }
    };
}
