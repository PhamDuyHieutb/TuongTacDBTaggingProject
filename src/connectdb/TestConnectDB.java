package connectdb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TestConnectDB {

	@SuppressWarnings("null")
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String datestr = "2017-05-15";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datestr);
		ArrayList<String> lines = new ArrayList<>();

		HashMap<String, Set<String>> Urls = new HashMap<String, Set<String>>();
//
//		 try {
//		 FileReader in = new
//		 FileReader("/home/hadoop/Documents/datatag/adx_URL.txt");
//		 FileWriter out = new
//		 FileWriter("/home/hadoop/Documents/datatag/Url.txt");
//		 BufferedReader br = new BufferedReader(in);
//		 BufferedWriter bw = new BufferedWriter(out);
//		 String temp;
//		
//		 while((temp=br.readLine())!=null){
//		 lines.add(temp);
//		 }
//		
//		 for(int i=0;i<lines.size();i++){
//		 String temp1= lines.get(i);
//		 for(int j=i+1;j<lines.size();i++){
//		 if(temp1.equals(lines.get(j))){
//		 lines.remove(j);
//		 }
//		 }
//		 out.write(temp1);
//		 }
//
//		 String temp1;
//		 while ((temp1 = br.readLine()) != null) {
//		 String temp2 = br.readLine();
//		 while (temp1.equals(temp2)) {
//		 temp2 = br.readLine();
//		 }
//		 out.write(temp1 + "\n");
//		 temp1 = temp2;
//		
//		 }
//		 out.close();
//		 System.out.println("done");
//		 } catch (Exception e) {
//		 // TODO: handle exception
//		 }

		try {
			FileReader in = new FileReader("/home/hadoop/Documents/datatag/Url_adn.csv");
			FileWriter out = new FileWriter("/home/hadoop/Documents/datatag/Url_pro.csv");
			BufferedReader br = new BufferedReader(in);
			BufferedWriter bw = new BufferedWriter(out);

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("dang cho ket noi");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.23.191/url_content", "hieupd",
					"k7VUKjgLwCcL");
			System.out.println("connect");
			Statement stmt = con.createStatement();

			String temp;
			while ((temp = br.readLine()) != null) { // lay du lieu cho vao
				temp.replaceAll("\\s","");										// hashmap
				String[] l = temp.split(",");
				Set<String> tags = new HashSet<String>();
				if(l.length==2){
				tags.add(l[1]);
				}
				if (l.length ==3) {
					tags.add(l[1].substring(1));
					tags.add(l[2].substring(0, (l[2].length() - 1)));
				}
				if(l.length==4){
					tags.add(l[1].substring(1));
					tags.add(l[2]);
					tags.add(l[3].substring(0, (l[3].length() - 1)));
				}
				if(l.length==5){
					tags.add(l[1].substring(1));
					tags.add(l[2]);
					tags.add(l[3]);
					tags.add(l[4].substring(0, (l[4].length() - 1)));
				}
				if (Urls.get(l[0]) != null) {
					Set<String> a = Urls.get(l[0]);
					// for(String s: tags){
					a.addAll(tags);
					
					// }
					Urls.put(l[0], a);
				} else {
					Urls.put(l[0], tags);

				}

			}
			

			Set set2 = Urls.entrySet();
			Iterator i2 = set2.iterator();
			
			int a = 364;

			while (i2.hasNext()) {

				Map.Entry U2 = (Map.Entry) i2.next();
				String url_next = (String) U2.getKey();
		
				Set tag_next = (Set) U2.getValue();
//				System.out.println(tag_next + url_next);
				byte bytes[] = url_next.getBytes();
				Checksum checksum = new CRC32();
				checksum.update(bytes, 0, bytes.length);
				long checksumvalue = checksum.getValue();
				for (Iterator<String> s = tag_next.iterator(); s.hasNext();) {
					String i;
					if ((i=s.next())!=null) {
						int snext = Integer.parseInt(i);
						stmt.executeUpdate("insert into Tag(id_url,tag) values(" + a + "," + snext + ")");
					}
				}

				// stmt.executeUpdate("insert into
				// Url(url,hash,lastUpdate,status) values(\"" + url_next + "\","
				// + checksumvalue + ",\"" + "2017-05-15" + "\",1)");
				a++;
			}

			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
