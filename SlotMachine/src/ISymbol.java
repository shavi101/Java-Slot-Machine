
public interface ISymbol {
	public void setImage(
			String i);/*
						 * Method which sets the image associated with one of
						 * the symbols in a reel,represented by the Symbol class
						 * implementing the interface.
						 */

	public String getImage();// Method which return the image.

	public void setValue(int v); /*
									 * Method which sets the value of the symbol as
									 * defined above.
									 */

	public int getValue();// Method which returns the value of the symbol.

}
