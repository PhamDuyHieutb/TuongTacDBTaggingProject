package selectcontent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectContent {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		try {

//			FileWriter out = new FileWriter("/home/hadoop/Documents/tagproject/datasample/total/total.txt");
//			BufferedWriter bw = new BufferedWriter(out);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dang cho ket noi");
			Connection connect = DriverManager.getConnection("jdbc:mysql://192.168.23.191/url_content", "hieupd",
					"k7VUKjgLwCcL");
			System.out.println("connect");

			Statement st = connect.createStatement();

			ResultSet rs = st.executeQuery("Select id_url,title,content,timeUpload from Content where id_url not in (select id_url from url_content.Url where status =1 and cat_tag is not null and sku is not null)");

			int i = 0;
			 int a =1;
			while (rs.next()) {
				System.out.println(i+"_"+ a);
				if (rs.getInt(1) != i) {
//					File f = new File("/home/hadoop/Documents/tagproject/datasample_total/total_" + rs.getInt(1));
//					f.mkdir();
					FileWriter fwtotal = new FileWriter("/home/hadoop/Documents/PreprocessData/datasample_total/total_" + rs.getInt(1)
				+"_"+ rs.getString(4).toString());
					BufferedWriter bwtotal = new BufferedWriter(fwtotal);
					bwtotal.write(rs.getString(2)+"::"+rs.getString(3)+"\n");
					bwtotal.close();
					a =1;
				}else{
					a++;
					FileWriter fwtotal = new FileWriter("/home/hadoop/Documents/PreprocessData/datasample_total/total_" + rs.getInt(1)
					+ "_" + rs.getString(4).toString(),true);
					BufferedWriter bwtotal = new BufferedWriter(fwtotal);
					bwtotal.write(rs.getString(2)+"::"+rs.getString(3)+"\n");
					bwtotal.close();
				}
//				FileWriter fwtitle = new FileWriter("/home/hadoop/Documents/tagproject/datasample/" + rs.getInt(1)
//						+ "/title-" + rs.getString(4).toString());
//				BufferedWriter bwtitle = new BufferedWriter(fwtitle);
//				FileWriter fwcontent = new FileWriter("/home/hadoop/Documents/tagproject/datasample/" + rs.getInt(1)
//						+ "/content-" + rs.getString(4).toString());
//				FileWriter fwtotal = new FileWriter("/home/hadoop/Documents/tagproject/datasample_total/total_" + rs.getInt(1)
//						+ "/total-" + rs.getString(4).toString());
//				BufferedWriter bwtotal = new BufferedWriter(fwtotal);
//				BufferedWriter bwcontent = new BufferedWriter(fwcontent);

//				bwtotal.write(rs.getString(2)+"::"+rs.getString(3));
//				bwtitle.write(rs.getString(2));
//				bwcontent.write(rs.getString(3));
//				bwcontent.close();
//				bwtitle.close();
//				bwtotal.close();
				i = rs.getInt(1);
			}
			rs.close();
			st.close();
			connect.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     System.out.println("done");
	}

}
