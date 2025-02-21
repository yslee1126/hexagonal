package groot.hexagonal.application.service

import groot.hexagonal.application.port.input.ArticleUseCase
import groot.hexagonal.application.port.output.ArticleApiPort
import groot.hexagonal.domain.Article
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleApiPort: ArticleApiPort): ArticleUseCase {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    override suspend fun getArticles() : List<Article> {
        return coroutineScope {
            val startTime = System.currentTimeMillis()
            log.debug("Starting parallel article fetching")

            val moArticlesDeferred = async {
                log.debug("Starting Mo articles fetch")
                val moStartTime = System.currentTimeMillis()
                articleApiPort.getMoArticles().also { articles ->
                    val duration = System.currentTimeMillis() - moStartTime
                    log.debug("Completed Mo articles fetch in {}ms, got {} articles", duration, articles.size)
                }
            }

            val liArticlesDeferred = async {
                log.debug("Starting Li articles fetch")
                val liStartTime = System.currentTimeMillis()
                articleApiPort.getLiArticles().also { articles ->
                    val duration = System.currentTimeMillis() - liStartTime
                    log.debug("Completed Li articles fetch in {}ms, got {} articles", duration, articles.size)
                }
            }

            val moArticles = moArticlesDeferred.await()
            val liArticles = liArticlesDeferred.await()

            val totalDuration = System.currentTimeMillis() - startTime
            log.debug("Completed all article fetching in {}ms, total articles: {}",
                totalDuration, moArticles.size + liArticles.size)

            moArticles + liArticles
        }
    }
}