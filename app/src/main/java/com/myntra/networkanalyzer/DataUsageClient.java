package com.myntra.networkanalyzer;

import java.util.ArrayList;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */
public interface DataUsageClient {

    @POST("/storeHistoryData")
    void postHistoricalDataUsage(@Body ArrayList<DataUsage> dataUsage,retrofit.Callback<ArrayList<DataUsage>> cb);
}
