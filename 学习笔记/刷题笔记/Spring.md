+ 事务的几种传播特性：
  + PROPAGATION_REQUIRED--支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
  + PROPAGATION_SUPPORTS--支持当前事务，如果当前没有事务，就以非事务方式执行。 
  + PROPAGATION_MANDATORY--支持当前事务，如果当前没有事务，就抛出异常。 
  + PROPAGATION_REQUIRES_NEW--新建事务，如果当前存在事务，把当前事务挂起。 
  + PROPAGATION_NOT_SUPPORTED--以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
  + PROPAGATION_NEVER--以非事务方式执行，如果当前存在事务，则抛出异常。 

