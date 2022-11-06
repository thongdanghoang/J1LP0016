package model.service;

import jakarta.persistence.Persistence;
import model.dto.AccountDTO;
import model.entity.AccountEntity;
import model.mapping.AccountMapper;
import model.mapping.IMapper;
import model.repository.AccountRepository;
import model.repository.IRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class AccountService {

    private final IMapper<AccountEntity, AccountDTO> mapper = new AccountMapper();

    private final IRepository<String, AccountEntity> repository =
            new AccountRepository(Persistence.createEntityManagerFactory("default"));

    public void create(AccountDTO account) throws Exception {
        //validate before submit to repository
        //IMPORTANT: ENCRYPT password first
        account.setPasswd(BCrypt.hashpw(account.getPasswd(), BCrypt.gensalt(10)));
        repository.create(mapper.toEntity(account));
    }

    public void update(AccountDTO account) throws Exception {
        //validate before submit to repository
        //IMPORTANT: ENCRYPT password first
        account.setPasswd(BCrypt.hashpw(account.getPasswd(), BCrypt.gensalt(10)));
        repository.edit(mapper.toEntity(account));
    }

    public AccountDTO find(String username) throws Exception {
        AccountEntity found = repository.findById(username);
        return mapper.toDTO(found);
    }

    public List<AccountDTO> findAll() throws Exception {
        return mapper.toDTOs(repository.findAll());
    }


    public void delete(String username) throws Exception {
        repository.destroy(username);
    }

}
