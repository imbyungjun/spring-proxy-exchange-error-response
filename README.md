# ProxyExchange chunk error sample

### Environment
* Java 11
* Kotlin 1.5

### Run & Results
#### Build and run sample web application.
```shell
./gradlew bootJar && java -jar build/libs/spring-playground-0.0.1-SNAPSHOT.jar
```

#### Request to api endpoint directly.
```shell
curl -v --raw http://localhost:8080/api/foo
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /api/foo HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 400
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 06 Sep 2021 04:51:11 GMT
< Connection: close
<
f
{"key":"value"}
0

* Closing connection 0
```
The hex character 'f' means that next chunk size is 15 in decimal. And the 0 after json body means that it's end of the response body. 


#### Request to proxy endpoint.
```shell
curl -v --raw http://localhost:8080/proxy/api/foo
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /proxy/api/foo HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 400
< Transfer-Encoding: chunked
< Date: Mon, 06 Sep 2021 04:51:29 GMT
< Connection: close
< Content-Type: application/json
<
* Illegal or missing hexadecimal sequence in chunked-encoding
* Closing connection 0
curl: (56) Illegal or missing hexadecimal sequence in chunked-encoding
{"key":"value"}
```
Response header said that it's a chunked body response. But, there's no hex character indicates next chunk length.