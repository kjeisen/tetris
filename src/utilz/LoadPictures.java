package utilz;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadPictures {
	public BufferedImage[] TPiece = new BufferedImage[4];
	public BufferedImage[] ZPiece = new BufferedImage[4];
	public BufferedImage[] SPiece = new BufferedImage[4];
	public BufferedImage[] LPiece = new BufferedImage[4];
	public BufferedImage[] LRPiece = new BufferedImage[4];
	public BufferedImage[] LONGPiece = new BufferedImage[4];
	public BufferedImage[] SQUAREPiece = new BufferedImage[4];
	public BufferedImage background;
	public BufferedImage[] pixels = new BufferedImage[4];
	
	public LoadPictures() {
		this.importImages();
	}
	
	public  void importImages() {
	
		InputStream isB = getClass().getResourceAsStream("/tetris_background.png");
		
		try {
			background = ImageIO.read(isB);
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				isB.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		for(int i = 0; i < 4; i++) {
		
		
		InputStream t = getClass().getResourceAsStream("/t_piece"+i+".png");
		InputStream s = getClass().getResourceAsStream("/s_piece"+i+".png");
		InputStream z = getClass().getResourceAsStream("/z_piece"+i+".png");
		InputStream l = getClass().getResourceAsStream("/l_piece"+i+".png");
		InputStream lr = getClass().getResourceAsStream("/lr_piece"+i+".png");
		InputStream longz = getClass().getResourceAsStream("/long_piece"+i+".png");
		InputStream square = getClass().getResourceAsStream("/square_piece"+i+".png");
		try {
			
			this.TPiece[i] = ImageIO.read(t);
			this.SPiece[i] = ImageIO.read(s);
			this.ZPiece[i] = ImageIO.read(z);
			this.LPiece[i] = ImageIO.read(l);
			this.LRPiece[i] = ImageIO.read(lr);
			this.LONGPiece[i] = ImageIO.read(longz);
			this.SQUAREPiece[i] =ImageIO.read(square);

		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				t.close();
				s.close();
				z.close();
				l.close();
				lr.close();
				longz.close();
				square.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		}
		
			InputStream isP = getClass().getResourceAsStream("/pixels10.png");
			try {
	
				BufferedImage img = ImageIO.read(isP);
				pixels[0] = img.getSubimage(0, 0, 10, 10);
				pixels[1]= img.getSubimage(10, 0, 10, 10);
				pixels[2] = img.getSubimage(0, 10, 10, 10);
				pixels[3] = img.getSubimage(10, 10, 10, 10);

			} catch (IOException e) {
				
				e.printStackTrace();
			} finally {
				try {
					isP.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
	}
}

