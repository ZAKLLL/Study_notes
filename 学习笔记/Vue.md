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

   

   

### 过滤器

+ **全局过滤器**

+ ```html
  <div id="app">
          <h1 >{{msg|myfilter('hi')}}</h1>
      </div>
      <script>
          //默认传插值表达式为参数，可以自行添加参数
          Vue.filter("myfilter",function(msg,str){
              alert(str)
              return msg.replace(/hello/g,"good");
          })
          var vm = new Vue({
              el: "#app",
              data: {
                  msg:"this is hello world , vue's hello world"
              },
          })
  </script>
  ```

+ **私有过滤器**(全局过滤器可以和私有过滤器起相同的名字，当名称相同时，将采用就近原则选择私有的)

+ ```html
  <body>
      <div id="app">
          <h1 >{{msg|myfilter('hi')}}</h1>
          <h1 >{{msg|myfilter2()}}</h1>
      </div>
      <script>
          //全局过滤器
          //默认传插值表达式为参数，可以自行添加参数
          Vue.filter("myfilter",function(msg,str){
              alert(str)
              return msg.replace(/hello/g,"good");
          })
          var vm = new Vue({
              el: "#app",
              data: {
                  msg:"this is hello world , vue's hello world"
              },
              filters:{ //定义私有过滤器
                  myfilter2:function(msg){
                      return msg.replace(/hello/g,"good--------");
                  }
              }
          })
      </script>
  </body>
  ```


### 自定义键盘码

+ Vue提供了一部分按键码

  + `.enter`
  + `.tab`
  + `.delete` (捕获“删除”和“退格”键)
  + `.esc`
  + `.space`
  + `.up`
  + `.down`
  + `.left`
  + `.right`

+ 自定义按键码

  + ```html
    <input type="text" class="form-control" v-model="name" @keyup.f2="add">
    //自定义全局按键修饰符
    Vue.config.keyCodes.f2=113
    ```

### 自定义指令(自定义私有指令和filters类似)

+ + ```javascript
    <!--此处的v-focus为自定义的focus指令-->
    <input type="text" class="form-control" v-model="keywords" v-focus>
    //自定义全局指令 参数一为指令名称,参数二是一个对象，身上有一些指令相关的函数，这些函数可以在特定阶段执行相关操作
    //定义的时候不用加v- z
    Vue.directive('focus',{
    	bind:function(el){ //每当指令绑定到元素上的时候，会立即执行bind函数一次
                           //注意 在每个函数中第一个参数永远是el,表示被绑定了指令的那个元素，这个el参数是一个原生的js对象
            // el.focus()
          },
       inserted:function(el){el.focus()},  //表示元素插入到dom中的时候回执行inserted函数，只执行一次
       updated(el) {            //当VNode更新的时候执行update,可能触发多次
                },
            })
    ```

    ```javascript
    <input type="text" class="form-control" v-model="keywords" v-focus v-color="'blue'">
    
    Vue.directive('color',{
                //样式只要通过指令绑定给了元素，不管元素有没有被插入到页面中去，该元素就已经拥有了内联样式
        		//binding是钩子函数的参数
                bind:function(el,binding){
                    el.style.color=binding.value
                },
            })
    ```

    ```javascript
  //当只使用bind和update函数的时候，自定义指令函数可写成：
    directives:{
        font:function(el,binding){
            el.style.fontsize=binding.value
        }
    }
    ```
  
    
  
  + **binding** 钩子函数参数（常用name,value和express）
  
    + `name`：指令名，不包括 `v-` 前缀。    
    + `value`：指令的绑定值，例如：`v-my-directive="1 + 1"`中，绑定值为 `2`。
    + `oldValue`：指令绑定的前一个值，仅在 `update` 和 `componentUpdated` 钩子中可用。无论值是否改变都可用。
    + `expression`：字符串形式的指令表达式。例如 `v-my-directive="1 + 1"` 中，表达式为 `"1 + 1"`。
    + `arg`：传给指令的参数，可选。例如 `v-my-directive:foo`中，参数为 `"foo"`。
    + `modifiers`：一个包含修饰符的对象。例如：`v-my-directive.foo.bar` 中，修饰符对象为 `{ foo: true, bar: true }`。
  
  + 和JS行为有关的操作，最好在inserted中操作，防止JS行为失效
  
  + 和样式有关的操作，一般都可以在bind中执行（执行时间更早）

### Vue的生命周期图

![lifecycle](C:\Users\HP\Documents\Study_notes\学习笔记\lifecycle.png)

### 网络请求

+ get和post

  + ```html
    <head>
        <meta charset="utf-8">
        <script src="https://cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.js"></script>
        <!--vue-resource依赖Vue-->
        <script src="https://cdn.bootcss.com/vue-resource/1.5.1/vue-resource.min.js"></script>
        <!--更推荐的请求方式-->
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    
    </head>
    
    <body>
    
        <div id="app">
            <input type="button" value="get请求" @click="getinfo">
            <input type="button" value="axios-get请求" @click="getinfo2">
            <input type="button" value="post请求" @click="postinfo">
            <input type="button" value="axious-post请求" @click="postinfo2">
        </div>
        <script>
            let obj = { "zUserPO": { "id": 1 } }
            //启用了根路径的情况下，Vue-resource访问使用相对路径
            Vue.http.options.root='http://localhost:8080/checksys'
    
            var vm = new Vue({
                el: "#app",
                data: {
                },
                methods: {
                    getinfo() {
                        this.$http.get('checksys/daliycontent/gettodayContent').then(
                            function (data) {
                                console.log(data)
                            }
                        )
                    },
                    postinfo() {
                        this.$http.post('checksys/check/getThisWeekCheckDay', obj).then(
                            function (data) {
                                console.log(data)
                            }
                        )
                    },
                    getinfo2() {
                        axios.get('http://localhost:8080/checksys/daliycontent/gettodayContent').then(
                            function (data) {
                                console.log(data)
                            }
                        )
                    },
                    postinfo2() {
                        axios.post('http://localhost:8080/check/getThisWeekCheckDay', obj).then(
                            function (data) {
                                console.log(data.data)
                            }
                        )
                    }
                }
            })
        </script>
    </body>
    </html>
    ```

  + 