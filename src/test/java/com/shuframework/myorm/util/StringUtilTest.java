package com.shuframework.myorm.util;

import org.junit.Test;

/**
 * Created by shu on 2018/10/10.
 */
public class StringUtilTest {

    @Test
    public void test2() {
//        String str = "abc_Abc"; //abcAbc
        String str = "abcAbc"; //abcAbc
        String s1 = StringUtil.toPropertyName(str);
        System.out.println(s1);
    }

    @Test
    public void test1() {
        String str = "abc_Abc";
        String s1 = StringUtil.capitalizeFully(str, ",");
        String s2 = StringUtil.capitalizeFully(str);
        System.out.println(s1);//Abc_abc
        System.out.println(s2);//AbcAbc
    }
}
