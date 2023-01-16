package main;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utilz.Constants.Colors;
import utilz.Constants.Directions;
import utilz.Constants.Orientation;
import utilz.Constants.PixelsInPicture;
import utilz.Constants.Types;
import utilz.LoadPictures;
import utilz.Pixel;

public class Pieces {
	private BufferedImage[] animations = new BufferedImage[4];
	private BufferedImage pic;
	private int orientation = Orientation.ZERO;
	private int length, height;
	private int x, y;
	private int type;
	private Pixel[][] board;
	private GamePanel gamePanel;
	public Pieces(BufferedImage[] animations, int length, int height, int type, Pixel[][] board, GamePanel gamePanel) {
		this.animations = animations;
		this.pic = animations[0];
		this.length = length * 30;
		this.height = height * 30;
		this.x = 120+6;
		this.y = -30+6;
		this.type = type;
		this.board = board;
		this.gamePanel = gamePanel;
	}

	public BufferedImage getPic() {
		return this.pic;
	}
	public void nextOrientation() {
		int tempO = this.orientation;
		this.orientation = (this.orientation + 1) % 4;
		if(collision()) {
			this.orientation = tempO;
			return;
		}
		int temp = this.length;
		this.length = this.height;
		this.height = temp;
		this.pic = animations[orientation];
		
		ensureProperBoundary();
	}
	private void ensureProperBoundary() {
		while(this.x + this.length > 312) this.x = this.x -30;
		while(this.x < 0) this.x = this.x +30;
		while(this.y + this.height > 612) this.y = this.y -30;
	}

	public int getOrientation() {
		return this.orientation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public int getLength() {
		return this.length;
	}
	public int getHeight() {
		return this.height;
	}
	public void setX(int X) {
		if(X < 0 || X + this.length > 312 | this.collision()) return;
		this.x = X;
	}
	public void setY(int Y) {
		if(Y + this.height > 612 | this.collision()) {
			turnIntoPixels();
		} else {
			this.y = Y;
		}
	}

	private boolean collision() {
		switch(type) {
		case Types.TPiece:
			for(int i = 0; i < 4; i++) {
				if(checkPositionIsOpen(PixelsInPicture.TPiece[orientation][i][1],PixelsInPicture.TPiece[orientation][i][0])) {
					continue;
				} else {
					return true;
				}
			}
			return false;
		case Types.ZPiece:
			for(int i = 0; i < 4; i++) {
				if(checkPositionIsOpen(PixelsInPicture.ZPiece[orientation][i][1],PixelsInPicture.ZPiece[orientation][i][0])) {
					continue;
				} else {
					return true;
				}
			}
			return false;
		case Types.SPiece:
			for(int i = 0; i < 4; i++) {
				if(checkPositionIsOpen(PixelsInPicture.SPiece[orientation][i][1],PixelsInPicture.SPiece[orientation][i][0])) {
					continue;
				} else {
					return true;
				}
			}
			return false;
		case Types.LPiece:
			for(int i = 0; i < 4; i++) {
				if(checkPositionIsOpen(PixelsInPicture.LPiece[orientation][i][1],PixelsInPicture.LPiece[orientation][i][0])) {
					continue;
				} else {
					return true;
				}
			}
			return false;
		case Types.LRPiece:
			for(int i = 0; i < 4; i++) {
				if(checkPositionIsOpen(PixelsInPicture.LRPiece[orientation][i][1],PixelsInPicture.LRPiece[orientation][i][0])) {
					continue;
				} else {
					return true;
				}
			}
			return false;
		case Types.LONGPiece:
			for(int i = 0; i < 4; i++) {
				if(checkPositionIsOpen(PixelsInPicture.LONGPiece[orientation][i][1],PixelsInPicture.LONGPiece[orientation][i][0])) {
					continue;
				} else {
					return true;
				}
			}
			return false;
		case Types.SQUAREPiece:
			for(int i = 0; i < 4; i++) {
				if(checkPositionIsOpen(PixelsInPicture.SQUAREPiece[orientation][i][1],PixelsInPicture.SQUAREPiece[orientation][i][0])) {
					continue;
				} else {
					return true;
				}
			}
			return false;
			
		}
		return false;
	}

	private boolean checkPositionIsOpen(int x, int y) {
		int xIndex = x + (this.x - 6)/30 ;
		int yIndex = y + (this.y - 6)/30;
		boolean dir = gamePanel.drop();
		if( gamePanel.getDirection() == Directions.DOWN) {
			yIndex += 1;
		} else {
			int dirs = gamePanel.getDirection();
			xIndex += dirs;
			
		}
		if(xIndex < 0 | xIndex >= 10 | yIndex >= 20 |yIndex < 0 ) return true;
		
	
		return gamePanel.checkPosition(xIndex, yIndex);
		
		
	}

	private void turnIntoPixels() {
		switch(type) {
		case Types.TPiece:
			for(int i = 0; i < 4; i++) {
				gamePanel.addPixel(PixelsInPicture.TPiece[orientation][i][1],PixelsInPicture.TPiece[orientation][i][0],this.x, this.y,  Colors.GREEN);
			}
			break;
		case Types.ZPiece:
			for(int i = 0; i < 4; i++) {
				gamePanel.addPixel(PixelsInPicture.ZPiece[orientation][i][1],PixelsInPicture.ZPiece[orientation][i][0],this.x, this.y,  Colors.GREEN);
			}
			break;
		case Types.SPiece:
			for(int i = 0; i < 4; i++) {
				gamePanel.addPixel(PixelsInPicture.SPiece[orientation][i][1],PixelsInPicture.SPiece[orientation][i][0],this.x, this.y,  Colors.RED);
			}
			break;
		case Types.LPiece:
			for(int i = 0; i < 4; i++) {
				gamePanel.addPixel(PixelsInPicture.LPiece[orientation][i][1],PixelsInPicture.LPiece[orientation][i][0],this.x, this.y,  Colors.BLUE);
			}
			break;
		case Types.LRPiece:
			for(int i = 0; i < 4; i++) {
				gamePanel.addPixel(PixelsInPicture.LRPiece[orientation][i][1],PixelsInPicture.LRPiece[orientation][i][0],this.x, this.y,  Colors.RED);
			}
			break;
		case Types.LONGPiece:
			for(int i = 0; i < 4; i++) {
				gamePanel.addPixel(PixelsInPicture.LONGPiece[orientation][i][1],PixelsInPicture.LONGPiece[orientation][i][0],this.x, this.y,  Colors.BLUE);
			}
			break;
		case Types.SQUAREPiece:
			for(int i = 0; i < 4; i++) {
				gamePanel.addPixel(PixelsInPicture.SQUAREPiece[orientation][i][1],PixelsInPicture.SQUAREPiece[orientation][i][0],this.x, this.y,  Colors.YELLOW);
			}
			break;
		
		}
		gamePanel.spawnPiece();
		


	
	}

	
}
