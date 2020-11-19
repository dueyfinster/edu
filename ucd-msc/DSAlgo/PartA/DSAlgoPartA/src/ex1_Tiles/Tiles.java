package ex1_Tiles;

import java.util.HashMap;

public class Tiles {
	private int floorWidth;
	private int floorHeight;
	private int blackTileCount;
	private int whiteTileCount;
	
	Tiles(int width, int height){
		this.floorWidth = width;
		this.floorHeight = height;
		
		CalculateTilesRequired();
	}
	
	private void CalculateTilesRequired(){
		int tilesAcross = (floorWidth / 4);
		int tilesDown = floorHeight / 4;
		int totalTiles = tilesAcross * tilesDown;
		
		HashMap<String, String> posColMap = new HashMap<String, String>();
		String position;
		String colour;
		for(int x=0; x<tilesDown; x++){
			for(int y=0; y<tilesAcross; y++){
				position = x + "," + y;
				if(x%2==0 & y%2==0 || x%2!=0 & y%2!=0){
					colour = "black";
					blackTileCount++;
				}else{
					colour = "white";
					whiteTileCount++;
				}
				
				posColMap.put(position, colour);
			}
		}
		
		printTiles(tilesDown, tilesAcross, posColMap);
	}
	
	public void printTiles(int tilesDown, int tilesAcross, HashMap<String, String> posColMap){
		for(int x=0; x<tilesDown; x++){
			for(int y=0; y<tilesAcross; y++){
				String pos = x + "," + y;
				System.out.println("Tile " + pos + " colour is: " + posColMap.get(pos));
			}
		}
		
		System.out.println("White Tiles:" + whiteTileCount);
		System.out.println("Black Tiles:" + blackTileCount);
		int totalTiles = whiteTileCount+blackTileCount;
		System.out.println("Total Tiles:" + totalTiles);
	}
	

	
	public static void main(String[] args) {
		Tiles tl = new Tiles(20,12);
	}
	
	
	
}
