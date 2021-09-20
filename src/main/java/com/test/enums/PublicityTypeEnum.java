package com.test.enums;

public enum PublicityTypeEnum {

    TYPE1("1", "正常"),
    TYPE2("2", "不正常");

    private String type;
    private String desc;

    PublicityTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static String getValue(String type) {
        PublicityTypeEnum[] pubEnums = values();
        for (PublicityTypeEnum pubEnum : pubEnums) {
            if (pubEnum.type().equals(type)) {
                return pubEnum.desc();
            }
        }
        return null;
    }

    public static String getType(String desc) {
        PublicityTypeEnum[] pubEnums = values();
        for (PublicityTypeEnum pubEnum : pubEnums) {
            if (pubEnum.desc().equals(desc)) {
                return pubEnum.type();
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

