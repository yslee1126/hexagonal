package groot.hexagonal.adapter.input.web

import groot.hexagonal.application.port.input.ArticleUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleController(private val articleUseCase: ArticleUseCase) {

    @GetMapping("/articles")
    suspend fun getArticles() : ResponseEntity<ArticleListResponse> {
        val articles = articleUseCase.getArticles()
        val articleResponses = articles.map { article -> ArticleResponse(article.title, article.content) }
        return ResponseEntity.ok(ArticleListResponse(articleResponses))
    }

}