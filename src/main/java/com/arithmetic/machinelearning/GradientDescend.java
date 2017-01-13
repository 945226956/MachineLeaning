package com.arithmetic.machinelearning;

import java.math.BigDecimal;
import java.util.List;

import com.arithmetic.machinelearning.bean.TrainData_room;

public class GradientDescend {
	//常量 theta0
	private BigDecimal  theta_zero;
	//房间大小theta1
	private BigDecimal  theta_size;
	//房间数量theta2
	private BigDecimal  theta_roomNum;
	//训练集
	private List<TrainData_room> trainData_roomSets;
	
	public static BigDecimal  learningRate = BigDecimal.valueOf(0.00001);
	
	//修改学习步骤大小
	public void setRate(BigDecimal r){
		learningRate = r;
	}
	
	public BigDecimal  getTheta_size() {
		return theta_size;
	}

	public void setTheta_size(BigDecimal  theta_size) {
		this.theta_size = theta_size;
	}

	public BigDecimal  getTheta_rootNum() {
		return theta_roomNum;
	}

	public void setTheta_rootNum(BigDecimal  theta_rootNum) {
		this.theta_roomNum = theta_rootNum;
	}

	public BigDecimal  getTheta_zero() {
		return theta_zero;
	}

	public void setTheta_zero(BigDecimal  theta_zero) {
		this.theta_zero = theta_zero;
	}

	public List<TrainData_room> getTrainData_roomSets() {
		return trainData_roomSets;
	}

	public void initTrainData_rooms(List<TrainData_room> trainData_roomSets) {
		this.trainData_roomSets = trainData_roomSets;
	}

	
	public void initFuntion(BigDecimal  theta_size,BigDecimal  theta_rootNum,BigDecimal  theta_zero){
		this.theta_size = theta_size;
		this.theta_roomNum = theta_rootNum;
		this.theta_zero = theta_zero;
	}
	
	int iter = 0;
	
	/**
	 * 模拟学习theta
	 * @param type  类型，
	 * 1：theta_zero
	 * 2：theta_size
	 * 3：theta_rootNum
	 */
	public void learnTheta(int type) {
		boolean findMinY = false;
		BigDecimal  lastResult = errorNum();
		while(!findMinY){
			getNextTheta(type);
			BigDecimal  errorNum_next = errorNum();
			//errorNum_next >= lastResult
			if(errorNum_next.compareTo(lastResult) >= 0  ){
				findMinY = true;
			}else{
				lastResult = errorNum_next;
				iter++;
				if(type==1){
					System.out.println("iter:"+iter+"---theta_zero:"+theta_zero);
				}
				if(type==2){
					System.out.println("iter:"+iter+"---theta_size:"+theta_size);
				}
				if(type==3){
					System.out.println("iter:"+iter+"---theta_roomNum:"+theta_roomNum);
				}
				
			}
		}
	}
	
	/**
	 * 改变theta的下一步
	 * theta = theta- rate *（函数关于theta的导数）
	 * @param type
	 *  1:theta0,2:theta_size,3:theta_roomNum
	 */
	public void getNextTheta(int type){
		if(type==1){
			//关于theta0出的导数(theta0)
			BigDecimal  derivative = BigDecimal.valueOf(0);
			for (int i = 0; i < trainData_roomSets.size(); i++) {
				TrainData_room tdr = trainData_roomSets.get(i);
				BigDecimal  functionY = getPriceByFunction(tdr.getPrice(),tdr.getRoomNum());
				BigDecimal  price = tdr.getPrice();
				//(functionY-price)*1; 这里是1吗？ Xi =?
				derivative = derivative.add(functionY.subtract(price).multiply(BigDecimal.valueOf(1)));
			}
			BigDecimal detaTheta;
			BigDecimal theta_old = theta_zero;
			//theta_zero = theta_zero-learningRate*param;
			theta_zero = theta_zero.subtract((learningRate.multiply(derivative)));
			detaTheta = theta_zero.subtract(theta_old);
			System.out.println("detaTheta:"+detaTheta);
		}
		if(type==2){
			//关于theta1出的导数(size)
			BigDecimal  derivative = BigDecimal.valueOf(0);
			for (int i = 0; i < trainData_roomSets.size(); i++) {
				TrainData_room tdr = trainData_roomSets.get(i);
				BigDecimal  functionY = getPriceByFunction(tdr.getPrice(),tdr.getRoomNum());
				BigDecimal  price = tdr.getPrice();
				//param+=(functionY-price)*1;
				derivative = derivative.add(functionY.subtract(price).multiply(tdr.getSize()));
			}
			BigDecimal detaTheta;
			BigDecimal theta_old = theta_size;
			//theta_size = theta_size-learningRate*param;
			theta_size = theta_size.subtract((learningRate.multiply(derivative)));
			detaTheta = theta_size.subtract(theta_old);
			System.out.println("detaTheta:"+detaTheta);
		}
		if(type==3){
			//关于theta1出的导数(size)
			BigDecimal  derivative = BigDecimal.valueOf(0);
			for (int i = 0; i < trainData_roomSets.size(); i++) {
				TrainData_room tdr = trainData_roomSets.get(i);
				BigDecimal  functionY = getPriceByFunction(tdr.getPrice(),tdr.getRoomNum());
				BigDecimal  price = tdr.getPrice();
				//param+=(functionY-price)*roomNum;
				derivative = derivative.add(functionY.subtract(price).multiply(tdr.getRoomNum()));
			}
			BigDecimal detaTheta;
			BigDecimal theta_old = theta_roomNum;
			//theta_rootNum = theta_rootNum-learningRate*param;
			theta_roomNum = theta_roomNum.subtract((learningRate.multiply(derivative)));
			detaTheta = theta_roomNum.subtract(theta_old);
			System.out.println("detaTheta:"+detaTheta);
		}
	}
	
	/**
	 * 计算方差
	 * @return
	 */
	public BigDecimal  errorNum(){
		BigDecimal  errorNum = BigDecimal.valueOf(0);
		for (int i = 0; i < trainData_roomSets.size(); i++) {
			TrainData_room tdr = trainData_roomSets.get(i);
			//BigDecimal  error_num_i=tdr.getPrice()-getPriceByFunction(tdr.getSize(),tdr.getRoomNum());
			BigDecimal  error_num_i=tdr.getPrice().subtract(getPriceByFunction(tdr.getSize(),tdr.getRoomNum()));
			//errorNum+=error_num_i*error_num_i;
			errorNum=errorNum.add(error_num_i.multiply(error_num_i));
		}
		return errorNum.divide(BigDecimal.valueOf(2));
	}
	/**
	 * 根据训练集的输入参数，房间大小和房间数量，求得函数预测的价格
	 * @param size
	 * @param room_num
	 * @return
	 */
	public BigDecimal  getPriceByFunction(BigDecimal  size,BigDecimal  room_num){
		
		//theta_zero+theta_size*size+theta_rootNum*room_num;
		return theta_zero.add(theta_size.multiply(size)).add(theta_roomNum.multiply(room_num));
	}

	public void learnTheta_zero() {
		learnTheta(1);
	}
	public void learnTheta_size() {
		learnTheta(2);
	}
	public void learnTheta_roomNum() {
		learnTheta(2);
	}
}
