/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Ralph Gerbig - initial API and implementation and initial documentation Arne Lange
 * - ocl2 implementation
 *******************************************************************************/

package org.melanee.ocl2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.melanee.core.modeleditor.providers.PLMMarkerNavigationProvider;
import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMPackage;
import org.melanee.core.workbench.interfaces.IConstraintLanguageService;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.models.definition.constraint.BodyConstraint;
import org.melanee.ocl2.models.definition.constraint.Constraint;
import org.melanee.ocl2.models.definition.constraint.ConstraintFactory;
import org.melanee.ocl2.models.definition.constraint.ConstraintPackage;
import org.melanee.ocl2.models.definition.constraint.DefinitionConstraint;
import org.melanee.ocl2.models.definition.constraint.DeriveConstraint;
import org.melanee.ocl2.models.definition.constraint.InitConstraint;
import org.melanee.ocl2.models.definition.constraint.InvariantConstraint;
import org.melanee.ocl2.models.definition.constraint.Level;
import org.melanee.ocl2.models.definition.constraint.PostConstraint;
import org.melanee.ocl2.models.definition.constraint.PreConstraint;
import org.melanee.ocl2.models.definition.constraint.Severity;
import org.melanee.ocl2.models.definition.constraint.impl.DeriveConstraintImpl;
import org.melanee.ocl2.models.definition.constraint.impl.InitConstraintImpl;
import org.melanee.ocl2.models.definition.constraint.impl.InvariantConstraintImpl;
import org.melanee.ocl2.service.util.ConstraintSearchAlgorithm;
import org.melanee.ocl2.service.util.DeepOCL2Util;
import org.melanee.ocl2.service.util.OclInvalid;

public class OCL2Service implements IConstraintLanguageService {

  @Override
  public Object evaluate(Object context, String expression) throws Exception {
    // TODO Auto-generated method stub
    return evaluate(context, context, expression);
  }

  @Override
  public Object evaluate(Object definitionContext, Object context, String expression)
      throws Exception {
    Clabject clabject;
    if (context instanceof Clabject) {
      clabject = (Clabject) context;
      DeepOclLexer ocl2Lexer = new DeepOclLexer(new ANTLRInputStream(expression));
      DeepOclParser parser = new DeepOclParser(new CommonTokenStream(ocl2Lexer));
      ParseTree tree = parser.specificationCS();
      DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
      try {
        return visitor.visit(tree).toString().replaceAll("\"", "");
      } catch (Exception e) {
        return new OclInvalid();
      }
    } else {
      return new OclInvalid();
    }
  }

  /**
   * gets the defined constraints for the current selected element in the graphical model.
   */
  @Override
  public String[] getDefinedConstraintsFor(Element definitionContext) {
    List<String> constraintList = new ArrayList<String>();
    int preIndex = 0;
    int postIndex = 0;
    int invIndex = 0;
    int bodyIndex = 0;
    int initIndex = 0;
    int defIndex = 0;
    int deriveIndex = 0;
    for (AbstractConstraint constraint : definitionContext.getConstraint()) {
      if (constraint instanceof PreConstraint) {
        if (constraint.getName() == null || constraint.getName() == "") {
          constraintList.add("pre" + preIndex);
        } else {
          constraintList.add(constraint.getName());
        }
        preIndex++;
      }
      if (constraint instanceof PostConstraint) {
        if (constraint.getName() == null || constraint.getName() == "") {
          constraintList.add("post" + postIndex);
        } else {
          constraintList.add(constraint.getName());
        }
        postIndex++;
      }
      if (constraint instanceof InvariantConstraint) {
        if (constraint.getName() == null || constraint.getName() == "") {
          constraintList.add("inv" + invIndex);
        } else {
          constraintList.add(constraint.getName());
        }
      }
      if (constraint instanceof BodyConstraint) {
        if ((constraint.getName() == null || constraint.getName() == "") && bodyIndex == 0) {
          constraintList.add("body" + bodyIndex);
          bodyIndex++;
        } else if (bodyIndex == 0) {
          constraintList.add(constraint.getName());
          bodyIndex++;
        } else {
          continue;
        }
      }
      if (constraint instanceof DefinitionConstraint) {
        if ((constraint.getName() == null || constraint.getName() == "") && defIndex == 0) {
          constraintList.add("def" + defIndex);
          defIndex++;
        } else if (bodyIndex == 0) {
          constraintList.add(constraint.getName());
          defIndex++;
        } else {
          continue;
        }
      }
      if (constraint instanceof InitConstraint) {
        if ((constraint.getName() == null || constraint.getName() == "") && initIndex == 0) {
          constraintList.add("init" + initIndex);
          initIndex++;
        } else if (initIndex == 0) {
          constraintList.add(constraint.getName());
          initIndex++;
        } else {
          continue;
        }
      }
      if (constraint instanceof DeriveConstraint) {
        if ((constraint.getName() == null || constraint.getName() == "") && deriveIndex > 0) {
          constraintList.add("derive" + bodyIndex);
          deriveIndex++;
        } else if (deriveIndex == 0) {
          constraintList.add(constraint.getName());
          deriveIndex++;
        } else {
          continue;
        }
      }
    }
    String[] stringList = new String[constraintList.size()];
    for (int i = 0; i < constraintList.size(); i++) {
      stringList[i] = constraintList.get(i);
    }
    return stringList;
  }

