/*******************************************************************************
 * Copyright (c) 2014 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dominik Kantner - initial API and implementation and initial documentation
 *******************************************************************************/
package org.melanee.ocl.service.ocl.lml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.AbstractEvaluationEnvironment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.LazyExtentMap;
import org.eclipse.ocl.util.Tuple;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.ocl.service.ocl.lml.internal.DeepOCLStandardLibraryImpl;
import org.melanee.ocl.service.ocl.lml.util.DeepOCLStandardLibraryUtil;
import org.melanee.ocl.service.ocl.lml.utilities.DeepOCLUtilities;

/**
 * The LMLEvaluationEnvironment
 * 
 * @author Dominik Kantner
 *
 */
public class DeepOCLEvaluationEnvironment
		extends AbstractEvaluationEnvironment<EObject, EObject, EObject, EObject, EObject> {

	/**
	 * stores the last levelcast
	 */
	protected CastData lastCast;

	/**
	 * stores if a levelcast is locked
	 */
	private boolean castLocked;

	/**
	 * stores the LMLEnvironmentFactory
	 */
	private final DeepOCLEnvironmentFactory factory;

	/**
	 * stores a helpCounter
	 */
	private int helpCounter;

	/**
	 * store a helpParticipantsSize
	 */
	private int helpParticipantsSize;

	/**
	 * @param factory
	 *            A LMLEnvironmentFactory
	 */
	public DeepOCLEvaluationEnvironment(DeepOCLEnvironmentFactory factory) {

		super();
		this.factory = factory;

	}

	/**
	 * @param parent
	 *            An EvaluationEnvironment
	 */
	public DeepOCLEvaluationEnvironment(EvaluationEnvironment<EObject, EObject, EObject, EObject, EObject> parent) {
		super(parent);
		DeepOCLEvaluationEnvironment lmlParent = (DeepOCLEvaluationEnvironment) parent;
		this.factory = lmlParent.factory;
	}

	/**
	 * @param lastCast
	 */
	public void setLastCast(CastData lastCast) {
		this.lastCast = lastCast;
	}

	/**
	 * @return
	 */
	public CastData getLastCast() {
		return lastCast;
	}

	/**
	 * @return
	 */
	public boolean isCastLocked() {
		return castLocked;
	}

	/**
	 * 
	 */
	public void lockCast() {
		castLocked = true;
	}

	/**
	 * 
	 */
	public void unlockCast() {
		castLocked = false;
	}

	/**
	 * clears all the help data
	 */
	public void clearHelpData() {
		lastCast = null;
		helpCounter = 0;
		helpParticipantsSize = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ocl.EvaluationEnvironment#navigateProperty(java.lang.Object,
	 * java.util.List, java.lang.Object)
	 */
	@Override
	public Object navigateProperty(EObject property, List<?> qualifiers, Object source)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

		if (property instanceof EStructuralFeature) {
			if (property instanceof EObject) {
				return ((EObject) source).eGet((EStructuralFeature) property);
			}
		}
		if (property instanceof NavigationAttribute) {
			NavigationAttribute navAttribute = ((NavigationAttribute) property);
			// check if its not a navigation over a super type
			if (lastCast == null || lastCast.getDestination() != navAttribute.getClabject()) {
				// check if there are more than one with this navigation name
				String name = navAttribute.getName();

				Clabject clabject = navAttribute.getClabject();
				if (source instanceof Clabject && clabject != source) {
					clabject = (Clabject) source;
				}
				HashSet<Clabject> participants = DeepOCLUtilities.getAllParticipantsForName(clabject, name);
				if (participants.size() == 0) {
					return getInvalidResult();
				}
				if (navAttribute.isSingle()) {
					return participants.toArray()[0];
				}
				return participants;

			} else { // navigation over supertype
				ArrayList<Clabject> participants;
				if (lastCast.getSource() != null) {
					participants = DeepOCLUtilities.getAllSubtypeParticpants(navAttribute, lastCast.getSource());
					helpCounter = participants.size();
					helpParticipantsSize = 0;

				} else {
					participants = DeepOCLUtilities.getAllSubtypeParticpants(navAttribute, (Clabject) source);
					helpParticipantsSize += participants.size();
					helpCounter--;
					if (helpCounter == 0) {
						helpCounter = helpParticipantsSize;
						helpParticipantsSize = 0;

					}
				}

				if (navAttribute.isSingle()) {
					return participants.toArray()[0];
				}
				return participants;
			}
		}

		if (property instanceof LevelCastAttribute) {

			return source; // return source as levelcast do not change the
							// dynamic object
		}
		if (property instanceof LinguisticIdentifierAttribute) {
			Clabject clabject = getPropertyClabject((LinguisticIdentifierAttribute) property, source);
			return clabject;
		}

		if (property instanceof Attribute) {
			String value = getPropertyValue((Attribute) property, source);

			if (value == null) {
				throw new IllegalArgumentException();
			}

			if (((Attribute) property).getDatatype() != null) {
				if (((Attribute) property).getDatatype().equals(PrimitiveType.INTEGER_NAME)) {
					return Integer.parseInt(value);
				}
				if (((Attribute) property).getDatatype().equals(PrimitiveType.STRING_NAME)) {
					return value;
				}
				if (((Attribute) property).getDatatype().equalsIgnoreCase(PrimitiveType.BOOLEAN_NAME)) {
					return value.equalsIgnoreCase("true");
				}
				if (((Attribute) property).getDatatype().equals(PrimitiveType.REAL_NAME)) {
					return Double.parseDouble(value);
				}
				return value;
			}
			return value;
		}

		return null;

		// needed Later
	}

	/**
	 * Returns the value of an Attribute (ontological or lingusitic)
	 * 
	 * @param attribute
	 * @param source
	 * @return
	 */
	private static String getPropertyValue(Attribute attribute, Object source) {

		if (!(source instanceof Clabject)) {
			return attribute.getValue();
		}

		if (!(attribute.eContainer() instanceof Clabject)) {
			return attribute.getValue();
		}

		if (source == attribute.eContainer()) {
			return attribute.getValue();
		}

		Clabject sourceClabject = (Clabject) source;
		// if source is not identical with container of attribute(might be the
		// cas when an instance of the container is the source, go throug all
		// sources attributes and check if there is an attribute with the same
		// name
		for (Attribute attr : sourceClabject.getAllAttributes()) {
			if (attr.getName().equals(attribute.getName())) {
				return attr.getValue();
			}
		}

		return null;
	}

	/**
	 * returns the clabject of a attribute
	 * 
	 * @param attribute
	 * @param source
	 * @return
	 */
	private Clabject getPropertyClabject(LinguisticIdentifierAttribute attribute, Object source) {
		if (!(source instanceof Clabject)) {
			return attribute.getClabject();
		}

		if (source == attribute.getClabject()) {
			return attribute.getClabject();
		}

		Clabject sourceClabject = (Clabject) source;
		return sourceClabject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ocl.EvaluationEnvironment#navigateAssociationClass(java.lang.
	 * Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object navigateAssociationClass(EObject associationClass, EObject navigationSource, Object source)
			throws IllegalArgumentException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ocl.EvaluationEnvironment#createExtentMap(java.lang.Object)
	 */
	@Override
	public Map<EObject, Set<EObject>> createExtentMap(Object object) {
		if (object instanceof EObject) {
			return new LazyExtentMap<EObject, EObject>((EObject) object) {

				// implements the inherited specification
				@Override
				protected boolean isInstance(EObject cls, EObject element) {
					if (cls instanceof EClass)
						return ((EClass) cls).isInstance(element);
					if (cls instanceof Clabject && element instanceof Clabject)
						return ((Clabject) element).isInstanceOf((Clabject) cls);
					return false;

				}
			};
		}
		return Collections.emptyMap();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ocl.EvaluationEnvironment#isKindOf(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public boolean isKindOf(Object object, EObject classifier) {

		if (classifier instanceof EClass && object instanceof EObject) {
			return ((EClass) classifier).isInstance(object);
		}

		if (classifier instanceof Clabject && object instanceof Clabject) {
			return ((Clabject) object).getTypes().contains(classifier); // only
																		// types
																		// with
																		// distance
																		// 1
			// return
			// LMLUtilities.getAllTypes((Clabject)object).contains(classifier);
		}
		return false;
	}

	@Override
	public boolean isTypeOf(Object object, EObject classifier) {

		if (classifier instanceof EClass && object instanceof EObject) {
			return ((EObject) object).eClass() == classifier;
		}

		if (classifier instanceof Clabject && object instanceof Clabject) {
			return ((Clabject) object).getTypes().contains(classifier); // only
																		// types
																		// with
																		// distance
																		// 1
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ocl.EvaluationEnvironment#getType(java.lang.Object)
	 */
	@Override
	public Clabject getType(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ocl.EvaluationEnvironment#createTuple(java.lang.Object,
	 * java.util.Map)
	 */
	@Override
	public Tuple<EObject, EObject> createTuple(EObject type, Map<EObject, Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ocl.AbstractEvaluationEnvironment#getJavaMethodFor(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	protected java.lang.reflect.Method getJavaMethodFor(EObject operation, Object receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ocl.AbstractEvaluationEnvironment#getInvalidResult()
	 */
	@Override
	protected Object getInvalidResult() {

		return DeepOCLStandardLibraryImpl.INSTANCE.getInvalid();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ocl.AbstractEvaluationEnvironment#callOperation(java.lang.
	 * Object, int, java.lang.Object, java.lang.Object[])
	 */
	public Object callOperation(EObject operation, int opcode, Object source, Object[] args) {

		if (operation instanceof EOperation && ((EOperation) operation).getName() != null
				&& ((EOperation) operation).getName().equals(DeepOCLStandardLibraryUtil.OCL_IS_DEEP_TYPE_OF_NAME)) {
			if (source instanceof Clabject) {

				List<Object> types = DeepOCLUtilities.getAllDirectTypes((Clabject) source);
				if (args.length == 1 && args[0] instanceof EObject) {
					EObject type = (EObject) args[0];
					if (isTypeOf(source, (EObject) args[0])) { // if its not a
																// deep type
						return false;
					}
					return types.contains(type);
				}
			}
			return null;
		}
		if (operation instanceof EOperation && ((EOperation) operation).getName() != null
				&& ((EOperation) operation).getName().equals(DeepOCLStandardLibraryUtil.OCL_IS_DEEP_KIND_OF_NAME)) {
			if (source instanceof Clabject) {
				List<Object> types = DeepOCLUtilities.getAllTypes((Clabject) source);
				if (args.length == 1 && args[0] instanceof EObject) {
					EObject type = (EObject) args[0];
					if (isKindOf(source, (EObject) args[0])) { // if its not a
																// deep type
						return false;
					}
					return types.contains(type);
				}
			}
			return null;
		}

		if (operation instanceof EOperation && ((EOperation) operation).getName() != null
				&& ((EOperation) operation).getName().equals(DeepOCLStandardLibraryUtil.OCL_AS_DEEP_TYPE_NAME)) {
			if (source instanceof Clabject) {
				List<Object> types = DeepOCLUtilities.getAllTypes((Clabject) source);
				if (args.length == 1 && args[0] instanceof EObject) {
					EObject type = (EObject) args[0];
					if (isKindOf(source, (EObject) args[0])) { // if its not a
																// deep type
						return false;
					}
					if (types.contains(type)) {
						return source;
					} else {
						return DeepOCLStandardLibraryImpl.INSTANCE.getInvalid();
					}
				}
			}
			return DeepOCLStandardLibraryImpl.INSTANCE.getInvalid();
		}
		if (operation instanceof EOperation && ((EOperation) operation).getName() != null
				&& ((EOperation) operation).getName().equals(DeepOCLStandardLibraryUtil.OCL_All_DEEP_INSTANCES_NAME)) {
			if (source instanceof Clabject) {
				List<Clabject> instances = ((Clabject) source).getInstances();
				if (args.length == 0) {
					List<Object> allInstances = DeepOCLUtilities.getAllInstances((Clabject) source);
					allInstances.removeAll(instances);
					return allInstances;
				} else if (args.length == 1 && args[0] instanceof Integer) {
					List<Clabject> allInstances = DeepOCLUtilities.getInstancesWithDistance((Clabject) source,
							(int) args[0] + 1);
					allInstances.removeAll(instances);
					return allInstances;
				} else if (args.length == 2 && args[0] instanceof Integer && args[1] instanceof Integer) {
					if ((int) args[0] >= 1 && (int) args[1] > (int) args[0]) {
						int tempDist = (int) args[0] + 1;
						ArrayList<Clabject> allInstances = new ArrayList<Clabject>();
						while (tempDist <= (int) args[1] + 1) {
							allInstances.addAll(DeepOCLUtilities.getInstancesWithDistance((Clabject) source, tempDist));
							tempDist++;
						}
						allInstances.removeAll(instances);
						return allInstances;
					}
				}
			}
			return null;
		}

		if (operation instanceof EOperation && ((EOperation) operation).getName() != null
				&& ((EOperation) operation).getName().equals("=")) {
			if (source instanceof Clabject && args.length == 1 && args[0] instanceof Clabject) {
				return source == args[0];
			}
			return null;
		}
		return null;

	}

}
