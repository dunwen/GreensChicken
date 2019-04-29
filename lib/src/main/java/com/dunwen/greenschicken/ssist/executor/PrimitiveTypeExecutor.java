package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.type.PrimitiveType;

public class PrimitiveTypeExecutor implements Executor {
  @Override public Class execute(Node node) {
    PrimitiveType primitiveType = (PrimitiveType) node;
    return TypesUtil.getBasicType(primitiveType);
  }
}
