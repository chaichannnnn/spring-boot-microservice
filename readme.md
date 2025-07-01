zipkin
    Spring Boot 3.x use java.net.http.HttpClient instead OkHttp that have some problem about container network or zipkin image especially Docker Desktop (Windows).
    So use "zipkin-sender-urlconnection" to override Sender