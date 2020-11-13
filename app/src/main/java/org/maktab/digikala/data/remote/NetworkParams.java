package org.maktab.digikala.data.remote;

import org.maktab.digikala.data.model.Orderby;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {
    public static final String BASE_PATH = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/";
    public static final String CONSUMER_KEY = "ck_4f323512c5bec27b7a4caedfdc7bf9a2ef74f685";
    public static final String CONSUMER_SECRET = "cs_d582cea0735f1536e9f78eb4af3bbb71e8bf5262";
    public static final String ORDER_BY_DATE = "date";
    public static final String ORDER_BY_MOST_RATED = "rating";
    public static final String ORDER_BY_MOST_VIEWED = "popularity";

    public static Map<String, String> BASE_OPTIONS = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};

    public static Map<String, String> getProductsOptions(Orderby orderby) {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.putAll(BASE_OPTIONS);
        switch (orderby){
            case DATE:
                queryOptions.put("orderby", ORDER_BY_DATE);
                break;
            case MOST_RATED:
                queryOptions.put("orderby", ORDER_BY_MOST_RATED);
                break;
            case MOST_VIEWED:
                queryOptions.put("orderby", ORDER_BY_MOST_VIEWED);
                break;
        }
        return queryOptions;
    }

    /*
    public static Map<String, String> getLatestOptions() {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.putAll(BASE_OPTIONS);
        queryOptions.put("order_by", ORDER_BY_DATE);
        return queryOptions;
    }

    public static Map<String, String> getMostRatedOptions() {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.putAll(BASE_OPTIONS);
        queryOptions.put("order_by", ORDER_BY_RATING);
        return queryOptions;
    }

    public static Map<String, String> getMostViewedOptions() {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.putAll(BASE_OPTIONS);
        queryOptions.put("order_by", ORDER_BY_POPULARITY);
        return queryOptions;
    }
*/
}
