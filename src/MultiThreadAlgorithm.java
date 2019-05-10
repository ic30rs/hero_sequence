//import java.util.*;
//
//public class MultiThreadAlgorithm {
//
//    private static List<Hero> seq;
//    private static int seqWave = 0;
//    private static int waveCount = 0;
//    private static long finalRemain = Long.MAX_VALUE;
//    private static int solCount = 0;
//    public static int complex = 4;
//
//
//    public static void main(String[] args) {
//        if(args.length == 1){
//            complex = Integer.parseInt(args[0]);
//        }
//        if(args.length == 1){
//            complex = Integer.parseInt(args[0]);
//        }
//        System.out.println("计算中...");
//        fightSequence();
//    }
//
//
//
//
//    private static void fightSequence(){
//        Stack<Long> enemyStack = initEnemies();
//
//        for(int i = HeroInfo.enemyList.length - 1; i>=0; i--){
//            enemyStack.push(HeroInfo.enemyList[i]);
//        }
//
//        List<Hero> heroList = new LinkedList<>(HeroInfo.heroList);
//
//
//        List<Hero> ans = new LinkedList<>();
//        waveCount = enemyStack.size();
//        long t1 = System.currentTimeMillis();
//        dfs( enemyStack, heroList,ans);
//        long use = System.currentTimeMillis() - t1;
//        System.out.println("---------------"+"\n总用时 "+(use)+"毫秒");
//        long totalPow = 0;
//        for(Hero hero : HeroInfo.heroList){
//            totalPow += hero.power;
//        }
//        System.out.println("门客总战斗力"+totalPow/10000+"万");
//        System.out.println("---------------");
//        printFight();
//
//    }
//
//
//
//    private static void dfs(Stack<Long> enemyStack, List<Hero> heroList, List<Hero> ans){
//        if(heroList.isEmpty() || enemyStack.isEmpty()){
//            long mRemain = 0;
//            if(seqWave < waveCount - enemyStack.size()){
//                finalRemain = Long.MAX_VALUE;
//            }
//            if(!enemyStack.isEmpty()){
//                mRemain = Math.min(enemyStack.peek(),finalRemain);
//            }
//            if(waveCount - enemyStack.size() >= seqWave && mRemain < finalRemain){
//                finalRemain = mRemain;
//                solCount++;
//                seqWave = waveCount - enemyStack.size();
//                seq = new ArrayList<>(ans);
//                //printAns(ans);
//            }
//            return;
//        }
//        long currEnemy = enemyStack.pop();
//        long currDmg = 0;
//        List<Hero> candidateList = new LinkedList<>();
//        boolean flag = false;
//        for(Hero hero : heroList){
//            currDmg += hero.power;
//            candidateList.add(hero);
//            if(currDmg>= currEnemy){
//                flag = true;
//            }
//            if(hero.power >= currEnemy){
//                break;
//            }
//        }
//        if(flag){
//            PriorityQueue<PossibleSolution> possibles = new PriorityQueue<>();
//            dfsInternal(candidateList,new ArrayList<>(),possibles,0,currEnemy);
//            //System.out.println("------");
//            for(int index = 0; index < complex; index ++){
//                //System.out.println("大小"+possibles.size());
//                if(!possibles.isEmpty()){
//                    List<Hero> possibleHeros = possibles.poll().heros;
//                    List<Hero> tempHeroList = new LinkedList<>(heroList);
//                    Stack<Long> tempStack = (Stack<Long>) enemyStack.clone();
//
//                    int ansSize = ans.size();
//                    tempHeroList.removeAll(possibleHeros);
//                    ans.addAll(possibleHeros);
//
//                    dfs(tempStack,tempHeroList,ans);
//
//                    int newAnsSize = ans.size();
//                    for(int i=0; i<newAnsSize - ansSize; i++){
//                        ans.remove(ans.size() - 1);
//                    }
//                }
//            }
//        }else{
//            heroList.clear();
//            ans.addAll(candidateList);
//            enemyStack.push(currEnemy - currDmg);
//            dfs(enemyStack,heroList,ans);
//            ans.removeAll(candidateList);
//        }
//    }
//
//
//
//    private static void dfsInternal( List<Hero> heroList, List<Hero> useList, PriorityQueue<PossibleSolution> possibleList, int start,  long targetDmg){
//        int minUse = 0;
//        for(Hero hero : useList){
//            minUse+=hero.power;
//        }
//        if(minUse >= targetDmg && (useList.size() == 1 || useList.size() > 1 && useList.get(useList.size()-1).power < targetDmg)){
//
//            possibleList.add(new PossibleSolution(minUse, new ArrayList<>(useList)));
//
//        }
//        for(int i=start; i<heroList.size(); i++){
//            useList.add(heroList.get(i));
//            dfsInternal(heroList, useList,possibleList, i+1,targetDmg);
//            useList.remove(useList.size()-1);
//        }
//    }
//
//    private static Stack<Long> initEnemies(){
//        Stack<Long> enemyStack = new Stack<>();
//        for(int i = HeroInfo.enemyList.length - 1; i>=0; i--){
//            enemyStack.push(HeroInfo.enemyList[i]);
//        }
//        return enemyStack;
//    }
//
//    static void printAns(List<Hero> ans){
//        System.out.println("------------------"+solCount+"-----------------");
//        System.out.println("可到"+seqWave+"波");
//        System.out.println("剩余"+finalRemain+"血");
//        for(Hero hero : ans){
//            System.out.println(hero.name);
//        }
//        System.out.println("------------------------------------");
//    }
//
//    private static void printFight(){
//        Stack<Long> enemies = initEnemies();
//        int currWave = 0;
//        for(Hero hero : seq){
//            if(!enemies.isEmpty()){
//                long currEnemy = enemies.pop();
//                currEnemy -= hero.power;
//                System.out.print(String.format("门客   %-6s\t攻击 boss %-2d",hero.name,(currWave+1)));
//                if(currEnemy >0){
//                    enemies.push(currEnemy);
//                    System.out.println(String.format(" 还剩 %10d 血",currEnemy ));
//                }else{
//                    System.out.println(" 打死了");
//                    currWave++;
//                }
//            }else{
//                System.out.println("怪已经被打光了");
//            }
//        }
//    }
//}
//
//
//
//
