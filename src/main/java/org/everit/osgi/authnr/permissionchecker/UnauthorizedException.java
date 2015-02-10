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

import java.util.Arrays;

public class UnauthorizedException extends RuntimeException {

    private static String createMessage(final long[] authorizationScope, final long targetResourceId,
            final String[] actions) {
        return "Method invocation not allowed, not authorized! [authorizationScope="
                + Arrays.toString(authorizationScope) + ", targetResourceId=" + targetResourceId + ", actions="
                + Arrays.toString(actions) + "]";
    }

    private static final long serialVersionUID = 6052511160822385474L;

    private final long[] authorizationScope;

    private final long targetResourceId;

    private final String[] actions;

    public UnauthorizedException(final long[] authorizationScope, final long targetResourceId, final String[] actions) {
        this(UnauthorizedException.createMessage(authorizationScope, targetResourceId, actions),
                authorizationScope, targetResourceId, actions);
    }

    public UnauthorizedException(final String message, final long[] authorizationScope, final long targetResourceId,
            final String[] actions) {
        super(message);
        this.authorizationScope = authorizationScope;
        this.targetResourceId = targetResourceId;
        this.actions = actions;
    }

    public String[] getActions() {
        return actions;
    }

    public long[] getAuthorizationScope() {
        return authorizationScope;
    }

    public long getTargetResourceId() {
        return targetResourceId;
    }

}
