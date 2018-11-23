package com.github.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    private static Logger logger = LoggerFactory.getLogger(IOUtils.class);

    /**
     *
     * 逐行读取文件，返回List集合
     * @param filePath 文件路径
     * @return List 集合
     */
    public static List<String> readLine(String filePath) {
        List<String> list = new ArrayList<String>();
		/* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                list.add(lineTxt);
            }
            br.close();
        } catch (Exception e) {
            logger.error("read errors :" + e);
        }
        return list;
    }

    /**
     * 逐行写入文件
     * @param content 内容
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    public static void writeFile(List<String> content, String filePath, String fileName) {
        FileOutputStream fos = null;
        BufferedOutputStream out = null;
        // BufferedWriter bw = null;
        try {
            if (!new File(filePath).exists()) {
                new File(filePath).mkdirs();
            }
            File fout = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(fout);
            // bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
			/*
			 * for (int i = 0; i < content.size() ; i++) { bw.write(content.get(i));
			 * //bw.newLine(); }
			 */
            out = new BufferedOutputStream(fos);
            for (int i = 0; i < content.size(); i++) {
                byte[] buf = content.get(i).getBytes("UTF-8");
                out.write(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // fos.close();
                // bw.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制文件
     * @param inStream 流
     * @param destFilePath 复制到的路径
     * @param destFileName 复制的文件名称
     */
    public static void copyFile(InputStream inStream,String destFilePath,String destFileName) {
        BufferedReader br = null;
        BufferedOutputStream outBuff = null;
        try {
			/*FileInputStream in = new FileInputStream("e:/file/eee.txt");
			FileOutputStream out = new FileOutputStream("e:/file/test.txt");
			StringBuffer sb = new StringBuffer();
			byte b[] = new byte[1024];
			int len = 0;

			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}*/
            if (!new File(destFilePath).exists()) {
                new File(destFilePath).mkdirs();
            }
            //FileInputStream inStream = new FileInputStream(new File(srcQualifiedName));
            FileOutputStream outStream = new FileOutputStream(new File(destFilePath + "/" + destFileName));
            //BufferedInputStream inBuff = new BufferedInputStream(inStream);
            outBuff = new BufferedOutputStream(outStream);
            br = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                //lineTxt删除\r
				/*if(lineTxt.contains("\\r")) {
					String[] split = lineTxt.split("\\r");
					String con = split[0]+split[1];
					byte[] bytes = con.getBytes("UTF-8");
					outBuff.write(bytes);
				}*/
                String con = lineTxt + "\n";
                byte[] bytes = con.getBytes("UTF-8");
                outBuff.write(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                outBuff.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
