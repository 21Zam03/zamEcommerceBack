package com.zam.zamMarket.Enums;

public enum RoleEnum {

    ADMIN("Administrador"),
    CLIENT("Cliente"),
    INVITED("Invitado"),
    DEVELOPER("Desarrollador");

    public final String name;

    RoleEnum(String name) {
        this.name = name;
    }

}
