package gadesign.entitys.utils;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

import gadesign.mapinfos.impls.MapInfoImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Util {

	// 调用本地数据表,将时间表格距离存入Map
	public static Map<Integer, ArrayList<Double>> dataInput(String dataPath,
			int sheetNum) {
		Map<Integer, ArrayList<Double>> map = new HashMap<Integer, ArrayList<Double>>();
		try {
			Workbook workbook = Workbook.getWorkbook(new File(dataPath));
			Sheet sheet = null;

			if (workbook.getSheet(sheetNum) == null) {
				System.out.println("工作表为空！");

			} else {
				sheet = workbook.getSheet(sheetNum);
				for (int i = 1; i <= sheet.getRows() - 1; i++) {
					ArrayList<Double> distn = new ArrayList<Double>();
					for (int j = 1; j <= sheet.getColumns() - 1; j++) {
						double result = Double.parseDouble(sheet.getCell(j, i)
								.getContents());
						// System.out.print(result+"\t");
						distn.add(result);
					}
					// System.out.println();
					map.put(i - 1, distn);
				}
			}
			workbook.close();
			return map;
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	// 将表格中的人数信息存入List
	public static List<Integer> peopleNumInput(String cityListPath, int sheetNum) {
		List<Integer> peopleList = new ArrayList<Integer>();

		try {
			Workbook workbook = Workbook.getWorkbook(new File(cityListPath));
			Sheet sheet = null;

			if (workbook.getSheet(sheetNum) != null) {
				sheet = workbook.getSheet(sheetNum);

				for (int j = 1; j <= sheet.getRows() - 1; j++) {
					String result = sheet.getCell(4, j).getContents();
					//System.out.print(result+"\t");
					peopleList.add(Integer.parseInt(result));
					//System.out.print(result + ",");
				}
			} else {
				System.out.println("工作表为空");
			}
			workbook.close();
			return peopleList;
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return peopleList;
	}

	// 调用本地数据表，将路径信息表格存入Map
	@Deprecated
	public static Map<Integer, ArrayList<String>> pathDataInput(
			String dataPath, int sheetNum) {
		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		try {
			Workbook workbook = Workbook.getWorkbook(new File(dataPath));
			Sheet sheet = null;

			if (workbook.getSheet(sheetNum) != null) {
				sheet = workbook.getSheet(sheetNum);

				for (int i = 1; i <= sheet.getRows() - 1; i++) {
					ArrayList<String> distn = new ArrayList<String>();
					for (int j = 1; j <= sheet.getColumns() - 1; j++) {
						String result = sheet.getCell(j, i).getContents();
						// System.out.print(result+"\t");
						distn.add(result);
					}
					// System.out.println();
					map.put(i - 1, distn);
				}
			} else {
				System.out.println("工作表为空");
			}
			workbook.close();
			return map;
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 
     * @author Jia Ziang
	 * @see ziangj92@gmail.com
	 * @since 06/01/2014
	 * @version 1.0
	 *
	 */
	
	@Deprecated
	// 网络URL调用,关键字调用,将Xml存入Excel
	public static Map<Integer, ArrayList<ODdata>> dataInputByName(String key,
			String cityXlsPath, String cityListPath) {
		Map<Integer, ArrayList<ODdata>> map = new HashMap<Integer, ArrayList<ODdata>>();

		try {
			Workbook workbook = Workbook.getWorkbook(new File(cityListPath));
			WritableWorkbook bookcopy = Workbook.createWorkbook(new File(
					cityXlsPath));
			Sheet sheet = null;
			WritableSheet sheet0 = bookcopy.createSheet("时间", 0);
			WritableSheet sheet1 = bookcopy.createSheet("距离", 1);
			WritableSheet sheet2 = bookcopy.createSheet("路径描述", 2);
			if (bookcopy.getSheet(0) == null) {
				System.out.println("工作表为空！");

			} else {
				sheet = workbook.getSheet(0);

				for (int i = 1; i <= sheet.getRows() - 1; i++) {
					ArrayList<ODdata> distn = new ArrayList<ODdata>();
					for (int j = 1; j <= sheet.getRows() - 1; j++) {

						Place origin = new Place();
						Place destination = new Place();

						origin.setNum(sheet.getCell(0, i).getContents());
						origin.setName(sheet.getCell(1, i).getContents());
						origin.setLat(sheet.getCell(2, i).getContents());
						origin.setLng(sheet.getCell(3, i).getContents());
						origin.setPeopleNum(sheet.getCell(4, i).getContents());

						destination.setNum(sheet.getCell(0, j).getContents());
						destination.setName(sheet.getCell(1, j).getContents());
						destination.setLat(sheet.getCell(2, j).getContents());
						destination.setLng(sheet.getCell(3, j).getContents());
						destination.setPeopleNum(sheet.getCell(4, j)
								.getContents());

						System.out.print(origin.getName() + ","
								+ destination.getName());

						ODdata result = null;
						if (origin.getNum().equals(destination.getNum())) {
							result = new ODdata(100000.0, 100000.0);
						} else {
							result = (new MapInfoImpl(key).mapInfoRequest(
									origin.getName(), destination.getName()));
							// 将路径信息存入表格
							String pathString = "";
							for (int k = 0; k < result.getPath().size(); k++) {

								String lnglat = result.getPath().get(k)[0]
										+ "-" + result.getPath().get(k)[1];
								pathString += lnglat + ",";
							}
							sheet2.addCell(new Label(j, i, pathString));

						}
						sheet0.addCell(new Label(j, i, result.getTime() + ""));// 将时间信息存入表格
						sheet1.addCell(new Label(j, i, result.getDistance()
								+ ""));// 将距离信息存入表格
						System.out.println("(" + result.getDistance() + ","
								+ result.getTime() + ")" + "\t");

						// double result = 0;
						distn.add(result);
					}
					System.out.println();
					map.put(i - 1, distn);
				}
			}
			bookcopy.write();
			workbook.close();
			bookcopy.close();
			return map;
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	// 网络URL调用,坐标调用，将Xml存入Excel
	public static Map<Integer, ArrayList<ODdata>> dataInputByCord(String key,
			String cityXlsPath, String cityListPath) {
		Map<Integer, ArrayList<ODdata>> map = new HashMap<Integer, ArrayList<ODdata>>();
		Workbook workbook = null;
		WritableWorkbook bookcopy = null;

		try {
			workbook = Workbook.getWorkbook(new File(cityListPath));
			bookcopy = Workbook.createWorkbook(new File(
					cityXlsPath));
			Sheet sheet = null;
			for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
				WritableSheet sheet0 = bookcopy.createSheet("时间" + k, k);
				// WritableSheet sheet1 = bookcopy.createSheet("距离", 1);
				// WritableSheet sheet2 = bookcopy.createSheet("路径描述", 2);

				sheet = workbook.getSheet(k);

				for (int i = 1; i <= sheet.getRows() - 1; i++) {
					ArrayList<ODdata> distn = new ArrayList<ODdata>();
					for (int j = 1; j <= sheet.getRows() - 1; j++) {

						Place origin = new Place();
						Place destination = new Place();

						origin.setNum(sheet.getCell(0, i).getContents());
						origin.setName(sheet.getCell(1, i).getContents());
						origin.setLat(sheet.getCell(2, i).getContents());
						origin.setLng(sheet.getCell(3, i).getContents());
						origin.setPeopleNum(sheet.getCell(4, i).getContents());

						destination.setNum(sheet.getCell(0, j).getContents());
						destination.setName(sheet.getCell(1, j).getContents());
						destination.setLat(sheet.getCell(2, j).getContents());
						destination.setLng(sheet.getCell(3, j).getContents());
						destination.setPeopleNum(sheet.getCell(4, j)
								.getContents());

						System.out.print(origin.getName() + ","
								+ destination.getName());

						ODdata result = null;
						if (origin.getNum().equals(destination.getNum())) {
							result = new ODdata(100000.0, 100000.0);
						} else {
							result = (new MapInfoImpl(key).mapInfoRequest(
									origin, destination));
							// 将路径信息存入表格
							// String pathString = "";
							// for (int k = 0; k < result.getPath().size(); k++)
							// {
							//
							// String lnglat = result.getPath().get(k)[0]
							// + "-" + result.getPath().get(k)[1];
							// pathString += lnglat + ",";
							// }
							// sheet2.addCell(new Label(j, i, pathString));
							//
						}
						sheet0.addCell(new Label(j, i, result.getTime() + ""));// 将时间信息存入表格
						// sheet1.addCell(new Label(j, i, result.getDistance()
						// + ""));// 将距离信息存入表格
						System.out.println("(" + result.getDistance() + ","
								+ result.getTime() + ")" + "\t");

						// double result = 0;
						distn.add(result);
					}
					System.out.println();
					map.put(i - 1, distn);
				}
			}

			bookcopy.write();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				workbook.close();
				bookcopy.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return map;
	}
	
	public static Workbook getWorkbook(String cityPath){
		Workbook workbook = null;
		
		try {
			workbook = Workbook.getWorkbook(new File(cityPath));
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return workbook;
	}

}

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */
