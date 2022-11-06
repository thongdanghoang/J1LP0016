package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link model.entity.DealerEntity} entity
 *
 * @author thongdanghoang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerDTO implements Serializable {
    private String ID;
    private String name;
    private String address;
    private String phone;
    private boolean continuing;
}