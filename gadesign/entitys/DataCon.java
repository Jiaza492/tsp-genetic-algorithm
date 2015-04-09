package gadesign.entitys;

import gadesign.entitys.utils.ODdata;
import gadesign.entitys.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class DataCon {

	private Map<Integer, ArrayList<Double>> distance;
	private Map<Integer, ArrayList<Double>> time;
	private Map<Integer, ArrayList<String>> path;
	private List<Integer> peopleNum;

	private Map<Integer, ArrayList<ODdata>> odd;

	public DataCon() {

	}

	// 本地用
	public DataCon(String dataPath, int sheetNum, String cityListPath) {
		this.time = Util.dataInput(dataPath, sheetNum);
		//this.distance = Util.dataInput(dataPath, 1);
		//this.path = Util.pathDataInput(dataPath, 2);
		this.peopleNum = Util.peopleNumInput(cityListPath, sheetNum);
	}

//	// 网络URL用
//	@Deprecated
//	public DataCon(String key, String cityXlsPath) {
//		String cityListPath = "D:/PrivateFile/毕业设计/非凸非光滑优化问题的遗传算法/研究成果/聚类结果.xls";
//		this.odd = Util.dataInputByName(key, cityXlsPath,cityListPath);
//	}

	//网络URL用
	public DataCon(String key, String cityXlsPath, String cityListPath){
		this.odd = Util.dataInputByCord(key, cityXlsPath, cityListPath);
	}
	//get&set
	public Map<Integer, ArrayList<Double>> getDistance() {
		return distance;
	}

	public void setDistance(Map<Integer, ArrayList<Double>> distance) {
		this.distance = distance;
	}

	public Map<Integer, ArrayList<Double>> getTime() {
		return time;
	}

	public void setTime(Map<Integer, ArrayList<Double>> time) {
		this.time = time;
	}

	public Map<Integer, ArrayList<String>> getPath() {
		return path;
	}

	public void setPath(Map<Integer, ArrayList<String>> path) {
		this.path = path;
	}

	public Map<Integer, ArrayList<ODdata>> getOdd() {
		return odd;
	}

	public void setOdd(Map<Integer, ArrayList<ODdata>> odd) {
		this.odd = odd;
	}

	public List<Integer> getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(List<Integer> peopleNum) {
		this.peopleNum = peopleNum;
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