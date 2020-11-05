package com.well.mom.tree;

import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

public class SearchTest {

    @Transactional(rollbackFor = Exception.class)
    public void importFromSpecialExcel(String file) throws Exception {
        List<ExcelSpareParts> sparePartsList = PoiKit.read(new File(file), ExcelSpareParts.class);
        if (root == null) {
            root = sparePartsTypeService.buildTree();
        }
        for (ExcelSpareParts item : sparePartsList) {
            SparePartsTypeDto type = findType(root, item);
            if (type == null) {
                log.warn("信息不正确：{}", item);
                continue;
            }
            AddAndUpdateSparePartsMasterRequest request = new AddAndUpdateSparePartsMasterRequest();
            request.setCreatedBy(0);
            request.setModelSpecification(item.getModelSpecification());
            request.setTypeId(type.getId());
            request.setTypeName(type.getTypeName());
            request.setRemark(item.getCompany());
            request.setCompanyId(0);
            request.setOrgId(0);
            log.info("生成数据:{}", request);
            add(request);
        }
    }
    private SparePartsTypeDto findType(SparePartsTypeNode root, ExcelSpareParts item) {
        SparePartsTypeNode bt = root.getChildren().get(item.getBigTypeName());
        if (bt != null) {
            SparePartsTypeNode mt = bt.getChildren().get(item.getMiddleTypeName());
            if (mt != null) {
                SparePartsTypeNode st = mt.getChildren().get(item.getSmallTypeName());
                if (st != null) {
                    SparePartsTypeNode dt = st.getChildren().get(item.getStandardName());
                    if(dt !=null) {
                        return dt.getNode();
                    }
                }
            }
        }
        return null;
    }
}
