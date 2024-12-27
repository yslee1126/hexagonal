package groot.hexagonal.application.service

import groot.hexagonal.application.port.out.MemberQueryPort
import groot.hexagonal.domain.Member
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
class MemberServiceJunitTest {

    @MockitoBean
    private lateinit var memberQueryPort: MemberQueryPort

    @Autowired
    private lateinit var memberService: MemberService

    @Test
    fun `getMembers should return list of members`() {
        // Given
        val name = "John"
        val size = 10
        val page = 1
        val expectedMembers = listOf(Member(1, "John Doe"), Member(2, "Jane Doe"))
        given(memberQueryPort.getMembers(name, size, page)).willReturn(expectedMembers)

        // When
        val result = memberService.getMembers(name, size, page)

        // Then
        assertEquals(expectedMembers, result)
        verify(memberQueryPort).getMembers(name, size, page)
    }

    @Test
    fun `createMember should return the created member`() {
        // Given
        val member = Member(1, "John Doe")
        given(memberQueryPort.createMember(member)).willReturn(member)

        // When
        val result = memberService.createMember(member)

        // Then
        assertEquals(member, result)
        verify(memberQueryPort).createMember(member)
    }
}