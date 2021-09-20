package com.test.enums;

public enum BidNameEnum {

    TYPE1("1", "标段1"),
    TYPE2("2", "标段2"),
    TYPE3("3", "标段3");

    private String type;
    private String desc;

    BidNameEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static String getValue(String type) {
        BidNameEnum[] bidEnums = values();
        for (BidNameEnum bidEnum : bidEnums) {
            if (bidEnum.type().equals(type)) {
                return bidEnum.desc();
            }
        }
        return null;
    }

    public static String getType(String desc) {
        BidNameEnum[] bidEnums = values();
        for (BidNameEnum bidEnum : bidEnums) {
            if (bidEnum.desc().equals(desc)) {
                return bidEnum.type();
            }
        }
        return null;
    }

    private String type() {
        return this.type;
    }

    private String desc() {
        return this.desc;
    }
}
