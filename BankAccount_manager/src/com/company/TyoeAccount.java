package com.company;

public enum TyoeAccount {

    TT("Thanh Toán"), TK("Tiết Kiệm"), TD("Tín Dụng"), VV("Vay Vốn");

   private String typeAc;

    TyoeAccount(String typeAc) {
        this.typeAc = typeAc;
    }

    public String getTypeAc() {
        return typeAc;
    }
}
