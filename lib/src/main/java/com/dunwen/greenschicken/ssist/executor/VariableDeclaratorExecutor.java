package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.ssist.pool.VariablePool;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import java.util.List;

/**
 * 变量赋值
 */
public class VariableDeclaratorExecutor implements Executor {
  /**
   * 变量赋值，node[0] 是变量名
   */
  public Object execute(Node node) {
    VariableDeclarator declarator = (VariableDeclarator) node;
    List<Node> nodes = declarator.getChildrenNodes();
    Node nameNode = nodes.get(0);
    Node valueNode = nodes.get(1);
    String name = (String) Executors.execute(nameNode);
    Object value = Executors.execute(valueNode);
    VariablePool.put(name, value);
    return value;
  }
}
