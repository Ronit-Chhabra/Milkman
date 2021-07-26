package ronit.subronit;
import java.io.*;
public class Datainoutstream {

	public static void main(String[] args) 
	{
        int Productid=1;
        String productName = "Laptop";
        int quantity=2;
        float price=1000000;
        int Productid2=2;
        String productName2 = "Bike";
        int quantity2=3;
        float price2=240000;
        try
        {
        	FileOutputStream fos =new FileOutputStream("products.txt",true);
        	DataOutputStream dos = new DataOutputStream(fos);
        	dos.writeInt(Productid);
        	dos.writeUTF(productName);
        	dos.writeInt(quantity);
        	dos.writeFloat(price);
        	dos.writeInt(Productid2);
        	dos.writeUTF(productName2);
        	dos.writeInt(quantity2);
        	dos.writeFloat(price2);
        	dos.flush();
        	dos.close();
        	FileInputStream fis = new FileInputStream("products.txt");
        	DataInputStream dis = new DataInputStream(fis);
        
        try
        {
        	while(true)
        	{
        		Productid=dis.readInt();
        		System.out.println("Id:"+Productid);
        		productName=dis.readUTF();
        		System.out.println("Name:"+productName);
        	    quantity=dis.readInt();
        	    System.out.println("Quantity:"+ quantity);
        	    price=dis.readFloat();
        	    System.out.println("price = "+price);
        	}
        }
	     catch(Exception ex){}
        dis.close();
        }
        catch(IOException e)
        {e.printStackTrace();}
	}

}
