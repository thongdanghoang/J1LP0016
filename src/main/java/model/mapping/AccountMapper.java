package model.mapping;

import model.dto.AccountDTO;
import model.entity.AccountEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thongdanghoang
 */
public class AccountMapper implements IMapper<AccountEntity, AccountDTO> {
    @Override
    public AccountEntity toEntity(AccountDTO accountDTO) {
        return new AccountEntity(accountDTO.getUsername(), accountDTO.getPasswd(), accountDTO.getRole());
    }

    @Override
    public List<AccountEntity> toEntities(List<AccountDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public AccountDTO toDTO(AccountEntity entity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(entity.getUsername());
        accountDTO.setRole(entity.getRole());
//        accountDTO.setPasswd(entity.getPassword());
        accountDTO.setPasswd("encrypted");
        return accountDTO;
    }

    @Override
    public List<AccountDTO> toDTOs(List<AccountEntity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