  /**
   * gets the possible constraints for the current selected element.
   */
  @Override
  public String[] getPossibleConstraintKindsFor(Element definitionContext) {
    // for Method we want body pre post
    if (definitionContext instanceof Method) {
      return new String[] {CONSTRAINT_KIND_BODY, CONSTRAINT_KIND_PRE, CONSTRAINT_KIND_POST};
    } else if (definitionContext instanceof Attribute) {
      // for attributes we want init and derive constraints
      return new String[] {CONSTRAINT_KIND_INIT, CONSTRAINT_KIND_DERIVE};
    } else {
      // for anything else we want inv and def constraints
      String[] strings = {CONSTRAINT_KIND_INVARIANT, CONSTRAINT_KIND_DEF};
      return strings;
    }
  }

  @Override
  public AbstractConstraint getConstraint(Element definitionContext, String constraintName) {
    DeepOclPersistenceService persistenceService = new DeepOclPersistenceService(definitionContext);
    return persistenceService.getConstraint(constraintName);
  }

  /**
   * would be nice if the interface allows to hand over the kind of constraint as a string, now the
   * remove constraint operation just checks the name of the constraint but not the type. It is
   * possible that 2 different constraint types have the same name.
   * 
   * @param definitionContext definition context --> Clabject
   * @param constraintName name of the constraint
   * @throws UnsupportedOperationException if not supported by the deep ocl 2 language this
   *         exception will be thrown
   */
  @Override
  public void deleteConstraint(Element definitionContext, String constraintName) {
    DeepOclPersistenceService persistenceService = new DeepOclPersistenceService(definitionContext);
    persistenceService.removeConstraint(constraintName);

  }

