package com.joutvhu.training.rest.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RouteConstants {
    public final String URL_BASE = "/";

    public final String URL_AUTH = "/auth";
    public final String URL_LOGIN = "/login";

    public final String URL_PRODUCT = "/api/product";
    public final String URL_PRODUCT_ID = "/{productId}";

    public final String URL_TEST = "/api/test";
    public final String URL_LIFECYCLE = "/lifecycle";
    public final String URL_TEST_AUTH = "/test-auth";
}
