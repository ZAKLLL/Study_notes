```java
public static Map<String, String> getCodeGenConfig() throws IOException {
    Yaml yaml = new Yaml();
    //D:\Projects\generator\southsmart-jpa-generator\src\main\resources\application.yml
    FileInputStream file = new FileInputStream("config/application.yml");
    Map<String, Object> map = (Map<String, Object>) yaml.load(file);
    List<String> configInfo = new ArrayList<>();
    file.close();
    dfs(map, "", configInfo);
    HashMap<String, String> env = new HashMap<>();
    for (String s : configInfo) {
        int i = s.indexOf(":");
        env.put(s.substring(0, i), i == s.length() - 1 ? "" : s.substring(i + 1));
    }
    return env;
}
```

