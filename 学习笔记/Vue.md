# Vue

1. 导入与使用：

   ```html
   <body>
   <div id="aa">
       <input type="text" v-model="name"/><br/>
       <button v-on:click="num++">+</button> <!--直接对数据进行操作-->
       <button v-on:click="fun1">-</button>  <!--调用fun1函数对数据进行操作-->
       <h1>{{name}}XJJ</h1>
       <h1>有{{num}}cm长</h1>
   </div 
   <script src="./node_modules/vue/dist/vue.js"></script> <!--导入vue-->
   <script>
       let vm = new Vue({
               el: "#aa",
               data: {
                   name: "周振昭",
                   num:18
               },
               methods:{
                   fun1(){
                       this.num--  <!--this指的是vm（vue实例）-->
                   }
               }
           }
       );
   </script>
   </body>
   ```

   2.{{表达式}}：

   + 表达式支持js语法，可以调用内置js函数(必须具有返回值)
   + 表达式必须得有返回值（1+1），没有结果的表达式不允许使用（var a=1+1)
   + 可以直接获取Vue实例中的数据或者函数

   3. v-model：用以v-m的双向绑定。

   4. v-on：绑定事件，可以直接编写js函数，或者调用vue实例中的methods中的自定义函数(可以用@标签进行替代 )

   5. computed: 计算属性，里面的方法必须具有返回值，每当计算属性中的变量发生变化时，vue会自动重新计算方法的值

   6. watch:监控计算属性，异步计算时使用

      1. ![1551060744842](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1551060744842.png)

   7. ![1551065136964](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1551065136964.png)

   8. 父组件向子组件传递信息：

      ```html
      <body>
      <div id="app">
          <input type="text" v-model="dd"/>
          <yy :p="dd"></yy>
      </div>
      <script src="./node_modules/vue/dist/vue.js"></script>
      <script>
          var yy={     //定义子组件
              template:"<h1>{{p}}</h1>",
              props: ['p'] 
          }
          var vm=new Vue({
              el: "#app",
              components:{   //注册子组件
               yy
              },
              data:{
                  dd: "test"
              }
          })
      </script>
      </body>
      ```

   9. Vue-router:

      ```javascript
      <body>
      <div id="app">
          <router-link to="/login">登录</router-link>
          <router-link to="/register">注册</router-link>
          <router-view></router-view>
      </div>
      <script src="./node_modules/vue/dist/vue.js"></script>
      <script src="./node_modules/vue-router/dist/vue-router.js"></script>
      <script>
          let login={
              template:` <div>
              账号:<input type="text"><br/>
              密码:<input type="text"><br/>
          </div>`
          }
          let register={
              template:`<div>
              账号:<input type="text"><br/>
              密码:<input type="text"><br/>
              确认密码:<input type="text">
          </div>`
          }
          const router = new VueRouter({
              routes:[
                  {path:"/login", component: login},
                  {path:"/register", component: register}
              ]
          });
          let vm = new Vue({
              el: "#app",
              components:{
                  login,
                  register
              },
              router
          });
      </script>
      </body>
      ```

      

