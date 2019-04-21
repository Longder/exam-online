package com.longder.exam;

import com.longder.exam.util.EncryptionUtil;
import org.junit.Test;

public class EncryptionUtilTest extends BaseTest{

    @Test
    public void testEncrypt(){
        String result =EncryptionUtil.encrypt("1234");
        System.out.println(result);
    }
}
