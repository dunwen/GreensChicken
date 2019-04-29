package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclaratorId;

/**
 * 处理变量名
 */
public class VariableDeclaratorIdExecutor implements Executor {
  /**
   * @return 变量名
   */
  @Override public String execute(Node node) {
    VariableDeclaratorId variableDeclaratorId = (VariableDeclaratorId) node;
    return variableDeclaratorId.getName();
  }
}
