/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/

package org.melanee.ocl2.service;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMPackage;
import org.melanee.ocl2.models.definition.constraint.BodyConstraint;
import org.melanee.ocl2.models.definition.constraint.Constraint;
import org.melanee.ocl2.models.definition.constraint.ConstraintFactory;
import org.melanee.ocl2.models.definition.constraint.ConstraintPackage;
import org.melanee.ocl2.models.definition.constraint.DefinitionConstraint;
import org.melanee.ocl2.models.definition.constraint.DeriveConstraint;
import org.melanee.ocl2.models.definition.constraint.Expression;
import org.melanee.ocl2.models.definition.constraint.InitConstraint;
import org.melanee.ocl2.models.definition.constraint.InvariantConstraint;
import org.melanee.ocl2.models.definition.constraint.Pointer;
import org.melanee.ocl2.models.definition.constraint.PostConstraint;
import org.melanee.ocl2.models.definition.constraint.PreConstraint;
import org.melanee.ocl2.models.definition.constraint.Text;
import org.omg.CORBA._PolicyStub;

public class DeepOclPersistenceService {

  private Element context;
  private Constraint constraint;
  private Text newLine;
  private List<String> dividedStrings;
  private EList<Enumeration> enumerations;
  private EList<Feature> attributes;
  private EList<Connection> connections;
  private List<String> attributeNames;
  private List<String> connectionNames;
  private List<String> enumerationNames;
  private EList<Clabject> allLevelClabjects;
  private List<String> allClabjectNames;
  EditingDomain editingDomain;

  public DeepOclPersistenceService(Element context) {
    this.context = context;
    this.newLine = ConstraintFactory.eINSTANCE.createText();
    this.newLine.setText("\n");
    this.constraint = ConstraintFactory.eINSTANCE.createConstraint();
    this.dividedStrings = new ArrayList<String>();
    if (context instanceof Clabject) {
      this.enumerations = ((Clabject) this.context).getDeepModel().getEnumeration();
      this.attributes = ((Clabject) this.context).getFeature();
      this.connections = ((Clabject) this.context).getConnections();
      this.allLevelClabjects = ((Clabject) this.context).getLevel().getClabjects();
    }
    if (context instanceof Feature) {
      Clabject c = ((Feature) context).getClabject();
      this.enumerations = c.getDeepModel().getEnumeration();
      this.attributes = c.getFeature();
      this.connections = c.getConnections();
      this.allLevelClabjects = c.getLevel().getClabjects();
    }
    this.attributeNames = new ArrayList<String>();
    this.enumerationNames = new ArrayList<String>();
    this.connectionNames = new ArrayList<String>();
    this.allClabjectNames = new ArrayList<>();
    this.editingDomain = TransactionUtil.getEditingDomain(context);

  }

  public void save1(String constraintText, AbstractConstraint constraint) {
    EditingDomain editingDomain = TransactionUtil.getEditingDomain(constraint);
    List<Expression> expresionList = new ArrayList<>();
    for (Expression e : ((Constraint) constraint).getExpression()) {
      if (e instanceof Text || e instanceof Pointer) {
        expresionList.add(e);
      }

    }
    Command command = RemoveCommand.create(editingDomain, constraint,
        ConstraintPackage.eINSTANCE.getConstraint_Expression(), expresionList);
    editingDomain.getCommandStack().execute(command);
    this.constraint = (Constraint) constraint;
    String[] splitted = constraintText.split("\n");
    for (String s : splitted) {
      saveLine(s);
    }
  }

