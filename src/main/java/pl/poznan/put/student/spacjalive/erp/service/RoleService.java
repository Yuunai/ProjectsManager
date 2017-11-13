package pl.poznan.put.student.spacjalive.erp.service;

import pl.poznan.put.student.spacjalive.erp.entity.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getRoles();

    public void saveRole(Role role);

    public void deleteRole(int id);

    public Role getRole(int id);
}
