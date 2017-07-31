package connectdb;

import java.util.HashSet;
import java.util.Set;

public class Url {
	
	String url;
	Set<String> tag = new HashSet<>();
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<String> getTag() {
		return tag;
	}

	public void setTag(Set<String> tag) {
		this.tag = tag;
	}

	public Url(String url, Set<String> tag) {
		super();
		this.url = url;
		this.tag = tag;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}

}
