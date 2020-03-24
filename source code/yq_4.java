import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.System;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class yq_4 {
	public static String[][] data = new String[100][100];
	public static int[][] num = new int [100][100]; // 记录省份及地区的数量 （以整形形式，便于比较）
	
	public static void sortByChinese(List<String> values) {
		final Collator pyComparator = Collator.getInstance(Locale.CHINA); // 中文排序关键对象
		Collections.sort(values,new Comparator<String>() {
		
		@Override
		public int compare(String s1,String s2) {
			int result = pyComparator.compare(s1, s2);
			return result;
		}
	});
}

	public static void sort(int i,int j) { // i是哪个省份  j是省份的地区数
		int k,p;
		for (k=3; k<j; k++)  { // 处理上一省份的地区排序
			for (p=2; p<j-i; p++) {
				if (num[i][p]<num[i][p+1]) {
					int t1 = num[i][p];  // 交换数字
					num[i][p] = num[i][p+1];
					num[i][p+1] = t1;
					String t2 = data[i][p]; // 交换地区名
					data[i][p] = data[i][p+1];
					data[i][p+1] = t2;
				}
				if (num[i][p]==num[i][p+1]) {  // 地区数字相等 则按地区名的拼音排序
					List<String> list = new ArrayList<String>();
					list.add(data[i][p]);
					list.add(data[i][p+1]);
					sortByChinese(list);
					String[] str = list.toArray(new String[list.size()]);
					if (str[0].equals(data[i][p])==false) { 
						int t1 = num[i][p];  // 交换数字
						num[i][p] = num[i][j];
						num[i][p+1] = t1;
						String t2 = data[i][p]; // 交换地区名
						data[i][p] = data[i][p+1];
						data[i][p+1] = t2;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		// 参数存储在args数组中
		System.out.println(args.length);
		for (String s : args) {
			System.out.println(s + "\t");
		}
		BufferedReader br = null;
		BufferedWriter bw = null;
//		String[][] data = new String[100][100];
//		int[][] num = new int [100][100]; // 记录省份及地区的数量 （以整形形式，便于比较）
		int[] order = new int[100]; // 记录省份输出顺序
		int tag = 0;
		int i, j, k, p;
		
		try {
			File path = new File("D:/yqtest"); // 读入文件的路径
			File in = new File(path, args[0]);
			File out = new File(path, args[1]);
			i = 0;
			j = 2;
			data[0][1] = "";
			FileReader fr = new FileReader(in);
			br = new BufferedReader(fr);
			
			String s = "";// 储存输入文件中的每行

			while ((s = br.readLine()) != null) {
				s = s.trim();
				int pos = s.indexOf('省'); // 定位省份
				if (data[i][1].equals(s.substring(0, pos + 1))) { // 与上一处理行同一个省份
					data[i][j] = s.substring(pos + 2);  // 保存地区
					int posn = data[i][j].indexOf('\t');
					num[i][j] = Integer.parseInt(data[i][j].substring(posn+1)); // 保存地区对应数字
					num[i][1] += num[i][j]; // 统计各个省份总数
					j = j + 1;
				} 
				else { // 与上一处理行不同省份
					if (tag==0) {  // tag=0 是第一个省份 
						data[i][1] = s.substring(0, pos + 1);
						data[i][j] = s.substring(pos + 2);
						int posn = data[i][j].indexOf('\t');
						num[i][j] = Integer.parseInt(data[i][j].substring(posn+1)); // 保存地区对应数字
						num[i][1] += num[i][j]; // 统计各个省份总数
						j = j + 1;
						tag = 1;
					}					
					else {// 不是第一个省份
						data[i][0] = j + ""; // 记录上一个处理完成省份的分支个数
						data[i][1] += "\t" + String.valueOf(num[i][1]); // 在省份后面连接总数 方便输出
						sort(i,j-1);
						i = i + 1;
						j = 2;
						data[i][1] = s.substring(0, pos + 1);
						data[i][j] = s.substring(pos + 2);
						int posn = data[i][j].indexOf('\t');
						num[i][j] = Integer.parseInt(data[i][j].substring(posn+1)); // 保存地区对应数字
						num[i][1] += num[i][j]; // 统计各个省份总数
						j = j + 1;
					}
				}
			}
			data[i][0] = j + "";
			data[i][1] += "\t" + String.valueOf(num[i][1]);
			sort(i,j); //处理最后一个省的排序
			int row = i+1; // 省份总个数
			int num1[] = new int[100];
			for (k=0;k<row;k++) { //初始化order数组
				order[k] = k;
				num1[k] = num[k][1];
			}
			
			for (k=1; k<row; k++) { // 处理省份的排列
				for (p=0; p<row-k; p++) {
					if (num[order[p]][1]<num[order[p+1]][1]) {
						int t = order[p];
						order[p] = order[p+1];
						order[p+1] = t;
					}
					if (num[order[p]][1]==num[order[p+1]][1]) {
						List<String> list = new ArrayList<String>();
						list.add(data[order[p]][1]);
						list.add(data[order[p+1]][1]);
						sortByChinese(list);
						String[] str = list.toArray(new String[list.size()]);
						if (str[0].equals(data[order[p]][1])==false) { 
							int t = order[p];
							order[p] = order[p+1];
							order[p+1] = t;
						}
					}
				}
			}
			
			FileWriter fw = new FileWriter(out);
			bw = new BufferedWriter(fw);
			
//			System.out.println(row);
			if (args.length == 3) { //指定省份时，无关顺序
				for (i = 0; i < row; i++) {
					if (args[2].equals(data[i][1].substring(0,data[0][1].indexOf('省')+1))) {
						bw.write(data[i][1]);
						bw.newLine();
						for (j = 2; j < Integer.parseInt(data[i][0]); j++) {
							bw.write(data[i][j]);
							bw.newLine();
						}
						break;
					}
				}
			} else { // 全部输出时 要按从大到小顺序输出
				for (i = 0; i < row; i++) { 
					bw.write(data[order[i]][1]);
					bw.newLine();
					for (j = 2; j < Integer.parseInt(data[order[i]][0]); j++) {
						bw.write(data[order[i]][j]);
						bw.newLine();
					}
					bw.newLine();
				}
			}
			bw.flush();
			for (i=0;i<row;i++) {  // 检验省份排序
				System.out.print(order[i] + "\t");
			}
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
