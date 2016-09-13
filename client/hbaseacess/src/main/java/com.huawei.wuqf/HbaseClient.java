package com.huawei.wuqf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqf on 16-9-13.
 */
public class HbaseClient implements IHbaseClient {

    public static Configuration configuration;

    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.properity.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.1.107");
    }

    private static HbaseClient client = new HbaseClient();
    static final String tableName = "wuqf";

    static final String rowKey1 = "rowKey1";
    static final String rowKey2 = "rowKey2";
    static final String[] rowKeys = {"rowKey1", "rowKey2", "rowKey3"};

    static String column1 = "column1";
    static String column2 = "column2";
    static String column3 = "column3";

//    static String column1 = "columnFamily1:column1";
//    static String column2 = "columnFamily1:column2";
//    static String column3 = "columnFamily1:column3";

    static String value1 = "value1";
    static String value2 = "value2";
    static String value3 = "value3";

    public static void main(String[] args) throws IOException {
        HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
        client.dropTable(hBaseAdmin, tableName);
        client.createTable(hBaseAdmin, tableName);
        client.insertData(tableName);
        client.queryAll(tableName);
        client.queryByRowKey(tableName);
        client.queryByColumnValue(tableName);
        client.queryByFilters(tableName);
        client.deleteByCondition(tableName, rowKey1);
        client.deleteRow(tableName, rowKey2);
        //client.dropTable(hBaseAdmin,tableName);
    }

    public void createTable(HBaseAdmin hBaseAdmin, final String tableName) throws IOException {


        boolean isExsits = hBaseAdmin.tableExists(tableName);
        if (isExsits) {
            hBaseAdmin.disableTable(tableName);
            hBaseAdmin.deleteTable(tableName);
        }


        HTableDescriptor descriptor = new HTableDescriptor(tableName);
        descriptor.addFamily(new HColumnDescriptor(column1));
        descriptor.addFamily(new HColumnDescriptor(column2));
        descriptor.addFamily(new HColumnDescriptor(column3));
        hBaseAdmin.createTable(descriptor);
    }

    public void insertData(String tableName) throws IOException {
        HConnection hconnection = HConnectionManager.createConnection(configuration);
        HTableInterface hTableInterface = hconnection.getTable(tableName);

        for (String rowKey : rowKeys) {
            Put put = new Put(rowKey.getBytes());
            put.addColumn(column1.getBytes(), null, value1.getBytes());
            put.addColumn(column2.getBytes(), null, value2.getBytes());
            put.addColumn(column3.getBytes(), null, value3.getBytes());
            hTableInterface.put(put);
        }


        hconnection.close();
    }

    public void dropTable(HBaseAdmin hBaseAdmin, String tableName) throws IOException {
        boolean isExsits = hBaseAdmin.tableExists(tableName);
        if (isExsits) {
            hBaseAdmin.disableTable(tableName);
            hBaseAdmin.deleteTable(tableName);
        }
    }

    public void deleteRow(String tablename, String rowkey) throws IOException {
        HConnection hconnection = HConnectionManager.createConnection(configuration);
        HTableInterface hTableInterface = hconnection.getTable(tableName);
        List list = new ArrayList();
        Delete d1 = new Delete(rowKey1.getBytes());
        list.add(d1);
        hTableInterface.delete(list);
        hconnection.close();
    }

    public void deleteByCondition(String tablename, String rowkey) throws IOException {

    }

    public void queryAll(String tableName) throws IOException {
        HConnection hConnection = HConnectionManager.createConnection(configuration);
        HTableInterface hTableInterface = hConnection.getTable(tableName);
        ResultScanner resultScanner = hTableInterface.getScanner(new Scan());
        for (Result result : resultScanner) {
            System.out.println(new String(result.getRow()));
            for (KeyValue keyValue : result.raw()) {
                System.out.println("row is " + new String(result.getRow()) + " . column is " + new String(keyValue.getFamily()) + " . value is " + new String(keyValue.getValue()));
            }
        }
        resultScanner.close();
    }

    public void queryByRowKey(String tableName) throws IOException {
        System.out.println("----------------queryByRowKey------------------");
        HConnection hConnection = HConnectionManager.createConnection(configuration);
        HTableInterface hTableInterface = hConnection.getTable(tableName);
        Get get = new Get(rowKey1.getBytes());
        Result result = hTableInterface.get(get);
        System.out.println();
        for (KeyValue keyValue : result.raw()) {
            System.out.println("row is " + new String(result.getRow()) + " . column is " + new String(keyValue.getFamily()) + " . value is " + new String(keyValue.getValue()));
        }
    }

    public void queryByColumnValue(String tableName) throws IOException {
        System.out.println("----------------queryByColumnValue------------------");
        HConnection hConnection = HConnectionManager.createConnection(configuration);
        HTableInterface hTableInterface = hConnection.getTable(tableName);
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes(column1), null, CompareFilter.CompareOp.EQUAL, Bytes.toBytes(value1));
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner resultScanner = hTableInterface.getScanner(scan);

        for (Result result : resultScanner) {
            System.out.println(new String(result.getRow()));
            for (KeyValue keyValue : result.raw()) {
                System.out.println("row is " + new String(result.getRow()) + " . column is " + new String(keyValue.getFamily()) + " . value is " + new String(keyValue.getValue()));
            }
        }
        resultScanner.close();
    }

    public void queryByFilters(String tableName) throws IOException{
        System.out.println("----------------queryByFilters------------------");
        HConnection hConnection = HConnectionManager.createConnection(configuration);
        HTableInterface hTableInterface = hConnection.getTable(tableName);
FilterList filterList=createFilterList();

        Scan scan = new Scan();
        scan.setFilter(filterList);
        ResultScanner resultScanner = hTableInterface.getScanner(scan);

        for (Result result : resultScanner) {
            System.out.println(new String(result.getRow()));
            for (KeyValue keyValue : result.raw()) {
                System.out.println("row is " + new String(result.getRow()) + " . column is " + new String(keyValue.getFamily()) + " . value is " + new String(keyValue.getValue()));
            }
        }
        resultScanner.close();
    }
    private FilterList createFilterList(){
        List<Filter> filters = new ArrayList<Filter>();

        Filter filter1 = new SingleColumnValueFilter(Bytes
                .toBytes(column1), null, CompareFilter.CompareOp.EQUAL, Bytes
                .toBytes(value1));
        filters.add(filter1);

        Filter filter2 = new SingleColumnValueFilter(Bytes
                .toBytes(column2), null, CompareFilter.CompareOp.EQUAL, Bytes
                .toBytes(value2));
        filters.add(filter2);

        Filter filter3 = new SingleColumnValueFilter(Bytes
                .toBytes(column3), null, CompareFilter.CompareOp.EQUAL, Bytes
                .toBytes(value3));
        boolean add;
        if (filters.add(filter3)) add = true;
        else add = false;

        FilterList filterList1 = new FilterList(filters);
        return filterList1;
    }
}
