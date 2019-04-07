安装webpack：

+ ```
  npm install webpack webpack-cli --save -dev
  ```

打包：

+ ``` 
  npx webpack --config webpack.config.js
  ```

main.js:

+ ```js
  import Vue from "../node_modules/vue/dist/vue.js";
  import VueRouter from "../node_modules/vue-router/dist/vue-router.js";
  
  Vue.use(VueRouter) //组件化后需要这样来开启路由功能
  let login = {
      template: ` <div>
          账号:<input type="text"><br/>
          密码:<input type="text"><br/>
      </div>`
  }
  let register = {
      template: `<div>
          账号:<input type="text"><br/>
          密码:<input type="text"><br/>
          确认密码:<input type="text">
      </div>`
  }
  const router = new VueRouter({
      routes: [
          {path: "/login", component: login}, //自定义路由规则
          {path: "/register", component: register}
      ]
  });
  let vm = new Vue({
      el: "#app",
      components: {
          login,
          register
      },
      router
  });
  ```

webpack.config.js:

+ ```javascript
  module.exports={
      entry:"./src/main.js", //指定打包文件入口
      output:{
          path: __dirname + "/dist", //__dirname是webpack文件所在路径的绝对路径
          filename: "biuld.js"
      }
  }
  ```

  

css样式打包：

![1551099383400](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1551099383400.png)