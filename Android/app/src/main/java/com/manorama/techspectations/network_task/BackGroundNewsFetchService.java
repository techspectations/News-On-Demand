package com.manorama.techspectations.network_task;

/**
 * Created by Godwin Joseph on 11-11-2016 20:50 for Techspectations application.
 */

public class BackGroundNewsFetchService {
    private static BackGroundNewsFetchService service = new BackGroundNewsFetchService();

    public static BackGroundNewsFetchService getInstance() {
        return service;
    }

    private BackGroundNewsFetchService() {
    }
}
