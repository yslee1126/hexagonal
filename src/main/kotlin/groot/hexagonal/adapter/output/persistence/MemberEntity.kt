package groot.hexagonal.adapter.output.persistence

import jakarta.persistence.*

@Entity
class MemberEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String? = null
)