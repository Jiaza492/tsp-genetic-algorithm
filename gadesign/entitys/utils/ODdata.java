package gadesign.entitys.utils;

import java.util.List;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class ODdata {

	//两点之间路径数据
	private double distance;
	private double time;
	private List<String[]> path;
	
	public ODdata (){
		
	}
	
	//两点重合，原地待命
	public ODdata (Double stayPosition,Double stayTime){
		this.distance = stayPosition;
		this.time = stayTime;
		this.path = null;
		
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public List<String[]> getPath() {
		return path;
	}
	public void setPath(List<String[]> path) {
		this.path = path;
	}
	
	
	
	
}
