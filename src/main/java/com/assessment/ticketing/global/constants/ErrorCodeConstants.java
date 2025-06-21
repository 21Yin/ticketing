package com.assessment.ticketing.global.constants;

public class ErrorCodeConstants {
    public static final String ERR_AUTH001 = "ERR_AUTH001"; //Invalid username or password
    public static final String ERR_COM002 = "ERR_COM002"; //Only admin users can perform this action
    public static final String ERR_TICKET_001 = "ERR_TICKET_001"; //ticket not found
    public static final String ERR_TICKET_002 = "ERR_TICKET_002"; //Failed to create ticket.
    public static final String ERR_ACC001 = "ERR_ACC001"; //Account already exist
    public static final String ERR_JWT001 = "ERR_JWT001"; //Expired Jwt
    public static final String ERR_JWT002 = "ERR_JWT002"; //Unsupported Jwt

}
