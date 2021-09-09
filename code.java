import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;

public class code {
	
	private static HashMap<String, String> cache = new HashMap<>();
	
	public static String translate(String langFrom, String langTo, String text) {
		try {
			if (cache.containsKey(text)) {
				return cache.get(text);
			}
			
	        String urlStr = "https://script.google.com/macros/s/AKfycbxBB3mEsS5JAMnGdp4JsjhU8kR_FUJJAoYmikmWl4CnoFnyLQ5ivCJGAiwnv1LFFSPXVA/exec" +
	                "?q=" + URLEncoder.encode(text, "UTF-8") +
	                "&target=" + langTo +
	                "&source=" + langFrom;
	        URL url = new URL(urlStr);
	        StringBuilder response = new StringBuilder();
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF8")));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        
	        cache.put(text, response.toString());
	        
	        return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
    }
	
}