  /**
   * adds a constraint to the model. need to use the emf.edit command framework, because at this
   * point we do not have direct access to change the models
   */
  @Override
  public AbstractConstraint addConstraint(Element definitionContext, String constraintKind) {
    EditingDomain editingDomain = TransactionUtil.getEditingDomain(definitionContext);
    Command command;
    Command commandLevel;
    Command commandStartLevel;
    Command commandEndLevel;
    Command commandSetName;
    Integer startLevel = null;
    Integer endLevel = null;
    if (definitionContext instanceof Clabject) {
      startLevel = ((Clabject) definitionContext).getLevelIndex();
      endLevel = ((Clabject) definitionContext).getDeepModel().getContent().size() - 1;
    }
    if (definitionContext instanceof Feature) {
      Clabject c = ((Feature) definitionContext).getClabject();
      startLevel = c.getLevelIndex();
      endLevel = c.getDeepModel().getContent().size() - 1;
    }
    if (definitionContext instanceof DeepModel) {
      DeepModel dm = (DeepModel) definitionContext;
      startLevel = 0;
      endLevel = dm.getContent().size();
    }
    if (definitionContext instanceof org.melanee.core.models.plm.PLM.Level) {
      org.melanee.core.models.plm.PLM.Level level =
          (org.melanee.core.models.plm.PLM.Level) definitionContext;
      startLevel = level.getLevel();
      endLevel = level.getDeepModel().getContent().size();
    }
    Level constraintLevel = ConstraintFactory.eINSTANCE.createLevel();
    switch (constraintKind) {
      case ("inv"):
        int invCounter = 0;
        // TODO what if the type is not Clabject? Level or DeepModel are allowed to have
        // constraints too!
        for (AbstractConstraint constr : definitionContext.getConstraint()) {
          if (constr instanceof InvariantConstraint) {
            invCounter++;
          }
        }
        InvariantConstraint inv = ConstraintFactory.eINSTANCE.createInvariantConstraint();
        command = AddCommand.create(editingDomain, definitionContext,
            PLMPackage.eINSTANCE.getElement_Constraint(), inv);
        String name = DeepOCL2Util.createConstraintName(getDefinedConstraintsFor(definitionContext),
            "inv", invCounter);
        inv.setName(name);
        constraintLevel.setEndLevel(endLevel);
        constraintLevel.setStartLevel(startLevel);
        inv.setLevel(constraintLevel);
        editingDomain.getCommandStack().execute(command);
        return (AbstractConstraint) inv;
      case ("init"):
        CompoundCommand compoundCommand = new CompoundCommand();
        for (AbstractConstraint constrt : definitionContext.getConstraint()) {
          // only one init constraint is allowed on an attribute
          if (constrt instanceof InitConstraint) {
            boolean result = MessageDialog.openConfirm(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Confirm",
                "Only one constraint of this type is permitted to exist in this element. "
                    + "Do you wish to remove the existing constraint and create a new one?");
            if (result) {
              compoundCommand.append(RemoveCommand.create(editingDomain, definitionContext,
                  PLMPackage.eINSTANCE.getElement_Constraint(), constrt));
            } else {
              return null;
            }
          }
        }
        Constraint init = ConstraintFactory.eINSTANCE.createInitConstraint();
        compoundCommand.append(AddCommand.create(editingDomain, definitionContext,
            PLMPackage.eINSTANCE.getElement_Constraint(), init));
        init.setName("init" + 0);
        constraintLevel.setEndLevel(endLevel);
        constraintLevel.setStartLevel(startLevel);
        init.setLevel(constraintLevel);
        editingDomain.getCommandStack().execute(compoundCommand);
        return (AbstractConstraint) init;
      case ("def"):
        Constraint def = ConstraintFactory.eINSTANCE.createDefinitionConstraint();
        def.setName("def" + 0);
        constraintLevel.setEndLevel(startLevel);
        constraintLevel.setStartLevel(startLevel);
        def.setLevel(constraintLevel);
        command = AddCommand.create(editingDomain, definitionContext,
            PLMPackage.eINSTANCE.getElement_Constraint(), def);
        editingDomain.getCommandStack().execute(command);
        return (AbstractConstraint) def;
      case ("body"):
        for (AbstractConstraint constrt : definitionContext.getConstraint()) {
          if (constrt instanceof BodyConstraint) {
            // only one body constraint is allowed
            boolean result = MessageDialog.openConfirm(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Confirm",
                "Only one constraint of this type is permitted to exist in this element. "
                    + "Do you wish to remove the existing constraint and create a new one?");
            if (result) {
              Command removeCommand = RemoveCommand.create(editingDomain, definitionContext,
                  PLMPackage.eINSTANCE.getElement_Constraint(), constrt);
              editingDomain.getCommandStack().execute(removeCommand);
            } else {
              return null;
            }
          }
        }
        Constraint body = ConstraintFactory.eINSTANCE.createBodyConstraint();
        body.setName("body" + 0);
        constraintLevel.setEndLevel(startLevel);
        constraintLevel.setStartLevel(startLevel);
        body.setLevel(constraintLevel);
        command = AddCommand.create(editingDomain, definitionContext,
            PLMPackage.eINSTANCE.getElement_Constraint(), body);
        editingDomain.getCommandStack().execute(command);
        return (AbstractConstraint) body;
      case ("post"):
        Constraint post = ConstraintFactory.eINSTANCE.createPostConstraint();
        post.setName("post" + 0);
        constraintLevel.setEndLevel(startLevel);
        constraintLevel.setStartLevel(startLevel);
        post.setLevel(constraintLevel);
        command = AddCommand.create(editingDomain, definitionContext,
            PLMPackage.eINSTANCE.getElement_Constraint(), post);
        editingDomain.getCommandStack().execute(command);
        return (AbstractConstraint) post;
      case ("pre"):
        Constraint pre = ConstraintFactory.eINSTANCE.createPreConstraint();
        pre.setName("pre" + 0);
        constraintLevel.setEndLevel(startLevel);
        constraintLevel.setStartLevel(startLevel);
        pre.setLevel(constraintLevel);
        command = AddCommand.create(editingDomain, definitionContext,
            PLMPackage.eINSTANCE.getElement_Constraint(), pre);
        editingDomain.getCommandStack().execute(command);
        return (AbstractConstraint) pre;
      case ("derive"):
        for (AbstractConstraint constrt : definitionContext.getConstraint()) {
          if (constrt instanceof DeriveConstraint) {
            // only one derive constraint is allowed, either remove the
            // current and define a new one or keep the old one
            boolean result = MessageDialog.openConfirm(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Confirm",
                "Only one constraint of this type is permitted to exist in this element. "
                    + "Do you wish to remove the existing constraint and create a new one?");
            if (result) {
              Command removeCommand = RemoveCommand.create(editingDomain, definitionContext,
                  PLMPackage.eINSTANCE.getElement_Constraint(), constrt);
              editingDomain.getCommandStack().execute(removeCommand);
            } else {
              return null;
            }
          }
        }
        Constraint derive = ConstraintFactory.eINSTANCE.createDeriveConstraint();
        derive.setName("derive" + 0);
        constraintLevel.setEndLevel(endLevel);
        constraintLevel.setStartLevel(startLevel);
        derive.setLevel(constraintLevel);
        command = AddCommand.create(editingDomain, definitionContext,
            PLMPackage.eINSTANCE.getElement_Constraint(), derive);
        editingDomain.getCommandStack().execute(command);
        return (AbstractConstraint) derive;
      default:
        return null;
    }
  }

