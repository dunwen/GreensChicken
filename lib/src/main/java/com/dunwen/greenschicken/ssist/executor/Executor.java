package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;

public interface Executor {
  Object execute(Node node);
}
