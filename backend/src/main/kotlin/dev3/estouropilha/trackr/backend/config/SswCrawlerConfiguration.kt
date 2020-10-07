package dev3.estouropilha.trackr.backend.config

import dev3.estouropilha.trackr.backend.crawlers.ssw.SswCrawlerDocumento
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SswCrawlerConfiguration {
    @Bean
    fun ssw(): SswCrawlerDocumento = SswCrawlerDocumento()
}