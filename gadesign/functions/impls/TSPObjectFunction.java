package gadesign.functions.impls;

import gadesign.entitys.DataCon;
import gadesign.entitys.Tool;
import gadesign.functions.Function;

/**
 * 
 * @author Jia Ziang
 * @see ziangj92@gmail.com
 * @since 06/01/2014
 * @version 1.0
 *
 */

public class TSPObjectFunction implements Function {

	DataCon dc = null;

	public TSPObjectFunction(DataCon dc) {
		this.dc = dc;
	}


	public double format(double[] variables) {
		double ftn;
		double ftn1 = 0, ftn2 = 0, ftn3 = 0;

		if (this.dc == null) {
			System.out.println("没有数据！");
		}
		// ftn1适应值计算方法
		for (int i = 0; i <= variables.length - 1; i++) {
			double a = 0;
			
			a = this.dc.getTime().get(((int) variables[i] - 1)).get(
					((int) variables[(i + 1) % variables.length] - 1));
			// System.out.println("增加路径："+a);
			ftn1 += a;
		}

		// ftn2适应值计算方法
		// 计算总人数
//		double sum = 0;
//		for(int k = 0;k<=variables.length-1;k++){
//			sum += this.dc.getPeopleNum().get((int)variables[k]-1);
//		}

		for (int j = 0;j<=variables.length-1; j++) {
			double temp = 0;
			if (variables[j] != 1.0) {
				int i = j;
				while (variables[i%variables.length] != 1.0) {
					temp = temp
							+ this.dc.getTime()
									.get(
											((int) variables[i
													% variables.length] - 1))
									.get(
											((int) variables[(i+1)
													% variables.length] - 1));
					i++;
				}
				//System.out.println("在"+variables[j]+"上车的到达时间为："+temp);
				
				ftn2 += temp*(double)this.dc.getPeopleNum().get((int)variables[j]-1);//需要乘以在站点varaible[j]上车的学生人数
			}else{
				continue;
			}
		}
		//System.out.println(ftn1+" "+ftn2);
		
		ftn = Tool.WEIGHT*ftn1+(1-Tool.WEIGHT)*ftn2;
		
		ftn = ftn*(-1);
		
		return ftn;
		
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
