package gadesign.algoritms;

import gadesign.entitys.DataCon;
import gadesign.populations.Population;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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

public class OverallBest implements Serializable {

	private Map<Integer, Population> popMap;
	private double[] bestPhenotype;
	private String bestGenotype;
	private double bestFtn;
	private double time;
	private int maxGeneration;
	

	public OverallBest() {
		this.popMap = new HashMap<Integer, Population>();// 生成Map，存储每一代的染色体
	}

	public OverallBest(double[] bestPhenotype, String bestGenotype,
			double bestFtn, double time) {
		this.popMap = new HashMap<Integer, Population>();// 生成Map，存储每一代的染色体
		this.bestFtn = bestFtn;
		this.bestGenotype = bestGenotype;
		this.bestPhenotype = bestPhenotype;
		this.time = time;
	}

	// 一次输出每条路径时间以及总时间
	public double outputTime(DataCon dc) {
		List<Double> timeList = new ArrayList<Double>();
		double TIME = 0;
		int count = 0;
		for (int i = 0; i <= this.bestPhenotype.length - 1; i++) {
			if (this.bestPhenotype[i] == 1) {
				count = count + 1;
				System.out.println("开始第" + count + "条路径：");
				double time = 0;
				int j = i + 1;
				while (this.bestPhenotype[j % this.bestPhenotype.length] != 1) {
					double a = 0;
					try {
						a = dc.getTime().get(
								((int) this.bestPhenotype[(j - 1)
										% this.bestPhenotype.length] - 1)).get(
								((int) this.bestPhenotype[(j)
										% this.bestPhenotype.length] - 1));
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("wrong");
					}
					System.out.println("增加时间：" + a / 60 + "分钟");
					time += (a / 60);
					j++;
				}
				timeList.add(time);
				System.out.println(time);
				TIME += time;
			}
		}

		return TIME;
	}

	// public double outputDistance(DataCon dc) {
	// double distance = 0;
	// for (int i = 0; i <= this.bestPhenotype.length - 1; i++) {
	// double a = 0;
	//
	// a = dc.getDistance().get(((int) this.bestPhenotype[i] - 1)).get(
	// ((int) this.bestPhenotype[(i + 1)
	// % this.bestPhenotype.length] - 1));
	// // System.out.println("增加路径："+a);
	// distance += a;
	// }
	// return distance;
	// }
	//
	// public void outputPath(DataCon dc) {
	//
	// }

	public double[] getBestPhenotype() {
		return bestPhenotype;
	}

	public void setBestPhenotype(double[] bestPhenotype) {
		this.bestPhenotype = bestPhenotype;
	}

	public String getBestGenotype() {
		return bestGenotype;
	}

	public void setBestGenotype(String bestGenotype) {
		this.bestGenotype = bestGenotype;
	}

	public double getBestFtn() {
		return bestFtn;
	}

	public void setBestFtn(double bestFtn) {
		this.bestFtn = bestFtn;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Map<Integer, Population> getPopMap() {
		return popMap;
	}

	public void setPopMap(Map<Integer, Population> popMap) {
		this.popMap = popMap;
	}

	public int getMaxGeneration() {
		return maxGeneration;
	}

	public void setMaxGeneration(int maxGeneration) {
		this.maxGeneration = maxGeneration;
	}

}
