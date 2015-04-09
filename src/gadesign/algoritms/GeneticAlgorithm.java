package gadesign.algoritms;

import gadesign.populations.Population;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class GeneticAlgorithm {

	// 选择算子,对父代操作，返回子代个体
	public Population select(Population parentPop) {
		Population cp = new Population();
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
			// System.out.println("父代染色体的累积选择概率："+cumulativePos[j]);
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
		// System.out.println("子代的种群大小为："+cp.getPopNum());
		return cp;
	}

	// 单次GA实验,将实验结果输出到记事本中
	public OverallBest singleGaAlgorithm(InitialParameters param) {
		// 进行一次单个实验并计算运行时间
		long startTime = System.currentTimeMillis();// 初始化时间

		//Map<Integer, Population> popMap = new HashMap<Integer, Population>();// 生成Map，存储每一代的染色体
		Population parentPop = new Population(param.getVariableDim(), param
				.getGenolength(), param.getPopNum());// 生成第一代染色体并存储
		//popMap.put(0, parentPop);
		OverallBest ob = new OverallBest();
		// 开始遗传算法主体
		for (int i = 1; i <= param.getGeneration(); i++) {

			parentPop.cross(param.getCrossPos());// 将父代交叉
			parentPop.mutaion(param.getMutPos());// 将父代变异
			Population sonpop = this.select(parentPop);// 将父代选择，返回子代
			sonpop.setGeneration(i);// 给子代的generation赋值
			//popMap.put(sonpop.getGeneration(), sonpop);// 将子代存入Map

			// if (i >= 2000) {
			// if (sonpop.getBestIndividual().getFitness() == popMap.get(
			// i - 500).getBestIndividual()
			// .getFitness()) {
			// ob.setMaxGeneration(sonpop.getGeneration());
			// ob.setBestPhenotype(popMap.get(sonpop.getGeneration())
			// .getBestIndividual().getPhenotype());
			// ob.setBestGenotype(popMap.get(sonpop.getGeneration())
			// .getBestIndividual().getGenotype());
			// ob.setBestFtn(popMap.get(sonpop.getGeneration())
			// .getBestIndividual().getFitness());
			// break;
			// }
			// } else {
			if(Math.abs(parentPop.getBestIndividual().getFitness()-sonpop.getBestIndividual().getFitness())>0.01){
			System.out.println(sonpop.getBestIndividual().getFitness());
			}
			parentPop = sonpop;// 将子代设置为父代，进行下一轮循环
			
			if(Math.abs(parentPop.getBestIndividual().getFitness()+8)<0.001){
				System.out.println(parentPop.getGeneration());
				break;
			}
		}

		long stopTime = System.currentTimeMillis();// 计算时间
		ob.setBestPhenotype(parentPop.
//				popMap.get(param.getGeneration())
				getBestIndividual().getPhenotype()
				);
		ob.setBestGenotype(parentPop
//				popMap.get(param.getGeneration())
				.getBestIndividual().getGenotype());
		ob.setBestFtn(parentPop
//				popMap.get(param.getGeneration())
				.getBestIndividual()
				.getFitness());
		double time = stopTime - startTime;
		ob.setTime(time);
//		ob.setPopMap(popMap);
		ob.setMaxGeneration(param.getGeneration());

		// System.out.println("全局最优个体为："
		// + "("
		// + ob.getBestPhenotype()[0]
		// + ","
		// + ob.getBestPhenotype()[1]
		// + ")"
		// + "\n"
		// + "全局最优值："
		// + ob.getBestFtn() + "\n" + "算法运行时间："
		// + ob.getTime());
		return ob;

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
