/*******************************************************************************
 * Copyright (c) 2014-2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - new ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.service.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Level;

public class ConstraintSearchAlgorithm {

  public ConstraintSearchAlgorithm() {
  }

  /**
   * The visualization search algorithm. The algorithm for levels does not
   * <b>support</b> aspects yet. All aspects which are found on the way to a
   * visualizer are stored and merged into the visualizer which is used for
   * rendering (mergeAspects == true).
   * 
   * @param e
   *          Element to visualize
   * @param mergeAspects
   *          Shall aspects be merged?
   * @return The found visualizer; null if no visualizer is found; A copy of the
   *         visualizer with aspects merged into it if aspects are found and
   *         mergeAspects == true
   */
  private List<AbstractConstraint> search(Level e, List<Class> constraintTypes) {
    List<AbstractConstraint> constraints = new ArrayList<>();
    constraints.addAll(getConstraintFromElement(e, e, constraintTypes));

    // search more abstract levels
    if (!(e.eContainer() instanceof DeepModel)) return null;

    DeepModel deepModel = (DeepModel) e.eContainer();
    for (int i = deepModel.getContent().indexOf(e) - 1; i > 0; i--) {
      constraints.addAll(
          getConstraintFromElement((Element) deepModel.getContent().get(i), e, constraintTypes));
    }
    return constraints;
  }

  private List<AbstractConstraint> search(Clabject c, Attribute a, List<Class> constraintTypes) {
    List<AbstractConstraint> constraints = new ArrayList<>();
    LinkedList<Clabject> superTypesToSearch = new LinkedList<Clabject>();
    LinkedList<Clabject> typesToSearch = new LinkedList<Clabject>();

    typesToSearch.add(c);

    Clabject currentClabject = null;
    Clabject currentType = null;

    // Go through the type hierarchy
    while ((currentType = typesToSearch.poll()) != null) {

      // We need to have the type of the current type at the beginning
      typesToSearch.addAll(currentType.getDirectTypes());

      superTypesToSearch = new LinkedList<Clabject>(currentType.getDirectSupertypes());
      superTypesToSearch.add(0, currentType);

      // Go through the inheritance hierarchy
      while ((currentClabject = superTypesToSearch.poll()) != null) {
        if (a == null) {
          constraints.addAll(getConstraintFromElement(currentClabject, c, constraintTypes));
        } else {
          constraints.addAll(getConstraintFromElement(currentClabject, a, constraintTypes));
        }
        superTypesToSearch.addAll(currentClabject.getDirectSupertypes());
        typesToSearch.addAll(currentClabject.getDirectTypes());
      }
    }
    return constraints;
  }

  /**
   * The visualization search algorithm. This algorithm searches first the
   * inheritance tree of the visualized element. If no visualizer is found the
   * types are searched. This is done for the whole classification tree. The
   * algorithm supports aspects. All aspects which are found on the way to a
   * visualizer are stored and merged into the visualizer which is used for
   * rendering (mergeAspects == true). In the case of a level the level itself is
   * searched for a visualizer, then the levels into the more absract direction.
   * For levels no aspects are supported.
   * 
   * @param e
   *          Element to visualize (Supports Clabjects and Levels)
   * @param mergeAspects
   *          Shall aspects be merged?
   * @return The found visualizer; null if no visualizer is found; A copy of the
   *         visualizer with aspects merged into it if aspects are found and
   *         mergeAspects == true
   */
  public List<AbstractConstraint> search(Element e, List<Class> constraintTypes) {

    if (e instanceof Level) { return search((Level) e, constraintTypes); }
    if (e instanceof Clabject) { return search((Clabject) e, null, constraintTypes); }
    if (e instanceof Attribute) { return search((Clabject) e.eContainer(), (Attribute) e,
        constraintTypes); }
    if (e instanceof DeepModel) { return e.getConstraint(); }
    if (e instanceof Level) { return e.getConstraint(); }
    return null;
  }

  /**
   * Searches a clabject for a constraint. The constraint has to have the correct
   * notation, format and conform to the isSameLevel attribute.
   * 
   * @param currentClabject
   *          The clabject to search the constraint for
   * @param elementToValidate
   *          The element to validate. This is needed to check if the element to
   *          validate and the constraint are at the same level.
   * @return Returns a List<AbstractConstraint>.
   */
  private List<AbstractConstraint> getConstraintFromElement(Element currentElement,
      Element elementToValidate, List<Class> constraintTypes) {
    List<AbstractConstraint> constraints = new ArrayList<>();
    // search for Attributes
    if (elementToValidate instanceof Attribute) {
      for (Attribute a : ((Clabject) currentElement).getDefinedAttributes()) {
        if (a.getName() != null && a.getName().equals(elementToValidate.getName())) {
          for (AbstractConstraint con : a.getConstraint()) {
            if (isApplicable(con, elementToValidate, constraintTypes)) {
              constraints.add(con);
            }
          }
        }
      }
    }
    // search for clabjects
    else {
      for (AbstractConstraint constraint : currentElement.getConstraint()) {
        // Check if the visualizer is of interest
        if (isApplicable(constraint, elementToValidate, constraintTypes)) {
          constraints.add(constraint);
        }
      }
    }
    return constraints;
  }

  /**
   * checks if the constraint is applicable
   * 
   * @param constraint
   * @param elementToValidate
   * @param constraintTypes
   * @return
   */
  private boolean isApplicable(AbstractConstraint constraint, Element elementToValidate,
      List<Class> constraintTypes) {
    return constraintTypes.contains(constraint.getClass())
        && DeepOCL2Util.isInLevel(elementToValidate, constraint);
  }

}