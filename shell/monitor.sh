#!/bin/bash -x


function loadZookeeper()
{
	serviceNum=`ps -ef |grep java |grep zookeeper |wc -l`
	if [ $serviceNum -lt 1 ];then
		/opt/bigdata/zookeepers/zkServer.sh stop
		/opt/bigdata/zookeepers/zkServer.sh start
	fi
}

function loadES()
{
	serviceNum=`ps -ef |grep java |grep elasticsearch |wc -l`
	echo $serviceNum
	if [ $serviceNum -lt 1 ];then
		echo '/opt/bigdata/elasticsearch-1.7.1/bin/elasticsearch &'
		/opt/bigdata/elasticsearch-1.7.1/bin/elasticsearch &
	fi
}

function loadKafka()
{
	serviceNum=`ps -ef |grep java |grep kafka |wc -l`
	if [ $serviceNum -lt 2 ];then
		nohup /opt/bigdata/kafka/kafka_2.11-0.8.2.1/bin/kafka-server-start.sh /opt/bigdata/kafka/kafka_2.11-0.8.2.1/config/server.properties &
	fi
}

function loadHbase()
{
	serviceNum=`ps -ef |grep java |grep hbase |wc -l`
	if [ $serviceNum -lt 1 ];then
		/opt/bigdata/hbase-1.1.2/bin/stop-hbase.sh ;
		/opt/bigdata/hbase-1.1.2/bin/start-hbase.sh
	fi
}

function loadTomcat()
{
	serviceNum=`ps -ef |grep java |grep apache-tomcat-8.0 |wc -l`
	if [ $serviceNum -lt 1 ];then
		/workspace/bin/apache-tomcat-8.0.15/bin/shutdown.sh;
		/workspace/bin/apache-tomcat-8.0.15/bin/startup.sh;
	fi
}

function loadNexus()
{
	serviceNum=`ps -ef |grep java |grep nexus |wc -l`
	if [ $serviceNum -lt 1 ];then
		/workspace/bin/nexus/nexus-2.10.0-02/bin/nexus start
	fi
}

function loadSuversion()
{
	serviceNum=`lsof -i:8000 |wc -l`
	if [ $serviceNum -lt 1 ];then
		svnserve -d -r /workspace/bin/svn
	fi
}
function loadEnvirment()
{
	export JAVA_HOME=/opt/jdk1.8.0_25
	export JRE_HOME=${JAVA_HOME}/jre
	export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
	export PATH=${JAVA_HOME}/bin:$PATH
}

function loadFlume()
{
	serviceNum=`ps -ef |grep flume |wc -l`
	if [ $serviceNum -lt 2 ];then
		nohup /opt/bigdata/flume/apache-flume-1.6.0-bin/bin/flume-ng agent --conf conf --conf-file conf/flume-conf.properties --name a1 &
	fi
}
currentTime=`date +%Y%m%d%H%M%s`
loadEnvirment
loadZookeeper
loadFlume
loadKafka
#loadES
#loadHbase
#loadTomcat
#loadNexus
#echo $currentTime
