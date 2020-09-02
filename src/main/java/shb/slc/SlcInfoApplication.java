package shb.slc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SlcInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SlcInfoApplication.class, args);
    }
}
