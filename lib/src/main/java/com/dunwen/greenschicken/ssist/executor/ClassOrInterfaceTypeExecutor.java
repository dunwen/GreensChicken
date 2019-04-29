package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.ssist.pool.ClassPool;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public class ClassOrInterfaceTypeExecutor implements Executor {
  @Override public Class execute(Node node) {
    ClassOrInterfaceType type = (ClassOrInterfaceType) node;
    String name = type.getName();
    return ClassPool.find(name);
  }
}
