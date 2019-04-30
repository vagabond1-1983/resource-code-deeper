# LeetCode练习
## [1.两数之和](array/TwoSum.java)
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
## [8.回文数字](array/palindromicNumber.java)
判断一个给定的数字是否是回文数字
e.g. 646 是
e.g. 456 否
## [14.最长公共前缀](string/LongestCommonPrefix.java)
编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。
示例 1:
输入: ["flower","flow","flight"]
输出: "fl"
示例 2:
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明: 所有输入只包含小写字母 a-z 。
## [242.字母异位](string/ValidAnagram.java)
判断一个字符串是否是字母异位
示例 1:
输入：s="anagram",t="nagaram"
输出：true
示例 2:
输入：s="rat",t="car"
输出：false
## [29.两数除法](binarySearch/DivideTwoInteger.java)
示例 1:
输入：dividend = 10, divisor = 3
输出：3
示例 2：
输入：dividend = 7, divisor = -3
输出：-2
## [27.删除元素](array/RemoveElement.java)
示例 1：
Given nums = [3,2,2,3], val = 3
return length = 2
3被移除，剩下2有2个
示例 2：
Given nums = [0,1,2,2,3,0,4,2], val = 2
return length = 5
2被移除，剩下[0,1,3,0,4]
## [20.有效的括号](stack/ValidParentheses.java)
示例 1:
输入: "()"
输出: true
示例 2:
输入: "()[]{}"
输出: true
示例 3:
输入: "(]"
输出: false
示例 4:
输入: "([)]"
输出: false
示例 5:
输入: "{[]}"
输出: true