package com.dunwen.greenschicken;

import com.dunwen.greenschicken.ssist.executor.Executors;
import com.github.javaparser.ast.Node;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class Utils {
  private Utils() {
  }

  public static Class classOrNull(String name) {
    try {
      return Class.forName(name);
    } catch (ClassNotFoundException e) {
    }
    return null;
  }

  public static Constructor constructorOrNull(Class[] args, Class aim) {
    try {
      Constructor[] constructors = aim.getConstructors();
      a:
      for (Constructor constructor : constructors) {
        Class[] parameterTypes = constructor.getParameterTypes();
        if (parameterTypes.length != args.length) {
          continue;
        }
        b:
        for (int j = 0; j < parameterTypes.length; j++) {
          Class aimClass = parameterTypes[j];
          Class srcClass = args[j];
          while (true) {
            if (srcClass == null) {
              continue a;
            }
            if (aimClass == srcClass) {
              continue b;
            }
            srcClass = srcClass.getSuperclass();
          }
        }
        return constructor;
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  public static Method methodOrNull(Class[] args, String name, Class aim) {
    try {
      Method[] methods = aim.getMethods();
      a:
      for (Method method : methods) {
        Class[] parameterTypes = method.getParameterTypes();
        if (!method.getName().equals(name) || parameterTypes.length != args.length) {
          continue;
        }
        b:
        for (int j = 0; j < parameterTypes.length; j++) {
          Class aimClass = parameterTypes[j];
          Class srcClass = args[j];
          if (aimClass.isPrimitive()) {
            // 基本类型查找
            if (srcClass.isPrimitive() && srcClass == aimClass) {
              continue;
            }
            if (aimClass == objectOrNull("TYPE", srcClass)) {
              continue;
            }
            continue a;
          }
          while (true) {
            if (srcClass == null) {
              continue a;
            }
            if (aimClass == srcClass) {
              continue b;
            }
            Class[] interfaces = srcClass.getInterfaces();
            for (Class anInterface : interfaces) {
              if (anInterface == aimClass) {
                continue b;
              }
            }
            srcClass = srcClass.getSuperclass();
          }
        }
        return method;
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  public static Field fieldOrNull(String name, Class clazz) {
    Field field = null;
    try {
      field = clazz.getField(name);
    } catch (NoSuchFieldException e) {
    }
    if (field == null) {
      try {
        field = clazz.getDeclaredField(name);
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      }
    }
    return field;
  }

  public static Object objectOrNull(String name, Object aim) {
    if (aim == null) return null;
    Field field = fieldOrNull(name, aim.getClass());
    try {
      return field.get(aim);
    } catch (Exception e) {
      return null;
    }
  }

  public static Object objectOrNull(String name, Class clazz) {
    if (clazz == null) return null;
    Field field = fieldOrNull(name, clazz);
    try {
      return field.get(null);
    } catch (Exception e) {
      return null;
    }
  }

  public static Object[] executeAll(List<? extends Node> nodes) {
    Object[] objects = new Object[nodes.size()];
    for (int i = 0; i < nodes.size(); i++) {
      objects[i] = Executors.execute(nodes.get(i));
    }
    return objects;
  }

  public static Class[] getTypes(Object[] args) {
    Class[] classes = new Class[args.length];
    for (int i = 0; i < args.length; i++) {
      classes[i] = args[i].getClass();
    }
    return classes;
  }

  public static Object setVule(String name, Object aim,Object value) {
    if (aim == null) return null;
    Field field = fieldOrNull(name, aim.getClass());
    try {
      field.setAccessible(true);
      field.set(aim,value);
      return field.get(aim);
    } catch (Exception e) {
      return null;
    }
  }
}
