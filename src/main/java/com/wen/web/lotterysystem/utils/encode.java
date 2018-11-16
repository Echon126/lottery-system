package com.wen.web.lotterysystem.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author admin
 * @date 2018-11-16 14:37
 */
public class encode {
    public static void encodePassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String str = encoder.encode("pwd");
        System.out.println(str);
        boolean corrent = encoder.matches("123456","$2a$04$9SajUrq2nsm5YNJ37yHpFObTLvfOr3Mfxa2yQ62E0MHV/hVxuiN2e");
        boolean corrent1 = encoder.matches("$2a$04$aYBUE69Kft5Po78eyKZWx.6lHvmk7PZVVVXW09eSdF9M/Mo2kCew.","$2a$04$9SajUrq2nsm5YNJ37yHpFObTLvfOr3Mfxa2yQ62E0MHV/hVxuiN2e");
        System.out.println(corrent);
        System.out.println(corrent1);
    }
}
