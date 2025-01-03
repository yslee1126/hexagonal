package groot.hexagonal.adapter.input.grpc

import groot.hexagonal.application.port.input.MemberUseCase
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory

@GrpcService
class MemberGrpcService(private val memberUseCase: MemberUseCase) : MemberServiceGrpc.MemberServiceImplBase() {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    //todo api key 적용?
    override fun getMembers(req: MemberRequest, responseObserver: StreamObserver<MemberResponse?>) {

        log.debug("Received gRPC request: name={}, size={}, page={}", req.name, req.size, req.page)
        //todo 예외 처리

        val members = memberUseCase.getMembers(req.name, req.size, req.page)
        val response = MemberResponse.newBuilder()
            .addAllMembers(members.map { member ->
                Member.newBuilder()
                    .setId(member.id ?: 0L)
                    .setName(member.name)
                    .setEmail(member.email ?: "")
                    .build()
            })
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

}