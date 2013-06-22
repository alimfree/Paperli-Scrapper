import java.sql.*;
import java.util.*;

public class MakeTweets {
public String[] listCount (LinkedList<String> e){
		
		LinkedList<String> result = new LinkedList<String>(new HashSet(e));
		System.out.println(result);
		String[] y = result.toArray(new String [0]); 
		return y;
		
		
}
	
	public static void main(String[] args) throws InterruptedException {
		MakeTweets test = new MakeTweets();
		Scraper foo = new Scraper();
		LinkedList<String> handles = foo.getHandle();
		String [] handle = test.listCount(handles);
		UpdateStatus hmoxie = new UpdateStatus(); 
		
		
		int i = 0;
		while(i<handle.length){
			java.util.Date date= new java.util.Date();
			Thread.sleep(10000);
			if(i%2 == 0){
				hmoxie.Updatetweet(handle[i] + " " + date + " Your picture's in our magazine today. Check it out bit.ly/XMLKtZ please subscribe");
				System.out.println(handle[i]);
			}
			else if( i%3 ==0){
				hmoxie.Updatetweet(handle[i] + " " + date + " Love your style. You’re in our magazine today bit.ly/XMLKtZ please subscribe");
				System.out.println(handle[i]);
			}
			else if(i%5 == 0){
				hmoxie.Updatetweet(handle[i] + " " + date + " Love your hijab. You’re in our magazine today bit.ly/XMLKtZ please subscribe");
				System.out.println(handle[i]);
			}
			else if(i% 7 ==0){
				hmoxie.Updatetweet(handle[i] + " " + date + " Your awesome picture got you a spot in our magazine. bit.ly/XMLKtZ please subscribe");
				System.out.println(handle[i]);
			}
			else if(i%13 ==0){
				hmoxie.Updatetweet(handle[i] + " " + date + " You have moxsie, so we added you to our magazine today, bit.ly/XMLKtZ please subscribe");
				System.out.println(handle[i]);
			}
			else{
				hmoxie.Updatetweet(handle[i] + " " + date + " Check yourself out in our magazine bit.ly/XMLKtZ please subscribe and tell friends");
				System.out.println(handle[i]);
			}
			
			i++;
	
		}
		
	}


}