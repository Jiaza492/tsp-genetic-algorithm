package gadesign.algoritms;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class InitialParameters {

	// 参数包装类

	
	int variableDim;//自变量维数
	int genolength;//染色体基因型长度
	int popNum;// 种群数量
	int generation;// 最大遗传代数
	double mutPos;// 变异概率
	double crossPos;// 交叉概率

	public InitialParameters() {

	}

	public InitialParameters(int variableDim, int genolength,int popNum, int generation, double crossPos, double mutPos) {
		this.variableDim = variableDim;
		this.genolength = genolength;
		this.crossPos = crossPos;
		this.generation = generation;
		this.mutPos = mutPos;
		this.popNum = popNum;
	}

	public int getPopNum() {
		return popNum;
	}

	public void setPopNum(int popNum) {
		this.popNum = popNum;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public double getMutPos() {
		return mutPos;
	}

	public void setMutPos(double mutPos) {
		this.mutPos = mutPos;
	}

	public double getCrossPos() {
		return crossPos;
	}

	public void setCrossPos(double crossPos) {
		this.crossPos = crossPos;
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

}
