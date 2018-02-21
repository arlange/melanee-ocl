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
package org.melanee.ocl.service.ocl.lml.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.ocl.service.ocl.lml.NavigationAttribute;

/**
 * This class provides LML specific utilities
 * 
 * @author Dominik Kantner
 *
 */
public final class DeepOCLUtilities {

  /**
   * This method returns all participants for a given name and clabjects
   * 
   * @param clabject
   * @param name
   * @return A HashSet of clabjects
   */
  public static HashSet<Clabject> getAllParticipantsForName(Clabject clabject, String name) {

    HashSet<Clabject> participants = new HashSet<Clabject>();
    if (clabject instanceof Connection) {
      Connection connection = (Connection) clabject;
      for (ConnectionEnd connectionEnd : connection.getAllConnectionEnd()) {
        if (connectionEnd.getMoniker() != null && connectionEnd.getMoniker().equals(name)
            || (connectionEnd.getDestination() != null
                && connectionEnd.getDestination().getName().equals(name))) {
          if (connectionEnd.getDestination() != clabject)
            participants.add(connectionEnd.getDestination());
        }
      }
    }
    for (Connection connection : clabject.getConnections()) {
      if (connection.getName() != null && connection.getName().equals(name)) {
        participants.add(connection);
      }
      for (ConnectionEnd connectionEnd : connection.getAllConnectionEnd()) {
        if ((connectionEnd.getMoniker() != null && connectionEnd.getMoniker().equals(name))
            || (connectionEnd.getDestination() != null
                && connectionEnd.getDestination().getName() != null
                && connectionEnd.getDestination().getName().equals(name))) {
          if (connectionEnd.getDestination() != clabject)
            participants.add(connectionEnd.getDestination());
        }
      }
    }
    return participants;
  }

  /**
   * @param navAttribute
   * @param source
   * @return A ArrayList of participant of a NavigationAttribute from an instanc
   *         viepoint
   */
  public static ArrayList<Clabject> getAllSubtypeParticpants(NavigationAttribute navAttribute,
      Clabject source) {

    if (navAttribute.getConnection() != null) {
      ArrayList<Clabject> participants = new ArrayList<Clabject>();
      List<Clabject> superTypeParticants = getSuperTypeParticipants(navAttribute);
      List<Connection> superTypeConnections = getSuperTypeConnections(navAttribute);
      for (Connection superTypeConnection : superTypeConnections) {
        for (Object instance : getAllInstances(superTypeConnection)) {
          Connection connectionInstance = (Connection) instance;
          if (connectionInstance.getParticipants().contains(source)) {
            for (Clabject participant : connectionInstance.getParticipants()) {
              if (participant != source && !participants.contains(participant)) {

                List<Object> allTypes = getAllTypes(participant);
                for (Object aType : allTypes) {
                  if (superTypeParticants.contains(aType)) {
                    // check multiplicit pf connectionEnd so that for multiple connectionEnds the
                    // particpants are inserted multiple times
                    ConnectionEnd connectionEnd = getConnectionEndForParticipant(connectionInstance,
                        participant);
                    if (connectionEnd != null && connectionEnd.getUpper() > 1) {
                      int participantAmount = connectionEnd.getUpper();
                      int count = 0;
                      while (count < participantAmount) {
                        participants.add(participant);
                        count++;
                      }
                    } else {
                      participants.add(participant);
                    }
                  }
                }

              }
            }
          }
        }
      }
      return participants;
    }
    if (navAttribute.getClabject() instanceof Connection) {
      List<ConnectionEnd> partList = navAttribute.getClabject().getAllNavigationsAsDestination();
      List<Clabject> connections = new ArrayList<Clabject>();
      for (ConnectionEnd part : partList) {
        connections.add(part.getConnection());
      }
      if (!connections.contains(navAttribute.getNavigationValue())) {
        ArrayList<Clabject> participants = new ArrayList<Clabject>();
        Clabject clabject = navAttribute.getNavigationValue();
        for (Object clabjectInstance : getAllInstances(clabject)) {
          if (((Clabject) clabjectInstance).getConnections().contains(source)) {
            participants.add((Clabject) clabjectInstance);
          }
        }
        return participants;
      }

    }
    ArrayList<Clabject> connections = new ArrayList<Clabject>();
    List<Connection> superTypeConnections = getSuperTypeConnections(navAttribute);
    for (Connection superTypeConnection : superTypeConnections) {
      for (Object instance : getAllInstances(superTypeConnection)) {
        Connection connectionInstance = (Connection) instance;
        if (connectionInstance.getParticipants().contains(source)
            && !connections.contains(connectionInstance)) {
          connections.add((Clabject) instance);
        }
      }
    }
    return connections;
  }

