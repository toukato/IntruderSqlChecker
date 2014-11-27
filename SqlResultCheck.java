package burp;

import java.util.ArrayList;

public class SqlResultCheck {
	
	public ArrayList<String> resultCheck(ArrayList<IntruderTableData> arraylist){
		
		try
		{
			int j = 0;
			int lengthI = 0;
			int lengthJ = 0;
			int sabun = 0;
			
			ArrayList<String> result = new ArrayList<String>();
			
			for(int i=0; i<arraylist.size(); i++)
			{			
				if(arraylist.get(i).getPayload().equals("'"))
				{
					j = i;
					lengthJ = Integer.parseInt(arraylist.get(j).getLength());
				}
				
				if(arraylist.get(i).getPayload().equals("''"))
				{
					lengthI = Integer.parseInt(arraylist.get(i).getLength());
					
					if(!arraylist.get(i).getStatus().equals(arraylist.get(j).getStatus()))
					{
						result.add("NG, Status UnMatch");
					}
					else if(lengthI - lengthJ > 10)
					{
						sabun = lengthI - lengthJ;
						result.add("NG, sabun:" + sabun);
					}
					else
					{
						result.add("OK");
					}
				}
				
				if(arraylist.get(i).getPayload().equals("'and'a'='a"))
				{
					j = i;
					lengthJ = Integer.parseInt(arraylist.get(j).getLength());
				}
				
				if(arraylist.get(i).getPayload().equals("'and'a'='b"))
				{
					lengthI = Integer.parseInt(arraylist.get(i).getLength());
					
					if(!arraylist.get(i).getStatus().equals(arraylist.get(j).getStatus()))
					{
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG, Status UnMatch(String)");
					}
					else if(!arraylist.get(i).getLength().equals(arraylist.get(j).getLength()))
					{
						sabun = lengthJ - lengthI;
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG ,sabun(String):" + sabun);
					}
					else
					{
//						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "OK");
					}
				}

				if(arraylist.get(i).getPayload().equals(" and 1=1"))
				{
					j = i;
					lengthJ = Integer.parseInt(arraylist.get(j).getLength());
				}
				
				if(arraylist.get(i).getPayload().equals(" and 1=0"))
				{
					lengthI = Integer.parseInt(arraylist.get(i).getLength());
					
					if(!arraylist.get(i).getStatus().equals(arraylist.get(j).getStatus()))
					{
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG, Status UnMatch(int)");
					}
					else if(!arraylist.get(i).getLength().equals(arraylist.get(j).getLength()))
					{
						sabun = lengthJ - lengthI;
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG, sabun(Int):" + sabun);
					}
					else
					{
//						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "OK");
					}
				}			
			}
			return result;
		}
		catch(NumberFormatException numError)
		{
			ArrayList<String> errArry = new ArrayList<String>();
			errArry.add("Intruder Columns Error");
			return errArry;
		}
	}
}
