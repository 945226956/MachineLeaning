package com.arithmetic.stringcompare.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * bea for same result string
 * @author zgc
 * @time 2017 01 13
 */
public class SameStringResult {
	String result = "";
	String s1 = "";
	String s2 = "";
	int s1Begin;
	int s1End;
	int s2Begin;
	int s2End;
	// 左侧结果比较
	SameStringResult left;
	// 右侧交过比较
	SameStringResult right;
	// 比较结果的上一个根比较结果
	SameStringResult root;

	public SameStringResult(String rs, String s1, String s2, int s1begin, int s1end, int s2begin, int s2end) {
		this.result = rs;
		this.s1 = s1;
		this.s2 = s2;
		s1Begin = s1begin;
		s1End = s1end;
		s2Begin = s2begin;
		s2End = s2end;
	}

	// 根据规则化的比较结果，抽取出比较结果对象.
	public List<CompareResult> getAleroCompareResultOBJ() {
		String compareRsStr = getAleroCompareResultStr();
		List<CompareResult> compareRs = new ArrayList<CompareResult>();
		getCompareRsEach(compareRsStr, compareRs);
		return compareRs;
	}

	public static final String OLD_FLAG_BEGIN = "@ALERO_OLDSTR_BEGIN@";
	public static final String OLD_FLAG_END = "@ALERO_OLDSTR_END@";
	public static final String NEW_FLAG_BEGIN = "@ALERO_NEWSTR_BEGIN@";
	public static final String NEW_FLAG_END = "@ALERO_NEWSTR_END@";
	public static final String SAME_FLAG_BEGIN = "@ALERO_SAMESTR_BEGIN@";
	public static final String SAME_FLAG_END = "@ALERO_SAMESTR_END@";

	public void getCompareRsEach( String regularStr, List<CompareResult> acrs) {
		String new_regular = "";
		
		String sectionOneStr = "";
		String sectionTwoStr = "";
		int sectionOneStr_begin = 0;
		int sectionOneStr_end = 0;
		int sectionTwoStr_begin = 0;
		int sectionTwoStr_end = 0;
		int type = -1;

		if (regularStr.startsWith(SAME_FLAG_BEGIN)) {
			//解析相同区域
			int beginIndex = SAME_FLAG_BEGIN.length();
			sectionOneStr = regularStr.substring(beginIndex,regularStr.indexOf(SAME_FLAG_END));
			sectionTwoStr = sectionOneStr;
			type = CompareResult.SAME_PART;
			new_regular = regularStr.substring(regularStr.indexOf(SAME_FLAG_END)+SAME_FLAG_END.length());
		}else{
			// 解析不同区域
			String tempRegularStr = "";
			if(regularStr.contains(SAME_FLAG_BEGIN)){
				tempRegularStr = regularStr.substring(0,regularStr.indexOf(SAME_FLAG_BEGIN));
				new_regular = regularStr.substring(regularStr.indexOf(SAME_FLAG_BEGIN));
			}else {
				tempRegularStr = regularStr;
			}
			if(tempRegularStr.contains(OLD_FLAG_BEGIN)){
				sectionOneStr = tempRegularStr.substring(tempRegularStr.indexOf(OLD_FLAG_BEGIN)+OLD_FLAG_BEGIN.length(),tempRegularStr.indexOf(OLD_FLAG_END));
				type = CompareResult.ADD_PART;
			}
			if(tempRegularStr.contains(NEW_FLAG_BEGIN)){
				sectionTwoStr = tempRegularStr.substring(tempRegularStr.indexOf(NEW_FLAG_BEGIN)+NEW_FLAG_BEGIN.length(),tempRegularStr.indexOf(NEW_FLAG_END));
				type = CompareResult.DELE_PART;
			}
			if(!"".equals(sectionOneStr) && !"".equals(sectionTwoStr)){
				type = CompareResult.UPDT_PART;
			}
		}
		
		//计算下表索引位置
		if(acrs.size() == 0){
			sectionOneStr_begin = 0;
			sectionOneStr_end = sectionOneStr_begin+sectionOneStr.length();
			sectionTwoStr_begin = 0;
			sectionTwoStr_end = sectionTwoStr_begin + sectionTwoStr.length();
		}else{
			CompareResult lastACR = acrs.get(acrs.size()-1);
			//这个节点的开始是上一个节点的结束
			sectionOneStr_begin = lastACR.getSectionOneStr_end();
			sectionOneStr_end = sectionOneStr_begin+sectionOneStr.length();
			sectionTwoStr_begin = lastACR.getSectionTwoStr_end();
			sectionTwoStr_end = sectionTwoStr_begin+sectionOneStr.length();
		}
		
		CompareResult acr = new CompareResult(acrs.size(), 
				sectionOneStr, sectionTwoStr, 
				sectionOneStr_begin, sectionOneStr_end, 
				sectionTwoStr_begin, sectionTwoStr_end, type);
		acrs.add(acr);
		
		if(new_regular!=null && !"".equals(new_regular)){
			//截取后面的规则化字符串在此解析
			getCompareRsEach(new_regular, acrs);
		}
	}

