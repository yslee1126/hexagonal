package groot.hexagonal.application.service

import groot.hexagonal.application.port.input.MemberUseCase
import groot.hexagonal.application.port.output.MemberQueryPort
import groot.hexagonal.domain.Member
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberQueryPort: MemberQueryPort): MemberUseCase {
    override fun getMembers(name: String?, size: Int, page: Int): List<Member> {
        return memberQueryPort.getMembers(name, size, page)
    }

    override fun createMember(member: Member): Member {
        return memberQueryPort.createMember(member)
    }

}