  /**
   * @param navAttribute
   * @return A list of superTypeParticipants
   */
  public static List<Clabject> getSuperTypeParticipants(NavigationAttribute navAttribute) {
    ArrayList<Clabject> superTypeParticipants = new ArrayList<Clabject>();
    List<Connection> superTypeConnections = getSuperTypeConnections(navAttribute);
    for (Connection superTypeConnection : superTypeConnections) {
      List<ConnectionEnd> allConnectionEnds = superTypeConnection.getConnectionEnd();
      for (ConnectionEnd connectionEnd : allConnectionEnds) {
        if ((connectionEnd.getMoniker() != null
            && connectionEnd.getMoniker().equals(navAttribute.getName()))
            || (connectionEnd.getDestination() != null
                && connectionEnd.getDestination().getName().equals(navAttribute.getName()))) {
          superTypeParticipants.add(connectionEnd.getDestination());
        }
      }
    }
    return superTypeParticipants;

  }

  /**
   * @param navAttribute
   * @return A list of superTypeConnections
   */
  public static List<Connection> getSuperTypeConnections(NavigationAttribute navAttribute) {

    Clabject clabject = navAttribute.getClabject();
    List<Connection> superTypeConnection = new ArrayList<Connection>();
    List<Connection> clabjectConnections = clabject.getConnections();
    for (Connection clabjectConnection : clabjectConnections) {
      List<Clabject> participants = clabjectConnection.getParticipants();
      List<String> particpantNames = new ArrayList<String>();
      for (Clabject particpant : participants) {
        particpantNames.add(particpant.getName());
      }
      if (navAttribute.getConnection() != null) {
        if (clabjectConnection.getMoniker().contains(navAttribute.getName())
            || particpantNames.contains(navAttribute.getName())) {
          superTypeConnection.add(clabjectConnection);
        }
      } else {
        if (clabjectConnection.getName() != null
            && clabjectConnection.getName().equals(navAttribute.getName())) {
          superTypeConnection.add(clabjectConnection);
        }
      }

    }
    return superTypeConnection;
  }

  /**
   * @param clabject
   * @return A list of all direct (also deep) types
   */
  public static List<Object> getAllDirectTypes(Clabject clabject) {
    Object[] directTypesArray = clabject.getTypes().toArray();
    ArrayList<Object> directTypes = new ArrayList<Object>(Arrays.asList(directTypesArray));

    if (directTypes.size() == 0) {
      return directTypes;
    }
    @SuppressWarnings("unchecked")
    List<Object> allDirectTypes = (List<Object>) directTypes.clone();
    for (Object type : directTypes) {
      allDirectTypes.addAll(getAllDirectTypes((Clabject) type));
    }
    return allDirectTypes;
  }

  /**
   * @param clabject
   * @return A list of all (also deep) types
   */
  public static List<Object> getAllTypes(Clabject clabject) {
    Object[] typesArray = clabject.getTypes().toArray();
    ArrayList<Object> types = new ArrayList<Object>(Arrays.asList(typesArray));

    if (types.size() == 0) {
      return types;
    }
    @SuppressWarnings("unchecked")
    List<Object> allTypes = (List<Object>) types.clone();
    for (Object type : types) {
      allTypes.addAll(getAllTypes((Clabject) type));
    }
    return allTypes;
  }

  /**
   * @param clabject
   * @return A list of all (also deep) instances
   */
  public static List<Object> getAllInstances(Clabject clabject) {
    Object[] instancesArray = clabject.getInstances().toArray();
    ArrayList<Object> instances = new ArrayList<Object>(Arrays.asList(instancesArray));

    if (instances.size() == 0) {
      return instances;
    }
    @SuppressWarnings("unchecked")
    List<Object> allInstances = (List<Object>) instances.clone();
    for (Object instance : instances) {
      allInstances.addAll(getAllInstances((Clabject) instance));
    }
    return allInstances;
  }

  /**
   * @param sourceClabject
   * @param distance
   * @return A List of (deep) instance with the given level instance from the
   *         sourceClabject
   */
  @SuppressWarnings("unchecked")
  public static List<Clabject> getInstancesWithDistance(Clabject sourceClabject, int distance) {
    ArrayList<Clabject> sourceClabjects = new ArrayList<Clabject>();
    ArrayList<Clabject> resultClabjects = new ArrayList<Clabject>();
    if (distance > 0) {
      sourceClabjects.add(sourceClabject);
      while (distance > 0) {
        resultClabjects.clear();
        for (Clabject sourceClab : sourceClabjects) {
          resultClabjects.addAll(sourceClab.getInstances());
        }
        sourceClabjects.clear();
        sourceClabjects = (ArrayList<Clabject>) resultClabjects.clone();
        distance--;
      }
    }
    return resultClabjects;
  }

  /**
   * @param connection
   * @param participant
   * @return The according ConnectionEnd for the participant
   */
  private static ConnectionEnd getConnectionEndForParticipant(Connection connection,
      Clabject participant) {
    for (ConnectionEnd part : connection.getAllConnectionEnd()) {
      if (part.getDestination() == participant) {
        return part;
      }
    }
    return null;
  }

}
