package com.machinelearning;

public class GradientDescentDemo {
	
	/**
	 * 模拟梯度下降方法收敛极小值
	 * y=x*x+2x+2
	 */
	public static double rate = 0.001;
	public static double beginX = 25;
	public static void main(String[] args) {
		System.out.println("最小值是:"+getMinY(beginX));
		System.out.println("MathResutl:"+getY(-1));
	}
	
	public static double getMinY(double x){
		boolean findMinY = false;
		double result = -999999999999.00 ;
		double lastResult = getY(x);
		while(!findMinY){
			double x_next = getNextX(x);
			double y_next = getY(x_next);
			System.out.println("x_next:"+x_next+" -------- y_next:"+y_next+" --------- lastResult:"+lastResult);
			if(y_next>=lastResult){
				findMinY = true;
				result = lastResult;
				System.out.println("resultX:"+x+" -------- resultY:"+lastResult);
			}else{
				x = x_next;
				lastResult = y_next;
			}
		}
		return result;
	}
	
	public static double getNextX(double x){
		double x_next=0;
		x_next = x-rate*getDerivation(x);
		return x_next;
	}
	
	/**
	 * 示例函数
	 * @param x
	 * @return
	 */
	public static double getY(double x){
		double y=x*x+2*x+1;
		return y;
	}
	
	/**
	 * 求函数的导数
	 * @return
	 */
	public static double getDerivation(double x){
		double y_derivation=2*x+2;
		return y_derivation;
	}
	
}
