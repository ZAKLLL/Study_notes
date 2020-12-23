```java
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HellWorldTest {
    @Autowired
    MockMvc mvc;

    @Test
    void t1() throws Exception {
        mvc.perform(get("/api/hi")).andExpect(status().isOk()).andExpect(content().string("Hi"));
    }

    @Test
    void t2() throws Exception {
        mvc.perform(get("/api/t?a=10")).andExpect(status().isOk()).andExpect(content().string("10"));
    }

    @Autowired
    HelloService helloService;

    @Test
    void t3() {
        System.out.println(helloService.hi());
    }
}
```

