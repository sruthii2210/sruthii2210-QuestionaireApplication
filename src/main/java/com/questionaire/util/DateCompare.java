package com.questionaire.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DateCompare {

	public boolean dateDifference(LocalDate quizDate) {
		LocalDate date=LocalDate.now();
		Period p = Period.between(date, quizDate);
		String string=p.toString();
		if(string.contains("-"))
		{
			System.out.println(date);
			System.out.println(string);
			return false;
		}
		else
		{
			System.out.println(quizDate);
			System.out.println(string);
			return true;
		}
	}
	
	
}

