package gadesign.populations;

 /**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */
import gadesign.chroms.Chrom;

import java.util.ArrayList;
import java.util.List;

public class Population {

	Chrom bestIndividual = null;// 每一代中最优秀的染色体
	int generation = 0;// 世代标志
	int popNum;// 每一代的染色体数量
	double mutationPosibility;// 该代的变异概率
	double crossPosibility;// 该代的交叉概率
	List<Chrom> chromPop = null;// Population数组，用于存储世代中的染色体

	// 构造方法
	public Population() {
		this.chromPop = new ArrayList<Chrom>();
	}

	public Population(Population pop) {
		this.chromPop = pop.getChromPop();
		this.popNum = pop.getChromPop().size();
		this.bestIndividual = pop.getBestIndividual();

	}

	public Population(int dim, int genolength, int popNum) {
		this.popNum = popNum;
		// this.chromPop = new ArrayList<Chrom>(popNum);
		//System.out.println("首代群体为：");
		this.chromPop = initialPop(dim, genolength);
	}

	// 初始化首代染色体
	public ArrayList<Chrom> initialPop(int dim, int genolength) {
		ArrayList<Chrom> icp = new ArrayList<Chrom>();
		for (int i = 0; i <= this.popNum - 1; i++) {
			Chrom cr = new Chrom(dim, genolength);
			if (this.bestIndividual == null) {
				this.setBestIndividual(cr);
			}
			if (cr.getFitness() > this.bestIndividual.getFitness()) {
				this.setBestIndividual(cr);// 给最优个体赋值
			}
			icp.add(cr);
		}
		return icp;
	}

	// 染色体变异操作，返回一个变异后的染色体
	public void mutaion(double mutationPosibility) {
		String mut, sub1, sub2;
		int genolength = this.chromPop.get(0).getGenolength();
		int variableDim = this.chromPop.get(0).getVariableDim();
		// 对该代中的染色体进行变异操作
		int chromNum;
		int genoNum;
		for (int i = 0; i <= popNum * genolength - 1; i++) {
			if (Math.random() <= mutationPosibility) {
				chromNum = (int) i / genolength;// 确定染色体标号
				genoNum = i % genolength;// 确定基因标号
				// System.out.println("变异前"+this.chromPop.get(chromNum).getGenotype());
				sub1 = this.chromPop.get(chromNum).getGenotype().substring(0,
						genoNum);
				sub2 = this.chromPop.get(chromNum).getGenotype().substring(
						genoNum + 1);
				char gene = this.chromPop.get(chromNum).getGenotype().charAt(
						genoNum);// 取得需要变异的基因

				if (gene == '0') {// 基因取反
					gene = '1';
				} else if (gene == '1') {
					gene = '0';
				}
				mut = sub1 + gene + sub2;// 生成变异后的染色体的基因型序列
				// System.out.print("变异前"
				// + this.chromPop.get(chromNum).getGenotype());
				// System.out.println("变异后" + mut);

				if (this.exsistChrom(mut) == false) {// 判断mut是否已存在,若存在，不执行操作
					this.chromPop.add(new Chrom(mut, variableDim));// 若不存在，将变异后的染色体加入群体中
					// System.out.println("添加变异后个体：" + mut);
				} else {
					// System.out.println("变异后个体已存在，无需添加");
				}
			}
		}
		// System.out.println("变异后种群大小为：" + this.chromPop.size());
	}

	// 单点交叉算子，染色体交叉操作，返回生成的子代染色体
	public void cross(double crossPosibility) {
		String parent1, parent2, son1, son2;
		int genolength = this.chromPop.get(0).getGenolength();
		int variableDim = this.chromPop.get(0).getVariableDim();
		// 对该代中的染色体进行交叉操作
		// 单点交叉操作
		// 父代保留策略
		for (int i = 0; i <= this.popNum - 1; i++) {
			if (Math.random() < crossPosibility) {
				double r = Math.random();
				int pos = (int) (Math.round(r * 1000)) % (genolength);// 确定基因交叉位置
				if (pos == 0) {
					pos = 1;
				}
				parent1 = this.chromPop.get(i).getGenotype();// 确定父代
				parent2 = this.chromPop.get((i + 1) % popNum).getGenotype();
				son1 = parent1.substring(0, pos) + parent2.substring(pos);// 生成子代的基因型字符串
				son2 = parent2.substring(0, pos) + parent1.substring(pos);

				if (this.exsistChrom(son1) == false) {// 判断son1是否已存在,若存在，不执行任何操作
					this.chromPop.add(new Chrom(son1, variableDim));
					// System.out.println("添加新的子代个体：" + son1);
				} else {
					// System.out.println(son1 + "已在群中存在，不必添加");
				}

				if (this.exsistChrom(son2) == false) {// 判断son2是否已存在,若存在，不执行任何操作
					this.chromPop.add(new Chrom(son2, variableDim));// 将子代全部加入到chromPop备用，统一做选择操作
					// System.out.println("添加新的子代个体：" + son2);
				} else {
					// System.out.println(son2 + "已在群中存在，不必添加");
				}
			}
		}
		// System.out.println("交叉后种群大小为：" + this.chromPop.size());
	}

