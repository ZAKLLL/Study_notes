# Eureka注册

+ 注册服务中心：

  + yml文件

    ```yml
    server:
      port: 10086
    spring:
      application:
        name: eureka-server
    eureka:
      client:
        register-with-eureka: false
        fetch-registry: false
        service-url:
          defaultZone: http://localhost:10086/eureka
      #优先使用ip地址注册
      instance:
        prefer-ip-address: true
    
    ```

  + 主类：

    ```java
    @SpringBootApplication
    @EnableEurekaServer //声明为服务中心
    public class EurekaApplication {
        public static void main(String[] args) {
            SpringApplication.run(EurekaApplication.class, args);
        }
    }
    ```

+ 注册生产者(在启动类添加@EnableDiscoveryClient)：

  + yml文件编写

    ```yml
    server:
      port: 8188
    eureka:
      client:
        service-url:
          defaultZone: http://localhost:10086/eureka  #将该服务注册到此地址
    spring:
      application:
        name: productor2                    #服务名，其他服务通过该名称对此服务进行调用
    ```

  + 提供接口：

    ```java
    @Controller
    public class TT {
        @RequestMapping("/TT")
        @ResponseBody
        public Map tt(){
            Map<String, String> map = new HashMap<>();
            map.put("k1", "v1");
            map.put("k2", "v3");
            return map;
        }
    }
    ```

+ 注册消费者(在启动类添加@EnableDiscoveryClient,以及@EnableFeignClients)：

  + yml

    ```yml
    eureka:
      client:
        service-url:
           defaultZone: http://localhost:10086/eureka
    spring:
      application:
        name: comsumer2
    server:
      port: 8082
    ```

  + 通过RestTemplate调用(在主类中注册RestTemplate):

    ```java
    	//启动类中注册
    	@Bean
        @LoadBalanced
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    
    //调用方法
    @Autowired
        private RestTemplate restTemplate;
        @Test
        public void TT() {
            String url = "http://productor2/TT";
            Map m = restTemplate.getForObject(url, Map.class);
            System.out.println(m);
        }
    ```

  + 通过feign调用(需要添加配置文件)：

    ```java
    //声明调用的服务提供者"productor2"，调用的具体服务路劲"TT"，对应productor中的controller中的接口访问路劲
    @FeignClient("productor2")
    @RequestMapping("TT")
    public interface UserClient {
        @GetMapping("/")
        Map tt();
    }
    
    //在消费者的Controller中对该服务进行调用
    @Controller
    public class TestController {
        @Autowired
        private UserClient userClient;
    
        @ResponseBody
        @RequestMapping("/aa")
        public Map d(){
            return this.userClient.tt();
        }
    }
    ```
    
  + 使用feign调用与失败调用,并且在调用过程中自定义HttpRequest

    ```java
    @Component
    @FeignClient(name = "authentication-server", fallback = AuthProvider.AuthProviderFallback.class)
    public interface AuthProvider {
        //调用authentication-server 对应的/auth/permission 接口
        //将token放置在远程调用的HttpRequest中的头信息里
        @PostMapping(value = "/auth/permission")
        Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("url") String url, @RequestParam("method") String method);
    
        @Component
        class AuthProviderFallback implements AuthProvider {
    
            @Override
            public Result auth(String authentication, String url, String method) {
                return Result.fail();
            }
        }
    }
    
    ----------Povider-------------
    @PostMapping(value = "/auth/permission")
    public Result decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
            boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
            return Result.success(decide);
        }
    ```

    

  

