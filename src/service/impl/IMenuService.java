package service.impl;

import helper.menuHelper.AdminMenuHelper;
import helper.menuHelper.CustomerMenuHelper;
import helper.menuHelper.MenuHelper;
import helper.serviceHelper.MenuServiceHelper;
import model.Admin;
import service.inter.MenuService;

public class IMenuService implements MenuService {
    MenuServiceHelper menuServiceHelper = new MenuServiceHelper();
    @Override
    public void menu() {
        menuServiceHelper.menu();
    }
}
