package burp;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class ClipboardControl {
	
	public String getClipboard() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);
		String str = "";
		
		try
		{
			str = (String) transferable.getTransferData(DataFlavor.stringFlavor);
			return str;
		}
		catch(UnsupportedFlavorException e)
		{
			return null;
		}
		catch (IOException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public void setClipboard(ArrayList<String> arraylist){
		
		String str = arraylist.get(0);
		
		for(int i=1; i<arraylist.size(); i++)
		{
			str = str + "\n" + arraylist.get(i);
		}
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection stringselection = new StringSelection(str);
		clipboard.setContents(stringselection, null);
	}

}
