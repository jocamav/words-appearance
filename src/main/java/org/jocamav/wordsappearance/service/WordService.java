package org.jocamav.wordsappearance.service;

import java.util.List;

import org.jocamav.wordsappearance.dto.WordAppearance;
import org.springframework.web.multipart.MultipartFile;

public interface WordService {
	List<WordAppearance> getPhraseStatistics(MultipartFile file);
	List<WordAppearance> getPhraseStatistics(String phrase);
}
