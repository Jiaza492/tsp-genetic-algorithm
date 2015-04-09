package latlongtrans;


//主程序，从这里运行，wrin负责导入地址，Lati负责转换经纬度，address负责储存经纬度，wrout负责输出excel

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class wrout extends address{

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//
//	//	String fileName = "d:" + File.separator + "test.xls";
//	//	System.out.println(wrout.readExcel(fileName));
//		String fileName1 = "d:" + File.separator + "bcd.xls";
//		wrout.writeExcel(fileName1);
//	}

	
	/**
	 * 把內容寫入excel文件中
	 * 
	 * @param fileName
	 *            要寫入的文件的名稱
	 */
	public static void writeExcel(String fileName) {
		
		WritableWorkbook wwb = null;
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);

			// 下面开始添加单元格
			wrin ggg=new wrin();//**这里是导入D盘的test文件，wrin负责导入
			wrin.main(tj);
		String jww;
		

			//int j=0;int k=0;
			//int[] ag = new int[tj.length]; 
	    	//int[] bg = new int[tw.length]; 
			// for (k=0;k<=20;k++)
			 // {	
				// ag[k]=Integer.parseInt(tj[k]);
			 // bg[k]=Integer.parseInt(tw[k]);
				// }
			for (int i = 0; i < tm; i++) {
				
				for (int j1 = 0; j1 < 2; j1++) {
					// 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
					Label labelC = new Label(0, i, tj[i]);
					Label labelD =new  Label(1, i, tw[i]);
				
					
					try {
						// 将生成的单元格添加到工作表中
						ws.addCell(labelC);
						ws.addCell(labelD);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}

				}
			}

			try {
				// 从内存中写入文件中
				wwb.write();
				// 关闭资源，释放内存
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}

	public void jw(String st[]) {   //jw[]取出经纬度
		String[] jw=new String[999];
		int j=0;
		for (j=0;j<=tm;j++){
		jw[j]=st[j];
		//System.out.println("jwd : "+j+"==="+jw[j]);
		}
}
}


