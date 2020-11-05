package com.well.mom.tree;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SparePartsTypeMapper {
    
    int add(AddSparePartsTypeRequest r);

    List<SparePartsTypeDto> list(ListSparePartsTypeRequest r);

    List<SparePartsTypeDto> queryTreeFromTop();

}
