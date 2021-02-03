package com.wangzhen.jvm;



import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class DirEntry {
    public String absDir;

    public  DirEntry(String classPath) throws Exception {
        File dir = new File(classPath);
        if(dir.exists()){
            absDir = dir.getAbsolutePath();
            log.info("当前路径:{}",absDir);
        }else {
            throw new Exception("文件夹不存在");
        }

    }

    public byte[] readClass(String className) throws Exception {
        File file = new File(absDir, className);
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        byte[] temp = new byte[1024];
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            out = new ByteArrayOutputStream(1024);
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
        }catch (Exception e){
            log.error("读取文件失败:{}",e);
        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return out.toByteArray();
    }

    public void printString(byte[] classByte){
        System.out.println(new String(classByte));
    }

    public static void main(String[] args) {

        byte [] b = {127,1,0,0,0,0,1};
        System.out.println(Integer.toHexString(b[0]));

    }

    public  void printHexString( byte[] b)
    {
        for (int i = 0; i < b.length; i++)
        {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1)
            {
               hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
        System.out.println("");
    }

}
