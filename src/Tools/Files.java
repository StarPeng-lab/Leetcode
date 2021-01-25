package Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//将data输入到文件中
public class Files {

    public static void writeToFile(String filePath, Object data){
        writeToFile(filePath, data, false); //append: false,默认不追加到文件末尾；true,默认追加到文件末尾
    }

    /**
     *
     * @param filePath：文件路径
     * @param data：输入的内容
     * @param append：是否追加到文件末尾
     */
    public static void writeToFile(String filePath, Object data, boolean append){
        if(filePath == null || data == null)
            return;

        try{
            File file = new File(filePath);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, append);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(data.toString());
            out.flush();
            out.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
