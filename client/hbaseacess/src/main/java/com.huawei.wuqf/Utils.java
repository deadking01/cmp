package com.huawei.wuqf;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

/**
 * Created by wuqf on 16-9-14.
 */
public class Utils {
    public  static void traversalResultScanner(ResultScanner resultScanner){

        for (Result result : resultScanner) {
            System.out.println(new String(result.getRow()));
            for (KeyValue keyValue : result.raw()) {
                System.out.println("row is " + new String(result.getRow()) + " . column family is " + new String(keyValue.getFamily()) + " .column is " + new String(keyValue.getQualifier())+" . value is " + new String(keyValue.getValue()));
            }
        }
    }

    public  static void traversalResult(Result result){
            System.out.println(new String(result.getRow()));
            for (KeyValue keyValue : result.raw()) {
                System.out.println("row is " + new String(result.getRow()) + " . column family is " + new String(keyValue.getFamily()) + " .column is " + new String(keyValue.getQualifier())+" . value is " + new String(keyValue.getValue()));
            }
    }
}
