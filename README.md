# shopping-management-learning
记录学习shopping-management-system项目时遇到的问题和涉及的知识点
https://github.com/zhanglei-workspace/shopping-management-system


<a name="index"/> 0.Java管理系统
##错误处理
###DbConn Class getConn Method 报错
  -Driver Name不符合 我使用的是MySQL 8.0以上版本，作者使用的是Oracle因此需要把Driver Name改成com.mysql.cj.jdbc.Driver（若使用的是MySQL 8.0以下版本需要改成com.mysql.jdbc.Driver）
  -URL使用不正确作者使用的是"jdbc:oracle:thin:@localhost:1521:orcl"，应该改为"jdbc:mysql://localhost:端口号/数据表连接名称?useSSL=false&serverTimezone=Asia/Shanghai";

##知识点
###JDBC
  -PreparedStatement
  -ExecuteQuery vs ExecuteUpdate
  -java.sql.SQLException异常处理
###MySQL
  -增删查改语句使用
  
