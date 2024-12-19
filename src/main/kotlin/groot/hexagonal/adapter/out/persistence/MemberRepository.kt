package groot.hexagonal.adapter.out.persistence

import groot.hexagonal.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long>, MemberDslRepository {

}