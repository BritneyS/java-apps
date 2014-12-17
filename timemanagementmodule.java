package com.myapps;

import java.util.ArrayList;
import java.util.Scanner;

/*
  Purpose: To return a more realistic time estimate for completing a task 
  without discouraging productivity
  
 */

public class TimeManagementModule {
	 
	String iniUnit, // initial time unit estimate
		   newUnit; //returns element at index value 'index' from ArrayList 'unit'
	int iniTime, // initial integer value of time estimation
		newTime, // total time 
		index; // index integer of unit to be retrieved from 'unit'
	ArrayList unit; // holds time unit
	 		
	
	//constructor
	TimeManagementModule() {
		
		//INITIALIZATIONS
		
		//variables
		 
		iniUnit = " "; 
		newUnit = " "; 
		iniTime = 0; 
		newTime = 0; 
		index = 0; 
		unit = new ArrayList(6); 
		 		
		
		//add elements to ArrayList 'unit'
		unit.add(0, "minute(s)");
		unit.add(1, "hour(s)");
		unit.add(2, "day(s)");
		unit.add(3, "week(s)");
		unit.add(4, "month(s)");
		unit.add(5, "year(s)");
		
	}
	
	//methods
	public void doubleTime(int initialTime) {
		// doubles 'newTime' based on 'iniTime'
		newTime = initialTime * 2;
	}
	
	public void setNewTime(int initialTime) {
		// copies value of 'iniTime'(passed as argument) into 'newTime'
		// use when doubleTime(int) is NOT used (i.e. when time is not doubled)
		newTime = initialTime;
	}
	public int getNewTime() {
		// accesses 'newTime'
		return newTime;	
	}
	public void estimate(int initialTime, String firstUnit) {
		// updates 'newUnit' with higher unit of time, based on 'iniUnit'(firstUnit)
		
		iniTime = initialTime;
		iniUnit = firstUnit;

		switch (firstUnit) 
		{
			case "y":
				index = 5; // 1 year --> 2 years
				this.doubleTime(iniTime);
				break;
			case "n":
				index = 4; // 1 month --> 2 months
				this.doubleTime(iniTime);
				break;
			case "w":
				index = 3; // 1 week --> 2 weeks
				this.doubleTime(iniTime);
				break;
			case "d":
				index = 3; // 1 day --> 1 week
				this.setNewTime(iniTime); // when 'iniTime' is NOT doubled
				break;
			case "h":
				index = 1; // 1 hour --> 2 hours
				this.doubleTime(iniTime);
				break;
			case "m":
				index = 0; // 1 minute --> 2 minutes
				this.doubleTime(iniTime);
				break;
			case "s":
				index = 0; // 1 second --> 2 minutes
				this.doubleTime(iniTime);
				break;
			default:
				System.out.println("Invalid entry");
				System.exit(0);
		}

		this.setNewUnit(index);
	}			
	public void setNewUnit(int index) {
		// mutates 'newUnit'
		newUnit = (String)unit.get(index); 
	}
	public String getNewUnit() {
		// accesses 'newUnit'
		return newUnit;
	}
	public void displayResult() {
		// displays final time
		System.out.println("\nActual time needed: " + this.getNewTime() + " " + this.getNewUnit());
	}
	
	//test driver
	public static void main(String[] args) {
			
		int time;
		String unit;
		
		TimeManagementModule module = new TimeManagementModule();
		
		//receiving user input from console
		Scanner input = new Scanner(System.in);
		
		System.out.print("How long do you think the task will take (as an integer)?: ");
		time = input.nextInt();
		
		System.out.print("\nIn years(y)/months(n)/weeks(w)/days(d)/hours(h)/minutes(m)/seconds(s)?"
				+ "\nEnter the corresponding letter: ");
		unit = input.next();
		
		module.estimate(time, unit);
		module.displayResult();
		
		//close Scanner object to eliminate resource leak risk
		input.close();
	}
		
}