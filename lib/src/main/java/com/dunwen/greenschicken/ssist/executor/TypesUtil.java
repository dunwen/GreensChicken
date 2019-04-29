package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;

public class TypesUtil {

  public static Class getTypes(Type type) {
    if (type instanceof PrimitiveType) {
      return getBasicType((PrimitiveType) type);
    }
    return null;
  }

  public static Class getBasicType(PrimitiveType type) {
    switch (type.getType()) {
      case Int:
        return Integer.class;
      case Byte:
        return Byte.class;
      case Short:
        return Short.class;
      case Long:
        return Long.class;
      case Float:
        return Float.class;
      case Double:
        return Double.class;
      case Boolean:
        return Boolean.class;
      case Char:
        return Character.class;
      default:
        return null;
    }
  }
}