  /**
   * save command and starting point to save the constraint string. Split into
   * each line and checking at the beginning if a constraint type is mentioned,
   * like inv for invariant.
   */
  public void save(String constraintString) {
    boolean add = true;
    String[] parts = constraintString.split("\n");
    for (int i = 0; i < parts.length; i++) {
      if (parts[i].trim().startsWith("inv")) {
        boolean notFound = true;
        // if name is already existent the constraint will be overridden
        String name = constraintString.substring(constraintString.indexOf("inv") + "inv".length(),
            constraintString.indexOf(":")).trim();
        for (AbstractConstraint constraint : this.context.getConstraint()) {
          Constraint constraint1 = (Constraint) constraint;
          if (constraint1 instanceof InvariantConstraint) {
            constraint1.getExpression().clear();
            this.constraint = constraint1;
            notFound = false;
            add = false;
          }
        }
        if (notFound) {
          this.constraint = ConstraintFactory.eINSTANCE.createInvariantConstraint();
          this.constraint.setName(name.trim());
        }
      }
      if (parts[i].trim().startsWith("derive")) {
        boolean notFound = false;
        for (AbstractConstraint constraint : this.context.getConstraint()) {
          Constraint c = (Constraint) constraint;
          if (c.getClass().getSimpleName().equals("DeriveConstraintImpl")
              && c.getName().equals("derive")) {
            c.getExpression().clear();
            this.constraint = c;
            notFound = false;
          }
        }
        if (notFound) {
          this.constraint = ConstraintFactory.eINSTANCE.createDeriveConstraint();
          this.constraint.setName("derive");
        }
      }
      if (parts[i].trim().startsWith("init")) {
        this.constraint = ConstraintFactory.eINSTANCE.createInitConstraint();
      }
      if (parts[i].trim().startsWith("body")) {
        for (AbstractConstraint constraint : this.context.getConstraint()) {
          Constraint c = (Constraint) constraint;
          if (c.getClass().getSimpleName().equals("BodyConstraintImpl")
              && c.getName().equals("body")) {
            c.getExpression().clear();
            this.constraint = c;
          } else {
            this.constraint = ConstraintFactory.eINSTANCE.createBodyConstraint();
            this.constraint.setName("body");
          }
        }
      }
      if (parts[i].trim().startsWith("def")) {
        // if name is already existent in clabject the constraint will
        // be overridden
        String sub = constraintString.substring(constraintString.indexOf(":"));
        String name = sub.substring(0, sub.indexOf(":"));
        for (AbstractConstraint constraint : this.context.getConstraint()) {
          Constraint constraint1 = (Constraint) constraint;
          if (constraint1.getClass().getSimpleName().equals("DefinitionConstraintImpl")
              && constraint1.getName().equals(name)) {
            constraint1.getExpression().clear();
            this.constraint = constraint1;
          } else {
            this.constraint = ConstraintFactory.eINSTANCE.createDefinitionConstraint();
            this.constraint.setName(name.trim());
          }
        }
      }
      if (parts[i].trim().startsWith("post")) {
        for (AbstractConstraint constraint : this.context.getConstraint()) {
          Constraint c = (Constraint) constraint;
          if (c.getClass().getSimpleName().equals("PostConstraintImpl")
              && c.getName().equals("post")) {
            c.getExpression().clear();
          } else {
            this.constraint = ConstraintFactory.eINSTANCE.createPostConstraint();
            this.constraint.setName("post");
          }
        }
      }
      if (parts[i].trim().startsWith("pre")) {
        for (AbstractConstraint constraint : this.context.getConstraint()) {
          Constraint c = (Constraint) constraint;
          if (c.getClass().getSimpleName().equals("PresConstraintImpl")
              && c.getName().equals("pre")) {
            c.getExpression().clear();
          } else {
            this.constraint = ConstraintFactory.eINSTANCE.createPreConstraint();
            this.constraint.setName("pre");
          }
        }
      }
      if (add) {
        this.context.getConstraint().add(this.constraint);
      }
      saveLine(parts[i]);
    }

  }

  /**
   * this method updates all context relevant information when a navigation in the
   * model happens
   * 
   * @param context
   */
  private void updateContext(Clabject context) {
    this.attributes = context.getFeature();
    this.connections = context.getConnections();
    this.allClabjectNames.clear();
    this.attributeNames.clear();
    this.connectionNames.clear();
    this.enumerationNames.clear();
    for (Clabject clabject : allLevelClabjects) {
      this.allClabjectNames.add(clabject.getName());
    }
    for (Feature feature : attributes) {
      this.attributeNames.add(feature.getName());
    }
    for (Connection connection : connections) {
      this.connectionNames.add(connection.getName());
    }
    for (Enumeration enumeration : enumerations) {
      this.enumerationNames.add(enumeration.getName());
    }

  }

  private void saveLine(String string) {
    if (this.context instanceof Clabject) {
      saveLine(string, (Clabject) this.context);
    } else if (this.context instanceof Feature) {
      saveLine(string, ((Feature) this.context).getClabject());
    } else if (this.context instanceof Level) {
      throw new UnsupportedOperationException("not yet implemented");
    } else if (this.context instanceof DeepModel) {
      throw new UnsupportedOperationException("not yet implemented");
    }
  }

