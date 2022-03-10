
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
    
