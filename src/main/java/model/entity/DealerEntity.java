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
@Table(name = "dealer", schema = "dbo", catalog = "J1LP0016")
public class DealerEntity {
    @ToString.Include
    @Id
    @Column(name = "dealer_id", nullable = false, length = 4)
    private String id;
    @ToString.Include
    @Column(name = "dealer_name", nullable = false, length = 70)
    private String name;
    @ToString.Include
    @Column(name = "dealer_address", nullable = false)
    private String address;
    @ToString.Include
    @Column(name = "dealer_phone", nullable = false, length = 15)
    private String phone;
    @ToString.Include
    @Column(name = "dealer_continuing", nullable = false)
    private boolean continuing;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DealerEntity that = (DealerEntity) o;

        if (continuing != that.continuing) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (continuing ? 1 : 0);
        return result;
    }

}