  /**
   * this method saves each line and checks for navigation in the line or
   * brackets.
   * 
   * @param string
   */
  private void saveLine(String string, Clabject context) {
    this.dividedStrings.clear();
    Clabject newContext = context;
    updateContext(context);
    divideString(string);
    this.dividedStrings.add("\n");

    for (String word : dividedStrings) {
      boolean point = false;
      if (word.contains(".")) {
        word = word.replace(".", "");
        point = true;
      }
      if (word.equals("self") && this.context instanceof Clabject) {
        context = (Clabject) this.context;
        updateContext(context);
        newContext = context;
      }
      if (context != newContext) {
        context = newContext;
        updateContext(context);
      }
      String tempword = word.trim();
      if (attributeNames.contains(tempword)) {
        createPointer(context.getFeatureForName(tempword));
        if (word.contains(" ")) {
          createText(" ");
        }
      } else if (connectionNames.contains(tempword)) {
        int index = connectionNames.indexOf(tempword);
        EList<Clabject> participants = connections.get(index).getParticipants();
        createPointer(connections.get(index));
        if (word.contains(" ")) {
          createText(" ");
        }
        for (Clabject participant : participants) {
          if (context.getName() != participant.getName()) {
            newContext = participant;
          }
        }

      } else if (allClabjectNames.contains(tempword)) {
        int index = allClabjectNames.indexOf(tempword);
        createPointer(allLevelClabjects.get(index));
        if (word.contains(" ")) {
          createText(" ");
        }
        newContext = allLevelClabjects.get(index);
      } else if (word.contains("::")) {
        String sub = word.substring(0, word.indexOf("::"));
        if (enumerationNames.contains(sub)) {
          createPointer(enumerations.get(enumerationNames.indexOf(sub)));
          createText("::");
          createText(word.substring(word.indexOf("::") + 2));
        } else {
          createText(word);
        }
      } else {
        createText(word);
      }
      if (point) {
        createText(".");
      }
    }
  }

  /**
   * create a Pointer element from the current context to wire the name to the
   * model
   * 
   * @param element
   */
  private void createPointer(Element element) {
    Pointer pointer = ConstraintFactory.eINSTANCE.createPointer();
    pointer.setPointer(element);
    if (editingDomain == null) {
      constraint.getExpression().add(pointer);
    } else {
      Command command;
      command = AddCommand.create(this.editingDomain, this.constraint,
          ConstraintPackage.eINSTANCE.getConstraint_Expression(), pointer);
      this.editingDomain.getCommandStack().execute(command);
    }
  }

  /**
   * create a simple Text element for non Pointer expressions
   * 
   * @param value
   */
  private void createText(String value) {
    Text text = ConstraintFactory.eINSTANCE.createText();
    text.setText(value);
    if (editingDomain == null) {
      constraint.getExpression().add(text);
    } else {
      Command command;
      command = AddCommand.create(this.editingDomain, this.constraint,
          ConstraintPackage.eINSTANCE.getConstraint_Expression(), text);
      this.editingDomain.getCommandStack().execute(command);
    }
  }

  /**
   * this method searches in each line for whitespace and dots. If the method
   * found such an element it divides the string up into parts and adds it to
   * dividedWords List. It does that in a recursive manner.
   * 
   * @param string
   */
  private void divideString(String string) {
    int dotIndex = 0;
    int spaceIndex = 0;
    String sub = null;
    String rest = null;
    if (string.contains(" ")) {
      spaceIndex = string.indexOf(" ");
    } else {
      spaceIndex = 1000;
    }
    if (string.contains(".")) {
      dotIndex = string.indexOf(".");
    } else {
      dotIndex = 1000;
    }
    if (spaceIndex < dotIndex) {
      sub = string.substring(0, spaceIndex + 1);
      rest = string.substring(spaceIndex + 1);
    } else if (dotIndex < spaceIndex && dotIndex != 0) {
      sub = string.substring(0, dotIndex + 1);
      rest = string.substring(dotIndex + 1);
    } else {
      sub = string;
      dividedStrings.add(sub);
      return;
    }
    dividedStrings.add(sub);
    if (rest.contains(" ") || rest.contains(".")) {
      divideString(rest);
    } else {
      dividedStrings.add(rest);
    }
  }

  /**
   * this method removes a constraint identified by its type and its name from the
   * clabject
   * 
   * @param kind
   * @param constraint
   */
  public void removeConstraint(String constraint) {
    // TODO testing
    Command removeCommand;
    Constraint c = (Constraint) getConstraint(constraint);
    removeCommand = RemoveCommand.create(editingDomain, this.context,
        PLMPackage.eINSTANCE.getElement_Constraint(), c);
    editingDomain.getCommandStack().execute(removeCommand);

  }

  /**
   * @param constraintName
   * @return when name is found it returns the constraint, if not null.
   */
  public AbstractConstraint getConstraint(String constraintName) {
    for (AbstractConstraint constraint : this.context.getConstraint()) {
      if (constraint.getName().equals(constraintName)) {
        return constraint;
      }
    }
    return null;
  }

  public Element getDefinitionContext() {
    return this.context;
  }
}
