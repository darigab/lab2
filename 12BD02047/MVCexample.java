import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class MVCexample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HttpServer server = HttpServer.create(new InetSocketAddress(1236), 0);
		server.createContext("/", new MyHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		
		News model = retrieveNewsFromDatabase();
		NewsView view= new NewsView();
		NewsController controller = new NewsController(model, view);
		controller.updateView();

	}
	
		
	private static News retrieveNewsFromDatabase(){
		News news1= new News();
		news1.setTitle("KFW 2014");
		news1.setAuthor("Евгений Овчинников");
		
		return news1;
	}
	
	public static Vector<String> readUserInput(HttpExchange t) throws IOException {
		 InputStream is = t.getRequestBody();
		 BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
		 Vector<String> data = new Vector<>();
		 String s;
		 s = br.readLine();
		 
		 s = URLDecoder.decode(s);
		// System.out.println(s);
		 StringTokenizer st = new StringTokenizer(s, "=&");
	     while (st.hasMoreTokens()) {
	    	 data.add(st.nextToken());
	     }
	     System.out.println("faaa");
	     return data;
	}
	
	
	
	public static String readFromFile(String fileName) throws IOException{
    	String response = null;
    	BufferedReader br = new BufferedReader(new FileReader(fileName));
    	String line = br.readLine();
    		    	while (line!=null){
    		    		response+=line+"\n";
    		    		line=br.readLine();
    		    	}
    		    	br.close();
    		    	return response;
	}
	
	
	static class MyHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			URI uri = t.getRequestURI();
        	String path = uri.toString();
            String response = new String();
            
            
            
            
            String templ = readFromFile("fas.txt");
            response=templ.replace("%space%", "");
            if(path.equals("/reg")){
            	String newContent = readFromFile("reg.txt");
            	response = templ.replace("%space%", newContent+"\n");
            }
            else if(path.equals("/news")){
            	String text=readFromFile("news1.txt");
            	response=templ.replace("%space%", text+"\n");
            	
            }
            
            else if(path.equals("/log")){
            	System.out.println();
            	response=templ.replace("%space%","Main Page ");
            	
            }
            
            
            else if(path.equals("/fas")){
            	response=templ.replace("%space%", "Main Page "+"\n");
            }
            
            else if(!path.equals("/")) {
        		path=path.substring(1);
        		String sub=readFromFile(path);
        		response=templ.replace("%space%", "Null");
            }
            else if(path.equals("/")){
            	response=templ.replace("%space%","Main Page ");
            }
                          
                      
			Headers header = t.getResponseHeaders();	//adding header
			header.add("Content-Type", "text/html; charset=ANSI");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
		}	

        	
		         
		            	                
		
		}

	
}
