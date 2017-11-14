import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This Class is the ConcreteStrategy in the Strategy Pattern
 * @author Ashraf Saber ,Juan Carlos Sandoval, Galen Rivoire 
 */
public class AncientRomanLayout implements BoardLayout{

	// we can have a random number generator and multiple backgrounds per theme
		@Override
		public Image boardViewImage(){
			Image backgroundImage = null;
			try{ backgroundImage=ImageIO.read(new File("AncientRomanLayout.jpg"));
			} catch(IOException e){e.getMessage();}
			return backgroundImage;
		}

		@Override
		public Shape boardShape(BoardShape boardShape) {
			Rectangle2D rect= new Rectangle2D.Double(boardShape.getX(),boardShape.getX(),boardShape.getWidth(), boardShape.getHeight() ) ;
			return rect;
		}


}
