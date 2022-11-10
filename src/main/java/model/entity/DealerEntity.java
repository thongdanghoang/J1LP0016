package model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dealer", schema = "ThCLKAjtjI", catalog = "")
public class DealerEntity {
    @Id
    @Column(name = "dealer_id", nullable = false, length = 64)
    private String id;
    @Basic
    @Column(name = "dealer_name", nullable = false, length = 64)
    private String name;
    @Basic
    @Column(name = "dealer_address", nullable = false, length = 64)
    private String address;
    @Basic
    @Column(name = "dealer_phone", nullable = false, length = 64)
    private String phone;
    @Basic
    @Column(name = "dealer_continuing", nullable = false)
    private boolean continuing;

    public DealerEntity() {
    }

    public DealerEntity(String id, String name, String address, String phone, boolean continuing) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.continuing = continuing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean consinuing) {
        this.continuing = consinuing;
    }

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
