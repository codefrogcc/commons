package com.github.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    private static Properties properties;

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);

    public static void load(String path,String fileName){
        //这里的path是项目文件的绝对路径
        //先获取项目绝对路径：Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //然后在项目路径后面拼接"properties/sysConfig.properties";
        properties= new Properties();// 属性集合对象
        InputStream fis;
        try {
            LOGGER.debug(path);
            if(StringUtils.isBlank(path)){
                fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            }else{
                fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(path + "/" + fileName);
            }
            properties.load(fis);
            fis.close();// 关闭流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取properties文件对应的属性值
     * @param path
     * @param fileName
     * @param key
     * @return String
     */
    public static String getProperty(String path,String fileName,String key){
        if(properties == null){
            load(path,fileName);
            LOGGER.debug("重新加载一遍");
        }
        InputStream fis = null;
        try {
            if(StringUtils.isBlank(path)){
                fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            }else{
                fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(path + "/" + fileName);
            }
            properties.load(fis);// 将属性文件流装载到Properties对象中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();// 关闭流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.debug("查询到的"+key+"的值："+properties.getProperty(key));
        return properties.getProperty(key);
    }

    /**
     * 修改或新增properties文件对应的属性值
     * @param path
     * @param fileName
     * @param key
     * @param value
     * @return Boolean
     */
    public static Boolean updateProperty(String path,String fileName,String key,String value){
        if(properties==null){
            load(path,fileName);
            LOGGER.debug("修改前重新加载一遍");
        }
        LOGGER.debug("获取添加或修改前的属性值："+key+"=" + properties.getProperty(key));
        properties.setProperty(key, value);
        // 文件输出流
        FileOutputStream fos = null;
        try {
            if(StringUtils.isBlank(path)){
                fos = new FileOutputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/" + fileName);
            }else{
                fos = new FileOutputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath() + path + "/" + fileName);
            }
            // 将Properties集合保存到流中
            properties.store(fos, "Copyright (c) Boxcode Studio");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                fos.close();// 关闭流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.debug("获取添加或修改后的属性值："+key+"=" + properties.getProperty(key));
        return true;
    }
}
