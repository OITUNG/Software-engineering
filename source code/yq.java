import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.System;

public class yq {
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		int tag = 0; // 0�� δ�����һ��
		String prov = "";  //  ʡ��
		try {
			FileReader fr = new FileReader("D:\\MAKOITUNG\\SWUN\\ComputerScience1702\\2019-2020Second\\�������\\�γ�ʵ��\\ʵ��2\\yq_in.txt");
			br = new BufferedReader(fr); 
			
			FileWriter fw = new FileWriter("D:\\MAKOITUNG\\SWUN\\ComputerScience1702\\2019-2020Second\\�������\\�γ�ʵ��\\ʵ��2\\yq_out.txt");
			bw = new BufferedWriter(fw); 
			
			String s = "";  
			while ((s = br.readLine())!=null) {
				s = s.trim();
		 	    int pos = s.indexOf('ʡ');
			
			    if (prov.equals(s.substring(0,pos+1))) { // ����һ��ͬһ��ʡ��
			    	bw.write(s.substring(pos+2));
			    	bw.newLine();
			    }
		    	else{  // ����һ�в�ͬʡ��
		    		if (tag!=0) {
		    			bw.newLine(); // tag=1 �����ǵ�һ��ʡ�� �ճ�һ��
	    			}
			    	prov = s.substring(0,pos+1);
		    		bw.write(prov);  // ��ʡ�ݵ������
		    		bw.newLine();
		    		bw.write(s.substring(pos+2));
			    	bw.newLine();
			    } 

		    	tag = 1;// 1���Ѷ�ȡ��һ��
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
