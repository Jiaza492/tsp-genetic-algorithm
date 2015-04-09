package gadesign.populations;

import gadesign.chroms.Chrom;
import gadesign.chroms.ChromTSP;
import gadesign.entitys.DataCon;
import gadesign.entitys.Tool;

import java.util.ArrayList;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class PopTSP extends Population {

	DataCon dc;

	public PopTSP() {
		super();
	}

	public PopTSP(int dim, int genolength, int popNum, DataCon dc) {
		this.popNum = popNum;
		// this.chromPop = new ArrayList<Chrom>(popNum);
		// System.out.println("首代群体为：");
		this.dc = dc;
		this.chromPop = initialPop(dim, genolength);

	}

	@Override
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

				// 将子代全部加入到chromPop备用，统一做选择操作
				if (this.exsistChrom(son1) == false) {// 判断son1是否已存在,若存在，不执行任何操作
					this.chromPop.add(new ChromTSP(son1, variableDim, this.dc));// 重写过
					// System.out.println("添加新的子代个体：" + son1);
				} else {
					// System.out.println(son1 + "已在群中存在，不必添加");
				}

				if (this.exsistChrom(son2) == false) {// 判断son2是否已存在,若存在，不执行任何操作
					this.chromPop.add(new ChromTSP(son2, variableDim, this.dc));// 重写过
					// System.out.println("添加新的子代个体：" + son2);
				} else {
					// System.out.println(son2 + "已在群中存在，不必添加");
				}
			}
		}
		// System.out.println("交叉后种群大小为：" + this.chromPop.size());
	}

	@Override
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
					pos[1] = (int) (Math.round(r2 * 1000)) % (genolength);
					if (pos[1] == 0) {
						pos[1] = 1;
					}
				}
				parent1 = this.chromPop.get(i).getGenotype();// 确定父代
				parent2 = this.chromPop.get((i + 1) % popNum).getGenotype();
				son1 = parent1.substring(0, pos[0])
						+ parent2.substring(pos[0], pos[1])
						+ parent1.substring(pos[1]);
				son2 = parent2.substring(0, pos[0])
						+ parent1.substring(pos[0], pos[1])
						+ parent2.substring(pos[1]);

				ChromTSP cTSP1 = new ChromTSP(son1, variableDim, this.dc);
				// 将子代全部加入到chromPop备用，统一做选择操作
				if (this.exsistChrom(son1) == false
						&& this.jugdePeoplNum(cTSP1.getPhenotype()) == true) {// 判断son1是否已存在,若存在，不执行任何操作
					this.chromPop.add(cTSP1);// 重写过
					// System.out.println("添加新的子代个体：" + son1);
				} else {
					// System.out.println(son1 + "已在群中存在，不必添加");
				}

				ChromTSP cTSP2 = new ChromTSP(son2, variableDim, this.dc);
				if (this.exsistChrom(son2) == false
						&& this.jugdePeoplNum(cTSP2.getPhenotype()) == true) {// 判断son2是否已存在,若存在，不执行任何操作
					this.chromPop.add(cTSP2);// 重写过
					// System.out.println("添加新的子代个体：" + son2);
				} else {
					// System.out.println(son2 + "已在群中存在，不必添加");
				}
			}
		}
	}

	@Override
	public ArrayList<Chrom> initialPop(int dim, int genolength) {
		ArrayList<Chrom> icp = new ArrayList<Chrom>();
		for (int i = 0; i <= this.popNum - 1; i++) {
			ChromTSP cr = null;
			cr = new ChromTSP(dim, genolength, this.dc);// 重写过的
			//System.out.println(cr.getGenotype());
			while (this.jugdePeoplNum(cr.getPhenotype()) == false) {
				//System.out.println("此路径超过人数");
				cr = new ChromTSP(dim, genolength, this.dc);
			}
			icp.add(cr);
			if (this.bestIndividual == null) {
				this.setBestIndividual(cr);
			}
			if (cr.getFitness() > this.bestIndividual.getFitness()) {
				this.setBestIndividual(cr);// 给最优个体赋值
			}
		}
		return icp;
	}

	@Override
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

				ChromTSP cTSP = new ChromTSP(mut, variableDim, this.dc);
				if (this.exsistChrom(mut) == false
						&& this.jugdePeoplNum(cTSP.getPhenotype()) == true) {// 判断mut是否已存在,若存在，不执行操作
					this.chromPop.add(cTSP);// 若不存在，将变异后的染色体加入群体中//重写过
					// System.out.println("添加变异后个体：" + mut);
				} else {
					// System.out.println("变异后个体已存在，无需添加");
				}
			}
		}
		// System.out.println("变异后种群大小为：" + this.chromPop.size());
	}

	public DataCon getDc() {
		return dc;
	}

	public void setDc(DataCon dc) {
		this.dc = dc;
	}

	private boolean jugdePeoplNum(double[] variables) {
		boolean falg = true;
		// 计算产生的每条路径上的学生人数总和
		//int count = 0;
		double temp;
		for (int j = 0; j <= variables.length - 1; j++) {
			if (variables[j] == 1.0) {
				temp = 0;
				int i = j+1;
				while (variables[i % variables.length] != 1.0) {
					temp = temp
							+ this.dc.getPeopleNum().get(
									(int) variables[i % variables.length] - 1);
					i++;
				}
				//count += 1;
				//System.out.print("第"+count+"条路径");
				//System.out.println(temp+",");
				if (temp > Tool.BUSCAP) {
					//System.out.println(temp);
					return false;
				}else{
					//System.out.println("该路径可行！");
				}
			}
			
		}
		//System.out.println();
		return falg;
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
