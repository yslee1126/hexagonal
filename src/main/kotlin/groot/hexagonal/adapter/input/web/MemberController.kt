package groot.hexagonal.adapter.input.web

import groot.hexagonal.application.port.input.MemberUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberUseCase: MemberUseCase) {

    @GetMapping("/members")
    fun getMembers(@RequestParam(defaultValue = "1") page: Int,
                   @RequestParam(defaultValue = "10") size: Int,
                   @RequestParam(required = false) name: String?) : ResponseEntity<MemberListResponse> {

        val members = memberUseCase.getMembers(name, size, page)

        val memberResponses = members.map { member ->
            MemberResponse(member.id, member.name)
        }

        val memberResponse = MemberListResponse(memberResponses)

        return ResponseEntity.ok(memberResponse)
    }

}