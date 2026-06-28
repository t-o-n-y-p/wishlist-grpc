package ru.otus.wishlist.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.grpc.test.autoconfigure.AutoConfigureTestGrpcTransport;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.grpc.client.ImportGrpcClients;
import ru.otus.protobuf.GiftsServiceGrpc;
import ru.otus.protobuf.UsersServiceGrpc;
import ru.otus.protobuf.WishlistsServiceGrpc;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@Import(IntegrationTestConfiguration.class)
@AutoConfigureTestGrpcTransport
@ImportGrpcClients({
  WishlistsServiceGrpc.WishlistsServiceBlockingStub.class,
  GiftsServiceGrpc.GiftsServiceBlockingStub.class,
  UsersServiceGrpc.UsersServiceBlockingStub.class
})
public @interface GrpcIntegrationTest {}
