package service.impl;

import model.Admin;
import repository.impl.IAdminRepository;
import repository.inter.AdminRepository;
import service.inter.AdminService;

import java.util.List;

public class IAdminService implements AdminService {
    AdminRepository adminRepository=new IAdminRepository();

    @Override
    public List<Admin> getAdmins() {
       return adminRepository.getAdmins();
    }
}
