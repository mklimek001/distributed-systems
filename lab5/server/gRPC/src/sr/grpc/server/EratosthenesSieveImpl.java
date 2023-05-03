package sr.grpc.server;

import gen.primes.EratosthenesSieveGrpc;
import gen.primes.Prime;
import gen.primes.SingleNumberRequest;
import gen.primes.SingleNumberResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;


public class EratosthenesSieveImpl extends EratosthenesSieveGrpc.EratosthenesSieveImplBase {

    //@Override
    public Mono<SingleNumberResponse> getNumberOfPrimes(Mono<SingleNumberRequest> request) {
        return request.map(SingleNumberRequest::getNumber)
                .map(i -> SingleNumberResponse.newBuilder().setNumber(sieveOfEratosthenes(i).peekLast().getNumber()).build());
    }

    //@Override
    public Mono<SingleNumberResponse> getHighestPrime(Mono<SingleNumberRequest> request) {

        return request.map(SingleNumberRequest::getNumber)
                .map(i -> SingleNumberResponse.newBuilder().setNumber((long) sieveOfEratosthenes(i).peekLast().getValue()).build());
    }


    //@Override
    public Flux<Prime> allPrimes(Mono<SingleNumberRequest> request) {
        return request.map(SingleNumberRequest::getNumber)
                .flatMapIterable(this::sieveOfEratosthenes)
                .map(o -> Prime.newBuilder()
                        .setPercent(o.getPercent())
                        .setNumber(o.getNumber())
                        .setValue(o.getValue())
                        .build());
    }

    private LinkedList<PrimeLikeClass> sieveOfEratosthenes(int n)
    {
        LinkedList<PrimeLikeClass> primes = new LinkedList<>();
        boolean[] prime = new boolean[n+1];
        for(int i=0;i<=n;i++)
            prime[i] = true;

        for(int p = 2; p*p <=n; p++)
        {
            if(prime[p])
            {
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        int counter = 1;

        for(int i = 2; i <= n; i++)
        {
            if(prime[i]){
                PrimeLikeClass primeLikeClass = new PrimeLikeClass(counter, i, 0F);
                primes.add(primeLikeClass);
            }

        }

        int counter2 = 1;
        for(PrimeLikeClass plc:primes){
            plc.setPercent((float) counter2/(float)counter);
            counter2++;
        }

        if(primes.size() == 0){
            primes.add(new PrimeLikeClass(0, -1, 0F));
        }

        return primes;
    }

}
