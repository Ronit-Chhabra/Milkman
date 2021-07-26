package ronit.subronit;

import java.io.*;

public class Fileinfileout {

	public static void main(String[] args)throws Exception 
	{
		copyImage();
    }
     static void copyImage()
     {
    	 FileInputStream read=null;
    	 FileOutputStream write=null;
    	 try
    	 {
    		 File src =new File("IMG_20170528_135626.jpg");
    	     read = new FileInputStream(src);
    		 File des =new File("java.jpg");
    		 write=new FileOutputStream(des);
    	     int c,count=0;
    	     while((c=read.read())!=-1)
    	     {
    	    	 write.write(c);
    	    	 count++;
    	     }
    	     System.out.println(count+ "bytes copied");
    	 }
    	 catch(FileNotFoundException ex)
    	 {
    		 ex.printStackTrace();
    	 }
    	 catch(IOException ex)
    	 {
    		 ex.printStackTrace();
    	 }
    	 try
    	 {
    		 read.close();
    		 write.close();
    	 }
    	 catch(IOException ex)
    	 {
    		 ex.printStackTrace();
    	 }
     }
}
