package com.well.mom.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BaseDto extends BaseObject {

    @JsonProperty("created_by")
    private Integer createdBy;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_by")
    private Integer updatedBy;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    
    @JsonProperty("deleted_by")
    private Integer deletedBy;

    @JsonProperty("deleted_at")
    private Timestamp deletedAt;
    
    @JsonProperty("deleted")
    private Integer deleted;
    
    @JsonProperty("org_id")
    private Integer orgId;
    
    @JsonProperty("company_id")
    private Integer companyId;


    // 辅助列，排序用
    @JsonIgnore
//    @TableField(exist = false)
    private Integer rowId;
}
