a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure r1
a1.sources.r1.type = org.apache.flume.source.kafka.KafkaSource
a1.sources.r1.zookeeperConnect = localhost:2181
a1.sources.r1.topic = test
a1.sources.r1.batchSize = 100
#a1.sources.r1.groupId = flume
a1.sources.r1.kafka.consumer.timeout.ms = 100
#a1.sources.r1.threads = 10

# Describe k1
a1.sinks.k1.type = elasticsearch
a1.sinks.k1.hostNames = 192.168.1.103:9300,192.168.1.104:9300
a1.sinks.k1.indexName = foo_index
a1.sinks.k1.indexType = bar_type
a1.sinks.k1.clusterName = elasticsearch
a1.sinks.k1.batchSize = 500
a1.sinks.k1.ttl = 5d
a1.sinks.k1.serializer = org.apache.flume.sink.elasticsearch.ElasticSearchDynamicSerializer
  
# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 10000
a1.channels.c1.transactionCapacity = 1000

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
