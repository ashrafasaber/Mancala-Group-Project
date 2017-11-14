import java.awt.*;
import java.io.IOException;
/**
 * This interface defines the layout of the board.
 * The implementation follows a Strategy Pattern
 * This is the Strategy Interface
 * @author Ashraf Saber ,Juan Carlos Sandoval, Galen Rivoire 
 */
public interface BoardLayout {
	/**
	 * Sets the Mancala board's background
	 * @return the img that will be used as a background
	 * @throws IOException 
	 */
	Image boardViewImage() ;
	
	/**
	 * Defines the shape of the board 
	 * @param boardShape
	 * @return
	 */
	Shape boardShape(BoardShape boardShape); 
}
//https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/2d/images/examples/LoadImageApp.java
//https://alvinalexander.com/blog/post/jfc-swing/complete-java-program-code-open-read-display-image-file
//https://alvinalexander.com/blog/post/java/open-read-image-file-java-imageio-class
//https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html