package com.kulkeez.lambda;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * 
 * Generates pseudo random unique names that combines one adjective and one noun,
 * like "friendly_tiger" or "good_apple".
 * 
 * There's about 1.5 million unique combinations, and if you keep requesting a new word
 * it will start to loop (but this code will generate all unique combinations before it starts
 * to loop.)
 * 
 * @author kulkeez
 *  
 */
@Component
public class RandomNameGenerator {

	private int pos;

    public RandomNameGenerator(int seed) {
        this.pos = seed;
    }

    public RandomNameGenerator() {
        this((int) System.currentTimeMillis());
    } 
    
    
    /**
     * Fetch a random name from the dictionary
     * @return
     */
    public synchronized String randomName() {
        Dictionary d = Dictionary.INSTANCE;
        pos = Math.abs(pos+d.getPrime()) % d.size();
        
        //System.out.println("Picking random Position in Dictonary: " + pos);
        //System.out.println("Random name: " + d.word(pos));
        
        return d.word(pos);
    }

    
    /**
     * For quick unit testing - print 10 random names
     * 
     * @param args
     */
    public static void main (String args[]) {
    	RandomNameGenerator namesGenerator = new RandomNameGenerator(0);
    	
    	int sz = Dictionary.INSTANCE.size();
        Set<String> s = new HashSet<String>(sz);

        for (int i=0; i<10; i++)
        	System.out.println(namesGenerator.randomName());
    }
}