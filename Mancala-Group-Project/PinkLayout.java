import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.*;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * This class is a concrete strategy for the strategy pattern
 * @author SRS
 *
 */
public class PinkLayout implements BoardLayout {
 
	/**
	 * This function returns the desired color-> to be used as a panel background in the view 
	 */
	public Color panelColors(){
		Color x =Color.PINK;
		return x;
	}
	
}