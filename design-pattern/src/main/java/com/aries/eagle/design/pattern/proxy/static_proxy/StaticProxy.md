# 静态代理

## 1.原理: 
每一个真实类对应一个代理类,当请求者请求真实类时,如果代理类中存在结果就直接返回给请求者;
如果代理中没有结果,那么需要再去访问真实类,同时将结果同步到代理类中

## 2.角色:
Printable:代理类与真实类需要实现共同的接口,抽取出真实类的特点

Printer:真实类

PrinterProxy:代理类

Main:作为一个请求者

## 3.优点:

1.协调调用者与被调用者,降低耦合度

2.作为客户端对象与目标对象的中介,可以有效地保护目标对象

## 4.缺点:

1.在客户端与目标对象之间增加了代理对象,处理请求的速度可能会变慢

2.如果代理的实现复杂,可能会增加系统实现的复杂性

3.如果想要为多个类进行代理,需要创建多个代理类,维护难度加大 