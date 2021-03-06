
合并数组
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //方法一: 用已有方法合并后排序，但时间复杂度较高
        // System.arraycopy(nums2, 0, nums1, m, n);   //(src, srcPos, dest, destPos, length)
        // Arrays.sort(nums1);

        //方法二: 双指针
        // int len1 = m-1;
        // int len2 = n-1;
        // int len = m+n-1;
        // while(len1>=0 && len2>=0){
        //     nums1[len--] = nu	ms1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        //     //如果nums1>nums2，nums就赋值为nums1的值，且位置--
        // }
        // System.arraycopy(nums2, 0, nums1, 0, len2 + 1);

        //方法三: 双指针, 但是不用System.copy
        int i = m-1, j = n-1;
        int index = m+n-1;
        while(j >= 0){    //为啥是j大于等于0呢?
            if(i < 0){
                nums1[index--] = nums2[j--];
            }else if(j < 0){
                nums1[index--] = nums2[i--];
            }else if(nums1[i] > nums2[j]){
                nums1[index--] = nums1[i--];
            }else {
                nums1[index--] = nums2[j--];
            }
        }
    }
数组转平衡搜索树
	/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        
       return result(nums,0,nums.length-1);

    }
    private TreeNode result(int[] nums, int lo, int hi)
    {
        if(lo>hi)
        {
            return null;
        }
        int mid = (hi-lo)/2 + lo;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = result(nums,lo,mid-1);
        root.right = result(nums,mid+1,hi);
        return root;
    }
}



动态规划-杨辉三角i
 public static List<List<Integer>> generate(int numsRaw)
    {
		//初始化一个包含list类型的list类型
        List<List<Integer>> result = new ArrayList<>();
		//创建二维数组
        int[][] dp = new int[numsRaw][numsRaw];
		//第i行第一列永远是1，第i行最后一个永远是1
        dp[0][0] = 1;
		//添加到返回list中
        result.add(new ArrayList<Integer>(){{add(dp[0][0]);}});
		//判断行数为1直接返回结果
        if(numsRaw == 1)
        {
            return result;
        }
		//开始计算
        for(int i = 1; i<numsRaw;i++)
        {	//创建一个sublist用来储存每行结果
            List<Integer> nums = new ArrayList<>();
            dp[i][0] = 1;
            nums.add(dp[i][0]);
			//计算每行每列对应的数，j的数量等于行数减去开头结尾
            for(int j = 1; j<i;j++)
            {	
				//在i行i列等于i-1行的第j个数加上i-1行的第j-1个数
                dp[i][j] = dp[i-1][j]+dp[i-1][j-1];
                nums.add(dp[i][j]);
            }
			//每行结尾
            dp[i][i] = 1;
            nums.add(dp[i][i]);
            result.add(nums);
        }
        return result;
    }、
    //杨辉三角II 动态规划思维解法
     public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        int count = rowIndex+1;
        int[][] dp = new int[count][count];
        dp[0][0]=1;
        if(rowIndex == 0)
        {
            res.add(1);
            return res;
        }
        for(int i =1;i<count;i++)
        {
            dp[i][0]=1;
            dp[i][i]=1;
            for(int j =1;j<i;j++)
            {
                dp[i][j] = dp[i-1][j]+dp[i-1][j-1];
            }
        }
        for(int i =0;i<=rowIndex;i++)
        {
          res.add(dp[rowIndex][i]);
        }
        return res;
    }
//杨辉三角 特殊解法 （不懂）
    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for(int i = 1; i <= rowIndex; i++){
            long nums = (long)row.get(i-1);
            long nums2 = (rowIndex-i+1)/i;
            int nums3 = (int) (nums2*nums);
            row.add((int)((long)row.get(i - 1) * (rowIndex - i + 1) / i));


        }
        return row;
	
