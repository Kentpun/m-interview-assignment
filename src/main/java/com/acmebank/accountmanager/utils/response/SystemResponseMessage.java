package com.acmebank.accountmanager.utils.response;

public interface SystemResponseMessage {
    Response SYS0000 = new Response("SYS0000", "Success.");
    Response SYS9999 = new Response("SYS9999", "Fail.");
    Response SYS0401 = new Response("SYS0401", "Access Denied.");
    Response PARM400 = new Response("PARM400", "Invalid field validation.");
}
