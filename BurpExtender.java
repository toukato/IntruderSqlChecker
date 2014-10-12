package burp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JMenuItem;

public class BurpExtender implements IBurpExtender, IContextMenuFactory
{
	@Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        // your extension code here
    	callbacks.registerContextMenuFactory(this);
    }
    
    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation)
    {
    	List<JMenuItem> menuList = new ArrayList<JMenuItem>();
    	JMenuItem menuItem = new JMenuItem("IntruderSqlChecker");
    	
    	menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClipboardControl clipboardcontrol = new ClipboardControl();
				String line = clipboardcontrol.getClipboard();
				ArrayList<IntruderTableData> arraylist = new ArrayList<IntruderTableData>();
				
				String[] lineAry = line.split("\n");
				
				for(int i = 0; i < lineAry.length; i++)
				{
					String[] strAry = lineAry[i].split("\t");
					IntruderTableData interdertabledata = new IntruderTableData();
					interdertabledata.setRequest(strAry[0]);
					interdertabledata.setPosition(strAry[1]);
					interdertabledata.setPayload(strAry[2]);
					interdertabledata.setStatus(strAry[3]);
					interdertabledata.setError(strAry[4]);
					interdertabledata.setTimeout(strAry[5]);
					interdertabledata.setLength(strAry[6]);
					
					arraylist.add(interdertabledata);
				}
				SqlResultCheck sqlresultcheck = new SqlResultCheck();
				clipboardcontrol.setClipboard(sqlresultcheck.resultCheck(arraylist));
			}
		});
    	
    	menuList.add(menuItem);
    	return menuList;
    }
}