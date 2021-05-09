//导入模块是require 就类似于import java.io
const http = require('http');

// 1: 创建一个httpserver服务
http.createServer(function(request,response){    
// 浏览器怎么认识hello server!!!    
//这句话的含义是：告诉浏览器将以text-html去解析hello server 这段数据。  
response.writeHead(200,{'Content-type':'text/html'});   

// 给浏览器输出内容    
response.end("<strong>hello server!!!</strong>");
}).listen(8888);
console.log("你启动的服务是：http://localhpst:8888以启动成功!!");
// 2: 监听一端口8888
// 3: 启动运行服务 node httpserver.js
// 4: 在浏览器访问http://localhost:8888