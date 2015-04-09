package gadesign.chroms;

import gadesign.entitys.DataCon;
import gadesign.entitys.Station;
import gadesign.functions.impls.TSPObjectFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class ChromTSP extends Chrom {

	DataCon dc = null;

	public ChromTSP() {
		super();
	}

	public ChromTSP(String genotype, int dim, DataCon dc) {
		this.genotype = genotype;
		this.genolength = genotype.length();
		this.phenotype = discode(genotype, dim);// 生成对应的表现型
		this.variableDim = this.phenotype.length;
		//System.out.println(this.genotype);
		this.dc = dc;
		calculateFitness();// 计算适应度值
		//System.out.println(this.fitness);
	}

	public ChromTSP(int variableDim, int genolength, DataCon dc) {
		this.genolength = genolength;
		this.variableDim = variableDim;
		this.genotype = initialChrom();
		discode();
		this.dc = dc;
		calculateFitness();
		// System.out.println(this.fitness);
	}

	@Override
	double[] selftrans(List<Integer> tempVariable, int percode) {
		// 初始化两个List
		// System.out.println(tempVariable);
		double[] variable = new double[tempVariable.size()];
		List<Double> temp1 = new ArrayList<Double>();
		List<Double> temp2 = new ArrayList<Double>();

		for (int j = 0; j <= tempVariable.size() - 1; j++) {
			double r = Math.random() * 0.5;
			temp1.add(tempVariable.get(j) + r);
			temp2.add(tempVariable.get(j) + r);
		}
		// 将temp1排序，
		Collections.sort(temp1);
		// System.out.println(temp1);
		// System.out.println(temp2);
		// 采用Geff编码方式
		for (int i = 0; i <= temp1.size() - 1; i++) {
			variable[i] = temp1.indexOf(temp2.get(i)) + 1;
			// System.out.print(variable[i]+",");
			if (variable[i] > Station.VARIABLE_LENGTH) {
				variable[i] = 1;// 如果路径中的元素大于站点数，赋值为原点
			}
		}

		return variable;
	}

	@Override
	void calculateFitness() {
		double ftn = 0;
		// 通过本方法计算该基因型的适应值
		ftn = new TSPObjectFunction(this.dc).format(this.phenotype);
		this.fitness = ftn;
	}

}
