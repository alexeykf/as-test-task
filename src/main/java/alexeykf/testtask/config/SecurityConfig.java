package alexeykf.testtask.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @SneakyThrows
    @Override
    protected void configure(HttpSecurity http) {
        super.configure(http);
        http.csrf().disable();
    }
}
