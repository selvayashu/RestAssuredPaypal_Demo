package com.paypalpayment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class TestData {
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	//public static String[][] xData;
	//public HashMap<String, Integer> testDataColumnFields = new HashMap<String, Integer>();
	public String testCase_Id;
	//public HashMap<String, Integer> testDataRowFields = new HashMap<String, Integer>();
	private  XSSFCell  cell;
	public int rowNum;
	private XSSFRow row;
	
	public String[][] readXL(String sheetname) throws Exception{
        //String sPath = "D:\\Selenium_Projects\\abc.xls"; //This is excel file path
        //File myFile = new File(sPath);  //Pass this file path to myFile
        FileInputStream myStream = new FileInputStream(System.getProperty("user.dir") 
				+ File.separator + "src/test/resources"
				+ File.separator + "Paypal_Data.xlsx");
       
        ExcelWBook  = new XSSFWorkbook(myStream); //Declare work book and pass myStream in it
        ExcelWSheet = ExcelWBook.getSheet(sheetname); //Go to the sheet at index 0 i.e. First One
       
        int xRows = ExcelWSheet.getLastRowNum() + 1; //Get rows number
        int xCols = ExcelWSheet.getRow(0).getLastCellNum(); //Get column number
       
        //System.out.println("Number of rows are : " + xRows);
        //System.out.println("Number of columns are : " + xCols);
       
        String[][] xData = new String[xRows][xCols]; //Declare an array of string type which will hold values
       
        for(int i=0; i<xRows; i++){
            XSSFRow row = ExcelWSheet.getRow(i); //Pointing to the row from we need to read data; it will be first row at first iteration
           
            for(int j=0; j<xCols; j++){
                XSSFCell cell = row.getCell(j);  //Pointing to the cell of the row
               
                String value = cellToString(cell); //Getting value of the cell and put it into a variable "value"; Here cellToString() is a user define function which will convert cell value in string.  
               
                xData[i][j] = value;   //Store that value in to array
               
               // System.out.print(value); //Print data in console
                //System.out.print("##");
            }
            //System.out.println("@");
        }
        //int colc=0;
        return xData;
    }
	
	public String fieldValue(String[][]arrayD,String testcase,String fieldname)
	{
		String fieldvalues="";
		for(int rowc=0;rowc<arrayD.length;rowc++) {
        	int tc=arrayD[rowc][0].compareTo(testcase);
        	//System.out.println("Fieldname:"+xData[rowc][0]);
        	if (tc == 0) {
        		for(int colc=0;colc<arrayD[rowc].length;colc++) {
        			int fn=arrayD[0][colc].compareTo(fieldname);
        			if(fn==0) {
        			//	System.out.println("Fieldname:"+arrayD[rowc][colc]);
        				fieldvalues= arrayD[rowc][colc];
        			}
        	}
        }
        }
		return fieldvalues;
	}
	//Section 4: This method will convert object of type cell into String
	public String cellToString(XSSFCell cell2){//Declare a method "cellToString()" which will convert an object of type cell into string
	        int type = cell2.getCellType();// This method "getCellType()" will return an integer which is 0-5 and on the basis of this integer value we will use SWITCH
	       //cell2.getCellType()
	        Object result; // Its an temporary object which will hold value in switch statement
	       
	        switch(type){
	       
	        case XSSFCell.CELL_TYPE_NUMERIC: // If a cell contain numeric value then it will return 0
	            result = cell2.getNumericCellValue();
	            System.out.println("Value of type is : " +type);
	            break;
	           
	        case XSSFCell.CELL_TYPE_STRING: // If a cell string value then it will return 1
	            result = cell2.getStringCellValue();
	            break;
	           
	        case XSSFCell.CELL_TYPE_FORMULA:  // If a cell contain formula then it will return 2
	            System.out.println("Can not eveulate formila in JAVA");
	            throw new RuntimeException("Can not eveulate formila in JAVA");
	           
	        case XSSFCell.CELL_TYPE_BLANK: // If a cell contain blank value then it will return 3
	            result = "";
	            break;
	           
	        case XSSFCell.CELL_TYPE_BOOLEAN: // If a cell contain boolean value then it will return 4
	            result = cell2.getBooleanCellValue();
	            break;
	           
	        case XSSFCell.CELL_TYPE_ERROR:// If a cell contain error then it will return 5
	            System.out.println("Can not eveulate formila in JAVA");
	            throw new RuntimeException("This cell has an error");
	           
	        default:
	            throw new RuntimeException("We dont support this cell type : " +type);
	        }
	        return result.toString();   //Here "toString()" method will convert result object to string; Here question may arise i.e why convert value into string...? This is so because we already have an array[xData] in which we will put this value and that is declared as STRING.
	       
	    }
	
	public static void main(String args[]) throws Exception
	{
		TestData td=new TestData();
		//td.getTestData("CreatePayment", "Payment");
		//System.out.println(td.getFieldValue("Body"));
		//td.setExcelFile("Payment");
		td.readXL("Payment");
		//System.out.println(td.getFieldValue("Body","CreatePayment"));
	}
}
