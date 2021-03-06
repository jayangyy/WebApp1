/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Jayang
 */
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;  
import java.io.FileWriter;  
import java.io.IOException;
import java.io.InputStream;  
import java.io.PrintWriter;  
import java.util.HashSet;  
import java.util.Set;  

import org.apache.commons.logging.Log;
/** 
 * 文件操作公用类
 * @author 杨洋 
 * @date 2015/7/20 
 * @modified by 
 * @modified  2015/7/20
 * @since JDK1.7 
 * @see com.woyo.utils.FileUtil 
 */  
public class FileUnitTool {  
 //   public static Log logger = Logger.getLogger(FileUtil.class);  
    public static Set<String> sets = new HashSet<String>();  
    /** 
     * 创建文件夹 
     * @param strFilePath 
     * 文件夹路径 
     */  
    public static boolean CreateFolder(String strFilePath) {  
        boolean bFlag = false;  
        try {  
            File file = new File(strFilePath.toString());  
            if (!file.exists()) {  
                bFlag = file.mkdir();  
            }  
        } catch (Exception e) {  
            //logger.error("新建目录操作出错" + e.getLocalizedMessage());  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    /** 
     * 创建文件 
     * @param strFilePath 
     * 文件夹路径 
     *  @param strFileContent 
     * 文件内容
     */  
    public boolean CreateFile(String strFilePath, String strFileContent) {  
        boolean bFlag = false;  
        try {  
            File file = new File(strFilePath.toString());  
            if (!file.exists()) {  
                bFlag = file.createNewFile();  
            }  
            if (bFlag == Boolean.TRUE) {  
                FileWriter fw = new FileWriter(file);  
                PrintWriter pw = new PrintWriter(fw);  
                pw.println(strFileContent.toString());  
                pw.close();  
            }  
        } catch (Exception e) {  
          ///  logger.error("新建文件操作出错" + e.getLocalizedMessage());  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    /** 
     * 删除文件 
     * @param strFilePath  文件地址
     * @return 
     */  
    public static boolean RemoveFile(String strFilePath) {  
        boolean result = false;  
        if (strFilePath == null || "".equals(strFilePath)) {  
            return result;  
        }  
        File file = new File(strFilePath);  
        if (file.isFile() && file.exists()) {  
            result = file.delete();  
            if (result == Boolean.TRUE) {  
                //logger.debug("[REMOE_FILE:" + strFilePath + "删除成功!]");  
            } else {  
               /// logger.debug("[REMOE_FILE:" + strFilePath + "删除失败]");  
            }  
        }  
        return result;  
    }  
  
    /** 
     * 删除文件夹(包括文件夹中的文件内容，文件夹) 
     * @param strFolderPath 文件夹地址
     * @return 
     */  
    public static boolean RemoveFolder(String strFolderPath) {  
        boolean bFlag = false;  
        try {  
            if (strFolderPath == null || "".equals(strFolderPath)) {  
                return bFlag;  
            }  
            File file = new File(strFolderPath.toString());  
            bFlag = file.delete();  
            if (bFlag == Boolean.TRUE) {  
               // logger.debug("[REMOE_FOLDER:" + file.getPath() + "删除成功!]");  
            } else {  
                //logger.debug("[REMOE_FOLDER:" + file.getPath() + "删除失败]");  
            }  
        } catch (Exception e) {  
           ///logger.error("FLOADER_PATH:" + strFolderPath + "删除文件夹失败!");  
            e.printStackTrace();  
        }  
        return bFlag;  
    }  
  
    /** 
     * 移除所有文件 
     *  
     * @param strPath 文件地址
     */  
    public static void RemoveFiles(String strPath) {  
        File file = new File(strPath);  
        if (!file.exists()) {  
            return;  
        }  
        if (!file.isDirectory()) {  
            return;  
        }  
        String[] fileList = file.list();  
        File tempFile = null;  
        for (int i = 0; i < fileList.length; i++) {  
            if (strPath.endsWith(File.separator)) {  
                tempFile = new File(strPath + fileList[i]);  
            } else {  
                tempFile = new File(strPath + File.separator + fileList[i]);  
            }  
            if (tempFile.isFile()) {  
                tempFile.delete();  
            }  
            if (tempFile.isDirectory()) {  
                RemoveFiles(strPath + "/" + fileList[i]);// 下删除文件夹里面的文件  
                RemoveFolder(strPath + "/" + fileList[i]);// 删除文件夹  
            }  
        }  
    }  
    /** 
     * 写入文件 
     *  
     * @param strPath 文件保存绝对路径地址
     */ 
  public static void InsertFile(String savePath,InputStream inputstream) throws IOException{
	  int bytesum = 0;  
      int byteread = 0;  
	  @SuppressWarnings("resource")
	  FileOutputStream fs = new FileOutputStream(savePath);  
	  byte[] buffer=new byte[1024];
	  while ((byteread = inputstream.read(buffer)) != -1) {  
          bytesum += byteread; // 字节数 文件大小  
          System.out.println(bytesum);  
          fs.write(buffer, 0, byteread);  
      }  
	  inputstream.close();   
  }
    public static void CopyFile(String oldPath, String newPath) {  
        try {  
            int bytesum = 0;  
            int byteread = 0;  
            File oldfile = new File(oldPath);  
            if (oldfile.exists()) { // 文件存在时  
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件  
                @SuppressWarnings("resource")
				FileOutputStream fs = new FileOutputStream(newPath);  
                byte[] buffer = new byte[1444];  
                while ((byteread = inStream.read(buffer)) != -1) {  
                    bytesum += byteread; // 字节数 文件大小  
                    System.out.println(bytesum);  
                    fs.write(buffer, 0, byteread);  
                }  
                inStream.close();  
               // logger.debug("[COPY_FILE:" + oldfile.getPath() + "复制文件成功!]");  
            }  
        } catch (Exception e) {  
            System.out.println("复制单个文件操作出错 ");  
            e.printStackTrace();  
        }  
    }  
  
    public static void CopyFolder(String oldPath, String newPath) {  
        try {  
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹  
            File a = new File(oldPath);  
            String[] file = a.list();  
            File temp = null;  
            for (int i = 0; i < file.length; i++) {  
                if (oldPath.endsWith(File.separator)) {  
                    temp = new File(oldPath + file[i]);  
                } else {  
                    temp = new File(oldPath + File.separator + file[i]);  
                }  
                if (temp.isFile()) {  
                    FileInputStream input = new FileInputStream(temp);  
                    FileOutputStream output = new FileOutputStream(newPath  
                            + "/ " + (temp.getName()).toString());  
                    byte[] b = new byte[1024 * 5];  
                    int len;  
                    while ((len = input.read(b)) != -1) {  
                        output.write(b, 0, len);  
                    }  
                    output.flush();  
                    output.close();  
                    input.close();  
                  //  logger.debug("[COPY_FILE:" + temp.getPath() + "复制文件成功!]");  
                }  
                if (temp.isDirectory()) {// 如果是子文件夹  
                    CopyFolder(oldPath + "/ " + file[i], newPath + "/ "  
                            + file[i]);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("复制整个文件夹内容操作出错 ");  
            e.printStackTrace();  
        }  
    }  
  
    public static void moveFile(String oldPath, String newPath) {  
        CopyFile(oldPath, newPath);  
        //removeFile(oldPath);  
    }  
  
    public static void moveFolder(String oldPath, String newPath) {  
        CopyFolder(oldPath, newPath);  
        //removeFolder(oldPath);  
    }  
}  
