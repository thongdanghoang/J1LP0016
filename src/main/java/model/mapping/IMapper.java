package model.mapping;

import java.util.List;

/**
 * @author thongdanghoang
 */
public interface IMapper<T, S> {
    T toEntity(S dto);

    List<T> toEntities(List<S> dtos);

    S toDTO(T entity);

    List<S> toDTOs(List<T> entities);
}
