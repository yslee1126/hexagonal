package groot.hexagonal.application.port.`in`

import groot.hexagonal.domain.Member

interface MemberUseCase {

    fun getMembers(name: String?, size: Int, page: Int) : List<Member>
    fun createMember(member: Member): Member

}