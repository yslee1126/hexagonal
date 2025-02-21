package groot.hexagonal.adapter.output.web

import groot.hexagonal.application.port.output.ArticleApiPort
import groot.hexagonal.domain.Article
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class ArticleAdapter(private val webClient: WebClient): ArticleApiPort {
    override suspend fun getMoArticles(): List<Article> {
        val moUrl = ""

        return webClient.get()
            .uri(moUrl)
            .retrieve()
            .awaitBody<MoResponse>()
            ._DATA
            .map { moArticle ->
                Article(
                    title = moArticle.ART_TITLE,
                    content = moArticle.ART_SUMMARY
                )
            }
    }

    override suspend fun getLiArticles(): List<Article> {
        val liUrl = ""

        return webClient.get()
            .uri(liUrl)
            .retrieve()
            .awaitBody<LiResponse>()
            .data
            .map { liArticle ->
                Article(
                    title = liArticle.artTitle,
                    content = liArticle.artSummary
                )
            }
    }
}