	/**
	 * 
	 * @author Jia Ziang
	 * @see ziangj92@gmail.com
	 * @since 06/01/2014
	 * @version 1.0
	 *
	 */
	
	//双点交叉算子
	public void doublecross(double crossPosibility) {
		String parent1, parent2, son1, son2;
		int genolength = this.chromPop.get(0).getGenolength();
		int variableDim = this.chromPop.get(0).getVariableDim();
		// 对该代中的染色体进行交叉操作
		// 双点交叉操作
		// 父代保留
		for (int i = 0; i <= this.popNum - 1; i++) {
			if (Math.random() < crossPosibility) {
				int[] pos = new int[2];
				while (pos[0] != pos[1]) {
					double r1 = Math.random();
					pos[0] = (int) (Math.round(r1 * 1000)) % (genolength - 1);// 确定基因交叉位置
					if (pos[0] == 0) {
						pos[0] = 1;
					}
					double r2 = Math.random();
					pos[1] = (int) (Math.round(r2 * 1000)) % (genolength);//保证第二位置大于第一位置
					if (pos[1] == 0) {
						pos[1] = 1;
					}
				}
				parent1 = this.chromPop.get(i).getGenotype();// 确定父代
				parent2 = this.chromPop.get((i + 1) % popNum).getGenotype();
				son1 = parent1.substring(0, pos[0])
						+ parent2.substring(pos[0], pos[1])
						+ parent1.substring(pos[1]);//确定子代
				son2 = parent2.substring(0, pos[0])+parent1.substring(pos[0], pos[1])+parent2.substring(pos[1]);
				
				// 将子代全部加入到chromPop备用，统一做选择操作
				if (this.exsistChrom(son1) == false) {// 判断son1是否已存在,若存在，不执行任何操作
					this.chromPop.add(new Chrom(son1, variableDim));// 重写过
					// System.out.println("添加新的子代个体：" + son1);
				} else {
					// System.out.println(son1 + "已在群中存在，不必添加");
				}

				if (this.exsistChrom(son2) == false) {// 判断son2是否已存在,若存在，不执行任何操作
					this.chromPop.add(new Chrom(son2, variableDim));// 重写过
					// System.out.println("添加新的子代个体：" + son2);
				} else {
					// System.out.println(son2 + "已在群中存在，不必添加");
				}

			}
		}
	}

	
	// 判断genotype序列在群体中是否已存在，若存在返回true，若不存在，返回false
	boolean exsistChrom(String genotype) {
		boolean judge = false;
		for (int j = 0; j <= this.chromPop.size() - 1; j++) {
			if (genotype.equals(this.chromPop.get(j).getGenotype())) {
				judge = true;
				// System.out.println("存在相同的染色体："+this.chromPop.get(j).getGenotype());
			}
		}
		return judge;
	}

	public void selectbestindividual() {

	}

	public Chrom getBestIndividual() {
		return bestIndividual;
	}

	public void setBestIndividual(Chrom bestIndividual) {
		this.bestIndividual = bestIndividual;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public int getPopNum() {
		return popNum;
	}

	public void setPopNum(int popNum) {
		this.popNum = popNum;
	}

	public double getMutationPosibility() {
		return mutationPosibility;
	}

	public void setMutationPosibility(double mutationPosibility) {
		this.mutationPosibility = mutationPosibility;
	}

	public double getCrossPosibility() {
		return crossPosibility;
	}

	public void setCrossPosibility(double crossPosibility) {
		this.crossPosibility = crossPosibility;
	}

	public List<Chrom> getChromPop() {
		return chromPop;
	}

	public void setChromPop(List<Chrom> chromPop) {
		this.chromPop = chromPop;
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