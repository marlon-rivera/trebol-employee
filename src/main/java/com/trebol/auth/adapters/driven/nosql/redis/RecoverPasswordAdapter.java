package com.trebol.auth.adapters.driven.nosql.redis;

import com.trebol.auth.domain.spi.IRecoveryPasswordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class RecoverPasswordAdapter implements IRecoveryPasswordPort {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String BASE_KEY = "RECOVERY_CODE_";
    private final Random random = new Random();

    @Override
    public String generateRecoverCode(String email) {
        String code = generateCode();
        String key = BASE_KEY + email;
        int expirationTimeMinutes = 10;
        redisTemplate.opsForValue().set(key, code, expirationTimeMinutes, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public boolean validateRecoverCode(String email, String recoveryCode) {
        String key = BASE_KEY + email;
        String code = redisTemplate.opsForValue().get(key);
        if(code == null) {
            return false;
        }else return recoveryCode.equals(code);
    }

    private String generateCode(){
        return String.valueOf(100000 + random.nextInt(900000));
    }
}
