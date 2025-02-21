package groot.hexagonal.adapter.output.web.config

import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        val httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(30))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(30))
                    .addHandlerLast(WriteTimeoutHandler(30))
            }

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
    }

}