package dev3.estouropilha.trackr.backend.config

import dev3.estouropilha.trackr.backend.crawlers.CorreiosCrawler
import dev3.estouropilha.trackr.backend.crawlers.SswCrawler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SswCrawlerConfiguration {
    @Bean
    fun ssw() = SswCrawler()

    @Bean
    fun correios() = CorreiosCrawler()
}