package com.company;

public enum Member_Level {

    NORMAL("Thành Viên"), GOLD("Thành Viên Vàng"), PLATINUM("Thành Viên Bạch Kim"), DIAMOND("Thành Viên Kim Cương");


    private String level;

    Member_Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

}
