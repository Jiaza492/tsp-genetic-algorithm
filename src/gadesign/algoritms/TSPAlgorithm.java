package gadesign.algoritms;

import gadesign.entitys.DataCon;
import gadesign.populations.PopTSP;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class TSPAlgorithm extends GeneticAlgorithm{

	//选择方法
	public PopTSP select(PopTSP parentPop) {
		PopTSP cp = new PopTSP();
		// 对该代中的染色基因进行选择操作
		double ftnSum = 0;
		for (int i = 0; i <= parentPop.getChromPop().size() - 1; i++) {// 计算父代群体中所有染色体的适应度之和
			ftnSum = ftnSum + parentPop.getChromPop().get(i).getFitness();
		}

		double[] cumulativePos = new double[parentPop.getChromPop().size()];// 把累计概率赋值给数组
		for (int j = 0; j <= parentPop.getChromPop().size() - 1; j++) {// 给每个染色体分配对应的选择概率和累计概率
			parentPop.getChromPop().get(j).setFitPos(
					parentPop.getChromPop().get(j).getFitness() / ftnSum);
			if (j == 0) {
				cumulativePos[j] = parentPop.getChromPop().get(j).getFitPos();
			} else {
				cumulativePos[j] = cumulativePos[j - 1]
						+ parentPop.getChromPop().get(j).getFitPos();// 计算累进概率
			}
			//System.out.println("父代染色体的累积选择概率："+cumulativePos[j]);
		}

		// 可以通过给适应度排序来选择进入下一代的染色体
		// 采用轮盘赌方法选择进入子代的染色体
		for (int i = 0; i <= parentPop.getPopNum() - 2; i++) {
			double r = Math.random();// 生成一个0-1个随机数
			if (r <= cumulativePos[0]) {
				cp.getChromPop().add(parentPop.getChromPop().get(0));
				// System.out.println(parentPop.getChromPop().get(0).getGenotype()+"成功进化到下一代");
			} else {
				for (int j = 1; j < parentPop.getChromPop().size(); j++) {// 可以按累计概率随机选择进入下一代的染色体，实行父代最优保留策略
					if (r <= cumulativePos[j]) {
						cp.getChromPop().add(parentPop.getChromPop().get(j));// 若选中该染色体，则将他加入到子代中
						// System.out.println(parentPop.getChromPop().get(j).getGenotype()+"成功进化到子代");
						break;
					}
				}
			}
		}

		cp.getChromPop().add(parentPop.getBestIndividual());// 将父代的最优个体添加到下一代List中

		// 循环更新子代中德最优个体
		for (int i = 0; i <= cp.getChromPop().size() - 1; i++) {
			if (cp.getBestIndividual() == null) {
				cp.setBestIndividual(cp.getChromPop().get(i));
			} else if (cp.getChromPop().get(i).getFitness() > cp
					.getBestIndividual().getFitness()) {
				cp.setBestIndividual(cp.getChromPop().get(i));
			}
		}

		cp.setPopNum(cp.getChromPop().size());// 给子代的popNum赋值
		cp.setDc(parentPop.getDc());
		// System.out.println("子代的种群大小为："+cp.getPopNum());
		return cp;
	}
	
	/**
	 * 
	 * @author Jia Ziang
	 * @see ziangj92@gmail.com
	 * @since 06/01/2014
	 * @version 1.0
	 *
	 */
	
	public OverallBest singleGaAlgorithm(InitialParameters param, DataCon dc) {
		//进行一次单个实验并计算运行时间
		long startTime = System.currentTimeMillis();// 初始化时间

		//Map<Integer, Population> popMap = new HashMap<Integer, Population>();// 生成Map，存储每一代的染色体
		PopTSP parentPop = new PopTSP(param.getVariableDim(), param
				.getGenolength(), param.getPopNum(), dc);// 生成第一代染色体并存储
		//popMap.put(0, parentPop);
		// 开始遗传算法主体
		PopTSP finalPop = null;
		for (int i = 1; i <= param.getGeneration(); i++) {

			parentPop.doublecross(param.getCrossPos());// 将父代交叉
			parentPop.mutaion(param.getMutPos());// 将父代变异
			PopTSP sonpop = this.select(parentPop);// 将父代选择，返回子代
			sonpop.setGeneration(i);// 给子代的generation赋值
			//popMap.put(sonpop.getGeneration(), sonpop);// 将子代存入Map
			parentPop = sonpop;// 将子代设置为父代，进行下一轮循环
			finalPop = sonpop; 
			System.out.println("第"+i+"代");

		}

		long stopTime = System.currentTimeMillis();// 计算时间
//		double[] bestPhtp = popMap.get(param.getGeneration())
//				.getBestIndividual().getPhenotype();
//		String bestGntp = popMap.get(param.getGeneration())
//		.getBestIndividual().getGenotype();
//		double bestFtn = popMap.get(param.getGeneration()).getBestIndividual().getFitness();
		double time = stopTime - startTime;
//		OverallBest ob = new OverallBest(bestPhtp, bestGntp, bestFtn, time);
//		ob.setPopMap(popMap);
		double[] bestPhtp = finalPop.getBestIndividual().getPhenotype();
		String bestGntp = finalPop.getBestIndividual().getGenotype();
		double bestFtn = finalPop.getBestIndividual().getFitness();
		OverallBest ob = new OverallBest(bestPhtp, bestGntp, bestFtn, time);
//		System.out.println("全局最优个体为："
//				+ "("
//				+ ob.getBestPhenotype()[0]
//				+ ","
//				+ ob.getBestPhenotype()[1]
//				+ ")"
//				+ "\n"
//				+ "全局最优值："
//				+ ob.getBestFtn() + "\n" + "算法运行时间："
//				+ ob.getTime());
		return  ob;

	
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
