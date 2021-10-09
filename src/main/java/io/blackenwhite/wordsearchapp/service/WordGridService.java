package io.blackenwhite.wordsearchapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.blackenwhite.wordsearchapp.Model.Grid;

@Service
public class WordGridService {
	Grid grid;
	
	public char[][] generateGrid(int gridSize,List<String> words){
		Grid myGrid=new Grid(gridSize);
		myGrid.fillGrid(words);
		return myGrid.getContents();
	}
	
}
