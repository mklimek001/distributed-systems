package gen.primes;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: web.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EratosthenesSieveGrpc {

  private EratosthenesSieveGrpc() {}

  public static final String SERVICE_NAME = "Primes.EratosthenesSieve";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest,
      gen.primes.SingleNumberResponse> getNumberOfPrimesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "numberOfPrimes",
      requestType = gen.primes.SingleNumberRequest.class,
      responseType = gen.primes.SingleNumberResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest,
      gen.primes.SingleNumberResponse> getNumberOfPrimesMethod() {
    io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest, gen.primes.SingleNumberResponse> getNumberOfPrimesMethod;
    if ((getNumberOfPrimesMethod = EratosthenesSieveGrpc.getNumberOfPrimesMethod) == null) {
      synchronized (EratosthenesSieveGrpc.class) {
        if ((getNumberOfPrimesMethod = EratosthenesSieveGrpc.getNumberOfPrimesMethod) == null) {
          EratosthenesSieveGrpc.getNumberOfPrimesMethod = getNumberOfPrimesMethod =
              io.grpc.MethodDescriptor.<gen.primes.SingleNumberRequest, gen.primes.SingleNumberResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "numberOfPrimes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.primes.SingleNumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.primes.SingleNumberResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EratosthenesSieveMethodDescriptorSupplier("numberOfPrimes"))
              .build();
        }
      }
    }
    return getNumberOfPrimesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest,
      gen.primes.SingleNumberResponse> getHighestPrimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "highestPrime",
      requestType = gen.primes.SingleNumberRequest.class,
      responseType = gen.primes.SingleNumberResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest,
      gen.primes.SingleNumberResponse> getHighestPrimeMethod() {
    io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest, gen.primes.SingleNumberResponse> getHighestPrimeMethod;
    if ((getHighestPrimeMethod = EratosthenesSieveGrpc.getHighestPrimeMethod) == null) {
      synchronized (EratosthenesSieveGrpc.class) {
        if ((getHighestPrimeMethod = EratosthenesSieveGrpc.getHighestPrimeMethod) == null) {
          EratosthenesSieveGrpc.getHighestPrimeMethod = getHighestPrimeMethod =
              io.grpc.MethodDescriptor.<gen.primes.SingleNumberRequest, gen.primes.SingleNumberResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "highestPrime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.primes.SingleNumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.primes.SingleNumberResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EratosthenesSieveMethodDescriptorSupplier("highestPrime"))
              .build();
        }
      }
    }
    return getHighestPrimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest,
      gen.primes.Prime> getAllPrimesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "allPrimes",
      requestType = gen.primes.SingleNumberRequest.class,
      responseType = gen.primes.Prime.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest,
      gen.primes.Prime> getAllPrimesMethod() {
    io.grpc.MethodDescriptor<gen.primes.SingleNumberRequest, gen.primes.Prime> getAllPrimesMethod;
    if ((getAllPrimesMethod = EratosthenesSieveGrpc.getAllPrimesMethod) == null) {
      synchronized (EratosthenesSieveGrpc.class) {
        if ((getAllPrimesMethod = EratosthenesSieveGrpc.getAllPrimesMethod) == null) {
          EratosthenesSieveGrpc.getAllPrimesMethod = getAllPrimesMethod =
              io.grpc.MethodDescriptor.<gen.primes.SingleNumberRequest, gen.primes.Prime>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "allPrimes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.primes.SingleNumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.primes.Prime.getDefaultInstance()))
              .setSchemaDescriptor(new EratosthenesSieveMethodDescriptorSupplier("allPrimes"))
              .build();
        }
      }
    }
    return getAllPrimesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EratosthenesSieveStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EratosthenesSieveStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EratosthenesSieveStub>() {
        @java.lang.Override
        public EratosthenesSieveStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EratosthenesSieveStub(channel, callOptions);
        }
      };
    return EratosthenesSieveStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EratosthenesSieveBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EratosthenesSieveBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EratosthenesSieveBlockingStub>() {
        @java.lang.Override
        public EratosthenesSieveBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EratosthenesSieveBlockingStub(channel, callOptions);
        }
      };
    return EratosthenesSieveBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EratosthenesSieveFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EratosthenesSieveFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EratosthenesSieveFutureStub>() {
        @java.lang.Override
        public EratosthenesSieveFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EratosthenesSieveFutureStub(channel, callOptions);
        }
      };
    return EratosthenesSieveFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void numberOfPrimes(gen.primes.SingleNumberRequest request,
        io.grpc.stub.StreamObserver<gen.primes.SingleNumberResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getNumberOfPrimesMethod(), responseObserver);
    }

    /**
     */
    default void highestPrime(gen.primes.SingleNumberRequest request,
        io.grpc.stub.StreamObserver<gen.primes.SingleNumberResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHighestPrimeMethod(), responseObserver);
    }

    /**
     */
    default void allPrimes(gen.primes.SingleNumberRequest request,
        io.grpc.stub.StreamObserver<gen.primes.Prime> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllPrimesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EratosthenesSieve.
   */
  public static abstract class EratosthenesSieveImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EratosthenesSieveGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EratosthenesSieve.
   */
  public static final class EratosthenesSieveStub
      extends io.grpc.stub.AbstractAsyncStub<EratosthenesSieveStub> {
    private EratosthenesSieveStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EratosthenesSieveStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EratosthenesSieveStub(channel, callOptions);
    }

    /**
     */
    public void numberOfPrimes(gen.primes.SingleNumberRequest request,
        io.grpc.stub.StreamObserver<gen.primes.SingleNumberResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getNumberOfPrimesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void highestPrime(gen.primes.SingleNumberRequest request,
        io.grpc.stub.StreamObserver<gen.primes.SingleNumberResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHighestPrimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void allPrimes(gen.primes.SingleNumberRequest request,
        io.grpc.stub.StreamObserver<gen.primes.Prime> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllPrimesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EratosthenesSieve.
   */
  public static final class EratosthenesSieveBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EratosthenesSieveBlockingStub> {
    private EratosthenesSieveBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EratosthenesSieveBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EratosthenesSieveBlockingStub(channel, callOptions);
    }

    /**
     */
    public gen.primes.SingleNumberResponse numberOfPrimes(gen.primes.SingleNumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getNumberOfPrimesMethod(), getCallOptions(), request);
    }

    /**
     */
    public gen.primes.SingleNumberResponse highestPrime(gen.primes.SingleNumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHighestPrimeMethod(), getCallOptions(), request);
    }

    /**
     */
    public gen.primes.Prime allPrimes(gen.primes.SingleNumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllPrimesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EratosthenesSieve.
   */
  public static final class EratosthenesSieveFutureStub
      extends io.grpc.stub.AbstractFutureStub<EratosthenesSieveFutureStub> {
    private EratosthenesSieveFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EratosthenesSieveFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EratosthenesSieveFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gen.primes.SingleNumberResponse> numberOfPrimes(
        gen.primes.SingleNumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getNumberOfPrimesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gen.primes.SingleNumberResponse> highestPrime(
        gen.primes.SingleNumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHighestPrimeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<gen.primes.Prime> allPrimes(
        gen.primes.SingleNumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllPrimesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_NUMBER_OF_PRIMES = 0;
  private static final int METHODID_HIGHEST_PRIME = 1;
  private static final int METHODID_ALL_PRIMES = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NUMBER_OF_PRIMES:
          serviceImpl.numberOfPrimes((gen.primes.SingleNumberRequest) request,
              (io.grpc.stub.StreamObserver<gen.primes.SingleNumberResponse>) responseObserver);
          break;
        case METHODID_HIGHEST_PRIME:
          serviceImpl.highestPrime((gen.primes.SingleNumberRequest) request,
              (io.grpc.stub.StreamObserver<gen.primes.SingleNumberResponse>) responseObserver);
          break;
        case METHODID_ALL_PRIMES:
          serviceImpl.allPrimes((gen.primes.SingleNumberRequest) request,
              (io.grpc.stub.StreamObserver<gen.primes.Prime>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getNumberOfPrimesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              gen.primes.SingleNumberRequest,
              gen.primes.SingleNumberResponse>(
                service, METHODID_NUMBER_OF_PRIMES)))
        .addMethod(
          getHighestPrimeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              gen.primes.SingleNumberRequest,
              gen.primes.SingleNumberResponse>(
                service, METHODID_HIGHEST_PRIME)))
        .addMethod(
          getAllPrimesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              gen.primes.SingleNumberRequest,
              gen.primes.Prime>(
                service, METHODID_ALL_PRIMES)))
        .build();
  }

  private static abstract class EratosthenesSieveBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EratosthenesSieveBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gen.primes.PrimesProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EratosthenesSieve");
    }
  }

  private static final class EratosthenesSieveFileDescriptorSupplier
      extends EratosthenesSieveBaseDescriptorSupplier {
    EratosthenesSieveFileDescriptorSupplier() {}
  }

  private static final class EratosthenesSieveMethodDescriptorSupplier
      extends EratosthenesSieveBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EratosthenesSieveMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EratosthenesSieveGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EratosthenesSieveFileDescriptorSupplier())
              .addMethod(getNumberOfPrimesMethod())
              .addMethod(getHighestPrimeMethod())
              .addMethod(getAllPrimesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
