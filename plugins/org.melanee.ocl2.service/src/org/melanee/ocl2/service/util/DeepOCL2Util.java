/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Ralph Gerbig - initial API and implementation and initial documentation Arne Lange
 * - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.service.util;

import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMPackage;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.models.definition.constraint.BodyConstraint;
import org.melanee.ocl2.models.definition.constraint.Constraint;
import org.melanee.ocl2.models.definition.constraint.PreConstraint;
import org.melanee.ocl2.service.DeepOclRuleVisitor;
import org.melanee.ocl2.service.exception.PreConstraintFailedException;

public class DeepOCL2Util {

  public static boolean isInLevel(Clabject clabject, AbstractConstraint constraint) {
    int levelIndex = clabject.getLevelIndex();
    // endLevel == -1 means "_"
    Constraint constr = (Constraint) constraint;
    if ((constr.getLevel().getStartLevel() <= levelIndex
        && constr.getLevel().getEndLevel() >= levelIndex)
        || (constr.getLevel().getStartLevel() <= levelIndex
            && constr.getLevel().getEndLevel() == -1)) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isInLevel(Feature feature, AbstractConstraint constraint) {
    return isInLevel(feature.getClabject(), constraint);
  }

  public static boolean isInLevel(Element element, AbstractConstraint constraint) {
    if (element instanceof Clabject) {
      return isInLevel((Clabject) element, constraint);
    } else if (element instanceof Feature) {
      return isInLevel((Feature) element, constraint);
    } else if (element instanceof DeepModel) {
      return true;
    } else if (element instanceof Level) {
      return true;
    } else {
      throw new UnsupportedOperationException("not here");
    }
  }

  public static DeepModel getDeepModelForElement(Element element) {
    if (element instanceof Clabject) {
      return ((Clabject) element).getDeepModel();
    } else if (element instanceof Feature) {
      return ((Attribute) element).getClabject().getDeepModel();
    } else if (element instanceof DeepModel) {
      return (DeepModel) element;
    } else {
      EObject container = element;
      while (container != null && !(container instanceof DeepModel)) {
        container = element.eContainer();
      }
      return (DeepModel) container;
    }
  }

  public static Command createSetCommandForValue(EditingDomain editingDomain, Element element,
      Object value) {
    if (element instanceof Attribute && value != null) {
      if (((Attribute) element).getDatatype().equals("Integer")) {
        try {
          Double dou = Double.parseDouble(value.toString());
          Integer integer = dou.intValue();
          return SetCommand.create(editingDomain, element,
              PLMPackage.eINSTANCE.getAttribute_Value(), integer.toString());
        } catch (Exception e) {
          return null;
        }
      } else if (((Attribute) element).getDatatype().equals("Real")) {
        try {
          Double doub = Double.parseDouble(value.toString());
          return SetCommand.create(editingDomain, element,
              PLMPackage.eINSTANCE.getAttribute_Value(), doub.toString());
        } catch (Exception e) {
          return null;
        }
      } else if (((Attribute) element).getDatatype().equals("String")) {
        try {
          String string = value.toString();
          return SetCommand.create(editingDomain, element,
              PLMPackage.eINSTANCE.getAttribute_Value(), string);
        } catch (Exception e) {
          return null;
        }
      } else if (((Attribute) element).getDatatype().equals("Boolean")) {
        try {
          Boolean bool = Boolean.parseBoolean(value.toString());
          return SetCommand.create(editingDomain, element,
              PLMPackage.eINSTANCE.getAttribute_Value(), bool.toString());
        } catch (Exception e) {
          return null;
        }
      } else if (((Attribute) element).getDatatype().equals("Natural")) {
        try {
          Double doub = Double.parseDouble(value.toString());
          return SetCommand.create(editingDomain, element,
              PLMPackage.eINSTANCE.getAttribute_Value(), doub.toString());
        } catch (Exception e) {
          return null;
        }
      } else if (((Attribute) element).getDatatype().equals("Character")) {
        try {
          if (value.toString().toCharArray().length == 1) {
            char cha = value.toString().toCharArray()[0];
            return SetCommand.create(editingDomain, element,
                PLMPackage.eINSTANCE.getAttribute_Value(), String.valueOf(cha));
          } else {
            return null;
          }
        } catch (Exception e) {
          return null;
        }
      }
    }
    return null;
  }

  public static boolean checkPreConstraints(Feature feature, Clabject element) {
    for (AbstractConstraint constraint : feature.getConstraint()) {
      if (constraint instanceof PreConstraint) {
        String oclExpression = ((PreConstraint) constraint).getText();
        DeepOclLexer ocl2Lexer = new DeepOclLexer(new ANTLRInputStream(oclExpression));
        DeepOclParser parser = new DeepOclParser(new CommonTokenStream(ocl2Lexer));
        ParseTree tree = parser.specificationCS();
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(element);
        Object result = visitor.visit(tree);
        try {
          // result has to be of boolean nature to be
          // valid
          if (Boolean.parseBoolean(result.toString()) == false) {
            throw new PreConstraintFailedException(feature.getName());
          }
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }

      }
    }
    return true;
  }

  public static String createConstraintName(String[] strings, String constraint, int index) {
    String name = constraint.concat(Integer.toString(index));
    for (String element : strings) {
      if (element.equals(name)) {
        name = constraint.concat(Integer.toString(++index));
      } else {
        name = constraint.concat(Integer.toString(index));
      }
    }
    return name;
  }

  public static List<Clabject> traverseClassifications(Clabject context,
      List<Clabject> returnList) {
    if (!context.getDirectTypes().isEmpty()) {
      returnList.addAll(context.getDirectTypes());
      for (Clabject newContext : context.getDirectTypes()) {
        traverseClassifications(newContext, returnList);
      }
    }
    return returnList;
  }

  public static boolean isWord(String s) {
    return (s.length() > 0 && s.split("\\s+").length == 1);
  }
}
