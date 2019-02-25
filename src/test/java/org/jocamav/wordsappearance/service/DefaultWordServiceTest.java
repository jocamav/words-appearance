package org.jocamav.wordsappearance.service;

import java.util.List;

import org.jocamav.wordsappearance.dto.WordAppearance;
import org.jocamav.wordsappearance.service.DefaultWordService;
import org.jocamav.wordsappearance.service.WordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DefaultWordService.class})
public class DefaultWordServiceTest {
	
	@Autowired
	private WordService wordService;

	@Test
	public void getStatisticsOfEmptyPhrase() {
		List<WordAppearance> phraseStatistics = wordService.getPhraseStatistics("");
		assertThat(phraseStatistics.size()).isEqualTo(0);
	}
	
	@Test
	public void getStatisticsOfHelloWorld() {
		List<WordAppearance> phraseStatistics = wordService.getPhraseStatistics("World hello my-friend!");
		assertThat(phraseStatistics.size()).isEqualTo(3);
		assertThat(phraseStatistics.get(0).getWord()).isEqualTo("hello");
		assertThat(phraseStatistics.get(0).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(1).getWord()).isEqualTo("my-friend");
		assertThat(phraseStatistics.get(1).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(2).getWord()).isEqualTo("world");
		assertThat(phraseStatistics.get(2).getAppearances()).isEqualTo(1);
	}
	

	@Test
	public void getStatisticsOfSentence() {
		String sentence = "Dorothy, dorothy saw a quasi-bird creature which terrified her.";
		List<WordAppearance> phraseStatistics = wordService.getPhraseStatistics(sentence);
		assertThat(phraseStatistics.size()).isEqualTo(8);
		assertThat(phraseStatistics.get(0).getWord()).isEqualTo("dorothy");
		assertThat(phraseStatistics.get(0).getAppearances()).isEqualTo(2);
		assertThat(phraseStatistics.get(1).getWord()).isEqualTo("a");
		assertThat(phraseStatistics.get(1).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(2).getWord()).isEqualTo("creature");
		assertThat(phraseStatistics.get(2).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(3).getWord()).isEqualTo("her");
		assertThat(phraseStatistics.get(3).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(4).getWord()).isEqualTo("quasi-bird");
		assertThat(phraseStatistics.get(4).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(5).getWord()).isEqualTo("saw");
		assertThat(phraseStatistics.get(5).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(6).getWord()).isEqualTo("terrified");
		assertThat(phraseStatistics.get(6).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(7).getWord()).isEqualTo("which");
		assertThat(phraseStatistics.get(7).getAppearances()).isEqualTo(1);
	}
	

	@Test
	public void getStatisticsOfSentenceFromEmptyFile() {
		String textFile = "";
		MultipartFile multipartFile = new MockMultipartFile("file.txt", textFile.getBytes());
		
		List<WordAppearance> phraseStatistics = wordService.getPhraseStatistics(multipartFile);
		assertThat(phraseStatistics.size()).isEqualTo(0);
	}
	
	@Test
	public void getStatisticsOfSentenceFromFile() {
		String textFile = "Dorothy, dorothy saw a quasi-bird creature which terrified her.";
		MultipartFile multipartFile = new MockMultipartFile("file.txt", textFile.getBytes());
		
		List<WordAppearance> phraseStatistics = wordService.getPhraseStatistics(multipartFile);
		assertThat(phraseStatistics.size()).isEqualTo(8);
		assertThat(phraseStatistics.get(0).getWord()).isEqualTo("dorothy");
		assertThat(phraseStatistics.get(0).getAppearances()).isEqualTo(2);
		assertThat(phraseStatistics.get(1).getWord()).isEqualTo("a");
		assertThat(phraseStatistics.get(1).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(2).getWord()).isEqualTo("creature");
		assertThat(phraseStatistics.get(2).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(3).getWord()).isEqualTo("her");
		assertThat(phraseStatistics.get(3).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(4).getWord()).isEqualTo("quasi-bird");
		assertThat(phraseStatistics.get(4).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(5).getWord()).isEqualTo("saw");
		assertThat(phraseStatistics.get(5).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(6).getWord()).isEqualTo("terrified");
		assertThat(phraseStatistics.get(6).getAppearances()).isEqualTo(1);
		assertThat(phraseStatistics.get(7).getWord()).isEqualTo("which");
		assertThat(phraseStatistics.get(7).getAppearances()).isEqualTo(1);
	}
}
