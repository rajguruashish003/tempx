package com.tempx.util;

public class ResponseParent {
    public  String message="";
    public  Object errorList;

    public  ResponseParent(){

    }

    public  ResponseParent(String message)
    {
        this.message=message;
    }

    @Override
    public  String toString()
    {
        return  "ResponseParent [message="+message+", errorList="+errorList+"]";
    }
}
