package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.AccountEntity;

import java.io.Serializable;

/**
 * A DTO for the {@link AccountEntity} entity
 *
 * @author thongdanghoang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {
    private String username;
    private String passwd;
    private String role;
}