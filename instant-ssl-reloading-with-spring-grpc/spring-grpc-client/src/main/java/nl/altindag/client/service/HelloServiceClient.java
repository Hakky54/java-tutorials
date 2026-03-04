/*
 * Copyright 2022 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.client.service;

import nl.altindag.grpc.HelloRequest;
import nl.altindag.grpc.HelloResponse;
import nl.altindag.grpc.HelloServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceClient {

    private final HelloServiceGrpc.HelloServiceBlockingStub blockingStub;

    public HelloServiceClient(HelloServiceGrpc.HelloServiceBlockingStub blockingStub) {
        this.blockingStub = blockingStub;
    }

    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloResponse response = blockingStub.hello(request);
        return response.getMessage();
    }

}
