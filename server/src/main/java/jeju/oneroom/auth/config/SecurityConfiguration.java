package jeju.oneroom.auth.config;

import jeju.oneroom.auth.handler.MemberAuthenticationEntryPoint;
import jeju.oneroom.auth.handler.OAuth2SuccessHandler;
import jeju.oneroom.auth.handler.UserAccessDeniedHandler;
import jeju.oneroom.auth.service.JwtTokenizer;
import jeju.oneroom.auth.utils.CustomAuthorityUtils;
import jeju.oneroom.user.repository.UserRepository;
import jeju.oneroom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final UserService userService;
    private final UserRepository userRepository;

    public class SecurityConfigurationV2 {
        private final JwtTokenizer jwtTokenizer;
        private final CustomAuthorityUtils authorityUtils;
        private final UserService userService;

        public SecurityConfigurationV2(JwtTokenizer jwtTokenizer,
                                       CustomAuthorityUtils authorityUtils,
                                       UserService userService) {
            this.jwtTokenizer = jwtTokenizer;
            this.authorityUtils = authorityUtils;
            this.userService = userService;
        }


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .headers().frameOptions().sameOrigin()
                    .and()
                    .csrf().disable()
                    .cors(withDefaults())
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                    .accessDeniedHandler(new UserAccessDeniedHandler())
                    .and()
                    .apply(new CustomFilterConfigurer())
                    .and()
                    .authorizeHttpRequests(authorize -> authorize
//                            .antMatchers(HttpMethod.POST, "/reviews").hasRole("USER")
                            .anyRequest().permitAll()
                    )
                    .oauth2Login(oauth2 -> oauth2
                            .successHandler(new OAuth2SuccessHandler(jwtTokenizer, authorityUtils, userService))
                    );

            return http.build();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

        public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
            @Override
            public void configure(HttpSecurity builder) throws Exception {
                JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

                builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
            }
        }
    }
}