package com.acmebank.accountmanager.exception.response;

import com.acmebank.accountmanager.utils.response.Response;

public interface EnquiryErrorResponse {
    Response ACC0001 = new Response("ACC0001", "Account Info Enquiry Failed");
    Response ACC0002 = new Response("ACC0002", "Account not found");
}
