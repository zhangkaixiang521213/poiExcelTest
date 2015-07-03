package poi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contents {
	
	public static String  fileToBeRead="E:\\excel\\file\\2014年10月 - 副本.xlsx";
	public static String  fileToBeReadW="E:\\excel\\file\\2014年10月.xlsx";
	//sheet
	public static int sheetIndex=0;
	//sheet1 H 7 sheet2 I 8 sheet3
	//sheet1 maxc=1 sheet2 maxc=1 sheet3 maxc=1
	public static int startIndex=7;
	
	//写总计夸几个
	public static int totalIndex=3;
	//是否写库
	public static boolean  isWrite=true;
	
	//赠品组数
	public static int totalCount=10;
	
	public static void main(String[] args) {
		System.out.println(getGiftGoods(550F));

	}
	
	public static String getGiftGoods(Float price){
		Map<Float,String> map = new HashMap<Float,String>();
		
		map.put(170.94F, "决明子枕2只");
		map.put(550F, "床上用品6件套");
		map.put(470.09F, "玫瑰花图案床单+被套+2只枕套1.5米");
		map.put(700F, "床上用品12件套");
		map.put(200F, "法兰绒空调毯双人");
		map.put(394.87F, "玫瑰花图案2件套（床单+被套）");
		map.put(344.8F, "床笠1.8米");
		map.put(500F, "蓝色床单+被套+2只枕套1.8米");
		map.put(300F, "床笠1.8米");
		map.put(100F, "蓝色床单");
		
		List<Float> list = new ArrayList<Float>();
		list.add(170.94F);
		list.add(550F);
		list.add(470.09F);
		list.add(700F);
		list.add(200F);
		list.add(394.87F);
		list.add(344.8F);
		list.add(500F);
		list.add(300F);
		list.add(100F);
		
		String goods=map.get(price);
		if(goods==null){
			goods="";
		}
		return goods;
		/*
		
		if(list.contains(price)){
			
		}
		
		BigDecimal price_bd=new BigDecimal(Float.toString(price));
		*/
	}
	
	

}
