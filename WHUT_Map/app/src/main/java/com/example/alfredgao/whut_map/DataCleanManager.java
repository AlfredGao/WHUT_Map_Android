package com.example.alfredgao.whut_map;
import java.io.File;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.math.BigDecimal;

/**
 * Created by AlfredGao on 5/22/16.
 */
public class DataCleanManager {



    public static long getFolderSize(File file) throws Exception {
        long size = 0;

        try {
            File[] fileList = file.listFiles();

            for (int i = 0; i < fileList.length; i++) {

                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static String caculateFormatOfCache (long size) {
        double kiloByte = size / 1024;

        if (kiloByte < 1) {
            String s = String.valueOf(size) + " Byte";
            return s;
        }

        double mByte = kiloByte/1024;

        if (mByte < 1) {
            BigDecimal result = new BigDecimal(Double.toString(kiloByte));
            String s = result.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString() + " KB";
            return s;
        }

        double gByte = mByte / 1024;
        if (gByte < 1) {
            BigDecimal result = new BigDecimal(Double.toString(mByte));
            String s = result.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString()+ " MB";
            return s;
        }

        double tByte = gByte / 1024;

        if (tByte < 1) {
            BigDecimal result = new BigDecimal(Double.toString(gByte));
            String s = result.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString()+ " GB";
            return s;
        }

        BigDecimal result = new BigDecimal(tByte);
        String s = result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + " TB";
        return s;

    }


    public static String  getTotalSize(Context context) throws Exception {
        long chaceSize = getFolderSize(context.getCacheDir());
        return caculateFormatOfCache(chaceSize);
    }

    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] child = dir.list();
            for (int i = 0; i < child.length; i++) {
                boolean success = deleteDir(new File(dir, child[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }




}
