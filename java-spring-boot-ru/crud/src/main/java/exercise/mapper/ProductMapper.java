package exercise.mapper;

import exercise.dto.*;
import exercise.model.Category;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

// BEGIN
@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {

    @Mapping(target = "category.id", source = "categoryId")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    public abstract ProductDTO map(Product model);

    @Mapping(target = "category", source = "categoryId", qualifiedByName = "getCategoryById")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);

    @Autowired
    CategoryRepository categoryRepository;

    @Named("getCategoryById")
    public Category getCategoryById(Long id) {
        return id != null ? categoryRepository.findById(id).orElse(null) : null;
    }
}
// END
