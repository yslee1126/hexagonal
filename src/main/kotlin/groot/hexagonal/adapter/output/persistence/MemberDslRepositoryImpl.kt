package groot.hexagonal.adapter.output.persistence

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MemberDslRepositoryImpl(private val queryFactory: JPAQueryFactory): MemberDslRepository {
    override fun getByName(name: String?, size: Int, page: Int): List<MemberDto> {
        val memberEntity = QMemberEntity.memberEntity

        val builder = BooleanBuilder()

        if (!name.isNullOrBlank()) {
            builder.and(memberEntity.name.eq(name))
        }

        val query = queryFactory.select(
                Projections.fields(
                    MemberDto::class.java,
                    memberEntity.id,
                    memberEntity.name
                )
            )
            .from(memberEntity)
            .where(builder)
            .offset((page - 1) * size.toLong())
            .limit(size.toLong())
            .fetch()

        return query

    }

}