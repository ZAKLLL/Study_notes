```java
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {
    
    @Autowired
    private Environment env;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //类文件路径
		//registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        
        //配置springboot中虚拟的路径，用于访问本地的文件资源
        String virtualResourceHandler = "/preview/**";
        String virtualResourceLocation = "file:D:/application/dev/appName/resource/";
        registry.addResourceHandler(virtualResourceHandler).addResourceLocations(virtualResourceLocation);
    }

}
```