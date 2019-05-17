import java.util.*;

class Solution {
    /**
     * 332题
     * @param tickets
     * @return
     */
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, LinkedList<String>> map = new HashMap<>();
        for(String[] trip : tickets){
            String from = trip[0];
            String to = trip[1];
            LinkedList<String> list = map.get(from);
            if(list == null){
                list = new LinkedList<>();
                map.put(from,list);
            }
            list.add(to);
        }
        for(LinkedList list : map.values()){
            Collections.sort(list);
        }
        LinkedList<String> ans = new LinkedList<>();
        ans.addLast("JFK");
        helper(map,ans,tickets.length+1);

        return ans;
    }

    private void helper(HashMap<String, LinkedList<String>> map, LinkedList<String> ans , int totalLen){
        LinkedList<String> prevQueue = map.get(ans.getLast());
        if(prevQueue == null) return;
        for(int i=0; i<prevQueue.size(); i++){
            String to = prevQueue.removeFirst();
            System.out.println(""+to+" "+i);
            ans.addLast(to);
            helper(map, ans, totalLen);
            if(ans.size() == totalLen) return;
            ans.removeLast();
            prevQueue.addLast(to);
        }
    }

    /**
     * 334题
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int big = small;
        for(int n : nums){
            if(n <= small){
                small = n;
            }
            if(n > small && n<=big){
                big = n;
            }
            if(n > small && n > big){
                return true;
            }
        }
        return false;
    }

    /**
     * 337题
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] robResult = helper(root);
        return(Math.max(robResult[0],robResult[1]));
    }

    public int[] helper(TreeNode root){
        if(root == null) return new int[]{0,0};

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int[] ret = new int[2];
        ret[0] = root.val + left[1] + right[1];
        ret[1] = Math.max(left[0], left[1]) + Math.max(right[0],right[1]);
        return  ret;
    }




    /**
     * 347题
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        List[] lists = new List[nums.length + 1];
        for(int i : nums){
            map.put(i, map.getOrDefault(1, 0)+1);
        }
        for(int key : map.keySet()){
            int freq = map.get(key);
            if(lists[freq] == null){
                lists[freq] = new ArrayList<Integer>();
            }
            lists[freq].add(key);
        }
        List<Integer> ret = new ArrayList<>();
        for(int i = lists.length-1; i>=0 && ret.size()<k; i++){
            if(lists[i] != null){
                ret.addAll(lists[i]);
            }
        }
        return  ret;

    }

    /**
     * 349题
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for(int i:nums1){
            set.add(i);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for(int i: nums2){
            if(set.contains(i)){
                set2.add(i);
            }
        }
        int[] ret = new int[set2.size()];
        int index = 0;
        for(int i : set2){
            ret[index] = i;
            index++;
        }
        return  ret;

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i:nums1){
            int count = map.getOrDefault(i,0);
            map.put(i,++count);
        }
        HashMap<Integer,Integer> map2 = new HashMap<>();
        for(int i: nums2){
            int count = map2.getOrDefault(i,0);
            map2.put(i,++count);
        }
        List<Integer> tempList = new ArrayList<>();
        for(int i : map2.keySet()){
            if(!map.containsKey(i))continue;
            int j = Math.min(map2.get(i), map.get(i));
            for(int index = 0; index < j; index++){
                tempList.add(i);
            }
        }
        int[] ret = new int[tempList.size()];
        int index = 0;
        for(int i : tempList){
            ret[index] = i;
            index++;
        }
        return  ret;
    }

    /**
     * 367题
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int lo = 0;
        int hi = num;
        while(lo < hi){
            long mid = lo+(hi-lo)/2;
            if(mid * mid == num){
                return true;
            }else if(mid * mid >num){
                hi = (int)mid - 1;
            }else{
                lo = (int)mid + 1;
            }
        }
        return false;
    }

    /**
     * 368题
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        for(int i : nums){
            boolean outFlag = false;
            for(List<Integer> list : results){
                boolean flag = true;
                for(int integer : list){
                    if(i % integer != 0){
                        flag = false;
                    }
                }
                if(flag){
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(i);
                    results.add(newList);
                    outFlag = true;
                }
            }
            if(!outFlag){
                List<Integer> mList = new ArrayList<>();
                mList.add(i);
                results.add(mList);
            }
        }
        List ret = results.get(0);
        for(List list : results){
            if(list.size() > ret.size()){
                ret = list;
            }
        }
        return ret;
    }

    /**
     * 91
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if(s.length()==0) return 0;
        int memo[] = new int[s.length()+1];
        memo[s.length()] = 1;
        if(s.charAt(s.length() - 1) == '0'){
            memo[s.length() - 1] = 0;
        }else{
            memo[s.length() - 1] = 1;
        }
        for(int i=s.length()-2; i>=0; i-- ){
            int twoDigits = Integer.parseInt(s.substring(i,i+2));
            if(twoDigits>=10 && twoDigits<=26){
                memo[i] = memo[i+1] + memo[i+2];
            }else{
                memo[i] = memo[i+1];
            }
        }

        return memo[0];
    }

    /**
     * 634
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return helper(price,special,needs,new HashMap<>());
    }

    private int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, HashMap<List<Integer>, Integer> map){
        if(map.containsKey(needs)){
            return map.get(needs);
        }
        int res = 0;
        for(int i = 0; i<needs.size(); i++){
            res += needs.get(i) * price.get(i);
        }
        for(List<Integer> offer : special){
            List<Integer> nextNeed = new ArrayList<>(needs);
            boolean flag = true;
            for(int i=0; i<needs.size(); i++){
                int remain = needs.get(i) - offer.get(i);
                if(remain < 0){
                    flag = false;
                    break;
                }
                nextNeed.set(i,remain);
            }
            if(flag){
                res = Math.min(res, offer.get(offer.size()-1)+helper(price, special, nextNeed, map));
            }
        }
        map.put(needs,res);
        return res;

    }

    /**
     * 872
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Queue<Integer> queue = new LinkedList<>();
        getSequence(root1,queue);
        boolean b = pollQueue(root2,queue);
        return queue.isEmpty() && b;
    }

    private void getSequence(TreeNode t, Queue<Integer> queue){
        if(t == null) return;
        if(t.left == null && t.right == null){
            queue.offer(t.val);
        }
        getSequence(t.left,queue);
        getSequence(t.right,queue);
    }

    private boolean pollQueue(TreeNode t, Queue<Integer>queue){
        if(t==null) return true;
        if(t.left == null && t.right == null){
            if(!queue.isEmpty() && t.val == queue.poll()){
                return true;
            }else{
                return false;
            }
        }
        return pollQueue(t.left,queue) && pollQueue(t.right,queue);
    }


    /**
     * 590
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        helper(root,list);
        return  list;
    }

    private void helper(Node node, List<Integer> list){
        for(Node n : node.children){
            helper(node,list);
        }
        list.add(node.val);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 567
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        if(s1.length() == 0) return true;
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for(int i=0; i<s1.length(); i++){
            map1[s1.charAt(i) - 'a'] ++;
            map2[s2.charAt(i) - 'a'] ++;
        }
        int same = 0;
        for(int i=0; i<26; i++){
            if(map1[i] == map2[i]){
                same++;
            }
        }
        if(same == 26){
            return true;
        }
        for(int i = s1.length(); i<s2.length(); i++){
            int oldChar = s2.charAt(i - s1.length()) - 'a';
            int newChar = s2.charAt(i) - 'a';
            //System.out.println(same);
            map2[oldChar]--;
            if(map2[oldChar] == map1[oldChar]){
                same ++;
            }else if(map2[oldChar] +1 == map1[oldChar]){
                same --;
            }
            //System.out.println(same);
            map2[newChar]++;
            if(map2[newChar]== map1[newChar]){
                same ++;
            }else if(map2[newChar] -1 == map1[newChar]){
                same --;
            }
            //System.out.println(same);

            if(same == 26){
                return true;
            }
        }

        return false;
    }


    /**
     * p1031
     * @param A
     * @param L
     * @param M
     * @return
     */
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] Ls = new int[A.length - L + 1];
        int[] Ms = new int[A.length - M + 1];
        genSun(A, L, Ls);
        genSun(A, M, Ms);
        for(int i : Ms){
            System.out.println(i);
        }
        int max = 0;
        for(int i=0; i<A.length; i++){
            if(i >= Ls.length) break;
            for(int j =0; j<A.length; j++){
                if(j >= Ms.length || (j+M-1<i || j>i+L-1))
                    continue;
                max = Math.max(max, Ls[i] + Ms[j]);
            }
        }
        return max;
    }

    private void genSun(int[] A, int M, int[] ms) {
        int window = 0;
        for(int i=0; i<M; i++){
            window += A[i];
        }
        ms[0] = window;
        for(int i = 1; i <= A.length-M; i++){
            window -= A[i-1];
            window += A[i+M-1];
            ms[i] = window;
        }
    }

    public boolean hasGroupsSizeX(int[] deck) {
        int count[] = new int[10001];
        for(int i : deck){
            count[i]++;
        }
        int res = 0;
        for(int i: count){
            res = gcd(i, res);
        }
        return  res >= 2;
    }

    public int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }


    public int removeElement(int[] nums, int val) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi){
           if(nums[lo] == val){
               nums[lo] = nums[hi];
               hi --;
           }else {
               lo ++;
           }
        }
        return hi + 1;
    }

    public int findLengthOfLCIS(int[] nums) {
        int ans = 0;
        int temp = 0;
        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i]>nums[i-1]){
                ans = Math.max(ans, ++temp);
            }else{
                temp = 1;
            }
        }
        return ans;
    }

    public String countAndSay(int n) {
        String[] dp = new String[n+1];
        dp[1] = "1";
        for(int i = 2; i<=n; i++){
            String curr = dp[i-1];
            StringBuffer sb = new StringBuffer(2*curr.length());
            int count = 0;
            char currChar = curr.charAt(0);
            for(int j=0; j<curr.length(); j++){
                if(j == 0 || curr.charAt(j) == curr.charAt(j-1)){
                    count ++;
                }else{
                    sb.append(count);
                    sb.append(currChar);
                    currChar = curr.charAt(j);
                    count = 1;
                }
            }
            dp[i] = sb.toString();
        }
        return dp[n];
    }

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a,b)->(a[0]-a[1])-(b[0]-b[1]));
        int ans = 0;
        for(int i=0; i<costs.length; i++){
            if(i<costs.length/2) ans += costs[i][0];
            else ans += costs[i][1];
        }
        return ans;
    }

}