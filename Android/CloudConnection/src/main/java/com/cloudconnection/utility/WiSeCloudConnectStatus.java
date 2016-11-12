package com.cloudconnection.utility;

/**
 * Created by Mopuri Yasmine on 11-02-2016.
 */
public class WiSeCloudConnectStatus {



    public static  final int STATE_IDLE=0;
    public static  final int STATE_RUNNING=1;
    public static  final int STATE_PRE_RUNNING=2;
    private static WiSeCloudConnectStatus wiSeCloudConnectStatus ;
    private int apiRunningStatus;

    public static WiSeCloudConnectStatus getInstance() {

        if(wiSeCloudConnectStatus==null)
            wiSeCloudConnectStatus    = new WiSeCloudConnectStatus();

        return wiSeCloudConnectStatus;
    }

    public int getApiRunningStatus() {
        return apiRunningStatus;
    }

    public void setApiRunningStatus(int apiRunningStatus) {
        this.apiRunningStatus = apiRunningStatus;
    }




}
