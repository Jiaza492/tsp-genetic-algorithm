package gadesign.web;

import gadesign.algoritms.OverallBest;
import gadesign.entitys.Tool;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 * 
 */

public class DoDrawServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoDrawServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String filepath = request.getParameter("myFile");
		// System.out.println(filepath);
		File obFile = null;
		if (request.getParameter("result") != null) {
			obFile = new File(request.getParameter("result"));
		} else {
			System.out.println("启用备用地址！");
			obFile = new File("C:/Users/ZIANG/Documents/输出结果.txt");
		}
		List<String[][]> allStationList = new ArrayList<String[][]>();
		List<double[]> pathList = new ArrayList<double[]>();

		if (obFile.length() > 0) {
			List<OverallBest> oblist = this.reserializedOb(obFile);
			for (int k = 0; k < oblist.size(); k++) {
				// 存储路径顺序
				OverallBest ob = oblist.get(k);
				double[] path = ob.getBestPhenotype();
				pathList.add(path);
				// 输出检查
				System.out.print("(");
				for (int i = 0; i < ob.getBestPhenotype().length; i++) {
					System.out.print((int) ob.getBestPhenotype()[i] + ",");
				}
				System.out.println(")");
				// 存储站点坐标信息
				String[][] stationList = this.readStationList(k,
						request.getParameter("stationList"));
				allStationList.add(stationList);
			}
			System.out.println("数据导入成功！");
		} else {
			request.setAttribute("msg", "文件不存在！");

		}

		request.setAttribute("pathList", pathList);
		request.setAttribute("allStationList", allStationList);

		request.getRequestDispatcher("/WEB-INF/jsp/map/mapDrawByStation.jsp").forward(
				request, response);

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 * 
	 */

	/**
	 * 
	 * @author Jia Ziang
	 * @see ziangj92@gmail.com
	 * @since 06/01/2014
	 * @version 1.0
	 * 
	 */

	@Deprecated
	private String[][] readCord(OverallBest ob, int k, String stationListPath) {
		String[][] cord = null;
		Workbook wb = null;
		try {
			wb = Workbook.getWorkbook(new File(stationListPath));
			Sheet sht = wb.getSheet(k);
			cord = new String[ob.getBestPhenotype().length][2];
			for (int i = 0; i < ob.getBestPhenotype().length; i++) {
				System.out.print("行驶至站点：" + ob.getBestPhenotype()[i]);
				String lgt = sht.getCell(2, (int) ob.getBestPhenotype()[i])
						.getContents();
				String lat = sht.getCell(3, (int) ob.getBestPhenotype()[i])
						.getContents();
				System.out.println("坐标：" + lgt + "," + lat);
				cord[i][0] = lgt;
				cord[i][1] = lat;
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			wb.close();
		}

		return cord;
	}

	private String[][] readStationList(int k, String stationListPath) {
		String[][] stationList = null;
		Workbook wb = null;

		try {
			wb = Workbook.getWorkbook(new File(stationListPath));
			Sheet sheet = wb.getSheet(k);
			stationList = new String[sheet.getRows() - 1][2];
			for (int i = 1; i < sheet.getRows(); i++) {
				String lgt = sheet.getCell(2, i).getContents();
				String lat = sheet.getCell(3, i).getContents();
				stationList[i - 1][0] = lgt;
				stationList[i - 1][1] = lat;
				System.out.println(lgt + "," + lat);
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			wb.close();
		}
		return stationList;
	}

	private List<OverallBest> reserializedOb(File obFile) {
		List<OverallBest> oblist = null;

		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(obFile));
			oblist = (List<OverallBest>) ois.readObject();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return oblist;

	}

	public void init() throws ServletException {
		// Put your code here
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
