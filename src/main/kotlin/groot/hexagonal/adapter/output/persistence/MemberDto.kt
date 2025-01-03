package groot.hexagonal.adapter.output.persistence

data class MemberDto (val id: Long? = null,
                      val name: String,) {
    constructor() : this(null, "")
}