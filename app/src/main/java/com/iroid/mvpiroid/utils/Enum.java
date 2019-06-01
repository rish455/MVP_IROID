package com.iroid.mvpiroid.utils;

import java.io.Serializable;

public final class Enum {
    public enum AccountType implements Serializable {
        INDIVIDUAL, BUSINESS
    }

    public enum PrivilegeCardType implements Serializable {
        GOLD, SILVER, BRONZE
    }

    public enum CardMore {
        BUY, VIEW
    }


}
