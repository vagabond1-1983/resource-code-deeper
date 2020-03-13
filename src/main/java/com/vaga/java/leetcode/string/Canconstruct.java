package com.vaga.java.leetcode.string;

import com.vaga.java.leetcode.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/13 4:34 下午
 * @description 383. 赎金信
 */
public class Canconstruct extends BaseTest {

    @Override
    protected Object[][] createData() {
        return new Object[][]{
                {"aa", "ab", false},
                {"aa","aab",true},
                {"ab","aab",true},
                {"fffbgf","effjfggbffjdgbjjhhdegh",true},
                {"fihjjjjei","hjibagacbhadfaefdjaeaebgi",false},
        };
    }

    @Test(dataProvider = "data")
    public void test(String ransomNote, String magazine, boolean expected) {
        assertThat(canConstruct(ransomNote, magazine)).isEqualTo(expected);

//        assertThat(leetcodeSolution(ransomNote, magazine)).isEqualTo(expected);
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (null == ransomNote || ransomNote.equals("")) return true;
        if (null == magazine || magazine.equals("")) return false;

        int rlen = ransomNote.length();
        int mlen = magazine.length();
        if (rlen > mlen) return false;
        int startIndex = 0;
        for (char i : ransomNote.toCharArray()) {
            int index = magazine.indexOf(i, startIndex);
            if (index == -1) {
                return false;
            }
            startIndex = index;
        }
        return true;
    }

    public boolean leetcodeSolution(String ransom, String magazine) {
        if (magazine.length() < ransom.length()) return false;
        int caps[] = new int[26];
        for (char c : ransom.toCharArray()) {
            int index = magazine.indexOf(c, caps[c - 'a']);
            if (index == -1)
                return false;
            caps[c - 97] = index + 1;
        }
        return true;
    }
}
