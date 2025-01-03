package groot.hexagonal.adapter.output.persistence

import groot.hexagonal.domain.Member
import org.junit.jupiter.api.Test

import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(properties = ["grpc.server.port=0"])
class MemberJpaAdapterTest {

    //unit 테스트를 위해
    @MockitoBean
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var memberJpaAdapter: MemberJpaAdapter

    @Test
    fun getMembers() {
        // Mock 동작 설정
        given(memberRepository.getByName("John", 10, 0)).willReturn(
            listOf(MemberDto(1, "John Doe"), MemberDto(2, "Jane Doe"))
        )

        // When
        val members = memberJpaAdapter.getMembers("John", 10, 0)

        // Then
        assert(members.size == 2)
        assert(members[0].name == "John Doe")

        // Verify: repository 호출 검증
        verify(memberRepository).getByName("John", 10, 0)
    }

    @Test
    fun createMember() {
        //Arrange
        val savedMember = MemberEntity(
            id = 1L,
            name = "John Doe",
            email = "john.doe@example.com"
        )

        // given
        given(memberRepository.save(any(MemberEntity::class.java))).willReturn(savedMember)

        // When
        val result = memberJpaAdapter.createMember(Member(name = "John Doe", email = "john.doe@example.com"))

        // Then
        assert(result.id == 1L)
        assert(result.name == "John Doe")
        assert(result.email == "john.doe@example.com")

        // Verify
        verify(memberRepository).save(any(MemberEntity::class.java))
    }
}