package com.qa.exceldataprovider;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.IOException;
import java.util.*;

public class Dataprovider_testdata {


	public static Iterator<Object[]> commonmethod_for_testdata(String sheetname,String Scriptname) throws IOException
	{
	
		ExcelReadWrite xl= new ExcelReadWrite("/NAMRATA/LEARNINGS/Appium/Apr30_BB_project/TestData/TestData.xls");
		HSSFSheet Sheet = xl.Setsheet(sheetname);
		
		int RowCount = xl.getrowcount(Sheet);
		int ColCount = xl.getcolcount(Sheet, 0);
		
		List<Object[]> arr_list= new ArrayList<Object[]>();
		
		for(int i=1;i<=RowCount;i++)
		{
			String Execute_Flag = xl.Readvalue(Sheet, i, "Execute_Flag");
			String Script_name = xl.Readvalue(Sheet, i, "Script_name");
			
			if((Execute_Flag.equalsIgnoreCase("Y")) && (Script_name.equals(Scriptname)))
			{
				Object[] x= new Object[1];
				Map<String,String> dMap= new HashMap<String,String>();
				
				for(int j=0;j<ColCount;j++)
				{
					
					String Skey = xl.Readvalue(Sheet, 0, j);
					String Value = xl.Readvalue(Sheet, i, j);
					
					dMap.put(Skey, Value);
					
				}//end of col for loop
				
				x[0]=dMap;
				arr_list.add(x);		
				
				
			}//end of if condition
			
		}//end of row for loop

		return arr_list.iterator();

	}

}