//动态规划 低买高卖
class Solution {
    public int maxProfit(int[] prices) {
	//定义数组长度
        int len = prices.length;
	//如果数组长度小于2，则无利润
        if(len<2)
        {
            return 0;
        }
	//定义dp数组记录状态
        int[] dp= new int[2];
	//0表示未持有股
        dp[0]=0;
	//1表示持股,此处是初始化各状态
        dp[1]=-prices[0];
	//从第二天开始遍历
        for(int i = 1;i<len;i++)
        {   //总利润=负利润+当前股价 
	    /*
	    持续更新未持有股票股票收益状态，用当前未持有股票的现金和持股负利润加上目前股价的总利润
	    假如前一天卖出的股票加上近日的股票价格大于前天未持有股票的价格，则更新未持有股票的现金状态为目前总利润的。（约等于卖出）
	    */
            dp[0] = Math.max(dp[0],dp[1]+prices[i]);
	    /*
	    持续更新持有股票收益状态，对比当前的股价和入股时候的股价， 此时现金是负利润，假如入股时候的股价负利润比当前股价负利润更
	    则更新持股状态为当前股价的负利润（也就是更新为在这天买入）。反之亦然。
	    */
            dp[1] = Math.max(dp[1],-prices[i]);
        }
        return dp[0];
	
    }
}
//股票低买高卖，速通写法
class Solution2 {
    public int maxProfit(int[] prices) {
    //t是隔天收益，x今日价格，x1是昨日价格，max是最大利润
   int i,t=0,x,x1=0,max=0;
        for(i=0;i<prices.length;i++)
        {
	    //从第一天开始遍历股价
            x=prices[i];
	    //假如不是第一天，从第二天该是算收益， t的隔天收益累加今日股价减去昨日股价
            if(i!=0)t+=x-x1;
	    //假如隔天股价差价小于零，则隔日收益为0
            if(t<0)t=0;
	    //和当前最大利润对比，假如隔日差价大于当前最大连，则更新目前最大利润为隔日差价
            if(t>max)max=t;
	    //更新昨日股价
            x1=x;
        }
        return max;
    }
}
//哈希表求最多或者找单个
class Solution {
    public int majorityElement(int[] nums) {
    	//初始化表
        Map<Integer,Integer> mymap = new HashMap<>();
        int r = nums.length/2;
	//遍历数组，在表中寻找数组对应的数字为key的键值对
        for(Integer i:nums)
        {
	    //一个计数，来查找对应key键值对的出现次数
            Integer count = mymap.get(i);
	    //count为null为没出现过，设置值为1，出现过则计数自加
            count = count ==null?1:++count;
	    //把键值对放入表中或者更新值
            mymap.put(i,count);

        }
	//遍历表中的键值对
        for(Integer i : mymap.keySet())
        {
	    //用count来记录每个键对应的count值
            Integer count = mymap.get(i);
	    //如果此计数大于或者小于（根据题目定），则返回此键
            if(count>r)
            {
                return i;
            }
        }
        return -1;

    }
}
//求两个数组交集（双指针）
 public int[] intersect(int[] nums1, int[] nums2) {
 	//临界条件判定
        if(nums1.length==0||nums2.length==0)
        {
            return null;
        }
	//给数组排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
	//定义printer
        int i =0;
        int j =0;
        int k =0;
	//定义一个新的返回数组
        int[] res = new int[nums1.length];
	//当左指针的大小小于数组1的长度与右指针小于数组2的长度
        while(i<nums1.length&&j<nums2.length)
        {	
	    //对各个情况进行判断
	    //指针对比，数组一的指针小于数组2的指针
            if(nums1[i]<nums2[j])
            {
	    	//左指针+1再进行对比
                i++;
            }
            else if(nums1[i]>nums2[j])
            {
                j++;
            }else
            {
                res[k++]=nums1[i];
                i++;
                j++;
            }
        }
	//将数组重新改为里面重合数字的长度的大小
        return Arrays.copyOfRange(res,0,k);
    }
    /*
    plus one解法
    */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int[] res = new int[len+1];
        if(digits[len-1]!=9)
        {
            digits[len-1]++;
            return digits;
        }
        for(int i = len-1;i>=0;i--)
        {
            if(digits[i]==9)
            {
                digits[i]=0;
            }
            else
            {
                digits[i]++;
                return digits;
            }
        
        }
        res[0]=1;
        return res;

    }
    /*
    移动0解法
    */
    public void moveZeroes(int[] nums) {
    	
        int len = nums.length;
	//定义一个index来记录非0数字所占到的索引
        int index = 0;
	//遍历
        for(int i =0;i<nums.length;i++)
        {	
	    //如果遍历到数字是非0数字
            if(nums[i]!=0)
	    {
	    	//则索引增加，且索引代表的位置等于此遍历到的数字
                nums[index++]=nums[i];
                
            }

        }
	//补齐9
        while(index<len)
        {
            nums[index++]=0;
        }

    }
    /*
    两数之和 （运用哈希表）
    */
     public int[] twoSum(int[] nums, int target) {
     
        Map<Integer,Integer> mymap = new HashMap<>();
	//遍历数组
        for(int i =0;i<nums.length;i++)
        {
	    //哈希表取目标数字减去当前数字的的值的键值对的值，如果是非空，直接返回新数组
            if(mymap.get(target-nums[i])!=null)
            {
	    	//返回数组，第一个值是当前目标值的键值，也就是目标数字的索引，后面的值是当前索引
                return new int[]{mymap.get(target-nums[i]),i};
            }
	    //添加到mymap里面
            mymap.put(nums[i],i);
        }
        return new int[]{0,0};
	
    }
    //翻转字符串（双指针）
        public void reverseString(char[] s) {
        int i = 0;
        int j = s.length-1;
        while(i<j)
        {
            swap(s,i++,j--);
        }    
    }
    public void swap(char[] s,int left,int right)
    {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
    //整数翻转哦
      public int reverse(int x) {
      	//定义一个res来储存每次循环的结果
        int res =0;
	//当x不为时，循环继续
        while(x!=0)
        {
	    //定义一个temp变量来储存每次x对10求余的值（对10求余表示得到得到每次循环x最后一位的数值）
            int temp = x%10;
	    //定义一个新res来储存目前的res x 10之后加上余数
            int newres = res*10+temp;
	    //防止数字溢出
            if((newres-temp)/10 != res)
            {
                return 0;
            }
	    //替换现有的res为new res
            res=newres;、
	    //x求商
            x /= 10;
        }
        return res;
    }
    //找到唯一的字符(哈希表）
    public int firstUniqChar(String s) {
    	//先定义一个哈希表
        Map<Character,Integer> mymap = new HashMap<>();
	//把字符串转换为字符数组（不转也行，可以使用s.charAt();）
        char[] chars = s.toCharArray();
	//遍历添加每个字符到map集合并在值记录期出现的次数
        for(char c:chars)
        {
            mymap.put(c,mymap.getOrDefault(c,0)+1);
        }
	//遍历，次数等于1的字符输出起index
       for(int i =0;i<s.length();i++)
       {
           if(mymap.get(chars[i])==1)
           {
               return i;
           }
       }
      
       return -1;
    }
    //判断异位字符（用ascii码判断）
    public boolean isAnagram(String s, String t) {
    	//新建两个数组来储存两个字符各自出现的ascii码
        int count1[] = new int[26];
        int count2[] = new int[26];
	//循环第一个字符串，把出现的字母的ascii码记录在数组1中，第二个字符串同理
        for(int i = 0; i<s.length();i++)
        {
	    //这里是用遍历到的字符的ascii码来减去'a'的ascii码来得到其他的ascii码,当对应的ascii码出现式，对应数组的index对应的值加1即可做到记录出现次数
            count1[s.charAt(i)-'a']++;
        }
        for(int i=0; i<t.length();i++)
        {
            count2[t.charAt(i)-'a']++;
        }
	//
        if(Arrays.equals(count1,count2))
        {
            return true;
        }
        return false;

