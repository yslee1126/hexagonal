package groot.hexagonal.application.port.output

import groot.hexagonal.domain.Member

interface MemberQueryPort {

    fun getMembers(name: String?, size: Int, page: Int) : List<Member>
    fun createMember(member: Member): Member

}