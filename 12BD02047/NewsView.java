import java.io.*;
import java.util.ArrayList;


public class NewsView {
	public void printCurrentNews(String title, String author){
	
		FileWriter writeFile = null;
		
		try {
		    File logFile = new File("news1.txt");
		    
		    writeFile = new FileWriter(logFile);
		    writeFile.append("Title:      "+title+"    ");
		    writeFile.append("\n");
		    writeFile.append("       Written by:     "+ author);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if(writeFile != null) {
		        try {
		            writeFile.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
}
		
		
		/*System.out.println("Title: "+ title);
		System.out.println("Wtitten by: "+ author);*/

