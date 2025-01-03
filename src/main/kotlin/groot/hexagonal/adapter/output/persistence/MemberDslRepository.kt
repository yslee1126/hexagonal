package groot.hexagonal.adapter.output.persistence

interface MemberDslRepository {

    fun getByName(name: String?, size: Int, page: Int): List<MemberDto>

}