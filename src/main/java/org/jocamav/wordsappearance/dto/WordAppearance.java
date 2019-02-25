package org.jocamav.wordsappearance.dto;

public class WordAppearance implements Comparable<WordAppearance>{
	
	private String word;
	private int appearances;
	
	public WordAppearance(String word) {
		this(word, 1);
	}
	
	public WordAppearance(String word, int appearances) {
		super();
		this.word = word;
		this.appearances = appearances;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getAppearances() {
		return appearances;
	}

	public void setAppearances(int appearances) {
		this.appearances = appearances;
	}
	
	public void increaseAppearances() {
		this.appearances += 1;
	}
	
	public int compareTo(WordAppearance o) {
		int diffAppearances = o.getAppearances() - this.getAppearances();
		if( diffAppearances == 0) {
			return this.getWord().compareTo(o.getWord());
		}
		else {
			return diffAppearances;
		}
	}

	@Override
	public String toString() {
		return String.format("WordAppearance [word=%s,appearances=%s]", word, appearances);
	}
	

}
