import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * This class is a concrete strategy for the strategy pattern
 * @author SRS
 *
 */
public class BlueLayout implements BoardLayout{
  
	 
	/**
	 * This function returns the desired color-> to be used as a panel background in the view 
	 */
	public Color panelColors(){
		Color x =Color.BLUE;
		return x;
	}
 

}
