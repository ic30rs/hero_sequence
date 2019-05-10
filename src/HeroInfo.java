import java.io.*;
import java.util.*;

public class HeroInfo {

    public static long [] enemyList = new long[]{20_0110L,45_0310L,80_0680L,125_1300L,180_1800L,245_2500L,320_3900L,405_5700L,
            500_8000L,661_6900L,842_6000L,1123_8000L,2000_0000,5000_0000,1_0000_0000,2_0000_0000};

    public static List<Hero> heroList;

    private static final String fileName= "hero.txt";
    //private static final String fileName="iceors_hero.txt";

    static{
        heroList = new ArrayList<>();
        readFile(fileName);
        Collections.sort(heroList);
        writeFile(fileName);
    }

    private static void readFile(String filename){
        File file = new File(filename);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while((line  = bufferedReader.readLine())!=null){
                String[] split = line.split("#");
                heroList.add(new Hero(split[0], Long.parseLong(split[1])));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeFile(String filename){
        File file = new File(filename);
        StringBuffer stringBuffer = new StringBuffer();
        for(Hero hero : heroList){
            stringBuffer.append(hero.name+"#"+hero.power+"\n");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(stringBuffer.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
