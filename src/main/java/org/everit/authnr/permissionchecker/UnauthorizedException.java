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

import java.util.Arrays;

/**
 * Exception thrown when the default implementation of
 * {@link AuthnrPermissionChecker#checkPermission(long, String...)} fails.
 */
public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = 6052511160822385474L;

  private static String createMessage(final long[] authorizationScope, final long targetResourceId,
      final String[] actions) {
    return "Method invocation not allowed, not authorized! [authorizationScope="
        + Arrays.toString(authorizationScope) + ", targetResourceId=" + targetResourceId
        + ", actions="
        + Arrays.toString(actions) + "]";
  }

  public final String[] actions;

  public final long[] authorizationScope;

  public final long targetResourceId;

  public UnauthorizedException(final long[] authorizationScope, final long targetResourceId,
      final String... actions) {
    this(UnauthorizedException.createMessage(authorizationScope, targetResourceId, actions),
        authorizationScope, targetResourceId, actions);
  }

  /**
   * Constructor.
   */
  public UnauthorizedException(final String message, final long[] authorizationScope,
      final long targetResourceId,
      final String... actions) {
    super(message);
    this.authorizationScope = authorizationScope;
    this.targetResourceId = targetResourceId;
    this.actions = actions;
  }

}
