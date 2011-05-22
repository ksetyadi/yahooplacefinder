import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class YahooPlaceFinder {
	private final static String BASE_URI = "http://where.yahooapis.com/geocode?";
	private final static String APP_ID = "your_app_id";
	private final static String FLAGS = "J";
	private final static String GFLAGS = "R";
	
	public String lat;
	public String lng;
	public String addr;

	public YahooPlaceFinder(String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	public YahooPlaceFinder(String addr) {
		this.addr = addr;
	}
	
	public JSONObject getReverseGeo() {
		JSONObject result;
		
		String qryStr = "location=" + this.lat + "+" + this.lng + 
			"&flags=" + YahooPlaceFinder.FLAGS + "&gflags=" + YahooPlaceFinder.GFLAGS +
			"&appid=" + YahooPlaceFinder.APP_ID;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(YahooPlaceFinder.BASE_URI + qryStr);
		
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			
			if (statusCode == 401) {
				return null;
			}
			
			HttpEntity myEntity = httpResponse.getEntity();
			InputStream is = myEntity.getContent();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String line;
			
			while ((line = rd.readLine()) != null) {
				//parsing data here
				sb.append(line);
			}
			
			rd.close();
			
			try {
				result = new JSONObject(sb.toString()).getJSONObject("ResultSet");
			} catch (JSONException e) {
				result = null;
				e.printStackTrace();
			}
		} catch (ClientProtocolException e) {
			result = null;
			e.printStackTrace();
		} catch (IOException e) {
			result = null;
			e.printStackTrace();
		}
		
		return result;
	}
}
