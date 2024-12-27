package groot.hexagonal.application.service

import groot.hexagonal.application.port.out.MemberQueryPort
import groot.hexagonal.domain.Member
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest: BehaviorSpec ({

    val memberQueryPort = mockk<MemberQueryPort>()
    val memberService = MemberService(memberQueryPort)

    Given("a request to get members") {
        val name = "John"
        val size = 10
        val page = 1
        val expectedMembers = listOf(Member(1, "John Doe"), Member(2, "Jane Doe"))

        every { memberQueryPort.getMembers(name, size, page) } returns expectedMembers

        When("the service is called to get members") {
            val result = memberService.getMembers(name, size, page)

            Then("it should return the expected list of members") {
                result shouldBe expectedMembers
            }

            Then("it should call the memberQueryPort with correct parameters") {
                verify { memberQueryPort.getMembers(name, size, page) }
            }
        }
    }

})