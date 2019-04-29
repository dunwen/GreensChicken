package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.type.ReferenceType;

public class ReferenceTypeExecutor implements Executor {
  @Override public Object execute(Node node) {
    ReferenceType referenceType = (ReferenceType) node;
    return Executors.execute(referenceType.getType());
  }
}
