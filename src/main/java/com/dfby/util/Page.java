package com.dfby.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;


@Data
@AllArgsConstructor
public class Page {
    private int pageNo;//当前页码
    private int pageSize;//每页多少记录
    private int rowCount;//总记录数
    private int pageCount;//总页数
    private List pageList;
    private boolean firstPage;
    private boolean lastPage;

    public Page(){
        this.pageNo=1;
        this.pageSize=5;
    }
//    public Page(int pageSize){
//        this.pageNo=1;
//        this.pageSize=pageSize;
//    }

    public int getPageCount(){//设置获取总页数
        this.pageCount=rowCount/pageSize;
        if(rowCount%pageSize!=0){
            this.pageCount++;
        }
        return this.pageCount;
    }

    public void setPageNo(int pageNo){//设置页码
        this.pageNo=pageNo;
    }

    public int getPageNo(){//获取页码
        if(pageNo>getPageCount()){
            pageNo=pageCount;
        }
        if(pageNo<1){
            pageNo=1;
        }
return pageNo;
    }


    public boolean isFirstPage(){
        return  this.pageNo==1?true:false;
    }
    public boolean isLastPage(){
        return this.pageNo>=this.pageCount?true:false;
    }


}
