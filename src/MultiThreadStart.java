import java.util.ArrayList;
import java.util.List;

public class MultiThreadStart {


    public static void main(String args[]){
        for(int i=4; i<=11 ; i++){
            final int complex = i;
            new Thread(() -> {new FinalAlgorithm(complex).fightSequence();
            int a =0; }).start();
        }
        List l =new ArrayList();
        l.forEach(System.out::println);
    }

}
