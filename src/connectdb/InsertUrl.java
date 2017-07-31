package connectdb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class InsertUrl {
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String datestr = "2017-05-15";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datestr);
		ArrayList<String> lines = new ArrayList<>();

		HashMap<String, Set<String>> Urls = new HashMap<String, Set<String>>();
		//
		// try {
		// FileReader in = new
		// FileReader("/home/hadoop/Documents/datatag/adx_URL.txt");
		// FileWriter out = new
		// FileWriter("/home/hadoop/Documents/datatag/Url.txt");
		// BufferedReader br = new BufferedReader(in);
		// BufferedWriter bw = new BufferedWriter(out);
		// String temp;
		//
		// while((temp=br.readLine())!=null){
		// lines.add(temp);
		// }
		//
		// for(int i=0;i<lines.size();i++){
		// String temp1= lines.get(i);
		// for(int j=i+1;j<lines.size();i++){
		// if(temp1.equals(lines.get(j))){
		// lines.remove(j);
		// }
		// }
		// out.write(temp1);
		// }
		//
		// String temp1;
		// while ((temp1 = br.readLine()) != null) {
		// String temp2 = br.readLine();
		// while (temp1.equals(temp2)) {
		// temp2 = br.readLine();
		// }
		// out.write(temp1 + "\n");
		// temp1 = temp2;
		//
		// }
		// out.close();
		// System.out.println("done");
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		try {
			FileReader in = new FileReader("/home/hadoop/Documents/tagproject/Url5.txt");
			BufferedReader br = new BufferedReader(in);


			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dang cho ket noi");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.23.191:3306/url_content", "hieupd", "k7VUKjgLwCcL");
			System.out.println("connect");
			Statement stmt = con.createStatement();
			
			String temp;
			while((temp=br.readLine())!=null){
				byte bytes[] = temp.getBytes();
				Checksum checksum = new CRC32();
				checksum.update(bytes, 0, bytes.length);
				long checksumvalue = checksum.getValue();
				 stmt.executeUpdate("insert into Url(url,hash,lastUpdate,status) values(\"" + temp + "\"," + checksumvalue + ",\"" + "2017-06-19" + "\",0)");
//				System.out.println("insert into Url(url,hash,lastUpdate,status) values(\"" + temp + "\"," + checksumvalue + ",\"" + "2017-06-15" + "\",0)");
			}
			System.out.println("done");

			
			stmt.close();
			con.close();
			// stmt.executeUpdate("insert into
			// Url(url,hash,lastUpdate,status) values(\"" + url_next + "\","
			// + checksumvalue + ",\"" + "2017-05-15" + "\",1)");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
