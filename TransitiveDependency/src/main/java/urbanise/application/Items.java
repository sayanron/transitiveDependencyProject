package main.java.urbanise.application;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author SBhattacharya
 * @Descripton: The items class stores is basically a POJO class which stores each of the line items in the user entered list. 
 * 				The inherits the java util Comparable interface for displaying sorted results.
 * @Note: For the purpose of demostration I have used System.out.println in place of logger.
 * 		  This is only used in couple of methods in Service Class to avoid unnecessary printing in console to improve readability of the output
 */

public class Items implements Comparable<Items>{
	   
        Set<Items> children = new HashSet<Items>();
        String name;

        public Items(String name) {
            this.name = name;
        }

        /*This is to check that all the children of the node
         *  cannot be in this node, otherwise we create a cycle.
         */
        public boolean addChild(Items n) {
            if (n.allDescendants().contains(this))
            {
                return false;
            }
            else
            {
                this.children.add(n);
                return true;
            }
        }

        /*This method adds all the descendants of each elements 
         */
        public Set<Items> allDescendants() {
            Set<Items> all = new TreeSet<>();

            for (Items c : children) {
                all.add(c);
                all.addAll(c.allDescendants());
            }
            return all;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Items items = (Items) o;
            return name != null ? name.equals(items.name) : items.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        @Override
        public String toString() {
           return name + "," + getDescendants();
        }

        @Override
        public int compareTo(Items o) {
            return name.compareTo(o.name);
        }
        
        public String getDescendants()
        {
        	return allDescendants().stream().map(n -> n.name).collect(Collectors.joining(","));
        }

}
