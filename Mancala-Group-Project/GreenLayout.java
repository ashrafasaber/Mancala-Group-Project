import java.awt.Color;
import java.awt.Image;

/**
 * This class is a concrete strategy for the strategy pattern
 * @author SRS
 *
 */
public class GreenLayout implements BoardLayout{
	/**
	 * This function returns the desired color-> to be used as a panel background in the view 
	 */
	public Color panelColors(){
		Color x =Color.GREEN;
		return x;
	}

 
}
