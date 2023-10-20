package api;

import org.springframework.aot.hint.TypeHint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.annotation.JsonProperty;


// demoing using springboot
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        String url = "https://henrytchen.com/custom-api/signUp";

        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec= client.get().uri(uriBuilder -> uriBuilder.path(url)
                .queryParam("username", "{title}")
                        .queryParam("password", "{selection}")
                .build("Johnny", "appleseed"))
        .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();

        System.out.println(responseBody);
    }
}