package DailyInterviewPro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class CheckFileExist {

    public static void main(String[] args) throws IOException {
        //File f= new File("/home/thedaydreamer4996/Varun/DataStructuresPractice/src/Tree/DiameterOfBT.java");
        System.out.println(Paths.get(new File("../").getCanonicalPath()).toAbsolutePath().normalize().toString());
        File f= new File(Paths.get(new File("../").getCanonicalPath()).toAbsolutePath().normalize().toString() + "/" + "DataStructuresPractice" + "/" + "src/Tree/Varun.java" );
        if (f.exists()){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
