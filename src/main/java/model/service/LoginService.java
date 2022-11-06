package model.service;

import jakarta.persistence.Persistence;
import model.dto.AccountDTO;
import model.entity.AccountEntity;
import model.mapping.AccountMapper;
import model.mapping.IMapper;
import model.repository.AccountRepository;
import model.repository.IRepository;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService {
    private final IMapper<AccountEntity, AccountDTO> mapper = new AccountMapper();

    private final IRepository<String, AccountEntity> repository =
            new AccountRepository(Persistence.createEntityManagerFactory("default"));

    public AccountDTO login(String username, String passwd) throws Exception {
        try {
            AccountEntity found = repository.findById(username);
            if (!BCrypt.checkpw(passwd, found.getPasswd())) {
                throw new Exception(username + "'s password is incorrect.");
            }
            return mapper.toDTO(found);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
