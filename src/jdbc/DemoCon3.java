package jdbc;
import java.util.Scanner;
import java.sql.*;

public class DemoCon3 {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		try(sc;){
			try {
				Connection con=DriverManager.getConnection
						("jdbc:oracle:thin:@localhost:1521:orcl","system","harsh");
						PreparedStatement ps1=con.prepareStatement("insert into product2 values(? ,?,?,?)");
						PreparedStatement ps2=con.prepareStatement("select * from product2");
						PreparedStatement ps3=con.prepareStatement("select * from product2 where pcode=?");
						PreparedStatement ps4=con.prepareStatement("update product2 set pestenprice=?, prqty=prqty+? where pcode=?");
						PreparedStatement ps5=con.prepareStatement("delete from product2 where pcode=?");
						while(true) {
							System.out.println("==choice===");
							System.out.println("1.Addproduct\n"+"2.viewAllProduct\n"+"3.viewproductByCode\n"+
							"4.updateProductByCode\n"+"5.DeleteproductByCode\n"+"6.exit");
							System.out.println("enter choice");
							switch(Integer.parseInt (sc.nextLine())) {
							case 1:
								System.out.println("enter the code");
								String code=sc.nextLine();
								System.out.println("enter the name");
								String Name=sc.nextLine();
								System.out.println("enter the price");
								float pprice=Float.parseFloat(sc.nextLine());
								System.out.println("enter the qty");
								int prqty=Integer.parseInt(sc.nextLine());
								
								ps1.setString(1,  code);
								ps1.setString(2, Name);
								ps1.setFloat(3, pprice);
								ps1.setInt(4, prqty);
								int k=ps1.executeUpdate();
								if(k>0) {
									System.out.println("product insert suscceffuly");
								}
								break;
							case 2:
								ResultSet rs=ps2.executeQuery();
								while(rs.next()) {
									System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"
											+rs.getInt(4));
								}
								break;
							case 3:
								System.out.println("enter the procode:");
								String cd= sc.nextLine();
								ps3.setString(1, cd); 
								ResultSet rs1= ps3.executeQuery();
								if(rs1.next()) {
									System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"
											+rs1.getInt(4));
								}else {
									System.out.println("invalid procode....");
									
								}
							break;
							case 4:
								System.out.println("enter the procode:");
								String cd2=sc.nextLine();
								ps3.setString(1, cd2);
								ResultSet rs3=ps3.executeQuery();
								if(rs3.next()) {
									System.out.println("old price"+rs3.getFloat(3));
									System.out.println("enter a new price");
									float nprice=Float.parseFloat(sc.nextLine());
									System.out.println("available qty:"+rs3.getInt(4));
									System.out.println("enter a qty:");
									String prqty1 = sc.nextLine();
									
									ps4.setFloat(1, nprice);
									ps4.setString(2,cd2);
									ps4.setString(3, prqty1);
									
									int z=ps4.executeUpdate();
									if(z>0) {
										System.out.println("product Details update:");
									}else {
										System.out.println("invalid procode...");
									}
									
								}
								break;
							case 5:
								System.out.println("enter the procode");
								String cd3= sc.nextLine();
								ps3.setString(1, cd3);
								ResultSet rs4=ps3.executeQuery();
								if(rs4.next()) {
									ps5.setString(1, cd3);
									int y=ps5.executeUpdate();
									if(y>0) {
										System.out.println("product details deleted succsesfully:");
										
									}else {
										System.out.println("invalid productcode");
									}
								}
								break;
							case 6:
								System.out.println("operation on product table stoped");
								System.exit(0);
								break;
								default:
									System.out.println("inavlid choice");
							}
						}
			}catch(Exception e) {
				e.printStackTrace();
			
			
								
					
									
								}
			
							}
						}
						
			
		
		
	

}
