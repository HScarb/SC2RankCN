package com.scarb.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Scarb on 9/29/2016.
 */
public class EncryptUtil {
    public static final String encryptMD5(String source) {
        if (source == null) {
            source = "";
        }
        Md5Hash md5 = new Md5Hash(source);
        return md5.toString();
    }
}