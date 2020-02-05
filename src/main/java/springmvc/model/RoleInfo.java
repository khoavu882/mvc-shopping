package springmvc.model;

import springmvc.entities.Role;

public class RoleInfo {

  private Integer id;
  private String roleName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public RoleInfo(Role role) {
    this.id = role.getId();
    this.roleName = role.getRoleName();
  }
}
