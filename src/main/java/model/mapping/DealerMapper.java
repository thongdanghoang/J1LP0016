package model.mapping;

import model.dto.DealerDTO;
import model.entity.DealerEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thongdanghoang
 */
public class DealerMapper implements IMapper<DealerEntity, DealerDTO> {
    @Override
    public DealerEntity toEntity(DealerDTO dto) {
        return new DealerEntity(dto.getID(), dto.getName(), dto.getAddress(), dto.getPhone(), dto.isContinuing());
    }

    @Override
    public List<DealerEntity> toEntities(List<DealerDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public DealerDTO toDTO(DealerEntity entity) {
        DealerDTO dealerDTO = new DealerDTO();
        dealerDTO.setID(entity.getId());
        dealerDTO.setName(entity.getName());
        dealerDTO.setAddress(entity.getAddress());
        dealerDTO.setPhone(entity.getPhone());
        dealerDTO.setContinuing(entity.isContinuing());
        return dealerDTO;
    }

    @Override
    public List<DealerDTO> toDTOs(List<DealerEntity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
