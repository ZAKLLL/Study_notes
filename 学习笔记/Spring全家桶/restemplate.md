**post请求**

```java
HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();

        //请求参数参考 麦克物联网接口文档
        paramsMap.add("username", username);
        paramsMap.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(paramsMap, headers);
        ResponseEntity<ReceiveHistoryPressureData> response = restTemplate.postForEntity(PRESSURE_HISTORY_DATA_URL, request, ReceiveHistoryPressureData.class);
        return response.getBody();
```

**get**:

