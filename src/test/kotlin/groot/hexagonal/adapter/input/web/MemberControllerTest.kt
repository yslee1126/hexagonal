package groot.hexagonal.adapter.input.web

import groot.hexagonal.application.port.input.MemberUseCase
import groot.hexagonal.domain.Member
import org.junit.jupiter.api.Test

import org.mockito.BDDMockito.given
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@WebMvcTest(MemberController::class)
class MemberControllerTest {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var memberUseCase: MemberUseCase

    @Test
    fun getMembers() {

        // Given
        val expectedMembers = listOf(
            Member(
                id = 1L,
                name = "홍길동",
                email = "hong@example.com"
            ),
            Member(
                id = 2L,
                name = "김철수",
                email = "kim@example.com"
            )
        )

        given(memberUseCase.getMembers(null, 10, 1)).willReturn(expectedMembers)

        // When & Then
        val result = mockMvc.perform(get("/members"))
            .andDo(print()) // 응답 내용을 콘솔에 출력
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.members.length()").value(expectedMembers.size)) // 반환된 멤버 수 확인
            .andExpect(jsonPath("$.members[0].id").value(expectedMembers[0].id)) // 첫 번째 멤버 ID 확인
            .andExpect(jsonPath("$.members[1].id").value(expectedMembers[1].id)) // 두 번째 멤버 ID 확인
            .andReturn()

        // 추가적인 검증이 필요하다면 아래와 같이 작성 가능
        val responseBody = result.response.contentAsString
        log.debug("response body {}", responseBody)
    }
}