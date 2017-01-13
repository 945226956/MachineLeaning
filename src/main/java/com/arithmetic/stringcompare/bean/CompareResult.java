package com.arithmetic.stringcompare.bean;

/**
 * bean for compare string 
 * @author zgc
 * @time 2017 01 13
 */
public class CompareResult {
	// 顺序
	int index;
	// 段落1的差异字符串
	String sectionOneStr = "";
	// 段落2的差异字符串
	String sectionTwoStr = "";
	// 段落1字符串对应的段落1位置
	int sectionOneStr_begin = 0;
	int sectionOneStr_end = 0;
	// 段落2字符串对应的段落2位置
	int sectionTwoStr_begin = 0;
	int sectionTwoStr_end = 0;

	// 2字符串的比较类型.0为相同,1为增加,2为删除,3为更新。
	public static final int SAME_PART = 0;
	public static final int ADD_PART = 1;
	public static final int DELE_PART = 2;
	public static final int UPDT_PART = 3;
	int type;

	public CompareResult(int index, String sectionOneStr, String sectionTwoStr, int sectionOneStr_begin, int sectionOneStr_end, int sectionTwoStr_begin, int sectionTwoStr_end, int type) {
		this.index = index;
		this.sectionOneStr = sectionOneStr;
		this.sectionTwoStr = sectionTwoStr;
		this.sectionOneStr_begin = sectionOneStr_begin;
		this.sectionOneStr_end = sectionOneStr_end;
		this.sectionTwoStr_begin = sectionTwoStr_begin;
		this.sectionTwoStr_end = sectionTwoStr_end;
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSectionOneStr() {
		return sectionOneStr;
	}

	public void setSectionOneStr(String sectionOneStr) {
		this.sectionOneStr = sectionOneStr;
	}

	public String getSectionTwoStr() {
		return sectionTwoStr;
	}

	public void setSectionTwoStr(String sectionTwoStr) {
		this.sectionTwoStr = sectionTwoStr;
	}

	public int getSectionOneStr_begin() {
		return sectionOneStr_begin;
	}

	public void setSectionOneStr_begin(int sectionOneStr_begin) {
		this.sectionOneStr_begin = sectionOneStr_begin;
	}

	public int getSectionOneStr_end() {
		return sectionOneStr_end;
	}

	public void setSectionOneStr_end(int sectionOneStr_end) {
		this.sectionOneStr_end = sectionOneStr_end;
	}

	public int getSectionTwoStr_begin() {
		return sectionTwoStr_begin;
	}

	public void setSectionTwoStr_begin(int sectionTwoStr_begin) {
		this.sectionTwoStr_begin = sectionTwoStr_begin;
	}

	public int getSectionTwoStr_end() {
		return sectionTwoStr_end;
	}

	public void setSectionTwoStr_end(int sectionTwoStr_end) {
		this.sectionTwoStr_end = sectionTwoStr_end;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
