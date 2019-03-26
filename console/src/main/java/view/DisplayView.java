package view;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class DisplayView {
	
	Scanner sc = new Scanner(System.in);
	
	public void displayHelp() {
		System.out.println("Availables Commands :\n");
		System.out.println("/l company  - LIST ALL COMPANIES");
		System.out.println("/l computer - LIST ALL COMPUTERS");
		System.out.println("/f company  - FIND SPECIFIC COMPANY");
		System.out.println("/f computer - FIND SPECIFIC COMPUTER");
		System.out.println("/a computer - ADD A NEW COMPUTER");
		System.out.println("/u computer - UPDATE A NEW COMPUTER");
		System.out.println("/d computer - DELETE AN EXISTANT COMPUTER");
		System.out.println("/d company  - DELETE AN EXISTANT COMPANY");
		System.out.println("exit  - EXIT APPLICATION");
	}
	
	public void displayErrorCommand() {
		System.out.println("\nThis command is invalid. \nPlease try again.");
	}
	
	/**
	* Asking attributes input for company.
	* 
	* @param attr
	*/
	public String askingInputCompany(String attr) {
		System.out.println("Please enter company " + attr + " :");
		return this.sc.nextLine();
	}
	
	/**
	* Asking attributes input for computer.
	* 
	* @param String attr
	*/
	public String askingInputComputer(String attr) {
		System.out.println("Please enter computer " + attr + " :");
		return this.sc.nextLine();
	}
	
	
	/**
	* Display the command result.
	* @param String result
	*/
	public void displayResult(String result) {
		System.out.println("\n" + result);
	}
	
	/**
	* Waiting for user to type a command.
	*/
	public String waitForCommand() {
		return this.sc.nextLine();
	}
}
