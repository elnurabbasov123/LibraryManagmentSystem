package main;

import service.impl.IMenuService;
import service.inter.MenuService;

public class Main {
    public static void main(String[] args) {
        MenuService menuService=new IMenuService();

        menuService.menu();
    }
}
