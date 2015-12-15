# cmp
cloud foundary operation system .based on a big data structure .
工程总体目标为搭建一个分布式的运维系统。搭建过程分为如下几个阶段：
1.搭建一套单机的日志采集环境，该环境中包含的功能模块有：日志生产模块（先自己写一个模拟桩），日志采集模块>（flume），消息中间件（kafka），实时日志处理模块（延时1s内，storm），日志存储模块（hbase和elastic），低延时日志处理模块（spark）。 
2.写程序从hbase和elastic里面读取相应的日志。
3.完成分布式环境的安装。
4.将各个组件转移到docker上面去。
5.完成跟踪任务下发的功能。                                                                               
~                        
