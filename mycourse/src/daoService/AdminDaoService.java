package daoService;

import entity.Admin;

public interface AdminDaoService {
    //admin is set by programmer, so it cannot be added/register

    public Boolean ifAdminNameExist(String name);
    public Admin findAdminByNameAndPw(String name, String password);
    public int numOfAdmin();
}
