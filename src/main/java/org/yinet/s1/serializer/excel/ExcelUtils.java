package org.yinet.s1.serializer.excel;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelUtils {
    /**
     *
     */
    private static Logger logger;
    public static Map<String,Object> bean = new HashMap<>();
    static {
        logger = Logger.getLogger(ExcelUtils.class);
    }

    public static void main(String[] args) {
        ClassPathExcelContext("src/main/resources/excel/CoreBasic.xlsx","org.yinet.s1.dao.excel.CoreBasic");
    }

    /**
     * 解析ecxel表
     * */
    private static void ClassPathExcelContext(String fileName,String clazzName){
        List<List<String>> objs;//用于后面转对象使用
        objs = new ArrayList<List<String>>();
        Workbook book = null;
        FileInputStream fis = null;
        try {
            logger.info("开始读取"+fileName+"文件的内容");
            fis = new FileInputStream(fileName);//取到文件
            book = new XSSFWorkbook(fis);
            fis.close();//及时关闭流
            logger.info("开始读取"+fileName+"工作谱的内容");
            Sheet sheet = book.getSheetAt(0);//取到工作谱（每一页是一个工作谱）
            Iterator<Row> rows = sheet.iterator();
            List<String> obj = null;
            int i = 0;
            logger.info("开始读取"+fileName+"行的内容");
            while (rows.hasNext()){
                Row row = rows.next();
                Iterator<Cell> cells = row.iterator();
                obj = new ArrayList<String>();
                logger.info("开始读取"+fileName+"列的内容");
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cell.setCellType(Cell.CELL_TYPE_STRING);//全部转换成string类型
                            break;
                    }
                    obj.add(cell.getStringCellValue());
                }
                objs.add(obj);
            }
            serializerFile(objs,clazzName);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                book.close();
            }catch (Exception e){
                logger.error(e);
            }
        }
    }
    private static void serializerFile(List<List<String>> file,String clazzName){
        List<Map<String,String>> objMap = new ArrayList<Map<String, String>>();
        Map<String,String> objs;
        logger.info("开始转换成map");
        for (int i = 1;i<file.size();i++){
            objs = new HashMap<String, String>();
            for (int j = 0;j<file.get(i).size();j++){
                objs.put(file.get(0).get(j),file.get(i).get(j));
            }
            objMap.add(objs);
        }
        System.err.println(objMap);
        serializerObj(objMap,clazzName);
    }
    private static void serializerObj(List<Map<String,String>> objs,String clazzName){
        Class clazz = null;
        Object beanObj;
        String[] str = clazzName.split("\\.");
        String beanName = str[str.length-1];
        System.err.println(beanName);
        try {
            logger.info("反射");
            clazz = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
            throw new RuntimeException("-------找不到-------"+clazzName);
        }

        try {
            logger.info("开始实例化反射类");
            beanObj = clazz.newInstance();
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(""+clazzName);
        }
        for (int i = 0;i<objs.size();i++) {
            for (Map.Entry<String, String> entry : objs.get(i).entrySet()) {
                try {
                    Object value = null;
                    logger.info("开始实例化反射属性");
                    Method m = writeMethod(beanObj, entry.getKey());

                    Class<?>[] setMethod = m.getParameterTypes();
                    //判断属性类型并赋值
                    for (Class<?> c : setMethod) {
                        if (c == int.class) value = Integer.parseInt(entry.getValue());
                        else if (c == float.class) value = Float.parseFloat(entry.getValue());
                        else if (c == double.class) value = Double.parseDouble(entry.getValue());
                        else if (c == long.class) value = Long.parseLong(entry.getValue());
                        else if (c == short.class) value = Short.parseShort(entry.getValue());
                        else value = entry.getValue();
                    }
                    m.invoke(beanObj, value);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
            bean.put(beanName+i,beanObj);
        }
        System.err.println(bean);
    }
    private static Method writeMethod(Object beanObj, String name) {
        //得到属性的set方法用于注入
        Method m;
        Field ff;
        logger.info("开始拼接set方法");
        String methodName = "set" + name.substring(0, 1).toUpperCase()
                + name.substring(1);
        try {
            logger.info("反射字段");
            //获取该类的字段
            ff = beanObj.getClass().getDeclaredField(name);
            ff.setAccessible(true);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(beanObj.getClass()+"没有"+name+"这个属性");
        }
        try {
            m = beanObj.getClass().getMethod(methodName,ff.getType());
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(beanObj.getClass()+"没有"+methodName+"这个方法");
        }
        return m;
    }
    public static Object getExcelBean(String beanName){
        if(bean.containsKey(beanName)) return bean.get(beanName);
        return null;
    }
}  