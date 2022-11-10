package model.service;

import jakarta.persistence.Persistence;
import model.dto.DealerDTO;
import model.entity.DealerEntity;
import model.mapping.DealerMapper;
import model.mapping.IMapper;
import model.repository.DealerRepository;
import model.repository.IRepository;

import java.util.List;
import java.util.Optional;

public class DealerService {

    private IRepository<String, DealerEntity> repository = new DealerRepository(Persistence.createEntityManagerFactory("default"));
    private IMapper<DealerEntity, DealerDTO> mapping = new DealerMapper();

    public void create(DealerDTO dealer) throws Exception {
            repository.create(mapping.toEntity(dealer));
    }

    public void update(DealerDTO dealer) throws Exception {
            repository.edit(mapping.toEntity(dealer));
    }

    public void delete(String id) throws Exception {
            repository.destroy(id);
    }

    public DealerDTO find(String id) throws Exception {
        return mapping.toDTO(repository.findById(id));
    }

    public List<DealerDTO> findAll() throws Exception {
        return mapping.toDTOs(repository.findAll());
    }

    public static void main(String[] args) {
        try {
            new DealerService().findAll().forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
