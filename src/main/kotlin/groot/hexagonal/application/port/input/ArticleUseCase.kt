package groot.hexagonal.application.port.input

import groot.hexagonal.domain.Article

interface ArticleUseCase {
    suspend fun getArticles() : List<Article>
}