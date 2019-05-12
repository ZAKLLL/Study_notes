+ 定义局部变量let（尽量避免使用var)

+ 定义常量const（不可更改，类似于java中的final）

+ 解构

+ map()：将数组按照自定义的方法遍历生成新的数组

  ```javascript
  let arr = ["1", "4", "5"];
  let arr2 = arr.map(a => parseInt(a));
  ```

  

+ reduce（a,b)：数组中的第一二个参数作为a,b参数传入，第一次返回值作为第二次的a参数传入，原有数组中的a,b后的第三个参数作为b传入(类比递归)：

  ```javascript
  let arr = [1, 2, 3, 4, 5, 6, 7];
  let sum = arr.reduce((a, b) => a + b);
  console.log(sum) //output为28	
  ```

+ Promise:

  ```javascript
   let p=new Promise(function (resolve,reject) {
          setTimeout(function () {
              let r = Math.random();
              if (r < 0.5) {
                  resolve("成功数据"+r);
              } else {
                  reject("失败数据"+r);
              }
          }, 1000);
      })
   	//then 表示成功，catch表示失败
      p.then( date  => console.log(date)).catch(date=>console.log(date));
  ```

+ map:<object;object>

+ object:<string,string>

