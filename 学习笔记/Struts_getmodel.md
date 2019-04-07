# Struts2S使用模型驅動ModelDriven進行傳值  
在一般情况下jsp页面与action进行传值  
+ 使用struts标签  
```
    <s:form action="doregister">
          <h3>注册用户名: <s:textfield name="username" /></h3>         
    	 <h3>注册 密码:  <s:password name="password" /></h3>       
 	    <s:submit />
 	</s:form>
```  
action 中：  
```
    private String username;
	private String password;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
```
+ 或者：  
JSP中:    
```
<s:form action="doregister">
           <h3>注册用户名: <s:textfield name="user.username" /></h3>      
    	   <h3>注册 密码:  <s:password name="user.password" /></h3>       
 	    <s:submit />
 	</s:form>

```   
action中 
```
    private Userpo user;
	
	public void SetUser(Userpo user){
        this.user=user;
    }
    public Userpo GetUser(){
        return this.user;
    }
```
+ 使用ModelDriven之后的传值
```
<s:form action="doregister">
          <h3>注册用户名: <s:textfield name="username" /></h3>         
    	 <h3>注册 密码:  <s:password name="password" /></h3>       
 	    <s:submit />
 	</s:form>
```
action中发生改变：
```
private Userpo user;
	
	public UserPO getModel(){
        this.user=new UserPo;
        return this.user;
    }
```
***注意看action中添加了 GetModel方法通过这样的方法可以直接向action中的user中的属性进行传值***
