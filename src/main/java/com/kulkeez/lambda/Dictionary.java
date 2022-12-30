package com.kulkeez.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * Dictionary of adjectives and nouns.
 * 
 * @author kulkeez
 *
 */
@Slf4j
public class Dictionary {
	private List<String> nouns = new ArrayList<String>();
    private List<String> adjectives = new ArrayList<String>();

    private final int prime;
    static final Dictionary INSTANCE = new Dictionary();

    /**
     * default constructor
     * 
     */
    public Dictionary() {
        try {
        	log.debug("Loading Adjectives and Nouns resource files...");
            load("a.txt", adjectives);
            load("n.txt", nouns);
            
            log.debug("Loaded Adjectives and Nouns into the Dictionary successfully!");
        } 
        catch (IOException e) {
            throw new Error(e);
        }

        int combo = size();
        int primeCombo = 2;
        
        while (primeCombo <= combo) {
            int nextPrime = primeCombo + 1;
            primeCombo *= nextPrime;
        }
        prime = primeCombo + 1;
    }

	    
    /**
     * Total size of the combined words.
     */
    public int size() {
        return nouns.size() * adjectives.size();
    }

    
    /**
     * Sufficiently big prime that's bigger than {@link #size()}
     */
    public int getPrime() {
        return prime;
    }

    
    /**
     * Build a word using an adjective and noun
     * 
     * @param i
     * @return
     */
    public String word(int i) {
        int a = i % adjectives.size();
        int n = i / adjectives.size();

        return adjectives.get(a) + "_" + nouns.get(n);
    }

    
	/**
	 * Load the various dictionary files
	 *     
	 * @param fileName
	 * @param col
	 * @throws IOException
	 */
    private void load(String fileName, List<String> col) throws IOException {
        BufferedReader resourceReader = new BufferedReader(
        		new InputStreamReader(getClass().getResourceAsStream(fileName), "US-ASCII"));
        String line;
        
        while ((line = resourceReader.readLine()) != null) {
        	//System.out.println("Line:" + line);
            col.add(line);
        }
    }

    
    /**
     * 
     * @param args
     */
    public static void main (String args[]) {
    	Dictionary d = new Dictionary();
    	log.debug("Dictionary Size is: " + d.size());
    }
    
}