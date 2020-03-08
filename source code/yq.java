import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.System;

public class yq {
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		int tag = 0; // 0： 未处理第一行
		String prov = "";  //  省份
		try {
			FileReader fr = new FileReader("D:\\MAKOITUNG\\SWUN\\ComputerScience1702\\2019-2020Second\\软件工程\\课程实践\\实践2\\yq_in.txt");
			br = new BufferedReader(fr); 
			
			FileWriter fw = new FileWriter("D:\\MAKOITUNG\\SWUN\\ComputerScience1702\\2019-2020Second\\软件工程\\课程实践\\实践2\\yq_out.txt");
			bw = new BufferedWriter(fw); 
			
			String s = "";  
			while ((s = br.readLine())!=null) {
				s = s.trim();
		 	    int pos = s.indexOf('省');
			
			    if (prov.equals(s.substring(0,pos+1))) { // 与上一行同一个省份
			    	bw.write(s.substring(pos+2));
			    	bw.newLine();
			    }
		    	else{  // 与上一行不同省份
		    		if (tag!=0) {
		    			bw.newLine(); // tag=1 即不是第一个省份 空出一行
	    			}
			    	prov = s.substring(0,pos+1);
		    		bw.write(prov);  // 将省份单独输出
		    		bw.newLine();
		    		bw.write(s.substring(pos+2));
			    	bw.newLine();
			    } 

		    	tag = 1;// 1：已读取第一行
				bw.flush();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
				br.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Completed!");
	}
}
