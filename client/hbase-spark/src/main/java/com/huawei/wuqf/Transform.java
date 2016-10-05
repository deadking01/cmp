package com.huawei.wuqf;

/**
 * Hello world!
 *
 */
public class Transform {
    public static void main(String[] args) {
        JavaSparkContext sc = new JavaSparkContext(master, "hbaseTest",
                System.getenv("SPARK_HOME"), System.getenv("JARS"));

        Configuration conf = HBaseConfiguration.create();
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("cf"));
        scan.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("airName"));

        try {
            String tableName = "flight_wap_order_log";
            conf.set(TableInputFormat.INPUT_TABLE, tableName);
            ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
            String ScanToString = Base64.encodeBytes(proto.toByteArray());
            conf.set(TableInputFormat.SCAN, ScanToString);

            JavaPairRDD<ImmutableBytesWritable, Result> myRDD =
                    sc.newAPIHadoopRDD(conf, TableInputFormat.class,
                            ImmutableBytesWritable.class, Result.class);

            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(myRDD.count());
        }
    }
}
