package model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author thongdanghoang
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "account", schema = "dbo", catalog = "J1LP0016")
public class AccountEntity {
    @ToString.Include
    @Id
    @Column(name = "account_username", nullable = false, length = 32)
    private String username;
    @ToString.Include
    @Column(name = "account_passwd", nullable = false, length = 64)
    private String passwd;
    @ToString.Include
    @Column(name = "account_role", nullable = false, length = 32)
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (passwd != null ? !passwd.equals(that.passwd) : that.passwd != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
