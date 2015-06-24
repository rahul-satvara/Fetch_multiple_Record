package Multiple_Records;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchLink {
	
	   
	   WebDriver driver;
		@Test
		 public void getSearchLink() throws IOException
		 {
			 String csv_path = "D:\\Records\\Ready\\records_256-359.csv";
			 BufferedReader br = new BufferedReader(new FileReader(csv_path));
			 String line;
			 String Url;
			 int linenumber = 0;
			 
			 driver = new FirefoxDriver();
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			 driver.get("http://www.domain.com.au/search/buy/?searchterm=15%2bmolan%2bstreet%2bringwood%2bvic%2b3134");
			 
				String main = "";
			    String fileName = "D:\\link1.csv";
				FileWriter writer = new FileWriter(fileName);
				String FILE_HEADER = "Street;Address;Suburb;State;Post Code;Local Authority;Land Use;Current Zoning;Up Zoning;Site Area;Description;Lot;Plan;Site type;Adopted;Height;Density;Owner;CMA;AVM;GMAP;Link1;";
				writer.append(FILE_HEADER);
				writer.append("\n"); 
				
				while ((line = br.readLine()) != null) {
					 if(linenumber>1074)
					 { 
					     String[] cols = line.split(";");
					     String a = cols[0];
					     String b = cols[1];
					     String c = cols[2];
					     String d = cols[3];
					     String e = cols[4];
					     
					     
					     
					     if(driver.findElements(By.cssSelector(".selectize-input  div.item a.remove")).size() > 0)
					     {
					    	 driver.findElement(By.cssSelector(".selectize-input  div.item a.remove")).click();
					     }
					    // driver.findElement(By.cssSelector(".homepage .search-wrap .frm-search.wide .search-input-wrap .selectize-input input[type='text']")).clear();
					     driver.findElement(By.cssSelector(".cT-searchRefine .cT-searchFacet.first .selectize-input input")).clear();
					     driver.findElement(By.cssSelector(".cT-searchRefine .cT-searchFacet.first .selectize-input input")).sendKeys(c);
					     driver.findElement(By.id("Sidebar_Sidebar_nsNavigators_SearchRefinerControl_btnSearch")).click();
					     
					     Url = driver.getCurrentUrl()+";";
					     
						 main="";
						 for(int j=0; j<cols.length; j++)
						 {
							 main += cols[j]+';';
						 } 
						
						try {    
						    writer.append(main+Url);
						    writer.append("\n");
					     }	
					     catch (IOException ex) {
						    ex.printStackTrace();    
						 }
						
						
					 }
					 linenumber++;
				}		 
				
			    writer.flush();
		        writer.close();
		 }
		
	}


