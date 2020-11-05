package com.well.mom.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ExcelExportConfig(autoSize = true)
public class ExcelSpareParts {
    // 大类    大类编码    中类    中类编码    小类    小类编码    标准名称    标准名称编码
    @ExcelRowNumber
    private Integer rowNumber;

    @ExcelImportField(title = "备件编码", required = false, requiredMsg = "大类不能为空")
    @ExcelExportField(position = 1)
    @JsonProperty("type_code")
    private String typeCode;

    @ExcelImportField(title = "备件大类", required = true, requiredMsg = "大类不能为空")
    @ExcelExportField(position = 2)
    @JsonProperty("big_type_name")
    private String bigTypeName;

    @ExcelImportField(title = "备件中类", required = true, requiredMsg = "大类编码不能为空")
    @ExcelExportField(position = 3)
    @JsonProperty("middle_type_name")
    private String middleTypeName;

    @ExcelImportField(title = "备件小类", required = true, requiredMsg = "中类不能为空")
    @ExcelExportField(position = 4)
    @JsonProperty("small_type_name")
    private String smallTypeName;

    @ExcelImportField(title = "备件名称", required = true, requiredMsg = "中类编码不能为空")
    @ExcelExportField(position = 5)
    @JsonProperty("standard_name")
    private String standardName;

    @ExcelImportField(title = "型号规范", required = true, requiredMsg = "小类不能为空")
    @ExcelExportField(position = 6)
    @JsonProperty("model_specification")
    private String modelSpecification;

    @ExcelImportField(title = "所属工厂", required = false, requiredMsg = "小类编码不能为空")
    @ExcelExportField(position = 7)
    @JsonProperty("company")
    private String company;

    @ExcelImportField(title = "计量单位", required = false, requiredMsg = "标准名称不能为空")
    @ExcelExportField(position = 8)
    @JsonProperty("unit_name")
    private String unitName;

    @ExcelImportField(title = "当前库存", required = false, requiredMsg = "标准名称编码不能为空")
    @ExcelExportField(position = 9)
    @JsonProperty("inventory")
    private BigDecimal inventory;

    @ExcelError(title = "错误", exportable = true) // 错误收集字段（解析、校验）
    @JsonProperty("error")
    private List<String> error;
}
