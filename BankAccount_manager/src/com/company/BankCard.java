package com.company;

public enum BankCard {

    INLAND_CARD("Thẻ Nội Địa"), INTERNATIONAL_CARD("Thẻ Quốc Tế"),
    DEBIT_CARD("Thẻ Ghi Nợ"), CREDIT_CARD("Thẻ Tín Dụng"), PREPAID_CARD("Thẻ Trả Trước");

    private String bankCard;

    BankCard(String card) {
        bankCard = card;
    }

    public String getBankCard() {
        return bankCard;
    }

}
