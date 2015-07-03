package poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSXFile {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
//		 Workbook wb = WorkbookFactory.create(new FileInputStream("E:\\excel\\test\\workbook.xlsx"));
		String fileToBeRead = Contents.fileToBeRead; // excel位置
		String fileToBeReadW = Contents.fileToBeReadW; // excel位置
//		String fileToBeRead = "E:\\excel\\test\\workbook.xlsx"; // excel位置
//		 OPCPackage pkg = OPCPackage.open(new File(fileToBeRead));
		
		int sheetIndex = Contents.sheetIndex;
		 
		 XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(fileToBeRead));
//		 XSSFWorkbook wb = new XSSFWorkbook(pkg);
		 
		 List<Bean> list = new ArrayList<Bean>();
		 Map<Integer,List<Bean>> rmap = new HashMap<Integer,List<Bean>>();
		 Bean bean = null;
		 Sheet sheet = wb.getSheetAt(sheetIndex);
		 List<Integer> inlist = new ArrayList<Integer>();
		 int c=Contents.startIndex;
		 int startIndex=c;
		 for(int i=1;i<=Contents.totalCount;i++){
			 inlist.add(c);
			 c+=2;
		 }
//		 inlist.add(7);
//		 inlist.add(9);
//		 inlist.add(11);
//		 inlist.add(13);
//		 inlist.add(15);
//		 inlist.add(17);
		 System.out.println("ok");
		 
		 int totalRow=0;
		 int maxc=0;
		 for (Row row : sheet) {
			 
			 if(row.getRowNum()==0){
				 continue;
			 }
			 list = new ArrayList<Bean>();
		        for (Cell cell : row) {
		        	if(cell.getColumnIndex()<startIndex){
		        		continue;
		        	}
//		            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
		            Integer cindex = cell.getColumnIndex();
		            
		            if(!inlist.contains(cindex)){
		            	continue;
		            }
		            Cell celln =  row.getCell(cindex);
		            Double cellValue = celln.getNumericCellValue();
//		            if(!(cellValue>0)){
//		            	continue;
//		            }
		            
		            if(cellValue==null){
		            	continue;
		            }
		            
		            Cell cellp =  row.getCell(cindex+1);
		            if(cellp == null){
		            	continue;
		            }
		            
		            Double cellValuep = cellp.getNumericCellValue();
		            
		            BigDecimal cellValuep_bd=new BigDecimal(Double.toString(cellValuep));
		            BigDecimal cellValue_bd=new BigDecimal(Double.toString(cellValue));
		            Double sum =  cellValue_bd.multiply(cellValuep_bd).doubleValue();
		            
		            String goods = Contents.getGiftGoods(Float.valueOf(String.valueOf(cellValuep)));
		            
		            bean = new Bean(cellValue, cellValuep, sum, goods);
		            list.add(bean);
		            
		            System.out.println("RowNum="+row.getRowNum() +"  cellValue="+cellValue +"  cellValuep="+cellValuep+"  sum="+sum+" goods="+goods);
		        }
		        if(list.size()>0){
			        rmap.put(row.getRowNum(), list);
			        totalRow++;
		        }
		        if(maxc<list.size()){
		        	maxc = list.size();
		        }
		    }
		 System.out.println("maxc="+maxc);
		 System.out.println("totalRow="+totalRow);
		 
		 if(Contents.isWrite){
			 writeNumber(rmap,totalRow,sheetIndex,maxc,startIndex,fileToBeReadW);
		 }
	}
	
	private static void writeNumber(Map<Integer,List<Bean>> rmap,int totalRow,int sheetIndex,int maxc,int startIndex,String fileToBeRead) throws FileNotFoundException, IOException{
//		String fileToBeRead = "E:\\excel\\file\\2014年1月 - 副本 (2).xlsx"; // excel位置
		 XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(fileToBeRead));
		 Sheet sheet = wb.getSheetAt(sheetIndex);
		 for (Row row : sheet) {
			 int rowNums = row.getRowNum();
			 if(rowNums>totalRow){
				 break;
			 }
			 
			 if(rowNums>0 && rowNums<=totalRow){
			 
//				 System.out.print("RowNum="+row.getRowNum() +"  ");
				 int rowNum = row.getRowNum();
				 List<Bean> list = rmap.get(rowNum);
				 int colIndex = startIndex;
				 int index=1;
				 Double totalSum = 0d;
				 XSSFCellStyle style =  wb.createCellStyle(); // 样式对象
				 DataFormat format = wb.createDataFormat();
				 style.setDataFormat(format.getFormat("#,##0.00"));
				 for(Bean bean:list){
					 if(index == 1){
						 System.out.println("colIndex="+colIndex);
						 Cell cell = row.createCell(colIndex);
						 cell.setCellValue(bean.getAccount());
						 
						 Cell price = row.createCell(colIndex+1);
						 price.setCellValue(bean.getPrice());
						 price.setCellStyle(style);
						 
						 Cell goods = row.createCell(colIndex+2);
						 goods.setCellValue(bean.getGiftGoods());
						 
						 Double sum = bean.getSum();
						 BigDecimal sum_bd = new BigDecimal(Double.toString(sum));
						 BigDecimal totalSum_bd = new BigDecimal(Double.toString(totalSum));
						 totalSum = sum_bd.add(totalSum_bd).doubleValue();
						 
						 System.out.println("RowNum="+row.getRowNum() +"  cellValue="+bean.getAccount() +"  cellValuep="+String.valueOf(bean.getPrice())+"  sum="+sum+" goods="+goods);

					 }else if(index == 2){
						 System.out.println("colIndex="+colIndex);
						 Cell cell = row.createCell(colIndex);
						 cell.setCellValue(bean.getAccount());
						 
						 Cell price = row.createCell(colIndex+1);
						 price.setCellValue(Float.valueOf(String.valueOf(bean.getPrice())));
						 price.setCellStyle(style);
						 
						 Cell goods = row.createCell(colIndex+2);
						 goods.setCellValue(bean.getGiftGoods());
						 
						 Double sum = bean.getSum();
						 BigDecimal sum_bd = new BigDecimal(Double.toString(sum));
						 BigDecimal totalSum_bd = new BigDecimal(Double.toString(totalSum));
						 totalSum = sum_bd.add(totalSum_bd).doubleValue();
					 }else if(index == 3){
						 System.out.println("colIndex="+colIndex);
						 Cell cell = row.createCell(colIndex);
						 cell.setCellValue(bean.getAccount());
						 
						 Cell price = row.createCell(colIndex+1);
						 price.setCellValue(bean.getPrice());
						 price.setCellStyle(style);
						 
						 Cell goods = row.createCell(colIndex+2);
						 goods.setCellValue(bean.getGiftGoods());
						 
						 Double sum = bean.getSum();
						 BigDecimal sum_bd = new BigDecimal(Double.toString(sum));
						 BigDecimal totalSum_bd = new BigDecimal(Double.toString(totalSum));
						 totalSum = sum_bd.add(totalSum_bd).doubleValue();
					 }
					 index+=1;
					 colIndex+=3;
					 
				 }
				 int totalWriteIndex=startIndex+Contents.totalIndex;
				 Cell cell = row.createCell(totalWriteIndex);
				 cell.setCellValue(totalSum);
				 cell.setCellStyle(style);
				 
				 System.out.println("totalSum="+String.valueOf(totalSum));
			 }
		 }
		
		 /* */
		 FileOutputStream out = null;
         try {
             out = new FileOutputStream(fileToBeRead);
             wb.write(out);
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             try {
                 out.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         
         System.out.println("完成。。。");
        
	}
	
	
}
