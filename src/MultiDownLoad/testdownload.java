package MultiDownLoad;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class testdownload {
    public static void main(String[] args) throws IOException {
       File file = new File("F:" + File.separator + "云盘存储目录" + File.separator + "3.txt");
       if(!file.exists()){
           file.createNewFile();
       }
    }
}
