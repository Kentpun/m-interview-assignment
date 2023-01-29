package com.acmebank.accountmanager.exception.response;

import com.acmebank.accountmanager.utils.response.Response;

public interface TransferErrorResponse {
    Response TXN0001 = new Response("TXN0001", "Transfer Failed");
    Response TXN0002 = new Response("TXN0002", "Invalid Transfer Order");
}
