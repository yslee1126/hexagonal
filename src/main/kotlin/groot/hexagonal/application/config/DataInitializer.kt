package groot.hexagonal.application.config

import groot.hexagonal.application.port.input.MemberUseCase
import groot.hexagonal.domain.Member
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("!default")
class DataInitializer(private val memberUseCase: MemberUseCase) : CommandLineRunner {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    override fun run(vararg args: String?) {
        // 유저 생성 로직
        val members = listOf(
            Member(name = "John Doe", email = "john.doe@example.com"),
            Member(name = "Jane Smith", email = "jane.smith@example.com")
        )

        members.forEach {
            val member = memberUseCase.createMember(it)
            log.debug("user created id {} name {} email {}", member.id, member.name, member.email)
        }

    }
}