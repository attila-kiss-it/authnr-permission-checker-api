/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.biz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.authnr.permissionchecker;

/**
 * Checking permissions of an authenticated resource.
 */
public interface AuthnrPermissionChecker {

  /**
   * Checks the permissions exactly the same way as the {@link #hasPermission(long, String...)}
   * method, but it throws an {@link UnauthorizedException} in case of permission check fails.
   *
   * @param targetResourceId
   *          The id of the resource that the authorized resource may have permission on.
   * @param actions
   *          One or more actions. If multiple actions are provided, the function returns true if
   *          the authorized resource can do any of the actions on the target. At least one action
   *          must be defined.
   * @throws UnauthorizedException
   *           if there is no available permission or if target resource does not exist.
   * @throws NullPointerException
   *           if action parameter is a null array or one of the actions is null.
   * @throws IllegalArgumentException
   *           if a zero length array argument is passed for the action parameter.
   */
  default void checkPermission(final long targetResourceId, final String... actions) {
    if (!hasPermission(targetResourceId, actions)) {
      throw new UnauthorizedException(getAuthorizationScope(), targetResourceId, actions);
    }
  }

  /**
   * Getting the resources that the current authenticated resource inherits the rights from. In
   * practice these could mean user groups or roles that a user is assigned to.
   *
   * @return The parent resource IDs transitively and the authenticated resourceId.
   */
  long[] getAuthorizationScope();

  /**
   * There must be only one system resource and it has permission to do everything.
   *
   * @return The resource id of the system.
   */
  long getSystemResourceId();

  /**
   * Check whether the authenticated resource has the permission with the given parameters.
   *
   * @param targetResourceId
   *          The id of the resource that the authorized resource may have permission on.
   * @param actions
   *          One or more actions. If multiple actions are provided, the function returns true if
   *          the authorized resource can do any of the actions on the target. At least one action
   *          must be defined.
   * @return <code>true</code> if there is available permission, <code>false</code> otherwise. The
   *         function returns false if the authorized resource or target resource does not exist.
   * @throws NullPointerException
   *           if action parameter is a null array or one of the actions is null.
   * @throws IllegalArgumentException
   *           if a zero length array argument is passed for the action parameter.
   */
  boolean hasPermission(long targetResourceId, String... actions);

}
