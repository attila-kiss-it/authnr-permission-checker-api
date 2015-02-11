/**
 * This file is part of Everit - Authenticated Authorization Permission Checker API.
 *
 * Everit - Authenticated Authorization Permission Checker API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Authenticated Authorization Permission Checker API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Authenticated Authorization Permission Checker API.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.authnr.permissionchecker;

/**
 * Checking permissions of an authenticated resource.
 */
public interface AuthnrPermissionChecker {

    /**
     * Checks the permissions exactly the same way as the {@link #hasPermission(long, String...)} method, but it throws
     * an {@link UnauthorizedException} in case of permission check fails.
     *
     * @param targetResourceId
     *            The id of the resource that the authorized resource may have permission on.
     * @param actions
     *            One or more actions. If multiple actions are provided, the function returns true if the authorized
     *            resource can do any of the actions on the target. At least one action must be defined.
     * @throws UnauthorizedException
     *             if there is no available permission or if target resource does not exist.
     * @throws NullPointerException
     *             if action parameter is a null array or one of the actions is null.
     * @throws IllegalArgumentException
     *             if a zero length array argument is passed for the action parameter.
     */
    default void checkPermission(final long targetResourceId, final String... actions) {
        if (!hasPermission(targetResourceId, actions)) {
            throw new UnauthorizedException(getAuthorizationScope(), targetResourceId, actions);
        }
    }

    /**
     * Getting the resources that the current authenticated resource inherits the rights from. In practice these could
     * mean user groups or roles that a user is assigned to.
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
     *            The id of the resource that the authorized resource may have permission on.
     * @param actions
     *            One or more actions. If multiple actions are provided, the function returns true if the authorized
     *            resource can do any of the actions on the target. At least one action must be defined.
     * @return <code>true</code> if there is available permission, <code>false</code> otherwise. The function returns
     *         false if the authorized resource or target resource does not exist.
     * @throws NullPointerException
     *             if action parameter is a null array or one of the actions is null.
     * @throws IllegalArgumentException
     *             if a zero length array argument is passed for the action parameter.
     */
    boolean hasPermission(long targetResourceId, String... actions);

}
