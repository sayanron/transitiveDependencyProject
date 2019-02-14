package main.java.urbanise.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.urbanise.application.Items;
import main.java.urbanise.exceptions.TransitiveDependencyExceptions;

/**
 * @author SBhattacharya
 * @Descripton: The TransitiveDependencyService class is acting as a service class here. 
 * 				Most of the business logic resides in this class.
 * @Note: For the purpose of demostration I have used System.out.println in place of logger.
 * 		  This is only used in couple of methods to avoid unnecessary printing in console to improve readability of the output
 * 
 */

public class TransitiveDependencyService {
		//Variable Declarations
		public static final String EXCEPTION_MESSAGE_CYCLIC = "Cyclic Dependencies are not allowed";
		public static final String EXCEPTION_MESSAGE_CHAR_ONLY = "Only characters are allowed";
	    private Map<String, Items> dependencyMap = new HashMap<String, Items>();
	    private List<String[]> allItems = new ArrayList<String[]>();
	    private List<String> inverseDependency = new ArrayList<String>();

		/*
		 * This method is called from the main class. 
		 * It calls the respective method to generate the required output
		 * */
	    
	    public void printResults()
		{
			//This is the logging statement. Using Syso for Demo purpose.
			System.out.println("Inside TransitiveDependencyService::printResults");
			//Logging Statement ends
			
			System.out.println("The list of items entered are: ");
			allItems.forEach(arr->System.out.println(Arrays.toString(arr)));			
			System.out.println("The transitive dependencies from the items entered are: ");
		    for (String name : dependencyMap.keySet()) {
	            System.out.println(dependencyMap.get(name).toString());
	        }
	        calculateInverseDependencies();
	        System.out.println("Elements with Inverse Dependencies are: ");
	        inverseDependency.forEach(System.out::println);
	        
	        //This is the logging statement. Using Syso for Demo purpose.
			System.out.println("Exiting printResults");
			//Logging Statement ends
		}

		    /*
		     * Get a node from the Map if it already exists, else create it and put it in the map.
		     */
	    public Items createDependencyMap(String itemName) 
	    {
	    	//This is the logging statement. Using Syso for Demo purpose.
			System.out.println("Inside TransitiveDependencyService::createDependencyMap");
			//Logging Statement ends
	    	Items currentNode;
	        if (!dependencyMap.containsKey(itemName)) {
	            currentNode = new Items(itemName);
	            dependencyMap.put(itemName, currentNode);
	        } else {
	            currentNode = dependencyMap.get(itemName);
	        }
	        
	       //This is the logging statement. Using Syso for Demo purpose.
			System.out.println("Exiting TransitiveDependencyService::createDependencyMap");
		   //Logging Statement ends
	        return currentNode;
	    }

	    /*@ToDo: Add logger*/
	    
	    public boolean addElements(String dependency) throws TransitiveDependencyExceptions {
	    	
	        String[] elements = dependency.split(" ");
	        allItems.add(elements);
	        String item = elements[0];
	        if(item.length()>1)
	        {
	        	throw new TransitiveDependencyExceptions(EXCEPTION_MESSAGE_CHAR_ONLY);
	        }

	        Items items = createDependencyMap(item);
	        /*For each in array is slower than general for statement so using for loop in Array*/
	        
	        for (int i = 1; i < elements.length; i++) {
	            if (!items.addChild(createDependencyMap(elements[i]))) {
	                throw new TransitiveDependencyExceptions(EXCEPTION_MESSAGE_CYCLIC);
	            }
	        }
	        return true;
	    }
	    /*
	     * This method calculate the InverseDependencies. 
	     * It is assumed that the inverse dependency is calculated based on the input user has provided
	     * It is NOT based on the output file*/
    
	    public void calculateInverseDependencies()
	    {
    		for (String inverseMapName : dependencyMap.keySet())
    		{
	    		int count=0;
	    		for (String[] elements : allItems) 
	    		if(Arrays.stream(elements).anyMatch(inverseMapName::equals)) count++;
	    		if(count == allItems.size()) inverseDependency.add(inverseMapName);
    		}
	    }
		    	
		 
		

}

