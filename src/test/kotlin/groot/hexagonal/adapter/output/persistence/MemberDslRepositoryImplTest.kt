package groot.hexagonal.adapter.output.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import groot.hexagonal.adapter.output.persistence.config.QueryDslConfig
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@DataJpaTest
@Import(QueryDslConfig::class)
@Transactional
class MemberDslRepositoryImplTest {

    @Autowired
    private lateinit var queryFactory: JPAQueryFactory

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var memberDslRepository: MemberDslRepository

    @BeforeEach
    fun setup() {
        memberDslRepository = MemberDslRepositoryImpl(queryFactory)

        // 테스트용 데이터 저장
        entityManager.persist(MemberEntity(name = "홍길동", email = "test1@ddd.com"))
        entityManager.persist(MemberEntity(name = "김철수", email = "test2@ddd.com"))
        entityManager.persist(MemberEntity(name = "이영희", email = "test3@ddd.com"))
        entityManager.flush()
    }

    @Test
    fun `특정 이름으로 멤버 조회`() {
        // Given
        val name = "홍길동"
        val size = 10
        val page = 1

        // When
        val results = memberDslRepository.getByName(name, size, page)

        // Then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo(name)
    }

    @Test
    fun `전체 멤버 조회`() {
        // Given
        val size = 10
        val page = 1

        // When
        val results = memberDslRepository.getByName(null, size, page)

        // Then
        assertThat(results).hasSize(3)
    }

    @Test
    fun `페이지네이션 테스트`() {
        // Given
        val size = 2
        val page = 2

        // When
        val results = memberDslRepository.getByName(null, size, page)

        // Then
        assertThat(results).hasSize(1)
    }
}