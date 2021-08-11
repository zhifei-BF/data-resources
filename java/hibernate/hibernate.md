# Hibernate

注意：hibernate在获取完持久化对象之后，将此对象的主键设置为null，再插入数据库时，会报错。最好的办法就是，让此对象实现Cloneable接口，重写clone方法。然后对象进行clone，主键设置为null，再存入即可。