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

      

## V指令

+ 插值表达式和v-text

+ v-cloak

+ v-html

+ v-bind   属性绑定机制，v-bind:title(绑定title属性)  可用 **：**替代

+ v-on      事件绑定机制 ，缩写为@ 

+ v-for     循环遍历数据

+ ```html
  <body>
      <div id="app">
          <!--遍历数组-->
          <p v-for="(item,i) in userlist" >索引为{{i}},姓名为{{item.name}},年龄为{{item.age}} </p>
          <!--遍历对象-->
          <p v-for="(val,key) in user" >key:{{key}}---val:{{val}}</p>
          <!--迭代数字-->
          <p v-for="i in 5" >第几次循环{{i}}</p>
      </div>
      <script>
          var vm = new Vue({
              el: "#app",
              data: {
                 userlist:[
                     {name:"张二狗",age:19},
                     {name:"李二娃",age:11},
                     {name:"赵四狗",age:13},
                     {name:"李天王",age:14}
                 ],
                 user:{
                     name:"张三",
                     age:98,
                     height:175
                 }
              },
              methods: {
              }
          })
      </script>
  </body>
  ```

+ v-model  双向数据绑定(只能应用再表单元素中 input select checkbox...)

+ ```js
  <body>
      <div id="app">
          <!--解决插值表达式闪烁的问题-->
          <!--插值表达式不会覆盖原有内容 -->
          <p v-cloak>{{msg2}}hello</p>
  
          <!--可以避免出现闪烁问题-->
          <!--hello会被v-text指令的内容替换-->
          <h4 v-text="msg">hello</h4>
  
          <div v-html="msg2">
          </div>
          <!--v-bind是用来绑定属性的指令，在这里绑定了title属性-->
          <input type="button" value="按钮" v-bind:title="mytitle">
          <!--v-band:title == :title -->
          <input type="button" value="按钮二" :title="mytitle">
          
          <!--v-on用来绑定事件-->
          <input type="button" value="按钮三" :title="mytitle" v-on:click="show('hello')">
          <!--使用v-model进行数据双向绑定-->    
          <input type="text" v-model="msg" >
  
      </div>
  </body>
  <script>
      var vm = new Vue({
          el: "#app",
          data: {
              msg: "123",
              msg2: "<h2>this is v-html's msg2 </h2>",
              mytitle: "自定义title"
          },
          methods: { //method 属性中定义了当前Vue实例所有可用的方法
              show: function (str) {
                  alert(str)
              }
          }
      })
  </script>
  ```

+ **v-if**和**v-show**  如果元素涉及到频繁地切换最好不使用v-if

+ ```html
  <div id="app">
          <button value="显示" @click="flag=!flag">显示</button>
          <!--v-if的特点是每次都会删除或创建元素-->
          <h1 v-if="flag">这是v-if控制的元素 </h1>
          <!--v-if的特点是使用style的display样式来进行是否显示-->
          <h1 v-show="flag">这是v-show控制的元素 </h1>
      </div>
      <script>
          var vm = new Vue({
              el: "#app",
              data: {
                  flag:true
              },
          })
      </script>
  ```

## vue使用样式

+ class样式:

+ ```html
  <head>
        <style>
          .toRed{
              color: red
          }
          .italic{
              font-style: italic
          }
      </style>
  </head>
  <body>
      <div id="app">
          <h1 :class="classobj" >Hello world</h1>
      </div>
      <script>
          var vm = new Vue({
              el: "#app",
              data: {
                  flag=false,
                  classobj={toRed:true,italic:false}
              },
              methods: {
              }
          })
      </script>
  </body>
  ```

+ style样式

+ ```html
  <body>
      <div id="app">
          <!--如果属性里面有横杠则需要添加单引号-->
          <h1 :style="{color:'red','font-weight':200}">hello world</h1>
          <!--v-bind绑定data中的数据-->
          <h1 :style="styleobj"> Hello World</h1>
      </div>
      <script>
          var vm = new Vue({
              el: "#app",
              data: {
                  styleobj:{ color: 'green', 'font-weight': 400 }
              },
              methods: {
              }
          })
      </script>
  </body>
  ```


### 事件修饰符

+ **.stop方法阻止冒泡事件**

+ ```js
  <div id="app">
          <div class="inner" @click="divclicker">
  			<!--如果不添加.stop时间，那就会调用到div中的点击事件因为btn在div中-->
              <input type="button" value="戳" @click.stop="btnclick">
          </div>
      </div>
      <script>
          var vm = new Vue({
              el: "#app",
              methods: {
                  divclicker(){
                      alert("divclicker")
                  },
                  btnclick(){
                      alert("btnclicker")
                  }
              }
          })
      </script>
  ```

+ **.prevent 阻止默认事件**

+ ```js
  <a href="https://www.baidu.com" @click.prevent="linkclick"> 百度</a> 
  //阻止默认点击后跳转到www.baidu.com
  ```

+ **.capture**  捕获事件，从外到里，只要点击了btn就会被先执行div中的click事件，再执行btn的点击事件

+ ```java
   <div class="inner" @click.capture="divclicker">
              <input type="button" value="戳" @click="btnclick">
  </div>
  ```

+ **.self**：只有点击自己才会触发

+ ```html
  <div class="inner" @click.self="divclicker">
            <input type="button" value="戳" @click="btnclick">
  </div>
  ```

+ **.once**：只触发一次事件函数。

+ ```html
  <input type="button" value="戳" @click.once="btnclick">
  <!--在页面再次刷新之前只能点击一次-->
  ```

+ ***.self只会组织自己身上冒泡行为的触发，并不会阻止真正的冒泡行为***

   

   


