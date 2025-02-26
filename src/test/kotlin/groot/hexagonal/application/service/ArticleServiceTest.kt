package groot.hexagonal.application.service

import groot.hexagonal.application.port.output.ArticleApiPort
import groot.hexagonal.domain.Article
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class ArticleServiceTest: BehaviorSpec({

    val articleApiPort = mockk<ArticleApiPort>()
    val articleService = ArticleService(articleApiPort)

    Given("a request to get articles") {

        val moArticles = listOf(Article("title mo 1", "Mo Article 1"), Article("title mo 2", "Mo Article 2"))
        val liArticles = listOf(Article("title li 1", "Li Article 1"), Article("title li 2", "Li Article 2"))

        coEvery { articleApiPort.getMoArticles() } returns moArticles
        coEvery { articleApiPort.getLiArticles() } returns liArticles

        When("the service is called to get articles") {

            val result = articleService.getArticles()

            Then("it should return the expected list of articles") {

                result shouldBe moArticles + liArticles

            }
        }
    }

})