package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

/**
 * 变量声明
 */
public class VariableDeclarationExprExecutor implements Executor {

  @Override public Object execute(Node node) {
    VariableDeclarationExpr expr = (VariableDeclarationExpr) node;
    Object object = Executors.execute(expr.getChildrenNodes().get(1));
    return object;
  }
}
