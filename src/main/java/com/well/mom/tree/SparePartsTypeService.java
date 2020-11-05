package com.well.mom.tree;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SparePartsTypeService {

    @Autowired
    private SparePartsTypeMapper sparePartsTypeMapper;


    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 构建备件分类树，以中文为关键字。
     * @return
     */
    public SparePartsTypeNode buildTree() {
        List<SparePartsTypeDto> origList = sparePartsTypeMapper.queryTreeFromTop();
        SparePartsTypeNode root = new SparePartsTypeNode();
        root.setNode(null);
        root.setChildren(new HashMap<>());
        SparePartsTypeNode prior = null;
        for (SparePartsTypeDto type : origList) {
            if (type.getTypeCode().length() == 2) {
                SparePartsTypeNode node = new SparePartsTypeNode(type);
                node.setParent(root);
                SparePartsTypeNode old = root.getChildren().putIfAbsent(type.getTypeName(), node);
                if (old != null) {
                    //说明以前有值
                }
                prior = node;
            } else {
                if (Objects.equals(type.getParentId(), prior.getNode().getId())) {
                    //优先判断，就近原则
                } else {
                    String code = getPriorCode(type.getTypeCode());
                    prior = findPrior(code, root);
                }
                //把当前结点挂在prior的child里
                SparePartsTypeNode node = new SparePartsTypeNode(type);
                log.info("{}", node);
                node.setParent(prior);
                prior.getChildren().putIfAbsent(type.getTypeName(), node);
            }
        }
        return root;
    }

    private String getPriorCode(String typeCode) {
        if (typeCode.length() == 9) {
            return typeCode.substring(0, 6);
        } else if (typeCode.length() == 6) {
            return typeCode.substring(0, 4);
        } else if (typeCode.length() == 4) {
            return typeCode.substring(0, 2);
        }
        return null;
    }

    private SparePartsTypeNode findPrior(String code, SparePartsTypeNode root) {
        if (root.getNode() != null) {
            if (Objects.equals(root.getNode().getTypeCode(), code)) {
                return root;
            }
            int b = code.indexOf(root.getNode().getTypeCode());
            if (b == -1) {
                return findPrior(code, root.getParent());
            }
        }

        if (CollectionUtils.isEmpty(root.getChildren().values())) {
            return findPrior(code, root.getParent());
        }
        Collection<SparePartsTypeNode> values = root.getChildren().values();
        for (SparePartsTypeNode node : values) {
            if (node.getNode().getTypeCode().equals(code)) {
                return node;
            } else if (code.startsWith(node.getNode().getTypeCode())) {
                return findPrior(code, node);
            }
        }
        return findPrior(code, root.getParent());
    }

    public static void main(String[] args) {
        String code = "010203009";
        System.out.println(code.substring(0, 6));
        SparePartsTypeService service = new SparePartsTypeService();
        service.buildTree();
    }
}