	/**
	 * 返回原始比较字符串1
	 * 
	 * @return
	 */
	public String getRootS1() {
		if (this.getRoot() == null) {
			return s1;
		} else {
			return this.getRoot().getRootS1();
		}
	}

	/**
	 * 返回原始比较字符串2
	 * 
	 * @return
	 */
	public String getRootS2() {
		if (this.getRoot() == null) {
			return s2;
		} else {
			return this.getRoot().getRootS2();
		}
	}

	/**
	 * 获取规则化得2字符串比较结果
	 */
	public String getAleroCompareResultStr() {
		String rs = "";
		String leftRs = "";
		String rightRs = "";
		if (this.getLeft() == null) {
			String s1leftCompareRs = s1.substring(0, s1Begin);
			String s2leftCompareRs = s2.substring(0, s2Begin);

			if (!"".equals(s1leftCompareRs)) {
				leftRs += OLD_FLAG_BEGIN + s1leftCompareRs + OLD_FLAG_END;
			}
			if (!"".equals(s2leftCompareRs)) {
				leftRs += NEW_FLAG_BEGIN + s2leftCompareRs + NEW_FLAG_END;
			}
		} else {
			leftRs = this.getLeft().getAleroCompareResultStr();
		}

		if (this.getRight() == null) {
			String s1rightCompareRs = s1.substring(s1End);
			String s2rightCompareRs = s2.substring(s2End);

			if (!"".equals(s1rightCompareRs)) {
				rightRs += OLD_FLAG_BEGIN + s1rightCompareRs + OLD_FLAG_END;
			}
			if (!"".equals(s2rightCompareRs)) {
				rightRs += NEW_FLAG_BEGIN + s2rightCompareRs + NEW_FLAG_END;
			}
		} else {
			rightRs = this.getRight().getAleroCompareResultStr();
		}

		rs = leftRs + SAME_FLAG_BEGIN + result + SAME_FLAG_END + rightRs;
		return rs;
	}

	/**
	 * 返回比较结果2叉树的最左侧结果集
	 * 
	 * @return
	 */
	/*
	 * public SameStringResult getTopLeft() { SameStringResult currentLeftRs =
	 * this.getLeft(); if (currentLeftRs != null) { if (currentLeftRs.getLeft()
	 * == null) { return currentLeftRs; } else { return
	 * currentLeftRs.getTopLeft(); } }else{ return null; }
	 * 
	 * }
	 *//**
	 * 返回比较结果2叉树的最右侧结果
	 * 
	 * @return
	 */
	/*
	 * public SameStringResult getTopRight() { SameStringResult currentRightRs =
	 * this.getRight(); if (currentRightRs != null) { if
	 * (currentRightRs.getRight() == null) { return currentRightRs; } else {
	 * return currentRightRs.getTopRight(); } }else{ return null; }
	 * 
	 * }
	 */

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public int getS1Begin() {
		return s1Begin;
	}

	public void setS1Begin(int s1Begin) {
		this.s1Begin = s1Begin;
	}

	public int getS1End() {
		return s1End;
	}

	public void setS1End(int s1End) {
		this.s1End = s1End;
	}

	public int getS2Begin() {
		return s2Begin;
	}

	public void setS2Begin(int s2Begin) {
		this.s2Begin = s2Begin;
	}

	public int getS2End() {
		return s2End;
	}

	public void setS2End(int s2End) {
		this.s2End = s2End;
	}

	public SameStringResult getLeft() {
		return left;
	}

	public void setLeft(SameStringResult left) {
		this.left = left;
	}

	public SameStringResult getRight() {
		return right;
	}

	public void setRight(SameStringResult right) {
		this.right = right;
	}

	public SameStringResult getRoot() {
		return root;
	}

	public void setRoot(SameStringResult root) {
		this.root = root;
	}

}
