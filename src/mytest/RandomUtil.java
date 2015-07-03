package mytest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
 
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.TreeBag;
 
/**
 * 按照权重随机抽取元素
 * @author heyang
 *
 */
public class RandomUtil {
    /**
     * 根据指定的样本数组和样本-权重映射随机抽取一个元素
     * @param elementArray 样本数组
     * @param weightMap 样本-权重映射
     * @return
     * @throws Exception
     */
    public static Object randomByWeight(Object[] elementArray,Map<Object,Integer> weightMap) throws Exception{
        if(elementArray==null||elementArray.length==0){
            throw new Exception("ElementArray must not be empty!");
        }
        if(weightMap==null|| weightMap.isEmpty()){
            throw new Exception("WeightMap must not be empty!");
        }
        int[] weightArray = new int[elementArray.length+1];
        Object[] tempElementArray = new Object[elementArray.length];
        for(int i=0;i<elementArray.length;i++){
            tempElementArray[i] = elementArray[i];
            Integer weight = weightMap.get(elementArray[i]);
            weightArray[i+1] = weight+weightArray[i];
        }
        double randomNum = weightArray[weightArray.length-1]*Math.random();
        int index = bidSearch(weightArray,randomNum);
        return index==-1?null:tempElementArray[index];
    }
     
    private static int bidSearch(int[] desArray,double des){
        int low = 0;
        int high = desArray.length-1;   
        while(low <= high) {
            int middle = (low + high)/2;   
            if(des > desArray[middle] && des<=desArray[middle+1]) {   
                return middle;
            }else if(des <desArray[middle]) {   
                high = middle - 1;   
            }else {   
                low = middle + 1;   
            }  
        }  
        return -1;
    }
     
    public static void main(String[] args) throws Exception{
        String[] a = {"a","b","c","d","e"};
        Map<Object,Integer> b = new HashMap<Object, Integer>();
        b.put("a", 12);
        b.put("b", 11);
        b.put("c", 0);
        b.put("d", 19);
        b.put("e", 0);
        b.put("f", 20);
        int total = 0;
        for(Object x:a){
            int c = b.get(x);
            total+=c;
        }
        for(Object x:a){
            BigDecimal percent = new BigDecimal(b.get(x)).divide(new BigDecimal(total),8,BigDecimal.ROUND_HALF_EVEN);
            System.out.println("Element:"+x+"\tpercent:"+ percent.toString());
        }
        Bag bag = new TreeBag();
        for(int i=0;i<1000000;i++){
            Object res = randomByWeight(a,b);
            bag.add(res);
        }
        for(Object x:a){
            System.out.println("Element:"+x+"\tcount:"+bag.getCount(x));
        }
         
    }
}
