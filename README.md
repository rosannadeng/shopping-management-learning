# shopping-management-learning  
记录学习[shopping-management-system](https://github.com/zhanglei-workspace/shopping-management-system)项目时遇到的问题和涉及的知识点  



## 0.Java管理系统  
### 错误处理  
#### DbConn Class getConn Method 报错  
  - Driver Name不符合，我使用的是MySQL 8.0以上版本，作者使用的是Oracle因此需要把Driver Name改成com.mysql.cj.jdbc.Driver(若使用的是MySQL 8.0以下版本需要改成com.mysql.jdbc.Driver) ; 
  - URL使用不正确作者使用的是"jdbc:oracle:thin:@localhost:1521:orcl"，应该改为"jdbc:mysql://localhost:端口号/数据表连接名称?useSSL=false&serverTimezone=Asia/Shanghai"。
#### 配置正确始终无法连接  
  - debug后发现driver驱动不成功，缺少[mysql-connector jar包](https://dev.mysql.com/downloads/connector/j/)
  - Project Structure-> Modules -> add jar

### 知识点  
#### JDBC  
  - JDBC操作步骤
  - Driver
  - Result Set
  - PreparedStatement
  - Connection
  - ExecuteQuery vs ExecuteUpdate  
  - java.sql.SQLException异常处理  
#### MySQL  
  - 增删查改语句使用  
  
