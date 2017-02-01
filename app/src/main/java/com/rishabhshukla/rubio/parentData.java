package com.rishabhshukla.rubio;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by rishabhshukla on 29/01/17.
 */

public class parentData implements ParentObject {
    private List<Object> childrenList;
    private String data="sdggsdsdsgdsgdsggs";

    public List<Object> getChildrenList() {
        return childrenList;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public List<Object> getChildObjectList() {
        return childrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        childrenList = list;
    }

    public String getData() {
        return data;
    }
}
