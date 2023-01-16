package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utilz.Pixel;
import utilz.*;
import utilz.Constants.Types;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;
public class GamePanel extends JPanel {
	private MouseInputs mouseInputs;
	private BufferedImage imgBackground;
	private BufferedImage[] pixels;
	private Pieces currentPiece;
	private Pixel[][] board = new Pixel[20][10];
	private int score = 120000;
	private int animationTick, animationSpeed = 20;
	private int playerDir = -1;
	private LoadPictures ALLPICTURES = new LoadPictures();
	private boolean moving = false;
	private int deletedRows = 0;
	private boolean drop;
	private float combo = 1;
	private int movementTick, movementWaitTime;
	private boolean allowMovement = true;
	
	public GamePanel() {
		importImg();
		mouseInputs = new MouseInputs(this);
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		spawnPiece();	
	}
	public void moveDown() {
		if(downWasPressedRecently()) return;
		this.playerDir = DOWN;
		this.moving = false;
		this.currentPiece.setY(this.currentPiece.getY() + 30);
	}
	public boolean drop() {
		return drop;
	}
	private boolean downWasPressedRecently() {
		if(animationTick >= animationSpeed) {
			animationTick = 0;
			return false;
		}
		animationTick++;
		return true;
	}
	private void importImg() {
			pixels = (ALLPICTURES.pixels);
			imgBackground = ALLPICTURES.background;
		
	}
	private void setPanelSize() {
		Dimension size = new Dimension(312,612);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}
	public void spawnPiece() {
		checkForCompletedRows();
		System.out.println(score);
		int num = new Random().nextInt((7));
		switch(num) {
		case Types.TPiece:
			this.currentPiece = new Pieces(ALLPICTURES.TPiece,3,2,Types.TPiece,this.board, this);
			break;
		case Types.SPiece:
			this.currentPiece = new Pieces(ALLPICTURES.SPiece,3,2,Types.SPiece,this.board, this);
			break;
		case Types.ZPiece:
			this.currentPiece = new Pieces(ALLPICTURES.ZPiece,3,2,Types.ZPiece,this.board, this);
			break;
		case Types.LPiece:
			this.currentPiece = new Pieces(ALLPICTURES.LPiece,3,2,Types.LPiece,this.board, this);
			break;
		case Types.LRPiece:
			this.currentPiece = new Pieces(ALLPICTURES.LRPiece,3,2,Types.LRPiece,this.board, this);
			break;
		case Types.LONGPiece:
			this.currentPiece = new Pieces(ALLPICTURES.LONGPiece,1,4,Types.LONGPiece,this.board, this);
			break;
		case Types.SQUAREPiece:
			this.currentPiece = new Pieces(ALLPICTURES.SQUAREPiece,2,2,Types.SQUAREPiece,this.board, this);
			break;
		default: 
			System.out.println("SHOULDNT HAPPEN");
		}
	}
	


	public void downPressed() {
		animationTick = 0;
	}
	public void setDirection(int direction) {
		this.playerDir = direction;
		this.moving = true;
		
		
		
	}
	public void setMoving(boolean value) {
		this.moving = value;
		
	}
	public int getDirection() {
		return playerDir;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updatePos();
		moveDown();
		movementTick++;
		if(movementTick > 1) {
			movementTick = 0;
			allowMovement = true;
		}
		g.drawImage(imgBackground, 0, 0, 312, 612,  null);
		g.drawImage(currentPiece.getPic(), currentPiece.getX(), currentPiece.getY(), currentPiece.getLength(), currentPiece.getHeight(),  null);
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] != null) {
					g.drawImage(pixels[board[i][j].getPixelColor()], j * 30 + 6, i * 30 + 6, 30,30, null);
				}
			}
		}
	}
	
	// Implement the method and left off deleting rows
	private void checkForCompletedRows() {
		deletedRows = 0;
		// loop through rows
		rowloop:
		for(int i = 19; i >= 0; i--) {
			// go through columns
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == null) 
					continue rowloop;
			}
			// found row to delete
			deletedRows++;
			for(int j = i; j > 0; j--) {
				board[j] = board[j-1];
			}
			i++;
				
		}
		for(int i = 0; i < deletedRows; i++) {	
			board[i] = new Pixel[10];
		}
		if(deletedRows>0) {
			score += 1000 * deletedRows * combo;
			combo *= 2;
			System.out.println("Combo: "+ combo);
		} else {
			combo = 1;
		}
		if(score > 5000) {
			this.animationSpeed =  (20000 - score/10)/1000;
		}
		
	}
	public void updatePos() {
		if(!allowMovement) return;
		allowMovement = false;
		if(moving) {
			switch(playerDir) {
			case LEFT:
				this.currentPiece.setX(this.currentPiece.getX() - 30);
				break;
			case RIGHT:
				this.currentPiece.setX(this.currentPiece.getX() + 30);
				break;
			case ROTATE:
				this.currentPiece.nextOrientation();
				break;
			case DOWN:
				this.currentPiece.setY(this.currentPiece.getY() + 30);
				break;
			}
		}
		
	}
	public void addPixel(int x, int y, int posx, int posy, int color) {
		int xIndex = x + (posx - 6)/30;
		int yIndex = y + (posy - 6)/30;
		if(yIndex < 0) {
			Game.setStop(true);
			return;
		}
		this.board[yIndex][xIndex] = new Pixel(color);
	}
	public boolean checkPosition(int x, int y) {
		if(board[y][x] != null) return false;
		return true;
	}
	
	
		
	

	
		
}