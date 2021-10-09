package io.blackenwhite.wordsearchapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.blackenwhite.wordsearchapp.service.WordGridService;

@RestController
public class WordSearchController {
	
	@Autowired
	WordGridService wordGridService;
	
	@GetMapping("/wordgrid")
	public String createWordGrid(@RequestParam int gSize,@RequestParam String wordList) {
		List<String> words=Arrays.asList(wordList.split(","));
		char[][] grid=wordGridService.generateGrid(gSize, words);
		String gridToString="";
		for(int i=0;i<gSize;i++) {
			for(int j=0;j<gSize;j++) {
				gridToString+= grid[i][j] + " ";
			}
			gridToString+="\r\n";
		}
		return gridToString;
	}
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello nabjo";
	}
}
