package test.java.urbanise.application;

import main.java.urbanise.exceptions.TransitiveDependencyExceptions;
import main.java.urbanise.service.TransitiveDependencyService;
/**
 * @author SBhattacharya
 * @Descripton: The TransitiveDependencyApplicationTest class is testing class I have used to test the functionality of this application. 
 * 				It covers the following scenarios:
 * 				1. Finding Transitive Dependency and Inverse Dependencies
 * 				2. Exception Handling for 2 scenarios.
 * @Note: For the purpose of demostration I have used System.out.println in place of logger.
 * 		  This is only used in couple of methods in Service Class to avoid unnecessary printing in console to improve readability of the output
 * 		  This class should be replaced with Jmeter test class in actual project scenarios.
 * 
 */

public class TransitiveDependencyApplicationTest {

	
		 public static void main(String args[]) {

		        try {
		        	
		        	calculateTransitiveDependencyAndInverseDependencies();
		        	/*Please uncomment the below line to test the exception scenarios*/
		        	//calculateTransitiveDependencyCyclic();
		        }
		         	catch (TransitiveDependencyExceptions e) {
		            e.printStackTrace();
		        	
		        }
		        /*Please uncomment the below try catch block to test the exception scenarios*/ 
		       /* try 
		        {
		        	
		        	calculateTransitiveDependencyString();
		        }

				catch (TransitiveDependencyExceptions e) {
				    e.printStackTrace();
				}*/
		 }

		public static void calculateTransitiveDependencyAndInverseDependencies() throws TransitiveDependencyExceptions
		{
			System.out.println("Transitive Dependency with No Inverse Dependencies");
			TransitiveDependencyService tDependencyApp = new TransitiveDependencyService();
			tDependencyApp.addElements("A B C");
			tDependencyApp.addElements("B C E");
			tDependencyApp.addElements("C G");
			tDependencyApp.addElements("D A F");
			tDependencyApp.addElements("E F");
			tDependencyApp.addElements("F H");
			
			tDependencyApp.printResults();
			
			System.out.println("Transitive Dependency with Inverse Dependencies");
			TransitiveDependencyService tDependencyApp1 = new TransitiveDependencyService();
			
			tDependencyApp1.addElements("A B C");
			tDependencyApp1.addElements("A B E");
			tDependencyApp1.addElements("A B G");
			tDependencyApp1.addElements("D A B");
			tDependencyApp1.addElements("A B F");
			tDependencyApp1.addElements("A B H");
		
			tDependencyApp1.printResults();
		}
		public static void calculateTransitiveDependencyCyclic() throws TransitiveDependencyExceptions
		{
			System.out.println("Testing Exception scenarios for Cyclic Dependencies");
			TransitiveDependencyService tDependencyApp = new TransitiveDependencyService();
			tDependencyApp.addElements("A B");
			tDependencyApp.addElements("B A");
			tDependencyApp.printResults();
		}
		public static void calculateTransitiveDependencyString() throws TransitiveDependencyExceptions
		{
			System.out.println("Testing Exception scenarios for Entering String");
			TransitiveDependencyService tDependencyApp = new TransitiveDependencyService();
			tDependencyApp.addElements("AB B");
			tDependencyApp.printResults();
		}
}
