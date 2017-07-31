package connectdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.jdbc.StringUtils;

public class UpdateCatTag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String db_adn = "jdbc:mysql://192.168.6.7:3306/adn", user = "hieupd", passwd_adn = "22X6sL8MM177Z5L",
				db_url = "jdbc:mysql://192.168.23.191:3306/url_content", user_url = "phuoclh",
				passwd_url = "gW7Gj1HJ2wcJ",
				query1 = "select id_url,cat_tag" + " from url_content.Url"
						+ " where status !=-1 and cat_tag is not null and cat_tag != '' and id_url=377;";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dang cho ket noi");

			// Connect to ADN Database
			Connection con = DriverManager.getConnection(db_url, user_url, passwd_url);
			System.out.println("connected to " + db_url);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query1);

			while (rs.next()) {
             String[] cattag = rs.getString(2).split(",");
             List<String> list = new ArrayList<String>(Arrays.asList(cattag));
             list.removeAll(Arrays.asList("18"));
             String join = String.join(",",list);
       
             String query2 ="update url_content.Url set cat_tag =" +"\'"+ join+"\'" +" where id_url =" + rs.getString(1);
             System.out.println(query2);
             int rows = stmt.executeUpdate(query2);
             System.out.println(rows);
			}
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
