package com.huawei.wuqf.esacess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class User
{
    private String name;

    private int id;

    private int age;



    public String getJsonString(ObjectMapper mapper)
    {
        String js = null;
        try
        {
            js = mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return js;
    }
}
