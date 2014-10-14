package burp;

import java.util.ArrayList;

public class SqlResultCheck {
	
	public ArrayList<String> resultCheck(ArrayList<IntruderTableData> arraylist){
		
		try
		{
			int j = 0;
			ArrayList<String> result = new ArrayList<String>();
			
			for(int i=0; i<arraylist.size(); i++)
			{			
				if(arraylist.get(i).getPayload().equals("'"))
				{
					j = i;
				}
				
				if(arraylist.get(i).getPayload().equals("''"))
				{
					if(!arraylist.get(i).getStatus().equals(arraylist.get(j).getStatus()))
					{
						result.add("NG");
					}
					else if(Integer.parseInt(arraylist.get(i).getLength()) - Integer.parseInt(arraylist.get(j).getLength()) > 10)
					{
						result.add("NG");
					}
					else
					{
						result.add("OK");
					}
				}
				
				if(arraylist.get(i).getPayload().equals("'and'a'='a"))
				{
					j = i;
				}
				
				if(arraylist.get(i).getPayload().equals("'and'a'='b"))
				{
					if(!arraylist.get(i).getStatus().equals(arraylist.get(j).getStatus()))
					{
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG");
					}
					else if(!arraylist.get(i).getLength().equals(arraylist.get(j).getLength()))
					{
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG");
					}
					else
					{
//						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "OK");
					}
				}

				if(arraylist.get(i).getPayload().equals(" and 1=1"))
				{
					j = i;
				}
				
				if(arraylist.get(i).getPayload().equals(" and 1=0"))
				{
					if(!arraylist.get(i).getStatus().equals(arraylist.get(j).getStatus()))
					{
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG");
					}
					else if(!arraylist.get(i).getLength().equals(arraylist.get(j).getLength()))
					{
						result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, "NG");
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
