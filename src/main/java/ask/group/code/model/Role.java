package ask.group.code.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTH_ROLE")
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROLE_ID")
    private int roleId;

    @Column(name="role_name")
    private String role;

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
