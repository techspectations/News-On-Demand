package com.cloudconnection.utility;

/**
 * Created by Riyas on 11-02-2016.
 */
public class CloudConnectStatus {



    public static  final int STATE_IDLE=0;
    public static  final int STATE_RUNNING=1;
    public static  final int STATE_PRE_RUNNING=2;
    private static CloudConnectStatus cloudConnectStatus ;
    private int apiRunningStatus;

    public static CloudConnectStatus getInstance() {

        if(cloudConnectStatus==null)
            cloudConnectStatus    = new CloudConnectStatus();

        return cloudConnectStatus;
    }

    public int getApiRunningStatus() {
        return apiRunningStatus;
    }

    public void setApiRunningStatus(int apiRunningStatus) {
        this.apiRunningStatus = apiRunningStatus;
    }
}
