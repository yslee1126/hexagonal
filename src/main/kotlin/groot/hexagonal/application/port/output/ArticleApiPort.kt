package groot.hexagonal.application.port.output

import groot.hexagonal.domain.Article

interface ArticleApiPort {
    suspend fun getMoArticles(): List<Article>
    suspend fun getLiArticles(): List<Article>
}