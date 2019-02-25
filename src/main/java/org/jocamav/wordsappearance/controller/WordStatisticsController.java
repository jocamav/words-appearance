package org.jocamav.wordsappearance.controller;

import java.util.List;

import org.jocamav.wordsappearance.dto.WordAppearance;
import org.jocamav.wordsappearance.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordStatisticsController {

	@Autowired
	private WordService wordService;
	
	@GetMapping("/")
    public String index() {
        return "uploadForm";
    }
	
	@PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		
		List<WordAppearance> phraseStatistics = wordService.getPhraseStatistics(file);

        redirectAttributes.addFlashAttribute("words", phraseStatistics);
        
        return "redirect:/";
    }
}
