package com.thinkgem.jeesite.modules.settlement.entity;

import java.io.Serializable;

/**
 * 接口返回数据实体类。
 * 
 * @author chuaiqing.
 *
 */
public class ResponseData implements Serializable {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = 7166145206600678380L;
    
    private boolean isError;
    private int errorType;
    private String errorMessage;
    private Object resultData;
    
    /**
     * 
     * @return
     */
    public boolean isError() {
        return isError;
    }
    /**
     * 
     * @param isError
     */
    public void setError(boolean isError) {
        this.isError = isError;
    }
    
    /**
     * 
     * @return
     */
    public int getErrorType() {
        return errorType;
    }
    /**
     * 
     * @param errorType
     */
    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
    
    /**
     * 
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * 
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    /**
     * 
     * @return
     */
    public Object getResultData() {
        return resultData;
    }
    /**
     * 
     * @param resultData
     */
    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

}
