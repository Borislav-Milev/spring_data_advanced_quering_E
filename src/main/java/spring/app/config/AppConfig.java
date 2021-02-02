package spring.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.app.util.contract.FileUtil;
import spring.app.util.FileUtilImpl;
import spring.app.util.contract.InputReader;
import spring.app.util.InputReaderImpl;

import java.io.*;

@Configuration
public class AppConfig {

    @Bean
    public FileUtil fileUtil(){
        return new FileUtilImpl();
    }

    @Bean
    public BufferedReader reader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public InputReader inputReader(){
        return new InputReaderImpl(this.reader());
    }
}
