
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.LinkedList;
import java.util.List;
	
/*
 * Getting a web page and returning an ArrayStack of Twitter handles on that page.
 */

public class Scraper {
	
	private static char at = 64; 
	private static String pageUrl = "http://paper.li/Hijabi_es/1361945313";
	private HtmlPage page1;
	LinkedList<String> twitterHandles = new LinkedList<String>();
	
	public HtmlPage ScrapeUrl(String pageUrl){
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		
		webClient.getCookieManager().setCookiesEnabled(true);
		
		
		//Get Page
		try {
			 page1 = webClient.getPage(pageUrl);
		} catch (FailingHttpStatusCodeException e) {
			System.out.println("failed to scrape page");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("url mispecified");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something else is wrong");
			e.printStackTrace();
		}
		
		
		//Wait for background Javascript
		webClient.waitForBackgroundJavaScript(20000);
		System.out.println(page1.asXml());
		return page1;
	}
		
	
	
	public static String formatHandle(String element){
		String handle = "";  
		for(int i = element.length() - 1;  i >= 0; i--){
			if (element.charAt(i) != '/') 
				handle = element.charAt(i)+ handle;
			
			else break;
		 }
		handle = at + handle;
		return handle; 
	 }
	
	public LinkedList<String> getHandle (){
			HtmlPage source = ScrapeUrl(pageUrl);
			LinkedList<String> handles = new LinkedList<String>();
			String handle = ""; 
			List<DomElement> links = source.getElementsByTagName("a");
			
			for(DomElement currentlink : links){
				//System.out.println(currentlink.asXml());
				
				
			
				if (currentlink.getAttribute("class").equals("source-name source-info-open") && 
						currentlink.getAttribute("href").startsWith("http://twitter.com/")){
					
					handle = formatHandle(currentlink.getAttribute("href"));
					handles.push(handle);
				}
			}
			 return handles;
		}
	public static void main(String[] args){
		Scraper test = new Scraper();
		test.ScrapeUrl("http://matrix.itasoftware.com/view/flights?session=7a89d9fb-8e1b-4657-8ac8-60cd40a1f546");
	}
}