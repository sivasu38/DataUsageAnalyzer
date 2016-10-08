package com.myntra.networkanalyzer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by c.sivasubramanian on 08/10/16.
 */
public interface DataUsageClient {

    @POST("/storeHistoryData")
    Call<ArrayList<DataUsage>> postHistoricalDataUsage(@Body ArrayList<DataUsage> dataUsage);
}
