package connectdb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UpdateCatTag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String db_adn = "jdbc:mysql://192.168.6.7:3306/adn", user = "hieupd", passwd_adn = "22X6sL8MM177Z5L",
				db_url = "jdbc:mysql://192.168.23.191:3306/url_content", user_url = "phuoclh",
				passwd_url = "gW7Gj1HJ2wcJ",
				query1 = "select id_url,cat_tag" + " from url_content.Url"
						+ " where status !=-1 and cat_tag is not null and cat_tag != '';";
		Set<Integer> removetag = new HashSet<>(Arrays.asList(1,2,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,156,188,3,5,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,51,63,64,69,70,71,72,73,74,85,86,99,100,101,102,103,104,105,106,108,109,111,112,113,114,115,117,118,119,120,122,123,125,126,127,129,130,132,133,134,135,136,137,138,139,140,141,142,143,144,146,147,149,150,152,154,159,160,161,162,163,164,165,167,168,184));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dang cho ket noi");

			// Connect to ADN Database
			PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(new File("Output.txt"))));
			
			Connection con = DriverManager.getConnection(db_url, user_url, passwd_url);
			System.out.println("connected to " + db_url);
			Statement stmt = con.createStatement();
			Statement stmt1 = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query1);

			while (rs.next()) {
             String[] cattag = rs.getString(2).split(",");
             List<String> list = new ArrayList<String>(Arrays.asList(cattag));
             Iterator<String> iter =list.iterator();
             while(iter.hasNext()){
            	 String str =iter.next();
            	 if(removetag.contains(Integer.parseInt(str))){
            		 iter.remove();
            	 }
             }
//             String join = String.join(",",list);
             if (list.size() >0) {
            	 fout.println(String.valueOf(rs.getInt(1)) + " " + String.join(",",list));
             }
             
//             String query2 ="update url_content.Url set cat_tag =" +"\'"+ join+"\'" +" where id_url =" + rs.getString(1);
//             System.out.println(query2);
//             int rows = stmt1.executeUpdate(query2);
//             System.out.println(rows);
			}
			fout.close();
            rs.close();
			con.close();
			stmt.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
