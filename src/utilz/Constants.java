package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Constants {
		
		
	public static class Directions {
		public static final int LEFT = -1;
		public static final  int ROTATE = 0;
		public static final int RIGHT = 1;
		public static final int DOWN = 2;
	}
	public static class Orientation {
		public static final int ZERO = 0;
		public static final int NINETY = 1;
		public static final int ONEEIGHTY = 2;
		public static final int TWOSEVENTY = 3;
	}
	public static class Types {
		public static final int TPiece = 0;
		public static final int ZPiece = 1;
		public static final int SPiece = 2;
		public static final int LPiece = 3;
		public static final int LRPiece = 4;
		public static final int LONGPiece = 5;
		public static final int SQUAREPiece = 6;
	}
	public static class  PixelsInPicture {
		public static final int[][][] TPiece = 
			{
				{{0,1},{1,0},{1,1},{1,2}},
				{{0,0},{1,0},{1,1},{2,0}}, 
				{{0,0},{0,1},{0,2},{1,1}},
				{{0,1},{1,0},{1,1},{2,1}},
			};
		public static final int[][][] LPiece =
			{
				{{0,0},{0,1},{0,2},{1,0}},
				{{0,0},{0,1},{1,1},{2,1}}, 
				{{0,2},{1,0},{1,1},{1,2}},
				{{0,0},{1,0},{2,0},{2,1}},
			};
		public static final int[][][] LRPiece =
			{
				{{0,0},{1,0},{1,1},{1,2}},
				{{0,0},{0,1},{1,0},{2,0}}, 
				{{0,0},{0,1},{0,2},{1,2}},
				{{0,1},{1,1},{2,0},{2,1}},
			};
		public static final int[][][] SPiece =
			{
				{{0,1},{0,2},{1,0},{1,1}},
				{{0,0},{1,0},{1,1},{2,1}}, 
				{{0,1},{0,2},{1,0},{1,1}},
				{{0,0},{1,0},{1,1},{2,1}}, 
			};
		public static final int[][][] ZPiece =
			{
				{{0,0},{0,1},{1,1},{1,2}},
				{{0,1},{1,0},{1,1},{2,0}}, 
				{{0,0},{0,1},{1,1},{1,2}},
				{{0,1},{1,0},{1,1},{2,0}}, 
			};
		public static final int[][][] LONGPiece =
			{
				{{0,0},{1,0},{2,0},{3,0}},
				{{0,0},{0,1},{0,2},{0,3}}, 
				{{0,0},{1,0},{2,0},{3,0}},
				{{0,0},{0,1},{0,2},{0,3}},
			};
		public static final int[][][] SQUAREPiece =
			{
				{{0,0},{0,1},{1,0},{1,1}},
				{{0,0},{0,1},{1,0},{1,1}},
				{{0,0},{0,1},{1,0},{1,1}},
				{{0,0},{0,1},{1,0},{1,1}},
			};
	}
	
	public static class Colors {
		public static final int GREEN = 0;
		public static final int BLUE = 1;
		public static final int RED = 2;
		public static final int YELLOW = 3;
	}
}
