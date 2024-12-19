package groot.hexagonal.adapter.out.persistence

data class MemberDto (val id: Long? = null,
                      val name: String,) {
    constructor() : this(null, "")
}