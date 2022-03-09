
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