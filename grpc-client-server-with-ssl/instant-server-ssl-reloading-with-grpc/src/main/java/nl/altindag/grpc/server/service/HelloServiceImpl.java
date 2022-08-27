package nl.altindag.grpc.server.service;

import io.grpc.stub.StreamObserver;
import nl.altindag.grpc.HelloRequest;
import nl.altindag.grpc.HelloResponse;
import nl.altindag.grpc.HelloServiceGrpc;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(String.format("Hello %s, nice to meet you!", request.getName()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
