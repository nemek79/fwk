package es.vir2al.fwk.fwk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class FwkPasswordUtils {
    
    private Logger logger = LoggerFactory.getLogger(FwkPasswordUtils.class);

    public static String encodePassword(String passwordIn) {

        org.springframework.security.crypto.password.PasswordEncoder encoder
        = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

        return encoder.encode(passwordIn);

    }

}
