package com.well.mom.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePartsTypeDto extends BaseDto {

    @JsonProperty("type_id")
    private Integer id;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("type_code")
    private String typeCode;

    @JsonProperty("parent_id")
    private Integer parentId;
}
