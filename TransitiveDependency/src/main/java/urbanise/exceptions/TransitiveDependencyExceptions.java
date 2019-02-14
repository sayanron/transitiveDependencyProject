package main.java.urbanise.exceptions;

public class TransitiveDependencyExceptions extends Exception {
	
	 /**@author SBhattacharya
	  * @Descripton:The TransitiveDependencyExceptions class is the custom exception class. 
	  * 			This class is created to throw custom exceptions in this project.
	  * @Note: For the purpose of demostration I have used System.out.println in place of logger.
	  * 		  This is only used in couple of methods to avoid unnecessary printing in console to improve readability of the output
	  * 
	  */
	private static final long serialVersionUID = 1L;

	public TransitiveDependencyExceptions(String message) {
         super(message);
         
     }

}
