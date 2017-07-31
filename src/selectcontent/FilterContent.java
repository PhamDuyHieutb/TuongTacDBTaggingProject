package selectcontent;

public class FilterContent {
	static String Ct;
	public static String FilterCont(String content){
		Ct = content.replace("&nbsp;","");
		Ct = Ct.replace("&acirc;","a");
		Ct = Ct.replace("&quot;","");
		Ct = Ct.replace("&ndash;","");
		Ct = Ct.replace("&agrave;","");
		Ct = Ct.replace("&eacute;","");
		Ct = Ct.replace("&aacute;","á");
		Ct = Ct.replace("&ocirc;","o");
		Ct = Ct.replace("&ograve;","");
		Ct = Ct.replace("&iacute;","");
		Ct = Ct.replace("&ecirc;","e");
	
			
		return Ct;
		
	}
}






