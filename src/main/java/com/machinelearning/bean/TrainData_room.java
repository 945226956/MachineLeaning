package com.machinelearning.bean;

import java.math.BigDecimal;

public class TrainData_room{
	private BigDecimal  size;
	private BigDecimal  roomNum;
	private BigDecimal  price;
	public TrainData_room(BigDecimal  size,BigDecimal  roomNum,BigDecimal  price){
		this.size = size;
		this.roomNum = roomNum;
		this.price = price;
	}
	//set  get method
	public BigDecimal  getSize() {
		return size;
	}
	public void setSize(BigDecimal  size) {
		this.size = size;
	}
	public BigDecimal  getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(BigDecimal  roomNum) {
		this.roomNum = roomNum;
	}
	public BigDecimal  getPrice() {
		return price;
	}
	public void setPrice(BigDecimal  price) {
		this.price = price;
	}
}