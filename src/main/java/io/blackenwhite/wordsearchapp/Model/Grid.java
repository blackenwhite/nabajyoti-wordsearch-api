package io.blackenwhite.wordsearchapp.Model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Grid {
	private int gSize;
	private char[][] contents;
	
	/*
	 * 0: UpDown
	 * 1: DownUp
	 * 2: LeftRight
	 * 3: RightLeft
	 * */
	
	
	public int getgSize() {
		return gSize;
	}

	public void setgSize(int gSize) {
		this.gSize = gSize;
	}

	public char[][] getContents() {
		return contents;
	}

	public void setContents(char[][] contents) {
		this.contents = contents;
	}

	public Grid(int gSize) {
		super();
		this.gSize = gSize;
		
		contents=new char[gSize][gSize];
		
		for(int i=0;i<gSize;i++) {
			for(int j=0;j<gSize;j++) {
				contents[i][j]='_';
			}
		}
	}
	
	public void displayGrid(){
		for(int i=0;i<gSize;i++) {
			for(int j=0;j<gSize;j++) {
				System.out.print(contents[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private Point getStartPoint(int wordLength,int dir) {
		Point res=new Point();
		int l=wordLength;
		if(dir==0) {
			res.x=ThreadLocalRandom.current().nextInt(0,gSize-l+1);
			res.y=ThreadLocalRandom.current().nextInt(0,gSize);
			return res;
		}
		
		if(dir==1) {
			res.x=ThreadLocalRandom.current().nextInt(l-1,gSize);
			res.y=ThreadLocalRandom.current().nextInt(0,gSize);
			return res;
		}
		
		if(dir==2) {
			res.x=ThreadLocalRandom.current().nextInt(0,gSize);
			res.y=ThreadLocalRandom.current().nextInt(0,gSize-l+1);
			return res;
		}
		
		if(dir==3) {
			res.x=ThreadLocalRandom.current().nextInt(0,gSize);
			res.y=ThreadLocalRandom.current().nextInt(l-1,gSize);
			return res;
		}
		
		return res;
		
	}
	
	
	private void fillWord(String word) {
		boolean success=false;
		while(!success) {
			int dir=ThreadLocalRandom.current().nextInt(0,4); // choose a random direction
			Point startPoint=getStartPoint(word.length(),dir);
			int i=startPoint.x;
			int j=startPoint.y;
			if(isPossible(word,i,j,dir)) {
				success=true;
				int x=i;
				int y=j;
				
				// UpDown
				if(dir==0) {
					// fill the whole word
					for(char c: word.toCharArray()) {
						contents[x++][y]=c;
					}
				}
				
				// DownUp
				if(dir==1) {
					
					// fill the whole word
					for(char c: word.toCharArray()) {
						contents[x--][y]=c;
					}
				}
				
				// LeftRight
				if(dir==2) {
					
					for(char c: word.toCharArray()) {
						contents[x][y++]=c;
					}
				}
				
				// RightLeft
				if(dir==3) {
					
					for(char c: word.toCharArray()) {
						contents[x][y--]=c;
					}
				}
			}
		}
	}
	/*
	 * Checks if it is possible to write the word with start point in the given direction
	 * */
	
	private boolean isPossible(String word,int startx,int starty,int dir) {
		int x=startx;
		int y=starty;
		if(dir==0) {
			// fill the whole word
			for(char c: word.toCharArray()) {
				if(!(contents[x][y]==c || contents[x][y]=='_')) {
					return false;
				}
				x++;
			}
			return true;
		}
		
		if(dir==1) {
			// fill the whole word
			for(char c: word.toCharArray()) {
				if(!(contents[x][y]==c || contents[x][y]=='_')) {
					return false;
				}
				x--;
			}
			return true;
		}
		
		if(dir==2) {
			for(char c: word.toCharArray()) {
				if(!(contents[x][y]==c || contents[x][y]=='_')) {
					return false;
				}
				y++;
			}
			return true;
		}
		
		if(dir==3) {
			for(char c: word.toCharArray()) {
				if(!(contents[x][y]==c || contents[x][y]=='_')) {
					return false;
				}
				y--;
			}
			return true;
		}
		
		
		return true;
	}
	/*
	 * Fills the grid with the given list of words in randomly random directions 
	 * 
	 * */
	public void fillGrid(List<String> words) {
		for(String word:words) {
			// if the word-length is greater than the gridSize , we are not going to fit it in
			if(word.length()>=gSize) {
				continue;
			}
			fillWord(word);
		}
		fillGridRedundant();
	}
	
	/*
	 * FILLS THE REMAINING BLOCKS
	 * */
	private void fillGridRedundant() {
		for(int i=0;i<gSize;i++) {
			for(int j=0;j<gSize;j++) {
				if(contents[i][j]=='_') {
					int x=ThreadLocalRandom.current().nextInt(0,26);
					char temp=(char)(x+'A');
					contents[i][j]=temp;
				}
			}
		}
	}
	
}
