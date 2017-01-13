package com.arithmetic.stringcompare.utils;

import java.util.List;

import com.arithmetic.stringcompare.bean.CompareResult;
import com.arithmetic.stringcompare.bean.SameStringResult;
/**
 * string compare utils
 * 字符串比较算法
 * @author zgc
 * @time 2017 01 13
 */
public class CompareDiffUtils {
	public static void main(String[] args) {
		String s1 = "acdbbbbcabfc";
		String s2 = "acfbbbbacabcf";
//		String s1 = "abbc";
//		String s2 = "bbaaa";
		//String s1 = "ees";
		//String s2 = "abs";
		SameStringResult ssr = maxPublicString(s1, s2);
		List<CompareResult> acrs= ssr.getAleroCompareResultOBJ();
		//System.out.println("s1:"+getRootStr(acrs, SECTION_ONE));
		//System.out.println("s2:"+getRootStr(acrs, SECTION_TWO));
		
		CompareResult testAcr = acrs.get(acrs.size()-1); 
		int b = testAcr.getSectionOneStr_begin();
		int e = testAcr.getSectionOneStr_end();
		//System.out.println(s1.substring(b,e)+"---"+testAcr.getSectionOneStr());
	}
	
	public static final int SECTION_ONE = 1;
	public static final int SECTION_TWO = 2;
	
	/**
	 * 根据比较结果逆向生成原字符
	 * @param acrs
	 * @param targ ,1 for s1,2 for s2
	 * @return root str
	 */
	public static String getRootStr(List<CompareResult> acrs,int targ){
		String s = "";
		for (int i = 0; i < acrs.size(); i++) {
			CompareResult acr = acrs.get(i);
			if(targ == SECTION_ONE){
				s+=acr.getSectionOneStr();
			}else{
				s+=acr.getSectionTwoStr();
			}
		}
		return s;
	}
	
	/**
	 * 查找2个字符串的最大公共连续字符串.
	 * 方法内部用使用递归算法，
	 * 分别求出 s1,s2根据最大联系字符串maxPublic_s分隔后的2对字符串s1_left和s2_left，s1_right和s2_right
	 * 在次重复该方法求得最大连续字公共字符串。构建2叉树结果
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static SameStringResult maxPublicString(String s1, String s2) {
		char[] s1_arr = s1.toCharArray();
		char[] s2_arr = s2.toCharArray();
		// 分别求出s1,s2字符串的长度
		int s1len = s1_arr.length;
		int s2len = s2_arr.length;
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return null;
		}
		if (s1.length() == 1 || s2.length() == 1) {
			String rs_temp = "";
			if (s1.length() == 1) {
				if (s2.contains(s1)) {
					rs_temp = s1;
				}
			} else {
				if (s1.contains(s2)) {
					rs_temp = s2;
				}
			}
			if (!"".equals(rs_temp)) {
				int s1Begin = s1.indexOf(rs_temp);
				int s1End = s1Begin + rs_temp.length();
				int s2Begin = s2.indexOf(rs_temp);
				int s2End = s2Begin + rs_temp.length();
				SameStringResult ssr = new SameStringResult(rs_temp, s1, s2,
						s1Begin, s1End, s2Begin, s2End);
				return ssr;
			} else {
				return null;
			}
		} else {
			// 记录相同元素下标，记录相同的元素个数
			int index = 0, count = 0;
			boolean setOneFound = false;
			boolean onlyOneFound = true;
			int firstOneIndex = -1;
			int firstOneCount = -1;
			// 遍历s1
			for (int i = 0; i < s1len; i++) {
				char si = s1_arr[i];
				// 遍历s2
				for (int j = 0; j < s2len; j++) {
					char sj = s2_arr[j];
					// 取出s2的每一个元素s1进行比较，
					if (si == sj) {
						if(!setOneFound){
							setOneFound = true;
							firstOneIndex = i;
							firstOneCount = 0;
						}
						
						// 如果有相同的元素，则同时都往后面移动
						for (int k = 1; i + k < s1len && j + k < s2len
								&& s1_arr[i + k] == s2_arr[j + k]; k++) {
							onlyOneFound = false;
							// 选出最大公共字符串
							if (k > count) {
								// 记录最大公共字符串长度的个数
								count = k;
								// 记录最大公共字符串长度的下标
								index = i;
							}
						}
					}
				}
			}
			if (count == 0 && !setOneFound) {
				SameStringResult ssr = new SameStringResult("", s1, s2,0, 0, 0, 0);
				return ssr;
			} else {
				if(setOneFound && onlyOneFound){
					index = firstOneIndex;
					count = firstOneCount;
				}
				String rs = "";
				for (int i = 0; i <= count; i++) {
					rs += s1_arr[index + i];
				}
				int s1Begin = s1.indexOf(rs);
				int s1End = s1Begin + rs.length();
				int s2Begin = s2.indexOf(rs);
				int s2End = s2Begin + rs.length();
				SameStringResult ssr = new SameStringResult(rs, s1, s2,
						s1Begin, s1End, s2Begin, s2End);

				String leftS1 = s1.substring(0, s1Begin);
				String leftS2 = s2.substring(0, s2Begin);
				SameStringResult leftRs = maxPublicString(leftS1, leftS2);
				if(leftRs!=null){
					leftRs.setRoot(ssr);
					ssr.setLeft(leftRs);	
				}
				String rightS1 = s1.substring(s1End);
				String rightS2 = s2.substring(s2End);
				SameStringResult rightRs = maxPublicString(rightS1, rightS2);
				if(rightRs!=null){
					rightRs.setRoot(ssr);
					ssr.setRight(rightRs);
					
				}
				ssr.setRoot(null);
				return ssr;
			}
		}
	}
}
