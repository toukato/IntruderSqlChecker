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
			String resultSingle = "-";
			String resultString = "-";
			String resultInt = "-";
			String resultAll = "";
			
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
						resultSingle = "NG";
					}
					else if(lengthI - lengthJ < 0 && lengthI - lengthJ > 10)
					{
						sabun = lengthI - lengthJ;
						resultSingle = "NG(" + sabun + ")";
					}
					else
					{
						resultSingle = "OK";
					}
					resultAll = resultSingle + ", " + resultString + ", " + resultInt;
					result.add(resultAll);	
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
						resultString = "NG";
					}
					else if(!arraylist.get(i).getLength().equals(arraylist.get(j).getLength()))
					{
						sabun = lengthJ - lengthI;
						resultString = "NG(" + sabun + ")";
					}
					else
					{
						resultString = "OK";
					}
					resultAll = resultSingle + ", " + resultString + ", " + resultInt;
					result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, resultAll);
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
						resultInt = "NG";
					}
					else if(!arraylist.get(i).getLength().equals(arraylist.get(j).getLength()))
					{
						sabun = lengthJ - lengthI;
						resultInt = "NG(" + sabun + ")";
					}
					else
					{
						resultInt = "OK";
					}
					resultAll = resultSingle + ", " + resultString + ", " + resultInt;
					result.set(Integer.parseInt(arraylist.get(i).getPosition()) - 1, resultAll);
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
