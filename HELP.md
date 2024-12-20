
#### 포스트 
https://yslee1126.github.io/posts/hexagonal/

---
#### 회고 
- 여전히 repository 관련 클래스는 3개 필요한가 
- in.redis.consumer 패키지로 redis stream consumer 위치 하면 될 것
- out.redis.client 패키지로 redis template 위치 하면 될 것 
- out.payment 패키지로 결제 관련 연동 client 위치하면 될 것 
- persistence 아래 패키지 세분화? 새로운 db 프레임워크가 도입될때 adapter 만 교체가능하도록    
- 계층간에 객체 변환할때 mapper 가 별도로 필요할지 아니면 dto 에서 toEntity 메소드로 구현하는게 좋을지
- spring framework 가 교체되었을때 도메인과 로직을 유지하려면 service 아래 비지니스로직 위치한 코드는 유지 할 수 있을까  


