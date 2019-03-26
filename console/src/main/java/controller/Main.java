package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.SpringConfig;
import exception.ComputerFieldException;
import exception.MapperException;
import exception.ValidatorException;

public class Main {
	
	/**
	* Start the program.
	*/
	public static void main(String[] args) {
		
		ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		Boolean isRunning = true;
		Controller programCommand = appContext.getBean("controller", Controller.class);
		
		while (isRunning) {
			String msg = programCommand.onSetup();
			
			if (msg.equals("exit")) {
				isRunning = false;
			} else {
				try {
					programCommand.executeCommand(msg);
				} catch (ComputerFieldException | ValidatorException | MapperException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		((ConfigurableApplicationContext)appContext).close();
	}
}
