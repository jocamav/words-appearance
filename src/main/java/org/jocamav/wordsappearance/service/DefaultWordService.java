package org.jocamav.wordsappearance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.jocamav.wordsappearance.dto.WordAppearance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DefaultWordService implements WordService{

	private static final Logger log = LoggerFactory.getLogger(DefaultWordService.class);
	
	public List<WordAppearance> getPhraseStatistics(MultipartFile file) {
		try {

			log.info(String.format("Checking file: %s", file.getOriginalFilename()));
			String phrase = new String(file.getBytes());
	        return getPhraseStatistics(phrase);
		}
		catch(Exception e) {
			log.error("Error reading file", e);
			return new ArrayList<WordAppearance>();
		}
	}
	
	public List<WordAppearance> getPhraseStatistics(String phrase) {
		String formatPhrase = getPhraseAsLowerCaseWithoutNonAlphanumericChars(phrase);
		return getPhraseStatisticsFromFormatedPhrase(formatPhrase);
	}

	private String getPhraseAsLowerCaseWithoutNonAlphanumericChars(String phrase) {
		return phrase.toLowerCase().replaceAll("[^a-z -]", "");
	}
	
	private boolean stringIsEmpty(String phrase) {
		return StringUtils.isEmpty(phrase);
	}
	
	private List<WordAppearance> getPhraseStatisticsFromFormatedPhrase(String formatPhrase) {
		String words[] = formatPhrase.split(" ");
		if(stringIsEmpty(formatPhrase)) {
			return new ArrayList<WordAppearance>();
		}
		HashMap<String, WordAppearance> wordsMap = getHashMapFromWords(words);
		return getListFromHashMap(wordsMap);
	}
	
	private HashMap<String, WordAppearance> getHashMapFromWords(String words[]) {
		HashMap<String, WordAppearance> wordsMap = new HashMap<String, WordAppearance>();
		for(String word : words) {
			addWordToHashMapIfNotPresent(wordsMap, word);
		}
		return wordsMap;
	}
	
	private void addWordToHashMapIfNotPresent(HashMap<String, WordAppearance> wordsMap, String word) {
		if(stringIsEmpty(word)) {
			return;
		}
		log.info(String.format("Checking word: %s", word));
		WordAppearance wordAppearance = wordsMap.get(word);
		if(wordsMap.get(word) == null) {
			wordsMap.put(word, new WordAppearance(word));
		}
		else {
			wordAppearance.increaseAppearances();
			wordsMap.put(word, wordAppearance);
		}
	}
	
	private List<WordAppearance> getListFromHashMap(HashMap<String, WordAppearance> wordsMap) {
		return (List<WordAppearance>)(Object) wordsMap.values().stream()
				.sorted()
				.collect(Collectors.toList());
	}
	
}
