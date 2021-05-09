//1: 导入mysql依赖包,  mysql属于第三方的模块就类似于 java.sql一样的道理
var mysql = require("mysql");
// 1: 创建一个mysql的Connection对象
// 2: 配置数据连接的信息 
var connection =mysql.createConnection({
    host:"127.0.0.1",
    port:3306,
    user:"root",
    password:"123456",
    database:"testdb"
});
// 3：开辟连接
connection.connect();
// 4: 执行curd
connection.query("select * from kss_user",function(error,results,fields){
    // 如果查询出错，直接抛出
    if(error)throw error;
    // 查询成功
    console.log("results = ",results);
});
// 5: 关闭连接
connection.end();
// 最后一步：运行node db.js 查看效果