package com.well.mom.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class SparePartsTypeNode {

    private SparePartsTypeDto node;

    private Map<String, SparePartsTypeNode> children;

    private SparePartsTypeNode parent;

    public SparePartsTypeNode(SparePartsTypeDto node) {
        this.node = node;
        children = new HashMap<>();
    }

    public SparePartsTypeNode() {
    }
}
