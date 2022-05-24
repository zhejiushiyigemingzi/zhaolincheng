package co.jsp.testList;

import java.util.ArrayList;
import java.util.List;

public class TestList {

	public static void main(String[] args){
		
		//错误private String list;
		// 创键一个String类型的LIST对象
		List<String> list = new ArrayList<String>();
		
		//装张三李四王五
		list.add("张三");
		list.add("李四");
		list.add("王五");
		//错误3list.add(123);
		//普通FOR循环打印集合里的每一个对象
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		
		//for each打印集合里的每个对象
		for(String rs : list){
			System.out.println(rs);
		}
		
		//创建一个INT类型的LIST对象
		//
	    //错误1List<Int> list1 = new ArrayList<Int>();
		//错误2List<int> list1 = new ArrayList<int>();
		List<Integer> list1 = new ArrayList<Integer>();
		
		list1.add(123);
		list1.add(456);
		list1.add(789);
	    
		for(int x = 0;x<list1.size();x++){
			System.out.println(list1.get(x));
		}
		
		for(Integer one : list1){
			System.out.println(one);
		}
	}
	
	
	
}
