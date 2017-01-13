package com.arithmetic.machinelearning;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.arithmetic.machinelearning.bean.TrainData_room;

public class GradientDescendTest {
	//f(size,room_num)=1+2*size+3*room_num;
	//train data (1,2,10),(5,6,29),(7,10,45);
	public static void main(String[] args) {
		
		//假设 f(size,room_num)=1+2*size+3*room_num;
		// theta0=1,theta_size=2,theta_room_num=3;
		//用三组正确的训练集验证算法正确性 (1,2,10),(5,6,29),(7,10,45);
		
		
		GradientDescend gd = new GradientDescend();
		// 初始化训练集
		TrainData_room td1 = new TrainData_room(BigDecimal.valueOf(1),BigDecimal.valueOf(2),BigDecimal.valueOf(9));
		TrainData_room td2 = new TrainData_room(BigDecimal.valueOf(5),BigDecimal.valueOf(6),BigDecimal.valueOf(29));
		TrainData_room td3 = new TrainData_room(BigDecimal.valueOf(7),BigDecimal.valueOf(10),BigDecimal.valueOf(45));
		List<TrainData_room> TrainData_roomSets = new ArrayList<TrainData_room>();
		TrainData_roomSets.add(td1);
		TrainData_roomSets.add(td2);
		TrainData_roomSets.add(td3);
		gd.initTrainData_rooms(TrainData_roomSets);
		//初始化三个theta
		BigDecimal theta_zero=BigDecimal.valueOf(1),theta_size = BigDecimal.valueOf(2),theta_room_num=BigDecimal.valueOf(7);
		//初始化方法
		gd.initFuntion(theta_size, theta_room_num, theta_zero);
		gd.setRate(BigDecimal.valueOf(0.01));
		
//		gd.learnTheta_zero();
//		gd.learnTheta_size();
		gd.learnTheta_roomNum();
//		System.out.println("theta_zero:"+gd.getTheta_zero());
//		System.out.println("theta_size:"+gd.getTheta_size());
		System.out.println("theta_rootNum:"+gd.getTheta_rootNum());
		
	}
}
