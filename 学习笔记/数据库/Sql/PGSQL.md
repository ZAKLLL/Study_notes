## 常用命令

+ 数据库连接: psql -U user -d dbname

+ 切换数据库(mysql 中的use dbname): \c dbname

+ 列举数据库： \l

+ 查看所有的表(show  tables): \dt

+ 查看表结构:  \d tableNam 

+ 查看索引： \di




# 新建用户并且创建只读权限

```sql
CREATE USER <userName> WITH ENCRYPTED PASSWORD 'userpassword';

# 只读权限
alter user <userName> set default_transaction_read_only=on;
# 连接数
alter user <userName> connection limit 30;

# schemaname(通常是public) 模式授权给username
GRANT USAGE ON SCHEMA <schemaname> TO <userName>;

# schemaname(通常是public) 下的所有表 授权给该用户
grant select on all tables in schema <schemaname> to <username>

# 将public 模式 下的某张表 授权给该用户
grant select on <tablename> to <username>
```