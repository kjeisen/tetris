package utilz;

import java.awt.image.BufferedImage;

import utilz.Constants.Colors;

public class Pixel {
	private int color;
	public Pixel(int color) {
		this.color = color;
		
	}
	public int getPixelColor() {
		return color;
	}
}
