package poi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contents {
	
	public static String  fileToBeRead="E:\\excel\\file\\2014��10�� - ����.xlsx";
	public static String  fileToBeReadW="E:\\excel\\file\\2014��10��.xlsx";
	//sheet
	public static int sheetIndex=0;
	//sheet1 H 7 sheet2 I 8 sheet3
	//sheet1 maxc=1 sheet2 maxc=1 sheet3 maxc=1
	public static int startIndex=7;
	
	//д�ܼƿ伸��
	public static int totalIndex=3;
	//�Ƿ�д��
	public static boolean  isWrite=true;
	
	//��Ʒ����
	public static int totalCount=10;
	
	public static void main(String[] args) {
		System.out.println(getGiftGoods(550F));

	}
	
	public static String getGiftGoods(Float price){
		Map<Float,String> map = new HashMap<Float,String>();
		
		map.put(170.94F, "��������2ֻ");
		map.put(550F, "������Ʒ6����");
		map.put(470.09F, "õ�廨ͼ������+����+2ֻ����1.5��");
		map.put(700F, "������Ʒ12����");
		map.put(200F, "�����޿յ�̺˫��");
		map.put(394.87F, "õ�廨ͼ��2���ף�����+���ף�");
		map.put(344.8F, "����1.8��");
		map.put(500F, "��ɫ����+����+2ֻ����1.8��");
		map.put(300F, "����1.8��");
		map.put(100F, "��ɫ����");
		
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
