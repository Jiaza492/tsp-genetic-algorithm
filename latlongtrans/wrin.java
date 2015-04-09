package latlongtrans;



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

public class wrin {
public static int tm;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String fileName = "d:" + File.separator + "test.xls";
		System.out.println(wrin.readExcel(fileName));
	//	String fileName1 = "d:" + File.separator + "abc.xls";
	//	wrin.writeExcel(fileName1);
	}

	/**
	 * 從excel文件中讀取所有的內容
	 * 
	 * @param file
	 *            excel文件
	 * @return excel文件的內容
	 */
	public static String readExcel(String fileName) {
		String[] gege=new String[999];
		StringBuffer sb = new StringBuffer();
		Workbook wb = null;
		try {
			// 构造Workbook（工作薄）对象
			wb = Workbook.getWorkbook(new File(fileName));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wb == null)
			return null;

		// 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了
		Sheet[] sheet = wb.getSheets();

		if (sheet != null && sheet.length > 0) {
			// 对每个工作表进行循环
			for (int i = 0; i < sheet.length; i++) {
				// 得到当前工作表的行数
				int rowNum = sheet[i].getRows();
				tm= rowNum;
				System.out.println(tm);
				//------------------------------------------------
				for (int j = 0; j < rowNum; j++) {
					// 得到当前行的所有单元格
					Cell[] cells = sheet[i].getRow(j);
					if (cells != null && cells.length > 0) {
						// 对每个单元格进行循环
						for (int k = 0; k < cells.length; k++) {
							// 读取当前单元格的值
							String cellValue = cells[k].getContents();
							gege[j]=cellValue;
							
							sb.append(k+"---"+j+gege[j]);//显示
						}
					}
					sb.append("\r\n");
				}
				sb.append("\r\n");
			}
			LatitudeUtils.main(gege);
		}
		
		// 最后关闭资源，释放内存
		wb.close();
		return sb.toString();
	}
}