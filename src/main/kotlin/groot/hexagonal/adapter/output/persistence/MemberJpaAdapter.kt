package groot.hexagonal.adapter.output.persistence

import groot.hexagonal.application.port.output.MemberQueryPort
import groot.hexagonal.domain.Member
import org.springframework.stereotype.Component

@Component
class MemberJpaAdapter (private val memberRepository: MemberRepository): MemberQueryPort {
    override fun getMembers(name: String?, size: Int, page: Int): List<Member> {

        val members = memberRepository.getByName(name, size, page)

        // mapper 를 별도로 구성해야할지 아니면 dto 내부에서 toMember() 메소드로 구현하는게 좋을지
        return members.map { dto ->
            Member(
                id = dto.id,
                name = dto.name
            )
        }
    }

    override fun createMember(member: Member): Member {
        val memberEntity = MemberEntity(
            name = member.name,
            email = member.email
        )

        val savedMember = memberRepository.save(memberEntity)
        return Member(id = savedMember.id,
            name =  savedMember.name,
            email = savedMember.email)
    }

}