  @Override
  public ConstraintPropertySheetComposite getPropertySheetCompositeFor(
      AbstractConstraint constraint, Object widgetFactory, Composite editingArea,
      Element definitionContext) {
    return new DeepOCL2Composite(editingArea, SWT.BORDER,
        (TabbedPropertySheetWidgetFactory) widgetFactory, constraint,
        new DeepOclPersistenceService(definitionContext));
  }

  /**
   * this class creates the constraint composite, i.e. editor the constructor sets up the initial
   * look.
   */
  public class DeepOCL2Composite extends ConstraintPropertySheetComposite {
    boolean editMode;
    final Text constraintBox;
    AbstractConstraint constraint;
    Text nameText;
    Text endLevelText;
    Text startLevelText;
    EditingDomain editingDomain;
    DeepOclPersistenceService persistenceService;
    Text message;
    CCombo severityCombo;
    DeepModel dm;

    public DeepOCL2Composite(Composite parent, int style,
        TabbedPropertySheetWidgetFactory widgetFacotry, AbstractConstraint constraint,
        DeepOclPersistenceService persistenceService) {
      super(parent, style);
      Integer startLevel = null;
      Integer endLevel = null;
      this.persistenceService = persistenceService;
      this.constraint = constraint;
      // sometimes there is no level defined on constraints, does not need
      // to be, i.e. for body, pre and post constraints
      if (((Constraint) this.constraint).getLevel() != null) {
        startLevel = ((Constraint) this.constraint).getLevel().getStartLevel();
        endLevel = ((Constraint) this.constraint).getLevel().getEndLevel();
      } else if (persistenceService.getDefinitionContext() instanceof Clabject) {
        startLevel = ((Clabject) persistenceService.getDefinitionContext()).getLevelIndex();
        endLevel = ((Clabject) persistenceService.getDefinitionContext()).getDeepModel()
            .getContent().size();

      } else if (persistenceService.getDefinitionContext() instanceof DeepModel) {
        startLevel = 0;
        endLevel = ((DeepModel) persistenceService.getDefinitionContext()).getContent().size();
      } else {
        startLevel = 0;
        endLevel = 1;
      }
      if (persistenceService.getDefinitionContext() instanceof Clabject) {
        this.dm = ((Clabject) persistenceService.getDefinitionContext()).getDeepModel();
      } else if (persistenceService.getDefinitionContext() instanceof Attribute) {
        this.dm =
            ((Attribute) persistenceService.getDefinitionContext()).getClabject().getDeepModel();
      } else if (persistenceService.getDefinitionContext() instanceof Method) {
        this.dm = ((Method) persistenceService.getDefinitionContext()).getClabject().getDeepModel();
      }

      /**
       * this is for initializing the editor layout that is then returned to the custom
       * propertySheet (parent).
       */

      GridLayout gridLayout = new GridLayout(6, true);
      this.setLayout(gridLayout);

      GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
      gridData.horizontalSpan = 5;
      gridData.horizontalAlignment = SWT.FILL;

      CLabel startLevelLabel = widgetFacotry.createCLabel(this, "Start Level");
      GridData gridDataStart = new GridData(SWT.FILL, SWT.FILL, true, false);

      this.startLevelText = widgetFacotry.createText(this, startLevel.toString(), SWT.SINGLE);
      this.startLevelText.setSize(this.startLevelText.getBorderWidth() + 10,
          this.startLevelText.getLineHeight());
      this.startLevelText.setLayoutData(gridDataStart);

      CLabel endLevelLabel = widgetFacotry.createCLabel(this, "End Level");
      // if end level == -1 then we have to diplay "_"
      if (endLevel == -1) {
        this.endLevelText = widgetFacotry.createText(this, "_", SWT.SINGLE);
      } else {
        this.endLevelText = widgetFacotry.createText(this, endLevel.toString(), SWT.SINGLE);
      }
      GridData gridDataEnd = new GridData(SWT.FILL, SWT.FILL, true, false);
      this.endLevelText.setLayoutData(gridDataEnd);

      // tooltips
      if (dm != null) {
        startLevelText.setToolTipText(dm.getLevelAtIndex(startLevel + 1).getName());
        if (endLevel == -1) {
          endLevelText
              .setToolTipText("Every Level below " + dm.getLevelAtIndex(startLevel + 1).getName());
        } else if (dm.getLevelAtIndex(endLevel + 1) == null) {
          endLevelText.setToolTipText("Level does not exit yet?");
        } else {
          endLevelText.setToolTipText(dm.getLevelAtIndex(endLevel + 1).getName());
        }
      }

      widgetFacotry.createCLabel(this, "Name");
      this.nameText = widgetFacotry.createText(this, constraint.getName(), SWT.SINGLE);
      GridData gridDataName = new GridData(SWT.FILL, SWT.FILL, true, false);
      this.nameText.setLayoutData(gridDataName);
      widgetFacotry.createCLabel(this, "Message");
      GridData gridDateMessage = new GridData(SWT.FILL, SWT.FILL, true, true);
      gridDateMessage.horizontalSpan = 3;
      gridDateMessage.horizontalAlignment = SWT.FILL;
      String constraintType = "";
      if (constraint instanceof InvariantConstraint) {
        constraintType = "inv";
      } else if (constraint instanceof InitConstraint) {
        constraintType = "init";
      } else if (constraint instanceof BodyConstraint) {
        constraintType = "body";
        startLevelLabel.dispose();
        endLevelLabel.dispose();
        startLevelText.dispose();
        endLevelText.dispose();
      } else if (constraint instanceof PreConstraint) {
        constraintType = "pre";
        startLevelLabel.dispose();
        endLevelLabel.dispose();
        startLevelText.dispose();
        endLevelText.dispose();
      } else if (constraint instanceof PostConstraint) {
        constraintType = "post";
        startLevelLabel.dispose();
        endLevelLabel.dispose();
        startLevelText.dispose();
        endLevelText.dispose();
      } else if (constraint instanceof DeriveConstraint) {
        constraintType = "derive";
      } else if (constraint instanceof DefinitionConstraint) {
        constraintType = "def";
        startLevelLabel.dispose();
        endLevelLabel.dispose();
        startLevelText.dispose();
        endLevelText.dispose();
      }

      this.message =
          widgetFacotry.createText(this, ((Constraint) constraint).getMessage(), SWT.SINGLE);
      this.message.setLayoutData(gridDateMessage);
      widgetFacotry.createCLabel(this, "Severity");
      this.severityCombo = widgetFacotry.createCCombo(this);
      int index = 0;
      String[] items = new String[ConstraintPackage.eINSTANCE.getSeverity().getELiterals().size()];
      for (EEnumLiteral literal : ConstraintPackage.eINSTANCE.getSeverity().getELiterals()) {
        items[index] = literal.getName();
        index++;
      }
      this.severityCombo.setItems(items);
      if (((Constraint) constraint).getSeverity().getName() != null) {
        this.severityCombo.setText(((Constraint) constraint).getSeverity().getName());
      }
      widgetFacotry.createCLabel(this, constraintType, SWT.RIGHT);
      constraintBox = widgetFacotry.createText(this, ((Constraint) constraint).getText(),
          SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
      gridData.heightHint = 4 * constraintBox.getLineHeight();
      constraintBox.setLayoutData(gridData);

      constraintBox.setEditable(false);
      severityCombo.setEditable(false);
      severityCombo.setEnabled(false);
      nameText.setEditable(false);
      message.setEditable(false);

      if (!startLevelText.isDisposed() && !endLevelText.isDisposed()) {
        startLevelText.setEditable(false);
        endLevelText.setEditable(false);
        startLevelText
            .setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
        endLevelText
            .setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      }
      constraintBox.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      message.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      nameText.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      editingDomain = TransactionUtil.getEditingDomain(constraint);
    }

    /**
     * this is the saving operation on the clabject. everything has to be done with the emf command
     * framework.
     */
    @Override
    public void save() {
      Level level = ((Constraint) constraint).getLevel();
      Command setNameCommand;
      if (constraintBox.getText() != "") {
        persistenceService.save1(constraintBox.getText(), constraint);
      }

      if (!(this.nameText.getText() == null) && !(this.nameText.getText() == "")) {
        setNameCommand = SetCommand.create(editingDomain, constraint,
            PLMPackage.eINSTANCE.getAbstractConstraint_Name(), this.nameText.getText());
        editingDomain.getCommandStack().execute(setNameCommand);
      }
      // constraint.setName(this.nameText.getText());
      if (!startLevelText.isDisposed() && !endLevelText.isDisposed()
          && this.startLevelText.getText() != null && this.startLevelText.getText() != ""
          && this.endLevelText.getText() != null && this.endLevelText.getText() != "") {
        CompoundCommand cmd = new CompoundCommand();
        Level l = ((Constraint) constraint).getLevel();
        cmd.append(
            SetCommand.create(editingDomain, l, ConstraintPackage.eINSTANCE.getLevel_StartLevel(),
                Integer.parseInt(startLevelText.getText())));
        if (endLevelText.getText().equals("_")) {
          cmd.append(SetCommand.create(editingDomain, l,
              ConstraintPackage.eINSTANCE.getLevel_EndLevel(), -1));
        } else {
          cmd.append(
              SetCommand.create(editingDomain, l, ConstraintPackage.eINSTANCE.getLevel_EndLevel(),
                  Integer.parseInt(endLevelText.getText())));
        }
        editingDomain.getCommandStack().execute(cmd);
        startLevelText.setEditable(false);
        endLevelText.setEditable(false);
        startLevelText
            .setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
        endLevelText
            .setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      }
      if (dm != null) {
        startLevelText.setToolTipText(dm.getLevelAtIndex(level.getStartLevel() + 1).getName());
        if (level.getEndLevel() == -1) {
          endLevelText.setToolTipText(
              "Every Level below " + dm.getLevelAtIndex(level.getStartLevel() + 1).getName());
        } else if (dm.getLevelAtIndex(level.getEndLevel() + 1) == null) {
          endLevelText.setToolTipText("Level does not exit yet?");
        } else {
          endLevelText.setToolTipText(dm.getLevelAtIndex(level.getEndLevel() + 1).getName());

        }
      }
      if (this.message.getText() != null && this.message.getText() != "") {
        Command setMessageCommand = SetCommand.create(editingDomain, constraint,
            ConstraintPackage.eINSTANCE.getConstraint_Message(), this.message.getText());
        editingDomain.getCommandStack().execute(setMessageCommand);
      }
      if (severityCombo.getText() != null && severityCombo.getText() != "") {
        ((Constraint) constraint).getSeverity();
        org.melanee.ocl2.models.definition.constraint.Severity severity =
            Severity.getByName(severityCombo.getText());
        Command setSeverityCommand = SetCommand.create(editingDomain, constraint,
            ConstraintPackage.eINSTANCE.getConstraint_Severity(), severity);
        editingDomain.getCommandStack().execute(setSeverityCommand);
      }
      constraintBox.setEditable(false);
      severityCombo.setEditable(false);
      severityCombo.setEnabled(false);
      nameText.setEditable(false);
      message.setEditable(false);
      constraintBox.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      message.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      nameText.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      this.requestLayout();

    }

    @Override
    public void edit() {
      if (!startLevelText.isDisposed() && !endLevelText.isDisposed()) {
        startLevelText.setEditable(true);
        endLevelText.setEditable(true);
        startLevelText.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
        endLevelText.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
      }
      constraintBox.setEditable(true);
      severityCombo.setEditable(true);
      severityCombo.setEnabled(true);
      nameText.setEditable(true);
      message.setEditable(true);
      constraintBox.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
      message.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
      nameText.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
      this.layout();

    }

    @Override
    public void cancel() {
      if (((Constraint) constraint).getMessage() != null) {
        this.message.setText(((Constraint) constraint).getMessage());
      }
      if (!startLevelText.isDisposed() && !endLevelText.isDisposed()) {
        startLevelText.setEditable(false);
        endLevelText.setEditable(false);
        startLevelText
            .setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
        endLevelText
            .setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      }
      constraintBox.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      message.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      nameText.setBackground(getDisplay().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
      constraintBox.setEditable(false);
      severityCombo.setEditable(false);
      severityCombo.setEnabled(false);
      nameText.setEditable(false);
      message.setEditable(false);
    }
  }

  /**
   * this method is only for validating invariant constraints.
   */
  @Override
  public Set<IValidationResult> validate(Element element, boolean createResourceMarkers) {
    ConstraintSearchAlgorithm searchAlgo = new ConstraintSearchAlgorithm();
    Set<IValidationResult> validationResult = new HashSet<>();
    DeepOclLexer ocl2Lexer;
    DeepOclParser parser;
    ParseTree tree;
    DeepOclRuleVisitor visitor;
    Set<Element> clabSet = new HashSet<>();
    if (element instanceof DeepModel) {
      clabSet.add(element);
      for (org.melanee.core.models.plm.PLM.Level level : ((DeepModel) element).getContent()) {
        clabSet.add(level);
        for (Element clab : level.getContent()) {
          // convert the list into a set, we do not want to evaluate
          // the constraint a million times.
          if (clab instanceof Clabject) {
            Clabject clabject = (Clabject) clab;
            clabSet.add(clabject);
            for (Element elm : clabject.getContent()) {
              if (elm instanceof Clabject) {
                clabSet.add((Clabject) elm);
              }
            }

          }
        }
      }
      for (Element claabject : clabSet) {
        Set<AbstractConstraint> constraintSet = new HashSet<AbstractConstraint>(
            searchAlgo.search(claabject, Arrays.asList(InvariantConstraintImpl.class)));
        check: for (AbstractConstraint constraint : constraintSet) {
          Constraint con = (Constraint) constraint;
          String exp = "inv " + con.getName() + ": " + con.getText();
          // begin parsing and interpreting process for invariant
          // constraints. result has to be of type boolean, else
          // OclInvalid is returned
          ocl2Lexer = new DeepOclLexer(new ANTLRInputStream(exp));
          parser = new DeepOclParser(new CommonTokenStream(ocl2Lexer));
          tree = parser.invCS();
          visitor = new DeepOclRuleVisitor(claabject);
          try {
            Object result = visitor.visit(tree).toString();
            if (result instanceof OclInvalid || result == null) {
              validationResult.add(new ValidationResult(con.getSeverity(),
                  "OclInvalid " + con.getMessage(), claabject));
            }
            Boolean booleanResult = Boolean.parseBoolean(result.toString());
            if (booleanResult.equals(false)) {
              validationResult
                  .add(new ValidationResult(con.getSeverity(), con.getMessage(), claabject));
            }
          } catch (NullPointerException e) {
            System.out.println("check threw a null pointer exception, check your constraint!");
            e.printStackTrace();
            continue check;
          }
        }
      }
    }
    // create gmf marker for every failed invariant constraint
    int depth = IResource.DEPTH_INFINITE;
    try {
      ResourcesPlugin.getWorkspace().getRoot().deleteMarkers(IMarker.PROBLEM, true, depth);
    } catch (CoreException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    marker: for (IValidationResult result : validationResult) {
      TreeIterator<EObject> iterator;
      if (result.getObject() instanceof DeepModel) {
        iterator = result.getObject().eResource().getContents().get(1).eAllContents();
      } else if (result.getObject() instanceof org.melanee.core.models.plm.PLM.Level) {
        iterator = ((org.melanee.core.models.plm.PLM.Level) result.getObject()).getDeepModel()
            .eResource().getContents().get(1).eAllContents();
      } else {
        iterator = ((Clabject) result.getObject()).getDeepModel().eResource().getContents().get(1)
            .eAllContents();
      }
      while (iterator.hasNext()) {
        EObject next = iterator.next();
        if (next instanceof View) {
          if (((View) next).getElement() == result.getObject()) {
            try {
              View obj = ((View) next);
              URI uri = obj.eResource().getURI();
              IFile file = ResourcesPlugin.getWorkspace().getRoot()
                  .getFile(new Path(uri.toPlatformString(true)));
              String location = EMFCoreUtil.getQualifiedName(result.getObject(), true);
              String elementId = obj.eResource().getURIFragment(obj);
              // 1 INFO, 2 WARNING, 4 ERROR
              int severity = 0;
              if (result.getSeverity().name().equals("error")) {
                severity = 4;
              } else if (result.getSeverity().name().equals("warning")) {
                severity = 2;
              } else if (result.getSeverity().name().equals("info")) {
                severity = 1;
              }
              PLMMarkerNavigationProvider.addMarker(file, elementId, location, result.getMessage(),
                  severity);
              continue marker;
            } catch (Exception e) {
              e.printStackTrace();
              continue marker;
            }
          }
        }
      }
    }
    return validationResult;
  }

  /**
   * Wrapper class for failed invariant validations.
   *
   */
  private class ValidationResult implements IValidationResult {
    private org.melanee.ocl2.models.definition.constraint.Severity sevirity;
    private String message;
    private Element element;

    public ValidationResult(org.melanee.ocl2.models.definition.constraint.Severity severity,
        String message, Element element) {
      this.sevirity = severity;
      this.message = element.getName() + ": " + message;
      this.element = element;
    }

    @Override
    public ValidationResultSeverity getSeverity() {
      if (sevirity.getName().equals("ERROR")) {
        return ValidationResultSeverity.error;
      } else if (sevirity.getName().equals("WARNING")) {
        return ValidationResultSeverity.warning;
      } else {
        return ValidationResultSeverity.information;
      }
    }

    @Override
    public String getMessage() {
      return this.message;
    }

    @Override
    public Element getObject() {
      return this.element;
    }

  }

  @Override
  public Command initAttribute(Attribute attribute) {
    // new instance of the SearchAlgorithmClass
    ConstraintSearchAlgorithm searchAlgo = new ConstraintSearchAlgorithm();
    // get EditingDomain
    EditingDomain editingDomain = TransactionUtil.getEditingDomain(attribute);
    // search for InitConstraints
    Set<AbstractConstraint> constraintSet = new HashSet<AbstractConstraint>(
        searchAlgo.search(attribute, Arrays.asList(InitConstraintImpl.class)));
    for (AbstractConstraint constraint : constraintSet) {
      String oclExpression = "init: " + ((Constraint) constraint).getText();
      DeepOclLexer ocl2Lexer = new DeepOclLexer(new ANTLRInputStream(oclExpression));
      DeepOclParser parser = new DeepOclParser(new CommonTokenStream(ocl2Lexer));
      ParseTree tree = parser.initCS();
      DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(attribute);
      Object result = visitor.visit(tree);
      if (result instanceof Collection) {
        Collection collection = (Collection) result;
        result = collection.toArray()[0];
      } 
      if (result instanceof Attribute) {
        Attribute attr = (Attribute) result;
        result = attr.getValue();
      }
      return DeepOCL2Util.createSetCommandForValue(editingDomain, attribute, result);
    }
    return null;
  }

  @Override
  public Command recalculateDerivedAttributes(Element element, int changeType) {
    // new instance of the SearchAlgorithmClass
    ConstraintSearchAlgorithm searchAlgo = new ConstraintSearchAlgorithm();
    EditingDomain editingDomain = null;
    CompoundCommand cmd = new CompoundCommand();
    DeepModel dm = DeepOCL2Util.getDeepModelForElement(element);
    if (dm != null) {
      try {
        for (org.melanee.core.models.plm.PLM.Level level : dm.getContent()) {
          for (Clabject clab : level.getClabjects()) {
            for (Feature attr : clab.getFeature()) {
              Set<AbstractConstraint> constraintSet = new HashSet<AbstractConstraint>(
                  searchAlgo.search(attr, Arrays.asList(DeriveConstraintImpl.class)));
              for (AbstractConstraint constraint : constraintSet) {
                String oclExpression = "derive: " + ((Constraint) constraint).getText();
                // begin parsing and interpreting derive
                // constraints. Result is a command from the
                // emf.edit command framework to set the value of
                // each attribute that has a derive constraint
                // defined
                try {
                  DeepOclLexer ocl2Lexer = new DeepOclLexer(new ANTLRInputStream(oclExpression));
                  DeepOclParser parser = new DeepOclParser(new CommonTokenStream(ocl2Lexer));
                  ParseTree tree = parser.derCS();
                  DeepOclRuleVisitor visitor =
                      new DeepOclRuleVisitor(((Attribute) attr).getClabject());
                  Object result = visitor.visit(tree);
                  if (result instanceof Collection) {
                    result = ((Collection) result).toArray()[0];
                    if (result instanceof Attribute) {
                      result = ((Attribute) result).getValue();
                    }
                  } else if (result instanceof Attribute) {
                    result = ((Attribute) result).getValue();
                  } else if (result instanceof Number) {
                    result = ((Number) result).toString();
                  }
                  Attribute toChange = (Attribute) attr;
                  editingDomain = TransactionUtil.getEditingDomain(toChange);
                  cmd.append(
                      DeepOCL2Util.createSetCommandForValue(editingDomain, toChange, result));
                } catch (NullPointerException e) {
                  e.printStackTrace();
                }
              }
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return cmd;
  }
}
