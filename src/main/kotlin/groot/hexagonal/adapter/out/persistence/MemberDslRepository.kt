package groot.hexagonal.adapter.out.persistence

interface MemberDslRepository {

    fun getByName(name: String?, size: Int, page: Int): List<MemberDto>

}