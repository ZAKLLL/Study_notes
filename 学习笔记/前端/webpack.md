安装webpack：

+ ```
  npm install webpack webpack-cli --save -dev
  ```

打包：

+ ``` 
  webpack --config webpack.config.js
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



### webpack 打包

+ 引入package.json
  
+ `npm init -y`
  
+ 项目结构目录

+ ![1557977465738](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1557977465738.png)

+ 将src下的main.js 打包到dist中的bundle.js

  + ` webpack ./src/main.js ./dist/budle.js`                                                                                
  
+ 开发环境中引入内存中实时更新的bundle.js

  + <script src="/bundle.js"></script>

+ webpack.config.js:

- ```javascript
  const path = require('path')
  const webpack = require('webpack')
  //导入在内存中生成html页面的插件
  const htmlWebpackPlugin=require('html-webpack-plugin')
  
  module.exports = {
      entry: path.join(__dirname, './src/main.js'),//表示要使用webpack打包的文件
      output: {
          path: path.join(__dirname, './dist'), //指定打包好的文件，输出的目的目录
          filename: 'bundle.js' //指定的输出的文件名称
      },
      //下面为开启热更新的第二种方法，不推荐使用
      devServer: {
          //webpack-dev-server --open --port 3000 --contentBase src --hot"
          open: true, //自动打开浏览器
          port: 3000, //配置node启动端口
          contentBase: 'src', //指定托管根目录
          hot: true //启用热更新的第一步
      },
      plugins: [ //配置插件的节点
          new webpack.HotModuleReplacementPlugin(),  //new 一个热更新的模块对象，这是热更新的第三步
          new htmlWebpackPlugin({
              template:path.join(__dirname,'./src/index.html'), //指定模板页面。
              filename:'index.html' //指定生成的页面的名称
          })
      ],
      moudle:{
          rules:[
              //调用规则从右到左
              {test:/\.css$/,use: ['style-loader','css-loader']} // 配置处理.css文件的第三方loader处理规则
          ]
      }
  }
  
  ```
  
- 启动服务器

  - `npm run dev`

+ 安装自动打包编译工具 `webpack-dev-server`，在package.json中配置dev

  + `npm i webpack-dev-server -D`

  + open是指npm run dev时打开，端口号为3000 默认文件目录为src 端口号为3000(JSON不能写备注)
  
  + ```json
    "scripts": {
        "dev": "webpack-dev-server --open --port 3000 --contentBase src --hot"
      }
    ```

+ 安装在内存中生成html的插件(无需在html页面中引入内存中的bundle.js)

  + `npm i html-webpack-plugin -D`

+ webpack处理css样式以及url，babel

  + 安装loader`npm i style-loader css-loader -D url-loader`
  
  + 高级语法转成低级语法：
  
     + `npm i babel-preset-env babel-preset-stage-0 -D` 
     + `npm i babel-core babel-loader babel-plugin-transform-runtiom -D`
     + 在根目录中中建立.babelrc的Babel的配置文件，属于**json**格式
     + ```json
        {
            "presets":["env","stage-0"],
        	 "plugins":["transform-runtime"]
        }
       ```
     + 在package.config.js中新增module节点
  
  + ```json
     moudle:{
            rules:[
                //调用规则从右到左
                {test:/\.css$/,use: ['style-loader','css-loader']}, // 配置处理.css文件的第三方loader处理规则
                {test:/	\.(jpg|png|gif|bmp|jpeg)$/,use: 'url-loader'}, //处理图片路径的loader
                //配置bebel转换高级语法
                {test:/\.js$/,use:'babel-loader',exclude:/node_modules/}
            ]
       }
     ```

### Webpack 导入Vue

+ 导入完整的Vue包(或者在webpack.config.js中配置新的节点)

  + `npm i vue -D`

  + main.js中：

  + ```js
    import Vue from '../node_modules/vue/dist/vue'
    ```

  + webpack.config.js中：

  + ```javascript
    resolve:{
        alias:{
            "vue$":"vue/dist/vue.js"  //自动寻找的node_moudles下的vue包
        }
    }
    ```

  + 导入Vue组件.vue格式 

    + `import  login from "./login.vue" `

  + 配置vue相关loader

    + `npm i vue-loader vue-template-compiler -D`

+ ![1558076457023](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558076457023.png)