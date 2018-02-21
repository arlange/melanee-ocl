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
package org.melanee.ocl.service.ocl.lml.internal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.types.PrimitiveType;
import org.eclipse.ocl.utilities.ExpressionInOCL;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.PredefinedType;
import org.eclipse.ocl.utilities.TypedElement;
import org.eclipse.ocl.utilities.UMLReflection;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.ocl.service.ocl.lml.AnyType;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.LevelCastAttribute;
import org.melanee.ocl.service.ocl.lml.LinguisticIdentifierAttribute;
import org.melanee.ocl.service.ocl.lml.NavigationAttribute;
import org.melanee.ocl.service.ocl.lml.TypeType;
import org.melanee.ocl.service.ocl.lml.impl.ConstraintImpl;
import org.melanee.ocl.service.ocl.lml.impl.ExpressionInOCLImpl;
import org.melanee.ocl.service.ocl.lml.impl.LevelCastAttributeImpl;
import org.melanee.ocl.service.ocl.lml.impl.LinguisticIdentifierAttributeImpl;
import org.melanee.ocl.service.ocl.lml.impl.NavigationAttributeImpl;
import org.melanee.ocl.service.ocl.lml.utilities.DeepOCLUtilities;

/**
 * The deep OCL UMLReflectionImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class DeepModelReflectionImpl implements
    UMLReflection<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint> {

  /**
   * Shared instance of the stateless reflection service.
   */
  public static final DeepModelReflectionImpl INSTANCE = new DeepModelReflectionImpl();

  /**
   * Initializes me.
   */
  protected DeepModelReflectionImpl() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getOCLType(java.lang.Object)
   */
  @Override
  public EObject getOCLType(Object metaElement) {
    // TODO Auto-generated method stub
    if (metaElement instanceof NavigationAttribute) {
      return ((NavigationAttribute) metaElement).getNavigationValue();
    }
    if (metaElement instanceof LinguisticIdentifierAttribute) {
      return ((LinguisticIdentifierAttribute) metaElement).getLinguisticType();
    }

    if (metaElement instanceof LevelCastAttribute) {
      return ((LevelCastAttribute) metaElement).getCast();
    }

    if (metaElement instanceof Attribute) {
      if (((Attribute) metaElement).getDatatype() != null) {
        if (((Attribute) metaElement).getDatatype().equals(PrimitiveType.INTEGER_NAME)) {
          return DeepOCLStandardLibraryImpl.INSTANCE.getInteger();
        }
        if (((Attribute) metaElement).getDatatype().equals(PrimitiveType.STRING_NAME)) {
          return DeepOCLStandardLibraryImpl.INSTANCE.getString();
        }
        if (((Attribute) metaElement).getDatatype().equals(PrimitiveType.REAL_NAME)) {
          return DeepOCLStandardLibraryImpl.INSTANCE.getReal();
        }
        if (((Attribute) metaElement).getDatatype().equalsIgnoreCase(PrimitiveType.BOOLEAN_NAME)) {
          return DeepOCLStandardLibraryImpl.INSTANCE.getBoolean();
        }
        return DeepOCLStandardLibraryImpl.INSTANCE.getString();
      }
      return DeepOCLStandardLibraryImpl.INSTANCE.getString();
    }
    if (metaElement instanceof Method) {
      return ((Method) metaElement).getClabject();
    }
    if (metaElement instanceof Clabject) {
      return (EObject) metaElement;
    }

    if (metaElement instanceof EAttribute) {
      return getOCLType(((EAttribute) metaElement).getEType());
    }

    if (metaElement instanceof EParameter) {
      return getOCLType(((EParameter) metaElement).getEType());
    }

    if (metaElement instanceof EOperation || metaElement instanceof EReference) {
      if (metaElement instanceof ETypedElement) {
        ETypedElement typedElement = (ETypedElement) metaElement;
        if (isMany(typedElement)) {
          EObject result = getOCLCollectionType(typedElement.getEType(), typedElement.isOrdered(),
              typedElement.isUnique());
          return result;
        }
        return getOCLType(((ETypedElement) metaElement).getEType());
      }
      return getOCLType(((EOperation) metaElement).getEType());
    }

    if (metaElement instanceof TypeType) {
      if (((TypeType) metaElement).getReferredType() instanceof Clabject) {
        return ((TypeType) metaElement).getReferredType();
      }
    }

    return null;
  }

  /**
   * @param type
   * @return The OCLType
   */
  EObject getOCLType(EClassifier type) {
    EObject resultType = type;

    if (resultType instanceof EDataType) {
      resultType = getOCLTypeFor((EDataType) resultType);
    }

    return resultType;
  }

  private EObject getOCLTypeFor(EDataType dataType) {

    // First check if it is already an OCL data type (EEnums represent
    // themselves)
    if (dataType instanceof EEnum) {
      return dataType;
    }
    if (dataType instanceof CollectionType<?, ?>) {
      return dataType;
    }
    if (dataType instanceof PrimitiveType<?>) {
      return dataType;
    }

    Class<?> instanceClass = dataType.getInstanceClass();

    if (instanceClass != null) {
      if (instanceClass == Boolean.class || instanceClass == boolean.class) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getBoolean();
      } else if (instanceClass == Double.class || instanceClass == BigDecimal.class
          || instanceClass == double.class || instanceClass == Float.class
          || instanceClass == float.class) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getReal();
      } else if (instanceClass == String.class) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getString();
      } else if (instanceClass == Integer.class || instanceClass == int.class
          || instanceClass == Long.class || instanceClass == long.class
          || instanceClass == Short.class || instanceClass == short.class
          || instanceClass == Byte.class || instanceClass == byte.class
          || instanceClass == BigInteger.class) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getInteger();
      } else if (List.class.isAssignableFrom(instanceClass)) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getSequence();
      } else if (Set.class.isAssignableFrom(instanceClass)) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getSet();
      } else if (Collection.class.isAssignableFrom(instanceClass)) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getCollection();
      } else if (instanceClass == Object.class) {
        return DeepOCLStandardLibraryImpl.INSTANCE.getOclAny();
      }
    }

    // All other data types map to themselves
    return dataType;
  }

  /**
   * @param type
   * @param isOrdered
   * @param isUnique
   * @return The OCLCollectionType
   */
  public EObject getOCLCollectionType(EObject type, boolean isOrdered, boolean isUnique) {
    EObject resultType = type;

    OCLFactory oclFactory = DeepOCLFactoryImpl.INSTANCE;

    if (isOrdered) {
      if (isUnique) {
        resultType = (EObject) oclFactory.createOrderedSetType(resultType);
      } else {
        resultType = (EObject) oclFactory.createSequenceType(resultType);
      }
    } else {
      if (isUnique) {
        resultType = (EObject) oclFactory.createSetType(resultType);
      } else {
        resultType = (EObject) oclFactory.createBagType(resultType);
      }
    }

    return resultType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getAllSupertypes(java.lang.
   * Object)
   */
  @Override
  public Collection<? extends EObject> getAllSupertypes(EObject classifier) {
    // TODO Auto-generated method stub
    if (classifier instanceof EClass) {
      return ((EClass) classifier).getEAllSuperTypes();
    } else if (classifier instanceof Clabject) {
      return ((Clabject) classifier).getSupertypes();
    } else {
      return Collections.emptySet();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isMany(java.lang.Object)
   */
  @Override
  public boolean isMany(Object metaElement) {
    //
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#asOCLType(java.lang.Object)
   */
  @Override
  public EObject asOCLType(EObject modelType) {
    // TODO Auto-generated method stub
    return modelType;
    // in ecore UMLReflectionImpl wird noch getOClTYPE(Object) aufgerufen->
    // vllt sp�ter
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getNestingPackage(java.lang.
   * Object)
   */
  @Override
  public EPackage getNestingPackage(EPackage pkg) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getNestedPackages(java.lang.
   * Object)
   */
  @Override
  public List<EPackage> getNestedPackages(EPackage pkg) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getPackage(java.lang.Object)
   */
  @Override
  public EPackage getPackage(EObject classifier) {
    // TODO Auto-generated method stub

    if (classifier instanceof EClass) {
      return ((EClass) classifier).getEPackage();
    }
    return ((EClass) classifier.eClass()).getEPackage();

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getClassifiers(java.lang.Object)
   */
  @Override
  public List<EObject> getClassifiers(EPackage pkg) {
    // TODO Auto-generated method stub
    return new ArrayList<EObject>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getOwningClassifier(java.lang.
   * Object)
   */
  @Override
  public EObject getOwningClassifier(Object feature) {
    // TODO Auto-generated method stub
    if (feature instanceof EStructuralFeature) {
      return ((EStructuralFeature) feature).getEContainingClass();

    }
    if (feature instanceof EOperation) {
      if (((EOperation) feature).getEContainingClass() == null) {
        if (((EOperation) feature).getEType().getName() == "EObject") { // this
          // is
          // the
          // case
          // for
          // the
          // oclastype
          // op
          return DeepOCLStandardLibraryImpl.INSTANCE.getT();
        }
      }
      return ((EOperation) feature).getEContainingClass();
    } else if (feature instanceof Feature) {
      return ((Feature) feature).getClabject();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getSignals(java.lang.Object)
   */
  @Override
  public List<EObject> getSignals(EObject owner) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.utilities.UMLReflection#createOperation(java.lang.String,
   * java.lang.Object, java.util.List, java.util.List)
   */
  @Override
  public EObject createOperation(String name, EObject resultType, List<String> paramNames,
      List<EObject> paramTypes) {
    // nearly the same as in Ecore UMLReflectionImpl
    if (name != null && name != PredefinedType.OCL_IS_IN_STATE_NAME
        && name != PredefinedType.TO_STRING_NAME && name != PredefinedType.OCL_AS_SET_NAME) { // there
                                                                                              // have
                                                                                              // to
                                                                                              // be
      // made other
      // adaption to
      // allow these
      // tyoes als,
      // later...
      EOperation result = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEOperation();
      result.setName(name);
      if (resultType instanceof EClassifier) {
        result.setEType((EClassifier) resultType);
      } else {
        result.setEType(resultType.eClass());
      }

      int i = 0;
      for (String pname : paramNames) {
        EParameter param = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEParameter();

        param.setName(pname);
        param.setEType((paramTypes.get(i++)).eClass());

        result.getEParameters().add(param);
      }
      return result;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getParameters(java.lang.Object)
   */
  @Override
  public List<EObject> getParameters(EObject operation) {
    // TODO Auto-generated method stub
    List<EObject> result = new ArrayList<EObject>();
    if (operation != null) {
      if (operation instanceof EOperation) {
        Object[] parametersArray = ((BasicEList<?>) ((EOperation) operation).getEParameters())
            .toArray();
        ArrayList<Object> parametersList = new ArrayList<>(Arrays.asList(parametersArray));

        for (Object object : parametersList) {
          result.add((EObject) object);
        }
        return result;

      }
      Object[] parametersArray = ((BasicEList<?>) ((Method) operation).getInput()).toArray();
      ArrayList<Object> parametersList = new ArrayList<>(Arrays.asList(parametersArray));

      for (Object object : parametersList) {
        result.add((EObject) object);
      }
    }
    return result;

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getOperations(java.lang.Object)
   */
  @Override
  public List<EObject> getOperations(EObject classifier) {
    // TODO Auto-generated method stub

    List<EObject> result = new ArrayList<EObject>();
    if (classifier != null) {
      if (classifier instanceof AnyType) {
        return result;
      }
      if (classifier instanceof EClass) {
        Object[] methodsArray = ((BasicEList<?>) ((EClass) classifier).getEAllOperations())
            .toArray();
        ArrayList<Object> methodsList = new ArrayList<>(Arrays.asList(methodsArray));

        for (Object object : methodsList) {
          result.add((EObject) object);
        }
        return result;

      }
      if (classifier instanceof Clabject) {
        Object[] methodsArray = ((BasicEList<?>) ((Clabject) classifier).getAllMethods()).toArray();
        ArrayList<Object> methodsList = new ArrayList<>(Arrays.asList(methodsArray));

        for (Object object : methodsList) {
          result.add((EObject) object);
        }
        return result;
      }

      result = Collections.emptyList();
      return result;

    }

    else {
      result = Collections.emptyList();
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#createProperty(java.lang.String,
   * java.lang.Object)
   */
  @Override
  public Attribute createProperty(String name, EObject resultType) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getAttributes(java.lang.Object)
   */
  @Override
  public List<EObject> getAttributes(EObject classifier) {
    // TODO Auto-generated method stub
    List<EObject> result = new ArrayList<EObject>();

    if (classifier != null) {
      if (classifier instanceof AnyType) {
        return result;
      } else if (classifier instanceof PrimitiveType
          || classifier instanceof org.melanee.ocl.service.ocl.lml.PrimitiveType) {
        return result;
      } else if (classifier instanceof EClass) {
        Object[] attributesArray = ((BasicEList<?>) ((EClass) classifier)
            .getEAllStructuralFeatures()).toArray();
        ArrayList<Object> attributesList = new ArrayList<>(Arrays.asList(attributesArray));
        for (Object object : attributesList) {
          result.add((EObject) object);
        }
        return result;
      }
      // MELOCL-9 Inheritance etc. do not have ontological attributes etc.
      else if (!(classifier instanceof Clabject)) {
        Object[] attributesArray = ((BasicEList<?>) classifier.eClass().getEAllStructuralFeatures())
            .toArray();
        ArrayList<Object> attributesList = new ArrayList<>(Arrays.asList(attributesArray));
        for (Object object : attributesList) {
          result.add((EObject) object);
        }
        return result;
      }

      Object[] attributesArray = ((BasicEList<?>) ((Clabject) classifier).getAllAttributes())
          .toArray();
      ArrayList<Object> attributesList = new ArrayList<>(Arrays.asList(attributesArray));
      NavigationAttribute navigationAttribute;
      // is this code ever reached(look at else if statement above)???
      // TODO
      // check usage
      if (classifier instanceof Clabject) {
        for (Connection connection : ((Clabject) classifier).getConnections()) {
          // only add connection as navigation Attribute if connection
          // has name
          if (connection.getName() != null && connection.getName() != "") {
            navigationAttribute = new NavigationAttributeImpl();
            navigationAttribute.setName(connection.getName());
            navigationAttribute.setClabject((Clabject) classifier);
            navigationAttribute.setNavigationValue(connection);
            navigationAttribute.setSingle(true);
            for (ConnectionEnd connectionEnd : connection.getAllConnectionEnd()) {

              if (connectionEnd.getDestination() != classifier) {

                if (!(connectionEnd.getUpper() <= 1 && connectionEnd.getUpper() >= 0)) {
                  navigationAttribute.setSingle(false);
                  break;
                }
              }
            }
            attributesList.add(navigationAttribute);
          }
          // get AllConnectionEnds
          for (ConnectionEnd connectionEnd : connection.getAllConnectionEnd()) {
            // only add nav attribute when not referring to self
            if (connectionEnd.getDestination() != classifier) {
              navigationAttribute = new NavigationAttributeImpl();
              if (connectionEnd.getMoniker() != null && connectionEnd.getMoniker() != "") {
                navigationAttribute = new NavigationAttributeImpl();
                navigationAttribute.setName(connectionEnd.getMoniker());
                navigationAttribute.setClabject((Clabject) classifier);
                navigationAttribute.setConnection(connection);
                navigationAttribute.setNavigationValue(connectionEnd.getDestination());
                if (connectionEnd.getUpper() <= 1 && connectionEnd.getUpper() >= 0) {
                  navigationAttribute.setSingle(true);
                }
                attributesList.add(navigationAttribute);
              }

              navigationAttribute = new NavigationAttributeImpl();
              navigationAttribute.setName(connectionEnd.getDestination().getName());
              navigationAttribute.setClabject((Clabject) classifier);
              navigationAttribute.setConnection(connection);
              navigationAttribute.setNavigationValue(connectionEnd.getDestination());
              if (connectionEnd.getUpper() <= 1 && connectionEnd.getUpper() >= 0) {
                navigationAttribute.setSingle(true);
              }
              attributesList.add(navigationAttribute);
            }
          }
        }

        // go once again over attribute list and check if there are
        // navigations with same name->change single to false
        for (Object attribute : attributesList) {
          if (attribute instanceof NavigationAttribute) {
            NavigationAttribute navAttribute = (NavigationAttribute) attribute;
            HashSet<Clabject> participants = DeepOCLUtilities
                .getAllParticipantsForName(navAttribute.getClabject(), navAttribute.getName());
            if (participants.size() > 1) {
              navAttribute.setSingle(false);
            }
          }
        }
      }

      if (classifier instanceof Connection) {
        for (ConnectionEnd connectionEnd : ((Connection) classifier).getAllConnectionEnd()) {
          navigationAttribute = new NavigationAttributeImpl();
          if (connectionEnd.getMoniker() != null && connectionEnd.getMoniker() != "") {
            navigationAttribute.setName(connectionEnd.getMoniker());
            navigationAttribute.setClabject((Clabject) classifier);
            navigationAttribute.setNavigationValue(connectionEnd.getDestination());
            if (connectionEnd.getUpper() <= 1 && connectionEnd.getUpper() >= 0) {
              navigationAttribute.setSingle(true);
            }
          } else {
            navigationAttribute.setName(connectionEnd.getDestination().getName());
            navigationAttribute.setClabject((Clabject) classifier);
            navigationAttribute.setNavigationValue(connectionEnd.getDestination());
            if (connectionEnd.getUpper() <= 1 && connectionEnd.getUpper() >= 0) {
              navigationAttribute.setSingle(true);
            }
          }
          attributesList.add(navigationAttribute);
        }

        // go once again over attribute list and check if there are
        // navigations with same name->change single to false
        for (Object attribute : attributesList) {
          if (attribute instanceof NavigationAttribute) {
            NavigationAttribute navAttribute = (NavigationAttribute) attribute;
            HashSet<Clabject> participants = DeepOCLUtilities
                .getAllParticipantsForName(navAttribute.getClabject(), navAttribute.getName());
            if (participants.size() > 1) {
              navAttribute.setSingle(false);
            }
          }
        }
      }

      Clabject clabject = (Clabject) classifier;
      LinguisticIdentifierAttribute linguisticIdentifierAttribute = new LinguisticIdentifierAttributeImpl();
      linguisticIdentifierAttribute.setName(LinguisticIdentifierAttributeImpl.IDENTIFIER_NAME);
      linguisticIdentifierAttribute.setClabject(clabject);
      attributesList.add(linguisticIdentifierAttribute);

      LevelCastAttribute levelCastAttribute;
      List<Object> typesArray = DeepOCLUtilities.getAllTypes(clabject);
      for (int i = 0; i < typesArray.size(); i++) {
        Clabject actType = (Clabject) typesArray.get(i);
        levelCastAttribute = new LevelCastAttributeImpl();
        levelCastAttribute.setName("_" + actType.getName() + "_");
        levelCastAttribute.setCast(actType);
        attributesList.add(levelCastAttribute);
      }

      List<Object> instancesArray = DeepOCLUtilities.getAllInstances(clabject);
      for (int i = 0; i < instancesArray.size(); i++) {
        Clabject actInstance = (Clabject) instancesArray.get(i);
        levelCastAttribute = new LevelCastAttributeImpl();
        levelCastAttribute.setName("_" + actInstance.getName() + "_");
        levelCastAttribute.setCast(actInstance);
        attributesList.add(levelCastAttribute);
      }

      for (Object object : attributesList) {
        result.add((EObject) object);
      }

    } else {
      result = Collections.emptyList();
    }

    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isQuery(java.lang.Object)
   */
  @Override
  public boolean isQuery(EObject operation) {
    // TODO Auto-generated method stub
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isStatic(java.lang.Object)
   */
  @Override
  public boolean isStatic(Object feature) {
    // TODO Auto-generated method stub
    if (feature instanceof EOperation) {
      if (((EOperation) feature).getName().equals("allInstances")) {
        return true;
      }
    }
    if (feature instanceof Attribute) {
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#setIsStatic(java.lang.Object,
   * boolean)
   */
  @Override
  public boolean setIsStatic(Object feature, boolean isStatic) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getQualifiers(java.lang.Object)
   */
  @Override
  public List<EObject> getQualifiers(EObject property) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isAssociationClass(java.lang.
   * Object)
   */
  @Override
  public boolean isAssociationClass(EObject type) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getAssociationClass(java.lang.
   * Object)
   */
  @Override
  public Clabject getAssociationClass(EObject property) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getMemberEnds(java.lang.Object)
   */
  @Override
  public List<EObject> getMemberEnds(EObject associationClass) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isOperation(java.lang.Object)
   */
  @Override
  public boolean isOperation(Object metaElement) {
    // TODO Auto-generated method stub
    if (metaElement instanceof EOperation) {
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isPackage(java.lang.Object)
   */
  @Override
  public boolean isPackage(Object metaElement) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isProperty(java.lang.Object)
   */
  @Override
  public boolean isProperty(Object metaElement) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isClassifier(java.lang.Object)
   */
  @Override
  public boolean isClassifier(Object metaElement) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isClass(java.lang.Object)
   */
  @Override
  public boolean isClass(Object metaElement) {
    // TODO Auto-generated method stub
    return metaElement instanceof EClass || metaElement instanceof Clabject;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isConstraint(java.lang.Object)
   */
  @Override
  public boolean isConstraint(Object metaElement) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isDataType(java.lang.Object)
   */
  @Override
  public boolean isDataType(Object metaElement) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isStereotype(java.lang.Object)
   */
  @Override
  public boolean isStereotype(EObject type) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getStereotypeApplication(java.
   * lang.Object, java.lang.Object)
   */
  @Override
  public Object getStereotypeApplication(Object baseElement, EObject stereotype) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isEnumeration(java.lang.Object)
   */
  @Override
  public boolean isEnumeration(EObject type) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getEnumeration(java.lang.Object)
   */
  @Override
  public EObject getEnumeration(Enumeration enumerationLiteral) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.utilities.UMLReflection#getEnumerationLiterals(java.lang.
   * Object)
   */
  @Override
  public List<Enumeration> getEnumerationLiterals(EObject enumerationType) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getEnumerationLiteral(java.lang.
   * Object, java.lang.String)
   */
  @Override
  public Enumeration getEnumerationLiteral(EObject enumerationType, String literalName) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#isComparable(java.lang.Object)
   */
  @Override
  public boolean isComparable(EObject type) {
    // TODO Auto-generated method stub
    if (type instanceof EClassifier) {
      Class<?> javaClass = ((EClassifier) type).getInstanceClass();
      if (javaClass != null)
        return Comparable.class.isAssignableFrom(javaClass);
      else {
        if (type instanceof org.melanee.ocl.service.ocl.lml.PrimitiveType) {
          return ((org.melanee.ocl.service.ocl.lml.PrimitiveType) type)
              .getName() == org.melanee.ocl.service.ocl.lml.PrimitiveType.INTEGER_NAME
              || ((org.melanee.ocl.service.ocl.lml.PrimitiveType) type)
                  .getName() == org.melanee.ocl.service.ocl.lml.PrimitiveType.REAL_NAME
              || ((org.melanee.ocl.service.ocl.lml.PrimitiveType) type)
                  .getName() == org.melanee.ocl.service.ocl.lml.PrimitiveType.STRING_NAME;
        }
      }
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#createCallOperationAction(java.
   * lang.Object)
   */
  @Override
  public CallOperationAction createCallOperationAction(EObject operation) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getOperation(java.lang.Object)
   */
  @Override
  public Method getOperation(CallOperationAction callOperationAction) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.utilities.UMLReflection#createSendSignalAction(java.lang.
   * Object)
   */
  @Override
  public SendSignalAction createSendSignalAction(EObject signal) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getSignal(java.lang.Object)
   */
  @Override
  public Clabject getSignal(SendSignalAction sendSignalAction) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#createConstraint()
   */
  @Override
  public Constraint createConstraint() {
    // TODO Auto-generated method stub
    ConstraintImpl constraint = new ConstraintImpl();
    return constraint;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getStereotype(java.lang.Object)
   */
  @Override
  public String getStereotype(Constraint constraint) {
    // TODO Auto-generated method stub
    return constraint.getStereotype();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#setStereotype(java.lang.Object,
   * java.lang.String)
   */
  @Override
  public void setStereotype(Constraint constraint, String stereotype) {
    constraint.setStereotype(stereotype);

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getConstraintName(java.lang.
   * Object)
   */
  @Override
  public String getConstraintName(Constraint constraint) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#setConstraintName(java.lang.
   * Object, java.lang.String)
   */
  @Override
  public void setConstraintName(Constraint constraint, String name) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#createExpressionInOCL()
   */
  @Override
  public ExpressionInOCL<EObject, EObject> createExpressionInOCL() {
    ExpressionInOCLImpl expressionInOCL = new ExpressionInOCLImpl();
    return expressionInOCL;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getSpecification(java.lang.
   * Object)
   */
  @Override
  public ExpressionInOCL<EObject, EObject> getSpecification(Constraint constraint) {
    // TODO Auto-generated method stub
    return constraint.getSpecification();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#setSpecification(java.lang.
   * Object, org.eclipse.ocl.utilities.ExpressionInOCL)
   */
  @Override
  public void setSpecification(Constraint constraint,
      ExpressionInOCL<EObject, EObject> specification) {
    constraint.setSpecification(specification);

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getConstraint(org.eclipse.ocl.
   * utilities.ExpressionInOCL)
   */
  @Override
  public Constraint getConstraint(ExpressionInOCL<EObject, EObject> specification) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.utilities.UMLReflection#getConstrainedElements(java.lang.
   * Object)
   */
  @Override
  public List<? extends EObject> getConstrainedElements(Constraint constraint) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#addConstrainedElement(java.lang.
   * Object, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public void addConstrainedElement(Constraint constraint, EObject constrainedElement) {
    // TODO Auto-generated method stub
    if (constrainedElement instanceof EModelElement) { // only works for
      // linguistc context
      constraint.getConstrainedElements().add((EModelElement) constrainedElement);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getName(java.lang.Object)
   */
  @Override
  public String getName(Object namedElement) {
    // TODO Auto-generated method stub
    if (namedElement instanceof Element) {
      return ((Element) namedElement).getName();
    }
    if (namedElement instanceof ENamedElement) {
      return ((ENamedElement) namedElement).getName();
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getQualifiedName(java.lang.
   * Object)
   */
  @Override
  public String getQualifiedName(Object namedElement) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getDescription(java.lang.Object)
   */
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getDescription(java.lang.Object)
   */
  @Override
  public String getDescription(Object namedElement) {
    // TODO Auto-generated method stub

    if (namedElement instanceof ETypedElement) {
      if (namedElement instanceof EParameter) {
        if (((EParameter) namedElement).getName() == "typespec") {
          return "OCLType";
        }
        if (((EParameter) namedElement).getName() == "s") {
          return "String";
        }
        if (((EParameter) namedElement).getName() == "index"
            || ((EParameter) namedElement).getName() == "lower"
            || ((EParameter) namedElement).getName() == "upper") {
          return "Integer";
        }
      }
      EClassifier type = ((ETypedElement) namedElement).getEType();
      return (type == null) ? "Void" : type.getName();
    } else if (namedElement instanceof EEnumLiteral) {
      return ((EEnumLiteral) namedElement).getEEnum().getName();
    } else if (namedElement instanceof NavigationAttribute) {
      NavigationAttribute navAttribute = (NavigationAttribute) namedElement;
      return navAttribute.getNavigationValue().getName();
    } else if (namedElement instanceof LinguisticIdentifierAttribute) {
      LinguisticIdentifierAttribute linguisticIdentifierAttribute = (LinguisticIdentifierAttribute) namedElement;
      return linguisticIdentifierAttribute.getClabject().eClass().getName() + (" (Linguistic)");
    } else if (namedElement instanceof Attribute) {
      Attribute attribute = (Attribute) namedElement;
      if (attribute.getDatatype() != null)
        return attribute.getDatatype();
      return "String";
    } else if (namedElement instanceof org.melanee.ocl.service.ocl.lml.PrimitiveType) {
      org.melanee.ocl.service.ocl.lml.PrimitiveType primitiveType = (org.melanee.ocl.service.ocl.lml.PrimitiveType) namedElement;
      return primitiveType.getName();
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.UMLReflection#getCommonSuperType(java.lang.
   * Object, java.lang.Object)
   */
  @Override
  public Clabject getCommonSuperType(EObject type1, EObject type2) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.utilities.UMLReflection#getRelationship(java.lang.Object,
   * java.lang.Object)
   */
  @Override
  public int getRelationship(EObject type1, EObject type2) {
    // TODO Auto-generated method stub
    if (type1 instanceof Clabject && type2 == null) {
      return 1;
    }
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.utilities.UMLReflection#setName(org.eclipse.ocl.utilities
   * .TypedElement, java.lang.String)
   */
  @Override
  public void setName(TypedElement<EObject> element, String name) {
    element.setName(name);

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.utilities.UMLReflection#setType(org.eclipse.ocl.utilities
   * .TypedElement, java.lang.Object)
   */
  @Override
  public void setType(TypedElement<EObject> element, EObject type) {
    element.setType(type);

  }

  /**
   * @param typedElement
   * @return if the typedElement has an upper bound > 1
   */
  static boolean isMany(ETypedElement typedElement) {
    if (typedElement instanceof EStructuralFeature) {
      int upperBound = typedElement.getUpperBound();
      return (upperBound > 1) || (upperBound < 0);
    }
    return typedElement.isMany();
  }

}
