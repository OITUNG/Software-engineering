import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.System;

public class yq_3 {
	public static void main(String[] args) {
		// �����洢��args������
		System.out.println(args.length);
		for (String s : args) {
			System.out.println(s + "\t");
		}
		BufferedReader br = null;
		BufferedWriter bw = null;
		String[][] data = new String[100][100];
		int tag = 0;
		int i, j;
		try {
			File path = new File("D:/yqtest"); // �����ļ���·��
			File in = new File(path, args[0]);
			File out = new File(path, args[1]);
			i = 0;
			j = 2;
			data[0][1] = "";
			FileReader fr = new FileReader(in);
			br = new BufferedReader(fr);

			String s = "";// ���������ļ��е�ÿ��

			while ((s = br.readLine()) != null) {
				s = s.trim();
				int pos = s.indexOf('ʡ');

				if (data[i][1].equals(s.substring(0, pos + 1))) { // ����һ��ͬһ��ʡ��
					data[i][j] = s.substring(pos + 2);
					j = j + 1;
				} 
				else { // ����һ�в�ͬʡ��
					if (tag==0) {  // tag=0 �ǵ�һ��ʡ�� 
						data[i][1] = s.substring(0, pos + 1);
						data[i][j] = s.substring(pos + 2);
						j = j + 1;
						tag = 1;
					}					
					else {// ���ǵ�һ��ʡ��
						data[i][0] = j + ""; // ��¼��һ���������ʡ�ݵķ�֧����
						i = i + 1;
						j = 2;
						data[i][1] = s.substring(0, pos + 1);
						data[i][j] = s.substring(pos + 2);
						j = j + 1;
					}
				}
			}
			data[i][0] = j + "";
			FileWriter fw = new FileWriter(out);
			bw = new BufferedWriter(fw);
			int row = i;
			System.out.println(row);
			if (args.length == 3) {
				for (i = 0; i <= row; i++) {
					if (args[2].equals(data[i][1])) {
						bw.write(data[i][1]);
						bw.newLine();
						for (j = 2; j < Integer.parseInt(data[i][0]); j++) {
							bw.write(data[i][j]);
							bw.newLine();
						}
						break;
					}
				}
			} else {
				for (i = 0; i <= row; i++) {
					bw.write(data[i][1]);
					bw.newLine();
					for (j = 2; j < Integer.parseInt(data[i][0]); j++) {
						bw.write(data[i][j]);
						bw.newLine();
					}
					bw.newLine();
				}
			}
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Completed!");
	}
}
