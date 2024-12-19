package groot.hexagonal.adapter.out.persistence

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MemberDslRepositoryImpl(private val queryFactory: JPAQueryFactory): MemberDslRepository {
    override fun getByName(name: String?, size: Int, page: Int): List<MemberDto> {
        val memberEntity = QMemberEntity.memberEntity
        val query = queryFactory.select(
            Projections.fields(
                MemberDto::class.java,
                memberEntity.id,
                memberEntity.name
            )
        )
            .from(memberEntity)
            .where(name?.let { memberEntity.name.eq(it) })
            .offset((page - 1) * size.toLong())
            .limit(size.toLong())
            .fetch()

        return query

    }

}