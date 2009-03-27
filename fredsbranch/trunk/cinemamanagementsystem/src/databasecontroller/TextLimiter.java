package databasecontroller;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextLimiter extends PlainDocument {
	
	private static final long serialVersionUID = 1L;
 
	private int max;
	
	public TextLimiter(int max){
		super();
		
		this.max = max;
	}
	
	public void insertString(int offset, String str, AttributeSet attrs){
		if (str != null && getLength() + str.length() > max) {
			Toolkit.getDefaultToolkit().beep();
		}
		else {
			try {
				super.insertString(offset, str, attrs);
			}
			catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
 
	public void replace(int offset, int length, String str, AttributeSet attrs){
		if (str != null && getLength() + str.length() - length > max) {
			Toolkit.getDefaultToolkit().beep();
		}
		else {
			try {
				super.replace(offset, length, str, attrs);
			}
			catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
}
