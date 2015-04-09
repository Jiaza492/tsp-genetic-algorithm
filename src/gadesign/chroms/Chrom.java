package gadesign.chroms;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class Chrom {

	int variableDim;// 决策变量维数
	int genolength;// 等位基因长度
	double fitness;// 该染色体的适应值
	double fitPos;// 该染色体被选中进入下一代的概率
	String genotype = null;// 字符串数组，存储染色体的基因型（{0,1}空间）
	double[] phenotype = null;// 双精度数组，存储染色体的表现型（决策变量空间）

	// 构造方法
	public Chrom() {

	}

	public Chrom(String genotype, int dim) {// 直接利用变换好的字符数组构造子代染色体，需要指定变量Dim以完成解码
		this.genotype = genotype;
		this.genolength = genotype.length();
		this.phenotype = discode(genotype, dim);// 生成对应的表现型
		this.variableDim = this.phenotype.length;
		// System.out.println(this.genotype);
		calculateFitness();// 计算适应度值
	}

	public Chrom(int variableDim, int genolength) {// 用于生成初代染色体
		this.genolength = genolength;
		this.variableDim = variableDim;
		this.genotype = initialChrom();
		discode();
		calculateFitness();
	}

	// 初始化染色体序列
	public String initialChrom() {
		String gt = new String();
		for (int i = 0; i <= genolength - 1; i++) {
			if (Math.random() > 0.5) {
				gt += '1';
			} else {
				gt += '0';
			}
		}
		// System.out.println(new String(gt));
		return gt;
	}

	// 已知基因型,以及变量维数，返回染色体序列对应的表现型,通过本方法使得表象型和基因型对应
	public double[] discode(String genotype, int variableDim) {
		double[] pt = null;
		List<Integer> tempVariable = new ArrayList<Integer>(variableDim);

		int percode = genolength / variableDim;// 每段二进制字符串的长度，用来表示一个变量

		for (int i = 0; i <= variableDim - 1; i++) {
			tempVariable.add(Integer.parseInt(genotype.substring(percode * i,
					percode * (i + 1)), 2));// 字符串分段处理并转化为十进制整数
			// System.out.println(str.substring(percode*i, percode*(i+1)));
			// System.out.println(tempVariable[i]);
			// System.out.println(pt[i]);
		}

		pt = selftrans(tempVariable, percode);
		return pt;
	}

	// discode()方法重载
	void discode() {
		List<Integer> tempVariable = new ArrayList<Integer>(variableDim);
		int percode = genolength / variableDim;// 每段二进制字符串的长度，用来表示一个变量

		for (int i = 0; i <= variableDim - 1; i++) {
			tempVariable.add(Integer.parseInt(genotype.substring(percode * i,
					percode * (i + 1)), 2));// 字符串分段处理并转化为十进制整数
			// System.out.println(str.substring(percode*i, percode*(i+1)));
			// System.out.println(tempVariable[i]);
			// System.out.println(pt[i]);
		}
		this.phenotype = selftrans(tempVariable, percode);
	}

	// 已知表现型，以及基因长度，返回对应的基因型序列并存储在基因型字符数组中，通过本方法使得基因型和表现型对应
	public String code(double[] phenotype, int genolength) {
		String gt = new String();

		return gt;
	}

	// 解码规则，对染色体上每段字符换进行操作，返回double型的十进制变量存储在表现型数组中，可重写该方法
	double[] selftrans(List<Integer> tempVariable, int percode) {
		double[] variable = new double[tempVariable.size()];
		
		return variable;
	}

	// 计算本染色体的适应值，可以重写
	void calculateFitness() {
		double ftn = 0;
		// 通过本方法计算该基因型的适应值
		
		this.fitness = ftn;
	}

	// 重载上述方法
	public double calculateFitness(double[] phenotype) {
		double ftn = 0;
		// 通过本方法计算该基因型的适应值
		
		return ftn;
	}

	public String getGenotype() {
		return genotype;
	}

	public int getVariableDim() {
		return variableDim;
	}

	public void setVariableDim(int variableDim) {
		this.variableDim = variableDim;
	}

	public int getGenolength() {
		return genolength;
	}

	public void setGenolength(int genolength) {
		this.genolength = genolength;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double[] getPhenotype() {
		return phenotype;
	}

	public void setPhenotype(double[] phenotype) {
		this.phenotype = phenotype;
	}

	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}

	public double getFitPos() {
		return fitPos;
	}

	public void setFitPos(double fitPos) {
		this.fitPos = fitPos;
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