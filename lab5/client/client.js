const {SingleNumberRequest, SingleNumberResponse, Prime} = require('./gen/web_pb.js');
const {EratosthenesSieveClient} = require('./gen/web_grpc_web_pb.js');

var client = new EratosthenesSieveClient('http://localhost:8080');

const countPrimeInput = document.getElementById('prim-cnt-input');
const countPrimeButton = document.getElementById('prim-cnt-button');

countPrimeButton.addEventListener('click', () => {
  var input = new SingleNumberRequest();
  input.setNumber(countPrimeInput.value);
  client.numberOfPrimes(input, {}, (err, r) => {
    addResponse(r);
  });
});


const maxPrimeInput = document.getElementById('prim-max-input');
const maxPrimeButton = document.getElementById('prim-max-button');

maxPrimeButton.addEventListener('click', () => {
  var input = new SingleNumberRequest();
  input.setNumber(maxPrimeInput.value);
  client.highestPrime(input, {}, (err, r) => {
    addResponse(r);
  });
});


const primesStreamInput = document.getElementById('primes-stream-input');
const primesStreamRun= document.getElementById('primes-stream-run');

primesStreamRun.addEventListener('click', () => {
  var input = new SingleNumberRequest();
  input.setNumber(primesStreamInput.value);
  var stream = client.allPrimes(input, {});
  stream.on('data', (r) => {
    addResponse(r);
  });
  stream.on('status', (status) => {
    console.log(status.code);
  });
  stream.on('end', (end) => {
    
  });
  document.getElementById('primes-stream-stop').addEventListener('click', () => {
      stream.cancel();
  });
});

addResponse = (r) => {
  const ele = `
  <div class="alert alert-primary" role="alert">
    <p>Index: ${r.getNumber()}</p>
    <p>Value: ${r.getValue()}</p>
    <p>Percent: ${r.getPercent()}</p>
  </div>
  `;
   const div = document.createElement('div');
   div.innerHTML = ele;

   document.getElementById('primes-playground').prepend(div);
   setTimeout(() => {
     div.remove();
   }, 5000)
}