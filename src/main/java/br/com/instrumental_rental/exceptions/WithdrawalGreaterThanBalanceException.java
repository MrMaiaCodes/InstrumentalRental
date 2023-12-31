package br.com.instrumental_rental.exceptions;

public class WithdrawalGreaterThanBalanceException extends Exception {

    private String code;

    private String message;

    public WithdrawalGreaterThanBalanceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {return code;}
}
