package com.trebol.auth.configuration;

import com.trebol.auth.adapters.driven.email.EmailAdapter;
import com.trebol.auth.adapters.driven.encode.EncoderAdapter;
import com.trebol.auth.adapters.driven.jpa.mysql.adapter.EmployeeAdapter;
import com.trebol.auth.adapters.driven.jpa.mysql.mapper.IEmployeeEntityMapper;
import com.trebol.auth.adapters.driven.jpa.mysql.repository.IEmployeeRepository;
import com.trebol.auth.adapters.driven.jwt.AuthenticationAdapter;
import com.trebol.auth.adapters.driven.jwt.JwtAdapter;
import com.trebol.auth.adapters.driven.nosql.redis.RecoverPasswordAdapter;
import com.trebol.auth.configuration.jwt.JwtService;
import com.trebol.auth.domain.api.IEmployeeServicePort;
import com.trebol.auth.domain.api.usecase.EmployeeUseCaseImpl;
import com.trebol.auth.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JavaMailSender mailSender;

    @Bean
    public IEmployeePersistencePort employeePersistencePort() {
        return new EmployeeAdapter(employeeRepository, employeeMapper);
    }

    @Bean
    public IEncoderPort encoderPort(){
        return new EncoderAdapter(passwordEncoder);
    }

    @Bean
    public IAuthenticationPort authenticationPort(){
        return new AuthenticationAdapter(authenticationManager);
    }

    @Bean
    public IJwtPort jwtPort(){
        return new JwtAdapter(jwtService);
    }

    @Bean
    public IEmailPort emailPort(){
        return new EmailAdapter(mailSender);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public IRecoveryPasswordPort recoveryPasswordPort(RedisTemplate<String, String> redisTemplate) {
        return new RecoverPasswordAdapter(redisTemplate);
    }

    @Bean
    public IEmployeeServicePort employeeServicePort(IRecoveryPasswordPort recoveryPasswordPort) {
        return new EmployeeUseCaseImpl(employeePersistencePort(), encoderPort(), authenticationPort(), jwtPort(), emailPort(), new SecureRandom(), recoveryPasswordPort);
    }


}
