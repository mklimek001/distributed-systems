Stworzono na podstawie: https://www.vinsguru.com/grpc-web-example/ oraz https://grpc.io/docs/platforms/web/basics/ 
   
Wnioski:
 - bardzo uciążliwa konfiguracja (problemy z wykonywaniem pakietów pobranych przez npm, konieczność dodawania odpowiednich ścieżek do zmiennej 'Path', trudności przy współpracy różnych wersji protoc, grpc-web itp., docker, odpowiednie .jar dla Flex i Mono w Javie)
 - możliwe jest przesyłanie danych tylko o zaprojektowanej wcześniej strukturze
 - ze wzglęgu na używany wciąż standard HTTP 1.1 konieczne jest korzystanie z odpowiedniego proxy, na przykład takiego jak w pliku envoy.yaml
 + możliwe jest korzystanie z różnych języków programowania po stronie serwera i klienta, pełna kontrila nad przepływem danych
 + brak konieczności obsługi plików JSON itp. - dostęp do danych jak do obiektów języka
 + możliwość przesyłania strumieni danych


To run server you need to run command:
```
docker compose -f docker-compose.yaml up
```