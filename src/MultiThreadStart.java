import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MultiThreadStart {


    public static void main(String args[]){
        if(args.length == 1){
            HeroInfo.fileName = args[0];
            HeroInfo.init();
        }
        for(int i=4; i<=11 ; i++){
            final int complex = i;
            new Thread(() -> {new FinalAlgorithm(complex).fightSequence();
            int a =0; }).start();
        }
    }


    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int length = 1;
        dp[p.charAt(0) - 'a'] = 1;
        for(int i = 1; i< p.length(); i++){
            int comp = p.charAt(i) - p.charAt(i - 1);
            if(comp == 0 || comp == 25){
                length++;
            }else{
                length = 1;
            }
            int index = p.charAt(i) - 'a';
            dp[index] = Math.max(dp[index],length);
        }
        int ans = 0;
        for(int i : dp){
            ans+=i;
        }
        return ans;
    }


}
