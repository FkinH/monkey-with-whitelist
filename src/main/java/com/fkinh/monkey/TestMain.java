package com.fkinh.monkey;

import java.io.*;

/**
 * Author: fkinh26@gmail.com
 * Date: 2015/12/24
 */
public class TestMain {

    public static void main(String[] args) {
        String dxComm = "D:\\Android\\android-sdk\\build-tools\\23.0.2\\dx.bat --dex --output=\"build\\classes.dex\" \"build\\classes\\main\"";
        String jarComm = "jar cvf build\\monkey.jar build\\classes.dex";
        clearCache();
        System.out.println(exec(dxComm));
        System.out.println(exec(jarComm));
    }

    public static void clearCache(){
        deleteFile("classes.dex");
        deleteFile("monkey.jar");
    }

    /**
     * deleter jar
     * @param jarFile
     */
    public static void deleteFile(String jarFile) {
        File file = new File(jarFile);
        if(file.exists()) {
            file.delete();
        }
    }

    /**
     * exec
     * @param command
     */
    public static int exec(String command) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            return p.exitValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
